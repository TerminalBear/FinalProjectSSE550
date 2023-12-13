

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConversionApp {

    private JTextField textField1;
    private JComboBox<String> unitComboBox1;
    private JLabel label2;
    private JTextField textField2;
    private JComboBox<String> unitComboBox2;
    private JButton button1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Conversion App");
        frame.setContentPane(new ConversionApp().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    JPanel panel1;

    public ConversionApp() {
        panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.setPreferredSize(new Dimension(600, 600));

        JLabel label1 = new JLabel("Enter length:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel1.add(label1, gbc);

        textField1 = new JTextField("");
        textField1.setPreferredSize(new Dimension(100, 200)); // increased width
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel1.add(textField1, gbc);

        unitComboBox1 = new JComboBox<>(new String[]{"Inches", "Feet","Miles"});
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel1.add(unitComboBox1, gbc);

        label2 = new JLabel("Converted length:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel1.add(label2, gbc);

        textField2 = new JTextField("No conversion yet");
        textField2.setPreferredSize(new Dimension(200, 200)); // increased width
        textField2.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel1.add(textField2, gbc);

        unitComboBox2 = new JComboBox<>(new String[]{"Centimeters", "Meters", "Kilometers"});
        gbc.gridx = 2;
        gbc.gridy = 1;
        panel1.add(unitComboBox2, gbc);

        button1 = new JButton("Convert");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        panel1.add(button1, gbc);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double input = Double.parseDouble(textField1.getText());
                    double output = 0;
                    if (unitComboBox1.getSelectedIndex() == 0 && unitComboBox2.getSelectedIndex() == 0) {
                        output = (input*2.54);
                        label2.setText("Inches Converted length from inches to centimeters:"+ String.valueOf(output));
                    } else if (unitComboBox1.getSelectedIndex() == 0 && unitComboBox2.getSelectedIndex() == 2) {
                        output = input *39370.1;
                        label2.setText("Inches Converted length in KiloMeters:");
                    } else if (unitComboBox1.getSelectedIndex() == 0 && unitComboBox2.getSelectedIndex() == 1) {
                        output = input / 39.37;
                        label2.setText("Inches Converted length in Meters:");
                    } else if (unitComboBox1.getSelectedIndex() == 1 && unitComboBox2.getSelectedIndex() == 0) {
                        output = input *30.48 ;
                        label2.setText("feet Converted length in Centimeters:");
                    } else if (unitComboBox1.getSelectedIndex() == 1 && unitComboBox2.getSelectedIndex() == 1) {
                        output = input /3.28;
                        label2.setText("Feet Converted length in meters:");
                    } else if (unitComboBox1.getSelectedIndex() == 1 && unitComboBox2.getSelectedIndex() == 2) {
                        output = input / 5280;
                        label2.setText("Converted feet to length in kilometers:");
                    } else if (unitComboBox1.getSelectedIndex() == 2 && unitComboBox2.getSelectedIndex() == 0) {
                        output = input *160934;
                        label2.setText("Converted length in centimeters:");
                    } else if (unitComboBox1.getSelectedIndex() == 2 && unitComboBox2.getSelectedIndex() == 1) {
                        output = input * 1609.34;
                        label2.setText("Converted length in meters:");
                    } else if (unitComboBox1.getSelectedIndex() == 2 && unitComboBox2.getSelectedIndex() == 2) {
                        output = input * 1.60934;
                        label2.setText("Converted length in kilometers:");
                    } else {
                        label2.setText("Unknown Error Occurred");
                    }
                    textField2.setText(String.valueOf(output));
                } catch (NumberFormatException nfe) {
                    label2.setText("Error: Please enter a valid number.");
                }
            }
        });
    }
}