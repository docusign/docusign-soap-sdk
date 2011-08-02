/*

Copyright (C) DocuSign, Inc.  All rights reserved.

This source code is intended only as a supplement to DocuSign SDK 
and/or on-line documentation.

This sample is designed to demonstrate DocuSign features and is not intended 
for production use. Code and policy for a production application must be 
developed to meet the specific data and security requirements of the 
application.

THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY
KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
PARTICULAR PURPOSE.
*/

using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.IO;
using hcc_poc.net.docusign.test;
using System.Xml.Serialization;
using System.Xml;
using System.Diagnostics;

namespace hcc_poc
{
    public partial class Receiver : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        { 
            if(!EventLog.SourceExists("HCC"))
            {
                EventLog.CreateEventSource("HCC", "Application");
            }

            EventLog.WriteEntry("HCC", "In PageLoad");
            if (Request.ContentType.ToLower().Contains("xml") == false)
            {
                // Look for xml files
                string filepath = Server.MapPath("/") + "\\";
                DirectoryInfo xmldir = new DirectoryInfo(filepath);
                FileInfo[] xmlfiles = xmldir.GetFiles("*.xml");
                if (xmlfiles.Length == 0)
                {
                    this.statusLabel.Text = "No envelopes have been posted";
                    EventLog.WriteEntry("HCC", "No XML Posted");
                }
                else
                {
                    this.statusLabel.Text = "You have received " + xmlfiles.Length + " envelopes";
                    EventLog.WriteEntry("HCC", "Envelopes to list");
                }
            }
            else
            {
                this.statusLabel.Text = "XML Posted. Started Parsing";
                EventLog.WriteEntry("HCC", "XML Posted. Started Parsing");
                
                StreamReader sr = new StreamReader(Request.InputStream);
                string xml = sr.ReadToEnd();
                string fileName = Server.MapPath("/") + "\\" + DateTime.Now.Ticks + ".xml";
                EventLog.WriteEntry("HCC", "Writing to " + fileName);
                File.WriteAllText(fileName, xml);

                XmlReader reader = new XmlTextReader(new StringReader(xml));
                XmlSerializer serializer = new XmlSerializer(typeof(DocuSignEnvelopeInformation), "http://www.docusign.net/API/3.0");
                DocuSignEnvelopeInformation envelopeInfo = serializer.Deserialize(reader) as DocuSignEnvelopeInformation;
                
                
                foreach (DocumentPDF document in envelopeInfo.DocumentPDFs)
                {
                    string filePath = Server.MapPath("/") + "\\" +  envelopeInfo.EnvelopeStatus.EnvelopeID + "-" + document.Name + ".pdf";
                    EventLog.WriteEntry("HCC", "writing file " + filePath);
                
                    File.WriteAllBytes(filePath, document.PDFBytes);
                }

                EventLog.WriteEntry("HCC", "Done!");
                this.statusLabel.Text = "All is well. Wrote " + envelopeInfo.DocumentPDFs.Length + " documents";
            }
        }
    }
}
