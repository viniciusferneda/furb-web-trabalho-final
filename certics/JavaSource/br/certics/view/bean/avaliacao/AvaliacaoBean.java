package br.certics.view.bean.avaliacao;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.certics.controller.facade.AvaliacaoFacade;
import br.certics.model.entity.AvaliacaoEntity;
import br.certics.model.entity.PerguntaRespostaEntity;
import br.certics.model.entity.PessoaFisicaEntity;
import br.certics.model.entity.SoftwareEntity;
import br.certics.model.enums.EscalaPontuacaoAva;
import br.certics.view.bean.ApplicationContextBean;
import br.certics.view.bean.MessageUtils;
import br.finf.control.facade.FacadeProvider;

@ViewScoped
@ManagedBean(name="avaliacaoBean")
public class AvaliacaoBean {

	private PessoaFisicaEntity avaliador;
	private SoftwareEntity software;
	private EscalaPontuacaoAva escalaPontuacaoAva;
	private List<PerguntaRespostaEntity> lPerguntaResposta;

	@ManagedProperty(value="#{applicationContextBean}")
	private ApplicationContextBean applicationContext;
	
	public void salvar() {
		AvaliacaoEntity avaliacao = new AvaliacaoEntity();
		avaliacao.setAvaliador(avaliador);
		avaliacao.setSoftware(software);
		avaliacao.setEscalaPontuacaoAva(escalaPontuacaoAva);
		avaliacao.setPerguntaResposta(lPerguntaResposta);
		
		AvaliacaoFacade facade = FacadeProvider.get().provide(AvaliacaoFacade.class);
		facade.salvar(avaliacao);
		
		MessageUtils.addInfoMessage("Avaliação salva com sucesso!");
		limpar();
	}
	
	public void limpar() {
		avaliador = null;
		software = null;
		escalaPontuacaoAva = null;
		lPerguntaResposta = null;
	}

	public ApplicationContextBean getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContextBean applicationContext) {
		this.applicationContext = applicationContext;
	}

	public PessoaFisicaEntity getAvaliador() {
		return avaliador;
	}

	public void setAvaliador(PessoaFisicaEntity avaliador) {
		this.avaliador = avaliador;
	}

	public SoftwareEntity getSoftware() {
		return software;
	}

	public void setSoftware(SoftwareEntity software) {
		this.software = software;
	}

	public EscalaPontuacaoAva getEscalaPontuacaoAva() {
		return escalaPontuacaoAva;
	}

	public void setEscalaPontuacaoAva(EscalaPontuacaoAva escalaPontuacaoAva) {
		this.escalaPontuacaoAva = escalaPontuacaoAva;
	}

	public List<PerguntaRespostaEntity> getlPerguntaResposta() {
		return lPerguntaResposta;
	}

	public void setlPerguntaResposta(List<PerguntaRespostaEntity> lPerguntaResposta) {
		this.lPerguntaResposta = lPerguntaResposta;
	}
	
}
