/**
 * 
 */
package org.mini.framework.dao;

import java.io.Serializable;
import java.util.List;

import org.mini.framework.bean.FieldColumn;

/**
 * @author Administrator
 *
 */
public interface GenericDao<T> {
	void insert(String statement, T t, List<FieldColumn> parameters) throws Exception;
	  
    void delete(String statement, T t, List<FieldColumn> parameters) throws Exception;
    
    void delete(String statement, Serializable id) throws Exception;
  
    void update(String statement, T t, List<FieldColumn> parameters) throws Exception;
  
    T queryById(String statement, Serializable id) throws Exception;
    
    T query(String statement, T t, List<FieldColumn> parameters) throws Exception;
  
    List<T> queryAll(String statement) throws Exception;
    
    <P extends Object> List<T> queryAll(String statement, P p, List<FieldColumn> parameters) throws Exception;
    
    void insert(String statement, T t) throws Exception;
	  
    void delete(String statement, T t) throws Exception;
  
    void update(String statement, T t) throws Exception;
    
    T query(String statement, T t) throws Exception;
    
    <P extends Object> List<T> queryAll(String statement, P p) throws Exception;
}
