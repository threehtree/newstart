
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%--${listDTO}--%>
<%--${dto}--%>
<textarea><c:out value="${dto.content}"/></textarea>
<%--여기서 내용을 <script>를 넣어서 공격해봄 , 공격끝낫으면 지워야지 --%>
<div>
    <button class="listBtn">리스트</button>
    <button class="modBtn">수정/삭제</button>
    <%--    버튼에 '기능'을 달고싶다 > script > eventlistener --%>
</div>

<div>
    <div>
        <input type="text" name ="replyText" value="샘플 댓글">
    </div>
</div>
<div>
    <div>
        <input type="text" name ="replyer" value="Test user">
    </div>
</div>

<div>
    <div>
        <button class="addReplyBtn">댓글 추가</button>
    </div>
</div>


<div>
    <ul class="replyUL">

    </ul>
</div>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<%--첫 axios 사용겸 async ,await 에 대한 설명이 나옴--%>
<script src="/resources/js/reply.js"></script>
<%--따로 뺀 js가 호출이 되는가 --%>
<script>

    const bno = ${dto.bno}
    const replyUL = qs(".replyUL")

    // console.log(replyService )
    // //자바를 호출하는 것처럼 함수를 호출
    // replyService.getList(bno, printReplies)
    function getServerList(){
        replyService.getList(bno, (replyArr) => {
            const liArr= replyArr.map(reply => `<li>\${reply.rno}</li>`)
            replyUL.innerHTML = liArr.join(" ")
            //이 함수는 axios통신 된 후에 실행되어야 한다
        })
    }

    function addServerReply(){

    replyService.addReply({bno: bno,
            replyText: qs("input[name='replyText']").value,
            replyer:qs("input[name='replyer']").value},
        //이제 값을 다 많들어 놧으니 입력하는 이벤트를 만들어야지
        ()=> {
            alert("replyadd...")
            getServerList()


    //이걸 실행하고 응답 받으면 해야할 작업이있다=> .then, callback 이 가능하다
        })
    }
    qsAddEvent(".addReplyBtn","click",addServerReply)
    //이벤트의 간소화를 위해 제작

    // function printReplies(replyArr){
    //     //목록 출력용
    //
    //     const liArr= replyArr.map(reply => `<li>AAA<li>`
    //     )
    //     replyUL.innerHTML = liArr.join(" ")
    //     //이 함수는 axios통신 된 후에 실행되어야 한다
    // }


</script>
<%--현재 컨트롤러에 파라메터에 ListDTO가 등록되어있고 dto는 mapper를 통해 넘겨줌--%>
<%--dto 는 현재 selectOne 을헤서 나온 boardDTO 객체 하나--%>
</body>
</html>
