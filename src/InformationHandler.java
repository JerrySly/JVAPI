import com.sun.xml.internal.ws.commons.xmlutil.Converter;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class InformationHandler
{
    private static   RequestManager manager=new RequestManager();

    public static ArrayList<Anime> GetTopAnime(int amount,int indexFrom) throws ParserConfigurationException, SAXException, IOException {
        ArrayList<Anime> top=new ArrayList<Anime>();
        Document doc=manager.GetTopAnime(amount,indexFrom);
        NodeList list=doc.getElementsByTagName("item");
        for (int i=0;i<list.getLength();i++){
            Anime anime=new Anime(list.item(i).getChildNodes().item(0).getTextContent(),
                    Integer.parseInt(list.item(i).getAttributes().item(0).getNodeValue()));
            top.add(anime);
        }
        return top;
    }
}
