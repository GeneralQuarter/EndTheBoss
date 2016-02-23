package virus.endtheboss.Modele.Personnages;

import android.support.annotation.RawRes;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import virus.endtheboss.Client.GestionClient;
import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Effects.Effet;

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
    protected int sesDegatDeBase;
    protected List<Capacite> capacites;
    protected List<Effet> effets;
    protected int capaciteEncours;
    protected String sonNom;
    protected int id;

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

    /*public void setSaVitaliteCourante(int saVitalite) {
        this.saVitaliteCourante = saVitalite;
    }*/

    public int getSonInitiative() {
        return sonInitiative;
    }

    /*public void setSonInitiative(int sonInitiative) {
        this.sonInitiative = sonInitiative;
    }*/

    public int getSaResistance() {
        return saResistance;
    }

    public void setSaResistance(int saResistance) {
        this.saResistance = saResistance;
    }

    public int getSaVitesse() {
        return saVitesse;
    }

    public void setSaVitesse(int saVitesse) {
        this.saVitesse = saVitesse;
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
        this.sesDegatDeBase = sesDegatDeBase;
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
        if((value - saResistance)> 0){
            if(saVitaliteCourante - (value - saResistance) > 0){
                saVitaliteCourante -= value - saResistance;
            }else{
                saVitaliteCourante = 0;
            }
        }
        //Log.i("Valeur du coup", value + " de dégats sur " + sonNom);
        if(send)
            GestionClient.send(new ActionPersonnage(id, ActionPersonnage.Action.DEGAT_AVEC_ARMURE, value));
    }

    public void coupPersonnageSansArmure(int value){
        coupPersonnageSansArmure(value, true);
    }

    public void coupPersonnageSansArmure(int value, boolean send){
        if(saVitaliteCourante - value > 0){
            saVitaliteCourante -= value;
        }else{
            saVitaliteCourante = 0;
        }
        //Log.i("Valeur du coup", value + " de dégats sur " + sonNom + "(Sans armure)");
        if(send)
            GestionClient.send(new ActionPersonnage(id, ActionPersonnage.Action.DEGAT_SANS_ARMURE, value));
    }

    public void soignerPersonnage(int value){
        soignerPersonnage(value, true);
    }

    public void soignerPersonnage(int value, boolean send){
        if(saVitaliteCourante+value>saVitaliteMaximale){
            saVitaliteCourante=saVitaliteMaximale;
        }else{
            saVitaliteCourante+=value;
        }
        //Log.i("Valeur du soin", value + " de soins sur " + sonNom);
        if(send)
            GestionClient.send(new ActionPersonnage(id, ActionPersonnage.Action.SOIN, value));
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

    public void appliquerEffets(){
        for(Effet e : effets){
            if(e.getDureeTour()-1 > 0){
                e.setDureeTour(e.getDureeTour()-1);
                e.appliquerEffet(this);
            }else{
                effets.remove(e);
            }
        }
    }

    public void ajouterEffet(Effet effet, boolean send){
        effets.add(effet);
        if(send)
            GestionClient.send(new ActionPersonnage(id, ActionPersonnage.Action.EFFET, effet));
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
}
