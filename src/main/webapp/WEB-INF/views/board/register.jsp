<%--
  Created by IntelliJ IDEA.
  User: ehdwn
  Date: 2022-04-30
  Time: 오후 4:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
</head>
<body>
<h1>Register</h1>
<form action="/board/register" method="post">
    <input type="text" name="title">
    <button>Click</button>
</form>

<h2>파일 업로드 테스트 </h2>
<form action="/upload1" method="post" enctype="multipart/form-data">
    <input type="file" name="files" multiple>
    <button>Upload</button>
</form>
</body>
</html>
