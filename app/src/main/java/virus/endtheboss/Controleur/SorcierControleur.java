package virus.endtheboss.Controleur;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import virus.endtheboss.Modele.Capacites.Sorcier.ChaineSombre;
import virus.endtheboss.Modele.Capacites.Sorcier.ContactMortel;
import virus.endtheboss.Modele.Capacites.Sorcier.FireBall;
import virus.endtheboss.Modele.Capacites.Sorcier.Ignite;
import virus.endtheboss.Modele.Capacites.Sorcier.Meteore;
import virus.endtheboss.Modele.Capacites.Sorcier.Seisme;
import virus.endtheboss.Modele.Capacites.Sorcier.TeleportationUrgence;
import virus.endtheboss.Modele.Capacites.Sorcier.Tornade;
import virus.endtheboss.Modele.Capacites.Sorcier.TransfomationMineure;
import virus.endtheboss.Modele.Capacites.Sorcier.VolDeVie;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.Personnages.Sorcier;
import virus.endtheboss.Vue.GameSurface;

/**
 * Created by Valentin on 16/02/2016.
 * Classe permettant de créer une entité sorcier avec des sorts et une vue
 */
public class SorcierControleur extends PersonnageControleur{
    public SorcierControleur(Context mContext, GameSurface gs, Carte c, Sorcier p) {
        super(mContext, gs, c, p);

        Random rand = new Random();
        List<Integer> listeSortInt = new ArrayList<>();
        while(listeSortInt.size() < 4){
            switch (rand.nextInt(10)+1){
                case 1:
                    if(!listeSortInt.contains(1)){
                        listeSortInt.add(1);
                        p.ajouterCapacite(new ChaineSombre(c));
                        Log.i("Sorcier", "Ajout de la capacite ChaineSombre");
                    }
                    break;
                case 2:
                    if(!listeSortInt.contains(2)){
                        listeSortInt.add(2);
                        p.ajouterCapacite(new FireBall(p, c));
                        Log.i("Sorcier", "Ajout de la capacite FireBall");
                    }
                    break;
                case 3:
                    if(!listeSortInt.contains(3)){
                        listeSortInt.add(3);
                        p.ajouterCapacite(new ContactMortel(p, c));
                        Log.i("Sorcier", "Ajout de la capacite ContactMortel");
                    }
                    break;
                case 4:
                    if(!listeSortInt.contains(4)){
                        listeSortInt.add(4);
                        p.ajouterCapacite(new Meteore(p, c));
                        Log.i("Sorcier", "Ajout de la capacite Meteore");
                    }
                    break;
                case 5:
                    if(!listeSortInt.contains(5)){
                        listeSortInt.add(5);
                        p.ajouterCapacite(new Ignite(c));
                        Log.i("Sorcier", "Ajout de la capacite Ignite");
                    }
                    break;
                case 6:
                    if(!listeSortInt.contains(6)){
                        listeSortInt.add(6);
                        p.ajouterCapacite(new TeleportationUrgence(p, c));
                        Log.i("Sorcier", "Ajout de la capacite TeleportationUrgence");
                    }
                    break;
                case 7:
                    if(!listeSortInt.contains(7)){
                        listeSortInt.add(7);
                        p.ajouterCapacite(new Tornade(p, c));
                        Log.i("Sorcier", "Ajout de la capacite Tornade");
                    }
                    break;
                case 8:
                    if(!listeSortInt.contains(8)){
                        listeSortInt.add(8);
                        p.ajouterCapacite(new TransfomationMineure(p, c));
                        Log.i("Sorcier", "Ajout de la capacite TransformationMineur(e)");
                    }
                    break;
                case 9:
                    if(!listeSortInt.contains(9)){
                        listeSortInt.add(9);
                        p.ajouterCapacite(new VolDeVie(p, c));
                        Log.i("Sorcier", "Ajout de la capacite VolDeVie");
                    }
                    break;
                case 10:
                    if(!listeSortInt.contains(10)){
                        listeSortInt.add(10);
                        p.ajouterCapacite(new Seisme(p, c));
                        Log.i("Sorcier", "Ajout de la capacite Seisme");
                    }
                    break;
            }
        }
    }
}
