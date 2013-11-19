package br.finf.dao.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@SuppressWarnings("serial")
public abstract class AbstractEntity implements Serializable {

	//public abstract void setId(Long id);

	//public abstract Long getId();
	
	protected boolean compareDouble(Double d1, Double d2) {
		if  (d1 == null && d2 == null) {
			return true;
		}
		if (d1 == null || d2 == null) {
			return false;
		}
		return d1.equals(d2);
	}

}
