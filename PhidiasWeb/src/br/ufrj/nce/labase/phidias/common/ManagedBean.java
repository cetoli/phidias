package br.ufrj.nce.labase.phidias.common;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public abstract class ManagedBean {

	private String mensagem;
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@SuppressWarnings("unchecked")
	public static Object getSessionAttribute(String attributeName) {
		try {
			ExternalContext ec = getExternalContext();
			if (ec != null) {
				Map attrMap = ec.getSessionMap();
				if (attrMap != null) {
					return attrMap.get(attributeName);
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static void setSessionAttribute(String attributeName,
			Object attributeValue) {
		try {
			ExternalContext ec = getExternalContext();
			if (ec != null) {
				Map attrMap = ec.getSessionMap();
				if (attrMap != null) {
					attrMap.put(attributeName, attributeValue);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static Object getRequestAttribute(String attributeName) {
		try {
			ExternalContext ec = getExternalContext();
			if (ec != null) {
				Map attrMap = ec.getRequestMap();
				if (attrMap != null) {
					return attrMap.get(attributeName);
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static void setRequestAttribute(String attributeName,
			Object attributeValue) {
		try {
			ExternalContext ec = getExternalContext();
			if (ec != null) {
				Map attrMap = ec.getRequestMap();
				if (attrMap != null) {
					attrMap.put(attributeName, attributeValue);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ExternalContext getExternalContext() {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			if (facesContext == null) {
				return null;
			} else {
				return facesContext.getExternalContext();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public static String getParameter(String nameParameter) {
		Map requestMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		return (String) requestMap.get(nameParameter);
	}
	
	protected void addInfoMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
	}
	
	protected void addErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}
}
