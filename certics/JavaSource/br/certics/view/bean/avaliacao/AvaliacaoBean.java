package br.certics.view.bean.avaliacao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.certics.controller.facade.AvaliacaoFacade;
import br.certics.controller.facade.PessoaFisicaFacade;
import br.certics.controller.facade.ResultadoEsperadoFacade;
import br.certics.controller.facade.SoftwareFacade;
import br.certics.model.entity.AvaliacaoEntity;
import br.certics.model.entity.PerguntaRespostaEntity;
import br.certics.model.entity.PessoaFisicaEntity;
import br.certics.model.entity.ResultadoEsperadoEntity;
import br.certics.model.entity.SoftwareEntity;
import br.certics.view.bean.ApplicationContextBean;
import br.certics.view.bean.MessageUtils;
import br.finf.control.facade.FacadeProvider;

@ViewScoped
@ManagedBean(name="avaliacaoBean")
public class AvaliacaoBean {

	private AvaliacaoEntity avaliacao = new AvaliacaoEntity();
	private List<PerguntaRespostaEntity> lPerguntaResposta = new ArrayList<PerguntaRespostaEntity>();

	@ManagedProperty(value="#{applicationContextBean}")
	private ApplicationContextBean applicationContext;
	
	public List<PessoaFisicaEntity> getAllAvaliador(){
		PessoaFisicaFacade pessoaFisicaFacade = FacadeProvider.get().provide(PessoaFisicaFacade.class);
		return pessoaFisicaFacade.selectAll();
	}

	public List<SoftwareEntity> getAllSoftware(){
		SoftwareFacade softwareFacade = FacadeProvider.get().provide(SoftwareFacade.class);
		return softwareFacade.selectAll();
	}

	public void salvar() {
		AvaliacaoFacade facade = FacadeProvider.get().provide(AvaliacaoFacade.class);
		avaliacao.setPerguntaResposta(lPerguntaResposta);
		facade.salvar(avaliacao);
		MessageUtils.addInfoMessage("Avaliação salva com sucesso!");
		limpar();
	}

	@PostConstruct
	public void init() {
		limpar();
	}

	public void limpar() {
		avaliacao = new AvaliacaoEntity();
		criaListaPerguntaResposta();
	}

	public void criaListaPerguntaResposta(){
		ResultadoEsperadoFacade resultadoEsperadoFacade = FacadeProvider.get().provide(ResultadoEsperadoFacade.class);
		List<ResultadoEsperadoEntity> lResultadoEsperado = resultadoEsperadoFacade.selectAll();
		for (ResultadoEsperadoEntity resultadoEsperado : lResultadoEsperado) {
			PerguntaRespostaEntity perguntaResposta = new PerguntaRespostaEntity();
			perguntaResposta.setAvaliacao(avaliacao);
			perguntaResposta.setResultadoEsperado(resultadoEsperado);
			lPerguntaResposta.add(perguntaResposta);
		}
	}

	public ApplicationContextBean getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContextBean applicationContext) {
		this.applicationContext = applicationContext;
	}
	
}
