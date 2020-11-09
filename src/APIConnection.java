import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class APIConnection {
    private URL url;
    private URLConnection connection;
    public APIConnection(String adress) throws IOException {
        url=new URL(adress);
        connection=url.openConnection();
    }
    public Document GetXMLDocument() throws IOException, ParserConfigurationException, SAXException {
        InputStream stream=connection.getInputStream();
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder=factory.newDocumentBuilder();
        return builder.parse(stream);
    }
    public  String GetStringViewOfXML() throws IOException, ParserConfigurationException, SAXException, TransformerException {
        TransformerFactory transfac = TransformerFactory.newInstance();
        Transformer trans = transfac.newTransformer();
        trans.setOutputProperty(OutputKeys.METHOD, "xml");
        trans.setOutputProperty(OutputKeys.INDENT, "yes");
        trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", Integer.toString(2));
       Document doc=GetXMLDocument();
       StringWriter sw=new StringWriter();
       StreamResult result=new StreamResult(sw);
        DOMSource source=new DOMSource(doc.getDocumentElement());
        trans.transform(source,result);
        return  sw.toString();

    }
}
