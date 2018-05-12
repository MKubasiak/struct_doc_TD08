import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;

/**
 * Classe Data
 *
 * Méthodes de récupération de données (titres ou albums)
 * Méthodes d'ajout ou de suppression
 *
 * @authors : Charlotte Fernandes & Maxime Kubasiak
 */

public class Data {

    public MongoClient mongo;
    public MongoDatabase database;
    public final static String DB = "musicdb";

    private final String KEY = "6c1897c3409c621a1522760e7164fbf6";
    private final String URL ="http://ws.audioscrobbler.com/2.0/?";
    private Document doc = new Document();

    /**
     * Méthode qui permet de récupérer les informations d'un album au format json
     * @param artiste nom de l'artiste
     * @param album nom de l'album
     * @return Document dans lequel sont stockées les informations d'un album
     */
    public Document getDataAlbum(String artiste, String album){
        HTTPTools httpTools = new HTTPTools();
        String jsonAlbum = httpTools.sendGet(URL+"method=album.getinfo&api_key="+KEY+"&artist="+artiste+"&album="+album+"&format=json");
        Document document = doc.parse(jsonAlbum);
        Document docAlbum = document.get("album", document);

        //Récupération des tracks
        ArrayList<Document> listTracks = (ArrayList)docAlbum.get("tracks", document).get("track");
        ArrayList<Document> listTrack = new ArrayList<>();
        for(int i = 0; i<listTracks.size(); i++){
            listTrack.add(new Document("name", listTracks.get(i).get("name")).append("rank", listTracks.get(i).get("@attr", document).get("rank")).append("mbid", "null"));
        }

        //Construction du document
        Document finalDoc = new Document("name", docAlbum.get("name"))
                .append("artist", docAlbum.get("artist"))
                .append("mbid", docAlbum.get("mbid"))
                .append("url", docAlbum.get("url"))
                .append("release", "null")
                .append("images", docAlbum.get("image"))
                .append("listeners", docAlbum.get("listeners"))
                .append("playcounts", docAlbum.get("playcount"))
                .append("tracks", listTrack);

        return finalDoc;
    }

    /**
     * Méthode qui permet de récupérer les informations d'un titre au format json
     * @param artiste nom de l'artiste
     * @param title nom du titre
     * @return Document dans lequel sont stockées les informations d'un titre
     */
    public Document getDataTitle(String artiste, String title){
        HTTPTools http = new HTTPTools();
        String jsonTrack = http.sendGet(URL+"method=track.getInfo&api_key="+KEY+"&artist="+artiste+"&track="+title+"&format=json");
        Document document = doc.parse(jsonTrack);
        Document docTrack = document.get("track", document);
        Document docArtist = docTrack.get("artist", document);

        //Récupération des noms des tags
        ArrayList<Document> listTags = (ArrayList)docTrack.get("toptags", document).get("tag");
        ArrayList<String> arrayName = new ArrayList();
        for(Document tagname : listTags){
            arrayName.add(tagname.getString("name"));
        }

        //Construction du document
        Document finalDoc = new Document("name", docTrack.get("name"))
                .append("mbid", docTrack.get("mbid"))
                .append("url", docTrack.get("url"))
                .append("duration", docTrack.get("duration"))
                .append("artist", new Document("name", docArtist.get("name")).append("mbid", docArtist.get("mbid")))
                .append("tags", arrayName);
        return finalDoc;
    }

    /**
     * Méthode qui permet de se connecter à la base de données MongoDB
     */
    public void connectDB(){
        // Creation client mongo
        mongo = new MongoClient( "localhost" , 27017 );
        // Accès à la bdd
        database = mongo.getDatabase(DB);
    }

    /**
     * Méthode qui permet d'inserer un document à la base de données
     * @param doc document bson à insérer
     * @param colName nom de la collection dans laquelle doit être inséré le document
     */
    public void insertDocument(Document doc, String colName){
        MongoCollection<Document> collection = this.database.getCollection(colName);
        collection.insertOne(doc);
    }

    /**
     * Méthode qui permet de supprimer un document à la base de données
     * @param mbid mbid permettant d'identifier le document à supprimer
     * @param colName nom de la collection dans laquelle sera supprimé le document
     */
    public void deleteDocument(String mbid, String colName){
        MongoCollection collection = this.database.getCollection(colName);
        collection.deleteMany(eq("mbid", mbid));
    }

}
