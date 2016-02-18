package virus.endtheboss.Serveur;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import virus.endtheboss.PlayerID;

/**
 *
 * @author Quentin Gangler
 */
public class Serveur {

    private List<Player> players;

    private int id;

    public Serveur(){
        System.out.println("-- Serveur EndTheBoss v0.1 --");
        players = new ArrayList<>();
        id = 0;
        startServeur();
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

                Socket sc = null;
                try {
                    while(id < 5){
                        sc = ss.accept();
                        connecte(sc);
                    }
                } catch(IOException e) {
                    System.err.println("Erreur lors de l'attente d'une connexion : " + e.getMessage());
                    System.exit(-1);
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

                PlayerID i;
                try {
                    output = new ObjectOutputStream(sc.getOutputStream());
                    input = new ObjectInputStream(sc.getInputStream());
                    i = addPlayer(output);
                    System.out.println("Envoi de l'id " + i.getId());
                    output.writeObject(i);
                } catch(IOException e) {
                    System.err.println("Association des flux impossible : " + e);
                    i = null;
                }

                Object receivedObject = null;
                boolean running = true;
                while(running){
                    try {
                        receivedObject = input.readObject();
                        System.out.println("Object reçu " + receivedObject);
                        if(receivedObject != null)
                            sendAll(receivedObject);
                    } catch (IOException ex) {
                        System.err.println("Déconnexion de " + i.getId());
                        running = false;
                    } catch (ClassNotFoundException ex) {
                        System.err.println("Classe introuvable : " + receivedObject);
                    }
                }

                try {
                    input.close();
                    output.close();
                    sc.close();
                } catch(IOException e) {
                    System.err.println("Erreur lors de la fermeture des flux et des sockets : " + e);
                }
            }
        };
        Thread echangeClient = new Thread(gestionClient);
        echangeClient.start();
    }

    private synchronized PlayerID addPlayer(ObjectOutputStream out){
        id++;
        PlayerID Pid = new PlayerID(id);
        players.add(new Player(Pid, out));
        return Pid;
    }

    private synchronized void sendAll(Object object){
        for(Player p : players){
            try {
                p.getOut().writeObject(object);
            } catch (IOException ex) {
                System.err.println("Impossible d'envoyer à " + players.indexOf(p));
            }
        }
    }
}
