package br.xereta.controller.facade;

import java.util.List;

import br.finf.control.facade.AbstractFacade;
import br.finf.dao.DBSession;
import br.finf.filter.QueryBuilder;
import br.xereta.model.be.LocalizacaoBE;
import br.xereta.model.entity.LocalizacaoEntity;

public class LocalizacaoFacade extends AbstractFacade {

	public LocalizacaoFacade(DBSession session) {
		super(session);
	}

	public List<LocalizacaoEntity> executeQuery(QueryBuilder query) {
		return getBE(LocalizacaoBE.class).executeQueryList(query);
	}
	
	public void salvar(LocalizacaoEntity entity) {
		getBE(LocalizacaoBE.class).save(entity);
	}

}
