package de.bht.se2.ideafactory;



import com.vaadin.Application;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import de.bht.se2.ideafactory.datasources.IdeaContainer;
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
	private NavigationTree tree = new NavigationTree(this);
	private HorizontalSplitPanel horizontalSplit = new HorizontalSplitPanel();
	private IdeaContainer dataSource = IdeaContainer.createWithTestData();

	public IdeaContainer getDataSource() {
		return dataSource;
	}


	@Override
	public void init() {
		buildLayout();
		setTheme("contact");
		setMainComponent((Component) getListView());
	}

	private void buildLayout() {
		setMainWindow(new Window("Idea Factory"));

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

	private Object getListView() {
		if (listView == null) {
			ideaForm = new IdeaForm(this);
			ideaList = new IdeaList(this);
			listView = new ListView(ideaList, ideaForm);
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
		
		
		Embedded em = new Embedded("Weblicationer", new ThemeResource("images/logo.png"));
		lo.addComponent(em);
		lo.setComponentAlignment(em, Alignment.MIDDLE_RIGHT);
		lo.setExpandRatio(em, 1);
		
		return lo;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		final Button source = event.getButton();

		if (source == newIdea) {
//			getMainWindow().addWindow(new CreateIdeaWindow());
			addNewContact();
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
	
	 private void addNewContact() {
         setMainComponent((Component) getListView());
         ideaForm.addContact();
     }
}
