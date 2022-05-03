
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modify</title>
</head>
<%--기능이 사실상 read 와 거의 똑같다--%>
<body>
<h1>Modify</h1>
${listDTO}/
${dto}

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

    //현재 우리가 계속 써야하는 값이 ListDTO에 모두 있다
</script>
<%--현재 컨트롤러에 파라메터에 ListDTO가 등록되어있고 dto는 mapper를 통해 넘겨줌--%>
<%--dto 는 현재 selectOne 을헤서 나온 boardDTO 객체 하나--%>
</body>
</html>
