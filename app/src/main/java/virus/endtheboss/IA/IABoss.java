package virus.endtheboss.IA;

import virus.endtheboss.Controleur.BossControleur;
import virus.endtheboss.Modele.Capacites.Boss.FrappeDistance;
import virus.endtheboss.Modele.Capacites.Boss.FrappeZone;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.Personnages.ActionPersonnage;
import virus.endtheboss.Modele.Personnages.Boss;
import virus.endtheboss.Modele.Personnages.Personnage;
import virus.endtheboss.Serveur.GestionServeur;
import virus.endtheboss.Serveur.Serveur;

/**
 * Created by Valentin on 25/02/2016.
 */
public class IABoss extends IA{

    private boolean aBouge;

    public IABoss(Boss boss, Carte carte, Serveur serveur){
        super(boss, carte, serveur);
        aBouge = false;

        personnage.ajouterCapacite(new FrappeZone((Boss) personnage, carte));
        personnage.ajouterCapacite(new FrappeDistance((Boss) personnage, carte));
    }

    @Override
    public void jouerTour() {
        aBouge = false;
        action();
    }

    private void action(){
        //System.out.println("Démarrage action");
        int sort1 = carte.getNombreCibleOptimale(personnage.getCapacite(1), personnage);
        switch(sort1){
            case 0:
                //System.out.println("Aucun ennemi trouvé (Sort 1)");
            case 1:
                //System.out.println("1 ennemi trouvé (Sort 1)");
                int sort2 = carte.getNombreCibleOptimale(personnage.getCapacite(2), personnage);
                switch(sort2) {
                    case 0:
                        //System.out.println("Aucun ennemi trouvé (Sort 2)");
                        CaseCarte cible = carte.getEnemiProche(personnage);
                        if (cible != null) {
                            if(!aBouge) {
                                aBouge = true;
                                //System.out.println("Déplacement...");
                                deplacementVersPersonnage(cible);
                                action();
                            }
                        } break;
                    case 1:
                        if(sort1 != 0) break;
                        //System.out.println("1 ennemi trouvé (Sort 2)");
                    case 2:
                        //System.out.println("2+ ennemi trouvé (Sort 2)");
                    case 3:
                    case 4:
                        //System.out.println("Lancement Sort 2");
                        GestionServeur.sendAll(new ActionPersonnage(personnage.getId(), ActionPersonnage.Action.LANCE_CAPACITE, personnage.getCapacite(2)));
                        personnage.getCapacite(2).lancerSort(carte.getCibleOptimale(personnage.getCapacite(2), personnage));
                        break;

                }
                if(sort2 == 0 || sort2 == 2 || sort2 == 3 || sort2 == 4 || sort1 == 0) break;
            case 2:
                //System.out.println("2+ ennemi trouvé (Sort 1)");
            case 3:
            case 4:
                //System.out.println("Lancement Sort 1");
                GestionServeur.sendAll(new ActionPersonnage(personnage.getId(), ActionPersonnage.Action.LANCE_CAPACITE, personnage.getCapacite(1)));
                personnage.getCapacite(1).lancerSort(personnage);
                break;
        }
    }
}
