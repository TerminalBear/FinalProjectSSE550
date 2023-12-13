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
    // creating this to handle exception if two operators are pressed
    
    private boolean operatorPressed = false;
    private void showErrorAndClear(String errorMessage) {
	    JOptionPane.showMessageDialog(frame, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
	    clearAll();
	}

	private void clearAll() {
	    textField.setText("");
	    operatorPressed = false;
	}
    
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
                new String[] { "Infix", "Prefix", "Postfix" , "Conversion App"},
                "Infix");
        
        if (mode == null ) {
          
            System.exit(0);
            
        }
        
        else if (mode =="Infix" )
        {
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
             else if(color.equals("Orange"))
             {
             	buttonColor="Orange";
             }
         
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
        JButton SinButton= new JButton("Sin");
        JButton CosButton= new JButton("Cos");
        // Changing button colors
        addButton.setBackground(getColor(buttonColor));
        subtractButton.setBackground(getColor(buttonColor));
        multiplyButton.setBackground(getColor(buttonColor));
        divideButton.setBackground(getColor(buttonColor));
        equalButton.setBackground(getColor(buttonColor));
        clearButton.setBackground(getColor(buttonColor));
        exponetialButton.setBackground(getColor(buttonColor));
        rootButton.setBackground(getColor(buttonColor));
        SinButton.setBackground(getColor(buttonColor));
        CosButton.setBackground(getColor(buttonColor));
        
        
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
        panel.add(SinButton);
        panel.add(CosButton);

        for (JButton button : numberButtons) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textField.setText(textField.getText() + button.getText());
                    
                
                }
            });
        }
      
final  String original= buttonColor;
color="White";
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 if (!operatorPressed) {
                     operator = "+";
                     firstOperand = Double.parseDouble(textField.getText());
                     textField.setText("");
                     addButton.setBackground(getColor(color));
                     operatorPressed = true;
                 } else {
                     showErrorAndClear("Error: You pressed two operators consecutively. Start your expression over");

                 }
            }
        });

        subtractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!operatorPressed) {
                operator = "-";
                firstOperand = Double.parseDouble(textField.getText());
                textField.setText("");
                subtractButton.setBackground(getColor(color));
                operatorPressed = true;
                }
                
                else {
                	 showErrorAndClear("Error: You pressed two operators consecutively. Start your expression over");

                }
            }
        });

        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	   if (!operatorPressed) {
            	operator = "*";
                firstOperand = Double.parseDouble(textField.getText());
                textField.setText("");
                multiplyButton.setBackground(getColor(color));
                operatorPressed = true;
                }
            	   else {
            		   showErrorAndClear("Error: You pressed two operators consecutively. Start your expression over");

            	   }
            }
        });

        divideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (!operatorPressed) {
            		operator = "/";
            	
                firstOperand = Double.parseDouble(textField.getText());
                textField.setText("");
                divideButton.setBackground(getColor(color));
                operatorPressed = true;
                }
            	else {
            		 showErrorAndClear("Error: You pressed two operators consecutively. Start your expression over");

            	}
            }
            
        });
        exponetialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if (!operatorPressed)
            	   {operator = "^";
                firstOperand = Double.parseDouble(textField.getText());
                textField.setText("");
                exponetialButton.setBackground(getColor(color));
                operatorPressed = true;
                }
               else {
            	   showErrorAndClear("Error: You pressed two operators consecutively. Start your expression over");

           	
               }
            }
        });
        rootButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(!operatorPressed)
            	   {operator = "^1/x";
                firstOperand = Double.parseDouble(textField.getText());
                textField.setText("");
                rootButton.setBackground(getColor(color));
                operatorPressed = true;
                }
               else
               {
            	   showErrorAndClear("Error: You pressed two operators consecutively. Start your expression over");

               }
            }
        });
        SinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(!operatorPressed)
            	   {operator = "Sin";
                firstOperand = Double.parseDouble(textField.getText());
                textField.setText("");
                SinButton.setBackground(getColor(color));
                operatorPressed = true;
                }
               else
               {
            	   showErrorAndClear("Error: You pressed two operators consecutively. Start your expression over");

               }
            }
        });
        CosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(!operatorPressed)
            	   {operator = "Cos";
                firstOperand = Double.parseDouble(textField.getText());
                textField.setText("");
                CosButton.setBackground(getColor(color));
                operatorPressed = true;
                }
               else
               {
            	   showErrorAndClear("Error: You pressed two operators consecutively. Start your expression over");

               }
            }
        });

        equalButton.addActionListener(new ActionListener() {
        	
        
        	@Override
  
            public void actionPerformed(ActionEvent e) {
                double secondOperand = Double.parseDouble(textField.getText());
            	operatorPressed = false;
            	
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
                 case "Sin":
                	 double angleInDegrees = firstOperand;

                   
                     double angleInRadians = Math.toRadians(angleInDegrees);

                   
                   
                        textField.setText(String.valueOf( Math.sin(angleInRadians)));
                        SinButton.setBackground(getColor(original));
                        break;
                 case "Cos":
                	 double angleInDegrees2 = firstOperand;

                   
                     double angleInRadians2 = Math.toRadians(angleInDegrees2);

                   
                   
                        textField.setText(String.valueOf( Math.cos(angleInRadians2)));
                        CosButton.setBackground(getColor(original));
                        break;
                    case "^":
                        textField.setText(String.valueOf( exponential(firstOperand, secondOperand)));
                        exponetialButton.setBackground(getColor(original));
                        break;
                    case "^1/x":
                    	 if (secondOperand != 0) {
                    	textField.setText(String.valueOf( Root(firstOperand, secondOperand)));
                       rootButton.setBackground(getColor(original));
                        break;
                    	 }
                    	 else 
                    	 {
                    		  textField.setText("Error: Division by zero please clear screen and try again"); 
                    		  rootButton.setBackground(getColor(original));
                    	 }
                    
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
        else if (mode.equals("Conversion App"))
        {
        	 JFrame frame = new JFrame("Conversion App");
             frame.setContentPane(new ConversionApp().panel1);
             frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             frame.pack();
             frame.setVisible(true);
        }
    }
    public static void main(String[] args) {
        new CalculatorGUI();
    }
}
