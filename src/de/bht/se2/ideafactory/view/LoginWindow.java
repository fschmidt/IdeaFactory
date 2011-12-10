package de.bht.se2.ideafactory.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class LoginWindow extends Window {

    private static final long serialVersionUID = -3613491557527984906L;

    private LoginWindow       window;

    private Button            loginButton;
    private TextField         userName;
    private PasswordField     password;

    @SuppressWarnings("serial")
    public LoginWindow() {

        window = this;

        setModal(true);

        setWidth("50%");

        center();

        setCaption("Login");
        addComponent(new Label("Please log in by using your name and password."));

        loginButton = new Button("Login");

        userName = new TextField();
        password = new PasswordField();

        VerticalLayout vl = new VerticalLayout();
        HorizontalLayout nameLayout = new HorizontalLayout();
        HorizontalLayout passwordLayout = new HorizontalLayout();
        nameLayout.addComponent(new Label("Username: "));
        nameLayout.addComponent(userName);
        passwordLayout.addComponent(new Label("Password: "));
        passwordLayout.addComponent(password);
        vl.addComponent(nameLayout);
        vl.addComponent(passwordLayout);
        vl.addComponent(loginButton);

        addComponent(vl);

        loginButton.addListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                window.close();
            }
        });
    }
}