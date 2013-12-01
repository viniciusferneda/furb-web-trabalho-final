package br.certics.model.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="SOFTWARE")
@SequenceGenerator(name="SOF_ID", sequenceName="SOF_ID")
@NamedQueries({ 
	@NamedQuery(name="SoftwareEntity.selectAll", query="select obj from SoftwareEntity obj ")
	})
public class SoftwareEntity extends Software{

}
