package br.certics.view.bean;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class MessageUtils {

	public static void addFatalMessage(String msg) {
		addMessage(FacesMessage.SEVERITY_FATAL, msg);
	}

	public static void addFatalMessage(String msg, String detail) {
		addMessage(FacesMessage.SEVERITY_FATAL, msg, detail);
	}

	public static void addWarningMessage(String msg) {
		addMessage(FacesMessage.SEVERITY_WARN, msg);
	}

	public static void addWarningMessage(String msg, String detail) {
		addMessage(FacesMessage.SEVERITY_WARN, msg, detail);
	}

	public static void addInfoMessage(String msg) {
		addMessage(FacesMessage.SEVERITY_INFO, msg);
	}

	public static void addInfoMessage(String msg, String detail) {
		addMessage(FacesMessage.SEVERITY_INFO, msg, detail);
	}

	public static void addErrorMessage(String msg) {
		addMessage(FacesMessage.SEVERITY_ERROR, msg);
	}

	public static void addErrorMessage(String msg, String detail) {
		addMessage(FacesMessage.SEVERITY_ERROR, msg, detail);
	}

	public static void addMessage(String msg) {
		addMessage(null, msg, "");
	}

	public static void addMessage(Severity severity, String msg) {
		addMessage(severity, msg, "");
	}

	public static void addMessage(String msg, String detail) {
		addMessage(msg, detail);
	}

	public static void addMessage(Severity severity, String msg, String detail) {
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage(severity, msg, detail));
	}

}
