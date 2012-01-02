package de.bht.se2.ideafactory.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.bht.se2.ideafactory.datasources.IdeaContainer;
import de.bht.se2.ideafactory.model.Idea;

public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
	final String[] fnames = { "Peter", "Alice", "Joshua", "Mike", "Olivia" };
	final String[] shortDecription = { "Car", "Bike", "Motorbike", "Candy",
		"Keks" };
	final String[] detailedDescription = { "5 wheels and a...",
		"Its blue and...", "It wonderfull because...",
		"You eat it and...", "It smells like..." };
	final String[] specifications = { "Blue", "Red", "Orange", "Black",
		"White" };

	Random r = new Random(0);
	// EclipseLinkIdeaDAO eli = new EclipseLinkIdeaDAO();
	// for(Idea idea : eli.getAll()){
	// c.addItem(idea);
	// }
	for (int i = 0; i < 10; i++) {
	    Idea p = new Idea();
	    p.setName(fnames[r.nextInt(fnames.length)]);
	    p.setShortDecription(shortDecription[r
		    .nextInt(shortDecription.length)]);
	    p.setDetailedDescription(detailedDescription[r
		    .nextInt(detailedDescription.length)]);
	    p.setSpecifications(specifications[r.nextInt(specifications.length)]);

	    EclipseLinkIdeaDAO eli = new EclipseLinkIdeaDAO();
	    eli.createIdea(p);
	    eli.closeConnections();
	}
	EclipseLinkIdeaDAO eli = new EclipseLinkIdeaDAO();
	for(Idea idea:eli.getAll()){
	    System.out.println(idea.getId());
	}
	eli.closeConnections();
    }

}
