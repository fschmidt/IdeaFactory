package de.bht.se2.ideafactory.view;

import com.vaadin.ui.Component;
import com.vaadin.ui.SplitPanel;




@SuppressWarnings("deprecation")
public class ListView extends SplitPanel{
	
	
	private static final long serialVersionUID = -4795863325250016662L;

	public ListView(IdeaList ideaList, IdeaForm ideaForm) {
        setFirstComponent(ideaList);
        setSecondComponent((Component) ideaForm);
        setSplitPosition(40);
    }
}
