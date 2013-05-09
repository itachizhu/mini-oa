<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="/struts-tags" prefix="s"%>
 <%
  String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
<script src="<%=path%>/scripts/jquery-1.8.0.min.js" type="text/javascript"></script>
<script src="<%=path%>/scripts/jquery.json-2.3.min.js" type="text/javascript"></script>
</head>
<body>
<table align="center">
 	<caption><h3>用户登录</h3></caption>
     <tr>
         <td>用户名：<input type="text" name="username" id="username" /></td>
     </tr>
     <tr>
         <td>密&nbsp;&nbsp;码：<input type="password" name="password" id="password" /></td>
     </tr>
     <tr align="center">
         <td colspan="2"><input type="button" value="登录" id="login"/></td>
     </tr>
 </table>
</body>
</html>
<script type="text/javascript">
$(function(){
	$("#login").click(function(){
		var obj = {"username":$("#username").val(),"password":$("#password").val()};
		//使用jquery的ajax请求
		$.post("doLogin", obj,
                function (item) {
                    if(item["isSuccess"]) {
                    	//如果你输入的用户名和密码正确，应该是输出这个消息
                    	alert("用户名密码正确");
                    } else {
                    	//否则是这样，这些都可以自定义，根据需要改动的。
                    	alert(item["message"]);
                    }
                });
	});
});

</script>