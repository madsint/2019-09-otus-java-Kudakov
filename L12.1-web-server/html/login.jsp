<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" type="text/css" href="styles.css">
<title>Login</title>
</head>
<body>
	<h3>Please enter your username and password:</h3>
	<form method="post" action="login">
		<label for="username">Username</label><br> <input type="text"
			id="username" name="username" /><br> <label for="password">Password</label><br>
		<input type="password" id="password" name="password" /><br> <input
			type="submit" value="Submit">
	</form>
	<h4 style="color: red;">${message}</h4>
</body>
</html>