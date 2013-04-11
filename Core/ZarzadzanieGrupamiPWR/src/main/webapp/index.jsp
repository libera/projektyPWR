<!DOCTYPE HTML>
<html>
	<head>
		<title>Politechnika Wroc³awska - zarz¹dzanie projektami studenckimi</title>
		<link rel="stylesheet" type="text/css" href="./styles/base.css" />
		<meta charset="utf-8" />
		
		<!--disable caching-->
		<meta http-equiv="Expires" content="0" />
		<meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate" />
		<meta http-equiv="Cache-Control" content="post-check=0, pre-check=0" />
		<meta http-equiv="Pragma" content="no-cache" />
		
		<script type="text/javascript" src="./js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="./js/jquery-ui-1.10.2.custom.min.js"></script>
		<script type="text/javascript" src="./js/groups.js"></script>
		<script type="text/javascript" src="./js/courses.js"></script>
		<script type="text/javascript" src="./js/loader.js"></script>
	</head>
	<body>
		<div id="lightbox">
		</div>
		<div id="login">
			<div class="top-bar">
				<header class="page-title">
					Zarz¹dzanie projektami studenckimi
				</header>
			</div>
			<header class="section-title">
				Logowanie
			</header>
			<table cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="2" style="text-align: center;">Aby uzyskaæ dostêp podaj nazwê u¿ytkownika oraz has³o.</td>
				</tr>
				<tr>
					<td class="param">U¿ytkownik:</td>
					<td class="value"><input type="text" id="user" /></td>
				</tr>
				<tr>
					<td class="param">Has³o:</td>
					<td class="value"><input type="password" id="pass" /></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: right;"><input type="button" id="login-button" value="Zaloguj" /></td>
				</tr>
			</table>
		</div>
		<div id="content">
			<div class="top-bar">
				<header class="page-title">
					Zarz¹dzanie projektami studenckimi
				</header>
				<div class="tools">
					<div class="user">
					</div>
					<div class="info">
						rok akademicki: 2012/2013<br />
						semestr: letni
					</div>
				</div>
				<div class="clear"></div>
			</div>
			<div class="left-col">
				<header class="section-title">Kursy</header>
				<ul id="courses">
				</ul>
				<div class="tools">
					<a href="" id="import-csv-button">Import CSV</a>
				</div>
			</div>
			<div class="middle-col">
				<header class="section-title">Grupy projektowe</header>
				<div id="groups">
					<div class="group">
						<header>
							System zarz¹dzania projektami studenckimi
							<div class="tools"><a href="" class="more">Wiêcej</a></div>
						</header>
						<div class="details">
							Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ligula nulla, viverra eget dictum nec, eleifend eget elit. Phasellus lectus erat, ultrices ac sollicitudin id, vestibulum non magna. Donec sollicitudin venenatis ipsum, eget pretium dolor volutpat quis. Proin vitae dolor odio. Sed luctus arcu ut tellus vehicula pretium. Praesent diam massa, rhoncus ac accumsan vitae, luctus at risus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent euismod ligula vel nulla adipiscing mollis. Mauris vel tincidunt nisi. Maecenas ac sapien dui. Nullam justo neque, varius vitae placerat ut, adipiscing vel turpis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Curabitur malesuada commodo magna, nec bibendum est bibendum at. Ut nisi ligula, vestibulum quis laoreet in, convallis at arcu. Sed lorem arcu, placerat ut euismod tincidunt, eleifend sed arcu.
						</div>
					</div>
				</div>
			</div>
			<div class="right-col">
				<header class="section-title">Nieprzypisani</header>
			</div>
		</div>
	</body>
</html>