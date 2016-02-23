package virus.endtheboss.Modele.Personnages;

import java.io.Serializable;

/**
 * Created by Quentin Gangler on 20/02/2016.
 * Matérialise une action d'un personnage (qui peux transiter par réseau)
 */
public class ActionPersonnage implements Serializable{

    public enum Action{
        DEBUT_TOUR,
        DEPLACEMENT,
        EFFET,
        CHANGE_RESISTANCE,
        CHANGE_DEGAT,
        CHANGE_VITESSE,
        TRANSPORT,
        SOIN,
        MORT,
        DEGAT_AVEC_ARMURE,
        DEGAT_SANS_ARMURE,
        FIN_TOUR
    }

    private int personnageID;
    private Action action;
    private Object value;

    public ActionPersonnage(int personnageID, Action action, Object value){
        this.personnageID = personnageID;
        this.action = action;
        this.value = value;
    }

    public ActionPersonnage(ActionPersonnage ap){
        this.personnageID = ap.getPersonnageID();
        this.action = ap.getAction();
        this.value = ap.getValue();
    }

    public int getPersonnageID() {
        return personnageID;
    }

    public Action getAction() {
        return action;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString(){
        return "Action " + action.toString() + " de " + personnageID + " avec value " + value;
    }
}
