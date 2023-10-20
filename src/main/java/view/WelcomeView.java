package view;

import interface_adapter.welcome.WelcomeViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WelcomeView extends JPanel implements ActionListener, PropertyChangeListener {

    private final JButton signUp;
    private final JButton signIn;

    public WelcomeView(WelcomeViewModel welcomeViewModel){


        JLabel title = new JLabel(WelcomeViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        signUp = new JButton(welcomeViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        signIn = new JButton(welcomeViewModel.SIGNIN_BUTTON_LABEL);
        buttons.add(signIn);

        signUp.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                }
        );
        signIn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
