package virus.endtheboss.Controleur;

import android.content.Context;

import virus.endtheboss.Modele.Capacites.Tank.CoupEpee;
import virus.endtheboss.Modele.Capacites.Tank.Grappin;
import virus.endtheboss.Modele.Capacites.Tank.Provocation;
import virus.endtheboss.Modele.Capacites.Tank.SoinPersonnel;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.Tank;
import virus.endtheboss.Vue.GameSurface;
import virus.endtheboss.Vue.PersonnageVue;

/**
 * Created by Quentin Gangler on 14/02/2016.
 */
public class TankControleur extends PersonnageControleur {

    public TankControleur(Context mContext, GameSurface gs, Carte c) {
        super(mContext, gs, c);
    }

    @Override
    protected void setPersonnage() {
        this.p = new Tank();

        this.p.ajouterCapacite(new CoupEpee((Tank) p, c));
        this.p.ajouterCapacite(new Grappin((Tank) p, c));
        this.p.ajouterCapacite(new Provocation((Tank) p, c));
        this.p.ajouterCapacite(new SoinPersonnel((Tank) p, c));

        c.placePlayer(p, 2, 1);
    }

    @Override
    protected void setVuePersonnage() {
        this.pv = new PersonnageVue(mContext, p);
        this.gs.layers.add(this.pv);
    }
}
