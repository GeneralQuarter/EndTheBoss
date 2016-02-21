package virus.endtheboss.Serveur;

import java.io.Serializable;

import virus.endtheboss.Client.Joueur;

/**
 * Created by Quentin Gangler on 19/02/2016.
 * Object qui représente un message du ou pour le serveur
 */
public class MessageServeur implements Serializable{

    public enum TypeMessage{
        CONNEXION,
        DEMANDE_JOUEURS_LIST,
        DECONNEXION,
        CHOIX_PERSO,
        LANCEMENT_PARTIE,
        ERR_SERVEUR_PLEIN,
        ERR_NOM_CLIENT_INVALIDE,
        ERR_PARTIE_EN_COURS,
        ERR_PERSO_PRIS,
    }

    private TypeMessage typeMessage;
    private Joueur joueur;

    public MessageServeur(Joueur joueur, TypeMessage typeMessage) {
        this.joueur = joueur;
        this.typeMessage = typeMessage;
    }

    public TypeMessage getTypeMessage() {
        return typeMessage;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    @Override
    public String toString(){
        return typeMessage + " de " + joueur.getNom() + " choix Perso " + joueur.getChoixPerso();
    }
}
