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

<div class="searchDiv">
    <select class="type">
        <option value="">---</option>
        <option value="t" ${listDTO.type == "t"?"selected":""}>제목</option>
        <option value="tc" ${listDTO.type == "tc"?"selected":""}>제목내용</option>
        <option value="tcw" ${listDTO.type == "tcw"?"selected":""}>제목내용작성자</option>
<%--        type에 선택에서 해당하는 검색 조건을 유지해두기 위한 selected << html 문법인듯합니다 --%>
<%--        <option> 태그의 selected 속성은 페이지가 로드될 때 옵션 중에서 미리 선택되어지는 옵션을 명시합니다.--%>
    </select>
    <input type="text" name="keyword" value="${listDTO.keyword}">
<%--    keyword 가 유지가 된다.--%>
<%--    input 의 value에 값이 입력되있으면 유지 인가 --%>

    <button class="searchBtn">Search</button>
</div>



<ul class ="dtoList">

    <c:forEach items="${dtoList}" var="board">
        <li>
            <span> ${board.bno}</span>
            <span><a href='/board/read/${board.bno}' class="dtoLink ">
                <c:out value="${board.title}"/></a>
            [ <c:out value="${board.replyCount}"/> ]
            </span>
<%--            <span><a href='/board/read${listDTO.link}&bno=${board.bno}'> ${board.title}</a></span>--%>
<%--        이제 반복문을 사용하지 않고 위임을 사용하면 이벤트를 한번만 줄 수 있다  --%>
        </li>
    </c:forEach>
</ul>
<%--만약 이렇게 링크를 주는걸 js를 통해서 햇으면 이벤트 위임을 이용해 훨씬 쉽고 간편하게 만들었겟지 --%>
<%--다만 단점도 있긴한데 검색엔진이 링크를 받을때는 페이지 번호만 받음 ((link로 만들어주면되지--%>

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
<%--    검색을 하면 일단 page는 1--%>
    <input type="hidden" name="size" value="${listDTO.size}">
    <input type="hidden" name="type" value="${listDTO.type}">
    <input type="hidden" name="keyword" value="${listDTO.keyword}">
<%--    type, keyword는 검색한 값으로 대입함--%>
</form>


<script>

    const linkDiv = document.querySelector(".pagination")
    //page에 대해 전부 필요해서 배열로 받음
    //이제 이벤트 위임으로 값을 넘겨받을거니 querySelector로 바꿈
    const actionForm = document.querySelector(".actionForm")
    //이거 자주 쓰니까 꺼내둠
    // console.log(linkTags)

    document.querySelector(".dtoList").addEventListener("click",(e)=>{

        e.preventDefault()
        e.stopPropagation()
        const target  = e.target
        if(target.getAttribute("class").indexOf('dtoLink') < 0){
            return
        }
        const url = target.getAttribute("href")

        // alert(url)
        //target의 위치를 찾아 href값 꺼냄
        //여기서 href값은 href='/board/read/$.{board.bno}'이다 /board/read/게시물 번호 로
        actionForm.setAttribute("action", url)
        actionForm.submit()
        //현재 우리에게 전달되는게 bno, 검색조건, 페이지 조건 이다
        //read > list로 이동해야하는데 . 계속 유지해야하는 데이터는 model이다 그래서 listDTO를 계속 써야함
    },false)
    //지난번 자바로 작업했던 반복문 이벤트리스너를
    // 이벤트 위임으로 한방에 정리



    linkDiv.addEventListener("click", (e) =>{
        e.stopPropagation() //전파 멈춰라
        e.preventDefault() //태그의 기본기능을 멈춰라

        const target = e.target

        if(target.getAttribute("class") !== 'page-link') {

            return
        }

        const pageNum = target .getAttribute("href")

        actionForm.querySelector("input[name='page']").value = pageNum
        actionForm.setAttribute("action", "/board/list")
        //뒤로가기 문제 방지
        actionForm.submit()


    },false)



    // for (const tag123 of linkTags){
    //     // console.log(tag123)
    //     tag123.addEventListener("click",(e)=> {
    //         e.preventDefault()
    //         //a태그의 기본동작 방지
    //         console.log(tag123.href)
    //         //href 에들어간 전체 경로
    //         const pageNum= tag123.getAttribute("href")
    //         //href 에서 Attribute 에 해당하는 값만.
    //         // 일반적으로 우리가 쓰는건 이값
    //
    //
    //         actionForm.querySelector("input[name = 'page']").value = pageNum
    //         actionForm.submit()
    //     },false)
    // }
    document.querySelector(".searchBtn").addEventListener("click",(e)=>{
        const type = document.querySelector('.searchDiv .type').value
        const keyword = document.querySelector(".searchDiv input[name='keyword']").value

        console.log(type,keyword)

        actionForm.setAttribute("action", "/board/list")
        //form으로 값 보내고 값이 안바뀌어 있어서 뒤로가기 문제 방지
        actionForm.querySelector("input[name='page']").value =1
        actionForm.querySelector("input[name='type']").value = type
        actionForm.querySelector("input[name='keyword']").value = keyword
        //위에서 말한 것 처럼 조회할때의 조건은 page는 1페이지, type,keyword는 입력받은 값을 받아서 해야지
        actionForm.submit()
        //여기서 추가로 bno만 있으면 조회 기능에 필요한 값도 다 들어가네
        //경로도 read로만 바꿔주면 되네
    },false)

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
