/**
 * 
 */
package org.mini.service;

import java.util.List;

import org.mini.model.system.User;

/**
 * @author Administrator
 *
 */
public interface UserService {
	void insert(User user) throws Exception;
	void update(User user) throws Exception;
	List<User> search(User user) throws Exception;
	User get(long id) throws Exception;
	void delete(long id) throws Exception;
}
