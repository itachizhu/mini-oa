package org.mini.framework.dao.impl;

public abstract class GenericDAOImpl<T> extends HibernateGenericDaoImpl<T> {

	
	/*
	 * public List<T> queryBySql(String sql) { List<T> list = new
	 * ArrayList<T>(); Session session = null; //Transaction t = null; try {
	 * session = getSession(); //t = session.beginTransaction(); //list =
	 * session.createSQLQuery(sql).addEntity(entityClass).list(); SQLQuery query
	 * = session.createSQLQuery(sql);
	 * 
	 * // 1、将查询结果转换成List<Map<字段名,字段值>>
	 * query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP); //
	 * 2、将结果转换成指定的bean List<你的指定javaBean>
	 * query.setResultTransformer(Transformers.aliasToBean(entityClass)); list =
	 * query.list(); //t.commit(); } catch (Exception e) { e.printStackTrace();
	 * //t.rollback(); } finally { //t = null; //session.close(); } return list;
	 * }
	 */

}
