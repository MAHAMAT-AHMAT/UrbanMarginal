package modele;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;

/**
 * Gestion des murs
 * @author emds
 *
 */
public class Mur extends Objet implements Global {

	/**
	 * Constructeur
	 */
	public Mur(int x, int y) {
		posX = x * L_MUR ;
		posY = y * H_MUR ;
		// création du label pour ce mur (pas d'importance pour le rang dans le panel, d'où -1)
		label = new Label(-1, new JLabel());
		// caractéristiques du mur (centrage, position, image)
		label.getjLabel().setHorizontalAlignment(SwingConstants.CENTER);
		label.getjLabel().setVerticalAlignment(SwingConstants.CENTER);
		label.getjLabel().setBounds(posX, posY, L_MUR, H_MUR);
	}
	
}
