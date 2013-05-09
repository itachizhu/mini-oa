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
	<form action="user_list" method="post" enctype="application/x-www-form-urlencoded">
		用户名：<input type="text" name="user.userName" value="${user.userName}" />
		工号：<input type="text" name="user.empNo" value="${user.empNo}" />
		<input type="submit" value="搜索" />
	</form>
	<table border="1"
		style="border-width: 1px; border-collapse: collapse; border-spacing: 1px; border-color: black;">
		<thead>
			<tr>
				<th>用户名</th>
				<th>密码</th>
				<th>添加日期</th>
				<th>是否有效</th>
				<th>是否删除</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="list" id="user">
				<tr>
					<td><s:property value="#user.userName" /></td>
					<td>${password}</td>
					<td>${createTime}</td>
					<td>
						<s:if test='%{#user.valid}'>是</s:if>
						<s:else>否</s:else>
					</td>
					<td>
						<s:if test='%{#user.deleted}'>是</s:if>
						<s:else>否</s:else>
					</td>
					<td><a href="javascript:showEdit(<s:property value="#user.id" />);">编辑</a> | <a href="javascript:void(${id});">删除</a></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</body>
</html>
<script type="text/javascript">
	function showEdit(id) {
		var title = "新增客户";
		var url = "user_view";
		if (id) {
			title = "编辑客户";
			url = url + "?id=" + id;
		}
		showWindow(title, url, 500, 500);
	}

	function del(id) {
		top.showBox();
		$.ajax({
			url : "user_delete",
			data : {
				"id" : id
			},
			success : function(result) {
				alert(result);
				/*
				if (!result) {
					top.showBox("删除失败", MSG_TYPE.ERROR);
					return;
				}
				top.showBox("删除成功", MSG_TYPE.SUCCESS, refresh);
				*/
			}
		});
	}
</script>