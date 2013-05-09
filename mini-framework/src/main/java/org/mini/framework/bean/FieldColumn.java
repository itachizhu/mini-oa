/**
 * 
 */
package org.mini.framework.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.*;

/**
 * @author Administrator
 *
 */
@XmlRootElement(name = "columnParameter")
@XmlAccessorType(XmlAccessType.FIELD)
public final class FieldColumn implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8447221754261874656L;
	
	@XmlAttribute(name = "columnName")
	private String columnName;
	@XmlAttribute(name = "fieldName")
	private String fieldName;
	@XmlAttribute(name = "parameterName")
	private String parameterName;
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
}
