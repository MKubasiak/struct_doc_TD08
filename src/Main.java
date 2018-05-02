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
        //docAlbum = data.getDataAlbum("Lorde", "Melodrama");
        //docAlbum = data.getDataAlbum("Ed%20Sheeran", "Divide");
        //docAlbum = data.getDataAlbum("Beyoncé", "Beyoncé");
        //docAlbum = data.getDataAlbum("Drake", "So%20Far%20Gone");
        //docAlbum = data.getDataAlbum("The%20Beatles", "Abbey%20Road");
        //docAlbum = data.getDataAlbum("Queen", "Greatest%20Hits");
        //docAlbum = data.getDataAlbum("Elvis%20Presley", "Elvis%20Presley");
        //docAlbum = data.getDataAlbum("Cher", "Believe");
        //data.insertDocument(docAlbum, COLALBUMS);
        //data.deleteDocument("63b3a8ca-26f2-4e2b-b867-647a6ec2bebd",COLALBUMS);

        //docTrack = data.getDataTitle("Cher", "Believe");
        //docTrack = data.getDataTitle("Bob%20Dylan", "You%27re%20Gonna%20Make%20Me%20Lonesome%20When%20You%20Go");
        //docTrack = data.getDataTitle("The%20Beatles", "Blackbird");
        //docTrack = data.getDataTitle("The%20Beatles", "Let%20It%20Be");
        //docTrack = data.getDataTitle("The%20Beatles", "Hey%20Jude");
        //docTrack = data.getDataTitle("Queen", "Bohemian%20Rhapsody");
        //docTrack = data.getDataTitle("Queen", "Somebody%20To%20Love");
        //docTrack = data.getDataTitle("Elvis%20Presley", "Can%27t%20Help%20Falling%20In%20Love");
        //docTrack = data.getDataTitle("Jimi%20Hendrix", "Purple%20Haze");
        //docTrack = data.getDataTitle("Pink%20Foyld", "Wish%20You%20Were%20Here");

        //data.insertDocument(docTrack, COLTRACKS);
        //data.deleteDocument("32ca187e-ee25-4f18-b7d0-3b6713f24635",COLTRACKS);
    }

    /**
     * Méthode main
     * @param args
     */
    public static void main(String[] args) {
        data.connectDB();
        fillDb();
    }
}
