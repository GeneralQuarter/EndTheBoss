package virus.endtheboss.Controleur;

import android.content.Context;

import virus.endtheboss.Modele.Boss;
import virus.endtheboss.Modele.Capacites.Boss.FrappeDistance;
import virus.endtheboss.Modele.Capacites.Boss.FrappeZone;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.Personnage;
import virus.endtheboss.Vue.GameSurface;
import virus.endtheboss.Vue.PersonnageVue;
import virus.endtheboss.Vue.PersonnageVueAvecBarreVie;

/**
 * Created by Quentin Gangler on 13/02/2016.
 */
public class BossControleur extends PersonnageControleur {

    public BossControleur(Context mContext, GameSurface gs, Carte c, Boss b) {
        super(mContext, gs,  c, b);

        p.ajouterCapacite(new FrappeZone((Boss) p, c));
        p.ajouterCapacite(new FrappeDistance((Boss) p, c));
        p.ajouterCapacite(new FrappeZone((Boss) p, c));
        p.ajouterCapacite(new FrappeDistance((Boss) p, c));
    }
}
