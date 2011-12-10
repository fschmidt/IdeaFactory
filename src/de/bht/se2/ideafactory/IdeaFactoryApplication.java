package de.bht.se2.ideafactory;

import com.vaadin.Application;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import de.bht.se2.ideafactory.view.CustomMenuBar;
import de.bht.se2.ideafactory.view.IdeaList;
import de.bht.se2.ideafactory.view.LoginWindow;

public class IdeaFactoryApplication extends Application {
    private static final long serialVersionUID = -2504849084551618605L;

    @Override
    public void init() {

        buildLayout();
    }

    private void buildLayout() {
        setMainWindow(new Window("Idea Factory"));

        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();

        layout.addComponent(new CustomMenuBar());
        layout.addComponent(new IdeaList());

        getMainWindow().setContent(layout);
        getMainWindow().addWindow(new LoginWindow());
    }

}
