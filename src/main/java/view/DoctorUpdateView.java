package view;

import interface_adapter.update.doctor.DoctorUpdateViewModel;

import javax.swing.*;
import java.awt.*;

public class DoctorUpdateView {
    private JFrame frame;
    private JPanel panel;
    private JButton backButton;
    private String oldUsername;
    private JTextField username;
    private JPasswordField password;
    private JTextField repeatPassword;
    private JTextField specialty;
    private JTextField degree;
    private JButton saveButton;

    DoctorUpdateView() {
        // Create and do settings for frame
        frame = new JFrame();
        frame.setTitle(DoctorUpdateViewModel.TITLE_LABEL);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(DoctorUpdateViewModel.FRAME_WIDTH_SIZE, DoctorUpdateViewModel.FRAME_HEIGHT_SIZE);
        frame.setLocationRelativeTo(null);

        // Create and do settings for main panel
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
        panel.setBackground(Color.lightGray);
        panel.setPreferredSize(DoctorUpdateViewModel.PANEL_DIMENSION);
        frame.add(panel, BorderLayout.CENTER);

        // Create the upper sub-panel that will contain the button to go back
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 10));
        upperPanel.setBackground(Color.lightGray);
        panel.add(upperPanel, BorderLayout.WEST);

        // Create the button for logging out of the profile
        backButton = new JButton(DoctorUpdateViewModel.BACK_BUTTON_LABEL);
        backButton.setFont(DoctorUpdateViewModel.BUTTON_FONT);
        backButton.setFocusable(false);
        backButton.setPreferredSize(DoctorUpdateViewModel.BUTTON_DIMENSION);
        upperPanel.add(backButton);

        // Create a sub-panel for the username text field
        JPanel usernamePanel = new JPanel();
        usernamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 7));
        usernamePanel.setBackground(Color.lightGray);
        panel.add(usernamePanel, BorderLayout.CENTER);

        // Create and do settings for the username text field
        username = new JTextField(20);
        username.setFont(DoctorUpdateViewModel.INPUT_FIELD_FONT);
        username.setToolTipText("Enter your new username");
        JLabel usernameLabel = new JLabel(DoctorUpdateViewModel.USERNAME_FIELD_LABEL);
        usernameLabel.setFont(DoctorUpdateViewModel.INPUT_FIELD_FONT);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(username);

        // Create a sub-panel for the password text field
        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 7));
        passwordPanel.setBackground(Color.lightGray);
        panel.add(passwordPanel, BorderLayout.CENTER);

        // Create and do settings for the username text field
        password = new JPasswordField(20);
        password.setFont(DoctorUpdateViewModel.INPUT_FIELD_FONT);
        password.setToolTipText("Enter your new password");
        JLabel passwordLabel = new JLabel(DoctorUpdateViewModel.PASSWORD_FIELD_LABEL);
        passwordLabel.setFont(DoctorUpdateViewModel.INPUT_FIELD_FONT);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(password);

        // Create a sub-panel for the repeat password text field
        JPanel repeatPasswordPanel = new JPanel();
        repeatPasswordPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 7));
        repeatPasswordPanel.setBackground(Color.lightGray);
        panel.add(repeatPasswordPanel, BorderLayout.CENTER);

        // Create and do settings for the username text field
        repeatPassword = new JPasswordField(20);
        repeatPassword.setFont(DoctorUpdateViewModel.INPUT_FIELD_FONT);
        repeatPassword.setToolTipText("Enter your new password again");
        JLabel repeatPasswordLabel = new JLabel(DoctorUpdateViewModel.REPEAT_PASSWORD_FIELD_LABEL);
        repeatPasswordLabel.setFont(DoctorUpdateViewModel.INPUT_FIELD_FONT);
        repeatPasswordPanel.add(repeatPasswordLabel);
        repeatPasswordPanel.add(repeatPassword);

        // Create a sub-panel for the specialty text field
        JPanel specialtyPanel = new JPanel();
        specialtyPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 7));
        specialtyPanel.setBackground(Color.lightGray);
        panel.add(specialtyPanel, BorderLayout.CENTER);

        // Create and do settings for the username text field
        specialty = new JTextField(20);
        specialty.setFont(DoctorUpdateViewModel.INPUT_FIELD_FONT);
        specialty.setToolTipText("Enter your new specialty");
        JLabel specialtyLabel = new JLabel(DoctorUpdateViewModel.SPECIALTY_FIELD_LABEL);
        specialtyLabel.setFont(DoctorUpdateViewModel.INPUT_FIELD_FONT);
        specialtyPanel.add(specialtyLabel);
        specialtyPanel.add(specialty);

        // Create a sub-panel for the specialty text field
        JPanel degreePanel = new JPanel();
        degreePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 7));
        degreePanel.setBackground(Color.lightGray);
        panel.add(degreePanel, BorderLayout.CENTER);

        // Create and do settings for the username text field
        degree = new JTextField(20);
        degree.setFont(DoctorUpdateViewModel.INPUT_FIELD_FONT);
        degree.setToolTipText("Enter your new degree");
        JLabel degreeLabel = new JLabel(DoctorUpdateViewModel.DEGREE_FIELD_LABEL);
        degreeLabel.setFont(DoctorUpdateViewModel.INPUT_FIELD_FONT);
        degreePanel.add(degreeLabel);
        degreePanel.add(degree);

        // Create the upper sub-panel that will contain the button to go back
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 630, 25));
        lowerPanel.setBackground(Color.lightGray);
        panel.add(lowerPanel, BorderLayout.EAST);

        // Create the button for logging out of the profile
        saveButton = new JButton(DoctorUpdateViewModel.SAVE_BUTTON_LABEL);
        saveButton.setFont(DoctorUpdateViewModel.BUTTON_FONT);
        saveButton.setFocusable(false);
        saveButton.setPreferredSize(DoctorUpdateViewModel.BUTTON_DIMENSION);
        lowerPanel.add(saveButton);
    }
    public void show(){
        frame.setVisible(true);
    }
}
