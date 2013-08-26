require 'date'
class Envelope
  include ActiveModel::Validations

  attr_accessor :subject, :email_blurb, :document1, :document2, :stock, :sigs,
                :fields, :conds, :collabs, :paper, :attach, :markup, :reminders, :expirations, :recipients, :notification,
                :documents, :tabs, :options

  validates_presence_of :subject, :email_blurb, :recipients

  def valid?

  end

  def initialize(params)
    if !params.nil?
      #Process the options parameters
      @options = true
      @subject = params['subject']
      @email_blurb = params['emailBlurb']
      if(params.has_key?('file1'))
        @document1 = params['file1']
      end
      if(params.has_key?('file2'))
        @document2 = params['file2']
      end
      if(params.has_key?('stockdoc'))
        @stock = true
      end
      if(params.has_key?('addsig'))
        @sigs = true
      end
      if(params.has_key?('formfields'))
        @fields = true
      end
      if(params.has_key?('conditionalfields'))
        @conds = true
      end
      if(params.has_key?('collabfields'))
        @collabs = true
      end
      if(params.has_key?('enablepaper'))
        @paper = true
      end
      if(params.has_key?('signerattachment'))
        @attach = true
      end
      if(params.has_key?('enablemarkup'))
        @markup = true
      end
      @reminders = params['reminders']
      @expirations = params['expiration']
    else
      @options = false
    end
  end

  def fill_envelope_info
    self.tap do |e|
      e.subject = @subject
      e.email_blurb = @email_blurb

      reminder = nil
      expiration = nil

      if !@reminders.empty?
        #Populate the reminder field with a frequency of two
        delay = Date.strptime(@reminders, "%m/%d/%Y")
        reminder = Docusign::Reminders.new.tap do |r|
          r.reminder_enabled = true
          r.reminder_delay = (delay - Date.today).to_s
          r.reminder_frequency = 2
        end
      end
      if !@expirations.empty?
        #Populate the expiration field with the user's specifications
        expire = Date.strptime(@expirations, "%m/%d/%Y")
        expiration = Docusign::Expirations.new.tap do |ex|
          ex.expire_enabled =  true
          ex.expire_after = (expire - Date.today).to_s
          ex.expire_warn = ((expire - 1) - Date.today).to_s
        end
      end

      if !reminder.nil? || !expiration.nil?
        e.notification = Docusign::Notification.new.tap do |n|
          n.expirations = expiration
          n.reminders = reminder
        end
      end
    end
  end

  def create_default_recipients(recipient_count, email)

    self.tap do |e|
      e.recipients = Array.new

      #Create the first recipient with the user's information
      recipient = Docusign::Recipient.new.tap do |r|
        r.userName = email
        r.email = email
        r.iD = 1
        r.type = Docusign::RecipientTypeCode::Signer
        r.captiveInfo = Docusign::RecipientCaptiveInfo.new.tap do |c|
          c.clientUserId = "1"
        end
      end
      e.recipients << recipient

      if recipient_count > 1
        #Create the second recipient with fake data
        recipient = Docusign::Recipient.new.tap do |r|
          r.userName = "DocuSign Recipient 2"
          r.email = "DocuSignRecipient2@mailinator.com"
          r.iD = 2
          r.type = Docusign::RecipientTypeCode::Signer
          r.captiveInfo = Docusign::RecipientCaptiveInfo.new.tap do |c|
            c.clientUserId = "2"
          end
        end
        e.recipients << recipient
      end
    end
  end

  def create_custom_recipients(params)
    self.tap do |e|
      e.recipients = Array.new
      params.each do |key, value|
        #Create each recipient according to the user's specifications
        if key.starts_with? 'RecipientName'
          id = key.split('').last
          uname = params['RecipientName' + id]
          email = params['RecipientEmail' + id]
          invite_email = params.has_key?('RecipientInviteToggle'+id)
          security = params['RecipientSecurity'+id]
          recipient = Docusign::Recipient.new.tap do |dr|
            dr.iD = id
            dr.userName = uname
            dr.email = email

            #Make all recipients after the first two Carbon Copy Recipients
            if id.to_i < 3
              dr.type = Docusign::RecipientTypeCode::Signer
            else
              dr.type = Docusign::RecipientTypeCode::CarbonCopy
            end
            if params.has_key?('RecipientRole' + id)
              dr.role_name = params['RecipientRole' + id]
            end
            if security.eql?('Access Code')
              dr.accessCode = params['RecipientSecuritySetting'+id]
            elsif security.eql?('ID Check')
              dr.requireIDLookup = true
            elsif security.eql?('Phone Authentication')
              dr.phoneAuthentication = Docusign::RecipientPhoneAuthentication.new.tap do |pa|
                pa.recipMayProvideNumber = true
                pa.validateRecipProvidedNumber = true
                pa.recordVoicePrint = false
              end
            end

            #Add captive information if the user has turned off email invitations
            if !invite_email && id.to_i < 3
              dr.captiveInfo = Docusign::RecipientCaptiveInfo.new.tap do |ci|
                ci.clientUserId = dr.iD
              end
            end
          end
          e.recipients << recipient
        end
      end
    end
  end

  def create_documents_and_tabs(recipient_count)
    self.tap do |e|
      e.documents = Array.new
      i = 1
      if @stock || !@options
        document = Docusign::Document.new.tap do |d|
          d.iD = i
          d.name = "DocuSign Demo Document"
          file = File.open(Rails.root.join('public', 'resources', 'DocuSignDemo11.pdf'), "rb")
          contents = file.read
          d.pDFBytes = Base64.encode64(contents)
          d.fileExtension = 'pdf'
          i = i + 1
        end
        e.documents << document
      elsif @options
        if !@document1.nil?
          document = Docusign::Document.new.tap do |d|
            d.iD = i
            d.name = @document1.original_filename
            file = File.open(@document1.path, "rb")
            contents = file.read
            d.pDFBytes = Base64.encode64(contents)
            d.fileExtension = @document1.content_type.to_s.split('/')[1]
            i = i + 1
          end
          e.documents << document
        end
        if @options && !@document2.nil?
          document = Docusign::Document.new.tap do |d|
            d.iD = i
            d.name = @document2.original_filename
            file = File.open(@document2.path, "rb")
            contents = file.read
            d.pDFBytes = Base64.encode64(contents)
            d.fileExtension = @document2.content_type.to_s.split('/')[1]
            i = i + 1
          end
          e.documents << document
        end
      end

      e.tabs = Array.new
      #Basic Company tab
      tab = Docusign::Tab.new.tap do |t|
        if !@document1.nil?
            t.documentID = 1
            t.pageNumber = 1
        elsif !@document2.nil?
            t.documentID = 2
            t.pageNumber = 1
        else
            t.documentID = 1               
            t.pageNumber = 2
        end
        t.recipientID = 1
        t.xPosition = 342
        t.yPosition = 303
        t.type = Docusign::TabTypeCode::Company
      end
      e.tabs << tab

      if !@options || @sigs
        #Basic InitialHere tab
        tab = Docusign::Tab.new.tap do |t|
          if !@document1.nil?
            t.documentID = 1
            t.pageNumber = 1
          elsif !@document2.nil?
            t.documentID = 2
            t.pageNumber = 1
          else
            t.documentID = 1
            t.pageNumber = 3
          end     
          t.recipientID = 1         
          t.xPosition = 454
          t.yPosition = 281
          t.type = Docusign::TabTypeCode::InitialHere
        end
        e.tabs << tab

        #Basic SignHere tab
        tab = Docusign::Tab.new.tap do |t|
          if !@document1.nil?
            t.documentID = 1
            t.pageNumber = 1
          elsif !@document2.nil?
            t.documentID = 2
            t.pageNumber = 1
          else
            t.documentID = 1
            t.pageNumber = 2
          end
          t.recipientID = 1
          t.xPosition = 338
          t.yPosition = 330
          t.type = Docusign::TabTypeCode::SignHere
        end
        e.tabs << tab

        #Basic FullName Anchor tab
        tab = Docusign::Tab.new.tap do |t|
          if !@document1.nil?
            t.documentID = 1
            t.pageNumber = 1
          elsif !@document2.nil?
            t.documentID = 2
            t.pageNumber = 1
          else
            t.documentID = 1
            t.pageNumber = 2
          end
          t.recipientID = 1
          t.type = Docusign::TabTypeCode::FullName
          t.anchorTabItem = Docusign::AnchorTab.new.tap do |a|
            a.anchorTabString = "printed"
            a.ignoreIfNotPresent = true
            a.xOffset = -97
            a.yOffset = -67
            a.unit = Docusign::UnitTypeCode::Pixels
          end
          t.name = "Full Name"
        end
        e.tabs << tab

        #Basic DateSigned tab
        tab = Docusign::Tab.new.tap do |t|
          if !@document1.nil?
            t.documentID = 1
            t.pageNumber = 1
          elsif !@document2.nil?
            t.documentID = 2
            t.pageNumber = 1
          else
            t.documentID = 1
            t.pageNumber = 2
          end
          t.recipientID = 1
          t.xPosition = 343
          t.yPosition = 492
          t.type = Docusign::TabTypeCode::DateSigned
        end
        e.tabs << tab

        #Scaled InitialHere tab
        tab = Docusign::Tab.new.tap do |t|
          if !@document1.nil?
            t.documentID = 1
            t.pageNumber = 1
          elsif !@document2.nil?
            t.documentID = 2
            t.pageNumber = 1
          else
            t.documentID = 1
            t.pageNumber = 3
          end
          t.recipientID = 1
          t.xPosition = 179
          t.yPosition = 583
          t.type = Docusign::TabTypeCode::InitialHere
          t.scaleValue = 0.6
        end
        e.tabs << tab

        if recipient_count > 1
          #Basic SignHere tab
          tab = Docusign::Tab.new.tap do |t|
            if !@document2.nil?
              t.documentID = 2
              t.pageNumber = 1
            elsif !@document1.nil?
              t.documentID = 1
              t.pageNumber = 1
            else
              t.documentID = 1
              t.pageNumber = 3
            end
            t.recipientID = 2
            t.xPosition = 339
            t.yPosition = 97
            t.type = Docusign::TabTypeCode::SignHere
          end
          e.tabs << tab

          #Basic DateSigned tab
          tab = Docusign::Tab.new.tap do |t|
            if !@document2.nil?
              t.documentID = 2
              t.pageNumber = 1
            elsif !@document1.nil?
              t.documentID = 1
              t.pageNumber = 1
            else
              t.documentID = 1
              t.pageNumber = 3
            end
            t.recipientID = 2
            t.xPosition = 343
            t.yPosition = 197
            t.type = Docusign::TabTypeCode::DateSigned
          end
          e.tabs << tab
        end
      end

      if !@options || @fields
        #Custom text tab
        tabtext = Docusign::Tab.new.tap do |t|
          if !@document2.nil?
            t.documentID = 2
            t.pageNumber = 1
          elsif !@document1.nil?
            t.documentID = 1
            t.pageNumber = 1
          else
            t.documentID = 1
            t.pageNumber = 3
          end
          t.recipientID = 1
          t.xPosition = 301
          t.yPosition = 414
          t.type = Docusign::TabTypeCode::Custom
          t.customTabType = Docusign::CustomTabType::Text
          t.name = "Favorite Color"
          t.tabLabel = "Favorite Color"
          t.value = "Green"
          t.customTabRequired = true
          t.customTabLocked = false

          if @collabs
            t.sharedTab = true
            t.requireInitialOnSharedTabChange = true
          end
        end
        e.tabs << tabtext
      end

      if !@options || @conds
        #Custom radio button tab
        tab = Docusign::Tab.new.tap do |t|
          if !@document2.nil?
            t.documentID = 2
            t.pageNumber = 1
          elsif !@document1.nil?
            t.documentID = 1
            t.pageNumber = 1
          else
            t.documentID = 1
            t.pageNumber = 3
          end
          t.recipientID = 1
          t.xPosition = 269
          t.yPosition = 508
          t.type = Docusign::TabTypeCode::Custom
          t.customTabType = Docusign::CustomTabType::Radio
          t.customTabRadioGroupName = "fruit"
          t.tabLabel = "FruitNo"
          t.name = "I Do Not Like Fruit"
        end
        e.tabs << tab

        #Custom radio button tab
        tab = Docusign::Tab.new.tap do |t|
          if !@document2.nil?
            t.documentID = 2
            t.pageNumber = 1
          elsif !@document1.nil?
            t.documentID = 1
            t.pageNumber = 1
          else
            t.documentID = 1
            t.pageNumber = 3
          end
          t.recipientID = 1
          t.xPosition = 202
          t.yPosition = 509
          t.type = Docusign::TabTypeCode::Custom
          t.customTabType = Docusign::CustomTabType::Radio
          t.customTabRadioGroupName = "fruit"
          t.tabLabel = "FruitYes"
          t.name = "I Like Fruit"
          t.value = "Yes"
        end
        e.tabs << tab

        #Custom text tab
        tabtext2 = Docusign::Tab.new.tap do |t|
          if !@document2.nil?
            t.documentID = 2
            t.pageNumber = 1
          elsif !@document1.nil?
            t.documentID = 1
            t.pageNumber = 1
          else
            t.documentID = 1
            t.pageNumber = 3
          end
          t.recipientID = 1
          t.xPosition = 262
          t.yPosition = 547
          t.type = Docusign::TabTypeCode::Custom
          t.customTabType = Docusign::CustomTabType::Text
          t.conditionalParentLabel = "fruit"
          t.conditionalParentValue = "I Like Fruit"
          t.name = "Favorite Fruit Type"
          t.tabLabel = "Favorite Fruit Type"
        end
        e.tabs << tabtext2
      end
      if @options && @attach

        #Attach a new document for the SignerAttachment Tab
        document = Docusign::Document.new.tap do |d|
          d.iD = i
          d.name = "Signer Attachment Document"
          file = File.open(Rails.root.join('public', 'resources', 'BlankPDF.pdf'), "rb")
          contents = file.read
          d.pDFBytes = Base64.encode64(contents)
          d.fileExtension = 'pdf'
          d.attachmentDescription = "Please attach your document here"
        end
        e.documents << document

        #SignerAttachment Tab
        tab = Docusign::Tab.new.tap do |t|
          t.documentID = i
          t.recipientID = 1
          t.pageNumber = 1
          t.xPosition = 20
          t.yPosition = 20
          t.type = Docusign::TabTypeCode::SignerAttachment
          t.name = "Signer Attachment"
          t.tabLabel = "Signer Attachment"
        end
        e.tabs << tab
      end
    end
  end
end
