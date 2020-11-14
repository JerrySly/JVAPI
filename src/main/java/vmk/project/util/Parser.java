package vmk.project.util;

import org.w3c.dom.Document;
import vmk.project.model.Detail;
import vmk.project.model.Record;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static Detail parseDetail(Document doc) {
        var detail = new Detail();
        var genres = new ArrayList<String>();
        var themes = new ArrayList<String>();
        var infos = doc.getElementsByTagName("info");
        for (int i = 0; i < infos.getLength(); i++) {
            var info = infos.item(i);
            var type = info.getAttributes().getNamedItem("type").getNodeValue();
            var text = info.getTextContent();
            switch (type) {
                case "Picture" -> detail.setImagePath(info.getLastChild().getAttributes().getNamedItem("src").getNodeValue());
                case "Genres" -> genres.add(text);
                case "Themes" -> themes.add(text);
                case "Plot Summary" -> detail.setPlotSummary(text);
            }
        }
        detail.setGenres(genres);
        detail.setThemes(themes);
        return detail;
    }

    public static List<Record> parseRecords(Document doc) {
        var records = doc.getElementsByTagName("item");
        var result = new ArrayList<Record>();
        for (int i = 0; i < records.getLength(); i++) {
            var record = records.item(i);
            var nodes = record.getChildNodes();
            var validRecord = new Record();
            validRecord.setId(record.getAttributes().getNamedItem("id").getNodeValue());
            validRecord.setName(nodes.item(0).getTextContent());
            validRecord.setVotes(Integer.parseInt(nodes.item(1).getTextContent()));
            validRecord.setSeen(Integer.parseInt(nodes.item(2).getTextContent()));
            validRecord.setStraightAverage(Float.parseFloat(nodes.item(3).getTextContent()));
            validRecord.setWeightedAverage(Float.parseFloat(nodes.item(4).getTextContent()));
            validRecord.setBayesianAverage(Float.parseFloat(nodes.item(5).getTextContent()));
            result.add(validRecord);
        }
        return result;
    }
}
