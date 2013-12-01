package br.certics.model.entity;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.certics.model.enums.PerfilUsuario;
import br.finf.dao.entity.AbstractEntity;

@MappedSuperclass
@SuppressWarnings("serial")
public class Usuario extends AbstractEntity {

	@Id
	@GeneratedValue(generator = "USR_ID", strategy = GenerationType.IDENTITY)
	@Column(name = "USR_ID", nullable=false)
	private Long id;

	@Column(name = "USR_EMAIL", nullable=false)
	private String email;

	@Column(name = "USR_SENHA", nullable=false)
	private String senha;

	@Enumerated(EnumType.STRING)
	@Column(name = "USR_PERFIL", nullable=false)
	private PerfilUsuario perfilUsuario;

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=PessoaFisicaEntity.class)
	@JoinColumn(name="USR_PESID", nullable=false)
	private PessoaFisicaEntity pessoaFisica;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public PerfilUsuario getPerfilUsuario() {
		return perfilUsuario;
	}

	public void setPerfilUsuario(PerfilUsuario perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}

	public PessoaFisicaEntity getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisicaEntity pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}
	
}
