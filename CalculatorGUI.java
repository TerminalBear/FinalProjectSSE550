import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI {
    private JFrame frame;
    private JTextField textField;
    private String operator = "";
    private double firstOperand = 0.0;
    private String mode; 
    
    public CalculatorGUI() {
        // Prompt the user to select the mode
        mode = (String) JOptionPane.showInputDialog(
                null,
                "Select mode:",
                "Calculator Mode",
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[] { "Infix", "Prefix", "Postfix" },
                "Infix");

        if (mode == null || !mode.equals("Infix")) {
            // If the user cancels the dialog or selects a mode other than "Infix",
            // exit the program
            System.exit(0);
        }

        frame = new JFrame("Calculator");
        textField = new JTextField();

        // Buttons
        JButton[] numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
        }

        JButton addButton = new JButton("+");
        JButton subtractButton = new JButton("-");
        JButton multiplyButton = new JButton("*");
        JButton divideButton = new JButton("/");
        JButton equalButton = new JButton("=");
        JButton clearButton = new JButton("Clear");

        // Panel/components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));

        for (JButton button : numberButtons) {
            panel.add(button);
        }
        panel.add(addButton);
        panel.add(subtractButton);
        panel.add(multiplyButton);
        panel.add(divideButton);
        panel.add(equalButton);
        panel.add(clearButton);

        for (JButton button : numberButtons) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textField.setText(textField.getText() + button.getText());
                }
            });
        }

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operator = "+";
                firstOperand = Double.parseDouble(textField.getText());
                textField.setText("");
            }
        });

        subtractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operator = "-";
                firstOperand = Double.parseDouble(textField.getText());
                textField.setText("");
            }
        });

        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operator = "*";
                firstOperand = Double.parseDouble(textField.getText());
                textField.setText("");
            }
        });

        divideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operator = "/";
                firstOperand = Double.parseDouble(textField.getText());
                textField.setText("");
            }
        });

        equalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double secondOperand = Double.parseDouble(textField.getText());
                switch (operator) {
                    case "+":
                        textField.setText(String.valueOf(firstOperand + secondOperand));
                        break;
                    case "-":
                        textField.setText(String.valueOf(firstOperand - secondOperand));
                        break;
                    case "*":
                        textField.setText(String.valueOf(firstOperand * secondOperand));
                        break;
                    case "/":
                        if (secondOperand != 0) {
                            textField.setText(String.valueOf(firstOperand / secondOperand));
                        } else {
                            textField.setText("Error: Division by zero");
                        }
                        break;
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                operator = "";
                firstOperand = 0.0;
            }
        });

        frame.add(textField, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CalculatorGUI();
    }
}
