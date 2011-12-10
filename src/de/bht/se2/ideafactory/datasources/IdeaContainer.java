package de.bht.se2.ideafactory.datasources;

import com.vaadin.data.util.BeanItemContainer;

import de.bht.se2.ideafactory.builder.IdeaBuilder;
import de.bht.se2.ideafactory.dao.EclipseLinkIdeaDAO;
import de.bht.se2.ideafactory.model.Idea;

public class IdeaContainer extends BeanItemContainer<Idea> {

	private static final long serialVersionUID = 7673382696025473186L;

	public IdeaContainer() throws InstantiationException,
			IllegalAccessException {
		super(Idea.class);
	}

	public static IdeaContainer createWithRandomIdea() {
		IdeaContainer ic = null;

		// @formatter:off
		Idea myIdea;
		// @formatter:on
		for (int i = 0; i < 10; i++) {
			myIdea = IdeaBuilder.idea().shortDecription("test")
					.detailedDescription("longtest").name("TestIdea")
					.specifications("specs").build();
			EclipseLinkIdeaDAO eli = new EclipseLinkIdeaDAO();
			eli.createIdea(myIdea);
			eli.closeConnections();

			try {
				ic = new IdeaContainer();
				ic.addItem(myIdea);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			// TODO Test the database

		}

		return ic;
	}

}
