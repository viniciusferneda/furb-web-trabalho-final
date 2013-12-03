package br.certics.controller.facade;

import java.util.List;

import br.certics.model.be.AreaCompetenciaBE;
import br.certics.model.entity.AreaCompetenciaEntity;
import br.finf.control.facade.AbstractFacade;
import br.finf.dao.DBSession;
import br.finf.filter.QueryBuilder;

public class AreaCompetenciaFacade extends AbstractFacade {

	public AreaCompetenciaFacade(DBSession session) {
		super(session);
	}

	public List<AreaCompetenciaEntity> executeQuery(QueryBuilder query) {
		return getBE(AreaCompetenciaBE.class).executeQueryList(query);
	}

	public List<AreaCompetenciaEntity> selectAll() {
		return getBE(AreaCompetenciaBE.class).selectAll();
	}

	public AreaCompetenciaEntity selectByID(Long id) {
		return getBE(AreaCompetenciaBE.class).selectByID(id);
	}
	
	public void salvar(AreaCompetenciaEntity areaCompetenciaEntity) {
		getBE(AreaCompetenciaBE.class).save(areaCompetenciaEntity);
	}

}
