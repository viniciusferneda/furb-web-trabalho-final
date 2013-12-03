package br.certics.ws;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.certics.controller.facade.AvaliacaoFacade;
import br.certics.model.entity.AvaliacaoEntity;
import br.finf.control.facade.FacadeProvider;

@WebService(name="WSConsultas", targetNamespace="http://localhost:8080/WSConsultas")
public class WSConsultas {
	
	@WebMethod(action="http://localhost:8080/WSConsultas/getAvaliacoes")
	@WebResult(name="getAvaliacoesResponse", partName="getAvaliacoesResponse")
	public String[] getAvaliacoes() {
		List<String> ofertas = new ArrayList<String>();
		List<AvaliacaoEntity> lAvaliacoes = FacadeProvider.get().provide(AvaliacaoFacade.class).selectAll();
		for (AvaliacaoEntity ava : lAvaliacoes) {
			ofertas.add(ava.getAvaliador().getNome()+" - "+ava.getSoftware().getNome()+" - "+ava.getEscalaPontuacaoAva().getDescricao());
		}
		return ofertas.toArray(new String[]{});
	}

}
