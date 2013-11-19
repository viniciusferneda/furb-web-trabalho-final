package br.xereta.view.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.xereta.model.entity.UsuarioEntity;

@SessionScoped
@ManagedBean(name = "applicationContextBean")
public class ApplicationContextBean {

	private UsuarioEntity usuarioLogado = null;

	public UsuarioEntity getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(UsuarioEntity usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

}
