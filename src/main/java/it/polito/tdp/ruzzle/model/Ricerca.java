package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {
	
	public List<Pos> trovaParola(String parola, Board board) {
		
		for(Pos p: board.getPositions()) {
			//per avere la stringa vera e propria bisogna fare get
			if(board.getCellValueProperty(p).get().charAt(0)==parola.charAt(0)) {
				//inizio potenziale della parola 
				//faccio ricorsione 
				List<Pos>  percorso = new ArrayList<Pos>();
				percorso.add(p); 
				if(cerca(parola,1,percorso, board))
					return percorso;
			}
		}
		
		return null; 
			
	}

	private boolean cerca(String parola, int livello, List<Pos> percorso, Board board) {
		//caso terminale
		if(livello==parola.length())
			return true; 
		
		//caso intermedio
		Pos ultima = percorso.get(percorso.size()-1); //è l'ultima che ho inserito nella soluzione parziale
		List<Pos> adiacenti = board.getAdjacencies(ultima); 
		
		for(Pos p: adiacenti) {
			//controllo che non sia stata usata e che sia corretta
			if(!percorso.contains(p) && parola.charAt(livello)== board.getCellValueProperty(p).get().charAt(0)) {
				percorso.add(p); 
			  if(cerca(parola,livello+1, percorso, board)) 
				  return true;
			  percorso.remove(percorso.size()-1);
			}
		}
		
		return false;
	}
	

}
