<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>聊天室</title>
    <style type="text/css">
        * {
            margin: 0px;
            padding: 0px
        }

        body {
            background: rgba(203, 130, 167, 0.56)
        }

        div {
            border: 1px solid red;
        }

        .frame {
            background: #fff;
            width: 700px;
            height: 500px;
            margin: 30px auto;
        }

        .content {
            width: 79%;
            height: 500px;
            float: left;
        }

        .online {
            width: 19%;
            height: 500px;
            float: left;
        }

        .message {
            height: 300px;
        }

        .send {
            overflow: hidden;
        }

        .online li {
            list-style: none;
        }


    </style>
</head>
<body>
<div class="frame">
    <div class="content">
        <div class="title"><h3>amcjt聊天室</h3></div>
        <div id="info" class="message"></div>
        <div class="send">
            <textarea id="txtMsg" cols=20 rows=5></textarea>
            <button id="btn" onclick="sendMessage()">发送</button>
        </div>
    </div>

    <div class="online">
        <ul>
            <li>李四</li>
            <li>阿斯顿</li>
            <li>登录</li>
            <li>拉我</li>
            <li>偶尔哦</li>
            <li>看啦旁边</li>
        </ul>
    </div>
</div>
<script>
    var socket = null;
    if ('WebSocket' in window) {
        socket = new WebSocket("ws://192.168.1.112:8080/charServer");
    }else {
        alert('该浏览器不支持');
    }



    socket.onopen = function (event) {
        alert("建立连接");
    }
    socket.onclose = function (event) {
        alert("close");
    }
    socket.onmessage = function (message) {
        document.getElementById("info").innerHTML+=("<p>"+ message.data+"</p>");
    }
    socket.onerror = function (event) {
        alert("error");
    }


    function sendMessage() {
        var msg = document.getElementById("txtMsg").value;
        socket.send(msg);
        document.getElementById("txtMsg").value = "";
    }

</script>
</body>
</html>
