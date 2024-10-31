import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HotelReservationSystem extends JFrame {
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private JTextArea bookingInfoArea;

    public HotelReservationSystem() {
        // Initialize rooms and GUI
        initializeRooms();
        setTitle("Hotel Reservation System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create tabs
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Room Booking", createRoomBookingPanel());
        tabbedPane.add("Room Availability", createRoomAvailabilityPanel());
        tabbedPane.add("Customer Management", createCustomerManagementPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

    private void initializeRooms() {
        rooms.add(new Room(101, "Single"));
        rooms.add(new Room(102, "Single"));
        rooms.add(new Room(201, "Double"));
        rooms.add(new Room(202, "Double"));
    }

    private JPanel createRoomBookingPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        bookingInfoArea = new JTextArea();
        bookingInfoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(bookingInfoArea);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        JLabel roomNumberLabel = new JLabel("Room Number:");
        JComboBox<Integer> roomNumberCombo = new JComboBox<>();
        for (Room room : rooms) {
            roomNumberCombo.addItem(room.roomNumber);
        }

        JLabel customerNameLabel = new JLabel("Customer Name:");
        JTextField customerNameField = new JTextField();

        JLabel customerIdLabel = new JLabel("Customer ID:");
        JTextField customerIdField = new JTextField();

        JButton bookRoomButton = new JButton("Book Room");
        bookRoomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int roomNumber = (int) roomNumberCombo.getSelectedItem();
                String customerName = customerNameField.getText();
                String customerId = customerIdField.getText();

                for (Room room : rooms) {
                    if (room.roomNumber == roomNumber && room.isAvailable()) {
                        room.setAvailable(false);
                        Customer customer = new Customer(customerName, customerId);
                        customers.add(customer);
                        bookingInfoArea.append("Room " + roomNumber + " booked by " + customer + "\n");
                        break;
                    }
                }
            }
        });

        inputPanel.add(roomNumberLabel);
        inputPanel.add(roomNumberCombo);
        inputPanel.add(customerNameLabel);
        inputPanel.add(customerNameField);
        inputPanel.add(customerIdLabel);
        inputPanel.add(customerIdField);
        inputPanel.add(new JLabel(""));
        inputPanel.add(bookRoomButton);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createRoomAvailabilityPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextArea availabilityArea = new JTextArea();
        availabilityArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(availabilityArea);

        JButton checkAvailabilityButton = new JButton("Check Availability");
        checkAvailabilityButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                availabilityArea.setText("");
                for (Room room : rooms) {
                    availabilityArea.append(room + "\n");
                }
            }
        });

        panel.add(checkAvailabilityButton, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createCustomerManagementPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextArea customerArea = new JTextArea();
        customerArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(customerArea);

        JButton viewCustomersButton = new JButton("View Customers");
        viewCustomersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                customerArea.setText("");
                for (Customer customer : customers) {
                    customerArea.append(customer + "\n");
                }
            }
        });

        panel.add(viewCustomersButton, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HotelReservationSystem app = new HotelReservationSystem();
            app.setVisible(true);
        });
    }
}