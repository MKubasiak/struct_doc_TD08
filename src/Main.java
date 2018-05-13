import org.bson.Document;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe Main
 *
 * Permet de se connecter à mongo et d'effectuer des opérations
 *
 * @authors : Charlotte Fernandes & Maxime Kubasiak
 */

public class Main {

    public static Document docAlbum = new Document();
    public static Document docTrack = new Document();
    public static Data data = new Data();

    public final static String COLALBUMS = "albums";
    public final static String COLTRACKS = "tracks";

    /**
     * Méthode main
     * @param args
     */
    public static void main(String[] args) {
        data.connectDB();
        boolean running = true;
        String artist, album, track, mbid;
        System.out.println("Bienvenue sur notre application.");
        while(running){
            System.out.println("Voici les fonctions disponibles :");
            System.out.println("[1] Remplir votre base de donnees avec nos donnees");
            System.out.println("[2] Afficher les informations d'un album");
            System.out.println("[3] Ajouter un album à votre base de données");
            System.out.println("[4] Supprimer un album à votre base de données");
            System.out.println("[5] Afficher les informations d'un titre");
            System.out.println("[6] Ajouter un titre à votre base de données");
            System.out.println("[7] Supprimer un titre à votre base de données");
            System.out.println("[0] Quitter l'application");

            // Choix de l'utilisateur
            Scanner sc = new Scanner(System.in);
            System.out.println("Entrez votre choix : ");
            int choix;
            try {
                choix = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Votre choix est invalide. Veuillez choisir une option parmis celles proposées.");
                continue;
            }
            // Option a effectué en fonction du choix de l'utilisateur :
            switch(choix) {
                case 1:
                    fillDb();
                    break;

                case 2:
                    sc.nextLine();
                    System.out.println("Veuillez saisir le nom de l'album à afficher :");
                    album = sc.nextLine();
                    System.out.println("Veuillez saisir l'artiste de l'album à afficher :");
                    artist = sc.nextLine();
                    showAlbum(artist, album);
                    break;

                case 3:
                    sc.nextLine();
                    System.out.println("Veuillez saisir le nom de l'album à ajouter :");
                    album = sc.nextLine();
                    System.out.println("Veuillez saisir l'artiste de l'album à ajouter :");
                    artist = sc.nextLine();
                    addAlbum(artist, album);
                    break;

                case 4:
                    sc.nextLine();
                    System.out.println("Veuillez saisir le mbid de l'album à supprimer :");
                    mbid = sc.nextLine();
                    deleteAlbum(mbid);
                    break;

                case 5:
                    sc.nextLine();
                    System.out.println("Veuillez saisir le nom du titre à afficher :");
                    track = sc.nextLine();
                    System.out.println("Veuillez saisir l'artiste du titre à afficher :");
                    artist = sc.nextLine();
                    showTrack(artist, track);
                    break;

                case 6:
                    sc.nextLine();
                    System.out.println("Veuillez saisir le nom du titre à ajouter :");
                    track = sc.nextLine();
                    System.out.println("Veuillez saisir l'artiste du titre à ajouter :");
                    artist = sc.nextLine();
                    addTrack(artist, track);
                    break;

                case 7:
                    sc.nextLine();
                    System.out.println("Veuillez saisir le mbid du titre à supprimer :");
                    mbid = sc.nextLine();
                    deleteTrack(mbid);
                    break;
                    
                case 0:
                    // Quitter l'application
                    System.out.println("Au revoir !");
                    running=false;
                    break;

                default:
                    System.out.println("Votre choix est invalide. Veuillez choisir une option parmis celles proposées.");
            }
        }
    }

    /**
     * Méthode pour remplir la bdd
     */
    public static void fillDb(){
        //Album
        addAlbum("Lorde", "Melodrama");
        addAlbum("Ed%20Sheeran", "Divide");
        addAlbum("Beyoncé", "Beyoncé");
        addAlbum("Drake", "So%20Far%20Gone");
        addAlbum("The%20Beatles", "Abbey%20Road");
        addAlbum("Queen", "Greatest%20Hits");
        addAlbum("Elvis%20Presley", "Elvis%20Presley");
        addAlbum("Cher", "Believe");

        //Track
        addTrack("Cher", "Believe");
        addTrack("Bob%20Dylan", "You%27re%20Gonna%20Make%20Me%20Lonesome%20When%20You%20Go");
        addTrack("The%20Beatles", "Blackbird");
        addTrack("The%20Beatles", "Let%20It%20Be");
        addTrack("The%20Beatles", "Hey%20Jude");
        addTrack("Queen", "Bohemian%20Rhapsody");
        addTrack("Queen", "Somebody%20To%20Love");
        addTrack("Elvis%20Presley", "Can%27t%20Help%20Falling%20In%20Love");
        addTrack("Jimi%20Hendrix", "Purple%20Haze");
        addTrack("Pink%20Foyld", "Wish%20You%20Were%20Here");

        System.out.println("L'ajout des albums et des titres s'est effectué avec succès.");

    }

    /**
     * Méthode qui permet d'afficher le contenu d'un album
     * @param artist
     * @param album
     */
    public static void showAlbum(String artist, String album){
        try{
            System.out.println(data.getDataAlbum(artist, album));
        }catch(NullPointerException e){
            System.out.println("L'album ou l'artiste n'existe pas");
        }
    }

    /**
     * Méthode qui permet d'afficher le contenu d'un titre
     * @param artist
     * @param title
     */
    public static void showTrack(String artist, String title){
        try{
            System.out.println(data.getDataTitle(artist, title));
        }catch(NullPointerException e){
            System.out.println("Le titre ou l'artiste n'existe pas");
        }
    }
    /**
     * Méthode qui permet d'ajouter un album à la BDD
     * @param artist
     * @param album
     */
    public static void addAlbum(String artist, String album){
        docAlbum = data.getDataAlbum(artist,album);
        try{
            data.insertDocument(docAlbum, COLALBUMS);
            System.out.println("L'ajout de l'album "+album+" s'est effectué avec succès");
        }catch(NullPointerException e){
            System.out.println("L'album ou l'artiste n'existe pas");
        }
    }

    /**
     * Méthode qui permet d'ajouter un titre à la BDD
     * @param artist
     * @param title
     */
    public static void addTrack(String artist, String title){
        docTrack = data.getDataTitle(artist, title);
        try{
            data.insertDocument(docTrack, COLTRACKS);
            System.out.println("L'ajout du titre "+title+" s'est effectué avec succès");
        }catch(NullPointerException e){
            System.out.println("Le titre ou l'artiste n'existe pas");
        }
    }

    /**
     * Méthode qui permet de supprimer un album à la BDD
     * @param mbid
     */
    public static void deleteAlbum(String mbid){
        try{
            data.deleteDocument(mbid,COLALBUMS);
            System.out.println("La suppression de l'album s'est effectuée avec succès");
        }catch(NullPointerException e){
            System.out.println("Le mbid n'existe pas");
        }
    }

    /**
     * Méthode qui permet de supprimer un titre à la BDD
     * @param mbid
     */
    public static void deleteTrack(String mbid) {
        try{
            data.deleteDocument(mbid,COLTRACKS);
            System.out.println("La suppression du titre s'est effectuée avec succès");
        }catch(NullPointerException e){
            System.out.println("Le mbid n'existe pas");
        }
    }
}
