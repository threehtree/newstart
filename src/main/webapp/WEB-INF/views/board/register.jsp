<%--
  Created by IntelliJ IDEA.
  User: ehdwn
  Date: 2022-04-30
  Time: 오후 4:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>
</head>
<body>
<h1>Register</h1>
<form action="/board/register" method="post">
    <input type="text" name="title">
    <button>Click</button>
</form>

<h2>파일 업로드 테스트 </h2>
<form action="/upload1" method="post" enctype="multipart/form-data">
    <input type="file" name="files" multiple>
    <button>Upload</button>
</form>

<div>
    <h2>Ajax Upload</h2>
    <input type="file" name="upload" multiple class="uploadFile">
    <button class="uploadBtn">UPLOAD</button>
</div>
<%--<img src="C:\upload\1승리 - 복사본">--%>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

<script>
    document.querySelector(".uploadBtn").addEventListener("click", (e)=>{
        const formobj = new FormData();
        const fileInput = document.querySelector(".uploadFile")
        console.log(fileInput.files)
        const files = fileInput.files
        console.log(files.length)

        for(let i = 0 ; i < files.length; i++){
            console.log(files[i])
            //FormData 형식으로 파일값을 가져옴
            formobj.append("files", files[i]);
            // 우리가 multiple이라 여러파일을 다 추가해야한다
            uploadToServer(formobj)

        }
    }, false)




//---------------------------------------------------------------------
    async function uploadToServer (formObj) {

        console.log("upload to server......")
        console.log(formObj)//FormData 받음

        const response = await axios({
            method: 'post',
            url: '/upload1',
            data: formObj,
            headers: {
                'Content-Type': 'multipart/form-data',
            },
        });

        return response.data
        //비동기는 promise 반환
    }
</script>

</body>
</html>
