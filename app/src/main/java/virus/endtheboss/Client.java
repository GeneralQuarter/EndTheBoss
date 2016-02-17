package virus.endtheboss;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    public Client(String adresse, Activity activity){
        super();
        this.adresse = adresse;
        this.mActivity = activity;
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
        while(o == null){
            try {
                Log.i("Client", "En attente d'object");
                o = input.readObject();
                publishProgress(o);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Object... values) {
        Log.i("Client", "Object reçu");
        postProgressOnActivity(values);
    }

    private void postProgressOnActivity(Object o){
        ((GameActivity) mActivity).objectReseauRecu(o);
    }

    public synchronized void send(Object o){
        if(output != null){
            try {
                output.writeObject(o);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
