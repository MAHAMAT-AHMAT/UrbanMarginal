package outils.connexion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * Gestion de la connexion entre 2 ordinateurs distants
 * @author emds
 *
 */
public class Connection extends Thread {
	
	// propriétés
	private Object leRecepteur ;
	private ObjectInputStream in ;
	private ObjectOutputStream out ;

	/**
	 * Constructeur
	 * @param socket
	 * @param leRecepteur
	 */
	public Connection(Socket socket, Object leRecepteur) {
		this.leRecepteur = leRecepteur ;
		// création du canal de sortie pour envoyer des informations
		try {
			this.out = new ObjectOutputStream(socket.getOutputStream()) ;
		} catch (IOException e) {
			System.out.println("erreur création canal out : "+e);
			System.exit(0);
		}
		// création du canal d'entrée pour recevoir des informations
		try {
			this.in = new ObjectInputStream(socket.getInputStream()) ;
		} catch (IOException e) {
			System.out.println("erreur création canal in : "+e);
			System.exit(0);
		}
		// démarrage du thread d'écoute (attente d'un message de l'ordi distant)
		this.start() ;
		// envoi de l'instance de connexion vers le recepteur (généralement le controleur)
		((controleur.Controle)this.leRecepteur).setConnection(this) ;
	}
	
	public synchronized void envoi(Object unObjet) {
		try {
			this.out.reset();
			out.writeObject(unObjet);
			out.flush();
		} catch (IOException e) {
			System.out.println("erreur d'envoi sur le canal out : "+e);
		}
	}
	
	/**
	 * Méthode thread qui permet d'attendre des message provenant de l'ordi distant
	 */
	public void run() {
		boolean inOk = true ;
		Object reception ;
		while (inOk) {
			try {
				reception = in.readObject();
				((controleur.Controle)leRecepteur).receptionInfo(this, reception);
			} catch (ClassNotFoundException e) {
				System.out.println("erreur de classe sur réception : "+e);
				System.exit(0);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "l'ordinateur distant est déconnecté");
				((controleur.Controle)leRecepteur).deconnection(this);
				inOk = false ;
				try {
					in.close();
				} catch (IOException e1) {
					System.out.println("la fermeture du canal d'entrée a échoué : "+e);
				}
			}
		}
		
	}
	
}
