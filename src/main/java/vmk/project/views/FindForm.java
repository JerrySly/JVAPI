package vmk.project.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

public class FindForm extends HorizontalLayout {
    private final MainView parent;

    private ComboBox<String> type;
    private TextField resultsSkip;
    private TextField resultsAmount;
    private Button find;

    public FindForm(MainView parent) {
        this.parent = parent;
        build();

        add(type, resultsSkip, resultsAmount, find);
        setAlignItems(FlexComponent.Alignment.END);
    }

    public void build() {
        type = new ComboBox<>("To find", List.of("Top rated anime", "Top rated manga"));
        type.setValue("Top rated anime");
        type.setAllowCustomValue(false);
        type.setPreventInvalidInput(true);

        resultsSkip = new TextField("To skip");
        resultsSkip.setRequired(true);
        resultsSkip.setValue("0");

        resultsAmount = new TextField("Amount");
        resultsAmount.setRequired(true);
        resultsAmount.setValue("10");

        find = new Button("ОК", click -> find());
    }

    private void find() {
        if (!isValidForm())
            return;

        var amount = Integer.parseInt(resultsAmount.getValue());
        var skip = Integer.parseInt(resultsSkip.getValue());

        var records = type.getValue().equals("Top rated anime") ?
                parent.service.getAnimes(amount, skip) :
                parent.service.getMangas(amount, skip);
        new RecordsGrid(parent, records);
    }

    private boolean isValidForm() {
        return isValidIntField(resultsAmount) &&
                isValidIntField(resultsSkip);
    }

    private boolean isValidIntField(TextField field) {
        if (field.isEmpty()) {
            Notification.show(field.getLabel() + " value cannot be empty", 3000, Notification.Position.TOP_CENTER);
            return false;
        }
        try {
            Integer.parseInt(field.getValue());
        } catch (NumberFormatException e) {
            Notification.show(field.getLabel() + " value is not a valid number", 3000, Notification.Position.TOP_CENTER);
            return false;
        }
        return true;
    }
}
