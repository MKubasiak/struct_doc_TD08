import com.mongodb.client.MongoCollection;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class Data {

    private String key = "6c1897c3409c621a1522760e7164fbf6";
    private String url ="http://ws.audioscrobbler.com/";
    private Document doc = new Document();

    /**
     * Méthode qui permet de récupérer les informations d'un album au format json
     * @param artiste nom de l'artiste
     * @param album nom de l'album
     */
    public Document getDataAlbum(String artiste, String album){
        HTTPTools httpTools = new HTTPTools();
        String jsonAlbum = httpTools.sendGet(url+"2.0/?method=album.getinfo&api_key="+key+"&artist="+artiste+"&album="+album+"&format=json");
        Document docAlbum = doc.parse(jsonAlbum);
        return docAlbum;
    }

    /**
     * Méthode qui permet de récupérer les informations d'un titre au format json
     * @param artiste nom de l'artiste
     * @param title nom du titre
     */
    public Document getDataTitle(String artiste, String title){
        HTTPTools http = new HTTPTools();
        String jsonTrack = http.sendGet(url+"2.0/?method=track.getInfo&api_key="+key+"&artist="+artiste+"&track="+title+"&format=json");
        Document docTrack = doc.parse(jsonTrack);
        return docTrack;
    }

    /**
     * Méthode qui permet d'inserer un document à la base de données
     * @param doc document bson à insérer
     * @param colName nom de la collection dans laquelle doit être inséré le document
     */
    public void insertDocument(Document doc, String colName){
        MongoCollection<Document> collection = Main.database.getCollection(colName);
        collection.insertOne(doc);
    }

    /**
     * Méthode qui permet de supprimer un document à la base de données
     * @param mbid mbid permettant d'identifier le document à supprimer
     * @param colName nom de la collection dans laquelle sera supprimé le document
     */
    public void deleteDocument(String mbid, String colName){
        MongoCollection collection = Main.database.getCollection(colName);
        if(colName == "albums"){
            collection.deleteMany(eq("album.mbid", mbid));
        }else{
            collection.deleteMany(eq("track.mbid", mbid));
        }
    }

}
