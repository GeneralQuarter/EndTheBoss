package virus.endtheboss.Modele;

import java.io.Serializable;

import virus.endtheboss.Enumerations.Action;
import virus.endtheboss.Enumerations.Deplacement;
import virus.endtheboss.PlayerID;

/**
 * Created by Quentin Gangler on 17/02/2016.
 */
public class ActionPersonnage implements Serializable{
    private Action action;
    private PlayerID destinataire;
    private Personnage p;
    private CaseCarte cc;
    private Deplacement d;
    private int value;

    public ActionPersonnage(Action action, Personnage p, int value){
        if(action == Action.DEGAT_AVEC_ARMURE || action == Action.DEGAT_SANS_ARMURE || action == Action.SOIN){
            this.action = action;
            this.p = p;
            this.value = value;
            this.cc = null;
            this.destinataire = null;
            this.d = null;
        }else{
            this.action = null;
            this.p = null;
            this.value = -200;
            this.cc = null;
            this.destinataire = null;
            this.d = null;
        }
    }

    public ActionPersonnage(Action action, PlayerID destinataire){
        if(action == Action.DEBUT_TOUR || action == Action.FIN_TOUR){
            this.action = action;
            this.p = p;
            this.value = -200;
            this.cc = null;
            this.destinataire = destinataire;
            this.d = null;
        }else{
            this.action = null;
            this.p = null;
            this.value = -200;
            this.cc = null;
            this.destinataire = null;
            this.d = null;
        }
    }

    public ActionPersonnage(Action action, Personnage p, Deplacement d){
        if(action == Action.DEPLACEMENT){
            this.action = action;
            this.p = p;
            this.value = -200;
            this.cc = null;
            this.d = d;
        }else{
            this.action = null;
            this.p = null;
            this.value = -200;
            this.cc = null;
            this.destinataire = null;
            this.d = null;
        }
    }

    public Action getAction() {
        return action;
    }

    public Personnage getP() {
        return p;
    }

    public CaseCarte getCc() {
        return cc;
    }

    public int getValue() {
        return value;
    }

    public Deplacement getD() {
        return d;
    }

    public PlayerID getDestinataire() {
        return destinataire;
    }
}
