package virus.endtheboss.Modele;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import virus.endtheboss.Client.GestionClient;
import virus.endtheboss.Enumerations.GameValues;
import virus.endtheboss.Modele.Capacites.Capacite;
import virus.endtheboss.Modele.Formes.Forme;
import virus.endtheboss.Modele.Formes.FormeCase;
import virus.endtheboss.Modele.Formes.FormeEnLosange;
import virus.endtheboss.Modele.Formes.FormeTous;
import virus.endtheboss.Modele.Personnages.ActionPersonnage;
import virus.endtheboss.Modele.Personnages.Boss;
import virus.endtheboss.Modele.Personnages.Personnage;
import virus.endtheboss.Modele.Personnages.Sbire;

/**
 * Created by Quentin Gangler on 13/02/2016.
 * Object qui représente un carte du jeu
 */
public class Carte implements Serializable{
    private CaseCarte[][] casesCarte;

    public Carte(){
        casesCarte = new CaseCarte[GameValues.nbHorTile][GameValues.nbVerTile];

        for(int y = 0; y < GameValues.nbVerTile; y++){
            for(int x = 0; x < GameValues.nbHorTile; x++){
                casesCarte[y][x] = new CaseVide(x, y);
            }
        }
    }

    //A enlever ou modifier !!
    public void placePlayer(Personnage p, int x, int y){
        casesCarte[y][x] = p;
        p.setX(x);
        p.setY(y);
    }

    public Personnage placerPersonnageAleatoirement(Personnage p){
        Random rand = new Random();
        int x, y;
        x = rand.nextInt(20);
        y = rand.nextInt(20);
        while(!(casesCarte[y][x] instanceof CaseVide)){
            x = rand.nextInt(20);
            y = rand.nextInt(20);
        }
        placePlayer(p, x, y);
        return p;
    }

    public CaseCarte get(CaseCarte origine){
        if(isCaseValide(origine)){
            return casesCarte[origine.getY()][origine.getX()];
        }
        return null;
    }

    public void emptyCase(CaseCarte cc){
        if(isCaseValide(cc)){
            casesCarte[cc.getY()][cc.getX()] = new CaseVide(cc.getX(), cc.getY());
        }
    }

    public boolean deplacerPersonnage(Personnage p, int xOffset, int yOffset){
        if(casesCarte[p.getY()][p.getX()] == p) {
            if(isCaseVide(new CaseVide(p.getX() + xOffset, p.getY() + yOffset))) {
                casesCarte[p.getY()][p.getX()] = new CaseVide(p.getX(), p.getY());
                p.setX(p.getX() + xOffset);
                p.setY(p.getY() + yOffset);
                casesCarte[p.getY()][p.getX()] = p;
                return true;
            }else {
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean transporterPersonnage(Personnage p, int x, int y, boolean send){
        if(casesCarte[p.getY()][p.getX()] instanceof Personnage){
            if(isCaseVide(new CaseVide(x, y))) {
                casesCarte[p.getY()][p.getX()] = new CaseVide(p.getX(), p.getY());
                p.setX(x);
                p.setY(y);
                casesCarte[p.getY()][p.getX()] = p;
                //Log.i("Carte", "Transport de " + p.getSonNom() + " en " + p.getX() + ", " + p.getY());
                if(send)
                    GestionClient.send(new ActionPersonnage(p.getId(), ActionPersonnage.Action.TRANSPORT, new CaseVide(x, y)));
                return true;
            }else{
                //Log.i("Carte", "Transport annulé vers " + casesCarte[y][x]);
                return false;
            }
        }else{
            //Log.i("Carte", "Personnage ne correspond pas");
            return false;
        }
    }

    public List<Personnage> getPersonnagesDansForme(Forme f, CaseCarte cible){
        List<Personnage> personnages = new ArrayList<>();
        if(isCaseValide(cible)) {
            for (CaseCarte cc : f.getForme(cible)) {
                if(isCasePersonnage(cc)){
                    personnages.add((Personnage) casesCarte[cc.getY()][cc.getX()]);
                }
            }
        }
        return personnages;
    }

    /**
     * Teste si la case passée en paramètre est bien sur la carte (d'après ces coordonnées)
     * @param cc La case a tester
     * @return true si la case est présente sur la carte
     */
    public boolean isCaseValide(CaseCarte cc){
        return cc.getX()>=0 && cc.getX()<GameValues.nbVerTile && cc.getY()>=0 && cc.getY()<GameValues.nbHorTile;
    }

    /**
     * Teste si la case passée en paramètre contient un personnage (d'après ces coordonnées)
     * @param cc La case à tester
     * @return true si la case contient un personnage
     */
    public boolean isCasePersonnage(CaseCarte cc){
        return isCaseValide(cc) && casesCarte[cc.getY()][cc.getX()] instanceof Personnage;
    }

    /**
     * Teste si la case passée en paramètre est une case vide (d'après ces coordonnées)
     * @param cc La case à tester
     * @return true si la case
     */
    public boolean isCaseVide(CaseCarte cc){
        return isCaseValide(cc) && casesCarte[cc.getY()][cc.getX()] instanceof CaseVide;
    }

    /**
     * Recherche l'enemi le plus proche du point de vue des IA.
     * @param unePosition
     * @return La cible qui n'est ni un sbire, ni un boss.
     */
    public Personnage getEnemiProche(CaseCarte unePosition){
        Personnage cible = null;
        int tailleZone = 1;
        while(cible == null && tailleZone < 50){
            List<Personnage> ciblesPotentielles = this.getPersonnagesDansForme(new FormeEnLosange(tailleZone), unePosition);
            if(ciblesPotentielles.size() == 1){
                if(!(ciblesPotentielles.get(0) instanceof Sbire) && !(ciblesPotentielles.get(0) instanceof Boss))
                    cible = ciblesPotentielles.get(0);
            }else if(ciblesPotentielles.size() > 1){
                if(!(ciblesPotentielles.get(0) instanceof Sbire) && !(ciblesPotentielles.get(0) instanceof Boss))
                    cible = ciblesPotentielles.get(0);
                for(Personnage p : ciblesPotentielles){
                    if(p.getSaVitaliteCourante() < cible.getSaVitaliteCourante()){
                        if(!(p instanceof Sbire) && !(ciblesPotentielles.get(0) instanceof Boss))
                            cible = p;
                    }
                }
            }else{
                tailleZone++;
            }
        }

        return cible;
    }

    /**
     * Recherche l'enemi le plus faible du point de vue des IA
     * @param unePosition
     * @return La cible qui n'est ni un sbire ni un boss.
     */
    public Personnage getEnemiFaible(CaseCarte unePosition){
        Personnage cible = null;
        List<Personnage> ciblesPotentielles = this.getPersonnagesDansForme(new FormeTous(), unePosition);
        if(!(ciblesPotentielles.get(0) instanceof Sbire) && !(ciblesPotentielles.get(0) instanceof Boss))
            cible = ciblesPotentielles.get(0);
        for(Personnage p : ciblesPotentielles){
            if(p.getSaVitaliteCourante() < cible.getSaVitaliteCourante()){
                if(!(p instanceof Sbire) && !(ciblesPotentielles.get(0) instanceof Boss))
                    cible = p;
            }
        }
        return cible;
    }

    public List<CaseCarte> getCasesDansForme(Forme f, CaseCarte cible){
        List<CaseCarte> cases = new ArrayList<>();
        if(isCaseValide(cible)) {
            for (CaseCarte cc : f.getForme(cible)) {
                cases.add((Personnage) casesCarte[cc.getY()][cc.getX()]);
            }
        }
        return cases;
    }

    public CaseCarte getCibleOptimale(Capacite uneCapacite, CaseCarte unePosition){
        List<Personnage> ciblesOptimales = new ArrayList<>();
        List<Personnage> ciblesPotentielles = new ArrayList<>();
        CaseCarte caseOptimale;
        List<CaseCarte> caseCiblables = this.getCasesDansForme(uneCapacite.getSaPortee(), unePosition);
        for(Personnage p : this.getPersonnagesDansForme(uneCapacite.getSonImpact(), caseCiblables.get(0))){
            if (!(p instanceof Boss) && !(p instanceof Sbire)){
                ciblesOptimales.add(p);
            }
        }
        caseOptimale = caseCiblables.get(0);
        if(!(uneCapacite.getSaPortee() instanceof FormeCase))
            for(CaseCarte c : caseCiblables){
                for(Personnage p : this.getPersonnagesDansForme(uneCapacite.getSonImpact(), c)) {
                    if (!(p instanceof Boss) && !(p instanceof Sbire)) {
                        ciblesPotentielles.add(p);
                    }
                }
                if(ciblesOptimales.size() < ciblesPotentielles.size()){
                    ciblesOptimales = ciblesPotentielles;
                    caseOptimale = c;
                }
            }
        return caseOptimale;
    }
}
