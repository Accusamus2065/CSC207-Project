//package view;
//
//import interface_adapter.swap_views.chatbot.SwapToDialogflowController;
//import interface_adapter.update.patient.PatientUpdateController;
//import interface_adapter.update.patient.PatientUpdateState;
//import interface_adapter.update.patient.PatientUpdateViewModel;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
//
//public class PatientUpdateView extends JPanel implements ActionListener, PropertyChangeListener {
//    public final String viewName;
//    private final JButton backButton;
//    private final JTextField username;
//    private final JPasswordField password;
//    private final JPasswordField repeatPassword;
//    private final JTextField sex;
//    private final JTextField gender;
//    private final JTextField height;
//    private final JTextField weight;
//    private final JTextField bloodType;
//    private final JButton saveButton;
//
//    // Create and do settings for frame
//    public PatientUpdateView(PatientUpdateViewModel patientUpdateViewModel,
//                             SwapToDialogflowController swapController,
//                             PatientUpdateController updateController) {
//        this.viewName = patientUpdateViewModel.getViewName();
//        patientUpdateViewModel.addPropertyChangeListener(this);
//
//        // Create and do settings for main panel
//        this.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 3));
//        this.setBackground(Color.lightGray);
//        this.setPreferredSize(PatientUpdateViewModel.PANEL_DIMENSION);
//
//        // Create the upper sub-panel that will contain the button to go back
//        JPanel upperPanel = new JPanel();
//        upperPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));
//        upperPanel.setBackground(Color.lightGray);
//        this.add(upperPanel, BorderLayout.WEST);
//
//        // Create the button for going back
//        backButton = new JButton(PatientUpdateViewModel.BACK_BUTTON_LABEL);
//        backButton.setFont(PatientUpdateViewModel.BUTTON_FONT);
//        backButton.setFocusable(false);
//        backButton.setPreferredSize(PatientUpdateViewModel.BUTTON_DIMENSION);
//        upperPanel.add(backButton);
//        backButton.addActionListener(e -> swapController.execute());
//
//        // Create a sub-panel for the username text field
//        JPanel usernamePanel = new JPanel();
//        usernamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
//        usernamePanel.setBackground(Color.lightGray);
//        this.add(usernamePanel, BorderLayout.CENTER);
//
//        // Create and do settings for the username text field
//        username = new JTextField(20);
//        username.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
//        username.setToolTipText("Enter your new username");
//        JLabel usernameLabel = new JLabel(PatientUpdateViewModel.USERNAME_FIELD_LABEL);
//        usernameLabel.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
//        usernamePanel.add(usernameLabel);
//        usernamePanel.add(username);
//        username.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                PatientUpdateState currentState = patientUpdateViewModel.getState();
//                String text = username.getText() + e.getKeyChar();
//                currentState.setNewUsername(text);
//                patientUpdateViewModel.setState(currentState);
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//
//            }
//        });
//
//        // Create a sub-panel for the password text field
//        JPanel passwordPanel = new JPanel();
//        passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
//        passwordPanel.setBackground(Color.lightGray);
//        this.add(passwordPanel, BorderLayout.CENTER);
//
//        // Create and do settings for the username text field
//        password = new JPasswordField(20);
//        password.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
//        password.setToolTipText("Enter your new password");
//        JLabel passwordLabel = new JLabel(PatientUpdateViewModel.PASSWORD_FIELD_LABEL);
//        passwordLabel.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
//        passwordPanel.add(passwordLabel);
//        passwordPanel.add(password);
//        password.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                PatientUpdateState currentState = patientUpdateViewModel.getState();
//                String text = password.getText() + e.getKeyChar();
//                currentState.setPassword(text);
//                patientUpdateViewModel.setState(currentState);
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//
//            }
//        });
//
//        // Create a sub-panel for the repeat password text field
//        JPanel repeatPasswordPanel = new JPanel();
//        repeatPasswordPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
//        repeatPasswordPanel.setBackground(Color.lightGray);
//        this.add(repeatPasswordPanel, BorderLayout.CENTER);
//
//        // Create and do settings for the username text field
//        repeatPassword = new JPasswordField(20);
//        repeatPassword.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
//        repeatPassword.setToolTipText("Enter your new password again");
//        JLabel repeatPasswordLabel = new JLabel(PatientUpdateViewModel.REPEAT_PASSWORD_FIELD_LABEL);
//        repeatPasswordLabel.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
//        repeatPasswordPanel.add(repeatPasswordLabel);
//        repeatPasswordPanel.add(repeatPassword);
//        repeatPassword.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                PatientUpdateState currentState = patientUpdateViewModel.getState();
//                String text = repeatPassword.getText() + e.getKeyChar();
//                currentState.setRepeatPassword(text);
//                patientUpdateViewModel.setState(currentState);
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//
//            }
//        });
//
//        // Create a sub-panel for the sex text field
//        JPanel sexPanel = new JPanel();
//        sexPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
//        sexPanel.setBackground(Color.lightGray);
//        this.add(sexPanel, BorderLayout.CENTER);
//
//        // Create and do settings for the sex text field
//        sex = new JTextField(20);
//        sex.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
//        sex.setToolTipText("Enter your new sex");
//        JLabel sexLabel = new JLabel(PatientUpdateViewModel.SEX_FIELD_LABEL);
//        sexLabel.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
//        sexPanel.add(sexLabel);
//        sexPanel.add(sex);
//        sex.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                PatientUpdateState currentState = patientUpdateViewModel.getState();
//                String text = sex.getText() + e.getKeyChar();
//                currentState.setSex(text);
//                patientUpdateViewModel.setState(currentState);
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//
//            }
//        });
//
//        // Create a sub-panel for the gender text field
//        JPanel genderPanel = new JPanel();
//        genderPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
//        genderPanel.setBackground(Color.lightGray);
//        this.add(genderPanel, BorderLayout.CENTER);
//
//        // Create and do settings for the sex text field
//        gender = new JTextField(20);
//        gender.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
//        gender.setToolTipText("Enter your new gender");
//        JLabel genderLabel = new JLabel(PatientUpdateViewModel.GENDER_FIELD_LABEL);
//        genderLabel.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
//        genderPanel.add(genderLabel);
//        genderPanel.add(gender);
//        gender.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                PatientUpdateState currentState = patientUpdateViewModel.getState();
//                String text = gender.getText() + e.getKeyChar();
//                currentState.setGender(text);
//                patientUpdateViewModel.setState(currentState);
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//
//            }
//        });
//
//        // Create a sub-panel for the height text field
//        JPanel heightPanel = new JPanel();
//        heightPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
//        heightPanel.setBackground(Color.lightGray);
//        this.add(heightPanel, BorderLayout.CENTER);
//
//        // Create and do settings for the height text field
//        height = new JTextField(20);
//        height.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
//        height.setToolTipText("Enter your new height");
//        JLabel heightLabel = new JLabel(PatientUpdateViewModel.HEIGHT_FIELD_LABEL);
//        heightLabel.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
//        heightPanel.add(heightLabel);
//        heightPanel.add(height);
//        height.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//
//            }
//        });
//
//        // Create a sub-panel for the weight text field
//        JPanel weightPanel = new JPanel();
//        weightPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
//        weightPanel.setBackground(Color.lightGray);
//        this.add(weightPanel, BorderLayout.CENTER);
//
//        // Create and do settings for the sex text field
//        weight = new JTextField(20);
//        weight.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
//        weight.setToolTipText("Enter your new weight");
//        JLabel weightLabel = new JLabel(PatientUpdateViewModel.WEIGHT_FIELD_LABEL);
//        weightLabel.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
//        weightPanel.add(weightLabel);
//        weightPanel.add(weight);
//        weight.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//
//            }
//        });
//
//        // Create a sub-panel for the weight text field
//        JPanel bloodTypePanel = new JPanel();
//        bloodTypePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
//        bloodTypePanel.setBackground(Color.lightGray);
//        this.add(bloodTypePanel, BorderLayout.CENTER);
//
//        // Create and do settings for the sex text field
//        bloodType = new JTextField(20);
//        bloodType.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
//        bloodType.setToolTipText("Enter your new blood type");
//        JLabel bloodTypeLabel = new JLabel(PatientUpdateViewModel.BLOOD_TYPE_FIELD_LABEL);
//        bloodTypeLabel.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
//        bloodTypePanel.add(bloodTypeLabel);
//        bloodTypePanel.add(bloodType);
//        bloodType.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                PatientUpdateState currentState = patientUpdateViewModel.getState();
//                String text = bloodType.getText() + e.getKeyChar();
//                currentState.setBloodType(text);
//                patientUpdateViewModel.setState(currentState);
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//
//            }
//        });
//
//        // Create the upper sub-panel that will contain the button to go back
//        JPanel lowerPanel = new JPanel();
//        lowerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 630, 5));
//        lowerPanel.setBackground(Color.lightGray);
//        this.add(lowerPanel, BorderLayout.EAST);
//
//        // Create the button for logging out of the profile
//        saveButton = new JButton(PatientUpdateViewModel.SAVE_BUTTON_LABEL);
//        saveButton.setFont(PatientUpdateViewModel.BUTTON_FONT);
//        saveButton.setFocusable(false);
//        saveButton.setPreferredSize(PatientUpdateViewModel.BUTTON_DIMENSION);
//        lowerPanel.add(saveButton);
//        saveButton.addActionListener(e -> {
//            PatientUpdateState currentState = patientUpdateViewModel.getState();
//            updateController.execute(
//                    currentState.getUsername(),
//                    currentState.getNewUsername(),
//                    currentState.getPassword(),
//                    currentState.getRepeatPassword(),
//                    currentState.getSex(),
//                    currentState.getGender(),
//                    currentState.getHeight(),
//                    currentState.getWeight(),
//                    currentState.getBloodType()
//            );
//        });
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        System.out.println("Click " + e.getActionCommand());
//    }
//
//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        PatientUpdateState state = (PatientUpdateState) evt.getNewValue();
//        if (state.getError() != null) {
//            JOptionPane.showMessageDialog(this, state.getError());
//        }
//    }
//}

package view;

import interface_adapter.swap_views.chatbot.SwapToDialogflowController;
import interface_adapter.update.patient.PatientUpdateController;
import interface_adapter.update.patient.PatientUpdateState;
import interface_adapter.update.patient.PatientUpdateViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PatientUpdateView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName;
    private final JButton backButton;
    private final JTextField username;
    private final JPasswordField password;
    private final JPasswordField repeatPassword;
    private final JTextField sex;
    private final JTextField gender;
    private final JTextField height;
    private final JTextField weight;
    private final JTextField bloodType;
    private final JButton saveButton;

    // Create and do settings for frame
    public PatientUpdateView(PatientUpdateViewModel patientUpdateViewModel,
                             SwapToDialogflowController swapController,
                             PatientUpdateController updateController) {
        this.viewName = patientUpdateViewModel.getViewName();
        patientUpdateViewModel.addPropertyChangeListener(this);

        // Create and do settings for main panel
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 3));
        this.setBackground(Color.lightGray);
        this.setPreferredSize(PatientUpdateViewModel.PANEL_DIMENSION);

        // Create the upper sub-panel that will contain the button to go back
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));
        upperPanel.setBackground(Color.lightGray);
        this.add(upperPanel, BorderLayout.WEST);

        // Create the button for going back
        backButton = new JButton(PatientUpdateViewModel.BACK_BUTTON_LABEL);
        backButton.setFont(PatientUpdateViewModel.BUTTON_FONT);
        backButton.setFocusable(false);
        backButton.setPreferredSize(PatientUpdateViewModel.BUTTON_DIMENSION);
        upperPanel.add(backButton);
        backButton.addActionListener(e -> swapController.execute());

        // Create a sub-panel for the username text field
        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
        usernamePanel.setBackground(Color.lightGray);
        this.add(usernamePanel, BorderLayout.CENTER);

        // Create and do settings for the username text field
        username = new JTextField(20);
        username.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
        username.setToolTipText("Enter your new username");
        JLabel usernameLabel = new JLabel(PatientUpdateViewModel.USERNAME_FIELD_LABEL);
        usernameLabel.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(username);
        username.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                PatientUpdateState currentState = patientUpdateViewModel.getState();
                String text = username.getText() + e.getKeyChar();
                currentState.setNewUsername(text);
                patientUpdateViewModel.setState(currentState);
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
        passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
        passwordPanel.setBackground(Color.lightGray);
        this.add(passwordPanel, BorderLayout.CENTER);

        // Create and do settings for the username text field
        password = new JPasswordField(20);
        password.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
        password.setToolTipText("Enter your new password");
        JLabel passwordLabel = new JLabel(PatientUpdateViewModel.PASSWORD_FIELD_LABEL);
        passwordLabel.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(password);
        password.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                PatientUpdateState currentState = patientUpdateViewModel.getState();
                String text = password.getText() + e.getKeyChar();
                currentState.setPassword(text);
                patientUpdateViewModel.setState(currentState);
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
        repeatPasswordPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
        repeatPasswordPanel.setBackground(Color.lightGray);
        this.add(repeatPasswordPanel, BorderLayout.CENTER);

        // Create and do settings for the username text field
        repeatPassword = new JPasswordField(20);
        repeatPassword.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
        repeatPassword.setToolTipText("Enter your new password again");
        JLabel repeatPasswordLabel = new JLabel(PatientUpdateViewModel.REPEAT_PASSWORD_FIELD_LABEL);
        repeatPasswordLabel.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
        repeatPasswordPanel.add(repeatPasswordLabel);
        repeatPasswordPanel.add(repeatPassword);
        repeatPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                PatientUpdateState currentState = patientUpdateViewModel.getState();
                String text = repeatPassword.getText() + e.getKeyChar();
                currentState.setRepeatPassword(text);
                patientUpdateViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        // Create a sub-panel for the sex text field
        JPanel sexPanel = new JPanel();
        sexPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
        sexPanel.setBackground(Color.lightGray);
        this.add(sexPanel, BorderLayout.CENTER);

        // Create and do settings for the sex text field
        sex = new JTextField(20);
        sex.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
        sex.setToolTipText("Enter your new sex");
        JLabel sexLabel = new JLabel(PatientUpdateViewModel.SEX_FIELD_LABEL);
        sexLabel.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
        sexPanel.add(sexLabel);
        sexPanel.add(sex);
        sex.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                PatientUpdateState currentState = patientUpdateViewModel.getState();
                String text = sex.getText() + e.getKeyChar();
                currentState.setSex(text);
                patientUpdateViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        // Create a sub-panel for the gender text field
        JPanel genderPanel = new JPanel();
        genderPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
        genderPanel.setBackground(Color.lightGray);
        this.add(genderPanel, BorderLayout.CENTER);

        // Create and do settings for the sex text field
        gender = new JTextField(20);
        gender.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
        gender.setToolTipText("Enter your new gender");
        JLabel genderLabel = new JLabel(PatientUpdateViewModel.GENDER_FIELD_LABEL);
        genderLabel.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
        genderPanel.add(genderLabel);
        genderPanel.add(gender);
        gender.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                PatientUpdateState currentState = patientUpdateViewModel.getState();
                String text = gender.getText() + e.getKeyChar();
                currentState.setGender(text);
                patientUpdateViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        // Create a sub-panel for the height text field
        JPanel heightPanel = new JPanel();
        heightPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
        heightPanel.setBackground(Color.lightGray);
        this.add(heightPanel, BorderLayout.CENTER);

        // Create and do settings for the height text field
        height = new JTextField(20);
        height.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
        height.setToolTipText("Enter your new height");
        JLabel heightLabel = new JLabel(PatientUpdateViewModel.HEIGHT_FIELD_LABEL);
        heightLabel.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
        heightPanel.add(heightLabel);
        heightPanel.add(height);
        height.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        // Create a sub-panel for the weight text field
        JPanel weightPanel = new JPanel();
        weightPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
        weightPanel.setBackground(Color.lightGray);
        this.add(weightPanel, BorderLayout.CENTER);

        // Create and do settings for the sex text field
        weight = new JTextField(20);
        weight.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
        weight.setToolTipText("Enter your new weight");
        JLabel weightLabel = new JLabel(PatientUpdateViewModel.WEIGHT_FIELD_LABEL);
        weightLabel.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
        weightPanel.add(weightLabel);
        weightPanel.add(weight);
        weight.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        // Create a sub-panel for the weight text field
        JPanel bloodTypePanel = new JPanel();
        bloodTypePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
        bloodTypePanel.setBackground(Color.lightGray);
        this.add(bloodTypePanel, BorderLayout.CENTER);

        // Create and do settings for the sex text field
        bloodType = new JTextField(20);
        bloodType.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
        bloodType.setToolTipText("Enter your new blood type");
        JLabel bloodTypeLabel = new JLabel(PatientUpdateViewModel.BLOOD_TYPE_FIELD_LABEL);
        bloodTypeLabel.setFont(PatientUpdateViewModel.INPUT_FIELD_FONT);
        bloodTypePanel.add(bloodTypeLabel);
        bloodTypePanel.add(bloodType);
        bloodType.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                PatientUpdateState currentState = patientUpdateViewModel.getState();
                String text = bloodType.getText() + e.getKeyChar();
                currentState.setBloodType(text);
                patientUpdateViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        // Create the upper sub-panel that will contain the button to go back
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 630, 5));
        lowerPanel.setBackground(Color.lightGray);
        this.add(lowerPanel, BorderLayout.EAST);

        // Create the button for logging out of the profile
        saveButton = new JButton(PatientUpdateViewModel.SAVE_BUTTON_LABEL);
        saveButton.setFont(PatientUpdateViewModel.BUTTON_FONT);
        saveButton.setFocusable(false);
        saveButton.setPreferredSize(PatientUpdateViewModel.BUTTON_DIMENSION);
        lowerPanel.add(saveButton);
        saveButton.addActionListener(e -> {
            PatientUpdateState currentState = patientUpdateViewModel.getState();
            updateController.execute(
                    currentState.getUsername(),
                    currentState.getNewUsername(),
                    currentState.getPassword(),
                    currentState.getRepeatPassword(),
                    currentState.getSex(),
                    currentState.getGender(),
                    currentState.getHeight(),
                    currentState.getWeight(),
                    currentState.getBloodType()
            );
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        PatientUpdateState state = (PatientUpdateState) evt.getNewValue();
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError());
        }
    }
}