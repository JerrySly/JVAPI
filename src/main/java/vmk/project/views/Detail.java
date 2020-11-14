package vmk.project.views;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import vmk.project.model.Record;

public class Detail extends HorizontalLayout {
    private final Record record;
    private final MainView parent;

    private VerticalLayout info;
    private Image image;

    public Detail(MainView parent, Record record) {
        this.record = record;
        this.parent = parent;
        build();

        add(info, image);
        setSizeFull();

        setAlignItems(Alignment.START);
        parent.setBody(this);
    }

    private void build() {
        var detail = parent.service.getDetails(record.getId());

        var titleText = new Span("Title name: ");
        titleText.getElement().getStyle().set("color", "grey");
        var title = new Span(titleText, new Text(record.getName()));
        title.getElement().getStyle().set("font-size", "300%");

        var ratedText = new Span("Rated: ");
        ratedText.getElement().getStyle().set("color", "grey");
        var rated = new Span(ratedText, new Text(record.getStraightAverage() + " / 10"));
        rated.getElement().getStyle().set("font-size", "200%");

        var plotText = new Span("Plot summary: ");
        plotText.getElement().getStyle().set("color", "grey");
        var plot = new Span(plotText, new Text(detail.getPlotSummary()));
        plot.getElement().getStyle().set("font-size", "150%");

        var genresText = new Span("Genres: ");
        genresText.getElement().getStyle().set("color", "grey");
        var genres = new Span(genresText, new Text(String.join(", ", detail.getGenres())));
        genres.getStyle().set("font-size", "125%");

        var themesText = new Span("Linked themes: ");
        themesText.getElement().getStyle().set("color", "grey");
        var themes = new Span(themesText, new Text(String.join(", ", detail.getThemes())));
        themes.getElement().getStyle().set("font-size", "125%");

        info = new VerticalLayout(title, rated, plot, genres, themes);

        image = new Image(detail.getImagePath(), "picture");
        image.setWidth("30%");
    }
}
