package de.bht.se2.ideafactory;

import com.vaadin.Application;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;

import de.bht.se2.ideafactory.dao.EclipseLinkPortalUserDao;
import de.bht.se2.ideafactory.datasources.IdeaContainer;
import de.bht.se2.ideafactory.model.PortalUser;
import de.bht.se2.ideafactory.view.CommentForm;
import de.bht.se2.ideafactory.view.IdeaForm;
import de.bht.se2.ideafactory.view.IdeaList;
import de.bht.se2.ideafactory.view.ListView;
import de.bht.se2.ideafactory.view.NavigationTree;

public class IdeaFactoryApplication extends Application implements
	ClickListener, Property.ValueChangeListener, ItemClickListener {
    private static final long serialVersionUID = -2504849084551618605L;
    private Button search = new Button("Search");
    private Button newIdea = new Button("New Idea");
    private Button share = new Button("Share");
    private Button help = new Button("Help");
    private ListView listView = null;
    private IdeaList ideaList = null;
    private IdeaForm ideaForm = null;
    private CommentForm commentForm = null;
    private NavigationTree tree = new NavigationTree(this);
    private HorizontalSplitPanel horizontalSplit = new HorizontalSplitPanel();
    private IdeaContainer dataSource = IdeaContainer.createWithTestData(null);
    private PortalUser currentUser = null;

    public IdeaContainer getDataSource() {
	return dataSource;
    }

    @Override
    public void init() {
	buildLayout();
	setTheme("contact");
	setMainComponent(getListView());
    }

    private void buildLayout() {

	try {
	    currentUser = new EclipseLinkPortalUserDao().getAll()
		    .get(0);
	} catch (Exception ex) {
	    // TODO do something ... fail
	}
	setMainWindow(new Window("Idea Factory"));
	getMainWindow().addListener(new Window.CloseListener() {
	    private static final long serialVersionUID = 7766888906958823739L;

	    @Override
	    public void windowClose(CloseEvent e) {
		System.out.println("Closing the application");
		getMainWindow().getApplication().close();
	    }
	});

	horizontalSplit.setFirstComponent(tree);

	VerticalLayout layout = new VerticalLayout();
	layout.setSizeFull();

	layout.addComponent(createToolbar());
	layout.addComponent(horizontalSplit);

	layout.setExpandRatio(horizontalSplit, 1);
	horizontalSplit
		.setSplitPosition(100, HorizontalSplitPanel.UNITS_PIXELS);

	getMainWindow().setContent(layout);

    }

    private void setMainComponent(Component c) {
	horizontalSplit.setSecondComponent(c);
    }

    private Component getListView() {
	if (listView == null) {
	    ideaList = new IdeaList(this);
	    commentForm = new CommentForm(this);
	    ideaForm = new IdeaForm(this, commentForm);
	    listView = new ListView(ideaList, ideaForm, commentForm);
	}
	return listView;
    }

    private Component createToolbar() {
	HorizontalLayout lo = new HorizontalLayout();
	lo.addComponent(newIdea);
	lo.addComponent(search);
	lo.addComponent(share);
	lo.addComponent(help);
	lo.setMargin(true);
	lo.setSpacing(true);
	newIdea.addListener((Button.ClickListener) this);

	search.setIcon(new ThemeResource("icons/folder-add.png"));
	share.setIcon(new ThemeResource("icons/users.png"));
	help.setIcon(new ThemeResource("icons/help.png"));
	newIdea.setIcon(new ThemeResource("icons/document-add.png"));

	lo.setMargin(true);
	lo.setSpacing(true);

	lo.setWidth("100%");
	lo.setStyleName("toolbar");

	Embedded em = new Embedded("Weblicationer", new ThemeResource(
		"images/logo.png"));
	lo.addComponent(em);
	lo.setComponentAlignment(em, Alignment.MIDDLE_RIGHT);
	lo.setExpandRatio(em, 1);

	return lo;
    }

    @Override
    public void buttonClick(ClickEvent event) {
	final Button source = event.getButton();

	if (source == newIdea) {
	    addNewIdea();
	}
    }

    public void valueChange(ValueChangeEvent event) {

	Property property = event.getProperty();
	if (property == ideaList) {
	    Item item = ideaList.getItem(ideaList.getValue());
	    if (item != ideaForm.getItemDataSource()) {
		ideaForm.setItemDataSource(item);
	    }
	}
    }

    @Override
    public void itemClick(ItemClickEvent event) {
	// TODO Auto-generated method stub
    }

    private void addNewIdea() {
	setMainComponent((Component) getListView());
	ideaForm.addIdea(currentUser);
    }

    public void showListView() {
	setMainComponent(getListView());
    }

    @Override
    public void close() {
	super.close();
    }

    public PortalUser getCurrentUser() {
	return currentUser;
    }

    public void setCurrentUser(PortalUser currentUser) {
	this.currentUser = currentUser;
    }
}
