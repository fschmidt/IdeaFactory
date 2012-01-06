package de.bht.se2.ideafactory.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

@Entity
public class Comment {
    @Id
    @TableGenerator(name = "TABLE_GEN_CO", table = "SEQUENCE_TABLE_ID", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "CO_SEQ")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN_CO")
    private Long id;
    @ManyToOne
    private PortalUser creator;

    private String title;

    @Lob
    private String text;

    @ManyToOne
    private Idea idea;

    public Comment(PortalUser creator, Idea idea) {
	this.creator = creator;
	this.idea = idea;
	creator.addComment(this);
	idea.addComment(this);

    }

    public Comment() {
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public PortalUser getCreator() {
	return creator;
    }

    public void setCreator(PortalUser creator) {
	this.creator = creator;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public Idea getIdea() {
	return idea;
    }

    public void setIdea(Idea idea) {
	this.idea = idea;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((text == null) ? 0 : text.hashCode());
	result = prime * result + ((title == null) ? 0 : title.hashCode());
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
	Comment other = (Comment) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (text == null) {
	    if (other.text != null)
		return false;
	} else if (!text.equals(other.text))
	    return false;
	if (title == null) {
	    if (other.title != null)
		return false;
	} else if (!title.equals(other.title))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Comment [id=" + id + ", creator=" + creator + ", title="
		+ title + ", text=" + text + ", idea=" + idea.getName() + "]";
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

}
