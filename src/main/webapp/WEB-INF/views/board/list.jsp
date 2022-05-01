<%--
  Created by IntelliJ IDEA.
  User: ehdwn
  Date: 2022-04-30
  Time: 오후 3:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Board List</title>
</head>

<body>
<h1>${listDTO}</h1>
<hr/>
<h3>${pageMaker}</h3>
<hr/>
<%--<h5>${listDTO.getLink()}</h5>--%>
<h5>${listDTO.link}</h5>
<ul>

    <c:forEach items="${dtoList}" var="board">
        <li>
            <span> ${board.bno}</span>
            <span><a href='/board/read${listDTO.link}&bno=${board.bno}'> ${board.title}</a></span>
        </li>
    </c:forEach>
</ul>


    <script>
        const result = '${result}'

        // console.log(result)
        if(result !== '') {
            alert("처리됨")
        }
        //js는 뒤에 ; 필수가 아님
        //EL 을 사용해 result라는 값을 브라우저에서 받아옴
        //RedirectAttributes 로 result라는 BoardDTO 이외의 값을 받음
    </script>
</body>
</html>
