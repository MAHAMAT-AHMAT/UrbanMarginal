package modele;

import java.util.ArrayList;
import java.util.Hashtable;

import controleur.Global;
import outils.connexion.Connection;

/**
 * Gestion de l'attaque (boule tirée, joueur éventuellement touché)
 * @author emds
 *
 */
public class Attaque extends Thread implements Global {

	// propriétés
	private Joueur attaquant ;
	private JeuServeur jeuServeur ;
	private ArrayList<Mur> lesMurs = new ArrayList<Mur>() ;
	private Hashtable<Connection, Joueur> lesJoueurs = new Hashtable<Connection, Joueur>() ;
	
	/**
	 * Constructeur
	 * @param attaquant
	 * @param jeuServeur
	 */
	public Attaque(Joueur attaquant, JeuServeur jeuServeur, ArrayList<Mur> lesMurs, Hashtable<Connection, Joueur> lesJoueurs) {
		this.attaquant = attaquant;
		this.jeuServeur = jeuServeur;
		this.lesMurs = lesMurs ;
		this.lesJoueurs = lesJoueurs ;
		this.start();
	}

	/**
	 * méthode dans le thread, pour faire bouger la boule
	 */
	public void run() {
		// l'attaquant est mis à la position 1 de la marche
		attaquant.affiche(MARCHE, 1);
		// récupération de la boule et orientation de l'attaquant
		Boule laboule = attaquant.getBoule() ;
		int orientation = attaquant.getOrientation() ;
		laboule.getLabel().getjLabel().setVisible(true);
		// gestion de l'éventuel joueur touché par la boule
		Joueur victime = null ;
		// boucle sur la trajectoire de la boule
		do {
			switch (orientation)
			{
				case GAUCHE: laboule.setPosX(laboule.getPosX()-LEPAS) ; break ;
				case DROITE: laboule.setPosX(laboule.getPosX()+LEPAS) ; break ;
				case HAUT  : laboule.setPosY(laboule.getPosY()-LEPAS) ; break ;
				case BAS   : laboule.setPosY(laboule.getPosY()+LEPAS) ; break ;
			}
			
			laboule.getLabel().getjLabel().setBounds(laboule.getPosX(), laboule.getPosY(), L_BOULE, H_BOULE);
			pause(5, 0);
			jeuServeur.envoi(laboule.getLabel());
			victime = toucheJoueur() ;
		}while(laboule.getPosX()>=0 && laboule.getPosX()<=L_ARENE && !toucheMur() && victime==null &&
			   laboule.getPosY()>=0 && laboule.getPosY()<=H_ARENE &&
			   laboule.getPosX()<=attaquant.getPosX() + attaquant.getPortee() * 32 && laboule.getPosX()>= attaquant.getPosX() - attaquant.getPortee() * 32 &&
			   laboule.getPosY()<=attaquant.getPosY() + attaquant.getPortee() * 32 && laboule.getPosY()>= attaquant.getPosY() - attaquant.getPortee() * 32) ;
		// controle si un joueur est touché et s'il n'est pas déjà mort
		if (victime!=null && !victime.estMort()) {
			// son du joueur touché
			switch (victime.getNumPerso())
			{
				case ROGUE  	: jeuServeur.envoi(HURT1) ; break ;
				case ARCHER 	: jeuServeur.envoi(HURT2) ; break ;
				case WARRIOR	: jeuServeur.envoi(HURT3) ; break ;
				case NECROMANCER: jeuServeur.envoi(HURT4) ; break ;
				case SORCERER	: jeuServeur.envoi(HURT5) ; break ;
			}
			// gestion du gain et de la perte de vie
			victime.perteVie(attaquant.getDegats());
			attaquant.gainVie();
			// animation de la victime touchée
			
			victime.affiche(BLESSE,  1);
			pause(80, 0);
			// controle si la victime est morte
			if (victime.estMort()) {
				// son du joueur touché
				switch (victime.getNumPerso())
				{
					case ROGUE  	: jeuServeur.envoi(DEATH1) ; break ;
					case ARCHER 	: jeuServeur.envoi(DEATH2) ; break ;
					case WARRIOR	: jeuServeur.envoi(DEATH3) ; break ;
					case NECROMANCER: jeuServeur.envoi(DEATH4) ; break ;
					case SORCERER	: jeuServeur.envoi(DEATH5) ; break ;
				}
				victime.affiche(MORT, 0);
			}else{
				// repositionnement normal de la victime
				victime.affiche(MARCHE,  1);				
			}
			// repositionnement normal de l'attaquant
			attaquant.affiche(MARCHE, 1);
		}
		// la boule a fini son parcourt et redevient invisible
		laboule.getLabel().getjLabel().setVisible(false);		
		jeuServeur.envoi(laboule.getLabel());
	}
	
	/**
	 * Gestion d'une pause (qui servira à réguler le mouvement de la boule)
	 * @param milli
	 * @param nano
	 */
	private void pause(long milli, int nano) {
		try {
			Thread.sleep(milli, nano);
		} catch (InterruptedException e) {
			System.out.println("Problème sur la pause");
		}
	}
	
	/**
	 * Controle si la boule touche un mur
	 * @return
	 */
	private boolean toucheMur() {
		for (Mur unMur : lesMurs) {
			if (attaquant.getBoule().toucheObjet(unMur)) {
				return true ;
			}
		}
		return false ;		
	}

	/**
	 * Controle si la boule touche un joueur
	 * @return
	 */
	private Joueur toucheJoueur() {
		for (Joueur unJoueur : lesJoueurs.values()) {
			if (attaquant.getBoule().toucheObjet(unJoueur)) {
				return unJoueur ;
			}
		}
		return null ;
	}
	
	
}
