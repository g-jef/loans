<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Library catalogue</title>
    <style>
        body { font-family: sans-serif; margin: 2em; }
        table { border-collapse: collapse; margin-top: 1em; }
        th, td { border: 1px solid #ccc; padding: 0.4em 0.8em; text-align: left; }
        .out { color: #a00; }
        .issued { background: #e8f5e9; padding: 0.6em; }
    </style>
</head>
<body>

<h1>Library catalogue</h1>

<c:if test="${param.issued == '1'}">
    <p class="issued">Loan issued.</p>
</c:if>

<form method="get" action="catalogue">
    <label for="q">Title or author</label>
    <input type="text" id="q" name="q" value="<c:out value='${term}'/>" size="30">
    <button type="submit">Search</button>
</form>

<table>
    <tr>
        <th>ISBN</th>
        <th>Title</th>
        <th>Author</th>
        <th>Status</th>
    </tr>
    <c:forEach var="book" items="${books}">
        <tr>
            <td><c:out value="${book.isbn}"/></td>
            <td><c:out value="${book.title}"/></td>
            <td><c:out value="${book.author}"/></td>
            <td>
                <c:choose>
                    <c:when test="${book.onLoan}"><span class="out">On loan</span></c:when>
                    <c:otherwise><a href="issue?bookId=${book.id}">Issue</a></c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
</table>

<c:if test="${empty books}">
    <p>No books matched that search.</p>
</c:if>

</body>
</html>
