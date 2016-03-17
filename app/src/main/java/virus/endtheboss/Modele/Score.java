package virus.endtheboss.Modele;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Quentin Gangler on 15/03/2016.
 */
public class Score implements Serializable{
    private String nomGagnant;
    private boolean winner;
    private String tempsJeu;

    private final String SAVEFILE = "scores";

    public Score(String nomGagnant, boolean winner, String tempsJeu) {
        this.nomGagnant = nomGagnant;
        this.winner = winner;
        this.tempsJeu = tempsJeu;
    }

    public String getNomGagnant() {
        return nomGagnant;
    }

    public void setNomGagnant(String nomGagnant) {
        this.nomGagnant = nomGagnant;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public String getTempsJeu() {
        return tempsJeu;
    }

    public void setTempsJeu(String tempsJeu) {
        this.tempsJeu = tempsJeu;
    }
}
