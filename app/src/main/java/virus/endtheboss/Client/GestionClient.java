package virus.endtheboss.Client;

import virus.endtheboss.ClientActivity;
import virus.endtheboss.Serveur.MessageServeur;

/**
 * Created by Quentin Gangler on 20/02/2016.
 * Classe qui fait le lien entre une activité et le client
 */
public class GestionClient {
    public static Client client;

    public static void connect(Joueur joueur, String adresseIP, ClientActivity activity){
        //client = new Client(adresseIP, this);
        if(client == null) {
            client = new Client("10.1.250.15", joueur, activity);
            client.execute();
        }else{
            send(new MessageServeur(joueur, MessageServeur.TypeMessage.CONNEXION));
        }
    }

    public static void changeActivity(ClientActivity activity){
        client.setActivity(activity);
    }

    public static void send(Object o){
        if(client != null)
            client.send(o);
    }
}
