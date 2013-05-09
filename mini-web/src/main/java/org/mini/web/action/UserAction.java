/**
 * 
 */
package org.mini.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.mini.framework.action.BaseAction;
import org.mini.model.system.User;
import org.mini.service.UserService;

import com.opensymphony.xwork2.Action;

/**
 * @author Administrator
 *
 */
public class UserAction extends BaseAction {
	
	@Resource
	private UserService userService;
	
	private List<User> list;
	private User user;
	private long id;

	/**
	 * @return the list
	 */
	public List<User> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	/*public void setList(List<User> list) {
		this.list = list;
	}*/
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String execute() throws Exception {
		list = userService.search(null);
		
		return Action.SUCCESS;
	}
	
	public String list() throws Exception {
		list = userService.search(user);
		
		return Action.SUCCESS;
	}

	public String view() throws Exception {
		if(id != 0) {
			user = userService.get(id);
		}
		
		return Action.SUCCESS;
	}
}
