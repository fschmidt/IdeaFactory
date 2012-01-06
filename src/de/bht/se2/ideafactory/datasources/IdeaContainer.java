package de.bht.se2.ideafactory.datasources;

import java.util.List;
import java.util.Random;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;

import de.bht.se2.ideafactory.dao.EclipseLinkIdeaDAO;
import de.bht.se2.ideafactory.dao.EclipseLinkPortalUserDAO;
import de.bht.se2.ideafactory.model.Comment;
import de.bht.se2.ideafactory.model.Idea;
import de.bht.se2.ideafactory.model.PortalUser;

public class IdeaContainer extends BeanItemContainer<Idea> {

    private static final long serialVersionUID = 7673382696025473186L;

    public static final Object[] NATURAL_COL_ORDER = new Object[] { "id",
	    "name", "creator", "shortDecription", "detailedDescription",
	    "specifications", "comments" };

    public static final String[] COL_HEADERS_ENGLISH = new String[] { "Id",
	    "Name", "Creator", "Short Decription", "Detailed Description",
	    "Specifications", "Comments" };

    private final EclipseLinkIdeaDAO eli;

    public IdeaContainer() throws InstantiationException,
	    IllegalAccessException {
	super(Idea.class);
	eli = EclipseLinkIdeaDAO.createInstance();

    }

    public Item addNewIdea(Idea idea) {

	eli.create(idea);

	BeanItem<Idea> bi = this.addItem(idea);

	return bi;
    }

    public void updateItem(Item idea) {

	@SuppressWarnings("unchecked")
	BeanItem<Idea> bii = (BeanItem<Idea>) idea;
	Idea updatedIdea = bii.getBean();

	eli.update(updatedIdea);
	for (Idea tmp : eli.getAll()) {
	    System.out.println("in db:" + tmp);
	}
    }

    public static IdeaContainer createWithTestData(PortalUser currentUser) {

	PortalUser user = currentUser;

	if (user == null) {
	    user = new PortalUser();
	    user.setFirstName("Frank");
	    user.setName("Schmidt");
	    user.setPassword("password");
	    user.setLoginName("fschmidt");
	    EclipseLinkPortalUserDAO.createInstance().create(user);
	    System.out.println("User:" + user);
	}

	// create the container first
	IdeaContainer c = null;

	try {
	    c = new IdeaContainer();
	} catch (InstantiationException e2) {
	    e2.printStackTrace();
	} catch (IllegalAccessException e2) {
	    e2.printStackTrace();
	}

	// try fetching ideas from the db
	EclipseLinkIdeaDAO eli1 = EclipseLinkIdeaDAO.createInstance();

	List<Idea> ideas = null;

	ideas = eli1.getAll();

	if (ideas.size() > 0) {
	    for (Idea idea : ideas) {
		c.addItem(idea);
	    }
	    return c;
	}

	// if we couldn't fetch anything from the db create some testdata
	final String[] fnames = { "Super Fancy Interface",
		"My First Printtoaster", "Dynamic Toothbrush", "Windows 3.11",
		"Custom Debugger" };
	final String[] shortDecription = { "Throwing no Exceptions",
		"prints and toasts concurrently", "has no methods",
		"full of bugs", "me want cookies" };
	final String[] detailedDescription = { "5 wheels and a...",
		"Its blue and...", "It wonderfull because...",
		"You eat it and...", "It smells like..." };
	final String[] specifications = { "Blue", "Red", "Orange", "Black",
		"White" };

	Random r = new Random(0);
	try {
	    c = new IdeaContainer();
	    for (int i = 0; i < 10; i++) {
		Idea p = new Idea(user);
		p.setName(fnames[r.nextInt(fnames.length)]);
		p.setShortDecription(shortDecription[r
			.nextInt(shortDecription.length)]);
		p.setDetailedDescription(detailedDescription[r
			.nextInt(detailedDescription.length)]);
		p.setSpecifications(specifications[r
			.nextInt(specifications.length)]);
		Comment newComment = new Comment(user, p);
		newComment.setText("test");
		newComment.setTitle("title");
		
		eli1.create(p);
	    }
	} catch (InstantiationException e1) {
	    e1.printStackTrace();
	} catch (IllegalAccessException e) {
	    e.printStackTrace();
	}

	ideas = eli1.getAll();

	for (Idea idea : ideas) {
	    c.addItem(idea);
	}

	return c;

    }
}
