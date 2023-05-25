package outils.son;

import outils.son.Sound;
import java.io.File;
import outils.son.exceptions.*;
import java.io.Serializable;

/**
 * <p>Titre : </p>
 *
 * <p>Description : </p>
 *
 * <p>Copyright : Copyright (c) 2007</p>
 *
 * <p>Société : </p>
 *
 * @author non attribuable
 * @version 1.0
 */

public class Son implements Serializable {

    //--- propriétés ---
    private String son ;
    private Sound sound ;

    //--- constructeur : charge en mémoire le son ---
    public Son (String nomfic) {
        this.son = nomfic ;
        try {
            this.sound = new Sound(new File(this.son));
        } catch (SonException ex) {
        }
    }

    //--- pour jouer une fois un son ---
    public void play() {
        this.sound.boucle(1) ;
    }

    //--- pour libérer la mémoire du son ---
    public void close() {
        this.sound.fermer();
    }

    //--- pour jouer un son en boucle ---
    public void playContinue () {
        sound.boucle() ;
    }

}

