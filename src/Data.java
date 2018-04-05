import org.bson.Document;

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
        //System.out.println(docAlbum.toString());
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
        //System.out.println(docTrack.toString());
        return docTrack;
    }

}
