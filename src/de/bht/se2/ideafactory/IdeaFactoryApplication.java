package de.bht.se2.ideafactory;



import com.vaadin.Application;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import de.bht.se2.ideafactory.view.CreateIdeaWindow;
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
		Button createIdea = new Button("Create new Idea");
		layout.addComponent((Component) createIdea);

		layout.addComponent(new CustomMenuBar());
		layout.addComponent(new IdeaList());

		getMainWindow().setContent(layout);
		
		createIdea.addListener(new ClickListener() {
			private static final long serialVersionUID = 2454000420687727304L;

			@Override
			public void buttonClick(ClickEvent event) {
				System.out.println("clicked");
				getMainWindow().addWindow(new  CreateIdeaWindow());
			}
		});
				
		getMainWindow().addWindow(new LoginWindow());
		
		

	}

}
