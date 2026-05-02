<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Book</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f9; padding: 20px; }
        .container { max-width: 500px; margin: auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], select { width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        .btn { padding: 10px 15px; color: white; background-color: #ffc107; border: none; border-radius: 5px; cursor: pointer; color: black; }
        .btn-cancel { background-color: #6c757d; text-decoration: none; display: inline-block; color: white; padding: 10px 15px; border-radius: 5px;}
        .error { color: red; margin-bottom: 15px; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Edit Book</h2>
        
        <c:if test="${not empty errorMessage}">
            <div class="error">${errorMessage}</div>
        </c:if>

        <form action="/edit/${book.id}" method="post">
            <div class="form-group">
                <label for="title">Title</label>
                <input type="text" id="title" name="title" value="${book.title}" required>
            </div>
            <div class="form-group">
                <label for="isbn">ISBN</label>
                <input type="text" id="isbn" name="isbn" value="${book.isbn}" required>
            </div>
            <div class="form-group">
                <label for="author">Author</label>
                <select id="author" name="author.id" required>
                    <c:forEach var="author" items="${authors}">
                        <option value="${author.id}" ${author.id == book.author.id ? 'selected' : ''}>${author.name}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn">Update</button>
            <a href="/" class="btn btn-cancel">Cancel</a>
        </form>
    </div>
</body>
</html>
