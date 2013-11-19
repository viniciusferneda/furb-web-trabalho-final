package br.xereta.model.be;

import java.util.List;

import org.hibernate.Query;

import br.finf.dao.DBSession;
import br.finf.model.rule.BasicBE;
import br.xereta.model.entity.UsuarioEntity;

public class UsuarioBE extends BasicBE<UsuarioEntity> {

	public UsuarioBE(DBSession session) {
		super(session);
	}
	
	@SuppressWarnings("rawtypes")
	public boolean isEmailUsado(String mail) {
		Query q = loadQuery("selectCountByMail");
		
		putParams(q, new Object[]{ mail });
		
		List list = q.list();
		if (list != null && !list.isEmpty()){
			Number count = (Number) list.get(0);
			return count.longValue() > 0;
		}
		return false;
	}

	public UsuarioEntity usuarioByEmailESenha(String email, String senha) {
		return executeNamedQuery("selectUsuarioByEmailESenha", new Object[]{ email, senha });
	}

}
