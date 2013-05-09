/**
 * 
 */
package org.mini.framework.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 *
 */
public class HttpUtil {
	private HttpUtil() {
		
	}
	
	public static boolean isAjax(HttpServletRequest request) {
		return request.getHeader("X-Requested-With") != null;
	}
}
