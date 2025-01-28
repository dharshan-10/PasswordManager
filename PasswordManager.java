import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class PasswordManager {
    private JFrame conn1;
    private JButton PassSearchBtn, PassDeleteBtn, addNoteBtn, getNoteBtn, addPasswordBtn;
    private JTextArea searchPassArea;
    private JTextArea tNote;
    private JFrame conn3;
    private JLabel addNoteLabel;
    private JButton addNote;
    private ArrayList<String> notes = new ArrayList<>();
    private Map<String, String> data = new HashMap<>();

    public PasswordManager() {
        // Initialize the main frame (conn1)
        conn1 = new JFrame("Password Manager");
        conn1.setSize(400, 450);
        conn1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        conn1.setLocationRelativeTo(null); // Center the window
        conn1.setLayout(null);
        conn1.setResizable(true);

        // Add Heading Label
        JLabel headingLabel = new JLabel("Password Manager", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setBounds(0, 20, 400, 40);
        conn1.add(headingLabel);

        // Add Password Button - First
        addPasswordBtn = new JButton("ADD PASSWORD");
        addPasswordBtn.setBackground(new Color(255, 165, 0));
        addPasswordBtn.setBounds(90, 100, 220, 40); // Adjusted position
        conn1.add(addPasswordBtn);
        addPasswordBtn.addActionListener(e -> addPassword());

        // Other Buttons
        PassSearchBtn = new JButton("SEARCH PASSWORD");
        PassSearchBtn.setBackground(new Color(255, 165, 0));
        PassSearchBtn.setBounds(90, 160, 220, 40);
        conn1.add(PassSearchBtn);
        PassSearchBtn.addActionListener(e -> searchPassword());

        PassDeleteBtn = new JButton("DELETE PASSWORD");
        PassDeleteBtn.setBackground(new Color(255, 165, 0));
        PassDeleteBtn.setBounds(90, 220, 220, 40);
        conn1.add(PassDeleteBtn);
        PassDeleteBtn.addActionListener(e -> deletePassword());

        addNoteBtn = new JButton("ADD NOTE");
        addNoteBtn.setBackground(new Color(255, 165, 0));
        addNoteBtn.setBounds(90, 280, 220, 40);
        conn1.add(addNoteBtn);
        addNoteBtn.addActionListener(e -> addNote());

        getNoteBtn = new JButton("GET NOTE");
        getNoteBtn.setBackground(new Color(255, 165, 0));
        getNoteBtn.setBounds(90, 340, 220, 40);
        conn1.add(getNoteBtn);
        getNoteBtn.addActionListener(e -> getNote());

        // Make the window visible
        conn1.setVisible(true);
    }

    private void searchPassword() {
        try {
            String acc_name = JOptionPane.showInputDialog("Enter your Account Name");
            if (acc_name != null && !acc_name.isBlank()) {
                String pass = data.get(acc_name.toLowerCase());
                if (pass != null) {
                    searchPassArea = new JTextArea(4, 5);
                    searchPassArea.setText(pass);
                    JOptionPane.showMessageDialog(conn1, new JScrollPane(searchPassArea), "Copy your password",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(conn1, "Account not Found!");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(conn1, "Error occurred while searching", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletePassword() {
        try {
            String acc_name = JOptionPane.showInputDialog("Enter the Account Name");
            if (acc_name != null && !acc_name.isBlank()) {
                if (data.containsKey(acc_name.toLowerCase())) {
                    data.remove(acc_name.toLowerCase());
                    JOptionPane.showMessageDialog(conn1, "Password deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(conn1, "Account not found!", "INFO", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(conn1, "Error occurred while deleting", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addPassword() {
        try {
            String acc_name = JOptionPane.showInputDialog("Enter the Account Name");
            String pass = JOptionPane.showInputDialog("Enter the Password");

            if (acc_name != null && pass != null && !acc_name.isBlank() && !pass.isBlank()) {
                data.put(acc_name.toLowerCase(), pass);
                JOptionPane.showMessageDialog(conn1, "Password added successfully!");
            } else {
                JOptionPane.showMessageDialog(conn1, "Account Name and Password cannot be empty!", "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(conn1, "Error occurred while adding password", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addNote() {
        try {
            NoteGUI();
            addNote.addActionListener(e -> {
                String note = tNote.getText();
                if (!note.isEmpty()) {
                    notes.add(note);
                    JOptionPane.showMessageDialog(conn3, "Note added successfully!");
                    conn3.setVisible(false);
                    tNote.setText("");
                } else {
                    JOptionPane.showMessageDialog(conn3, "Note cannot be empty!", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(conn3, "Error while adding note", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void getNote() {
        try {
            if (!notes.isEmpty()) {
                String allNotes = notes.get(notes.size() - 1);
                searchPassArea = new JTextArea(4, 5);
                searchPassArea.setText(allNotes);
                JOptionPane.showMessageDialog(conn1, new JScrollPane(searchPassArea), "View your note",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(conn1, "No notes available!", "INFO", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(conn1, "Error while retrieving note", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void NoteGUI() {
        conn3 = new JFrame("Add Note");
        conn3.setSize(500, 500);
        conn3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        conn3.setLocationRelativeTo(null);
        conn3.setLayout(null);
        conn3.setVisible(true);
        conn3.setResizable(false);

        addNoteLabel = new JLabel("Add Note");
        addNoteLabel.setBounds(200, 20, 100, 30);
        conn3.add(addNoteLabel);

        tNote = new JTextArea(10, 10);
        tNote.setBounds(100, 60, 300, 300);
        conn3.add(tNote);

        addNote = new JButton("ADD NOTE");
        addNote.setBounds(140, 380, 220, 30);
        conn3.add(addNote);
    }

    public static void main(String[] args) {
        try {
            new PasswordManager();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
