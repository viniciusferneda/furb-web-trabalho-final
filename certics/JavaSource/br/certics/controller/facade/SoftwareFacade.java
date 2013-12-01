package br.certics.controller.facade;

import java.util.List;

import br.certics.model.be.SoftwareBE;
import br.certics.model.entity.SoftwareEntity;
import br.finf.control.facade.AbstractFacade;
import br.finf.dao.DBSession;
import br.finf.filter.QueryBuilder;

public class SoftwareFacade extends AbstractFacade {

	public SoftwareFacade(DBSession session) {
		super(session);
	}

	public List<SoftwareEntity> executeQuery(QueryBuilder query) {
		return getBE(SoftwareBE.class).executeQueryList(query);
	}

	public List<SoftwareEntity> selectAll() {
		return getBE(SoftwareBE.class).selectAll();
	}

	public void salvar(SoftwareEntity SoftwareEntity) {
		getBE(SoftwareBE.class).save(SoftwareEntity);
	}

}
