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
		this.maxpeople=0;
	}

	public List<Nerc> getNercList() {
		return podao.getNercList();
	}

	public List<Poweroutage> worstCase(int years, int hours) {

		
		recursion(new ArrayList<Poweroutage>(), 0, years, hours);

		return worstcase;

	}

	private void recursion (List<Poweroutage>parziale, int L, int years, int hours) {
		//caso terminale
		
		if(L==this.listapwrpernerc.size())
			return;
		
		//controlli
		
		if(this.worstCustomersAffected(parziale)) {
			worstcase=new ArrayList<>(parziale);
		}
		
		
		//casi intermedi
		parziale.add(this.listapwrpernerc.get(L));
		
		if(validitaAnni(parziale, years)&& this.validitaOre(parziale, hours))
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
			return true;
		}

		return false;

	}

	private boolean validitaOre(List<Poweroutage>lista, int hours) {
		
		int count=0;
		
		if(lista.size()!=0) {
			
			
			for(Poweroutage p: lista) {
				count += Math.abs(Duration.between(p.getDateEventBegan(), p.getDateEventFinished()).toHours());
		}
			
			
					if(count>hours) {
						return false;
						
					}
					
					return true;
			
		}
		
		return false;
		
	}
	
	
	public boolean worstCustomersAffected(List<Poweroutage> lista) {
		int people=0;
		for(Poweroutage p: lista) {
			people+=p.getCustomersAffected();
		}
		if(people>this.maxpeople) {
			maxpeople=people;
			return true;
		}
		return false;
	}

}
