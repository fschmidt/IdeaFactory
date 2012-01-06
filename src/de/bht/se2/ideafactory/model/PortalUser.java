package de.bht.se2.ideafactory.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

@Entity
public class PortalUser {
    @Id
    @TableGenerator(name = "TABLE_GEN_PU", table = "SEQUENCE_TABLE_PU", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "PU_SEQ")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN_PU")
    private Long id;
    private String loginName;
    private String firstName;
    private String name;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Idea.class)
    private List<Idea> ideas = new ArrayList<Idea>();

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Comment.class)
    private List<Comment> comments = new ArrayList<Comment>();

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public List<Comment> getComments() {
	return comments;
    }

    public void setComments(List<Comment> comments) {
	this.comments = comments;
    }

    public String getLoginName() {
	return loginName;
    }

    public void setLoginName(String loginName) {
	this.loginName = loginName;
    }

    public void addIdea(Idea idea) {
	ideas.add(idea);
    }

    public List<Idea> getIdeas() {
	return ideas;
    }

    public void setIdeas(List<Idea> ideas) {
	this.ideas = ideas;
    }

    @Override
    public String toString() {
	return firstName + " " + name;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((comments == null) ? 0 : comments.hashCode());
	result = prime * result
		+ ((firstName == null) ? 0 : firstName.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result
		+ ((password == null) ? 0 : password.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	PortalUser other = (PortalUser) obj;
	if (comments == null) {
	    if (other.comments != null)
		return false;
	} else if (!comments.equals(other.comments))
	    return false;
	if (firstName == null) {
	    if (other.firstName != null)
		return false;
	} else if (!firstName.equals(other.firstName))
	    return false;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (password == null) {
	    if (other.password != null)
		return false;
	} else if (!password.equals(other.password))
	    return false;
	return true;
    }

    public void addComment(Comment comment) {
	comments.add(comment);

    }
}
