package de.bht.se2.ideafactory.view;
import com.vaadin.ui.Table;

import de.bht.se2.ideafactory.datasources.IdeaContainer;

public class IdeaList extends Table {
	
	private static final long serialVersionUID = 1692212555340482748L;

	public IdeaList() {
		setSizeFull();
		setContainerDataSource(IdeaContainer.createWithRandomIdea());
		
		setSelectable(true);
		setImmediate(true);
		
		setNullSelectionAllowed(false);
	}
}