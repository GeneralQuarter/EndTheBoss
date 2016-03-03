package virus.endtheboss.Serveur;

import virus.endtheboss.Client.Joueur;

/**
 * Created by Quentin Gangler on 25/02/2016.
 */
public class GestionServeur {

    public static Serveur serveur;
    public static JoueurServeur joueurServeur;

    public static void sendAll(Object o){
        if(serveur != null)
            serveur.sendAll(o);
    }

    public static void sendToLocal(Object o){
        if(joueurServeur != null && serveur != null){
            serveur.receptionObject(joueurServeur, o);
        }
    }
}
