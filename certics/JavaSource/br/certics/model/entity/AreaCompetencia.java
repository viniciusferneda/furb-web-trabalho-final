package br.certics.model.entity;

import java.util.List;

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
public class AreaCompetencia extends AbstractEntity{

	@Id
	@GeneratedValue(generator = "ACO_ID", strategy = GenerationType.IDENTITY)
	@Column(name="ACO_ID", nullable=false)
	private Long id;
	
	@Column(name="ACO_PERG_CHAVE", nullable=false)
	private String perguntaChave;
	
	@Column(name="ACO_DESC", nullable=false)
	private String descricao;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="areaCompetencia", targetEntity = ResultadoEsperadoEntity.class)
	private List<ResultadoEsperadoEntity> resultadoEsperado;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPerguntaChave() {
		return perguntaChave;
	}

	public void setPerguntaChave(String perguntaChave) {
		this.perguntaChave = perguntaChave;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<ResultadoEsperadoEntity> getResultadoEsperado() {
		return resultadoEsperado;
	}

	public void setResultadoEsperado(List<ResultadoEsperadoEntity> resultadoEsperado) {
		this.resultadoEsperado = resultadoEsperado;
	}
	
}
