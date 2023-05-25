package outils.connexion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Gestion d'un serveur : attente de connexions de clients
 * @author emds
 *
 */
public class ServeurSocket extends Thread {

	// propriétés
	private Object leRecepteur ;
	private ServerSocket serverSocket ;
	
	/**
	 * Constructeur
	 * @param leRecepteur
	 * @param port
	 */
	public ServeurSocket(Object leRecepteur, int port) {
		this.leRecepteur = leRecepteur ;
		// création du socket serveur d'écoute des clients
		try {
			this.serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("erreur grave création socket serveur : "+e);
			System.exit(0);
		}
		// démarrage du thread d'écoute (attente d'un client)
		this.start();
	}
	
	/**
	 * Méthode thread qui va attendre la connexion d'un client
	 */
	public void run() {
		Socket socket ;
		// boucle infinie pour attendre un nouveau client
		while (true) {
			try {
				System.out.println("le serveur attend");
				socket = serverSocket.accept();
				System.out.println("un client s'est connecté");
				new Connection(socket, leRecepteur);
			} catch (IOException e) {
				System.out.println("erreur sur l'objet serverSocket : "+e);
				System.exit(0);
			}
		}
	}
	
}
