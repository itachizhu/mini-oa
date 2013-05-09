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
@XmlRootElement(name = "operate")
@XmlAccessorType(XmlAccessType.FIELD)
public class Operate {
	@XmlAttribute(name = "operateId")
	private String operateId;
	
	@XmlElement(name = "columnParameter", nillable = true)
	private List<FieldColumn> fieldColumn;

	public String getOperateId() {
		return operateId;
	}

	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}

	public List<FieldColumn> getFieldColumn() {
		return fieldColumn;
	}

	public void setFieldColumn(List<FieldColumn> fieldColumn) {
		this.fieldColumn = fieldColumn;
	}

}
