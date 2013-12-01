package br.certics.model.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="ORGANIZACAO_SOLICITANTE")
@SequenceGenerator(name="OSO_ID", sequenceName="OSO_ID")
@NamedQueries({ 
	@NamedQuery(name="OrganizacaoSolicitanteEntity.selectAll", query="select obj from OrganizacaoSolicitanteEntity obj ")
	})
public class OrganizacaoSolicitanteEntity extends OrganizacaoSolicitante{

}
