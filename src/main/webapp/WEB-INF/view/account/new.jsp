<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message var="pageTitle" code="account.new.pageTitle"/>

<html>
<head>
<title>${pageTitle}</title>
</head>
<body>
<h1><spring:message code="account.new.message.enterUserData"/></h1>
<form:form modelAttribute="newUserForm" action=".">
	<div><spring:message code="account.new.firstName"/><form:input path="firstName" /></div>
	<div><spring:message code="account.new.lastName"/><form:input path="lastName" /></div>
	<div><spring:message code="account.new.email"/><form:input path="email" /></div>
	<div><spring:message code="account.new.password"/><form:input path="password" /></div>
	<div><input type="submit" value="Submit"></input></div>
</form:form>
</body>
</html>