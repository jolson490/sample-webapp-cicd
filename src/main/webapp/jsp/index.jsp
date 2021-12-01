<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sample Java webapp with CI/CD to AWS, by Joshua Olson</title>
<link rel="icon" type="image/x-icon" href="assets/static/favicon.ico" />
</head>

<body>
    <a href="${pageContext.request.contextPath}/checkAnswer">Go here</a> to check provided answers for converted values for temperature or volume.
    <br>
    <br> This application was last updated/deployed at: ${lastUpdated}
    <br>
    <br>

    <i>For more info, see the <a target="_blank" href="https://github.com/jolson490/sample-webapp-cicd">GitHub repo</a>.
    </i>
</body>
</html>
