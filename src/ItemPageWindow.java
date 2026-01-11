
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ItemPageWindow extends JFrame {
    private JComboBox<String> itemDropdown;
    private JTextArea bidHistory;
    private JTextField bidInput;
    private JButton bidButton;
    private JLabel statusLabel;
    private JLabel highestBidLabel;

    private String selectedItem;
    private double highest = 0;

    public ItemPageWindow(String item) {
        this.selectedItem = item;

        setTitle("Auction - Bidding");
        setSize(600, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(20, 20, 20));

        setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        add(contentPanel, BorderLayout.CENTER);

        // Left panel (label + dropdown)
        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel selectLabel = new JLabel("Select Item to Bid:");
        selectLabel.setForeground(Color.YELLOW);
        leftPanel.add(selectLabel);

        itemDropdown = new JComboBox<>(new String[]{"Smart Watch", "Laptop", "Phone"});
        itemDropdown.setMaximumSize(new Dimension(200, 25));
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(itemDropdown);

        contentPanel.add(leftPanel, BorderLayout.WEST);

        // Right panel (bid input + button + status)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setOpaque(false);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        rightPanel.add(new JLabel("Enter your bid:")).setForeground(Color.WHITE);
        bidInput = new JTextField();
        bidInput.setMaximumSize(new Dimension(200, 25));
        rightPanel.add(bidInput);

        bidButton = new JButton("Place Bid");
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(bidButton);

        statusLabel = new JLabel("");
        statusLabel.setForeground(Color.GREEN);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        rightPanel.add(statusLabel);

        contentPanel.add(rightPanel, BorderLayout.EAST);

        // Bottom panel (history + highest)
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Current Highest Bid"));

        bidHistory = new JTextArea();
        bidHistory.setEditable(false);
        JScrollPane scroll = new JScrollPane(bidHistory);
        bottomPanel.add(scroll, BorderLayout.CENTER);

        highestBidLabel = new JLabel("Highest: $0.00");
        highestBidLabel.setForeground(Color.RED);
        highestBidLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPanel.add(highestBidLabel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        loadBids();

        itemDropdown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedItem = (String) itemDropdown.getSelectedItem();
                loadBids();
            }
        });

        bidButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedItem = (String) itemDropdown.getSelectedItem();
                try {
                    double bidAmount = Double.parseDouble(bidInput.getText());

                    if (bidAmount <= highest) {
                        statusLabel.setText("Your bid is too low.");
                        return;
                    }

                    BidDAO bidDAO = new BidDAO();
                    bidDAO.placeBid(selectedItem, Session.userId, bidAmount);
                    statusLabel.setText("Bid placed successfully!");
                    bidInput.setText("");
                    loadBids();

                } catch (NumberFormatException ex) {
                    statusLabel.setText("Invalid bid amount.");
                }
            }
        });
    }

    private void loadBids() {
        BidDAO bidDAO = new BidDAO();
        selectedItem = (String) itemDropdown.getSelectedItem();
        List<Double> bids = bidDAO.getBidsForItem(selectedItem);
        bidHistory.setText("");
        highest = 0;

        for (Double bid : bids) {
            bidHistory.append("$" + bid + "\\n");
            if (bid > highest) highest = bid;
        }

        highestBidLabel.setText("Highest: $" + highest);
    }
}
