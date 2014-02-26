package br.certics.model.entity;

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

import br.finf.dao.entity.AbstractEntity;

@MappedSuperclass
@SuppressWarnings("serial")
public class ResultadoEsperado extends AbstractEntity{

	@Id
	@GeneratedValue(generator = "REP_ID", strategy = GenerationType.AUTO)
	@Column(name="REP_ID", nullable=false)
	private Long id;
	
	@Column(name="REP_TIT", nullable=false)
	private String titulo;
	
	@Column(name="REP_DESC", nullable=false)
	private String descricao;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=AreaCompetenciaEntity.class)
	@JoinColumn(name="REP_ACOID", nullable=false)
	private AreaCompetenciaEntity areaCompetencia;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="resultadoEsperado", targetEntity=PerguntaRespostaEntity.class)
	private List<PerguntaRespostaEntity> perguntaResposta;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public AreaCompetencia getAreaCompetencia() {
		return areaCompetencia;
	}

	public void setAreaCompetencia(AreaCompetenciaEntity areaCompetencia) {
		this.areaCompetencia = areaCompetencia;
	}

	public List<PerguntaRespostaEntity> getPerguntaResposta() {
		return perguntaResposta;
	}

	public void setPerguntaResposta(List<PerguntaRespostaEntity> perguntaResposta) {
		this.perguntaResposta = perguntaResposta;
	}
	
}
