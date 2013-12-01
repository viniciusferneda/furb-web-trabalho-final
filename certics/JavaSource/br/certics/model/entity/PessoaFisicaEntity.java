package br.certics.model.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="PESSOA_FISICA")
@SequenceGenerator(name="PES_ID", sequenceName="PES_ID")
@NamedQueries({ 
	@NamedQuery(name="PessoaFisicaEntity.selectAll", query="select obj from PessoaFisicaEntity obj "),
	@NamedQuery(name="PessoaFisicaEntity.selectCountBySexo", query="select count(obj) from PessoaFisicaEntity obj where obj.sexo like ? ")
	})
public class PessoaFisicaEntity extends PessoaFisica{

}
