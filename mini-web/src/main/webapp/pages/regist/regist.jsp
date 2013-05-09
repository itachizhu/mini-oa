<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

<title>请输入您的注册信息</title>

</head>


<body>
<H1>请输入您的注册信息</H1>
<s:fielderror/>
<FORM METHOD="POST" ACTION="regist">
	用户名：<INPUT TYPE="text" NAME="name"><br>
	密&nbsp;&nbsp;码：<INPUT TYPE="text" NAME="pass"><br>
	年&nbsp;&nbsp;龄：<INPUT TYPE="text" NAME="age"><br>
	生&nbsp;&nbsp;日：<INPUT TYPE="text" NAME="birth"><p>
	<INPUT TYPE="submit" value="注册">
</FORM>


</body>

</html>