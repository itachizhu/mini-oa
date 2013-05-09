/**
 * 
 */
package org.mini.web.service.impl;

import org.mini.web.service.TestService;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 *
 */
@Component("TestService")
public class TestServiceImpl implements TestService {

	@Override
	public int add(int num1, int num2) {
		// TODO Auto-generated method stub
		return num1 + num2;
	}

}
