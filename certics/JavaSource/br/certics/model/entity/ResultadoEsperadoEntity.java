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
	@NamedQuery(name="ResultadoEsperadoEntity.selectAll", query="select obj from ResultadoEsperadoEntity obj "),
	@NamedQuery(name="ResultadoEsperadoEntity.selectCountByTitulo", query="select count(obj) from ResultadoEsperadoEntity obj where obj.titulo like ? ")
	})
public class ResultadoEsperadoEntity extends ResultadoEsperado{

}
