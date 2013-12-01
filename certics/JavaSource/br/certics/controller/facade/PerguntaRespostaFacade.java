package br.certics.controller.facade;

import java.util.List;

import br.certics.model.be.PerguntaRespostaBE;
import br.certics.model.entity.PerguntaRespostaEntity;
import br.finf.control.facade.AbstractFacade;
import br.finf.dao.DBSession;
import br.finf.filter.QueryBuilder;

public class PerguntaRespostaFacade extends AbstractFacade {

	public PerguntaRespostaFacade(DBSession session) {
		super(session);
	}

	public List<PerguntaRespostaEntity> executeQuery(QueryBuilder query) {
		return getBE(PerguntaRespostaBE.class).executeQueryList(query);
	}

	public List<PerguntaRespostaEntity> selectAll() {
		return getBE(PerguntaRespostaBE.class).selectAll();
	}

	public void salvar(PerguntaRespostaEntity PerguntaRespostaEntity) {
		getBE(PerguntaRespostaBE.class).save(PerguntaRespostaEntity);
	}

}
