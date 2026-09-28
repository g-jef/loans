<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Issue a book</title>
    <style>
        body { font-family: sans-serif; margin: 2em; }
        .errors { color: #a00; }
        label { display: block; margin-top: 1em; }
    </style>
    <script>
        function checkMemberRef(form) {
            var ref = form.memberRef.value;
            if (!/^LM-[0-9]{4}$/.test(ref)) {
                alert("Membership references look like LM-1234.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>

<h1>Issue a book</h1>

<p>
    <strong><c:out value="${book.title}"/></strong>
    by <c:out value="${book.author}"/>
</p>

<c:if test="${not empty errors}">
    <ul class="errors">
        <c:forEach var="error" items="${errors}">
            <li><c:out value="${error}"/></li>
        </c:forEach>
    </ul>
</c:if>

<form method="post" action="issue" onsubmit="return checkMemberRef(this);">
    <input type="hidden" name="bookId" value="${book.id}">

    <label for="memberRef">Membership reference</label>
    <input type="text" id="memberRef" name="memberRef" value="<c:out value='${memberRef}'/>"
           pattern="LM-[0-9]{4}" maxlength="10" required
           title="Membership references look like LM-1234">

    <p><button type="submit">Issue loan</button>
       <a href="catalogue">Cancel</a></p>
</form>

</body>
</html>
