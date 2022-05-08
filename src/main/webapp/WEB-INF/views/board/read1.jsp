
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
    <h2 class="replyCountShow"></h2>
    <ul class="replyUL">

    </ul>
    <style>
        .pageUL {
            display: flex;

        }
        .pageUL li {
            list-style: none;
            margin: 0.1em;
            border: 1px solid blue;
        }
        .pageUL .current {
            background-color: blue;
        }

    </style>


    <ul class="pageUL">

    </ul>
</div>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<%--첫 axios 사용겸 async ,await 에 대한 설명이 나옴--%>
<script src="/resources/js/reply.js"></script>
<%--따로 뺀 js가 호출이 되는가 --%>
<script>

    const bno = ${dto.bno}
    const replyUL = qs(".replyUL")
    const pageUL = qs(".pageUL")
    let replyCount = ${dto.replyCount}
    //댓글이 추가/삭제시 갯수를 맞추기 위해
        //이걸 기준으로 페이지와 사이즈가 필요하다
        //페이지랑 사이즈가 같이 쓰이니 DTO써도 좋앗을듯
    replyService.setReplyCount (function (num) {
        console.log("-----set reply count new value " + num)
        replyCount = num
        qs(".replyCountShow").innerHTML= replyCount
        console.log(replyCount)
    })
        //클로저. 이걸 reply.js 에 보내서
    //reply.js에서 값을 수정하면 수정되겟지
        const pageNum = 1;
        const pageSize = 10;

    function printPage(targetPage){
        const lastPageNum = Math.ceil(replyCount/pageSize)

        let endPageNum =Math.ceil(targetPage/10)*10

        const startPageNum = endPageNum-9
        endPageNum = lastPageNum < endPageNum ? lastPageNum: endPageNum

        let str = ''

        if(startPageNum > 1){
            str  += `<li data-num=\${startPageNum -1} >\${startPageNum -1} 이전</li>`
            //입력시 data-num으로 페이지를 보냄
        }

        for(let i = startPageNum; i< endPageNum; i++){
            str += `<li data-num=\${i} class="\${i === pageParam?'current':''}">\${i}</li>`
            //입력시 data-num으로 페이지를 보냄
            //pageParam이 현재 제일 끝 페이지 , 맞으면 Css를 잡은 이름 사용
        }
        pageUL.innerHTML = str
    }

    // console.log(replyService )
    // //자바를 호출하는 것처럼 함수를 호출
    // replyService.getList(bno, printReplies)
    function getServerList(param){
        replyService.getList(param, (replyArr) => {
            const liArr= replyArr.map(reply => `<li>\${reply.rno}</li>`)
            replyUL.innerHTML = liArr.join(" ")
            //이 함수는 axios통신 된 후에 실행되어야 한다
            printPage(param.page)
            //param은 현재 페이지를 가지고 있다
            //서버에서 목록가져올때 페이지도 같이 뿌리면 되지
        })
    }

    function addServerReply(){

    replyService.addReply(
        {bno: bno,
        replyText: qs("input[name='replyText']").value,
        replyer:qs("input[name='replyer']").value},
        pageSize,
        //이제 값을 다 많들어 놧으니 입력하는 이벤트를 만들어야지
        //원래 다른 방법을 쓰시려 한것 같지만 pageNum을 같이 보내자
        (param)=> {
            getServerList(param)
    //이걸 실행하고 응답 받으면 해야할 작업이있다=> .then, callback 이 가능하다
        }
        )
    }
    qsAddEvent(".addReplyBtn","click",addServerReply)
    qsAddEvent(".pageUL","click", (evt,realTarget) => {
        //이벤트 위임을 위해 만듬
        const num = realTarget.getAttribute("data-num")
        //페이지 버튼마다 자신의 페이지를 data- 로 보낸다
        // alert(num)
        getServerList({bno:bno,page:num,size:pageSize})
        //페이지 이동햇으니 새로 뿌려준다

    }, "li")

    //이벤트 위임은 안되는 녀석

    //이벤트의 간소화를 위해 제작

    // function printReplies(replyArr){
    //     //목록 출력용
    //
    //     const liArr= replyArr.map(reply => `<li>AAA<li>`
    //     )
    //     replyUL.innerHTML = liArr.join(" ")
    //     //이 함수는 axios통신 된 후에 실행되어야 한다
    // }

    //after loading 게시글이 열릴때 댓글 목록이 보여야한다
    const pageParam = Math.ceil(replyCount/pageSize)
    //소수점 나오니까 반올림 해야함 ceil

    getServerList({bno:bno,page:pageParam,size:pageSize})



</script>
<%--현재 컨트롤러에 파라메터에 ListDTO가 등록되어있고 dto는 mapper를 통해 넘겨줌--%>
<%--dto 는 현재 selectOne 을헤서 나온 boardDTO 객체 하나--%>
</body>
</html>
