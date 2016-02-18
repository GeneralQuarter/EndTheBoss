package virus.endtheboss.Vue;

import virus.endtheboss.Client;
import virus.endtheboss.Controleur.PersonnageControleur;
import virus.endtheboss.Enumerations.Action;
import virus.endtheboss.Enumerations.Deplacement;
import virus.endtheboss.Modele.ActionPersonnage;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Personnage;
import virus.endtheboss.PlayerID;

/**
 * Created by Quentin Gangler on 17/02/2016.
 */
public class GestionReseau {

    public static Client client;

    public static void changePositionPersonnage(PersonnageControleur p, Deplacement d){
        if(client != null)
            client.send(new ActionPersonnage(Action.DEPLACEMENT, p.getPersonnage(), d));
    }

    public static void faiDommagePersonnage(Personnage p, int value){
        if(client != null)
            client.send(new ActionPersonnage(Action.DEGAT_AVEC_ARMURE, p, value));
    }

    public static void soignePersonnage(Personnage p, int value){
        if(client != null)
            client.send(new ActionPersonnage(Action.SOIN, p, value));
    }

    public static void faitDommagePersonnageSansArmure(Personnage p, int value){
        if(client != null)
            client.send(new ActionPersonnage(Action.DEGAT_SANS_ARMURE, p, value));
    }

    public static void envoyerDebutTour(PlayerID dst){
        if(client != null)
            client.send(new ActionPersonnage(Action.DEBUT_TOUR, dst));
    }

    public static void envoyerFinTour(PlayerID dst){
        if(client != null)
            client.send(new ActionPersonnage(Action.FIN_TOUR, dst));
    }
}
