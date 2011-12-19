package de.bht.se2.ideafactory.datasources;

import java.util.Random;

import com.vaadin.data.util.BeanItemContainer;
import de.bht.se2.ideafactory.dao.EclipseLinkIdeaDAO;
import de.bht.se2.ideafactory.model.Idea;

public class IdeaContainer extends BeanItemContainer<Idea> {

	private static final long serialVersionUID = 7673382696025473186L;

	public IdeaContainer() throws InstantiationException,
			IllegalAccessException {
		super(Idea.class);
	}

	public static final Object[] NATURAL_COL_ORDER = new Object[] {
			"id", "name", "creator", "shortDecription", "detailedDescription", "specifications",
			 "comments" };

	public static final String[] COL_HEADERS_ENGLISH = new String[] {
		"Id", "Name", "Creator", "Short Decription", "Detailed Description", "Specifications",
	 "Comments" };
	
	
	
//	public static IdeaContainer createWithRandomIdea() {
//		IdeaContainer ic = null;
//		
//		// @formatter:off
//		Idea myIdea;
//		// @formatter:on
//		for (int i = 0; i < 5; i++) {
//			myIdea = IdeaBuilder.idea().shortDecription("test")
//					.detailedDescription("longtest").name("TestIdea")
//					.specifications("specs").build();
//			EclipseLinkIdeaDAO eli = new EclipseLinkIdeaDAO();
//			eli.createIdea(myIdea);
//			eli.closeConnections();
//
//			try {
//				ic = new IdeaContainer();
//				ic.addItem(myIdea);
//			} catch (InstantiationException e) {
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				e.printStackTrace();
//			}
//			// TODO Test the database
//
//		}
//
//		return ic;
//	}
//	
	
	public static IdeaContainer createWithTestData() {

		final String[] fnames = { "Peter", "Alice", "Joshua", "Mike", "Olivia"};
		final String[] shortDecription = { "Car", "Bike", "Motorbike", "Candy",
				"Keks"};
		final String[] detailedDescription = { "5 wheels and a...", "Its blue and...", "It wonderfull because...",
				"You eat it and...", "It smells like..."};
		final String[] specifications = { "Blue", "Red",
				"Orange", "Black", "White"};


		IdeaContainer c = null;
		Random r = new Random(0);
		try {
			c = new IdeaContainer();
			for (int i = 0; i < 10; i++) {
				Idea p = new Idea();
				p.setName(fnames[r.nextInt(fnames.length)]);
				p.setShortDecription(shortDecription[r.nextInt(shortDecription.length)]);
				p.setDetailedDescription(detailedDescription[r.nextInt(detailedDescription.length)]);
				p.setSpecifications(specifications[r.nextInt(specifications.length)]);
								
				c.addItem(p);
				EclipseLinkIdeaDAO eli = new EclipseLinkIdeaDAO();
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

		return c;

	}

}
