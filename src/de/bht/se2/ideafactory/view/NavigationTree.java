package de.bht.se2.ideafactory.view;

import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Tree;

import de.bht.se2.ideafactory.IdeaFactoryApplication;

public class NavigationTree extends Tree {

	private static final long serialVersionUID = 310258093868900190L;
	public static final Object SHOW_ALL = "Show all";
	public static final Object SEARCH = "Search";

	public NavigationTree(IdeaFactoryApplication  app) {

		addItem(SHOW_ALL);
        addItem(SEARCH);
        

       /*
         * We want items to be selectable but do not want the user to be able to
         * de-select an item.
         */
        setSelectable(true);
        setNullSelectionAllowed(false);

       // Make application handle item click events
        addListener((ItemClickListener) app);
	}

}
