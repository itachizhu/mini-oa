<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/pages/includes/header.jsp"%>
<%@include file="/pages/includes/struts-tags.jsp"%>
<title><s:property value="%{getText('user.list.title')}"/></title>
</head>
<body>
	<div id="user">
	<input type="hidden" id="user.id" value="{user.id}" />
	用户名：<input type="text" id="user.userName" value="${user.userName}" /><br/>
	工号：<input type="text" id="user.empNo" value="${user.empNo}" /><br/>
	密码：<input type="text" id="user.password" value="${user.password}" /><br/>
	<input type="button" value="提交" />
	</div>
</body>
</html>