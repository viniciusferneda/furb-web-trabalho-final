package br.certics.model.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="AREA_COMPETENCIA")
@SequenceGenerator(name="ACO_ID", sequenceName="ACO_ID")
@NamedQueries({ 
	@NamedQuery(name="AreaCompetenciaEntity.selectAll", query="select obj from AreaCompetenciaEntity obj "),
	@NamedQuery(name="AreaCompetenciaEntity.selectByID", query="select obj from AreaCompetenciaEntity obj where obj.id"),
	@NamedQuery(name="AreaCompetenciaEntity.selectCountByPerguntaChave", 
				query="select count(obj) from AreaCompetenciaEntity obj " +
						"where obj.perguntaChave like ? ")
	})
public class AreaCompetenciaEntity extends AreaCompetencia{
	
}