<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Check Answer</title>
</head>

<body>
    In the boxes below fill in the values from a given problem that a student completed from their unit conversion worksheet, and then click "Check Answer" to find out whether
    their response is correct.
    <br>
    <br>The student was instructed to take the "Input Numerical Value" which is expressed in "Input Unit of Measure", and convert that to the corresponding value (i.e.
    "Student Response") expressed in "Target Unit of Measure" and round that to the nearest tenth. The student's answer is deemed to be correct if it is equal to the actual
    converted value rounded to the nearest tenth.
    <br>
    <br>

    <%-- The fields on this form are setup such that when the user clicks the button then the values they specified are retained - so that they can see what values
         they provided, and so they only have to change whatever values they'd like to before clicking the button again. --%>
    <%-- The form data is bound to the model specified by the modelAttribute. --%>
    <form:form action="checkAnswer" method="post" modelAttribute="problemAttribute">
        <div>
            <label>Type of Unit:</label>
            <%-- It would be ideal if this entire page wouldn't need to be reloaded whenever the user changes the value for the following dropdown menu, e.g. via AJAX. --%>
            <form:select path="unitType" onchange="this.form.submit();" required="true">
                <form:option value="" />
                <form:option id="Temperature" value="Temperature" label="Temperature"></form:option>
                <form:option id="Volume" value="Volume" label="Volume"></form:option>
            </form:select>
        </div>

        <div>
            <label>Input Numerical Value:</label> <input type="number" step="any" name="inputValue" value="${problemAttribute.inputValue}" required />
        </div>

        <div>
            <label>Input Unit of Measure:</label>
            <%-- The purpose of 'path' is to bind to the 'inputUnit' variable within the model (i.e. the Problem object). --%>
            <form:select path="inputUnit" required="true">
                <form:option value="" />
                <c:forEach var="unit" items="${listUnits}">
                    <form:option id="${unit.key}" value="${unit.key}" label="${unit.value}"></form:option>
                </c:forEach>
            </form:select>
        </div>

        <div>
            <label>Target Unit of Measure:</label>
            <form:select path="targetUnit" required="true">
                <form:option value="" />
                <c:forEach var="unit" items="${listUnits}">
                    <form:option id="${unit.key}" value="${unit.key}" label="${unit.value}"></form:option>
                </c:forEach>
            </form:select>
        </div>

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
