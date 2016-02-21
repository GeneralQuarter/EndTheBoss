package virus.endtheboss.Client;

import java.io.Serializable;

/**
 * Created by Quentin Gangler on 18/02/2016.
 * Classe permetant d'nvoyer et de recevoir des objects d'un serveur
 */
public class Joueur implements Serializable{
    private int id;
    private String nom;
    private int choixPerso;

    public Joueur(String nom) {
        this.nom = nom;
        this.id = -1;
        this.choixPerso = -1;
    }

    public Joueur(Joueur joueur){
        this.id = joueur.getId();
        this.choixPerso = joueur.getChoixPerso();
        this.nom = joueur.getNom();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public int getChoixPerso() {
        return choixPerso;
    }

    public void setChoixPerso(int choixPerso) {
        this.choixPerso = choixPerso;
    }
}
