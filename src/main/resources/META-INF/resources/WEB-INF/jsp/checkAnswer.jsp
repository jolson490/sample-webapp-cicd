<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Check Answer</title>
</head>

<body>
    In the boxes below fill in the values from a given problem that a student completed from their unit conversion worksheet, and then click "Check" to find out whether their
    response is correct.
    <br>
    <br>
    The student was instructed to take the "Input Numerical Value" which is expressed in "Input Unit of Measure", and convert that to the corresponding value (i.e.
    "Student Response") expressed in "Target Unit of Measure".
    <br>
    <br>

    <%-- The form data is bound to the model specified by the modelAttribute. --%>
    <form:form action="checkAnswer" method="post" modelAttribute="problemAttribute">
        <div>
            <label>Input Numerical Value:</label> <input type="number" step="any" name="inputValue" value="${problemAttribute.inputValue}" required />
        </div>

        <%-- inputUnit (Input Unit of Measure), targetUnit (Target Unit of Measure) --%>

        <div>
            <label>Student Response:</label> <input type="number" step="any" name="studentResponse" value="${problemAttribute.studentResponse}" required />
        </div>

        <div>
            <button type="submit">Check Answer</button>
        </div>

        <div>
            <label>Output (whether the student's answer is correct):</label> ${problemAttribute.problemOutput}
        </div>
    </form:form>
    <br>

    <a href="${pageContext.request.contextPath}/">Home page</a>
</body>
</html>
