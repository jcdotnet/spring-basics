<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%-- JSP standard tag library --%>

<!doctype html>
<html>
<head>
	<meta charset="UTF-8" />
	<title>Student Confirmation Page</title>
</head>

<body>
	The student is confirmed: ${student.firstName} ${student.lastName } 
	
	<br><br>
	Province: ${student.provinceValue}
	
	<br><br>
	Favorite programming language: ${student.favoriteLanguage}
	
	<br><br>
	Enrolled in: ${student.favoriteLanguage}
	<ul>
		<c:forEach var="course" items="${student.takenCourses}">
			<li> ${course} </li>
		</c:forEach>
	</ul>
	
</body>

</html>