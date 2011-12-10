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

        // @formatter:off
		Idea myIdea = IdeaBuilder.idea()
		        .shortDecription("test")
				.detailedDescription("longtest")
				.name("TestIdea")
				.specifications("specs")
				.build();
		// @formatter:on

        // TODO Test the database

        EclipseLinkIdeaDAO eli = new EclipseLinkIdeaDAO();
        eli.createIdea(myIdea);
        eli.closeConnections();

        IdeaContainer ic = null;

        try {
            ic = new IdeaContainer();
            ic.addItem(myIdea);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return ic;
    }

}
