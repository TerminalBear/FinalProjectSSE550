import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PostfixCalculatorGUI {
    private JFrame frame;
    private JTextArea textArea;
    private JTextField textField;
    private PostOrderCalculator postOrderCalculator;

    public PostfixCalculatorGUI() {
        frame = new JFrame("Postfix Calculator");
        textArea = new JTextArea();
        textField = new JTextField();
        postOrderCalculator = new PostOrderCalculator();

        textArea.setText("Welcome to the Postfix Calculator!\n" +
                "Please enter your postfix expressions in the text field below.\n" +
                "For example, to add 1 and 2, type: '1 2 +'\n" +
                "Then press Enter to calculate.\n funcstions include +, - , / , * , ^ , ~ (Root), S (Sin in radians), C (Cos in radians)\n"
                + "For sin or cosine just type any number after the base and select either operation \n");
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textField.getText();
                textField.setText("");

                try {
                    String[] tokens = input.split("\\s+");
                    double result = postOrderCalculator.calculatePostOrder(tokens);
                    textArea.append(input + ": " + result + "\n");
                } catch (IllegalArgumentException ex) {
                    textArea.append("Error: " + ex.getMessage() + "\n");
                }
            }
        });

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(textField, BorderLayout.SOUTH);
        frame.pack();
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new PostfixCalculatorGUI();
    }
}

