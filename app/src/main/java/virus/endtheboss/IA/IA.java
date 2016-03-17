package virus.endtheboss.IA;

import virus.endtheboss.Enumerations.Deplacement;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.CaseCarte;
import virus.endtheboss.Modele.CaseVide;
import virus.endtheboss.Modele.Personnages.ActionPersonnage;
import virus.endtheboss.Modele.Personnages.Personnage;
import virus.endtheboss.Serveur.GestionServeur;
import virus.endtheboss.Serveur.Serveur;

/**
 * Created by Quentin Gangler on 25/02/2016.
 */
public abstract class IA {

    protected Personnage personnage;

    protected Carte carte;

    protected Serveur serveur;

    public IA(Personnage p, Carte carte, Serveur serveur){
        this.personnage = p;
        this.carte = new Carte(carte);
        this.serveur = serveur;
    }

    public abstract void jouerTour();

    protected void deplacementVersPersonnage(CaseCarte cc){
        int yOffset = 0;
        int xOffset = 0;
        boolean sleep;

        for(int pas = 0; pas < personnage.getSaVitesse(); pas++){
            sleep = true;

            if(personnage.getY() > cc.getY())
                yOffset = -1;
            else if(personnage.getY() < cc.getY())
                yOffset = 1;
            if(personnage.getX() > cc.getX())
                xOffset = -1;
            if(personnage.getX() < cc.getX())
                xOffset = 1;

            if(Math.abs(personnage.getX() - cc.getX()) >= Math.abs(personnage.getY() - cc.getY()))
                if(!(carte.get(new CaseVide(personnage.getX()+xOffset, personnage.getY())) instanceof Personnage)) {
                    if(carte.deplacerPersonnage(personnage, xOffset, 0)) {
                        if (xOffset == 1)
                            serveur.sendAll(new ActionPersonnage(personnage.getId(), ActionPersonnage.Action.DEPLACEMENT, Deplacement.DROITE));
                        else
                            serveur.sendAll(new ActionPersonnage(personnage.getId(), ActionPersonnage.Action.DEPLACEMENT, Deplacement.GAUCHE));
                    }
                }else{
                    if(cc.getX() != personnage.getX()+xOffset && cc.getY() != personnage.getY()) {
                        System.out.println("La case suivante est un personnage mais pas la cible. Recherche d'une case vide adjacente...");
                        if (!(carte.get(new CaseVide(personnage.getX(), personnage.getY() + 1)) instanceof Personnage)) {
                            if (carte.deplacerPersonnage(personnage, 0, 1))
                                serveur.sendAll(new ActionPersonnage(personnage.getId(), ActionPersonnage.Action.DEPLACEMENT, Deplacement.BAS));
                        } else if (!(carte.get(new CaseVide(personnage.getX(), personnage.getY() - 1)) instanceof Personnage)) {
                            if (carte.deplacerPersonnage(personnage, 0, -1))
                                serveur.sendAll(new ActionPersonnage(personnage.getId(), ActionPersonnage.Action.DEPLACEMENT, Deplacement.HAUT));
                        } else {
                            sleep = false;
                        }
                    }
                }

            else if(Math.abs(personnage.getX()-cc.getX()) < Math.abs(personnage.getY()-cc.getY()))
                if(!(carte.get(new CaseVide(personnage.getX(), personnage.getY()+yOffset)) instanceof Personnage)) {
                    if(carte.deplacerPersonnage(personnage, 0, yOffset)){
                        if(yOffset == 1)
                            serveur.sendAll(new ActionPersonnage(personnage.getId(), ActionPersonnage.Action.DEPLACEMENT, Deplacement.BAS));
                        else
                            serveur.sendAll(new ActionPersonnage(personnage.getId(), ActionPersonnage.Action.DEPLACEMENT, Deplacement.HAUT));
                    }
                }else{
                    if(cc.getY() != personnage.getY()+yOffset && cc.getX() != personnage.getX()) {
                        System.out.println("La case suivante est un personnage mais pas la cible. Recherche d'une case vide adjacente...");
                        if (!(carte.get(new CaseVide(personnage.getX() + 1, personnage.getY())) instanceof Personnage)) {
                            if (carte.deplacerPersonnage(personnage, 1, 0))
                                serveur.sendAll(new ActionPersonnage(personnage.getId(), ActionPersonnage.Action.DEPLACEMENT, Deplacement.DROITE));
                        } else if (!(carte.get(new CaseVide(personnage.getX() - 1, personnage.getY())) instanceof Personnage)) {
                            if (carte.deplacerPersonnage(personnage, -1, 0))
                                serveur.sendAll(new ActionPersonnage(personnage.getId(), ActionPersonnage.Action.DEPLACEMENT, Deplacement.GAUCHE));
                        } else {
                            sleep = false;
                        }
                    }
                }

            if(sleep) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
