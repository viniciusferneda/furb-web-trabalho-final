package br.certics.view.bean.avaliacao;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.certics.controller.facade.AvaliacaoFacade;
import br.certics.controller.facade.PessoaFisicaFacade;
import br.certics.controller.facade.SoftwareFacade;
import br.certics.model.entity.AvaliacaoEntity;
import br.certics.model.entity.PessoaFisicaEntity;
import br.certics.model.entity.SoftwareEntity;
import br.certics.view.bean.ApplicationContextBean;
import br.certics.view.bean.MessageUtils;
import br.finf.control.facade.FacadeProvider;

@ViewScoped
@ManagedBean(name="avaliacaoBean")
public class AvaliacaoBean {

	private AvaliacaoEntity avaliacao = new AvaliacaoEntity();

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
		facade.salvar(avaliacao);
		MessageUtils.addInfoMessage("Avaliação salva com sucesso!");
		limpar();
	}
	
	public void limpar() {
		avaliacao = new AvaliacaoEntity();
	}

	public ApplicationContextBean getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContextBean applicationContext) {
		this.applicationContext = applicationContext;
	}
	
}
