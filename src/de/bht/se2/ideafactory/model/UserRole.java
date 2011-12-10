package de.bht.se2.ideafactory.model;


public interface UserRole {
    public <T extends IContribution> boolean hasUserRightsToEdit(Class<T> clazz);
}
