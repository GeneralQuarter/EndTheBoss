package virus.endtheboss.Modele;

import java.io.Serializable;

/**
 * Created by Quentin Gangler on 13/02/2016.
 * Classe qui représente une case de la carte
 */
public abstract class CaseCarte implements Serializable{
    protected int x;
    protected int y;

    public CaseCarte(){}

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
