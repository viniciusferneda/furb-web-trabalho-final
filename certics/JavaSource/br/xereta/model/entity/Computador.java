package br.xereta.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import br.certics.model.entity.UsuarioEntity;
import br.finf.dao.entity.AbstractEntity;

@MappedSuperclass
@SuppressWarnings("serial")
public abstract class Computador extends AbstractEntity {

	@Id
	@GeneratedValue(generator = "CMP_ID", strategy = GenerationType.IDENTITY)
	@Column(name = "CMP_ID", nullable = false)
	private Long id;

	@Column(name = "CMP_DESCRICAO", nullable = false)
	private String descricao;

	@Column(name = "CMP_MAC", nullable = false)
	private String mac;

	@Column(name = "CMP_NOTIFICAR_MAIL", nullable = false)
	private Boolean nofificarPorEmail = false;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UsuarioEntity.class)
	@JoinColumn(name = "CMP_USUARIO", nullable = false)
	private UsuarioEntity usuario;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "computador", targetEntity = LocalizacaoEntity.class)
	private List<LocalizacaoEntity> localizacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Boolean getNofificarPorEmail() {
		return nofificarPorEmail;
	}
	
	public String getNofificarPorEmailText() {
		return nofificarPorEmail ? "Sim" : "Não";
	}

	public void setNofificarPorEmail(Boolean nofificarPorEmail) {
		this.nofificarPorEmail = nofificarPorEmail;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public List<LocalizacaoEntity> getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(List<LocalizacaoEntity> localizacao) {
		this.localizacao = localizacao;
	}

}
