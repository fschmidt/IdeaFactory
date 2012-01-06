package de.bht.se2.ideafactory.view;

import java.util.Arrays;
import java.util.List;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;

import de.bht.se2.ideafactory.IdeaFactoryApplication;
import de.bht.se2.ideafactory.model.Comment;
import de.bht.se2.ideafactory.model.Idea;

public class CommentForm extends Form implements ClickListener {

    public static final Object[] NATURAL_COL_ORDER = new Object[] { "title",
	    "text" };

    private static final long serialVersionUID = 3767736508811705893L;
    private Button save = new Button("Save", (ClickListener) this);
    private Button cancel = new Button("Cancel", (ClickListener) this);
    private IdeaFactoryApplication app;
    private BeanItem<Idea> idea = null;
    HorizontalLayout footer;
    private TextArea area = new TextArea("insert comment");

    public CommentForm(IdeaFactoryApplication app) {
	this.app = app;

	setWriteThrough(false);

	footer = new HorizontalLayout();
	footer.setSpacing(true);
	footer.addComponent(save);
	footer.addComponent(cancel);
	footer.addComponent(area);
	area.setVisible(false);
	footer.setVisible(false);
	setFooter(footer);

    }

    @Override
    public void buttonClick(ClickEvent event) {

	Button source = event.getButton();

	if (source == save) {

	    commit();

	    app.getDataSource().updateItem(idea);
	    setReadOnly(true);
	    app.showListView();
	} else if (source == cancel) {
	    super.setItemDataSource(null);
	    getFooter().setVisible(false);
	    discard();
	    setReadOnly(true);
	}

    }

    public void setItemDataSource(BeanItem<Idea> idea) {
	if (idea != null) {

	    this.idea = idea;

	    List<Object> orderedProperties = Arrays.asList(NATURAL_COL_ORDER);
	    Comment newComment = new Comment(app.getCurrentUser(), idea.getBean());
	    super.setItemDataSource(new BeanItem<Comment>(newComment),
		    orderedProperties);

	    setReadOnly(false);
	    getFooter().setVisible(true);
	} else {
	    super.setItemDataSource(null);
	    getFooter().setVisible(false);
	}
    }

    @Override
    public void setReadOnly(boolean readOnly) {
	super.setReadOnly(readOnly);
	save.setVisible(!readOnly);
	cancel.setVisible(!readOnly);
    }

    public void setComment(boolean setComment) {
	Boolean boo = false;
	boo = !setComment;
	area.setVisible(boo);
    }
}
