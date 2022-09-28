<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>json异常测试</title>
    <script
            src="http://code.jquery.com/jquery-1.12.4.js"
            integrity="sha256-Qw82+bXyGq6MydymqBxNPYTaUXXq7c8v3CwiYwLLNXU="
            crossorigin="anonymous"></script>

</head>
<body>
<div>
    <input type="button" onclick="requestJson()" value="请求json"/>
</div>
<div>请求体为：</div>
<div>
    <TextArea id="textReq" style="width: 200px;height: 100px"></TextArea>
</div>

<div>服务器返回结果：</div>
<div>
    <TextArea id="textArea" style="width: 200px;height: 100px"></TextArea>
</div>


</body>

<script type="text/javascript">
    //请求json，输出是json
    function requestJson() {

        var product = {};
        product.id = "3000";
        product.name = "Apple iPhone X";

        console.log(JSON.stringify(product));
        $("#textReq").val(JSON.stringify(product));

        $.ajax({
            type: 'post',
            url: '${pageContext.request.contextPath }/exception/json',
            contentType: 'application/json;charset=utf-8',
            //数据格式为json串
            data: JSON.stringify(product),
            success: function (data) {//返回json结果
                console.log(data);
                $("#textArea").val(JSON.stringify(data));
            }

        });


    }
</script>
</html>
