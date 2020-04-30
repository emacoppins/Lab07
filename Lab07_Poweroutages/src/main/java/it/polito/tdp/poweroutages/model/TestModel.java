package it.polito.tdp.poweroutages.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model(19);
		
		int i=0;
		for(Poweroutage p: model.worstCase(20, 100)) {
			i+=p.getCustomersAffected();
		}
		System.out.println(i);
	}

}
