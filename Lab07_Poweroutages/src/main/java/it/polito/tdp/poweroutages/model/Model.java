package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {

	private PowerOutageDAO podao;
	private List<Poweroutage> listapwrpernerc;
	private List<Poweroutage> worstcase;
	private int maxpeople;

	public Model(int id) {
		podao = new PowerOutageDAO();
		this.listapwrpernerc = podao.getPowerOutagesByNerc(id);
		this.maxpeople = 0;
	}

	public List<Nerc> getNercList() {
		return podao.getNercList();
	}

	public List<Poweroutage> worstCase(int years, int hours) {

		recursion(new ArrayList<Poweroutage>(), 0, years, hours);

		return worstcase;

	}

	private void recursion (List<Poweroutage>parziale, int L, int years, int hours) {
		
	
		
		
		//aggiungo soluzione livello precedente se peggiore!!!!!!!!
		
		if(this.worstCustomersAffected(parziale))
			worstcase=new ArrayList<>(parziale);
		
		
		//caso terminale
		if(L==this.listapwrpernerc.size())
			return;
		
		
		//casi intermedi
		parziale.add(this.listapwrpernerc.get(L));
		
	if(validitaOre(parziale, hours) && validitaAnni(parziale, years))
		recursion(parziale,L+1, years, hours);
		
		parziale.remove(parziale.size()-1);
		recursion(parziale, L+1, years, hours);
		
		
		
	}

	private boolean validitaAnni(List<Poweroutage> lista, int years) {

		int count = 0;

		if (lista.size() != 0) {
			count = Math.abs(lista.get(0).getDateEventBegan().getYear()
					- lista.get(lista.size() - 1).getDateEventFinished().getYear());
			if (count > years) {
				return false;
			}

		}

		return true;
	}

	private boolean validitaOre(List<Poweroutage> lista, int hours) {

		int count = 0;

		if (lista.size() != 0) {

			for (Poweroutage p : lista) {
				count += Math.abs(Duration.between(p.getDateEventBegan(), p.getDateEventFinished()).toHours());
			}

			if (count > hours) {
				return false;

			}

		}

		return true;

	}

	public boolean worstCustomersAffected(List<Poweroutage> lista) {
		int people = 0;
		for (Poweroutage p : lista) {
			people += p.getCustomersAffected();
		}
		if (people > this.maxpeople) {
			maxpeople = people;
			return true;
		}
		return false;
	}

	
private void cerca1(List<Poweroutage> parziale, int livello, int years, int hours) {
		
		//Controllo se il powerOutages che aggiungo a parziale non abbia un numero di ore tale che parziale superi il limite 
		//inserito dall'utente
		if(!this.validitaOre(parziale, hours)) {
			return;
			
		}
		
		if(this.worstCustomersAffected(parziale)) {
			
			
			this.worstcase=new ArrayList<>(parziale);
			
		}
		
		for(Poweroutage l: this.listapwrpernerc) {
			if(!parziale.contains(l)) {
				parziale.add(l);
				//Controllo se il powerOutages che aggiungo a parziale non abbia un intervallo annuale superiore a quello limite
				if(!this.validitaAnni(parziale, years)) {
					return;
				}
				cerca1(parziale, livello+1, years, hours);
				parziale.remove(parziale.size()-1);
			}
		}
	}
	
	
}
