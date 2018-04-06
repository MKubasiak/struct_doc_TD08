import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class Data {

    public MongoClient mongo;
    public MongoDatabase database;
    public final static String DB = "musicdb";

    private String key = "6c1897c3409c621a1522760e7164fbf6";
    private String url ="http://ws.audioscrobbler.com/";
    private Document doc = new Document();

    /**
     * Méthode qui permet de récupérer les informations d'un album au format json
     * @param artiste nom de l'artiste
     * @param album nom de l'album
     * @return Document dans lequel sont stockées les informations d'un album
     */
    public Document getDataAlbum(String artiste, String album){
        HTTPTools httpTools = new HTTPTools();
        String jsonAlbum = httpTools.sendGet(url+"2.0/?method=album.getinfo&api_key="+key+"&artist="+artiste+"&album="+album+"&format=json");
        Document docAlbum = doc.parse(jsonAlbum);
        Document document = docAlbum.get("album", docAlbum);
        Document finalDoc = new Document("name", document.get("name"))
                .append("artist", document.get("artist"))
                .append("mbid", document.get("mbid"))
                .append("url", document.get("url"))
                .append("release", "")
                .append("images", document.get("image"))
                .append("listeners", document.get("listeners"))
                .append("playcounts", document.get("playcount"))
                .append("tracks", document.get("tracks"));
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
        String jsonTrack = http.sendGet(url+"2.0/?method=track.getInfo&api_key="+key+"&artist="+artiste+"&track="+title+"&format=json");
        Document docTrack = doc.parse(jsonTrack);
        Document document = docTrack.get("track", docTrack);
        Document finalDoc = new Document("name", document.get("name"))
                .append("mbid", document.get("mbid"))
                .append("url", document.get("url"))
                .append("duration", document.get("duration"))
                .append("artist", document.get("artist"))
                .append("tags", document.get("toptags"));
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
        if(colName == "albums"){
            collection.deleteMany(eq("album.mbid", mbid));
        }else{
            collection.deleteMany(eq("track.mbid", mbid));
        }
    }

}
