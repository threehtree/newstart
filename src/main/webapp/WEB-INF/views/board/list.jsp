<%--
  Created by IntelliJ IDEA.
  User: ehdwn
  Date: 2022-04-30
  Time: 오후 3:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Board List</title>
</head>
<body>
    <script>
        const result = ${result}
        console.log(result)
        alert(result)
        //js는 뒤에 ; 필수가 아님
        //EL 을 사용해 result라는 값을 브라우저에서 받아옴
        //RedirectAttributes 로 result라는 BoardDTO 이외의 값을 받음
    </script>
</body>
</html>
