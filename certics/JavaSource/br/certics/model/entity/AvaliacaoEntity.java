package br.certics.model.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="AVALIACAO")
@SequenceGenerator(name="AVA_ID", sequenceName="AVA_ID")
@NamedQueries({ 
	@NamedQuery(name="AvaliacaoEntity.selectAll", 
			query="select obj from AvaliacaoEntity obj " +
					"inner join fetch obj.avaliador ava " +
					"inner join fetch obj.software sof " +
					"inner join fetch sof.organizacaoSolicitante oso " +
					"order by obj.escalaPontuacaoAva")
	})
public class AvaliacaoEntity extends Avaliacao{

}
