package br.xereta.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import br.finf.dao.entity.AbstractEntity;
import br.xereta.model.enums.SexoEnum;

@MappedSuperclass
@SuppressWarnings("serial")
public class Usuario extends AbstractEntity {

	@Id
	@GeneratedValue(generator = "USR_ID", strategy = GenerationType.IDENTITY)
	@Column(name = "USR_ID", nullable = false)
	private Long id;

	@Column(name = "USR_NOME", nullable = false)
	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(name = "USR_SEXO", nullable = false)
	private SexoEnum sexo;

	@Column(name = "USR_MAIL", nullable = false)
	private String mail;

	@Column(name = "USR_SENHA", nullable = false)
	private String senha;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", targetEntity = ComputadorEntity.class)
	private List<ComputadorEntity> computadores;

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

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<ComputadorEntity> getComputadores() {
		return computadores;
	}

	public void setComputadores(List<ComputadorEntity> computadores) {
		this.computadores = computadores;
	}

}
