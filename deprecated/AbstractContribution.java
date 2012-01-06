package de.bht.se2.ideafactory.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class AbstractContribution implements Serializable {

    private static final long serialVersionUID = -1536665818542021119L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PortalUser creator;

    private String name;
    @Lob
    private String shortDecription;
    @Lob
    private String detailedDescription;
    @Lob
    private String specifications;

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
}
