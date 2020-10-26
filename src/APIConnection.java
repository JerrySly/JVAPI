import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
}
