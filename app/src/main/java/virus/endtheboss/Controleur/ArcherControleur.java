package virus.endtheboss.Controleur;

import android.content.Context;

import virus.endtheboss.Modele.Archer;
import virus.endtheboss.Modele.Capacites.Archer.EntrainementArc;
import virus.endtheboss.Modele.Capacites.Archer.PluieFleche;
import virus.endtheboss.Modele.Capacites.Archer.Saut;
import virus.endtheboss.Modele.Capacites.Archer.TirArc;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.Personnage;
import virus.endtheboss.Vue.GameSurface;
import virus.endtheboss.Vue.PersonnageVue;

/**
 * Created by Quentin Gangler on 13/02/2016.
 */
public class ArcherControleur extends PersonnageControleur {

    public ArcherControleur(Context mContext, GameSurface gs, Carte c, Archer archer) {
        super(mContext, gs, c, archer);

        p.ajouterCapacite(new TirArc((Archer) p, c));
        p.ajouterCapacite(new PluieFleche((Archer) p, c));
        p.ajouterCapacite(new Saut((Archer) p, c));
        p.ajouterCapacite(new EntrainementArc((Archer) p, c));

    }
}
