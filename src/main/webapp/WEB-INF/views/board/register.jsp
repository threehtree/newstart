<%--
  Created by IntelliJ IDEA.
  User: cooki
  Date: 2022-04-13
  Time: 오후 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Register Page</h1>
<form action="/board/register" method="post">
    <input type="text" name="title">
    <button>CLICK</button>
</form>

<h2>파일 업로드 테스트</h2>
<form action="/upload1" method="post" enctype="multipart/form-data">
    <input type="file" name="files" multiple>
    <button>Upload</button>
</form>

<div>
    <h2>Ajax Upload</h2>
    <input type="file" name="upload" multiple class="uploadFile">
    <button class="uploadBtn">UPLOAD</button>
</div>

<div class="uploadResult">
<%--    여기에 받은 form데이타값 넣을것--%>

</div>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

<script>
    const uploadResult = document.querySelector(".uploadResult")

    document.querySelector(".uploadBtn").addEventListener("click",(e)=> {
        //axios통신을 위한 부분임
        const formObj = new FormData();
        //파일의 통신은 formdata를 사용해야한다함

        const fileInput = document.querySelector(".uploadFile")

        console.log(fileInput)
        console.log("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
        console.log(fileInput.files)


        const files = fileInput.files
        //파일에 대한 상세내용

        for (let i = 0; i < files.length; i++) {
            console.log(files[i])
            formObj.append("files", files[i])
            //지금 여러개의 파일을 보내서 배열
        }

        uploadToServer(formObj).then(resultArr =>{
            uploadResult.innerHTML = resultArr.map(result =>`<div>
            <img src="/view?fileName=\${result.thumbnail}">
            <button class = "delBtn">x</button>
            \${result.original}</div>`).join(" ")
            // 배열로 값이 뽑혀서 이렇게 join으로 하나의 문자열로 다 뽑아줘야함
            //00
        })

    }, false)


    async function uploadToServer (formObj) {

        console.log("upload to server......")
        console.log(formObj)

        const response = await axios({
            method: 'post',
            url: '/upload1',
            data: formObj,
            headers: {
                'Content-Type': 'multipart/form-data',
            },
        });

        return response.data
        //axios통신후 json문자열을 가져온듯
    }

</script>

</body>
</html>
