/**
 * 
 */
package org.mini.framework.cache;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;


/**
 * @author Administrator
 *
 */
public abstract class XmlCache<T> {
	private static Logger logger = LoggerFactory.getLogger(XmlCache.class);
	
	private final ConcurrentMap<String, T> xmlCache = new ConcurrentHashMap<String, T>();
	
	private final ConcurrentMap<String, String> fileStamp = new ConcurrentHashMap<String, String>();
	
	private final ResourceLoader resourceLoader = new DefaultResourceLoader();
	
	private static String configFolderName = "classpath:";
	
	private String configFileName = "beans_to_columns.xml";
	
	protected Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public XmlCache() {
		clazz = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	protected String getConfigFileName() {
		return configFileName;
	}
	
	protected void setConfigFileName(String configFileName) {
		this.configFileName = configFileName;
	}
	
	private boolean isNeedRefresh() {
		try {
			Resource resource = resourceLoader.getResource(configFolderName + configFileName);
			
			if(!resource.exists())
				return true;
			
			String lastStamp = String.valueOf(resource.getFile().lastModified());
			String key = resource.getFile().getName().toLowerCase();
			if(fileStamp.containsKey(key)) {
				if(fileStamp.get(key).equals(lastStamp) && xmlCache.containsKey(clazz.getName())) {
					return false;
				} else {
					fileStamp.remove(key);
					fileStamp.put(key, lastStamp);
					return true;
				}
			} else {
				fileStamp.put(key, lastStamp);
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("xml文件解释读取输入流错误:", e);
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("xml文件解释其他异常错误:", e);
			e.printStackTrace();
		}
		
		return true;
	}
	
	@SuppressWarnings("unchecked")
	protected T getInstance() {
		if(isNeedRefresh()) {
			String key = clazz.getName();
			InputStream is = null;
			try {
				Resource resource = resourceLoader.getResource(configFolderName + configFileName);
				
				if(!resource.exists())
					return null;
				
				JAXBContext context = JAXBContext.newInstance(clazz);
				
				is = resource.getInputStream();
				
				T value = (T)context.createUnmarshaller().unmarshal(is);
				
				xmlCache.put(key, value);
				
				logger.debug("从文件中读取数据");
				
				return value;
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				logger.error("jaxb解释错误:", e);
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("xml文件解释读取输入流错误:", e);
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("xml文件解释其他异常错误:", e);
				e.printStackTrace();
			} finally {
				if(is != null) {
					try {
						is.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						logger.error("xml文件解释关闭输入流错误:", e);
					}
					is = null;
				}
			}
			return null;
		} else {
			logger.debug("从内存缓存中读取数据");
			return xmlCache.get(clazz.getName());
		}
	}
}
