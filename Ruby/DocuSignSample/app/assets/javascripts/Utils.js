function addRowToTable() {
    var tbl = document.getElementById('recipientList');
    var lastRow = tbl.rows.length;
    // if there's no header row in the table, then iteration = lastRow + 1
    var iteration = lastRow;
    var row = tbl.insertRow(lastRow);
    row.id = 'Recipient' + iteration;
    row.name = 'Recipient' + iteration;

    // left cell
    var cellLeft = row.insertCell(0);
    var name = document.createElement('input');
    name.type = 'text';
    name.name = 'RecipientName' + iteration;
    name.id = 'txtRow' + iteration;
    cellLeft.appendChild(name);

    var cellMiddle1 = row.insertCell(1);
    var email = document.createElement('input');
    email.type = 'text';
    email.name = 'RecipientEmail' + iteration;
    email.id = 'txtRow' + iteration;
    cellMiddle1.appendChild(email);

    // right cell
    var cellMiddle2 = row.insertCell(2);

    var security = document.createElement('select');
    security.onchange = function () { EnableDisableInput(iteration) };
    security.id = "RecipientSecurity" + iteration;
    security.name = "RecipientSecurity" + iteration;
    var noneopt = document.createElement('option');
    noneopt.text = 'None';
    noneopt.value = 'None';
    security.add(noneopt);

    var accessopt = document.createElement('option');
    accessopt.text = 'Access Code:';
    accessopt.value = 'AccessCode';
    security.add(accessopt);

    var phoneopt = document.createElement('option');
    phoneopt.text = 'Phone Authentication';
    phoneopt.value = 'PhoneAuthentication';
    security.add(phoneopt);

    cellMiddle2.appendChild(security);

    var securitySetting = document.createElement('input');
    securitySetting.type = 'text';
    securitySetting.name = 'RecipientSecuritySetting' + iteration;
    securitySetting.id = 'RecipientSecuritySetting' + iteration;
    securitySetting.style.display = "none";
    cellMiddle2.appendChild(securitySetting);

    // select cell
    var cellRight = row.insertCell(3);
    var invite = document.createElement('ul');
    invite.className = 'switcher';
    invite.id = 'RecipientInvite' + iteration
    var li1 = document.createElement('li');
    li1.className = 'active';
    var a1 = document.createElement('a');
    a1.href = '#';
    a1.title = 'On';
    a1.innerHTML = 'ON';
    li1.appendChild(a1);
    var li2 = document.createElement('li');
    var a2 = document.createElement('a');
    a2.href = '#';
    a2.title = 'OFF';
    a2.innerHTML = 'OFF';
    li2.appendChild(a2);
    invite.appendChild(li1);
    invite.appendChild(li2);

    var inputHidden = document.createElement('input');
    inputHidden.title = 'RecipientInviteToggle' + iteration;
    inputHidden.id = 'RecipientInviteToggle' + iteration;
    inputHidden.name = 'RecipientInviteToggle' + iteration;
    inputHidden.checked = true;
    inputHidden.type = 'checkbox';
    inputHidden.style.display = "none";
    invite.appendChild(inputHidden);
    cellRight.appendChild(invite);

    activate();
}

function dialogOpen() {
    $("#dialogmodal").dialog({
        height: 350,
        width: 600,
        modal: true
    });
}

function dialogClose() {
    $("#dialogmodal").dialog('close');
}

function EnableDisableDiv() {
    if ($("#stockdoc").attr("checked")) {
        $("#files").hide();
        $("#files").disableSelection();
    } else {
        $("#files").show();
        $("#files").enableSelection();
    }
}

function EnableDisableInput(id) {
    if ($("#RecipientSecurity"+id).attr("selectedIndex") == 1) {
        $("#RecipientSecuritySetting"+id).show();
        $("#RecipientSecuritySetting"+id).enableSelection();
    }
    else {
        $("#RecipientSecuritySetting"+id).hide();
        $("#RecipientSecuritySetting"+id).disableSelection();
    }
}

function activate() {
    $(".switcher li").bind("click", function () {
        var act = $(this);
        $(act).parent().children('li').removeClass("active").end();
        $(act).addClass("active");
        var text = act.context.textContent;
        if (text == "OFF") {
            $(act).parent().children('input').attr('checked', false);
        }
        else {
            $(act).parent().children('input').attr('checked', true);
        }
    });
}