package de.bht.se2.ideafactory.view;


import com.vaadin.data.Property;
import com.vaadin.ui.Table;

import de.bht.se2.ideafactory.IdeaFactoryApplication;
import de.bht.se2.ideafactory.datasources.IdeaContainer;

public class IdeaList extends Table {
	
	private static final long serialVersionUID = 1692212555340482748L;
	private IdeaFactoryApplication app;

	public IdeaList(IdeaFactoryApplication app) {
		this.app=app;
		setSizeFull();
		setContainerDataSource(IdeaContainer.createWithTestData());
		setVisibleColumns(IdeaContainer.NATURAL_COL_ORDER);
		setColumnHeaders(IdeaContainer.COL_HEADERS_ENGLISH);
		
		setSelectable(true);
		setImmediate(true);
		
		setNullSelectionAllowed(false);
		
		setSelectable(true);
		setImmediate(true);
		
		addListener((Property.ValueChangeListener) app);
		
        
	}
}