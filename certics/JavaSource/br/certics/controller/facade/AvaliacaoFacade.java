package br.certics.controller.facade;

import java.util.List;

import br.certics.model.be.AvaliacaoBE;
import br.certics.model.entity.AvaliacaoEntity;
import br.finf.control.facade.AbstractFacade;
import br.finf.dao.DBSession;
import br.finf.filter.QueryBuilder;

public class AvaliacaoFacade extends AbstractFacade {

	public AvaliacaoFacade(DBSession session) {
		super(session);
	}

	public List<AvaliacaoEntity> executeQuery(QueryBuilder query) {
		return getBE(AvaliacaoBE.class).executeQueryList(query);
	}

	public List<AvaliacaoEntity> selectAll() {
		return getBE(AvaliacaoBE.class).selectAll();
	}

	public void salvar(AvaliacaoEntity AvaliacaoEntity) {
		getBE(AvaliacaoBE.class).save(AvaliacaoEntity);
	}

}
