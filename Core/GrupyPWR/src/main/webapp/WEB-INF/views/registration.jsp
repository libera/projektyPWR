<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Language" content="en-us" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>User Registration</title>
</head>

<body>

<h3>User Registration</h3>
<form method="post" action="registration.html">
<table align="left">
	<tr>
		<td>Imie</td>
		<td>
			<input name="imiona" type="text" />
		</td>
	</tr>
	<tr>
		<td>Nazwisko</td>
		<td>
			<input name="nazwisko" type="text" />
		</td>
	</tr>
	<tr>
		<td>Login</td>
		<td>
			<input name="UserName" type="text" />
		</td>
	</tr>
	<tr>
		<td>Password</td>
		<td>
			<input name="Password" type="password" />
		</td>
	</tr>
	<tr>
		<td>User Email</td>
		<td>
			<input name="Email" type="text" />
		</td>
	</tr>
	
	<tr>
		<td>&nbsp;</td>
		<td>
			<input name="Submit" type="submit" value="submit" />
		</td>
	</tr>
</table>
</form>
</body>

</html>
