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
<%--model check--%>
<hr/>
<style>
    .pagination {
        display: flex;
    }
    .pagination .page-item{
        margin: 0.3em;
        list-style: none;
    }
</style>

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

<ul class="pagination">
    <li class="page-item">
        <a class="page-link" href="#" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
        </a>
    </li>

    <c:forEach begin="${pageMaker.start}" end="${pageMaker.end}" var="num">
        <li class="page-item"><a class="page-link" href="${num}">${num}</a></li>
        <%--        일단 a태그의 기본 동작인 이동을 막아야지 --%>
    </c:forEach>

    <li class="page-item">
        <a class="page-link" href="#" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
        </a>
    </li>
</ul>

<form class="actionForm" action="/board/list" method="get">
    <input type="hidden" name="page" value="${listDTO.page}">
    <input type="hidden" name="size" value="${listDTO.size}">
    <input type="hidden" name="type" value="${listDTO.type}">
    <input type="hidden" name="keyword" value="${listDTO.keyword}">
</form>


<script>

    const linkTags= document.querySelectorAll(".page-link")
    //page에 대해 전부 필요해서 배열로 받음
    const actionForm = document.querySelector(".actionForm")
    console.log(linkTags)

    for (const tag123 of linkTags){
        // console.log(tag123)
        tag123.addEventListener("click",(e)=> {
            e.preventDefault()
            //a태그의 기본동작 방지
            console.log(tag123.href)
            //href 에들어간 전체 경로
            const pageNum= tag123.getAttribute("href")
            //href 에서 Attribute 에 해당하는 값만.
            // 일반적으로 우리가 쓰는건 이값

            actionForm.querySelector("input[name = 'page']").value = pageNum
            actionForm.submit()
        },false)
    }

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
