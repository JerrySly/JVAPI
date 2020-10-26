import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class RequestManager {
    private static String adress ="https://cdn.animenewsnetwork.com/encyclopedia/";
    public static Document GetTopAnime(int amount,int indexFrom) throws IOException, ParserConfigurationException, SAXException {
        String request="reports.xml?id=172&nlist="+amount+"&nskip="+indexFrom;
        return new APIConnection(adress+request).GetXMLDocument();
    }

}
