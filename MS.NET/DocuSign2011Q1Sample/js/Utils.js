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
    email.type = 'email';
    email.name = 'RecipientEmail' + iteration;
    email.id = 'txtRow' + iteration;
    cellMiddle1.appendChild(email);

    // right cell
    var cellMiddle2 = row.insertCell(2);
    var security = document.createElement('input');
    security.type = 'text';
    security.name = 'RecipientSecurity' + iteration;
    security.id = 'txtRow' + iteration;

    cellMiddle2.appendChild(security);

    // select cell
    var cellRight = row.insertCell(3);
    var invite = document.createElement('ul');
    invite.name = 'RecipientInvite' + iteration;
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
    cellRight.appendChild(invite);
}
