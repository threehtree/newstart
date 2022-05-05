
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Modify</title>
</head>
<%--기능이 사실상 read 와 거의 똑같다--%>
<body>
<h1>Modify</h1>
${listDTO}///
${dto}
${board}////
<%--boarDTO는 서비스 단에서 넘겨줫어.--%>

<form class="modForm" action="/board/modify/${dto.bno}" method="post">
    <input type="hidden" name="page" value="${listDTO.page}">
<%--    form가 나온것 -> 값을 보낸다 --%>
<%--    현재 ListDTO가 페이지에 관련된건 다 수집중--%>
    <input type="hidden" name="size" value="${listDTO.size}">
    <input type="hidden" name="type" value="${listDTO.type}">
    <input type="hidden" name="keyword" value="${listDTO.keyword}">
    <div>
        <div>
            <input type ="text" name="bno" value="<c:out value="${dto.bno}"/>" readonly>
        </div>
    </div>
    <div>
        <div>
            <input type ="text" name="title" value="<c:out value="${dto.title}"/>" >
        </div>
    </div>
    <div>
        <div>
            <textarea name="content"><c:out value="${dto.content}"/></textarea>
<%--            <textarea></textarea> 이 사이에 여백있으면 랜더링때 그대로 반영됨 --%>
        </div>
    </div>
</form>

<div>
    <button class="listBtn">리스트</button>
    <button class="modPostBtn">수정</button>
    <button class="delPostBtn">삭제</button>
    <%--    버튼에 '기능'을 달고싶다 > script > eventlistener --%>
</div>

<form class="actionForm" action="/board/remove/${bno}" method="post">
    <input type="hidden" name="bno" value="${dto.bno}">
<%--    현재 여기에 bno값을 가지고 있다고?--%>
</form>

<script>
    function sQuery(expression){
        return document.querySelector(expression)
    //    계속 너무 긴 반복을 줄이려고 함수로 만들어서 사용
    }

    const bno = ${dto.bno}

        const actionForm = sQuery(".actionForm")

        document.querySelector(".listBtn").addEventListener("click", (e)=>{
            self.location = `/board/list${listDTO.link}`
            //문자열을 만들때는 ` `로 감싸고 EL태그 그냥써야 합쳐지네
        },false)

        sQuery(".delPostBtn").addEventListener("click", (e)=>{

            actionForm.setAttribute("action", `/board/remove/${bno}`)
            //혹시 몰라 링크 한번 더 줌
            actionForm.submit()

        },false)

        sQuery(".modPostBtn").addEventListener("click", (e)=>{

            sQuery(".modForm").submit()

        },false)
    //이건 Post식으로 넘어가니까 url페이징한게 날라가버린다, ListDTO로 재지정해야겟다
    //페이로드 확인 하면 우리가 아까 파라메터로 받았던 값이 보인다 /이걸 링크로 다시 만들어야한다

    //현재 우리가 계속 써야하는 값이 ListDTO에 모두 있다
</script>
<%--현재 컨트롤러에 파라메터에 ListDTO가 등록되어있고 dto는 mapper를 통해 넘겨줌--%>
<%--dto 는 현재 selectOne 을헤서 나온 boardDTO 객체 하나--%>
</body>
</html>
