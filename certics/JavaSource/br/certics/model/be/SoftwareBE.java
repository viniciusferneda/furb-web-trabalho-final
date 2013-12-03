package br.certics.model.be;

import java.util.List;

import javax.persistence.Query;

import br.certics.model.entity.SoftwareEntity;
import br.finf.dao.DBSession;
import br.finf.model.rule.BasicBE;

public class SoftwareBE extends BasicBE<SoftwareEntity> {

	public SoftwareBE(DBSession session) {
		super(session);
	}
	
	@SuppressWarnings("unchecked")
	public List<SoftwareEntity> selectAll() {
		Query q = loadQuery("selectAll");
		return q.getResultList();
	}
	
	public SoftwareEntity selectByID(Long id) {
		return executeNamedQuery("selectByID", id);
	}

}
