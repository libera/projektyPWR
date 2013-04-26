<%@ page contentType="text/html; charset=utf-8" %>
<%@ page language="java" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
	<head>
		<title>Politechnika Wrocławska - zarządzanie projektami studenckimi</title>
		<link rel="stylesheet" type="text/css" href="./styles/base.css" />
		<meta charset="utf-8" />
		
		<!--disable caching-->
		<meta http-equiv="Expires" content="0" />
		<meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate" />
		<meta http-equiv="Cache-Control" content="post-check=0, pre-check=0" />
		<meta http-equiv="Pragma" content="no-cache" />
		
		<script type="text/javascript" src="./js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="./js/jquery-ui-1.10.2.custom.js"></script>
		<script type="text/javascript" src="./js/jquery.md5.js"></script>
		<script type="text/javascript" src="./js/jquery.jqGrid.min.js"></script>
		<script type="text/javascript" src="./js/groups.js"></script>
		<script type="text/javascript" src="./js/courses.js"></script>
		<script type="text/javascript" src="./js/loader.js"></script>
	</head>
	<body>
		<div id="lightbox">
			<div class="bg">
			</div>
			<div id="upload-csv" class="dialog">
				<header class="section-title">Upload CSV</header>
				<form enctype="multipart/form-data" id="upload-csv-form">
					<table>
						<tr>
							<td class="info" colspan="2"></td>
						</tr>
						<tr>
							<td><input name="filecontent" type="file" id="csv-file" /></td>
							<td><input type="button" value="Wyślij" /></td>
						</tr>
					</table>
					<input type="hidden" name="userid" id="csv-userid" value="" />
				</form>
			</div>
			<div id="register-success" class="dialog">
				<header class="section-title success">Zarejestrowano pomyślnie!</header>
				<span class="success">Twoje hasło zostało wysłane na adres email podany przy rejestracji.</span>
			</div>
			<div id="logout-success" class="dialog">
				<header class="section-title success">Wylogowano!</header>
				<span class="success">Użytkownik został wylogowany.</span>
			</div>
			<div id="change-pass" class="dialog">
				<header class="section-title">Zmiana hasła</header>
				<table>
					<tr>
						<td class="info" colspan="2"></td>
					</tr>
					<tr>
						<td class="param">Stare hasło:</td>
						<td class="value"><input type="password" id="change-pass-old" /></td>
					</tr>
					<tr>
						<td class="param">Nowe hasło:</td>
						<td class="value"><input type="password" id="change-pass-new" /></td>
					</tr>
					<tr>
						<td class="param">Powtórz hasło:</td>
						<td class="value"><input type="password" id="change-pass-repeat" /></td>
					</tr>
					<tr>
						<td style="text-align: right;" colspan="2"><input type="button" id="change-pass-button" value="Zmień hasło" /></td>
					</tr>
				</table>
			</div>
		</div>
		<div id="login">
			<div class="top-bar">
				<header class="page-title">
					Zarządzanie projektami studenckimi
				</header>
			</div>
			<header class="section-title">
				Logowanie
			</header>
			<table>
				<tr>
					<td colspan="2" class="info"></td>
				</tr>
				<tr>
					<td class="param">Użytkownik:</td>
					<td class="value"><input type="text" id="login-user" /></td>
				</tr>
				<tr>
					<td class="param">Hasło:</td>
					<td class="value"><input type="password" id="login-pass" /></td>
				</tr>
				<tr>
					<td style="text-align: left;"><a href="#" id="register-link">Zarejestruj</a></td>
					<td style="text-align: right;"><input type="button" id="login-button" value="Zaloguj" /></td>
				</tr>
			</table>
		</div>
		<div id="register">
			<div class="top-bar">
				<header class="page-title">
					Zarządzanie projektami studenckimi
				</header>
			</div>
			<header class="section-title">
				Rejestracja
			</header>
			<table>
				<tr>
					<td colspan="2" class="info"></td>
				</tr>
				<tr>
					<td class="param">Imię:</td>
					<td class="value"><input type="text" id="register-firstname" /></td>
				</tr>
				<tr>
					<td class="param">Nazwisko:</td>
					<td class="value"><input type="text" id="register-surname" /></td>
				</tr>
				<tr>
					<td class="param">Adres email:</td>
					<td class="value"><input type="text" id="register-email" /></td>
				</tr>
				<tr>
					<td class="param">Nazwa użytkownika:</td>
					<td class="value"><input type="text" id="register-user" /></td>
				</tr>
				<!--  <tr>
					<td class="param">Hasło:</td>
					<td class="value"><input type="password" id="register-pass" /></td>
				</tr>
				<tr>
					<td class="param">Powtórz hasło:</td>
					<td class="value"><input type="password" id="register-pass-repeat" /></td>
				</tr>
				-->
				<tr>
					<td style="text-align: left;"></td>
					<td style="text-align: right;"><input type="button" id="register-button" value="Zarejestruj" /></td>
				</tr>
			</table>
		</div>
		<div id="content">
			<div class="top-bar">
				<header class="page-title">
					Zarządzanie projektami studenckimi
				</header>
				<div class="tools">
					<div class="user">
					</div>
					<div class="info">
						Użytkownik: <span id="user-logged-name"></span><br />
						<a href="#" id="user-change-pass">zmień hasło</a> - <a href="#" id="user-logout">wyloguj</a>
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
				</div>
			</div>
			<div class="right-col">
				<header class="section-title">Nieprzypisani</header>
				<table id="notingroup" class="students" cellpadding="0" cellspacing="0">
				</table>
			</div>
		</div>
	</body>
</html>