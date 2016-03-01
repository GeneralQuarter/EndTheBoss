package virus.endtheboss.Serveur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import virus.endtheboss.Client.Joueur;
import virus.endtheboss.Enumerations.Deplacement;
import virus.endtheboss.IA.IABoss;
import virus.endtheboss.Modele.CaseVide;
import virus.endtheboss.Modele.Effects.Effet;
import virus.endtheboss.Modele.Personnages.ActionPersonnage;
import virus.endtheboss.Modele.Personnages.Archer;
import virus.endtheboss.Modele.Personnages.Boss;
import virus.endtheboss.Modele.Carte;
import virus.endtheboss.Modele.Personnages.Personnage;
import virus.endtheboss.Modele.Personnages.Sbire;
import virus.endtheboss.Modele.Personnages.Sorcier;
import virus.endtheboss.Modele.Personnages.Support;
import virus.endtheboss.Modele.Personnages.Tank;

/**
 *
 * @author Quentin Gangler
 */
public class Serveur {

    private List<JoueurServeur> joueurServeurs;

    private List<Personnage> entites;

    private int id;

    private boolean enPartie;

    private int[] personnageRestant;

    private int quiJoue = -1;

    private IABoss iaBoss;

    private Carte c;

    public Serveur(){
        System.out.println("-- Serveur EndTheBoss v1.0 --");
        init();
        startServeur();
    }

    private void init(){
        System.out.println("Réinistialisation du serveur");
        joueurServeurs = new ArrayList<>();
        id = 0;
        enPartie = false;
        //0 = tank, 1 = Archer, 3 = Sorcier, 4 = Pretre, 4 = Boss, 5 = Sbire
        personnageRestant = new int[]{0,1,2,3,4,5};
    }

    private void startServeur(){

        Runnable serveur = new Runnable(){

            @Override
            public void run() {
                ServerSocket ss = null;
                try {
                    ss = new ServerSocket(5560);
                } catch (IOException ex) {
                    System.err.println("Création socket impossible" + ex.getMessage());
                    System.exit(-1);
                }

                Socket sc;
                try {
                    while(id < 666) {
                        sc = ss.accept();
                        connecte(sc);
                    }
                } catch(IOException e) {
                    System.err.println("Erreur lors de l'attente d'une connexion : " + e.getMessage());
                }
            }

        };
        Thread serverThread = new Thread(serveur);
        serverThread.start();
    }

    private void connecte(final Socket sc){

        Runnable gestionClient = new Runnable() {
            @Override
            public void run() {
                System.out.println("Connection établie");

                ObjectOutputStream output = null;
                ObjectInputStream input = null;

                try {
                    output = new ObjectOutputStream(sc.getOutputStream());
                    input = new ObjectInputStream(sc.getInputStream());
                } catch(IOException e) {
                    System.err.println("Association des flux impossible : " + e);
                }

                Object receivedObject = null;
                boolean running = true;
                JoueurServeur client = null;
                while(running){
                    try {
                        if(input != null)
                            receivedObject = input.readObject();
                        else
                            running = false;
                        System.out.println("Object reçu " + receivedObject);
                        if(receivedObject instanceof MessageServeur){
                            MessageServeur ms = (MessageServeur) receivedObject;
                            if(client == null) {
                                id++;
                                client = new JoueurServeur(ms.getJoueur(), id, output);
                            }else{
                                client.setJoueur(new Joueur(ms.getJoueur()));
                                System.out.println("Nouveau joueur choix : " + client.getJoueur().getChoixPerso() + " id : " + client.getJoueur().getId());
                            }
                        }

                        if(receivedObject instanceof Boolean){
                            running = (Boolean) receivedObject;
                        }

                        if(client != null)
                            receptionObject(client, receivedObject);
                    } catch (IOException ex) {
                        removeJoueur(client);
                        running = false;
                    } catch (ClassNotFoundException ex) {
                        System.err.println("Classe introuvable : " + receivedObject);
                    }
                }

                try {
                    if(input != null) {
                        input.close();
                        output.close();
                    }
                    sc.close();
                } catch(IOException e) {
                    System.err.println("Erreur lors de la fermeture des flux et des sockets : " + e);
                }
            }
        };
        Thread echangeClient = new Thread(gestionClient);
        echangeClient.start();
    }

    private synchronized void addJoueur(JoueurServeur joueurServeur){
        joueurServeurs.add(joueurServeur);
        System.out.println(joueurServeur.getJoueur().getNom() + " vient de se connecter au lobby (" + joueurServeurs.size() + "/4)");
        sendAll(new MessageServeur(joueurServeur.getJoueur(), MessageServeur.TypeMessage.CONNEXION));
    }

    private synchronized void removeJoueur(JoueurServeur joueurServeur){
        if(joueurServeurs.contains(joueurServeur)){
            if(joueurServeur.getJoueur().getChoixPerso() != -1){
                personnageRestant[joueurServeur.getJoueur().getChoixPerso()] = joueurServeur.getJoueur().getChoixPerso();
            }
            joueurServeurs.remove(joueurServeurs.indexOf(joueurServeur));
            System.out.println(joueurServeur.getJoueur().getNom() + " s'est déconnecté du lobby (" + joueurServeurs.size() + "/4)");
            sendAllExceptOne(joueurServeur.getJoueur(), new MessageServeur(joueurServeur.getJoueur(), MessageServeur.TypeMessage.DECONNEXION));
            sendToOne(joueurServeur, false);
        }

        if(joueurServeurs.isEmpty()){
            init();
        }
    }

    public synchronized void sendAll(Object object){
        for(JoueurServeur js : joueurServeurs){
            try {
                js.getOutput().writeObject(object);
            } catch (IOException ex) {
                System.err.println("Impossible d'envoyer à " + js.getJoueur().getNom() + " : " + ex.getMessage());
                removeJoueur(js);
            }
        }
    }

    private synchronized void sendAllExceptOne(Joueur joueur, Object object){
        for(JoueurServeur js : joueurServeurs){
            if(js.getJoueur().getId() != joueur.getId()){
                try {
                    js.getOutput().writeObject(object);
                } catch (IOException e) {
                    System.err.println("Impossible d'envoyer à " + js.getJoueur().getNom());
                    removeJoueur(js);
                }
            }
        }
    }

    private synchronized void sendToOne(JoueurServeur joueurServeur, Object object){
        try {
            joueurServeur.getOutput().writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized boolean isNomPris(String nom){
        boolean res = false;
        for(JoueurServeur js : joueurServeurs){
            if(js.getJoueur().getNom().equals(nom)){
                res = true;
            }
        }
        return res;
    }

    private synchronized boolean prendrePerso(int choixPerso){
        if(choixPerso >= 0 && choixPerso <= 4) {
            if (personnageRestant[choixPerso] == choixPerso) {
                personnageRestant[choixPerso] = -1;
                return true;
            }
        }
        return false;
    }

    private synchronized void sendListJoueurs(JoueurServeur joueurServeur){
        List<Joueur> joueurs = new ArrayList<>();
        for(JoueurServeur js : joueurServeurs){
            if(joueurServeur.getJoueur().getId() != js.getJoueur().getId()){
                joueurs.add(js.getJoueur());
            }
        }
        sendToOne(joueurServeur, joueurs);
    }

    private synchronized void updateJoueur(Joueur joueur){
        for(JoueurServeur js : joueurServeurs){
            if(js.getJoueur().getId() == joueur.getId()){
                js.setJoueur(new Joueur(joueur));
            }
        }
    }

    private boolean isTousPret(){
        boolean res = true;
        for(JoueurServeur js : joueurServeurs){
            if(js.getJoueur().getChoixPerso() == -1){
                System.out.println("Le joueur " + js.getJoueur().getNom() + " n'a pas encore chois de personnage");
                res = false;
            }
        }
        return res;
    }

    private synchronized void createCarte(){
        c = new Carte();

        //Ajout décors et autres

        sendAll(c);

        entites = new ArrayList<>();

        Boss boss = new Boss();
        boss.setId(666);
        c.placePlayer(boss, 10, 10);
        iaBoss = new IABoss(boss, c, this);

        entites.add(boss);

        sendAll(boss);

        for(JoueurServeur js : joueurServeurs){
            Personnage p;
            switch(js.getJoueur().getChoixPerso()){
                case 0:
                    p = new Tank();break;
                case 1:
                    p = new Archer();break;
                case 2:
                    p = new Sorcier();break;
                case 3:
                    p = new Support();break;
                case 4:
                    p = new Boss();break;
                case 5:
                    p = new Sbire();break;
                default:p = new Tank();break;
            }
            p.setId(js.getJoueur().getId());
            p.setSonNom(js.getJoueur().getNom());
            p = c.placerPersonnageAleatoirement(p);
            System.out.println("Initiative de " + p.getSonNom() + " : " + p.getSonInitiative());
            entites.add(p);
            sendAll(p);
        }

        Collections.sort(entites, new Comparator<Personnage>() {
            @Override
            public int compare(Personnage lhs, Personnage rhs) {
                return ((Integer) rhs.getSonInitiative()).compareTo(lhs.getSonInitiative());
            }
        });

        nextTour();
    }

    private synchronized JoueurServeur getJoueurServeurDepuisID(int id){
        for(JoueurServeur js : joueurServeurs){
            if(js.getJoueur().getId() == id){
                return js;
            }
        }
        return null;
    }

    private synchronized Personnage getEntite(int id){
        for(Personnage p : entites){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    private synchronized int getNextTourJoueurID(){
        if(quiJoue == -1 && !entites.isEmpty()){
            quiJoue = 0;
        }else if(!entites.isEmpty() && quiJoue >= entites.size()-1){
            quiJoue = 0;
        }else if(!entites.isEmpty()){
            quiJoue++;
        }
        System.out.println("Qui Joue : " + quiJoue + ", Nb entites : " + entites.size() + ", Entite id : " + entites.get(quiJoue).getId());

        return entites.get(quiJoue).getId();
    }

    private synchronized void nextTour(){
        int nextID = getNextTourJoueurID();
        if(nextID == 666) {
            GestionServeur.serveur = this;
            iaBoss.jouerTour();
            nextID = getNextTourJoueurID();
        }
        if(nextID != 666) {
            JoueurServeur js = getJoueurServeurDepuisID(nextID);
            sendToOne(js, new ActionPersonnage(nextID, ActionPersonnage.Action.DEBUT_TOUR, null));
        }
    }

    private synchronized boolean receptionObject(JoueurServeur joueurServeur, Object object){
        if(object instanceof MessageServeur){
            MessageServeur ms = (MessageServeur) object;
            switch(ms.getTypeMessage()){
                case CONNEXION:
                    if(joueurServeurs.size() > 3){
                        sendToOne(joueurServeur, new MessageServeur(joueurServeur.getJoueur(), MessageServeur.TypeMessage.ERR_SERVEUR_PLEIN));
                        return false;
                    }
                    else if(isNomPris(joueurServeur.getJoueur().getNom())){
                        sendToOne(joueurServeur, new MessageServeur(joueurServeur.getJoueur(), MessageServeur.TypeMessage.ERR_NOM_CLIENT_INVALIDE));
                        return true;
                    }
                    else if(enPartie){
                        sendToOne(joueurServeur, new MessageServeur(joueurServeur.getJoueur(), MessageServeur.TypeMessage.ERR_PARTIE_EN_COURS));
                        return false;
                    }else{
                        addJoueur(joueurServeur);
                        return true;
                    }
                case DECONNEXION:
                    removeJoueur(joueurServeur);
                    return false;
                case CHOIX_PERSO:
                    if(!prendrePerso(joueurServeur.getJoueur().getChoixPerso())){
                        joueurServeur.getJoueur().setChoixPerso(-1);
                        sendToOne(joueurServeur, new MessageServeur(joueurServeur.getJoueur(), MessageServeur.TypeMessage.ERR_PERSO_PRIS));
                    }else{
                        updateJoueur(joueurServeur.getJoueur());
                        sendAll(new MessageServeur(new Joueur(joueurServeur.getJoueur()), MessageServeur.TypeMessage.CHOIX_PERSO));
                        if(isTousPret()){
                            enPartie = true;
                            sendAll(new MessageServeur(null, MessageServeur.TypeMessage.LANCEMENT_PARTIE));
                            createCarte();
                        }
                    }
                    return true;
                case DEMANDE_JOUEURS_LIST:
                    sendListJoueurs(joueurServeur);
                    break;
            }
        }

        if(object instanceof ActionPersonnage){
            ActionPersonnage ac = (ActionPersonnage) object;
            switch(ac.getAction()){
                case DEPLACEMENT:
                    Deplacement d = (Deplacement) ac.getValue();
                    switch(d){
                        case DROITE:
                            c.deplacerPersonnage(getEntite(ac.getPersonnageID()), 1, 0);
                            sendAllExceptOne(joueurServeur.getJoueur(), new ActionPersonnage(ac));
                            break;
                        case GAUCHE:
                            c.deplacerPersonnage(getEntite(ac.getPersonnageID()), -1, 0);
                            sendAllExceptOne(joueurServeur.getJoueur(), new ActionPersonnage(ac));
                            break;
                        case HAUT:
                            c.deplacerPersonnage(getEntite(ac.getPersonnageID()), 0, -1);
                            sendAllExceptOne(joueurServeur.getJoueur(), new ActionPersonnage(ac));
                            break;
                        case BAS:
                            c.deplacerPersonnage(getEntite(ac.getPersonnageID()), 0, 1);
                            sendAllExceptOne(joueurServeur.getJoueur(), new ActionPersonnage(ac));
                            break;
                    }
                    break;
                case FIN_TOUR:
                    nextTour();
                    break;
                case TRANSPORT:
                    CaseVide cv = (CaseVide) ac.getValue();
                    c.transporterPersonnage(getEntite(ac.getPersonnageID()), cv.getX(), cv.getY(), false);
                    sendAllExceptOne(joueurServeur.getJoueur(), new ActionPersonnage(ac));
                    break;
                case DEGAT_SANS_ARMURE:
                    Personnage personnage = getEntite(ac.getPersonnageID());
                    int value = (Integer) ac.getValue();
                    if(personnage != null){
                        personnage.coupPersonnageSansArmure(value, false);
                        sendAllExceptOne(joueurServeur.getJoueur(), new ActionPersonnage(ac));
                    }
                    break;
                case DEGAT_AVEC_ARMURE:
                    personnage = getEntite(ac.getPersonnageID());
                    value = (Integer) ac.getValue();
                    if(personnage != null){
                        personnage.coupPersonnage(value, false);
                        sendAllExceptOne(joueurServeur.getJoueur(), new ActionPersonnage(ac));
                    }
                    break;
                case SOIN:
                    personnage = getEntite(ac.getPersonnageID());
                    value = (Integer) ac.getValue();
                    if(personnage != null){
                        personnage.soignerPersonnage(value, false);
                        sendAllExceptOne(joueurServeur.getJoueur(), new ActionPersonnage(ac));
                    }
                    break;
                case EFFET:
                    personnage = getEntite(ac.getPersonnageID());
                    Effet effet = (Effet) ac.getValue();
                    if(personnage != null){
                        personnage.ajouterEffet(effet, false);
                        sendAllExceptOne(joueurServeur.getJoueur(), new ActionPersonnage(ac));
                    }
                    break;
                case CHANGE_DEGAT:
                    personnage = getEntite(ac.getPersonnageID());
                    value = (Integer) ac.getValue();
                    if(personnage != null){
                        personnage.setSesDegatDeBase(value, false);
                        sendAllExceptOne(joueurServeur.getJoueur(), new ActionPersonnage(ac));
                    }
                    break;
                case CHANGE_VITESSE:
                    personnage = getEntite(ac.getPersonnageID());
                    value = (Integer) ac.getValue();
                    if(personnage != null){
                        personnage.setSaVitesse(value, false);
                        sendAllExceptOne(joueurServeur.getJoueur(), new ActionPersonnage(ac));
                    }
                    break;
                case CHANGE_RESISTANCE:
                    personnage = getEntite(ac.getPersonnageID());
                    value = (Integer) ac.getValue();
                    if(personnage != null){
                        personnage.setSaResistance(value, false);
                        sendAllExceptOne(joueurServeur.getJoueur(), new ActionPersonnage(ac));
                    }
                    break;
                case MORT:
                    personnage = getEntite(ac.getPersonnageID());
                    if(personnage != null){
                        entites.remove(personnage);
                        System.out.println("Entite size : " + entites.size());
                        c.emptyCase(personnage);
                        System.out.println("Case ou était le personnage : " + c.get(personnage));
                        sendAll(new ActionPersonnage(ac));
                    }
                    break;
            }
        }

        return true;
    }
}
