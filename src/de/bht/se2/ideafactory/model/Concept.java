package de.bht.se2.ideafactory.model;

import java.util.ArrayList;

public class Concept {

	private ArrayList<PortalUser> contributors;
	private ArrayList<Idea> ideas;

	public void addContributor(PortalUser user) {
		contributors.add(user);
	}

	public void removeContributor(PortalUser user) {
		contributors.remove(user);
	}

	public void addIdea(Idea idea) {
		ideas.add(idea);
	}

	public void remove(Idea idea) {
		ideas.remove(idea);
	}

}
