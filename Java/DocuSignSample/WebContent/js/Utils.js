//=====
// Add row to recipient table on senddocument.php
//=====
function addRecipientRowToTable() {
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
    email.type = 'email';
    email.name = 'RecipientEmail' + iteration;
    email.id = 'txtRow' + iteration;
    cellMiddle1.appendChild(email);

    // right cell
    var cellMiddle2 = row.insertCell(2);

    var security = document.createElement('select');
    security.onchange = function () { EnableDisableInput(iteration); }
    security.id = "RecipientSecurity" + iteration;
    security.name = 'RecipientSecurity' + iteration;
    var noneopt = document.createElement('option');
    noneopt.text = 'None';
    noneopt.value = 'None';
    security.add(noneopt);

    var idopt = document.createElement('option');
    idopt.text = 'ID Check';
    idopt.value = 'IDCheck';
    security.add(idopt);

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
    securitySetting.defaultValue = "12345";
    securitySetting.style.display = "none";
    cellMiddle2.appendChild(securitySetting);

    // select cell
    var cellRight = row.insertCell(3);
    var invite = document.createElement('ul');
    invite.className = 'switcher';
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

//=====
// Add row to role table on sendatemplate.php
//=====
function addRoleRowToTable() {
    var tbl = document.getElementById('RecipientTable');
    var lastRow = tbl.rows.length;
    // if there's no header row in the table, then iteration = lastRow + 1
    var iteration = lastRow;
    var row = tbl.insertRow(lastRow);
    row.id = 'Role' + iteration;
    row.name = 'Role' + iteration;

    // rolename cell
    var cellLeft = row.insertCell(0);
    var rname = document.createElement('input');
    rname.type = 'text';
    rname.name = 'RoleName' + iteration;
    rname.id = 'txtRow' + iteration;
    cellLeft.appendChild(rname);
    
    // name cell
    var cellMiddle1 = row.insertCell(1);
    var name = document.createElement('input');
    name.type = 'text';
    name.name = 'Name' + iteration;
    name.id = 'txtRow' + iteration;
    cellMiddle1.appendChild(name);

    // email cell
    var cellMiddle2 = row.insertCell(2);
    var email = document.createElement('input');
    email.type = 'email';
    email.name = 'RoleEmail' + iteration;
    email.id = 'txtRow' + iteration;
    cellMiddle2.appendChild(email);

    // security cell
    var cellMiddle3 = row.insertCell(3);
    var security = document.createElement('select');
    security.onchange = function () { EnableDisableInput(iteration); }
    security.id = "RoleSecurity" + iteration;
    security.name = 'RoleSecurity' + iteration;
    
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

    cellMiddle3.appendChild(security);
    
    var securitySetting = document.createElement('input');
    securitySetting.type = 'text';
    securitySetting.name = 'RollSecuritySetting' + iteration;
    securitySetting.id = 'RoleSecuritySetting' + iteration;
    securitySetting.style.display = "none";
    cellMiddle3.appendChild(securitySetting);

    // select cell
    var cellRight = row.insertCell(4);
    var invite = document.createElement('ul');
    invite.className = 'switcher';
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
    inputHidden.title = 'RoleInviteToggle' + iteration;
    inputHidden.id = 'RoleInviteToggle' + iteration;
    inputHidden.name = 'RoleInviteToggle' + iteration;
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

function toggle(id) {
    var state = document.getElementById(id).style.display;
    if (state == 'block') {
        document.getElementById(id).style.display = 'none';
    } else {
        document.getElementById(id).style.display = 'block';
    }
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
    if ($("#RecipientSecurity"+id).attr("selectedIndex") == 2) {
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
