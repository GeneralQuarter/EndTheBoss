package virus.endtheboss.Modele;

import android.widget.Switch;

import java.util.Random;

import virus.endtheboss.Enumerations.Deplacement;

/**
 * Created by Valentin on 11/02/2016.
 * Classe abstraite de personnage. Définit les caractéristiques de tous
 * les personnages du jeu.
 */
public abstract class Personnage {
    protected int saVitaliteMaximale;
    protected int saVitaliteCourante;
    protected int sonInitiative;
    protected int saResistance;
    protected int saVitesse;
    protected int sesDegatDeBase;

    protected int saPositionX;
    protected int saPositionY;

    public Personnage(){

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

    public void deplacement(Deplacement unDeplacement){
        switch (unDeplacement){
            case GAUCHE: this.saPositionX--; break;
            case DROITE: this.saPositionX++; break;
            case HAUT: this.saPositionY--; break;
            case BAS: this.saPositionY++; break;
            default: break;
        }

        this.saVitesse--;
    }

    public void attaqueDeBase(Personnage uneCible){
        uneCible.setSaVitaliteCourante(uneCible.getSaVitaliteCourante() - this.sesDegatDeBase);
    }

    
}