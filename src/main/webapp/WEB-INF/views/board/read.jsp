
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
    <ul class="replyUL">

    </ul>
</div>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<%--첫 axios 사용겸 async ,await 에 대한 설명이 나옴--%>

<script>

    async function getReplyList(bno){
        try{
        const res = await axios.get(`/replies/list/${bno}`)
        const data = res.data

        return data
        }catch (err){
            return err
        }
    }

    const bno = ${dto.bno}
        getReplyList(bno)
            .then(arr=>{
                const liStr=arr.map(replyDTO => `<li>\${replyDTO.rno}</li>`).join(" ")
                document.querySelector(".replyUL").innerHTML = liStr
            })
            .catch(err=> console.log(err))
     //axios 첫 통신 확인
    //ajax 는 댓글 같은곳에 쓰면 되겟다
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
