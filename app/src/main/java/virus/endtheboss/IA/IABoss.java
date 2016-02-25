package virus.endtheboss.IA;

import virus.endtheboss.Controleur.BossControleur;
import virus.endtheboss.Modele.Personnages.Boss;

/**
 * Created by Valentin on 25/02/2016.
 */
public class IABoss {
    private BossControleur sonBossControleur;

    public IABoss(BossControleur unBossControleur){
        this.sonBossControleur = unBossControleur;
    }
}
