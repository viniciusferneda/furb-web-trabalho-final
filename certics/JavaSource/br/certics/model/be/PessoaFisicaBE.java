package br.certics.model.be;

import java.util.List;

import javax.persistence.Query;

import br.certics.model.entity.PessoaFisicaEntity;
import br.finf.dao.DBSession;
import br.finf.model.rule.BasicBE;

public class PessoaFisicaBE extends BasicBE<PessoaFisicaEntity> {

	public PessoaFisicaBE(DBSession session) {
		super(session);
	}
	
	@SuppressWarnings("rawtypes")
	public boolean isCPFUsado(String cpf) {
		Query q = loadQuery("selectCountByCPF");
		
		putParams(q, new Object[]{ cpf });
		
		List list = q.getResultList();
		if (list != null && !list.isEmpty()){
			Number count = (Number) list.get(0);
			return count.longValue() > 0;
		}
		return false;
	}

}
