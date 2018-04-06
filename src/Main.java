import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Main {

    public static Document docAlbum = new Document();
    public static Document docTrack = new Document();
    public static Data data = new Data();

    public final static String COLALBUMS = "albums";
    public final static String COLTRACKS = "tracks";

    public static void fillDb(){
       //docAlbum = data.getDataAlbum("IAM", "l'école du micro d'argent");
        //data.insertDocument(docAlbum, COLALBUMS);
        //data.deleteDocument("63b3a8ca-26f2-4e2b-b867-647a6ec2bebd",COLALBUMS);

        docTrack = data.getDataTitle("Cher", "Believe");
        /***** A RETESTER ******/
        //data.insertDocument(docTrack, COLTRACKS);
        //data.deleteDocument("32ca187e-ee25-4f18-b7d0-3b6713f24635",COLTRACKS);
    }

    public static void main(String[] args) {
        data.connectDB();
        fillDb();

    }
}
