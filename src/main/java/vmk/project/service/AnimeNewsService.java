package vmk.project.service;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;
import vmk.project.model.Detail;
import vmk.project.model.Record;
import vmk.project.util.Parser;

import javax.xml.parsers.DocumentBuilderFactory;
import java.util.List;

@Service
public class AnimeNewsService {
    String url = "https://cdn.animenewsnetwork.com/encyclopedia/";
    HttpClient client = HttpClients.createDefault();

    public Detail getDetails(String id){
        try {
            var builder = new URIBuilder(url + "api.xml")
                    .setParameter("title", id);
            var request = new HttpGet(builder.build());
            var response = client.execute(request);
            var doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(response.getEntity().getContent());
            return Parser.parseDetail(doc);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Record> getMangas(int amount, int skip) {
        return getRecords("173", amount, skip);
    }

    public List<Record> getAnimes(int amount, int skip) {
        return getRecords("172", amount, skip);
    }

    private List<Record> getRecords(String id, int amount, int skip){
        try {
            var builder = new URIBuilder(url + "reports.xml")
                    .setParameter("id", id)
                    .setParameter("nlist", String.valueOf(amount))
                    .setParameter("nskip", String.valueOf(skip));
            var request = new HttpGet(builder.build());
            var response = client.execute(request);
            var doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(response.getEntity().getContent());
            return Parser.parseRecords(doc);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
