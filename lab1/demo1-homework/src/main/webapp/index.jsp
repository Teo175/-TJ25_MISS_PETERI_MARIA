<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome</title>
</head>
<body>
<h1>Welcome!</h1>
<p>Select a page:</p>

<form action="controller-servlet" method="post">
    <label>
        <input type="radio" name="selection" value="1" checked> Page 1
    </label><br>
    <label>
        <input type="radio" name="selection" value="2"> Page 2
    </label><br><br>
    <button type="submit">Go</button>
</form>

</body>
</html>
