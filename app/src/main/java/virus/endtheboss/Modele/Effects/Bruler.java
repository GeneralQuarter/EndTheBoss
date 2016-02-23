package virus.endtheboss.Modele.Effects;

import android.media.effect.Effect;

import virus.endtheboss.Modele.Personnages.Personnage;

/**
 * Created by Quentin Gangler on 23/02/2016.
 */
public class Bruler extends Effet {

    public Bruler(int dureeTour){
        super("Brule !", dureeTour);
    }

    @Override
    public void appliquerEffet(Personnage p) {
        p.coupPersonnageSansArmure(10);
    }
}
