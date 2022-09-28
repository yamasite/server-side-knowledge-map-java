<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Spring Page Redirection</title>
</head>
<body>
<h2>Spring 页面重定向</h2>
<p>点击下面按钮重定向到一个新的页面</p>
<form method="GET" action="/demo/redirect">
	<table>
		<tr>
			<td>
				<input type="submit" value="页面重定向"/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>