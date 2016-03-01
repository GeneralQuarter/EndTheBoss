package virus.endtheboss.Serveur;

/**
 * Created by Quentin Gangler on 25/02/2016.
 */
public class GestionServeur {

    public static Serveur serveur;

    public static void sendAll(Object o){
        if(serveur != null)
            serveur.sendAll(o);
    }
}
