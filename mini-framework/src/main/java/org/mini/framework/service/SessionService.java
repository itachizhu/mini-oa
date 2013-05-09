/**
 * 
 */
package org.mini.framework.service;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 *
 */
public interface SessionService {
	boolean checkAuthority(HttpServletRequest request, HttpServletResponse response);
	
	boolean checkAuthority(HttpServletRequest request, HttpServletResponse response, String sessionKey, Set<String> filterUrlList, String redirectURL);
}
