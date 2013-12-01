package br.certics.model.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import br.finf.dao.entity.AbstractEntity;

@MappedSuperclass
@SuppressWarnings("serial")
public class OrganizacaoSolicitante extends AbstractEntity{

	@Id
	@GeneratedValue(generator = "OSO_ID", strategy = GenerationType.IDENTITY)
	@Column(name="OSO_ID", nullable=false)
	private Long id;
	
	@Column(name="OSO_NOME", nullable=false)
	private String nome;
	
	@Column(name="OSO_CPF", nullable=false)
	private String cpf;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="organizacaoSolicitante", targetEntity=SoftwareEntity.class)
	private Set<SoftwareEntity> software;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Set<SoftwareEntity> getSoftware() {
		return software;
	}

	public void setSoftware(Set<SoftwareEntity> software) {
		this.software = software;
	}
	
}
