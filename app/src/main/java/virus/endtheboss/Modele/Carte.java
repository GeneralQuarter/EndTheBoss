package virus.endtheboss.Modele;

import virus.endtheboss.Enumerations.GameValues;

/**
 * Created by Quentin Gangler on 13/02/2016.
 */
public class Carte {
    private CaseCarte[][] casesCarte;

    public Carte(){
        casesCarte = new CaseCarte[GameValues.nbHorTile][GameValues.nbVerTile];
    }

    public CaseCarte get(int x, int y){
        if(x>0 && x<GameValues.nbHorTile && y>0 && y<GameValues.nbVerTile){
            return casesCarte[y][x];
        }
        return null;
    }

    public void set(CaseCarte cc){
        if(cc.getX()>0 && cc.getX()<GameValues.nbHorTile && cc.getY()>0 && cc.getY()<GameValues.nbVerTile){
            casesCarte[cc.getY()][cc.getX()] = cc;
        }
    }

    public void placePlayer(Personnage p, int x, int y){
        casesCarte[y][x] = p;
        p.setX(x);
        p.setY(y);
    }

    public boolean deplacerPersonnage(Personnage p, int xOffset, int yOffset){
        if((Personnage) casesCarte[p.getY()][p.getX()] == p) {
            casesCarte[p.getY()][p.getX()] = null;
            p.setX(p.getX() + xOffset);
            p.setY(p.getY() + yOffset);
            casesCarte[p.getY()][p.getX()] = p;
            return true;
        }else{
            return false;
        }
    }
}