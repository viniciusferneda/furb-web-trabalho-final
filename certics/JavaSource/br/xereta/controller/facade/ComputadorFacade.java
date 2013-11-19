package br.xereta.controller.facade;

import java.util.List;

import br.finf.control.facade.AbstractFacade;
import br.finf.dao.DBSession;
import br.finf.filter.QueryBuilder;
import br.xereta.model.be.ComputadorBE;
import br.xereta.model.entity.ComputadorEntity;

public class ComputadorFacade extends AbstractFacade {

	public ComputadorFacade(DBSession session) {
		super(session);
	}

	public List<ComputadorEntity> executeQuery(QueryBuilder query) {
		return getBE(ComputadorBE.class).executeQueryList(query);
	}

	public void salvar(ComputadorEntity usuario) {
		getBE(ComputadorBE.class).save(usuario);
	}

	public List<ComputadorEntity> getAllComputadorByUsuarioId(Long usuario) {
		return getBE(ComputadorBE.class).getAllByUsuarioId(usuario);
	}
	
	public ComputadorEntity getComputadorByMac(String mac) {
		return getBE(ComputadorBE.class).getComputadorByMac(mac);
	}

	public void habilitarNotificacao(Long computador) {
		getBE(ComputadorBE.class).habilitarNotificacao(computador);
	}
	
	public void desabilitarNotificacao(Long computador) {
		getBE(ComputadorBE.class).desabilitarNotificacao(computador);
	}

}
