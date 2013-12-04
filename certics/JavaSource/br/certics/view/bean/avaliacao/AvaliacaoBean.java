package br.certics.view.bean.avaliacao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.certics.controller.facade.AvaliacaoFacade;
import br.certics.controller.facade.PerguntaRespostaFacade;
import br.certics.controller.facade.PessoaFisicaFacade;
import br.certics.controller.facade.ResultadoEsperadoFacade;
import br.certics.controller.facade.SoftwareFacade;
import br.certics.model.entity.AvaliacaoEntity;
import br.certics.model.entity.PerguntaRespostaEntity;
import br.certics.model.entity.PessoaFisicaEntity;
import br.certics.model.entity.ResultadoEsperadoEntity;
import br.certics.model.entity.SoftwareEntity;
import br.certics.model.enums.EscalaPontuacaoAva;
import br.certics.model.enums.EscalaPontuacaoRE;
import br.certics.view.bean.ApplicationContextBean;
import br.certics.view.bean.MessageUtils;
import br.finf.control.facade.FacadeProvider;

@ViewScoped
@ManagedBean(name="avaliacaoBean")
public class AvaliacaoBean {

	private AvaliacaoEntity avaliacao = new AvaliacaoEntity();
	private SoftwareEntity software = new SoftwareEntity();
	private PessoaFisicaEntity avaliador = new PessoaFisicaEntity();
	
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
	
	public List<EscalaPontuacaoRE> getAllEscalaPontuacaoRE(){
		EscalaPontuacaoRE[] escalaPontuacaoREs = EscalaPontuacaoRE.values();
		List<EscalaPontuacaoRE> lEscalaPontuacaoRE = new ArrayList<EscalaPontuacaoRE>();
		for (int i = 0; i < escalaPontuacaoREs.length; i++) {
			lEscalaPontuacaoRE.add(escalaPontuacaoREs[i]);
		}
		return lEscalaPontuacaoRE;
	}

	public void salvar() {
		AvaliacaoFacade facade = FacadeProvider.get().provide(AvaliacaoFacade.class);
		avaliacao.setSoftware(software);
		avaliacao.setAvaliador(avaliador);
		avaliacao.setPerguntaResposta(lPerguntaResposta);
		avaliacao.setEscalaPontuacaoAva(EscalaPontuacaoAva.S);
		for (PerguntaRespostaEntity pergRes : lPerguntaResposta) {
			if(EscalaPontuacaoRE.N.equals(pergRes.getEscalaPontuacaoRE()) || EscalaPontuacaoRE.P.equals(pergRes.getEscalaPontuacaoRE())){
				avaliacao.setEscalaPontuacaoAva(EscalaPontuacaoAva.N);
				break;
			}
		}
		facade.salvar(avaliacao);
		salvarPerguntaResposta();
		MessageUtils.addInfoMessage("Avaliação salva com sucesso!");
		limpar();
	}

	private void salvarPerguntaResposta() {
		PerguntaRespostaFacade perguntaRespostaFacade = FacadeProvider.get().provide(PerguntaRespostaFacade.class);
		for (PerguntaRespostaEntity pergRes : lPerguntaResposta) {
			pergRes.setAvaliacao(avaliacao);
			perguntaRespostaFacade.salvar(pergRes);
		}
	}

	@PostConstruct
	public void init() {
		limpar();
	}

	public void limpar() {
		avaliacao = new AvaliacaoEntity();
		software = new SoftwareEntity();
		avaliador = new PessoaFisicaEntity();
		criaListaPerguntaResposta();
	}

	public void criaListaPerguntaResposta(){
		lPerguntaResposta = new ArrayList<PerguntaRespostaEntity>();
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

	public AvaliacaoEntity getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(AvaliacaoEntity avaliacao) {
		this.avaliacao = avaliacao;
	}

	public List<PerguntaRespostaEntity> getlPerguntaResposta() {
		return lPerguntaResposta;
	}

	public void setlPerguntaResposta(List<PerguntaRespostaEntity> lPerguntaResposta) {
		this.lPerguntaResposta = lPerguntaResposta;
	}

	public SoftwareEntity getSoftware() {
		return software;
	}

	public void setSoftware(SoftwareEntity software) {
		this.software = software;
	}

	public PessoaFisicaEntity getAvaliador() {
		return avaliador;
	}

	public void setAvaliador(PessoaFisicaEntity avaliador) {
		this.avaliador = avaliador;
	}
	
}
