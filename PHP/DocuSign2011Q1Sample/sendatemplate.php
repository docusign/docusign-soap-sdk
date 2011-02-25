<?php 

?>

<!DOCTYPE html">
<html>
    <head>
		<link rel="stylesheet" href="css/jquery.ui.all.css" />
		<link rel="Stylesheet" href="css/sendatemplate.css" />
		<link rel="Stylesheet" href="css/homestyle.css"/>
		<script src="js/jquery-1.4.4.js"></script>
		<script src="js/jquery.ui.core.js"></script>
		<script src="js/jquery.ui.widget.js"></script>
		<script src="js/jquery.ui.datepicker.js"></script>
		<script type="text/javascript" charset="utf-8">
			$(function () {
				var today = new Date().getDate();
				$("#reminders").datepicker({
					showOn: "button",
					buttonImage: "images/calendar-blue.gif",
					buttonImageOnly: true,
					minDate: today
				});
				$("#expiration").datepicker({
					showOn: "button",
					buttonImage: "images/calendar-blue.gif",
					buttonImageOnly: true,
					minDate: today
				});
				$(".switcher li").bind("click", function () {
					var act = $(this);
					$(act).parent().children('li').removeClass("active").end();
					$(act).addClass("active");
				});
			});

		</script>
	</head>
    <body>
		<div>
			<input id="Subject" name="SubjectLine" placeholder="<enter the subject>" type="text"
				class="email" /><img alt="" src="" class="helplink" /><br />
			<textarea id="Blurb" cols="20" name="BlurbText" placeholder="<enter the e-mail blurb>"
				rows="4" class="email"></textarea>
		</div>
		<div>
			<table id="TemplateTable">
				<tr>
					<td>
						Select a Template
					</td>
				</tr>
			</table>
		</div>
		<div>
			<table width="100%" id="RecipientTable" name="RecipientTable">
				<tr class="rowheader">
					<td class="fivecolumn">
						<b>Role Name</b>
					</td>
					<td class="fivecolumn">
						<b>Name</b>
					</td>
					<td class="fivecolumn">
						<b>E-mail</b>
					</td>
					<td class="fivecolumn">
						<b>Security</b>
					</td>
					<td class="fivecolumn">
						<b>Send E-mail Invite</b>
					</td>
				</tr>
			</table>
		</div>
		<div>
			<table width="100%">
				<tr class="rowbody">
					<td class="fourcolumn">
					</td>
					<td class="fourcolumn">
						<input type="text" id="reminders" class="datepickers" />
					</td>
					<td class="fourcolumn">
						<input type="text" id="expiration" class="datepickers" />
					</td>
					<td class="fourcolumn">
					</td>
				</tr>
				<tr>
					<td class="fourcolumn">
					</td>
					<td class="fourcolumn">
						Add Daily Reminders
					</td>
					<td class="fourcolumn">
						Add Expiration
					</td>
					<td class="fourcolumn">
					</td>
				</tr>
				<tr>
					<td class="fourcolumn">
					</td>
					<td class="leftbutton">
						<input id="SendNow" type="submit" value="Send Now" align="right" />
					</td>
					<td class="rightbutton">
						<input id="EditFirst" type="submit" value="Edit Before Sending" align="left" />
					</td>
					<td class="fourcolumn">
					</td>
				</tr>
			</table>
			<div class="notification" style="visibility: hidden">
				<table>
					<tr>
						<td>
							<img alt="" src="" />
						</td>
						<td>
							Your envelope was sent. The unique envelope ID is:<br />
							<br />
							Go to the detailed status page to check on it!
						</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>
