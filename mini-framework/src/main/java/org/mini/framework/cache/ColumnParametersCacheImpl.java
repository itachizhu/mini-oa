/**
 * 
 */
package org.mini.framework.cache;

import java.util.List;

import org.mini.framework.bean.ColumnParameter;
import org.mini.framework.bean.ColumnParameters;

/**
 * @author Administrator
 *
 */
public class ColumnParametersCacheImpl extends XmlCache<ColumnParameters> implements ColumnParametersCache {
	
	public ColumnParametersCacheImpl(String fileName) {
		super.setConfigFileName(fileName);
	}
	
	public ColumnParametersCacheImpl() {
		
	}
	
	@Override
	public void setConfigFileName(String fileName) {
		super.setConfigFileName(fileName);
	}

	@Override
	public List<ColumnParameter> getParameters() {
		// TODO Auto-generated method stub
		ColumnParameters value = super.getInstance();
		if(value == null)
			return null;
		
		return value.getParameters();
	}
	
	
	
}
