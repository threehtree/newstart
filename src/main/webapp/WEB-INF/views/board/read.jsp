
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${listDTO}/
${dto}
<div>
<button class="listBtn">리스트</button>
<button class="modBtn">수정/삭제</button>
<%--    버튼에 '기능'을 달고싶다 > script > eventlistener --%>
</div>
<script>

    const bno = ${dto.bno}

     document.querySelector(".listBtn").addEventListener("click", (e)=>{


        self.location = `/board/list${listDTO.link}`
         //문자열을 만들때는 ` `로 감싸고 EL태그 그냥써야 합쳐지네
     },false)
     document.querySelector(".modBtn").addEventListener("click", (e)=>{
        self.location = `/board/modify/${bno}${listDTO.link}`
     },false)
     //현재 우리가 계속 써야하는 값이 ListDTO에 모두 있다
</script>
<%--현재 컨트롤러에 파라메터에 ListDTO가 등록되어있고 dto는 mapper를 통해 넘겨줌--%>
<%--dto 는 현재 selectOne 을헤서 나온 boardDTO 객체 하나--%>
</body>
</html>
