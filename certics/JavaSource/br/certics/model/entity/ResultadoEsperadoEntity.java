package br.certics.model.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="RESULTADO_ESPERADO")
@SequenceGenerator(name="REP_ID", sequenceName="REP_ID")
@NamedQueries({ 
	@NamedQuery(name="ResultadoEsperadoEntity.selectAll", 
			query="select obj from ResultadoEsperadoEntity obj " +
					" inner join fetch obj.areaCompetencia aco " +
					"order by obj.areaCompetencia.titulo")
	})
public class ResultadoEsperadoEntity extends ResultadoEsperado{

	@Override
	public void setAreaCompetencia(AreaCompetenciaEntity areaCompetencia) {
		super.setAreaCompetencia(areaCompetencia);
	}
}
