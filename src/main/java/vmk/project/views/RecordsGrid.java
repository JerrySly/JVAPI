package vmk.project.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.function.ValueProvider;
import vmk.project.model.Record;

import java.util.ArrayList;
import java.util.List;

public class RecordsGrid extends Grid<Record> {
    private final List<Record> records;
    private final MainView parent;

    public RecordsGrid(MainView parent, List<Record> records) {
        super(Record.class);
        this.parent = parent;
        this.records = records;
        build();

        parent.setBody(this);
    }

    public void build() {
        setItems(records);
        var orderedColumns = new ArrayList<Grid.Column<Record>>() {{
            add(getColumnByKey("id"));
            add(getColumnByKey("name"));
            add(getColumnByKey("seen"));
            add(getColumnByKey("votes"));
            add(getColumnByKey("straightAverage"));
            add(getColumnByKey("weightedAverage"));
            add(getColumnByKey("bayesianAverage"));
        }};

        setColumnOrder(orderedColumns);
        getColumnByKey("id").setWidth("1%");
        getColumnByKey("name").setWidth("15%");
        getColumnByKey("votes").setWidth("1%");
        getColumnByKey("seen").setWidth("1%");
        addComponentColumn(this::buildDetailsButton);
        getColumns().get(getColumns().size() - 1).setWidth("1%");
        setHeightByRows(true);
    }

    private Button buildDetailsButton(Record record) {
        return new Button("", new Icon(VaadinIcon.ARROW_FORWARD), click -> details(record));
    }

    private void details(Record record) {
        new Detail(parent, record);
    }
}
