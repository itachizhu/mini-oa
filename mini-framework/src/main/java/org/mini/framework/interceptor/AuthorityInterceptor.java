package org.mini.framework.interceptor;

import org.mini.framework.action.BaseAction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		BaseAction action = (BaseAction)invocation.getAction();
		
		System.out.println("进入Authority拦截器");
		
		if(action.checkRole()) {
			return invocation.invoke();
		} else if(action.isAjax()) {
			return Action.INPUT;
		} else {
			return Action.LOGIN;
		}
	}

}
