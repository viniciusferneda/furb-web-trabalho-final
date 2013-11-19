package br.xereta.model.be;

import java.util.List;

import br.finf.dao.DBSession;
import br.finf.model.rule.BasicBE;
import br.xereta.model.entity.ComputadorEntity;

public class ComputadorBE extends BasicBE<ComputadorEntity> {

	public ComputadorBE(DBSession session) {
		super(session);
	}

	public List<ComputadorEntity> getAllByUsuarioId(Long usuario) {
		return executeNamedQueryList("getAllComputadorByUsuarioId", new Object[]{usuario});
	}
	
	public ComputadorEntity getComputadorByMac(String mac) {
		return executeNamedQuery("getComputadorByMac", new Object[]{ mac });
	}

	public void habilitarNotificacao(Long computador) {
		alterarNotificacao(computador, true);
	}
	
	public void desabilitarNotificacao(Long computador) {
		alterarNotificacao(computador, false);
	}

	private void alterarNotificacao(Long computador, boolean valor) {
		ComputadorEntity pc = executeNamedQuery("getComputadorById", new Object[]{ computador });
		pc.setNofificarPorEmail(valor);
		
		save(pc);
	}
	
}
