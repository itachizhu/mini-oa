/**
 * 
 */
package org.mini.framework.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mini.common.utils.StringUtil;
import org.mini.framework.bean.ColumnParameter;
import org.mini.framework.bean.FieldColumn;
import org.mini.framework.bean.Operate;
import org.mini.framework.cache.ColumnParametersCache;
import org.mini.framework.dao.GenericDao;

/**
 * @author Administrator
 * 
 */
public abstract class HibernateGenericDaoImpl<T> implements GenericDao<T> {
	protected Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public HibernateGenericDaoImpl() {
		entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Resource
	private SessionFactory sessionFactory;

	@Resource
	ColumnParametersCache columnParametersCache;

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	protected T queryForObject(final String hql, Object[] params) {
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		return (T) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	protected T queryForTopObject(final String hql, Object[] params) {
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		return (T) query.setFirstResult(0).setMaxResults(1).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	protected List<T> queryForList(final String hql, Object[] params) {
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	protected List<T> queryForList(final String hql, final Object[] params,
			final int recordNum) {
		Query query = getSession().createQuery(hql);
		setQueryParams(query, params);
		return query.setFirstResult(0).setMaxResults(recordNum).list();
	}

	private void setQueryParams(Query query, Object[] params) {
		if (null == params) {
			return;
		}
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
	}

	@Override
	public void insert(String statement, T t, List<FieldColumn> parameters)
			throws Exception {
		if (!StringUtil.isNullOrEmpty(statement) && parameters != null
				&& parameters.size() > 0) {
			Query query = getSession().getNamedQuery(statement);

			for (FieldColumn parameter : parameters) {
				Field f = entityClass
						.getDeclaredField(parameter.getFieldName());
				Method m = (Method) entityClass.getMethod("get"
						+ StringUtil.firstLetterToUpper(f.getName()));
				Object o = m.invoke(t);

				if (o instanceof List<?>)
					query.setParameterList(parameter.getParameterName(),
							((List<?>) o).toArray());
				else
					query.setParameter(parameter.getParameterName(), o);
			}

			query.executeUpdate();
		} else if (!StringUtil.isNullOrEmpty(statement)) {
			Query query = getSession().getNamedQuery(statement);
			query.executeUpdate();
		} else {
			getSession().save(t);
		}
	}

	@Override
	public void delete(String statement, T t, List<FieldColumn> parameters)
			throws Exception {
		if (!StringUtil.isNullOrEmpty(statement) && parameters != null
				&& parameters.size() > 0) {
			Query query = getSession().getNamedQuery(statement);

			for (FieldColumn parameter : parameters) {
				Field f = entityClass
						.getDeclaredField(parameter.getFieldName());
				Method m = (Method) entityClass.getMethod("get"
						+ StringUtil.firstLetterToUpper(f.getName()));
				Object o = m.invoke(t);

				if (o instanceof List<?>)
					query.setParameterList(parameter.getParameterName(),
							((List<?>) o).toArray());
				else
					query.setParameter(parameter.getParameterName(), o);
			}

			query.executeUpdate();
		} else if (!StringUtil.isNullOrEmpty(statement)) {
			Query query = getSession().getNamedQuery(statement);
			query.executeUpdate();
		} else {
			getSession().delete(t);
		}
	}

	@Override
	public void delete(String statement, Serializable id) throws Exception {
		getSession().delete(queryById(statement, id));
	}

	@Override
	public void update(String statement, T t, List<FieldColumn> parameters)
			throws Exception {
		if (!StringUtil.isNullOrEmpty(statement) && parameters != null
				&& parameters.size() > 0) {
			Query query = getSession().getNamedQuery(statement);

			for (FieldColumn parameter : parameters) {
				Field f = entityClass
						.getDeclaredField(parameter.getFieldName());
				Method m = (Method) entityClass.getMethod("get"
						+ StringUtil.firstLetterToUpper(f.getName()));
				Object o = m.invoke(t);

				if (o instanceof List<?>)
					query.setParameterList(parameter.getParameterName(),
							((List<?>) o).toArray());
				else
					query.setParameter(parameter.getParameterName(), o);
			}

			query.executeUpdate();
		} else if (!StringUtil.isNullOrEmpty(statement)) {
			Query query = getSession().getNamedQuery(statement);
			query.executeUpdate();
		} else {
			getSession().update(t);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T queryById(String statement, Serializable id) throws Exception {
		return (T) getSession().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T query(String statement, T t, List<FieldColumn> parameters)
			throws Exception {
		if (!StringUtil.isNullOrEmpty(statement) && parameters != null
				&& parameters.size() > 0) {
			Query query = getSession().getNamedQuery(statement);

			for (FieldColumn parameter : parameters) {
				Field f = entityClass
						.getDeclaredField(parameter.getFieldName());
				Method m = (Method) entityClass.getMethod("get"
						+ StringUtil.firstLetterToUpper(f.getName()));
				Object o = m.invoke(t);

				if (o instanceof List<?>)
					query.setParameterList(parameter.getParameterName(),
							((List<?>) o).toArray());
				else
					query.setParameter(parameter.getParameterName(), o);
			}

			return (T) query.list().get(0);
		} else if (!StringUtil.isNullOrEmpty(statement)) {
			Query query = getSession().getNamedQuery(statement);
			return (T) query.list().get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<T> queryAll(String statement) throws Exception {
		String hql = "from " + entityClass.getSimpleName();
		return queryForList(hql, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <P extends Object> List<T> queryAll(String statement, P p,
			List<FieldColumn> parameters) throws Exception {
		if (!StringUtil.isNullOrEmpty(statement) && parameters != null
				&& parameters.size() > 0 && p != null) {
			Query query = getSession().getNamedQuery(statement);

			for (FieldColumn parameter : parameters) {
				Field f = entityClass
						.getDeclaredField(parameter.getFieldName());
				Method m = (Method) entityClass.getMethod("get"
						+ StringUtil.firstLetterToUpper(f.getName()));
				Object o = m.invoke(p);

				if (o instanceof List<?>)
					query.setParameterList(parameter.getParameterName(),
							((List<?>) o).toArray());
				else
					query.setParameter(parameter.getParameterName(), o);
			}

			return (List<T>) query.list();
		} else if (!StringUtil.isNullOrEmpty(statement)) {
			Query query = getSession().getNamedQuery(statement);
			return (List<T>) query.list();
		} else {
			String hql = "from " + entityClass.getSimpleName();
			return queryForList(hql, null);
		}
	}

	private List<FieldColumn> getFieldColumn(String statement) {
		if (columnParametersCache != null) {
			List<ColumnParameter> parameters = columnParametersCache
					.getParameters();

			if (parameters == null)
				return null;

			String key = entityClass.getName();

			for (ColumnParameter p : parameters) {
				if (p.getClassId().equals(key)) {
					if (p.getOperates() != null) {
						List<Operate> operates = p.getOperates();
						for (Operate o : operates) {
							if (o.getOperateId().equals(statement)) {
								return o.getFieldColumn();
							}
						}
					} else {
						break;
					}
				}
			}
		}

		return null;
	}

	@Override
	public void insert(String statement, T t) throws Exception {
		// TODO Auto-generated method stub
		List<FieldColumn> filedColumn = getFieldColumn(statement);

		insert(statement, t, filedColumn);
	}

	@Override
	public void delete(String statement, T t) throws Exception {
		// TODO Auto-generated method stub
		List<FieldColumn> filedColumn = getFieldColumn(statement);

		delete(statement, t, filedColumn);
	}

	@Override
	public void update(String statement, T t) throws Exception {
		// TODO Auto-generated method stub
		List<FieldColumn> filedColumn = getFieldColumn(statement);

		update(statement, t, filedColumn);
	}

	@Override
	public T query(String statement, T t) throws Exception {
		// TODO Auto-generated method stub
		List<FieldColumn> filedColumn = getFieldColumn(statement);
		//System.out.println("读取了filed数据");

		return query(statement, t, filedColumn);
	}

	@Override
	public <P> List<T> queryAll(String statement, P p) throws Exception {
		// TODO Auto-generated method stub
		List<FieldColumn> filedColumn = getFieldColumn(statement);

		return queryAll(statement, p, filedColumn);
	}

}
