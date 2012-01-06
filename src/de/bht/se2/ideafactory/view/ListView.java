package de.bht.se2.ideafactory.view;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalSplitPanel;

public class ListView extends VerticalSplitPanel {

    private static final long serialVersionUID = -4795863325250016662L;
    private final HorizontalSplitPanel ideaArea;

    public ListView(IdeaList ideaList, IdeaForm ideaForm, CommentForm commentForm) {
	setFirstComponent(ideaList);
	ideaArea = new HorizontalSplitPanel();
	ideaArea.setFirstComponent((Component) ideaForm);
	ideaArea.setSecondComponent(commentForm);
	setSecondComponent(ideaArea);
	setSplitPosition(40);
    }
}
