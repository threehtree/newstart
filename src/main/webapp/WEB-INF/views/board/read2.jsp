<%--
  Created by IntelliJ IDEA.
  User: LDJ
  Date: 2022-05-07
  Time: 오전 7:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<ul class="replyUL">

</ul>

<ul class="pageUL">

</ul>



<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
    //-------------------------------------------------------------------
    //js파일로 뺏더니 브라우저에서 캐시 날려야 실험가능

    let initState = {
        bno: ${dto.bno},
        replyArr :[],
        replyCount : 0,
        size: 0,
        pageNum : 1
    }// 이 값들이 변하면 랜더링이 다시 필요하다
    //view model


    function render(obj) {
        console.log("Render....")
        const replyUL = document.querySelector(".replyUL")
        const pageUL = document.querySelector(".pageUL")
        //state의 모든 데이터를 다시 뿌려주는 거지
        //언디파인드 방지를 위해 초기값 줌
        function printList(){
            const arr = obj.replyArr

            replyUL.innerHTML = "<li>print list</li>"

        }
        function printPage(){
            pageUL.innerHTML = "<li>page list</li>"
        }
        printList()
        printPage()
    }


    //=====================================================================
    //axios 통신

    const replyService =//모듈패턴//
        // {
    //     state: initState,
    //     setState :function (changedState, callback){
    //         console.log("changedState",changedState)
    //         state = changedState
    //         //state값이 바뀌었으니까 뿌려주는 reder함수가 필요하다
    //     },
    //     //처음으로 initState값을 가져와서 초기화하고
    //     // State가 바뀐 값인 changedState 삽입
    //     getServerList: function (){
    //
    //     }
    // }
        (function (initState,callback){
        let state = initState
        // const callback = callbackFn

            const setState = (newState)=> {
            state = {...state, ...newState}
                console.log(state)
                //newState안에 replyCount라는 속성이 있다면, pageNum이라는 속성이 있다면
                if(newState.replyCount || newState.pageNum){

                }

                callback(state)
            }

            // async function getServerList(){
            //     const paramObj = {page: state.pageNum, size:state.size}
            //     const res = axios.get(`/replies/list/\${state.bno}`)
            // }(1) 18:37


            return {setState}
        })(initState, render)

    replyService.setState({replyCount:${dto.replyCount}})
    // replyService.setState({pageNum:11})


    <%--console.log("===================================")--%>
    <%--console.log(${dto})--%>
    <%--console.log("===================================")--%>
    //날짜 뛰어넘으니 Board,DTO 안에 추가 된 컬럼 replyCount를 프로퍼티에 추가안함
    //기본값이 0 이니까 Integer가 아닌 int
</script>
</body>
</html>
