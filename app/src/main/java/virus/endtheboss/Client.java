package virus.endtheboss;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.net.Socket;

/**
 * Created by Quentin Gangler on 16/02/2016.
 *
 */
public class Client extends AsyncTask<Void, Object, Void>{
    private int id;
    private boolean hote;
    private String adresse;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Activity mActivity;
    private boolean running;

    public Client(String adresse, Activity activity){
        super();
        this.adresse = adresse;
        this.mActivity = activity;
        running = true;
    }

    @Override
    protected Void doInBackground(Void... params) {
        Socket s = null;
        try {
            Log.i("Client", "Connexion...");
            s = new Socket(adresse, 5560);
        } catch (IOException ex) {
            Log.i("Client", "Impossible de ce connecter à l'hote : " + ex.getMessage());
        }

        Log.i("Client", "Connection établie");

        try {
            input = new ObjectInputStream(s.getInputStream());
            output = new ObjectOutputStream(s.getOutputStream());
            Log.i("Client", "Association de flux réussie !");
        } catch(IOException e) {
            Log.i("Client", "Association des flux impossible : " + e);
        }

        Object o = null;
        while(running){
            try {
                Log.i("Client", "En attente d'object");
                try {
                    o = input.readObject();
                    publishProgress(o);
                }catch(EOFException ex){
                    running = false;
                    Log.i("Client", "Impossible de lire le flux (fin de flux)");
                } catch (OptionalDataException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (ClassNotFoundException e) {
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

        return null;
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        Log.i("Client", "Object reçu");
        if(values[0] != null)
            postProgressOnActivity(values[0]);
    }

    private void postProgressOnActivity(Object o){
        Log.i("Client", "Execution de la méthode postProgressOnActivity");
        ((GameActivity) mActivity).objectReseauRecu(o);
    }

    public void disconnect(){
        running = false;
    }

    public synchronized void send(Object o){
        Log.i("Client", "Envoi d'object, output null ? " + output);
        if(output != null){
            try {
                output.writeObject(o);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
