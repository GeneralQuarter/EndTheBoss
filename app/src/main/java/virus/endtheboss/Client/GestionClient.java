package virus.endtheboss.Client;

import virus.endtheboss.ClientActivity;

/**
 * Created by Quentin Gangler on 20/02/2016.
 * Classe qui fait le lien entre une activité et le client
 */
public class GestionClient {
    public static Client client;

    public static void connect(Joueur joueur, String adresseIP, ClientActivity activity){
        //client = new Client(adresseIP, this);
        if(client == null) {
            client = new Client("192.168.173.1", joueur, activity);
            client.execute();
        }
    }

    public static void changeActivity(ClientActivity activity){
        client.setActivity(activity);
    }

    public static void send(Object o){
        client.send(o);
    }
}
