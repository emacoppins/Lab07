package it.polito.tdp.poweroutages.model;

import java.time.Duration;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model(3);
		int count=0;
		
		int i=0;
		for(Poweroutage p: model.worstCase(4, 200)) {
			i+=p.getCustomersAffected();
			count += Math.abs(Duration.between(p.getDateEventBegan(), p.getDateEventFinished()).toHours());
		}
		
		
		System.out.println(i);
		System.out.println(model.worstCase(4, 200));
		System.out.println(count);
		
		
		
	}
	


}



