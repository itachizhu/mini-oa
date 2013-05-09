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
@XmlRootElement(name = "columnParameters")
@XmlAccessorType(XmlAccessType.FIELD)
public final class ColumnParameter {
	@XmlAttribute(name = "classId")
	private String classId;

	@XmlElement(name = "operate", nillable = true)
	private List<Operate> operates;

	public List<Operate> getOperates() {
		return operates;
	}

	public void setOperates(List<Operate> operates) {
		this.operates = operates;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

}
