package virus.endtheboss.Controleur;

import android.content.Context;

import virus.endtheboss.Modele.Boss;
import virus.endtheboss.Modele.Capacites.Boss.FrappeDistance;
import virus.endtheboss.Modele.Capacites.Boss.FrappeZone;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Vue.GameSurface;
import virus.endtheboss.Vue.PersonnageVueAvecBarreVie;

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

        p.ajouterCapacite(new FrappeZone((Boss) p, c));
        p.ajouterCapacite(new FrappeDistance((Boss) p, c));
        p.ajouterCapacite(new FrappeZone((Boss) p, c));
        p.ajouterCapacite(new FrappeDistance((Boss) p, c));

        c.placePlayer(p, 10, 10);
    }

    @Override
    protected void setVuePersonnage() {
        this.pv = new PersonnageVueAvecBarreVie(mContext, p);
        this.gs.layers.add(this.pv);
    }
}
