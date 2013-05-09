package org.mini.web.action;

import javax.annotation.Resource;

import org.mini.framework.action.BaseAction;

public class LoginAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//这些是跟客户直接打交道的数据。建立好后再建立一下getter和setter就好了，前面介绍过，就不一一介绍了。
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	//Action的默认方法，至于怎么回事，你别管。问Struts作者去吧
	public String execute() throws Exception {
		return SUCCESS;
		/*
		if(getUsername().equals("test") && getPassword().equals("test")) {
			return "success";
		} else {
			return "error";
		}
		*/
	}
	
	//自定义方法，以供调用。
	public String login() throws Exception {
	
		
		return INPUT;
	}
}
