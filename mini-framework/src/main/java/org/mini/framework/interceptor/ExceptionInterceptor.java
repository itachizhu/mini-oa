/**
 * 
 */
package org.mini.framework.interceptor;

import org.mini.framework.action.BaseAction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author Administrator
 *
 */
public class ExceptionInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("进入Exception拦截器");
		
		try {
			return invocation.invoke();
		} catch(Exception e) {
			e.printStackTrace();
			//记录日志
			
			//处理action
			return handleException(invocation);
		}
	}
	
	private String handleException(ActionInvocation invocation) {
		try {
			BaseAction action = (BaseAction)invocation.getAction();
			
			action.setIsSuccess(false);
			action.setMessage("系统异常，请联系管理员");
			//
			if(action.isAjax()) {
				return Action.INPUT;
			} else {
				return Action.ERROR;
			}
		} catch(Exception e) {
			return Action.ERROR;
		}
	}

}
