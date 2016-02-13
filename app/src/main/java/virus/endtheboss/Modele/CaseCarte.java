package virus.endtheboss.Modele;

/**
 * Created by Quentin Gangler on 13/02/2016.
 */
public abstract class CaseCarte {
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
