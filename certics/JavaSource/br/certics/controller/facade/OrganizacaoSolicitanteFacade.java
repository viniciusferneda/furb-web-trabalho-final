package br.certics.controller.facade;

import java.util.List;

import br.certics.model.be.OrganizacaoSolicitanteBE;
import br.certics.model.entity.OrganizacaoSolicitanteEntity;
import br.finf.control.facade.AbstractFacade;
import br.finf.dao.DBSession;
import br.finf.filter.QueryBuilder;

public class OrganizacaoSolicitanteFacade extends AbstractFacade {

	public OrganizacaoSolicitanteFacade(DBSession session) {
		super(session);
	}

	public List<OrganizacaoSolicitanteEntity> executeQuery(QueryBuilder query) {
		return getBE(OrganizacaoSolicitanteBE.class).executeQueryList(query);
	}

	public List<OrganizacaoSolicitanteEntity> selectAll() {
		return getBE(OrganizacaoSolicitanteBE.class).selectAll();
	}

	public void salvar(OrganizacaoSolicitanteEntity OrganizacaoSolicitanteEntity) {
		getBE(OrganizacaoSolicitanteBE.class).save(OrganizacaoSolicitanteEntity);
	}

}
