package strategie;

import table.Pioche;
import table.Talon;

/**
 *Interface representant les strategies des joueurs virtuels
 *
 * @author aureliengoulon
 *
 */
public interface StrategieDeJeu {
	
	public boolean jouer(Pioche p, Talon t);
}