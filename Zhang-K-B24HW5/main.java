import java.nio.file.Files;
import java.nio.file.Path;

public class main {
    public static void main(String[] args) {
        try {
            Path filePath = Path.of("TheRavenB24.txt");
            String content = Files.readString(filePath);
            //System.out.println(content);
           // System.out.println("Content length: " + content.length());

            hasing hash = new hasing(content, 1000, 123);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}   
