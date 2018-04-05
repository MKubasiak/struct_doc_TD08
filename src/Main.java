import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Main {

    public static MongoClient mongo;
    public static MongoDatabase database;

    public static Document docAlbum = new Document();
    public static Document docTrack = new Document();

    public final static String DB = "musicdb";
    public final static String COLALBUMS = "albums";
    public final static String COLTRACKS = "tracks";

    public static void main(String[] args) {
        // Creating a Mongo client
        mongo = new MongoClient( "localhost" , 27017 );

        // Accessing the database
        database = mongo.getDatabase(DB);

        Data dataAlbum = new Data();
        docAlbum = dataAlbum.getDataAlbum("Cher", "Believe");
        //dataAlbum.insertDocument(docAlbum, COLALBUMS);
        //dataAlbum.deleteDocument("63b3a8ca-26f2-4e2b-b867-647a6ec2bebd",COLALBUMS);

        Data dataTrack = new Data();
        docTrack = dataTrack.getDataTitle("Cher", "Believe");
        /***** A RETESTER ******/
        //dataTrack.insertDocument(docTrack, COLTRACKS);
        //dataTrack.deleteDocument("63b3a8ca-26f2-4e2b-b867-647a6ec2bebd",COLTRACKS);*/


    }
}
