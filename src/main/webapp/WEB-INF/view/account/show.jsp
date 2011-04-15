<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head><title>User</title></head>
<body>
		<b>${user.stringKey}</b> ${user.firstName} ${user.lastName} ${user.email}<br/>
		Positions:
		<ol>
			<c:forEach items="${user.positions}" var="position">
				<li><b>${position.stringKey}</b> ${position.companyName} ${position.positionName}</li>
			</c:forEach>
		</ol>
</body>
</html>