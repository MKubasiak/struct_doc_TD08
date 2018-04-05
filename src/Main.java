import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Main {

    public static void main(String[] args) {
        Data album = new Data();
        album.getDataAlbum("Cher", "Believe");

        Data title = new Data();
        title.getDataTitle("Cher", "Believe");
        
    }
}
