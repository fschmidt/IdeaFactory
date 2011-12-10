package de.bht.se2.ideafactory.model;

import java.util.ArrayList;

public class Concept extends AbstractContribution {

	private ArrayList<IPortalUser> contributors;
	private ArrayList<Idea> ideas;

	public void addContributor(IPortalUser user) {
		contributors.add(user);
	}

	public void removeContributor(IPortalUser user) {
		contributors.remove(user);
	}

	public void addIdea(Idea idea) {
		ideas.add(idea);
	}

	public void remove(Idea idea) {
		ideas.remove(idea);
	}

}
