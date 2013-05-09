/**
 * 
 */
package org.mini.framework.webservice;

import org.apache.cxf.frontend.ClientProxyFactoryBean;

/**
 * @author Administrator
 * 
 */
public class WebServiceClient {

	private WebServiceClient() {

	}

	@SuppressWarnings("unchecked")
	public static <T> T getService(String url, Class<T> clazz) {
		ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
		factory.setServiceClass(clazz);
		factory.setAddress(url);
		T service = (T) factory.create();
		return service;
	}

}
