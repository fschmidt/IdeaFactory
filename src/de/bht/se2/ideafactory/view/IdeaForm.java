package de.bht.se2.ideafactory.view;

import java.util.Arrays;
import java.util.List;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;

import de.bht.se2.ideafactory.IdeaFactoryApplication;
import de.bht.se2.ideafactory.model.Idea;
import de.bht.se2.ideafactory.model.PortalUser;

public class IdeaForm extends Form implements ClickListener {

    public static final Object[] NATURAL_COL_ORDER = new Object[] { "name",
	    "shortDecription", "detailedDescription", "specifications",
	    "comments" };

    private static final long serialVersionUID = 3767736508811705893L;
    private Button save = new Button("Save", (ClickListener) this);
    private Button cancel = new Button("Cancel", (ClickListener) this);
    private Button edit = new Button("Edit Idea", (ClickListener) this);
    private Button comment = new Button("Comment", (ClickListener) this);
    private Button saveComment = new Button("Save Comment",
	    (ClickListener) this);
    private Button cancelComment = new Button("Cancel", (ClickListener) this);
    private IdeaFactoryApplication app;
    private Idea newIdea = null;
    public boolean newIdeaMode = false;
    HorizontalLayout footer;
    private TextArea area = new TextArea("insert comment");
    private final CommentForm commentForm;

    public IdeaForm(IdeaFactoryApplication app, CommentForm commentForm) {
	this.app = app;
	this.commentForm = commentForm;

	setWriteThrough(false);

	footer = new HorizontalLayout();
	footer.setSpacing(true);
	footer.addComponent(save);
	footer.addComponent(cancel);
	footer.addComponent(edit);
	footer.addComponent(comment);
	footer.addComponent(saveComment);
	footer.addComponent(cancelComment);
	footer.addComponent(area);
	area.setVisible(false);
	saveComment.setVisible(false);
	cancelComment.setVisible(false);
	footer.setVisible(false);
	setFooter(footer);

    }

    @SuppressWarnings("unchecked")
    @Override
    public void buttonClick(ClickEvent event) {

	Button source = event.getButton();

	if (source == save) {

	    commit();
	    if (newIdeaMode) {

		Item addedItem = app.getDataSource().addNewIdea(newIdea);

		setItemDataSource(addedItem);

		newIdeaMode = false;

	    } else {
		app.getDataSource().updateItem(getItemDataSource());
	    }
	    setReadOnly(true);
	    app.showListView();
	} else if (source == cancel) {
	    if (newIdeaMode) {
		newIdeaMode = false;
		setItemDataSource(null);
	    } else {
		discard();
	    }
	    setReadOnly(true);
	} else if (source == edit) {
	    setReadOnly(false);
	} else if (source == comment) {
	    // app.getMainWindow().showNotification("Commented");

	    commentForm
		    .setItemDataSource(((BeanItem<Idea>) getItemDataSource()));
	}

    }

    @Override
    public void setItemDataSource(Item newDataSource) {
	newIdeaMode = false;
	if (newDataSource != null) {

	    List<Object> orderedProperties = Arrays.asList(NATURAL_COL_ORDER);
	    super.setItemDataSource(newDataSource, orderedProperties);

	    setReadOnly(true);
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
	comment.setVisible(readOnly);
	edit.setVisible(readOnly);
    }

    public void setComment(boolean setComment) {
	Boolean boo = false;
	boo = !setComment;
	saveComment.setVisible(boo);
	cancelComment.setVisible(boo);
	area.setVisible(boo);
    }

    public void addIdea(PortalUser creator) {
	// Create a temporary item for the form
	newIdea = new Idea(creator);
	setItemDataSource(new BeanItem<Idea>(newIdea));
	newIdeaMode = true;
	setReadOnly(false);
    }

}
