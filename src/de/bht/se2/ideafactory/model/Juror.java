package de.bht.se2.ideafactory.model;

public class Juror implements UserRole {

	@Override
	public <T extends IContribution> boolean hasUserRightsToEdit(Class<T> clazz) {
		return false;
	}
}
