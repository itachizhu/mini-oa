/**
 * 
 */
package org.mini.common.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Administrator
 *
 */
public abstract class CacheImpl implements Cache {

	private static ConcurrentMap<String, Object> cache = new ConcurrentHashMap<String, Object>();

	@Override
	public void put(String key, Object value) {
		// TODO Auto-generated method stub
		if(hasKey(key))
			remove(key);
		
		cache.put(key, value);
	}

	@Override
	public Object get(String key) {
		// TODO Auto-generated method stub
		if(hasKey(key))
			return cache.get(key);
		
		return null;
	}

	@Override
	public void remove(String key) {
		// TODO Auto-generated method stub
		cache.remove(key);
	}

	@Override
	public boolean hasKey(String key) {
		// TODO Auto-generated method stub
		return cache.containsKey(key);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		cache.clear();
	}

	@Override
	public int size() {
		return cache.size();
	}
}
