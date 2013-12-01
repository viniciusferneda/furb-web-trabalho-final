package br.certics.model.be;

import java.util.List;

import javax.persistence.Query;

import br.certics.model.entity.AvaliacaoEntity;
import br.finf.dao.DBSession;
import br.finf.model.rule.BasicBE;

public class AvaliacaoBE extends BasicBE<AvaliacaoEntity> {

	public AvaliacaoBE(DBSession session) {
		super(session);
	}
	
	@SuppressWarnings("unchecked")
	public List<AvaliacaoEntity> selectAll() {
		Query q = loadQuery("selectAll");
		return q.getResultList();
	}

}
