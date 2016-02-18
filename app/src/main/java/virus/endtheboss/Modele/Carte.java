package virus.endtheboss.Modele;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import virus.endtheboss.Enumerations.GameValues;
import virus.endtheboss.Modele.Formes.Forme;
import virus.endtheboss.Vue.GestionReseau;

/**
 * Created by Quentin Gangler on 13/02/2016.
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

    public CaseCarte get(CaseCarte origine){
        if(isCaseValide(origine)){
            return casesCarte[origine.getY()][origine.getX()];
        }
        return null;
    }

    public boolean deplacerPersonnage(Personnage p, int xOffset, int yOffset){
        if((Personnage) casesCarte[p.getY()][p.getX()] == p) {
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

    public boolean transporterPersonnage(Personnage p, int x, int y){
        if(casesCarte[p.getY()][p.getX()] instanceof Personnage){
            if(isCaseVide(new CaseVide(x, y))) {
                casesCarte[p.getY()][p.getX()] = new CaseVide(p.getX(), p.getY());
                p.setX(x);
                p.setY(y);
                casesCarte[p.getY()][p.getX()] = p;
                Log.i("Carte", "Transport de " + p.getSonNom() + " en " + p.getX() + ", " + p.getY());
                return true;
            }else{
                Log.i("Carte", "Transport annulé vers " + casesCarte[y][x]);
                return false;
            }
        }else{
            Log.i("Carte", "Personnage ne correspond pas");
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
        if(isCaseValide(cc)){
            return casesCarte[cc.getY()][cc.getX()] instanceof Personnage;
        }
        return false;
    }

    /**
     * Teste si la case passée en paramètre est une case vide (d'après ces coordonnées)
     * @param cc La case à tester
     * @return true si la case
     */
    public boolean isCaseVide(CaseCarte cc){
        if(isCaseValide(cc)){
            return casesCarte[cc.getY()][cc.getX()] instanceof CaseVide;
        }
        return false;
    }

    public List<Personnage> updateCarte(Carte c){
        List<Personnage> res = new ArrayList<>();
        casesCarte = c.getCasesCarte();

        for(int y = 0; y < GameValues.nbVerTile; y++){
            for(int x = 0; x < GameValues.nbHorTile; x++){
                if(casesCarte[y][x] instanceof Personnage){
                    res.add((Personnage) casesCarte[y][x]);
                }
            }
        }

        return res;
    }

    public CaseCarte[][] getCasesCarte(){
        return casesCarte;
    }
}
