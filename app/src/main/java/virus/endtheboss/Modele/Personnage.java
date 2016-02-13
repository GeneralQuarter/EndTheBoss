package virus.endtheboss.Modele;

import android.support.annotation.RawRes;
import android.util.Log;

import virus.endtheboss.Enumerations.Deplacement;

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

    /**
     * Animation du personnage
     */
    protected @RawRes int idle;
    protected @RawRes int up;
    protected @RawRes int down;
    protected @RawRes int left;
    protected @RawRes int right;

    public Personnage(){
        super();
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

    public @RawRes int getRight() {
        return right;
    }

    public void deplacement(Deplacement unDeplacement){
        Log.i("parsonnage", "Déplacement");
        switch (unDeplacement){
            case GAUCHE: this.x--; break;
            case DROITE: this.x++; break;
            case HAUT: this.y--; break;
            case BAS: this.y++; break;
            default: break;
        }

        this.saVitesse--;
    }

    public void attaqueDeBase(Personnage uneCible){
        uneCible.setSaVitaliteCourante(uneCible.getSaVitaliteCourante() - this.sesDegatDeBase);
    }
}
