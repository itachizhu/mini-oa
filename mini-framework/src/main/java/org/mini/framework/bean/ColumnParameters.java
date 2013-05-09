/**
 * 
 */
package org.mini.framework.bean;

import java.util.List;

import javax.xml.bind.annotation.*;

/**
 * @author Administrator
 *
 */
@XmlRootElement(name = "classMaps")
@XmlAccessorType(XmlAccessType.FIELD)
public final class ColumnParameters {
	@XmlElement(name = "columnParameters",nillable=true)
	private List<ColumnParameter> parameters;

	public List<ColumnParameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<ColumnParameter> parameters) {
		this.parameters = parameters;
	}
}