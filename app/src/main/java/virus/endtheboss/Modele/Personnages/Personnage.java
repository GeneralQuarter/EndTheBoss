package virus.endtheboss.Modele.Personnages;

import android.support.annotation.RawRes;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import virus.endtheboss.Client.GestionClient;
import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Effects.Effet;
import virus.endtheboss.Modele.Formes.FormeEnLosange;
import virus.endtheboss.Serveur.GestionServeur;

/**
 * Created by Valentin on 11/02/2016.
 * Classe abstraite de personnage. Définit les caractéristiques de tous
 * les personnages du jeu.
 *
 */
public abstract class Personnage extends CaseCarte {
    protected int saVitaliteMaximale;
    protected int saVitaliteCourante;
    protected int sonInitiative;
    protected int saResistance;
    protected int saVitesse;
    protected int saVitesseInitiale;
    protected int sesDegatDeBase;
    protected List<Capacite> capacites;
    protected List<Effet> effets;
    protected int capaciteEncours;
    protected String sonNom;
    protected int id;
    boolean mort;

    /**
     * Animation du personnage
     */
    protected @RawRes int idle;
    protected @RawRes int up;
    protected @RawRes int down;
    protected @RawRes int left;
    protected @RawRes int right;

    public Personnage(String sonNom){
        super();
        this.sonNom = sonNom;
        capacites = new ArrayList<>();
        effets = new ArrayList<>();
        capaciteEncours = -1;
        mort = false;
    }

    /**
     * Getter et Setter de la classe personnage.
     */

    public int getSaVitaliteMaximale() {
        return saVitaliteMaximale;
    }

    /*public void setSaVitaliteMaximale(int saVitalite) {
        this.saVitaliteMaximale = saVitalite;
    }*/

    public int getSaVitaliteCourante() {
        return saVitaliteCourante;
    }

    public void setSaVitaliteCourante(int saVitalite) {
        this.saVitaliteCourante = saVitalite;
    }

    public int getSonInitiative() {
        return sonInitiative;
    }

    /*public void setSonInitiative(int sonInitiative) {
        this.sonInitiative = sonInitiative;
    }*/

    public int getSaResistance() {
        return saResistance;
    }

    public int getSaVitesseInitiale() {
        return saVitesseInitiale;
    }

    public void setSaResistance(int saResistance) {
        setSaResistance(saResistance, true);
    }

    public void setSaResistance(int saResistance, boolean send){
        if(saResistance<0)
            this.saResistance=0;
        else
            this.saResistance = saResistance;
        if(send){
            GestionClient.send(new ActionPersonnage(id, ActionPersonnage.Action.CHANGE_RESISTANCE, this.saResistance));
            GestionServeur.sendAll(new ActionPersonnage(id, ActionPersonnage.Action.CHANGE_RESISTANCE, this.saResistance));
        }
    }

    public int getSaVitesse() {
        return saVitesse;
    }

    public void setSaVitesse(int saVitesse) {
        setSaVitesse(saVitesse, true);
    }

    public void setSaVitesse(int saVitesse, boolean send){
        this.saVitesse = saVitesse;
        if(send) {
            GestionClient.send(new ActionPersonnage(id, ActionPersonnage.Action.CHANGE_VITESSE, this.saVitesse));
            GestionServeur.sendAll(new ActionPersonnage(id, ActionPersonnage.Action.CHANGE_VITESSE, this.saVitesse));
        }
    }

    public void resetVitesse(){
        saVitesse = saVitesseInitiale;
    }

    public int getCapaciteEncours() {
        return capaciteEncours;
    }

    public void setCapaciteEncours(int capaciteEncours) {
        this.capaciteEncours = capaciteEncours;
    }

    public @RawRes int getIdle() {
        return idle;
    }

    public @RawRes int getUp() {
        return up;
    }

    public @RawRes int getDown() {
        return down;
    }

    public @RawRes int getLeft() {
        return left;
    }

    public int getSesDegatDeBase() {
        return sesDegatDeBase;
    }

    public void setSesDegatDeBase(int sesDegatDeBase) {
        setSesDegatDeBase(sesDegatDeBase, true);
    }

    public void setSesDegatDeBase(int sesDegatDeBase, boolean send){
        this.sesDegatDeBase = sesDegatDeBase;
        if(send) {
            GestionClient.send(new ActionPersonnage(id, ActionPersonnage.Action.CHANGE_DEGAT, this.sesDegatDeBase));
            GestionServeur.sendAll(new ActionPersonnage(id, ActionPersonnage.Action.CHANGE_DEGAT, this.sesDegatDeBase));
        }
    }

    public @RawRes int getRight() {
        return right;

    }

    public String getSonNom() {
        return sonNom;
    }

    public void setSonNom(String sonNom) {
        this.sonNom = sonNom;
    }

    public void coupPersonnage(int value){
       coupPersonnage(value, true);
    }

    public void coupPersonnage(int value, boolean send){
        System.out.println("Vie de " + sonNom + " qui va se prendre un coup : " + saVitaliteCourante + " de valeur " + value + " (Armure)" + " résitance : " + saResistance);
        if((value - saResistance)> 0){
            if(saVitaliteCourante - (value - saResistance) > 0){
                saVitaliteCourante -= value - saResistance;
            }else{
                saVitaliteCourante = 0;
                if(send) {
                    GestionClient.send(new ActionPersonnage(id, ActionPersonnage.Action.MORT, null));
                    GestionServeur.sendToLocal(new ActionPersonnage(id, ActionPersonnage.Action.MORT, null));
                    send = false;
                }
            }
        }

        if (this instanceof Tank) {
            if (saResistance < 20) {
                saResistance++;
                if (saResistance < 8)
                    if(!capacites.isEmpty())
                        capacites.get(1).setSaPortee(new FormeEnLosange(saResistance));
            }
        }

        //Log.i("Valeur du coup", value + " de dégats sur " + sonNom);
        if(send) {
            GestionClient.send(new ActionPersonnage(id, ActionPersonnage.Action.DEGAT_AVEC_ARMURE, value));
            GestionServeur.sendAll(new ActionPersonnage(id, ActionPersonnage.Action.DEGAT_AVEC_ARMURE, value));
        }
    }

    public void coupPersonnageSansArmure(int value){
        coupPersonnageSansArmure(value, true);
    }

    public void coupPersonnageSansArmure(int value, boolean send){
        System.out.println("Vie de " + sonNom + " qui va se prendre un coup : " + saVitaliteCourante + " de valeur " + value + "(Sans armure) résitance : " + saResistance);
        if(saVitaliteCourante - value > 0){
            saVitaliteCourante -= value;
        }else{
            saVitaliteCourante = 0;
            if(send) {
                GestionClient.send(new ActionPersonnage(id, ActionPersonnage.Action.MORT, null));
                GestionServeur.sendToLocal(new ActionPersonnage(id, ActionPersonnage.Action.MORT, null));
            }
        }

        if(this instanceof Tank) {
            if (saResistance < 35) {
                saResistance+=2;
                if (saResistance < 8)
                    if(!capacites.isEmpty())
                        capacites.get(1).setSaPortee(new FormeEnLosange(saResistance));
            }
        }

        //Log.i("Valeur du coup", value + " de dégats sur " + sonNom + "(Sans armure)");
        if(send) {
            GestionClient.send(new ActionPersonnage(id, ActionPersonnage.Action.DEGAT_SANS_ARMURE, value));
            GestionServeur.sendAll(new ActionPersonnage(id, ActionPersonnage.Action.DEGAT_SANS_ARMURE, value));
        }
    }

    public void soignerPersonnage(int value){
        soignerPersonnage(value, true);
    }

    public void soignerPersonnage(int value, boolean send){
        System.out.println("Soin de " + sonNom + " avec valeur " + value + " vie avant soin : " + saVitaliteCourante + " résitance : " + saResistance);
        if(saVitaliteCourante+value>saVitaliteMaximale){
            saVitaliteCourante=saVitaliteMaximale;
        }else{
            saVitaliteCourante+=value;
        }
        //Log.i("Valeur du soin", value + " de soins sur " + sonNom);
        if(send) {
            GestionClient.send(new ActionPersonnage(id, ActionPersonnage.Action.SOIN, value));
            GestionServeur.sendAll(new ActionPersonnage(id, ActionPersonnage.Action.SOIN, value));
        }
    }

    public void ajouterCapacite(Capacite c){
        if(!capacites.contains(c))
            capacites.add(c);
    }

    public Capacite getCapacite(int numeroCapacite){
        if(numeroCapacite > 0 && numeroCapacite <= 4){
            return capacites.get(numeroCapacite-1);
        }
        return null;
    }

    public synchronized void appliquerEffets(){
        for(Effet e : effets){
            if(e.getDureeTour()-1 > 0){
                e.setDureeTour(e.getDureeTour()-1);
                e.appliquerEffet(this);
            }
        }
    }

    public void ajouterEffet(Effet effet, boolean send){
        effets.add(effet);
        if(send) {
            GestionClient.send(new ActionPersonnage(id, ActionPersonnage.Action.EFFET, effet));
            GestionServeur.sendAll(new ActionPersonnage(id, ActionPersonnage.Action.EFFET, effet));
        }
    }

    public void ajouterEffet(Effet effet){
        ajouterEffet(effet, true);
    }

    public List<Capacite> getCapacites(){
        return capacites;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "Personnage " + sonNom + " : \nVie " + saVitaliteCourante + "\nRésistance " + saResistance;
    }

    public boolean isMort() {
        return mort;
    }

    public void setMort(boolean mort) {
        this.mort = mort;
    }
}
