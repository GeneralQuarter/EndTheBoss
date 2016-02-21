package virus.endtheboss.Controleur;

import android.content.Context;

import virus.endtheboss.Modele.Capacites.Tank.CoupEpee;
import virus.endtheboss.Modele.Capacites.Tank.Grappin;
import virus.endtheboss.Modele.Capacites.Tank.Provocation;
import virus.endtheboss.Modele.Capacites.Tank.SoinPersonnel;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.Personnages.Tank;
import virus.endtheboss.Vue.GameSurface;

/**
 * Created by Quentin Gangler on 14/02/2016.
 * Classe permettant de créer une entité tank avec des sorts et une vue
 */
public class TankControleur extends PersonnageControleur {

    public TankControleur(Context mContext, GameSurface gs, Carte c, Tank tank) {
        super(mContext, gs, c, tank);

        this.p.ajouterCapacite(new CoupEpee((Tank) p, c));
        this.p.ajouterCapacite(new Grappin((Tank) p, c));
        this.p.ajouterCapacite(new Provocation(c));
        this.p.ajouterCapacite(new SoinPersonnel((Tank) p, c));
    }
}
