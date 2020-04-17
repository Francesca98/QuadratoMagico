package it.polito.tdp.quadrato.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RisolviQuadrato {
	private int N ; // lato del quadrato
	private int N2 ; // numero di caselle (N^2)
	private int magica ; // costante magica
	private List<List<Integer>> soluzioni = new LinkedList<>(); //una liste di liste xk hai tutte le soluzioni (che sono liste)
	
	/*mi serve un costruttore che mi dica la dimensione del lato N */
	public RisolviQuadrato(int N){
		this.N=N;
		this.N2= N*N; // questa è ridondante la faccio solo per comodita
		this.magica= N*(N2+1)/2;
	}
	
	/* questi sono gli algoritmi ricorsivi e sono sempre uno di ricerca(quadrati) e
	 *  poi quello ricorsivo vero e proprio */
	//Calcola tutti i quadrati magici
	public List<List<Integer>> quadrati() {
		List<Integer> parziale = new ArrayList<>();
		int livello=0;
		cerca(parziale,livello);
		return this.soluzioni;
	}
	//procedura ricorsiva (privata)
public void cerca(List<Integer> parziale, int livello ) {
	if(livello == N2) //caso terminale
	{
		if(controlla(parziale))
		{
			//vuol dire che è vero quindi è magico
			//System.out.println(parziale);
	 //è sbagliato xk in questo modo sto mettendo il riferimento a pariziale  e non gli elementi 
		/*nel caso di oggetti mutabili nella ricorsione come appunto parziale che a livello 0 causa backtarck è vuoto
		 * devo fare una copia, se fosse stata una stringa non avrei avuto problemi xk la stringa è immutabile*/
		
			List<Integer> copia = new ArrayList<>(parziale); //è una copia non è il riferimento
			this.soluzioni.add(copia);
		}
		
		
	
		return ;
		
	}
	//controlli intermedi , quando arrivo ad avere una riga piena vedo se è una soluzione accettabile altrimenti la 
	//una riga è completa quando N livello è multiplo di N
	 /* se N=4 allora inizio a controllare quando il livello è 4 cioè la riga è completata altrimenti non ha senso*/

if(livello%N==0 && livello!=0) //livello%N==0  vuol dire che è multiplo
{
	if(!controllaRiga(parziale, (livello/N)-1)) //se livello vale 4 controllo la riga 0
		//poi l'altro multiplo sarà 8 quindi avrò 4-1=3
		return; //PRUNING
	//se la riga non ha il numero magico esco fuori cioè taglio i rami ri ricorsione a certi livelli xk so che non vanno bene
	}
	//caso intermedio 
	for(int valore=1; valore<= N2; valore++)
	{
		if(!parziale.contains(valore)) /*se la soluzione non contiene ancora quell'elemento allora posso 
		provare a metterlo
		*/
		{
			parziale.add(valore);
			cerca(parziale,livello+1);
			parziale.remove(parziale.size()-1); //backtrack
			
		}
	}
	
		
	}
	
	
	/**
	 * Verifica se una soluzione rispetta tutte le somme
	 * @param parziale
	 * @return
	 */
	/* qui controlla che tutte le righe/colonne/ diagonali abbiano tutti i numeri altrimenti c'è qualcosa 
	 * di sbagliato e non ha senso fare la somma*/

	private boolean controlla(List<Integer> parziale) {
		if(parziale.size()!=this.N*this.N)
			throw new IllegalArgumentException("Numero di elementi insufficiente") ;
		
		// Fai la somma delle righe
		for(int riga=0; riga<this.N; riga++) {
			int somma = 0 ;
			for(int col=0; col<this.N; col++) {
				somma += parziale.get(riga*this.N+col) ;
			}
			if(somma!=this.magica)
				return false ;
		}
		
		// Fai la somma delle colonne
		for(int col=0; col<this.N; col++) {
			int somma = 0 ;
			for(int riga=0; riga<this.N; riga++) {
				somma += parziale.get(riga*this.N+col) ;
			}
			if(somma!=this.magica)
				return false ;
		}
		
		// diagonale principale
		int somma = 0;
		for(int riga=0; riga<this.N; riga++) {
			somma += parziale.get(riga*this.N+riga) ;
		}
		if(somma!=this.magica)
			return false ;
		
		// diagonale inversa
		somma = 0;
		for(int riga=0; riga<this.N; riga++) {
			somma += parziale.get(riga*this.N+(this.N-1-riga)) ;
		}
		if(somma!=this.magica)
			return false ;

		return true ;
	}
	
	private boolean controllaRiga (List<Integer> parziale , int numeroRiga)
	{ //FA LA somma della riga specificata 
		int somma=0;
		for(int col=0;col<N;col++)
		{
			somma +=parziale.get(numeroRiga*N+col);
		}
		return somma==magica; // se sono uguali ritorna vero
		
	}
}
