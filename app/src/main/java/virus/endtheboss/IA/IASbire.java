package virus.endtheboss.IA;

import virus.endtheboss.Modele.Capacites.Sbire.AttaqueSimple;
import virus.endtheboss.Modele.Capacites.Sbire.SoinBoss;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Personnages.ActionPersonnage;
import virus.endtheboss.Modele.Personnages.Boss;
import virus.endtheboss.Modele.Personnages.Sbire;
import virus.endtheboss.Serveur.Serveur;

/**
 * Created by Valentin on 03/03/2016.
 */
public class IASbire extends IA {

    public IASbire(Sbire sbire, Carte carte, Serveur serveur){
        super(sbire, carte, serveur);
        personnage.ajouterCapacite(new AttaqueSimple((Sbire)personnage,carte));
        personnage.ajouterCapacite(new SoinBoss((Sbire) personnage , carte));
    }

    @Override
    public void jouerTour() {
        Boss boss = (Boss) serveur.getEntite(666);
        CaseCarte cible = carte.getEnemiProche(personnage);
        if(cible != null)
            deplacementVersPersonnage(cible);
        if(boss != null && boss.getSaVitaliteCourante()<100){
            serveur.sendAll(new ActionPersonnage(personnage.getId(), ActionPersonnage.Action.LANCE_CAPACITE, personnage.getCapacite(2)));
            personnage.getCapacite(2).lancerSort(boss);
        }else if(carte.getNombreCibleOptimale(personnage.getCapacite(1),personnage) >= 1){
            serveur.sendAll(new ActionPersonnage(personnage.getId(), ActionPersonnage.Action.LANCE_CAPACITE, personnage.getCapacite(1)));
            personnage.getCapacite(1).lancerSort(carte.getCibleOptimale(personnage.getCapacite(1), personnage));
        }
    }

    public int getIDSbire(){
        return personnage.getId();
    }

}
