package virus.endtheboss.Serveur;

import java.io.ObjectOutputStream;

import virus.endtheboss.Client.Joueur;

/**
 * Created by Quentin Gangler on 19/02/2016.
 * Object qui représente un joueur coté serveur
 */
public class JoueurServeur {

    private ObjectOutputStream output;
    private Joueur joueur;

    public JoueurServeur(Joueur joueur, int id, ObjectOutputStream output) {
        this.joueur = joueur;
        this.joueur.setId(id);
        this.output = output;
    }

    public ObjectOutputStream getOutput() {
        return output;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }
}
