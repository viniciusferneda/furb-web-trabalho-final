package br.certics.model.be;

import java.util.List;

import javax.persistence.Query;

import br.certics.model.entity.PerguntaRespostaEntity;
import br.finf.dao.DBSession;
import br.finf.model.rule.BasicBE;

public class PerguntaRespostaBE extends BasicBE<PerguntaRespostaEntity> {

	public PerguntaRespostaBE(DBSession session) {
		super(session);
	}
	
	@SuppressWarnings("unchecked")
	public List<PerguntaRespostaEntity> selectAll() {
		Query q = loadQuery("selectAll");
		return q.getResultList();
	}

}
