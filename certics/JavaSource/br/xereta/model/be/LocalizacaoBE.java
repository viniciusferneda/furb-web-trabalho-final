package br.xereta.model.be;

import br.finf.dao.DBSession;
import br.finf.model.rule.BasicBE;
import br.xereta.model.entity.LocalizacaoEntity;

public class LocalizacaoBE extends BasicBE<LocalizacaoEntity> {

	public LocalizacaoBE(DBSession session) {
		super(session);
	}


}
