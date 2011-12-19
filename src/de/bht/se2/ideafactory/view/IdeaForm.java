package de.bht.se2.ideafactory.view;

import java.util.Arrays;
import java.util.List;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;

import de.bht.se2.ideafactory.IdeaFactoryApplication;
import de.bht.se2.ideafactory.dao.EclipseLinkIdeaDAO;
import de.bht.se2.ideafactory.datasources.IdeaContainer;
import de.bht.se2.ideafactory.model.Idea;

public class IdeaForm extends Form implements ClickListener {

	private static final long serialVersionUID = 3767736508811705893L;
	private Button save = new Button("Save", (ClickListener) this);
	private Button cancel = new Button("Cancel", (ClickListener) this);
	private Button edit = new Button("Edit Idea", (ClickListener) this);
	private Button comment = new Button("Comment", (ClickListener) this);
	private Button saveComment = new Button("Save Comment", (ClickListener) this);
	private Button cancelComment = new Button("Cancel", (ClickListener) this);
	private IdeaFactoryApplication app;
	private Idea newIdea = null;
	public boolean newContactMode = false;
	HorizontalLayout footer;
	private TextArea area = new TextArea("insert comment");

	public IdeaForm(IdeaFactoryApplication app) {
		this.app = app;

		// Enable buffering so that commit() must be called for the form
		// before input is written to the data. (Form input is not written
		// immediately through to the underlying object.)
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

	@Override
	public void buttonClick(ClickEvent event) {

		Button source = event.getButton();
		EclipseLinkIdeaDAO dao = new EclipseLinkIdeaDAO();

		if (source == save) {
			/* If the given input is not valid there is no point in continuing */
			if (!isValid()) {
				return;
			}
			if (newContactMode) {
				/* We need to add the new person to the container */
				System.out.println("in contact mode");
				Item addedItem = app.getDataSource().addItem(newIdea);

				setItemDataSource(addedItem);
				dao.createIdea(newIdea);

				getWindow().showNotification(
						"New Idea:  " + app.getDataSource().getItem(newIdea)
								+ "added", Notification.TYPE_TRAY_NOTIFICATION);

				newContactMode = false;

			}
			dao.update(newIdea);
			commit();
			setReadOnly(true);
		} else if (source == cancel) {
			if (newContactMode) {
				newContactMode = false;
				setItemDataSource(null);
			} else {
				discard();
			}
			setReadOnly(true);
		} else if (source == edit) {
			setReadOnly(false);
		} else if (source == comment){
			setComment(false);
		} else if (source == cancelComment){
			setComment(true);
		} else if (source == saveComment){
			dao.update(newIdea);
			commit();
			setReadOnly(true);
		}

	}

	@Override
	public void setItemDataSource(Item newDataSource) {
		if (newDataSource != null) {
			
			List<Object> orderedProperties = Arrays
					.asList(IdeaContainer.NATURAL_COL_ORDER);
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
	
	public void setComment(boolean setComment){
		Boolean boo = false;
		boo =! setComment;		
		saveComment.setVisible(boo);
		cancelComment.setVisible(boo);
		area.setVisible(boo);
	}

	public void addContact() {
		// Create a temporary item for the form
		newIdea = new Idea();
		setItemDataSource(new BeanItem<Idea>(newIdea));
		newContactMode = true;
		setReadOnly(false);
	}

}
