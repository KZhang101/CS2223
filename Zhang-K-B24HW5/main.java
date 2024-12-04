import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("1: Hashing\n2: Dijkstra");
        int part = input.nextInt();
        input.nextLine();
        if(part == 1){
            try {
                Path ravenPath = Path.of("TheRavenB24.txt");
                Path caskPath = Path.of("TheCaskOfAmontilladoB24.txt");
                Path heartPath = Path.of("TellTaleHeartB24.txt");
                String raven = Files.readString(ravenPath);
                String cask = Files.readString(caskPath);
                String heart = Files.readString(heartPath);

                String cask_heart = cask + heart;
                String heart_cask = heart + cask;

                System.out.println("\nPlease select the text to be hashed");
                System.out.println("1: Raven\n2: Cask of A\n3: Tell Tale H");
                System.out.println("4: Cask + Tell\n5: Tell + Cask");
                
                int tired = input.nextInt();
                input.nextLine();

                switch(tired) {
                    case 1:{ // Raven 
                        hasing hash = new hasing(raven, 1000, 123);
                        break;
                    }
                    case 2:{ // Cask
                        hasing hash = new hasing(cask, 1000, 123);
                        break;
                    }
                    case 3:{ // Tell
                        hasing hash = new hasing(heart, 1000, 123);
                        break;
                    }
                    case 4:{ // Cask + Tell
                        hasing hash = new hasing(cask_heart, 1499, 123);
                        break;
                    }
                    case 5:{ // Tell + Task
                        hasing hash = new hasing(heart_cask, 1499, 123);
                        break;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(part == 2){
            dijkstra pathFinder = new dijkstra();
        }
    }
}   
