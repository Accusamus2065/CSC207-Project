package view;

import interface_adapter.swap_views.list_of_patients.SwapToPatientListController;
import interface_adapter.update.doctor.DoctorUpdateController;
import interface_adapter.update.doctor.DoctorUpdateState;
import interface_adapter.update.doctor.DoctorUpdateViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DoctorUpdateView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName;
    private final JButton backButton;
    private JTextField username = null;
    private JPasswordField password = null;
    private JTextField repeatPassword = null;
    private JTextField specialty = null;
    private JTextField degree = null;
    private final JButton saveButton;

    public DoctorUpdateView(DoctorUpdateViewModel doctorUpdateViewModel,
                            SwapToPatientListController swapController,
                            DoctorUpdateController updateController) {
        this.viewName = doctorUpdateViewModel.getViewName();
        doctorUpdateViewModel.addPropertyChangeListener(this);

        // Create and do settings for main panel
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
        this.setBackground(Color.lightGray);
        this.setPreferredSize(DoctorUpdateViewModel.PANEL_DIMENSION);

        // Create the upper sub-panel that will contain the button to go back
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 10));
        upperPanel.setBackground(Color.lightGray);
        this.add(upperPanel, BorderLayout.WEST);

        // Create the button for logging out of the profile
        backButton = new JButton(DoctorUpdateViewModel.BACK_BUTTON_LABEL);
        backButton.setFont(DoctorUpdateViewModel.BUTTON_FONT);
        backButton.setFocusable(false);
        backButton.setPreferredSize(DoctorUpdateViewModel.BUTTON_DIMENSION);
        upperPanel.add(backButton);
        backButton.addActionListener(e -> {
            swapController.execute();
            username.setText("");
            password.setText("");
            repeatPassword.setText("");
            specialty.setText("");
            degree.setText("");
        });

        // Create a sub-panel for the username text field
        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 7));
        usernamePanel.setBackground(Color.lightGray);
        this.add(usernamePanel, BorderLayout.CENTER);

        // Create and do settings for the username text field
        username = new JTextField(20);
        username.setFont(DoctorUpdateViewModel.INPUT_FIELD_FONT);
        username.setToolTipText("Enter your new username");
        JLabel usernameLabel = new JLabel(DoctorUpdateViewModel.USERNAME_FIELD_LABEL);
        usernameLabel.setFont(DoctorUpdateViewModel.INPUT_FIELD_FONT);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(username);
        username.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                DoctorUpdateState currentState = doctorUpdateViewModel.getState();
                String text = username.getText() + e.getKeyChar();
                currentState.setNewUsername(text);
                doctorUpdateViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        // Create a sub-panel for the password text field
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 7));
        passwordPanel.setBackground(Color.lightGray);
        this.add(passwordPanel, BorderLayout.CENTER);

        // Create and do settings for the username text field
        password = new JPasswordField(20);
        password.setFont(DoctorUpdateViewModel.INPUT_FIELD_FONT);
        password.setToolTipText("Enter your new password");
        JLabel passwordLabel = new JLabel(DoctorUpdateViewModel.PASSWORD_FIELD_LABEL);
        passwordLabel.setFont(DoctorUpdateViewModel.INPUT_FIELD_FONT);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(password);
        password.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                DoctorUpdateState currentState = doctorUpdateViewModel.getState();
                String text = password.getText() + e.getKeyChar();
                currentState.setPassword(text);
                doctorUpdateViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        // Create a sub-panel for the repeat password text field
        JPanel repeatPasswordPanel = new JPanel();
        repeatPasswordPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 7));
        repeatPasswordPanel.setBackground(Color.lightGray);
        this.add(repeatPasswordPanel, BorderLayout.CENTER);

        // Create and do settings for the username text field
        repeatPassword = new JPasswordField(20);
        repeatPassword.setFont(DoctorUpdateViewModel.INPUT_FIELD_FONT);
        repeatPassword.setToolTipText("Enter your new password again");
        JLabel repeatPasswordLabel = new JLabel(DoctorUpdateViewModel.REPEAT_PASSWORD_FIELD_LABEL);
        repeatPasswordLabel.setFont(DoctorUpdateViewModel.INPUT_FIELD_FONT);
        repeatPasswordPanel.add(repeatPasswordLabel);
        repeatPasswordPanel.add(repeatPassword);
        repeatPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                DoctorUpdateState currentState = doctorUpdateViewModel.getState();
                String text = repeatPassword.getText() + e.getKeyChar();
                currentState.setRepeatPassword(text);
                doctorUpdateViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        // Create a sub-panel for the specialty text field
        JPanel specialtyPanel = new JPanel();
        specialtyPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 7));
        specialtyPanel.setBackground(Color.lightGray);
        this.add(specialtyPanel, BorderLayout.CENTER);

        // Create and do settings for the username text field
        specialty = new JTextField(20);
        specialty.setFont(DoctorUpdateViewModel.INPUT_FIELD_FONT);
        specialty.setToolTipText("Enter your new specialty");
        JLabel specialtyLabel = new JLabel(DoctorUpdateViewModel.SPECIALTY_FIELD_LABEL);
        specialtyLabel.setFont(DoctorUpdateViewModel.INPUT_FIELD_FONT);
        specialtyPanel.add(specialtyLabel);
        specialtyPanel.add(specialty);
        specialty.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                DoctorUpdateState currentState = doctorUpdateViewModel.getState();
                String text = specialty.getText() + e.getKeyChar();
                currentState.setSpecialty(text);
                doctorUpdateViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        // Create a sub-panel for the specialty text field
        JPanel degreePanel = new JPanel();
        degreePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 7));
        degreePanel.setBackground(Color.lightGray);
        this.add(degreePanel, BorderLayout.CENTER);

        // Create and do settings for the degree text field
        degree = new JTextField(20);
        degree.setFont(DoctorUpdateViewModel.INPUT_FIELD_FONT);
        degree.setToolTipText("Enter your new degree");
        JLabel degreeLabel = new JLabel(DoctorUpdateViewModel.DEGREE_FIELD_LABEL);
        degreeLabel.setFont(DoctorUpdateViewModel.INPUT_FIELD_FONT);
        degreePanel.add(degreeLabel);
        degreePanel.add(degree);
        degree.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                DoctorUpdateState currentState = doctorUpdateViewModel.getState();
                String text = degree.getText() + e.getKeyChar();
                currentState.setDegree(text);
                doctorUpdateViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        // Create the upper sub-panel that will contain the button to save
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 630, 25));
        lowerPanel.setBackground(Color.lightGray);
        this.add(lowerPanel, BorderLayout.EAST);

        // Create the button for saving
        saveButton = new JButton(DoctorUpdateViewModel.SAVE_BUTTON_LABEL);
        saveButton.setFont(DoctorUpdateViewModel.BUTTON_FONT);
        saveButton.setFocusable(false);
        saveButton.setPreferredSize(DoctorUpdateViewModel.BUTTON_DIMENSION);
        lowerPanel.add(saveButton);
        saveButton.addActionListener(e -> {
            DoctorUpdateState currentState = doctorUpdateViewModel.getState();
            updateController.execute(
                    currentState.getUsername(),
                    currentState.getNewUsername(),
                    currentState.getPassword(),
                    currentState.getRepeatPassword(),
                    currentState.getSpecialty(),
                    currentState.getDegree());
            username.setText("");
            password.setText("");
            repeatPassword.setText("");
            specialty.setText("");
            degree.setText("");
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        DoctorUpdateState state = (DoctorUpdateState) evt.getNewValue();
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError());
        }
    }
}
