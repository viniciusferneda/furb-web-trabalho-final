package br.certics.model.be;

import java.util.List;

import javax.persistence.Query;

import br.certics.model.entity.ResultadoEsperadoEntity;
import br.finf.dao.DBSession;
import br.finf.model.rule.BasicBE;

public class ResultadoEsperadoBE extends BasicBE<ResultadoEsperadoEntity> {

	public ResultadoEsperadoBE(DBSession session) {
		super(session);
	}
	
	@SuppressWarnings("unchecked")
	public List<ResultadoEsperadoEntity> selectAll() {
		Query q = loadQuery("selectAll");
		return q.getResultList();
	}

}
