package br.xereta.view.bean.computador;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.certics.view.bean.ApplicationContextBean;
import br.certics.view.bean.MessageUtils;
import br.finf.control.facade.FacadeProvider;
import br.xereta.controller.facade.ComputadorFacade;
import br.xereta.model.entity.ComputadorEntity;

@ViewScoped
@ManagedBean(name = "cadastroComputadorBean")
public class CadastroComputadorBean {

	private String mac;
	private String descricao;
	private Boolean nofificarPorEmail;

	@ManagedProperty(value = "#{applicationContextBean}")
	private ApplicationContextBean applicationContext;

	@PostConstruct
	public void init() {
		nofificarPorEmail = false;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getNofificarPorEmail() {
		return nofificarPorEmail;
	}

	public void setNofificarPorEmail(Boolean nofificarPorEmail) {
		this.nofificarPorEmail = nofificarPorEmail;
	}

	public ApplicationContextBean getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContextBean applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	
	public void salvar() {
		ComputadorEntity computador = new ComputadorEntity();
		computador.setMac(mac);
		computador.setDescricao(descricao);
		computador.setNofificarPorEmail(nofificarPorEmail);
		computador.setUsuario(applicationContext.getUsuarioLogado());
		
		ComputadorFacade facade = FacadeProvider.get().provide(ComputadorFacade.class);
		facade.salvar(computador);
		
		MessageUtils.addInfoMessage("Novo computador salvo com sucesso!");
		limpar();
	}
	
	public void limpar() {
		mac = null;
		descricao = null;
		nofificarPorEmail = null;
	}
	
	
}
