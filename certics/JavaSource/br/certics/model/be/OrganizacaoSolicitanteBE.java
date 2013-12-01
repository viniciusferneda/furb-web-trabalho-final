package br.certics.model.be;

import java.util.List;

import javax.persistence.Query;

import br.certics.model.entity.OrganizacaoSolicitanteEntity;
import br.finf.dao.DBSession;
import br.finf.model.rule.BasicBE;

public class OrganizacaoSolicitanteBE extends BasicBE<OrganizacaoSolicitanteEntity> {

	public OrganizacaoSolicitanteBE(DBSession session) {
		super(session);
	}
	
	@SuppressWarnings("unchecked")
	public List<OrganizacaoSolicitanteEntity> selectAll() {
		Query q = loadQuery("selectAll");
		return q.getResultList();
	}

}
