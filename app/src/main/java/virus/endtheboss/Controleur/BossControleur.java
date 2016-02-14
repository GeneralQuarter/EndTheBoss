package virus.endtheboss.Controleur;

import android.content.Context;

import virus.endtheboss.Modele.Boss;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Vue.GameSurface;

/**
 * Created by Quentin Gangler on 13/02/2016.
 */
public class BossControleur extends PersonnageControleur {

    public BossControleur(Context mContext, GameSurface gs, Carte c) {
        super(mContext, gs,  c);
    }

    @Override
    protected void setPersonnage() {
        this.p = new Boss();

        c.placePlayer(p, 10, 10);
    }
}
