package de.bht.se2.ideafactory.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class AbstractContribution implements IContribution, Serializable {
	
	private static final long serialVersionUID = -1536665818542021119L;
	
	@Id
        @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private IPortalUser creator;
	private String name;
	@Lob
	private String shortDecription;
	@Lob
	private String detailedDescription;
	@Lob
	private String specifications;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.bht.se2.ideafactory.model.IContribution#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.bht.se2.ideafactory.model.IContribution#setId(java.lang.Long)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.bht.se2.ideafactory.model.IContribution#getCreator()
	 */
	@Override
	public IPortalUser getCreator() {
		return creator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.bht.se2.ideafactory.model.IContribution#setCreator(de.bht.se2.ideafactory
	 * .model.IPortalUser)
	 */
	@Override
	public void setCreator(IPortalUser creator) {
		this.creator = creator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.bht.se2.ideafactory.model.IContribution#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.bht.se2.ideafactory.model.IContribution#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.bht.se2.ideafactory.model.IContribution#getShortDecription()
	 */
	@Override
	public String getShortDecription() {
		return shortDecription;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.bht.se2.ideafactory.model.IContribution#setShortDecription(java.lang
	 * .String)
	 */
	@Override
	public void setShortDecription(String shortDecription) {
		this.shortDecription = shortDecription;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.bht.se2.ideafactory.model.IContribution#getDetailedDescription()
	 */
	@Override
	public String getDetailedDescription() {
		return detailedDescription;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.bht.se2.ideafactory.model.IContribution#setDetailedDescription(java
	 * .lang.String)
	 */
	@Override
	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.bht.se2.ideafactory.model.IContribution#getSpecifications()
	 */
	@Override
	public String getSpecifications() {
		return specifications;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.bht.se2.ideafactory.model.IContribution#setSpecifications(java.lang
	 * .String)
	 */
	@Override
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
}
