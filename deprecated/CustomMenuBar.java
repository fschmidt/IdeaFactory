package de.bht.se2.ideafactory.view;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CustomMenuBar extends VerticalLayout {

    private MenuBar menubar = new MenuBar();

    public CustomMenuBar() {
    }

    private Command menuCommand = new Command() {
        public void menuSelected(MenuItem selectedItem) {
            getWindow().showNotification("Action " + selectedItem.getText());
        }
    };

}