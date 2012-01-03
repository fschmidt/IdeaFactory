package de.bht.se2.ideafactory.datasources;

import java.util.List;
import java.util.Random;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import de.bht.se2.ideafactory.dao.EclipseLinkIdeaDAO;
import de.bht.se2.ideafactory.model.Idea;

public class IdeaContainer extends BeanItemContainer<Idea> {

	@Override
	public BeanItem<Idea> addItem(Object itemId) {
		EclipseLinkIdeaDAO dao = new EclipseLinkIdeaDAO();
		if (itemId instanceof Idea) {
			// add Idea to the db
			dao.createIdea((Idea) itemId);
		}
		// update idea in db
		return super.addItem(itemId);
	}

	private static final long serialVersionUID = 7673382696025473186L;

	public IdeaContainer() throws InstantiationException,
			IllegalAccessException {
		super(Idea.class);
	}

	public static final Object[] NATURAL_COL_ORDER = new Object[] { "id",
			"name", "creator", "shortDecription", "detailedDescription",
			"specifications", "comments" };

	public static final String[] COL_HEADERS_ENGLISH = new String[] { "Id",
			"Name", "Creator", "Short Decription", "Detailed Description",
			"Specifications", "Comments" };

	public static IdeaContainer createWithTestData() {

		IdeaContainer c = null;

		try {
			c = new IdeaContainer();
		} catch (InstantiationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IllegalAccessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		EclipseLinkIdeaDAO eli = new EclipseLinkIdeaDAO();

		List<Idea> ideas = eli.getAll();

		if (ideas.size() > 0) {
			for (Idea idea : ideas) {
				c.addItem(idea);
			}
			return c;
		}

		final String[] fnames = { "Peter", "Alice", "Joshua", "Mike", "Olivia" };
		final String[] shortDecription = { "Car", "Bike", "Motorbike", "Candy",
				"Keks" };
		final String[] detailedDescription = { "5 wheels and a...",
				"Its blue and...", "It wonderfull because...",
				"You eat it and...", "It smells like..." };
		final String[] specifications = { "Blue", "Red", "Orange", "Black",
				"White" };

		Random r = new Random(0);
		try {
			c = new IdeaContainer();
			for (int i = 0; i < 10; i++) {
				Idea p = new Idea();
				p.setName(fnames[r.nextInt(fnames.length)]);
				p.setShortDecription(shortDecription[r
						.nextInt(shortDecription.length)]);
				p.setDetailedDescription(detailedDescription[r
						.nextInt(detailedDescription.length)]);
				p.setSpecifications(specifications[r
						.nextInt(specifications.length)]);

				eli = new EclipseLinkIdeaDAO();
				eli.createIdea(p);
				eli.closeConnections();
			}
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		eli = new EclipseLinkIdeaDAO();
		ideas = eli.getAll();

		for (Idea idea : ideas) {
			c.addItem(idea);
		}

		return c;

	}

}
