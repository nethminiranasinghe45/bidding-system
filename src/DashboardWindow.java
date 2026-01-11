import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DashboardWindow extends JFrame {
    private JList<String> itemList;
    private String currentUser;

    public DashboardWindow(String username) {
        this.currentUser = username;

        setTitle("Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome, " + username);
        welcomeLabel.setBounds(10, 10, 200, 25);
        add(welcomeLabel);

        itemList = new JList<>();
        JScrollPane scrollPane = new JScrollPane(itemList);
        scrollPane.setBounds(10, 50, 360, 200);
        add(scrollPane);

        loadItems();

        itemList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String selectedItem = itemList.getSelectedValue();
                    dispose();
                    new ItemPageWindow( selectedItem).setVisible(true);
                }
            }
        });
    }

    private void loadItems() {
        ItemDAO itemDAO = new ItemDAO();
        List<String> items = itemDAO.getAllItems();
        itemList.setListData(items.toArray(new String[0]));
    }
}

