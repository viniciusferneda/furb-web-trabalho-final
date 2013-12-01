package br.certics.controller.facade;

import java.util.List;

import br.certics.model.be.ResultadoEsperadoBE;
import br.certics.model.entity.ResultadoEsperadoEntity;
import br.finf.control.facade.AbstractFacade;
import br.finf.dao.DBSession;
import br.finf.filter.QueryBuilder;

public class ResultadoEsperadoFacade extends AbstractFacade {

	public ResultadoEsperadoFacade(DBSession session) {
		super(session);
	}

	public List<ResultadoEsperadoEntity> executeQuery(QueryBuilder query) {
		return getBE(ResultadoEsperadoBE.class).executeQueryList(query);
	}

	public List<ResultadoEsperadoEntity> selectAll() {
		return getBE(ResultadoEsperadoBE.class).selectAll();
	}

	public void salvar(ResultadoEsperadoEntity ResultadoEsperadoEntity) {
		getBE(ResultadoEsperadoBE.class).save(ResultadoEsperadoEntity);
	}

}
