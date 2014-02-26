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

import br.certics.model.enums.EscalaPontuacaoRE;
import br.finf.dao.entity.AbstractEntity;

@MappedSuperclass
@SuppressWarnings("serial")
public class PerguntaResposta extends AbstractEntity{

	@Id
	@GeneratedValue(generator="PRE_ID", strategy=GenerationType.AUTO)
	@Column(name="PRE_ID", nullable=false)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=AvaliacaoEntity.class)
	@JoinColumn(name="PRE_AVAID", nullable=false)
	private AvaliacaoEntity avaliacao;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=ResultadoEsperadoEntity.class)
	@JoinColumn(name="PRE_REPID", nullable=false)
	private ResultadoEsperadoEntity resultadoEsperado;
	
	@Column(name="PRE_EVI", nullable=false)
	private String evidencia;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PRE_EPRE", nullable=false)
	private EscalaPontuacaoRE escalaPontuacaoRE;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AvaliacaoEntity getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(AvaliacaoEntity avaliacao) {
		this.avaliacao = avaliacao;
	}

	public ResultadoEsperadoEntity getResultadoEsperado() {
		return resultadoEsperado;
	}

	public void setResultadoEsperado(ResultadoEsperadoEntity resultadoEsperado) {
		this.resultadoEsperado = resultadoEsperado;
	}

	public String getEvidencia() {
		return evidencia;
	}

	public void setEvidencia(String evidencia) {
		this.evidencia = evidencia;
	}

	public EscalaPontuacaoRE getEscalaPontuacaoRE() {
		return escalaPontuacaoRE;
	}

	public void setEscalaPontuacaoRE(EscalaPontuacaoRE escalaPontuacaoRE) {
		this.escalaPontuacaoRE = escalaPontuacaoRE;
	}

}
