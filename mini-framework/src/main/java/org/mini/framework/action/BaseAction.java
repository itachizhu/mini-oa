package org.mini.framework.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements Action,ServletRequestAware, SessionAware, ServletResponseAware { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected Map<String, Object> session;
    protected ActionMapping actionMapping;
    
    private boolean isSuccess = false;
    private String message;
    
    public void setIsSuccess(boolean isSuccess) {
    	this.isSuccess = isSuccess;
    }
    
    public boolean getIsSuccess() {
    	return isSuccess;
    }
    
    public void setMessage(String message) {
    	this.message = message;
    }
    
    public String getMessage() {
    	return message;
    }
	
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
	
	public HttpServletResponse GetResponseInstance() {
		return response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	
	public HttpServletRequest GetRequestInstance() {
		return this.request;
	}
	
	public void setActionMapping(ActionMapping actionMapping) {  
        this.actionMapping = actionMapping;  
    }
	
	protected void noCache() {
		response.setHeader("Pragma","No-cache");   
		response.setHeader("Cache-Control","no-cache");  
		response.setHeader("Cache-Control", "no-store");  
		response.setDateHeader("Expires", 0);
	}

	public boolean checkRole() {
		return true;
	}
	
	public boolean isAjax() {
		return request.getHeader("X-Requested-With") != null;
	}
}
