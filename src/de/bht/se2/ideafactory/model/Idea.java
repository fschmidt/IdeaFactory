package de.bht.se2.ideafactory.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Idea extends AbstractContribution {

	@OneToMany(cascade = CascadeType.ALL)
	private List<Comment> comments;

	public void addComment(Comment comment) {
		getComments().add(comment);
	}

	public void removeComment(Comment comment) {
		getComments().remove(comment);
	}

	public List<Comment> getComments() {
		return comments;
	}
}
