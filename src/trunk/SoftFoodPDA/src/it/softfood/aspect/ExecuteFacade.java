package it.softfood.aspect;





import it.softfood.entity.Tavolo;
import it.softfood.facade.SoftfoodFacade;

import java.util.Date;
import java.util.List;


public class ExecuteFacade{
	
	public List<Tavolo> selezionaTavoliLiberi(){
		System.out.println("GIUS");
		SoftfoodFacade soft= SoftfoodFacade.getInstance();
		return soft.selezionaTavoliLiberi();
	}
	
	
}