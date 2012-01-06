package de.bht.se2.ideafactory.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

@Entity
public class Idea {

    @SuppressWarnings("unused")
    private static final long serialVersionUID = -5722274971456061010L;

    @Id
    @TableGenerator(name = "TABLE_GEN_ID", table = "SEQUENCE_TABLE_ID", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "ID_SEQ")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN_ID")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private PortalUser creator;

    private String name;
    @Lob
    private String shortDecription;
    @Lob
    private String detailedDescription;
    @Lob
    private String specifications;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Comment.class)
    private List<Comment> comments = new ArrayList<Comment>();

    public Idea() {
    }

    public Idea(PortalUser creator) {
	this.creator = creator;
	creator.addIdea(this);
    }

    public Long getId() {
	return id;
    }

    public PortalUser getCreator() {
	return creator;
    }

    public void setCreator(PortalUser creator) {
	this.creator = creator;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getShortDecription() {
	return shortDecription;
    }

    public void setShortDecription(String shortDecription) {
	this.shortDecription = shortDecription;
    }

    public String getDetailedDescription() {
	return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
	this.detailedDescription = detailedDescription;
    }

    public String getSpecifications() {
	return specifications;
    }

    public void setSpecifications(String specifications) {
	this.specifications = specifications;
    }

    public void addComment(Comment comment) {
	getComments().add(comment);
    }

    public void removeComment(Comment comment) {
	getComments().remove(comment);
    }

    public List<Comment> getComments() {
	return comments;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((comments == null) ? 0 : comments.hashCode());
	result = prime
		* result
		+ ((detailedDescription == null) ? 0 : detailedDescription
			.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result
		+ ((shortDecription == null) ? 0 : shortDecription.hashCode());
	result = prime * result
		+ ((specifications == null) ? 0 : specifications.hashCode());
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
	Idea other = (Idea) obj;
	if (comments == null) {
	    if (other.comments != null)
		return false;
	} else if (!comments.equals(other.comments))
	    return false;
	if (detailedDescription == null) {
	    if (other.detailedDescription != null)
		return false;
	} else if (!detailedDescription.equals(other.detailedDescription))
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
	if (shortDecription == null) {
	    if (other.shortDecription != null)
		return false;
	} else if (!shortDecription.equals(other.shortDecription))
	    return false;
	if (specifications == null) {
	    if (other.specifications != null)
		return false;
	} else if (!specifications.equals(other.specifications))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Idea [ Id: " + this.getId() + ", Name: " + this.getName()
		+ ", Comments: " + getComments() + "]";
    }

}
