package br.certics.model.be;

import java.util.List;

import javax.persistence.Query;

import br.certics.model.entity.UsuarioEntity;
import br.finf.dao.DBSession;
import br.finf.model.rule.BasicBE;

public class UsuarioBE extends BasicBE<UsuarioEntity> {

	public UsuarioBE(DBSession session) {
		super(session);
	}
	
	@SuppressWarnings("rawtypes")
	public boolean isEmailUsado(String email) {
		Query q = loadQuery("selectCountByEmail");
		
		putParams(q, new Object[]{ email });
		
		List list = q.getResultList();
		if (list != null && !list.isEmpty()){
			Number count = (Number) list.get(0);
			return count.longValue() > 0;
		}
		return false;
	}

	public UsuarioEntity usuarioByEmailESenha(String email, String senha) {
		return executeNamedQuery("selectUsuarioByEmailESenha", email, senha);
	}

}
