<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head><title>Users list</title></head>
<body>
List:
<ul>
<c:forEach items="${users}" var="user">
	<li>
		<a href="./id/${user.stringKey}">${user.stringKey}</a> ${user.firstName} ${user.lastName} ${user.email}<br/>
		Positions:
		<ol>
			<c:forEach items="${user.positions}" var="position">
				<li><b>${position.stringKey}</b> ${position.companyName} ${position.positionName}</li>
			</c:forEach>
		</ol>
	</li>
</c:forEach>
</ul>
</body>
</html>