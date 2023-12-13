import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class PrefixCalculatorGUI {
    private JFrame frame;
    private JTextArea textArea;
    private JTextField textField;
    private PreOrderCalculator preOrderCalculator;

    public PrefixCalculatorGUI() {
        frame = new JFrame("Prefix Calculator");
        textArea = new JTextArea();
        textField = new JTextField();
        preOrderCalculator = new PreOrderCalculator();

        textArea.setText("Welcome to the Prefix Calculator!\n" +
                "Please enter your prefix expressions in the text field below.\n" +
                "For example, to add 1 and 2, type: '+ 1 2'\n" +
                "Then press Enter to calculate.\n funcstions include +, - , / , * , ^ , ~ (Root) \n S (Sin in radians), C (Cos in radians) \n\n"
                   + "For sin or cosine just type any number after the base and select either operation\n" );
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
                    Queue<String> tokenQueue = new LinkedList<>(Arrays.asList(tokens));
                    Node root = buildExpressionTreePreOrder(tokenQueue);

                    if (!tokenQueue.isEmpty()) {
                        throw new IllegalArgumentException("Invalid pre-order expression");
                    }

                    double result = preOrderCalculator.calculatePreOrder(root);
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private Node buildExpressionTreePreOrder(Queue<String> tokens) {
        if (tokens.isEmpty()) {
            return null;
        }

        String token = tokens.poll();

        Node node;

        if (isOperator(token)) {
            node = new Node(token.charAt(0));
            node.left = buildExpressionTreePreOrder(tokens);
            node.right = buildExpressionTreePreOrder(tokens);

            if (node.left == null || node.right == null) {
                throw new IllegalArgumentException("Invalid pre-order expression");
            }
        } else {
            node = new Node(Double.parseDouble(token));
        }

        return node;
    }

    private boolean isOperator(String token) {
        return "+-*/^~SC".contains(token);
    }

    public static void main(String[] args) {
        new PrefixCalculatorGUI();
    }
}