<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:message var="pageTitle" code="account.new.pageTitle" />

<html>
<head>
<title>${pageTitle}</title>
</head>
<body>
<h1><spring:message code="account.new.message.enterUserData" /></h1>
<form:form modelAttribute="newUserForm" commandName="newUserForm">
	<div><spring:message code="account.new.firstName" /><form:input
		path="firstName" /><form:errors path="firstName"
		cssStyle="color: red;" /></div>
	<div><spring:message code="account.new.lastName" /><form:input
		path="lastName" /><form:errors path="lastName" cssStyle="color: red;" /></div>
	<div><spring:message code="account.new.email" /><form:input
		path="email" /><form:errors path="email" cssStyle="color: red;" /></div>
	<div><spring:message code="account.new.password" /><form:input
		path="password" /><form:errors path="password" cssStyle="color: red;" /></div>
	<div>positions</div>
	<c:forEach items="${newUserForm.positions}" var="position"
		varStatus="pStatus">
		<table>
			<tbody>
				<tr><td><form:input path="positions[${pStatus.index}].companyName" /></td>
				<td><form:input path="positions[${pStatus.index}].positionName" /></td></tr>
				<tr><td><form:errors path="positions[${pStatus.index}].companyName" cssStyle="color: red;"/></td>
				<td><form:errors path="positions[${pStatus.index}].positionName" cssStyle="color: red;"/></td></tr>
			</tbody>
		</table>

	</c:forEach>
	<div><input type="submit" value="Submit"></input></div>
</form:form>
</body>
</html>