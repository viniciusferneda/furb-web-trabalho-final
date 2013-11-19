package br.xereta.controller.facade;

import java.util.List;

import br.finf.control.facade.AbstractFacade;
import br.finf.dao.DBSession;
import br.finf.filter.QueryBuilder;
import br.xereta.model.be.UsuarioBE;
import br.xereta.model.entity.UsuarioEntity;

public class UsuarioFacade extends AbstractFacade {

	public UsuarioFacade(DBSession session) {
		super(session);
	}

	public List<UsuarioEntity> executeQuery(QueryBuilder query) {
		return getBE(UsuarioBE.class).executeQueryList(query);
	}

	public boolean isEmailUsado(String mail) {
		return getBE(UsuarioBE.class).isEmailUsado(mail);
	}

	public void salvar(UsuarioEntity usuario) {
		getBE(UsuarioBE.class).save(usuario);
	}

	public UsuarioEntity usuarioByEmailESenha(String email, String senha) {
		return getBE(UsuarioBE.class).usuarioByEmailESenha(email, senha);
	}

}
