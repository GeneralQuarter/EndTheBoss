package virus.endtheboss.Controleur;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import virus.endtheboss.Modele.Capacites.Chaine;
import virus.endtheboss.Modele.Capacites.ChaineSombre;
import virus.endtheboss.Modele.Capacites.ContactMortel;
import virus.endtheboss.Modele.Capacites.DeplacementAffect;
import virus.endtheboss.Modele.Capacites.FireBall;
import virus.endtheboss.Modele.Capacites.Ignite;
import virus.endtheboss.Modele.Capacites.Meteore;
import virus.endtheboss.Modele.Capacites.RenfortDegat;
import virus.endtheboss.Modele.Capacites.Seisme;
import virus.endtheboss.Modele.Capacites.SoinCible;
import virus.endtheboss.Modele.Capacites.TeleportationUrgence;
import virus.endtheboss.Modele.Capacites.Tornade;
import virus.endtheboss.Modele.Capacites.TransfomationMineur;
import virus.endtheboss.Modele.Capacites.VolDeVie;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.Sorcier;
import virus.endtheboss.Vue.GameSurface;
import virus.endtheboss.Vue.PersonnageVue;

/**
 * Created by Valentin on 16/02/2016.
 */
public class SocierControleur extends PersonnageControleur{
    public SocierControleur(Context mContext, GameSurface gs, Carte c) {
        super(mContext, gs, c);
    }
    @Override
    protected void setPersonnage() {
        this.p = new Sorcier();
        Random rand = new Random();
        List<Integer> listeSortInt = new ArrayList<>();
        while(listeSortInt.size() < 4){
            switch (rand.nextInt(10)+1){
                case 1:
                    if(!listeSortInt.contains(1)){
                        listeSortInt.add(1);
                        p.ajouterCapacite(new ChaineSombre((Sorcier) p, c));
                    }
                    break;
                case 2:
                    if(!listeSortInt.contains(2)){
                        listeSortInt.add(2);
                        p.ajouterCapacite(new FireBall((Sorcier) p, c));
                    }
                    break;
                case 3:
                    if(!listeSortInt.contains(3)){
                        listeSortInt.add(3);
                        p.ajouterCapacite(new ContactMortel((Sorcier) p, c));
                    }
                    break;
                case 4:
                    if(!listeSortInt.contains(4)){
                        listeSortInt.add(4);
                        p.ajouterCapacite(new Meteore((Sorcier) p, c));
                    }
                    break;
                case 5:
                    if(!listeSortInt.contains(5)){
                        listeSortInt.add(5);
                        p.ajouterCapacite(new Ignite((Sorcier) p, c));
                    }
                    break;
                case 6:
                    if(!listeSortInt.contains(6)){
                        listeSortInt.add(6);
                        p.ajouterCapacite(new TeleportationUrgence((Sorcier) p, c));
                    }
                    break;
                case 7:
                    if(!listeSortInt.contains(7)){
                        listeSortInt.add(7);
                        p.ajouterCapacite(new Tornade((Sorcier) p, c));
                    }
                    break;
                case 8:
                    if(!listeSortInt.contains(8)){
                        listeSortInt.add(8);
                        p.ajouterCapacite(new TransfomationMineur((Sorcier) p, c));
                    }
                    break;
                case 9:
                    if(!listeSortInt.contains(9)){
                        listeSortInt.add(9);
                        p.ajouterCapacite(new VolDeVie((Sorcier) p, c));
                    }
                    break;
                case 10:
                    if(!listeSortInt.contains(10)){
                        listeSortInt.add(10);
                        p.ajouterCapacite(new Seisme((Sorcier) p, c));
                    }
                    break;
            }
        }


        c.placePlayer(p, 2, 1);
    }

    @Override
    protected void setVuePersonnage() {
        this.pv = new PersonnageVue(mContext, p);
        this.gs.layers.add(this.pv);
    }
}
