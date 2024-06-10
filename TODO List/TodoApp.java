import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TodoApp {
    private JFrame frame;
    private DefaultListModel<String> listModel;
    private JList<String> list;

    public TodoApp() {
        frame = new JFrame("To-Do List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane listScrollPane = new JScrollPane(list);

        JTextField textField = new JTextField();
        textField.setColumns(20);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String item = textField.getText();
                if (!item.isEmpty()) {
                    listModel.addElement(item);
                    textField.setText("");
                }
            }
        });

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                }
            }
        });

        JButton markDoneButton = new JButton("Mark as Done");
        markDoneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    String item = listModel.getElementAt(selectedIndex);
                    listModel.set(selectedIndex, "<html><strike>" + item + "</strike></html>");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(textField);
        panel.add(addButton);
        panel.add(removeButton);
        panel.add(markDoneButton);

        frame.getContentPane().add(listScrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
    }

    public void display() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        TodoApp app = new TodoApp();
        app.display();
    }
}
