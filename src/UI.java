import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public   class UI {
    public static void Menu() throws IOException, ParserConfigurationException, SAXException {
        while(true) {
            System.out.println("Welcom to AnimeNews App");
            System.out.println("Menu:");
            System.out.println("1. Top Anime");
            System.out.println("2. Top Manga");
            System.out.println("3. Exit");
            System.out.printf("Pls choose action: ");
            Scanner myInput = new Scanner( System.in );
            int index = myInput.nextInt();
            switch (index) {
                case (1):
                    TopAnime();
                case 3:
                    Exit();
                default:
                    System.out.println("Not correct");
            }
        }
    }
    public  static void TopAnime() throws IOException, SAXException, ParserConfigurationException {
        InformationHandler informationHandler=new InformationHandler();
        Scanner scanner=new Scanner(System.in);
        System.out.printf("How much place in top you need: ");
        int amount=scanner.nextInt();
        System.out.printf("From what place top you need: ");
        int indexFrom=scanner.nextInt();
        ArrayList<Anime> top=InformationHandler.GetTopAnime(amount,indexFrom);
        for (Anime anime: top) {
            System.out.println(indexFrom+" "+anime.Name+" "+anime.Id);
            indexFrom++;
        }
    }
    public static void Exit(){
        exit(404);
    }
}
