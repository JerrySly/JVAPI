package vmk.project.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import vmk.project.service.AnimeNewsService;

@Route
public class MainView extends VerticalLayout {
    final AnimeNewsService service;
    Component prevBody;
    Component body;

    public MainView(AnimeNewsService service) {
        this.service = service;
        add(new HorizontalLayout(new Button("К поиску", event -> setBody(new FindForm(this))), new Button("Назад", event -> setPrevBody())));
        var divider = new Span() {{
            getStyle().set("background-color", "black");
            getStyle().set("flex", "0 0 1px");
            getStyle().set("align-self", "stretch");
        }};
        add(divider);
        setBody(new FindForm(this));
    }

    public void setPrevBody() {
        if (prevBody != null)
            setBody(prevBody);
    }

    public void setBody(Component body) {
        if (this.body != null)
            remove(this.body);
        add(body);
        prevBody = this.body;
        this.body = body;
    }
}
