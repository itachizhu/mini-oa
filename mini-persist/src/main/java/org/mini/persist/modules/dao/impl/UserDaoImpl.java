/**
 * 
 */
package org.mini.persist.modules.dao.impl;

import java.util.List;

import org.mini.framework.dao.impl.GenericDAOImpl;
import org.mini.model.system.User;
import org.mini.persist.modules.dao.UserDao;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 *
 */
@Repository
public class UserDaoImpl extends GenericDAOImpl<User> implements UserDao {
	private final String QUERY_USER = "queryUer";

	@Override
	public void insert(User user) throws Exception {
		// TODO Auto-generated method stub
		super.insert(null, user);
	}

	@Override
	public void update(User user) throws Exception {
		// TODO Auto-generated method stub
		super.update(null, user);
	}

	@Override
	public List<User> search(User user) throws Exception {
		// TODO Auto-generated method stub
		//return super.queryAll(null);
		if (user == null) {
			return super.queryAll(null);
		}
		return super.queryAll(QUERY_USER, user);
	}

	@Override
	public void delete(long id) throws Exception {
		// TODO Auto-generated method stub
		super.delete(null, id);
	}

	@Override
	public User get(long id) throws Exception {
		// TODO Auto-generated method stub
		return super.queryById(null, id);
	}

}
