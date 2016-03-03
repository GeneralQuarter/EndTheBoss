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

        for(int pas = 0; pas < personnage.getSaVitesse(); pas++){

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
                    System.out.println("La case suivante est un personnage");
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
                    System.out.println("La case suivante est un personnage");
                }

            try{
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
