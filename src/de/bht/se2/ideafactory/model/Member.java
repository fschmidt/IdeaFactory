package de.bht.se2.ideafactory.model;

public class Member implements UserRole {

	@Override
	public <T extends IContribution> boolean hasUserRightsToEdit(Class<T> clazz) {
		return true;
	}
}
