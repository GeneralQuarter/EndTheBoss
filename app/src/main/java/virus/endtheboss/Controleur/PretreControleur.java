package virus.endtheboss.Controleur;

import android.content.Context;

import virus.endtheboss.Modele.Capacites.Pretre.Chaine;
import virus.endtheboss.Modele.Capacites.Pretre.DeplacementAffect;
import virus.endtheboss.Modele.Capacites.Pretre.RenfortDegat;
import virus.endtheboss.Modele.Capacites.Pretre.SoinCible;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.Support;
import virus.endtheboss.Vue.GameSurface;
import virus.endtheboss.Vue.PersonnageVue;

/**
 * Created by Valentin on 16/02/2016.
 */
public class PretreControleur extends PersonnageControleur {

    public PretreControleur(Context mContext, GameSurface gs, Carte c, Support p) {
        super(mContext, gs, c, p);

        p.ajouterCapacite(new Chaine((Support) p, c));
        p.ajouterCapacite(new SoinCible((Support) p, c));
        p.ajouterCapacite(new RenfortDegat((Support) p, c));
        p.ajouterCapacite(new DeplacementAffect((Support) p, c));
    }
}
