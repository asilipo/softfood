package it.softfood;

import java.util.ArrayList;

import it.softfood.entity.Tavolo;
import it.softfood.facade.PDATavoloFacade;

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PDATavoloFacade pda=new PDATavoloFacade();
		ArrayList<Tavolo> list=(ArrayList<Tavolo>) pda.selezionaTavoliLiberi();
		Tavolo tav1=list.get(0);
		Tavolo tav2=list.get(1);
		ArrayList<String> stringList=new ArrayList<String>();
		stringList.add(tav1.getRiferimento());
		stringList.add(tav2.getRiferimento());
		Long id=pda.occupaTavoli(stringList);
		System.out.println("NEW ID: "+id);
		
		

	}

}
