package de.bht.se2.ideafactory.model;

public interface IContribution {

	public abstract Long getId();

	public abstract void setId(Long id);

	public abstract IPortalUser getCreator();

	public abstract void setCreator(IPortalUser creator);

	public abstract String getName();

	public abstract void setName(String name);

	public abstract String getShortDecription();

	public abstract void setShortDecription(String shortDecription);

	public abstract String getDetailedDescription();

	public abstract void setDetailedDescription(String detailedDescription);

	public abstract String getSpecifications();

	public abstract void setSpecifications(String specifications);

}