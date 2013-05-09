/**
 * 
 */
package org.mini.common.cache;

/**
 * @author Administrator
 * 
 */
public interface Cache {
	public void put(String key, Object value);

	public Object get(String key);

	public void remove(String key);

	public boolean hasKey(String key);

	public void clear();
	
	public int size();
}
