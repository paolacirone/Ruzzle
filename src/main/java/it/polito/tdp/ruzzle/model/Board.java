package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Memorizza le lettere presenti nella scacchiera Ruzzle.
 * è la classe che memorizza la matrice di gioco
 * @author Fulvio
 *
 */
public class Board {
	private List<Pos> positions; //contiene la lista di posizioni della matrice 
	private Map<Pos, StringProperty> cells; //Mappa di celle, utilizziamo delle StringProperty
	//utilizzando le Stryng property possiamo far si che il bottone sia legato alla string property
	//il cambiamento fa riferimento all'interfaccia grafica
	//ogni volta che cambia la stringa, tramite il binding anche il testo del bottone cambierà 
	//l'unica differenza con la stringa normale è he si usano i get

	private int size; //nel nostro caso 4, grandezza della matrice 

	/**
	 * Crea una nuova scacchiera della dimensione specificata
	 * @param size
	 */
	public Board(int size) {
		this.size = size;

		//Definisco le "caselle" del gioco (e la forma del piano di gioco)
		this.positions = new ArrayList<>();
		for (int row = 0; row < this.size; row++) {
			for (int col = 0; col < this.size; col++) {
				this.positions.add(new Pos(row, col));
			}
		}

		//Definisco il contenuto delle caselle
		this.cells = new HashMap<>();

		//Ogni casella conterrà una String Property, inizialmente vuota, per contenere il proprio carattere  
		for (Pos p : this.positions) {
			this.cells.put(p, new SimpleStringProperty());
		}
	}
	
	/**
	 * Fornisce la {@link StringProperty} corrispondente alla {@link Pos} specificata. <p>
	 * 
	 * Può essere usata per sapere che lettera è presente
	 * (es. {@code getCellValueProperty(p).get()}) oppure per fare un binding della proprietà stessa sulla mappa visuale.
	 * @param p
	 * @return
	 */
	public StringProperty getCellValueProperty(Pos p) {
		return this.cells.get(p) ;
	}

	/**
	 * Restituisce la lista di oggetti {@link  Pos} che corrispondono alle posizioni lecite sulla scacchiera. Gli elementi sono ordinati per righe.
	 * @return
	 */
	public List<Pos> getPositions() {
		return positions;
	}

	/**
	 * Crea una nuova scacchiera generando tutte lettere casuali
	 */
	public void reset() {
		for(Pos p: this.positions) {
			int random = (int)(Math.random()*26) ;
			String letter = Character.toString((char)('A'+random)) ;
			this.cells.get(p).set(letter); //questo set si riflette sul bottone
		}
	}
	
	/**
	 * Data una posizione, restituisce tutte le posizioni adiacenti
	 * @param p
	 * @return
	 */
	public List<Pos> getAdjacencies(Pos p) {
		List<Pos> result = new ArrayList<>() ;
		
		for(int r = -1; r<=1; r++) {
			for(int c = -1; c<=1; c++) {
				// tutte le 9 posizioni nell'intorno della cella				
				if(r!=0 || c!=0) { // escludo la cella stessa (offset 0,0)
					Pos adj = new Pos(p.getRow()+r, p.getCol()+c) ;
					//controllo che gli indici non siano fuori dalla griglia
					if(positions.contains(adj)) {
						result.add(adj) ;
					}
				}
			}
		}
		
		return result ;
	}


	
}
