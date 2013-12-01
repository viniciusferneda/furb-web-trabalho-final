package br.certics.model.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="PERGUNTA_RESPOSTA")
@SequenceGenerator(name="PRE_ID", sequenceName="PRE_ID")
@NamedQueries({ 
	@NamedQuery(name="PerguntaRespostaEntity.selectAll", query="select obj from PerguntaRespostaEntity obj ")
	})
public class PerguntaRespostaEntity extends PerguntaResposta{

}
