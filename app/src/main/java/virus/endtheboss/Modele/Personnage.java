package virus.endtheboss.Modele;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.Random;

import virus.endtheboss.Enumerations.Deplacement;
import virus.endtheboss.GameValues;

/**
 * Created by Valentin on 11/02/2016.
 * Classe abstraite de personnage. Définit les caractéristiques de tous
 * les personnages du jeu.
 *
 */
public abstract class Personnage extends Drawable{
    protected int saVitaliteMaximale;
    protected int saVitaliteCourante;
    protected int sonInitiative;
    protected int saResistance;
    protected int saVitesse;
    protected int sesDegatDeBase;

    protected int saPositionX;
    protected int saPositionY;

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

    @Override
    public void draw(Canvas canvas){
        Paint p = new Paint();
        p.setColor(Color.GREEN);
        canvas.drawRect(saPositionX * GameValues.tileWidth+1, saPositionY * GameValues.tileHeight+1, (saPositionX+1)*GameValues.tileWidth, (saPositionY+1)*GameValues.tileHeight, p);
    }

    @Override
    public int getOpacity(){
        return PixelFormat.OPAQUE;
    }

    @Override
    public void setAlpha(int arg0)
    {
    }

    @Override
    public void setColorFilter(ColorFilter arg0)
    {
    }
}
