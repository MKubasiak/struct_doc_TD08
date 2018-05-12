import org.bson.Document;

/**
 * Classe Main
 *
 * Permet de lancer mongo et d'effectuer des opérations
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
     * Méthode pour remplir la bdd
     */
    public static void fillDb(){
        addAlbum("Lorde", "Melodrama");
        addAlbum("Ed%20Sheeran", "Divide");
        addAlbum("Beyoncé", "Beyoncé");
        addAlbum("Drake", "So%20Far%20Gone");
        addAlbum("The%20Beatles", "Abbey%20Road");
        addAlbum("Queen", "Greatest%20Hits");
        addAlbum("Elvis%20Presley", "Elvis%20Presley");
        addAlbum("Cher", "Believe");

        /*
         * Exemple de suppression d'album
         */
        //data.deleteDocument("63b3a8ca-26f2-4e2b-b867-647a6ec2bebd",COLALBUMS);

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

        /*
         * Exemple de suppression de track
         */
        //data.deleteDocument("32ca187e-ee25-4f18-b7d0-3b6713f24635",COLTRACKS);
    }

    public static void addAlbum(String artist, String album){
        docAlbum = data.getDataAlbum(artist,album);
        data.insertDocument(docAlbum, COLALBUMS);
    }

    public static void addTrack(String artist, String title){
        docTrack = data.getDataTitle("Cher", "Believe");
        data.insertDocument(docTrack, COLTRACKS);
    }
    /**
     * Méthode main
     * @param args
     */
    public static void main(String[] args) {
        data.connectDB();
        //Si vous souhaitez remplir votre base de données, passez en argument du main 'fill'
        //Sinon passez l'argument 'noFill'
       if(args[0].equals("fill")){
            fillDb();
       }
    }
}
