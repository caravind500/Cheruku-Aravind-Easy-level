import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculator implements ActionListener {
    JFrame frame;
    JTextField display;
    JPanel panel;

    String firstOperand = "";
    String secondOperand = "";
    String operator = "";

    public calculator() {
        // Create the frame
        frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        // Create the display
        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setEditable(false);
        frame.add(display, BorderLayout.NORTH);

        // Create the panel with buttons
        panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Using GridBagLayout for better scalability

        // Button labels
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C"
        };

        // Create buttons and add to panel
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.insets = new Insets(5, 5, 5, 5);

        int gridX = 0;
        int gridY = 0;

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            button.addActionListener(this);
            constraints.gridx = gridX;
            constraints.gridy = gridY;

            panel.add(button, constraints);

            gridX++;
            if (gridX > 3) {
                gridX = 0;
                gridY++;
            }
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.chars().allMatch(Character::isDigit) || command.equals(".")) {
            if (operator.isEmpty()) {
                firstOperand += command;
                display.setText(firstOperand);
            } else {
                secondOperand += command;
                display.setText(secondOperand);
            }
        } else if (command.equals("=")) {
            if (!firstOperand.isEmpty() && !secondOperand.isEmpty() && !operator.isEmpty()) {
                double result = calculate(Double.parseDouble(firstOperand), Double.parseDouble(secondOperand), operator);
                display.setText(String.valueOf(result));
                firstOperand = String.valueOf(result);
                secondOperand = "";
                operator = "";
            }
        } else if (command.equals("C")) {
            firstOperand = "";
            secondOperand = "";
            operator = "";
            display.setText("");
        } else {
            if (!firstOperand.isEmpty() && secondOperand.isEmpty()) {
                operator = command;
            }
        }
    }

    private double calculate(double a, double b, String operator) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    JOptionPane.showMessageDialog(frame, "Cannot divide by zero");
                    return 0;
                }
                return a / b;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(calculator::new);
    }
}
