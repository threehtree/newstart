
const replyService = (function (){


    const addReply = async function (replyObj, size, callback){
        //댓글 추가
        console.log("AddReply...........")

        const res = await axios.post("/replies/", replyObj)

        const replyCount = parseInt(res.data.result)
        //이걸로 댓글의 총갯수를 줄 수 잇지만  추가로
        //size와 게시글의번호 bno가 필요하다
        //이걸 addReply호출때 마다 값을 가져올까?
        //reply.js라는 모듈패턴 에 아예 잡아둘까?
        //모듈패턴은 데이터를 유지 가능하다 [클로저]
        const bno = replyObj.bno
        const page = Math.ceil(replyCount/size)
        callback({bno,page,size})
    }

    const getList = async function ({bno,page,size},callback){
        //댓글 목록 가져오기
        console.log("getlist...........")

        const parameter = {page:page||1, size:size||10}
        //값이 없을때 기본값 지정

        const res =await axios.get(`/replies/list/${bno}`,{params: parameter})
        //{params: parameter} 이부분이 쿼리스트링 만드는 부분
        //console.log(res.data)
        callback(res.data)

    }

    return {addReply,getList}
    //이렇게 되면 외부에서 참조할수 있다
    //replyService.getList 이런식
    //함수를 모듈처럼 쓸 수 있다

})()
//실행결과가 return이 된다

const qs = function (str){
    return document.querySelector(str)
}//노가다 하기 싫음 , 전역변수화

const qsAddEvent = function (selector, type, callback){
    const target = document.querySelector(selector)

    target.addEventListener(type, callback, false)
    //false는 캡쳐링 방지
}