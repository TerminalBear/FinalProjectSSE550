import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hello_world {
    private JTextField textField1;
    private JTextField textField2;
    private JButton button1;
    private JLabel label1;
    private JLabel label2;
    private JComboBox<String> unitComboBox1;
    private JComboBox<String> unitComboBox2;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Conversion App");
        frame.setContentPane(new Hello_world().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel panel1;

    public Hello_world() {
        panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.setPreferredSize(new Dimension(300, 300));

        label1 = new JLabel("Enter length:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel1.add(label1, gbc);

        textField1 = new JTextField("xxx.xxxxx");
        textField1.setPreferredSize(new Dimension(2, 200)); // increased width
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
                    double output;
                    if (unitComboBox1.getSelectedIndex() == 0 && unitComboBox2.getSelectedIndex() == 0) {
                        output = (input/2.54);
                        label2.setText("Converted length from inches to centimeters:"+ String.valueOf(output));
                    } else if (unitComboBox1.getSelectedIndex() == 0 && unitComboBox2.getSelectedIndex() == 1) {
                        output = input * 3.28084;
                        label2.setText("Converted length in feet:");
                    } else if (unitComboBox1.getSelectedIndex() == 1 && unitComboBox2.getSelectedIndex() == 0) {
                        output = input / 3.28084;
                        label2.setText("Converted length in meters:");
                    } else {
                        output = input;
                        label2.setText("Converted length in feet:");
                    }
                    textField2.setText(String.valueOf(output));
                } catch (NumberFormatException exception) {
                    textField2.setText("Error: Invalid input!");
                }
            }
        });
    }
}