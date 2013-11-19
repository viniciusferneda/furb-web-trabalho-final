package br.xereta.view.bean.localizacao;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import br.finf.control.facade.FacadeProvider;
import br.xereta.controller.facade.ComputadorFacade;
import br.xereta.controller.facade.LocalizacaoFacade;
import br.xereta.model.entity.ComputadorEntity;
import br.xereta.model.entity.LocalizacaoEntity;

@RequestScoped
@ManagedBean(name = "localizacaoBean")
public class LocalizacaoBean {

	@ManagedProperty("#{param.mac}")
	private String mac;

	@ManagedProperty("#{param.latitude}")
	private String latitude;

	@ManagedProperty("#{param.longitude}")
	private String longitude;

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String executar() {
		if (mac == null) {
			return "MAC " + mac + "inváido";
		}
		
		if (latitude == null || isNegativo(latitude)) {
			return "Latitude " + mac + "inváido";
		}
		
		if (longitude == null || isNegativo(longitude)) {
			return "Longitude " + mac + "inváido";
		}
		
		ComputadorFacade facade = FacadeProvider.get().provide(ComputadorFacade.class);
		ComputadorEntity cmp = facade.getComputadorByMac(mac);
		if (cmp == null) {
			return "Não foi encontrado computador com MAC: " + mac;
		}
		
		LocalizacaoEntity loc = new LocalizacaoEntity();
		loc.setComputador(cmp);
		loc.setLatitude(Double.parseDouble(latitude));
		loc.setLongitude(Double.parseDouble(longitude));
		loc.setHorario(new Date());
		
		
		LocalizacaoFacade locFacade = FacadeProvider.get().provide(LocalizacaoFacade.class);
		try {
			locFacade.salvar(loc);
		} catch (Exception e) {
			return "Não conseguiu salvar a localização.";
		}
		
		return "OKAY";
	}

	private boolean isNegativo(String valor) {
		try {
			double val = Double.parseDouble(valor);
			return val < 0.0;
		} catch (Exception e) {
			return true;
		}
	}

}
