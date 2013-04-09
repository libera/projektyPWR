<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h3>Wiadomosc : ${message}</h3>	
	<h3>Nazwa_uzytkownika : ${username}</h3>	
	
	<a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
	
</body>
</html>