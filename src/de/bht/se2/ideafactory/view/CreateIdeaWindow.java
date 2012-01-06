package de.bht.se2.ideafactory.view;

import java.util.Arrays;
import java.util.List;

import com.vaadin.data.Item;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;

import de.bht.se2.ideafactory.dao.EclipseLinkIdeaDAO;
import de.bht.se2.ideafactory.datasources.IdeaContainer;
import de.bht.se2.ideafactory.model.Idea;

public class CreateIdeaWindow extends Window {

	private static final long serialVersionUID = -8184077133750771978L;
	private CreateIdeaWindow window;
	private TextField name;
	private TextField shortDecription;
	private TextArea detailedDescription;
	private TextField specification;
	private Button submit;
	EclipseLinkIdeaDAO dao = EclipseLinkIdeaDAO.createInstance();

	public CreateIdeaWindow()  {

		window = this;
		setModal(true);

		setWidth("50%");
		setHeight("50%");
		center();
		setCaption("Create Idea");
		addComponent(new Label("Here you can create a new Idea"));

		name = new TextField();
		shortDecription = new TextField();
		detailedDescription = new TextArea();
		shortDecription = new TextField();
		specification = new TextField();
		submit = new Button("Create Idea");
		
		
		VerticalLayout vl = new VerticalLayout();

		HorizontalLayout nameLayout = new HorizontalLayout();
		HorizontalLayout shortDecriptionLayout = new HorizontalLayout();
		HorizontalLayout detailedDescriptionLayout = new HorizontalLayout();
		HorizontalLayout specificationLayout = new HorizontalLayout();

		nameLayout.addComponent(new Label("name: "));
		nameLayout.addComponent(name);
		shortDecriptionLayout.addComponent(new Label("short Decription: "));
		shortDecriptionLayout.addComponent(shortDecription);
		detailedDescriptionLayout.addComponent(new Label("detailed Description: "));
		detailedDescriptionLayout.addComponent(detailedDescription);
		specificationLayout.addComponent(new Label("Specifications: "));
		specificationLayout.addComponent(specification);
		
		vl.addComponent(nameLayout);
		vl.addComponent(shortDecriptionLayout);
		vl.addComponent(detailedDescriptionLayout);
		vl.addComponent(specificationLayout);
		vl.addComponent(submit);
		addComponent(vl);
		
		submit.addListener(new ClickListener() {
			private static final long serialVersionUID = 3916109170205189095L;

			@Override
			public void buttonClick(ClickEvent event) {
				System.out.println("clicked");
				Idea newIdea = new Idea();
				newIdea.setName(name.toString());
				newIdea.setShortDecription(shortDecription.toString());
				newIdea.setDetailedDescription(detailedDescription.toString());
				newIdea.setSpecifications(specification.toString());
				
				dao.create(newIdea);
				window.close();
				
			}
		});
	}
	

	


}
