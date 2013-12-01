package br.certics.model.be;

import java.util.List;

import javax.persistence.Query;

import br.certics.model.entity.AreaCompetenciaEntity;
import br.finf.dao.DBSession;
import br.finf.model.rule.BasicBE;

public class AreaCompetenciaBE extends BasicBE<AreaCompetenciaEntity> {

	public AreaCompetenciaBE(DBSession session) {
		super(session);
	}
	
	@SuppressWarnings("unchecked")
	public List<AreaCompetenciaEntity> selectAll() {
		Query q = loadQuery("selectAll");
		return q.getResultList();
	}

}
