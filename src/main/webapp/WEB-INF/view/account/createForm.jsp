<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Create new account</title>
</head>
<body>
<h1>New user data</h1>
<form:form modelAttribute="newUser">
	<div>First name: <form:input path="firstName" /></div>
	<div>Last name: <form:input path="lastName" /></div>
	<div>Email: <form:input path="email" /></div>
	<div>Password: <form:input path="password" /></div>
	<div><input type="submit" value="Submit"></input></div>
</form:form>
</body>
</html>