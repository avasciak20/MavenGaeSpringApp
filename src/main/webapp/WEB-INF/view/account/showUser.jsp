<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Create new account</title>
</head>
<body>
<h1>New user added</h1>
	<div>Id: ${user.id}</div>
	<div>First name: ${user.firstName}</div>
	<div>Last name: ${user.lastName}</div>
	<div>Email: ${user.email}</div>
	<div>Password: ${user.password}</div>
</body>
</html>