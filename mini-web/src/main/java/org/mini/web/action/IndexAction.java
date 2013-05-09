/**
 * 
 */
package org.mini.web.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.mini.framework.action.BaseAction;
import org.mini.framework.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * @author Administrator
 *
 */
@Controller
@Namespace("/")
public class IndexAction extends BaseAction {
	private static Logger logger = LoggerFactory.getLogger(IndexAction.class);
	
	@Resource(name="sessionService")
	private SessionService sessionService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Action(value = "", results = { @Result(name = SUCCESS, location = "/pages/index/index.jsp") })
	@Override
    public String execute() throws Exception {
		//org.springframework.orm.hibernate4.LocalSessionFactoryBean
		//List<Demo> list = demoService.queryAll();
		//org.springframework.web.util.Log4jConfigListener
		return SUCCESS;
	}

}
