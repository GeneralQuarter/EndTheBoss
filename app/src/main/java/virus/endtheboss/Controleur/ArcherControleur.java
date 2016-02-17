package virus.endtheboss.Controleur;

import android.content.Context;

import virus.endtheboss.Modele.Archer;
import virus.endtheboss.Modele.Capacites.Archer.EntrainementArc;
import virus.endtheboss.Modele.Capacites.Archer.PluieFleche;
import virus.endtheboss.Modele.Capacites.Archer.Saut;
import virus.endtheboss.Modele.Capacites.Archer.TirArc;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Vue.GameSurface;
import virus.endtheboss.Vue.PersonnageVue;

/**
 * Created by Quentin Gangler on 13/02/2016.
 */
public class ArcherControleur extends PersonnageControleur {

    public ArcherControleur(Context mContext, GameSurface gs, Carte c) {
        super(mContext, gs, c);
    }

    @Override
    protected void setPersonnage() {
        this.p = new Archer();

        p.ajouterCapacite(new TirArc((Archer) p, c));
        p.ajouterCapacite(new PluieFleche((Archer) p, c));
        p.ajouterCapacite(new Saut((Archer) p, c));
        p.ajouterCapacite(new EntrainementArc((Archer) p, c));

        c.placePlayer(p, 2, 1);
    }

    @Override
    protected void setVuePersonnage() {
        this.pv = new PersonnageVue(mContext, p);
        this.gs.layers.add(this.pv);
    }
}
