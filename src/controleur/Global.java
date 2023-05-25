package controleur;

/**
 * Regroupement des constantes de l'application
 * @author emds
 *
 */
public interface Global {
	
	public static final Integer PORT = 6666 ;
	
	// fichiers
	public static final String
		SEPARATOR = "//",
		CHEMIN = "media" + SEPARATOR,
		CHEMINFONDS = CHEMIN + "fonds" + SEPARATOR,
		CHEMINPERSOS = CHEMIN + "personnages" + SEPARATOR,
		CHEMINMURS = CHEMIN + "murs" + SEPARATOR,
		CHEMINBOULES = CHEMIN + "boules" + SEPARATOR,
		CHEMINSONS = CHEMIN + "sons/",
		EXTIMAGE = ".gif" ;

	// images
	public static final String
	FONDCHOIX = CHEMINFONDS + "fondchoix.jpg",	
	FONDARENE = CHEMINFONDS+"fondarene.png",
	PERSO = CHEMINPERSOS + "perso",
	BOULE = CHEMINBOULES + "boule",
	MUR = CHEMINMURS + "mur.gif" ;
	
	// sons
	public static final String
	SONPRECEDENT = CHEMINSONS + "button_click.wav", // sur le clic du bouton précédent
	SONSUIVANT = CHEMINSONS + "button_click.wav", // sur le clic du bouton suivant
	SONGO = CHEMINSONS + "enter_realm.wav", // sur le clic du bouton go
	SONWELCOME = CHEMINSONS + "welcome.wav", // à l'entrée de la frame ChoixJoueur
	SONAMBIANCE = CHEMINSONS + "sorc.wav" ; // son d'ambiance dans tout le jeu
	
	public static final Integer
		FIGHT1 = 0,
		FIGHT2 = 1,
		FIGHT3 = 2,
		HURT1 = 3,
		HURT2 = 4,
		HURT3 = 5,
		HURT4 = 6,
		HURT5 = 7,
		DEATH1 = 8,
		DEATH2 = 9,
		DEATH3 = 10,
		DEATH4 = 11,
		DEATH5 = 12 ;
	public static final String[] 
		SON = {
				"bladeSwing.wav",
				"arrowShoot.wav",
				"magicShoot.wav",
				
				"rogue_hit.wav",
				"archer_hit.wav",
				"warrior_hit.wav",
				"wizard_hit.wav",
				"priest_hit.wav",
				
				"rogue_death.wav",
				"archer_death.wav",
				"warrior_death.wav",
				"wizard_death.wav",
				"priest_death.wav"} ;

	
	// personnages
	public static final int
		ROGUE = 1,
		ARCHER = 2,
		WARRIOR = 3,
		NECROMANCER = 4,
		SORCERER = 5,
		GAUCHE = 0,
		DROITE = 1,
		HAUT = 2,
		BAS = 3,
		TIRE = 4,
		NBETATSMARCHE = 2,
		NBETATSBLESSE = 2,
		LEPAS = 10, // nombre de pixels de déplacement d'un pas
		NBPERSOS = 5,
		H_MESSAGE = 8,
		H_PERSO = 32,
		L_PERSO = 32 ;
	public static final String
		MARCHE = "marche",
		BLESSE = "touche",
		MORT = "tombe" ;
	
	// messages serveurs
	public static final String SEPARE = "~" ;
	public static final int
		PSEUDO = 0,
		CHAT = 1,
		ACTION = 2 ;
	
	// tailles dans frames
	public static final int
		H_ARENE = 800,
		L_ARENE = 1200,
		H_CHAT = 200,
		H_SAISIE = 25,
		MARGE = 5 ; // écarts entre les objets
	
	// murs
	public static final int
		NBMURS = 372, // nombre de murs
		H_MUR = 16, // hauteur du mur
		L_MUR = 16 ; // largeur du mur
	
	// boule
	public static final int
	H_BOULE = 32, // hauteur de la boule
	L_BOULE = 32 ; // largeur de la boule
	
	
}
