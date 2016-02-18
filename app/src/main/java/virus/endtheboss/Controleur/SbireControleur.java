package virus.endtheboss.Controleur;

import android.content.Context;

import virus.endtheboss.Modele.Capacites.Sbire.AttaqueSimple;
import virus.endtheboss.Modele.Capacites.Sbire.SoinBoss;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.Sbire;
import virus.endtheboss.Vue.GameSurface;
import virus.endtheboss.Vue.PersonnageVueAvecBarreVie;

/**
 * Created by Valentin on 18/02/2016.
 */
public class SbireControleur extends PersonnageControleur {
    public SbireControleur(Context mContext, GameSurface gs, Carte c) {
        super(mContext, gs,  c);
    }

    @Override
    protected void setPersonnage() {
        this.p = new Sbire();

        p.ajouterCapacite(new AttaqueSimple((Sbire) p, c));
        p.ajouterCapacite(new SoinBoss((Sbire) p, c));
        p.ajouterCapacite(new AttaqueSimple((Sbire) p, c));
        p.ajouterCapacite(new SoinBoss((Sbire) p, c));

        c.placePlayer(p, 5, 10);
    }

    @Override
    protected void setVuePersonnage() {
        this.pv = new PersonnageVueAvecBarreVie(mContext, p);
        this.gs.layers.add(this.pv);
    }
}
