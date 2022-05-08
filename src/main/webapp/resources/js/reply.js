
const replyService = (function (){


    const addReply = async function (replyObj, callback){
        //댓글 추가
        console.log("AddReply...........")

        const res = await axios.post("/replies/", replyObj)
        callback()
    }

    const getList = async function (bno,callback){
        //댓글 목록 가져오기
        console.log("getlist...........")

        const res =await axios.get(`/replies/list/${bno}`)
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