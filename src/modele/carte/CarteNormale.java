package carte;

/**
 * Classe modelisant la carte normale ( de 0 a 9)
 * @author ikounga_marvel
 *
 */
public class CarteNormale extends Carte{
	
	private int numero;
	
	/**
	 * Constructeur de la classe
	 * @param numero numero de la carte
	 * @param couleur couleur de la carte
	 * @param points points que rapporte la carte
	 * @param image image de la carte
	 */
	public CarteNormale(String couleur, int points, int numero, String image) {
		super(couleur, points, image);
		this.numero = numero;
	}

	/**
	 * getter du numero
	 * @return numero le numero de la carte
	 */
	public int getNumero() {
		return numero;
	}
	

	/**
	 * Methode permettant de savoir si une carte est speciale
	 * @return boolean false
	 */
	@Override
	public boolean isSpecial() {
		return false;
	}

	/**
	 * Methode permettant d'afficher une carte speciale
	 * @return void
	 */
	public void afficher() {
		System.out.print(" (" + numero +", "+this.getCouleur()+")");
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return " (" + numero +", "+this.getCouleur()+")";
	}

	/**
	 * Methode permettant de verifier la compatibilite entre la carte et celle au dessus du talon
	 * @param  c carte au dessus du talon 
	 * @return boolean
	 */
	@Override
	public boolean verifier(Carte c) {
		
		
		if (c.isSpecial()) {
			String couleur = c.getCouleur();
			if (this.getCouleur() == couleur || couleur =="multi") {
				return true;
			}else{
				return false;
			}
			
		}else{
			if (c.getCouleur() == this.getCouleur() || this.getNumero() == c.getPoints()) {
				return true;
			}
			else {
				return false;
			}
		}
		
	}
	
	/**
	 * Methode permettant de conna”tre le numero ou la specialite de la carte 
	 * Equivalent de getNumero
	 * @return String
	 */
	public String getTypeCarte() {
		return ""+this.getNumero();
	}
}
