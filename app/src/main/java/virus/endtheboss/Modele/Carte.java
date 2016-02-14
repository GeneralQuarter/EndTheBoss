package virus.endtheboss.Modele;

import java.util.ArrayList;
import java.util.List;

import virus.endtheboss.Enumerations.GameValues;
import virus.endtheboss.Modele.Formes.Forme;

/**
 * Created by Quentin Gangler on 13/02/2016.
 */
public class Carte {
    private CaseCarte[][] casesCarte;

    public Carte(){
        casesCarte = new CaseCarte[GameValues.nbHorTile][GameValues.nbVerTile];
    }

    public void placePlayer(Personnage p, int x, int y){
        casesCarte[y][x] = p;
        p.setX(x);
        p.setY(y);
    }

    public CaseCarte get(CaseCarte origine){
        if(checkCase(origine)){
            return casesCarte[origine.getY()][origine.getX()];
        }
        return null;
    }

    public boolean deplacerPersonnage(Personnage p, int xOffset, int yOffset){
        if((Personnage) casesCarte[p.getY()][p.getX()] == p) {
            if(checkCase(new CaseVide(p.getX()+xOffset,p.getY()+yOffset))) {
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
        if((Personnage) casesCarte[p.getY()][p.getX()] == p){
            casesCarte[p.getX()][p.getY()] = new CaseVide(p.getX(), p.getY());
            p.setX(x);
            p.setY(y);
            casesCarte[p.getY()][p.getX()] = p;
            return true;
        }else{
            return false;
        }
    }

    public List<Personnage> getPersonnagesDansForme(Forme f, CaseCarte cible){
        List<Personnage> personnages = new ArrayList<>();
        if(checkCase(cible)) {
            for (CaseCarte cc : f.getForme(cible)) {
                if (checkCase(cc)) {
                    if(casesCarte[cc.getY()][cc.getX()] instanceof Personnage){
                        personnages.add((Personnage) casesCarte[cc.getY()][cc.getX()]);
                    }
                }
            }
        }
        return personnages;
    }

    private boolean checkCase(CaseCarte cc){
        return cc.getX()>=0 && cc.getX()<GameValues.nbVerTile && cc.getY()>=0 && cc.getY()<GameValues.nbHorTile;
    }
}
