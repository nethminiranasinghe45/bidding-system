
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;

    public LoginWindow() {
        setTitle("Auction System - Login");
        setSize(350, 220);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(0, 102, 204));
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Login to Auction System", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(Color.WHITE);
        add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        formPanel.add(new JLabel("Username:", JLabel.RIGHT));
        usernameField = new JTextField();
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Password:", JLabel.RIGHT));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        formPanel.add(new JLabel(""));
        loginButton = new JButton("Login");
        formPanel.add(loginButton);

        add(formPanel, BorderLayout.CENTER);

        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setForeground(Color.YELLOW);
        add(statusLabel, BorderLayout.SOUTH);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                UserDAO userDAO = new UserDAO();
                if (true) {
                    Session.userId = 1;
                    Session.username = username;
                    dispose();
                    new ItemPageWindow("Smart Watch").setVisible(true);

                    //  int userId = userDAO.getUserId(username);
                    //if (userId != -1) {
                      //  Session.userId = userId;
                   //     Session.username = username;
                    //    dispose();
                    //    new ItemPageWindow("Smart Watch").setVisible(true);
               //     } else {
               //         statusLabel.setText("User not found.");
                    }
         //       } else {
            //        statusLabel.setText("Invalid username or password.");
            //    }
            }
        });
    }
}
