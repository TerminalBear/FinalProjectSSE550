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
    private String color;
    // creating the exponential method to be passed

    public static double exponential(double base, double exponent) {
        return Math.pow(base, exponent);
    }
    public static double Root(double value, double rootDegree) {
        return Math.pow(value, 1.0 / rootDegree);
    }
    
    private static Color getColor(String colorName) {
        try {
            // Use reflection to get the Color field with the specified name
            java.lang.reflect.Field field = Class.forName("java.awt.Color").getField(colorName.toLowerCase());
            return (Color) field.get(null);
        } catch (Exception e) {
            // Handle exceptions (e.g., color name not found)
            e.printStackTrace();
            return Color.BLACK; // Default color
        }
    }

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
        // Prompt the user to select a color for the calculator 
        color = (String) JOptionPane.showInputDialog(
                null,
                "Select color of calculator:",
                "Calculator Mode",
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[] { "Blue", "Red", "Orange" },
        
        		"Green");
        //finding the color the user selected 
        String buttonColor=null ;
        if(color.equals("Blue"))
        {
        	buttonColor = "Cyan";
        }
        else if(color.equals("Red"))
        {
        	buttonColor = "Red";
        }
        else if(color.equals("Green"))
        {
        	buttonColor="Green";
        }
    
        if (mode == null ) {
          
            System.exit(0);
            
        }
        
        else if (mode =="Infix" )
        {
        frame = new JFrame("Calculator");
        textField = new JTextField();
        {
        // Buttons
        JButton[] numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setBackground(getColor(buttonColor));
        }

        JButton addButton = new JButton("+");
        JButton subtractButton = new JButton("-");
        JButton multiplyButton = new JButton("*");
        JButton divideButton = new JButton("/");
        JButton equalButton = new JButton("=");
        JButton clearButton = new JButton("Clear");
        JButton exponetialButton = new JButton("^");
        JButton rootButton = new JButton("^(1/x)");
        // Changing button colors
        addButton.setBackground(getColor(buttonColor));
        subtractButton.setBackground(getColor(buttonColor));
        multiplyButton.setBackground(getColor(buttonColor));
        divideButton.setBackground(getColor(buttonColor));
        equalButton.setBackground(getColor(buttonColor));
        clearButton.setBackground(getColor(buttonColor));
        exponetialButton.setBackground(getColor(buttonColor));
        rootButton.setBackground(getColor(buttonColor));
        
        
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
        panel.add(exponetialButton);
        panel.add(rootButton);

        for (JButton button : numberButtons) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textField.setText(textField.getText() + button.getText());
                    
                
                }
            });
        }
final  String original= buttonColor;
color="Red";
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operator = "+";
                firstOperand = Double.parseDouble(textField.getText());
                textField.setText("");
                addButton.setBackground(getColor(color));
            }
        });

        subtractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operator = "-";
                firstOperand = Double.parseDouble(textField.getText());
                textField.setText("");
                subtractButton.setBackground(getColor(color));
            }
        });

        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operator = "*";
                firstOperand = Double.parseDouble(textField.getText());
                textField.setText("");
                multiplyButton.setBackground(getColor(color));
            }
        });

        divideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operator = "/";
                firstOperand = Double.parseDouble(textField.getText());
                textField.setText("");
                divideButton.setBackground(getColor(color));
            }
            
        });
        exponetialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operator = "^";
                firstOperand = Double.parseDouble(textField.getText());
                textField.setText("");
                exponetialButton.setBackground(getColor(color));
            }
        });
        rootButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                operator = "^1/x";
                firstOperand = Double.parseDouble(textField.getText());
                textField.setText("");
                rootButton.setBackground(getColor(color));
            }
        });


        equalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double secondOperand = Double.parseDouble(textField.getText());
                switch (operator) {
                    case "+":
                        textField.setText(String.valueOf(firstOperand + secondOperand));
                        addButton.setBackground(getColor(original));
                      
                        
                        break;
                    case "-":
                        textField.setText(String.valueOf(firstOperand - secondOperand));
                        subtractButton.setBackground(getColor(original));
                        
                        break;
                    case "*":
                        textField.setText(String.valueOf(firstOperand * secondOperand));
                        multiplyButton.setBackground(getColor(original));
                        break;
                    case "/":
                        if (secondOperand != 0) {
                            textField.setText(String.valueOf(firstOperand / secondOperand));
                            divideButton.setBackground(getColor(original));
                        } else {
                            textField.setText("Error: Division by zero");
                        }
                  
                        break;
                    case "^":
                        textField.setText(String.valueOf( exponential(firstOperand, secondOperand)));
                        exponetialButton.setBackground(getColor(original));
                        break;
                    case "^1/x":
                        textField.setText(String.valueOf( Root(firstOperand, secondOperand)));
                       rootButton.setBackground(getColor(original));
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
    }}
        else if (mode.equals("Postfix"))
        {
        	 
        	 new PostfixCalculatorGUI();
        }
        else if (mode.equals("Prefix"))
         {
        	 new PrefixCalculatorGUI();
         }
    }
    public static void main(String[] args) {
        new CalculatorGUI();
    }
}
