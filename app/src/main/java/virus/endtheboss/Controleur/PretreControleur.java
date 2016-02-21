package virus.endtheboss.Controleur;

import android.content.Context;

import virus.endtheboss.Modele.Capacites.Pretre.Chaine;
import virus.endtheboss.Modele.Capacites.Pretre.DeplacementAffect;
import virus.endtheboss.Modele.Capacites.Pretre.RenfortDegat;
import virus.endtheboss.Modele.Capacites.Pretre.SoinCible;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.Personnages.Support;
import virus.endtheboss.Vue.GameSurface;

/**
 * Created by Valentin on 16/02/2016.
 * Classe permettant de créer une entité pretre avec des sorts et une vue
 */
public class PretreControleur extends PersonnageControleur {

    public PretreControleur(Context mContext, GameSurface gs, Carte c, Support p) {
        super(mContext, gs, c, p);

        p.ajouterCapacite(new Chaine(c));
        p.ajouterCapacite(new SoinCible(p, c));
        p.ajouterCapacite(new RenfortDegat(c));
        p.ajouterCapacite(new DeplacementAffect(c));
    }
}
