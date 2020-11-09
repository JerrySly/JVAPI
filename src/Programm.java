import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.jdom2.JDOMException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;


public class Programm {
    public static void main(String[] args) throws IOException, JDOMException, ParserConfigurationException, SAXException, XMLStreamException, TransformerException {

        UI.Menu();

    }
}
