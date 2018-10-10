<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/WEB-INF/javaTime.tld" prefix="javatime" %>
<html>
<head>
    <title>Meals</title>
    <style>
        .content {
            text-align: center;
            font-family: Calibri;
            margin: auto;
            font-size: x-large;
        }
        table {
            border: 1px solid black;
            margin: auto;
            border-collapse: collapse;
            font-size: x-large;
        }
        td, th {
            border: 1px solid black;
            text-align: center;
        }
    </style>
</head>

<body>
<div class=content>
    <h3>List of Meals</h3>
    <table>
        <tr>
            <th>id</th>
            <th>dateTime</th>
            <th>description</th>
            <th>calories</th>
            <th>isExceed</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${requestScope.meals}" var="meal">
            <c:if test="${meal.exceed}">
                <tr style="color: red;">
            </c:if>
            <c:if test="${!meal.exceed}">
                <tr style="color : green">
            </c:if>
                    <td><c:out value="${meal.id}"/></td>
                    <javatime:format value="${meal.dateTime}" style="MS" var="formatedDate"/>
                    <td><c:out value="${formatedDate}"/></td>
                    <td><c:out value="${meal.description}"/></td>
                    <td><c:out value="${meal.calories}"/></td>
                    <td><c:out value="${meal.exceed}"/></td>
                    <td>
                        <form method="post" action="meals">
                            <input type="hidden" name="deleteId" value="${meal.id}"/>
                            <input type="submit" name="delete" value="delete"/>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="meals">
                            <input type="hidden" name="updateId" value="${meal.id}"/>
                            <input type="submit" name="update" value="update"/>
                        </form>
                    </td>
            </form>
                </tr>
        </c:forEach>
    </table>

    <c:if test="${requestScope.mealToUpdate == null}">
        <H3>Add Meal</H3>
    </c:if>
    <c:if test="${requestScope.mealToUpdate != null}">
        <H3>Update Meal</H3>
    </c:if>

    <form method="post" action="meals">
        <label>
            Description
            <input type="text" name="description"
                   value="<c:out value='${mealToUpdate.description}' />">
        </label><br>
        <label>
            Date
            <input type="datetime-local" name="datetime"
                   value="<c:out value='${mealToUpdate.dateTime}' />">
        </label><br>
        <label>
            Calories
            <input type="number" name="calories"
                   value="<c:out value='${mealToUpdate.calories}' />">
        </label><br>
        <c:if test="${requestScope.mealToUpdate == null}">
            <input type="submit" value="add!" name="ok">
        </c:if>
        <c:if test="${requestScope.mealToUpdate != null}">
            <input type="hidden" name="idToUpdate" value="${mealToUpdate.id}"/>
            <input type="submit" value="update!" name="ok">
        </c:if>
    </form>

</div>

</body>
</html>
