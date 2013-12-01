package br.certics.model.entity;

import java.util.Set;

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
public class Avaliacao extends AbstractEntity{

	@Id
	@GeneratedValue(generator = "AVA_ID", strategy = GenerationType.IDENTITY)
	@Column(name="AVA_ID", nullable=false)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=PessoaFisicaEntity.class)
	@JoinColumn(name="AVA_PESID", nullable=false)
	private PessoaFisicaEntity avaliador;
	
	@ManyToOne(fetch=FetchType.LAZY, targetEntity=SoftwareEntity.class)
	@JoinColumn(name="AVA_SOFID", nullable=false)
	private SoftwareEntity software;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="avaliacao", targetEntity=PerguntaRespostaEntity.class)
	private Set<PerguntaRespostaEntity> perguntaResposta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PessoaFisicaEntity getAvaliador() {
		return avaliador;
	}

	public void setAvaliador(PessoaFisicaEntity avaliador) {
		this.avaliador = avaliador;
	}

	public SoftwareEntity getSoftware() {
		return software;
	}

	public void setSoftware(SoftwareEntity software) {
		this.software = software;
	}

	public Set<PerguntaRespostaEntity> getPerguntaResposta() {
		return perguntaResposta;
	}

	public void setPerguntaResposta(Set<PerguntaRespostaEntity> perguntaResposta) {
		this.perguntaResposta = perguntaResposta;
	}
	
}
