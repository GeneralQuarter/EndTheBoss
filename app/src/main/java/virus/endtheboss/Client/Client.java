package virus.endtheboss.Client;

import android.os.AsyncTask;
import android.util.Log;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.net.Socket;

import virus.endtheboss.ClientActivity;
import virus.endtheboss.Serveur.MessageServeur;

/**
 * Created by Quentin Gangler on 16/02/2016.
 *
 */
public class Client extends AsyncTask<Void, Object, Void>{
    private ClientActivity activity;
    private Joueur joueur;
    private String adresse;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private boolean running;

    public Client(String adresse, Joueur joueur, ClientActivity activity){
        super();
        this.adresse = adresse;
        this.activity = activity;
        this.joueur = joueur;
        running = true;
    }

    public synchronized void setActivity(ClientActivity activity){
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(Void... params) {
        Socket s;
        try {
            Log.i("Client", "Connexion...");
            s = new Socket(adresse, 5560);
            connecte(s);
        } catch (IOException ex) {
            Log.i("Client", "Impossible de ce connecter à l'hote : " + ex.getMessage());
            publishProgress(ex.getMessage());
        }

        return null;
    }

    private void connecte(Socket s){
        Log.i("Client", "Connection établie");

        try {
            input = new ObjectInputStream(s.getInputStream());
            output = new ObjectOutputStream(s.getOutputStream());
            Log.i("Client", "Association de flux réussie !");
            Log.i("Client", "Connexion au serveur...");
            output.writeObject(new MessageServeur(joueur, MessageServeur.TypeMessage.CONNEXION));
        } catch(IOException e) {
            Log.i("Client", "Association des flux impossible : " + e);
        }

        Object o;
        while(running){
            //Log.i("Client", "En attente d'object");
            try {
                o = input.readObject();
                publishProgress(o);
            }catch(IOException ex){
                running = false;
                Log.i("Client", "Impossible de lire le flux (fin de flux)");
            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        try {
            Log.i("Client", "Déconnexion");
            input.close();
            output.close();
            s.close();
        } catch(IOException e) {
            System.err.println("Erreur lors de la fermeture des flux et des sockets : " + e);
        }
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        //Log.i("Client", "Object reçu " + values[0]);
        if(values[0] != null)
            postProgressOnActivity(values[0]);
    }

    private void postProgressOnActivity(Object o){
        //Log.i("Client", "Execution de la méthode postProgressOnActivity");
        activity.receptionObjectFromClient(o);
    }

    public synchronized void disconnect(){
        running = false;
    }

    public synchronized void send(Object o){
        Log.i("Client", "Envoi d'object, Object " + o);
        if(output != null){
            try {
                output.writeObject(o);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
