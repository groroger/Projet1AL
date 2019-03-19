package fr.afcepf.al33.projet1.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.afcepf.al33.projet1.IBusiness.ClientIBusiness;
import fr.afcepf.al33.projet1.entity.Client;


@ManagedBean(name="mbFindClient")
@SessionScoped
public class RechercherClientManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@EJB
	private ClientIBusiness proxyClientBu;
	
	private Client client;
	private Client foundClient;
	private List<Client> clients;

	

	
	public void onSelect(Client cl, String typeOfSelection, String indexes) {
	
		client=clients.get(Integer.parseInt(indexes));
		foundClient = client;
		System.out.println(cl.getNom());
		System.out.println(cl.getId());
		
		 FacesContext facesContext = FacesContext.getCurrentInstance();
		 HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		 session.setAttribute("foundClient", foundClient);
		 facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext,null,"/ficheClient.xhtml?faces-redirect=true");
	}
	
//	public String selectClient(Client client) {
//		client = proxyClientBu.searchById(client.getId());
//		return "/ficheClient.xhtml";
//	}
	
	@PostConstruct
	public void init() {
		clients = proxyClientBu.getAllClients();
		
	}

	public ClientIBusiness getProxyClientBu() {
		return proxyClientBu;
	}

	public void setProxyClientBu(ClientIBusiness proxyClientBu) {
		this.proxyClientBu = proxyClientBu;
	}
	
	
	

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Client getFoundClient() {
		return foundClient;
	}

	public void setFoundClient(Client foundClient) {
		this.foundClient = foundClient;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	


	
}
