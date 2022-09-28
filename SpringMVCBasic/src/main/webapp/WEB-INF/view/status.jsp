<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<div>
    商品：
    ${productName}
</div>
<div>
    文件上传结果：
    ${message}
</div>

<hr>
<img src="download?filename=${newFileName}"/>
<h3>文件下载</h3>
<a href="download?filename=${newFileName}">
    ${originalFilename }
</a>

</body>
</html>
