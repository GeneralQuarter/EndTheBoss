package virus.endtheboss.Modele;

import android.support.annotation.RawRes;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import virus.endtheboss.Enumerations.Deplacement;
import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Vue.GestionReseau;

/**
 * Created by Valentin on 11/02/2016.
 * Classe abstraite de personnage. Définit les caractéristiques de tous
 * les personnages du jeu.
 *
 */
public abstract class Personnage extends CaseCarte{
    protected int saVitaliteMaximale;
    protected int saVitaliteCourante;
    protected int sonInitiative;
    protected int saResistance;
    protected int saVitesse;
    protected int sesDegatDeBase;
    protected List<Capacite> capacites;
    protected int capaciteEncours;
    protected String sonNom;

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
        capaciteEncours = -1;
    }

    /**
     * Getter et Setter de la classe personnage.
     */

    public int getSaVitaliteMaximale() {
        return saVitaliteMaximale;
    }

    public void setSaVitaliteMaximale(int saVitalite) {
        this.saVitaliteMaximale = saVitalite;
    }

    public int getSaVitaliteCourante() {
        return saVitaliteCourante;
    }

    public void setSaVitaliteCourante(int saVitalite) {
        this.saVitaliteCourante = saVitalite;
    }

    public int getSonInitiative() {
        return sonInitiative;
    }

    public void setSonInitiative(int sonInitiative) {
        this.sonInitiative = sonInitiative;
    }

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
        if((value - saResistance)> 0){
            if(saVitaliteCourante - (value - saResistance) > 0){
                saVitaliteCourante -= value - saResistance;
            }else{
                saVitaliteCourante = 0;
            }
        }
        Log.i("Valeur du coup", value + " de dégats sur " + sonNom);
        GestionReseau.faiDommagePersonnage(this, value);
    }

    public void coupPersonnageSansArmure(int value){
        if(saVitaliteCourante - value > 0){
            saVitaliteCourante -= value;
        }else{
            saVitaliteCourante = 0;
        }
        Log.i("Valeur du coup", value + " de dégats sur " + sonNom + "(Sans armure)");
        GestionReseau.faitDommagePersonnageSansArmure(this, value);
    }

    public void soignerPersonnage(int value){
        if(saVitaliteCourante+value>saVitaliteMaximale){
            saVitaliteCourante=saVitaliteMaximale;
        }else{
            saVitaliteCourante+=value;
        }
        Log.i("Valeur du soin", value + " de soins sur " + sonNom);
        GestionReseau.soignePersonnage(this, value);
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

    public List<Capacite> getCapacites(){
        return capacites;
    }
}
