<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> <%-- Spring forms --%>

<!doctype html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>Student Registration Form</title>
	
	<style>
		.error { color: red; }
	</style>
</head>

<body>

	<form:form action="processForm" modelAttribute="student">
	
		First name: <form:input path="firstName" />		
		<form:errors path="firstName" cssClass="error" />
		
		<br><br>
		
		Last name: <form:input path="lastName" />
				
		<br><br>
		
		Province: 
		
		<form:select path="province">
		    <%-- without data binding 
			    <form:option value="Almeria" label="Almería" />
			    <form:option value="..." label="..." />< 
		     --%>
			<form:options items="${student.provinces}" />		
		</form:select>
		
		<br><br>
		
		Which technology are you interested in?&nbsp; 
		Java <form:radiobutton path="favoriteLanguage" value="Java" />
		C# <form:radiobutton path="favoriteLanguage" value="C#" />
		JavaScript <form:radiobutton path="favoriteLanguage" value="JavaScript" />
		PHP <form:radiobutton path="favoriteLanguage" value="PHP" />
		
		<form:errors path="favoriteLanguage" cssClass="error" />
		
		<br><br>
		
		Courses to take:
		<%-- without data binding
			Java SE <form:checkbox path="courses" value="JavaSE" />
			Java Enterprise <form:checkbox path="courses" value="JavaEE" />
			Spring <form:checkbox path="courses" value="Spring" />
	    --%>
	    <form:checkboxes path="takenCourses" items="${student.courses}" />
	    <form:errors path="takenCourses" cssClass="error" />

		<br><br>
		
		<input type="submit" value="Submit" />
	</form:form>
	
	<br><br>
	
	<!-- Spring forms automatically add the CSRF token -->
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" value="Log out" />
	</form:form>
</body>
</html>