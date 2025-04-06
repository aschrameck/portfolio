import javax.swing.*;
import javax.swing.JFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.Color;

public class CurrencyConverter extends JFrame implements ActionListener, MouseListener{    
    JTextField inputField;
    JTextField outputField;
    JComboBox<String> inputSelector;
    JComboBox<String> outputSelector;
    JButton calcButton;
    
    String currencies[] = {"USD", "JPY", "GBP", "EURO"}; // + whatever currencies you want to add
    String srcCurrency;
    String dstCurrency;
    
    public CurrencyConverter(String frameTitle, int frameWidth, int frameHeight) {
        // Initialize frame object for the application window
        setTitle(frameTitle);
        setSize(frameWidth, frameHeight);

        // Set layout manager for the application frame
        setLayout(new GridBagLayout());
        
        // Initialize input GUI objects
        JLabel inputLabel = new JLabel("Input value: ");
        
        
        inputField = new JTextField(15);
        inputField.setText("");//
        //inputField.setText("input value");
        inputField.setEditable(true);
        inputField.addActionListener(this);//
        //inputField.addActionListener(instanceOfCurrencyConverter);
        
        inputSelector = new JComboBox<String>(currencies);
        inputSelector.addActionListener(new ActionListener() {// Creates and modifies a new ActionListener object
            @Override
            public void actionPerformed(ActionEvent event) {
                //TODO: Implement input selector switching which selection is set
                srcCurrency = "srcCurrency";  //  however selection is performed with JComboBoxes
            }
        });
        // Add components to the top-level container
        addWithLayout(inputLabel, 0, 0);
        addWithLayout(inputField, 1, 0);
        addWithLayout(inputSelector, 2, 0);
        
        // Initialize output GUI objects
        JLabel outputLabel = new JLabel("Output value: ");
        
        outputField = new JTextField(15);
        outputField.setText(" ");
        outputField.setEditable(false);
        
        outputSelector = new JComboBox<String>(currencies);
        outputSelector.addActionListener(new ActionListener() {// Creates and modifies a new ActionListener object
            @Override
            public void actionPerformed(ActionEvent event) {
                dstCurrency = "dstCurrency";  //  however selection is performed with JComboBoxes        
            }
        });
        // Add components to the top-level container
        addWithLayout(outputLabel, 0, 1);
        addWithLayout(outputField, 1, 1);
        addWithLayout(outputSelector, 2, 1);
        
        // Initialize conversion button GUI object
        calcButton = new JButton("Convert!");
        calcButton.addActionListener(this);
    
        //Mouse Listener, changes color when Mouse is pressed
        calcButton.addMouseListener(this);


        // Add components to the top-level container
        addWithLayout(calcButton, 0, 2);
        
        // Pack the frame to fit the contents
        pack();
       
        // Sets the program to exit when the user closes the last open frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Make the frame visible to the user
        setVisible(true);
    }
    
    // Handles layout constraint creation
    private void addWithLayout(JComponent component, int gridx, int gridy) {
        // Create object specifying the component layout
        GridBagConstraints layoutConst = new GridBagConstraints();
        // position in frame
        layoutConst.gridx = gridx;
        layoutConst.gridy = gridy;
        // padding around component (10 pixels all around)
        layoutConst.insets = new Insets(10, 10, 10, 10);
        
        add(component, layoutConst);
    }
    
 
    /* Method is automatically called when an event 
    occurs (e.g, Enter key is pressed) */
    @Override
    public void actionPerformed(ActionEvent event) {
        String userInput;
        double exchangeValue;

        // Get the selected currencies from the combo boxes
        srcCurrency = (String) inputSelector.getSelectedItem();
        dstCurrency = (String) outputSelector.getSelectedItem();

        // Get user's currency input
        userInput = inputField.getText().trim(); // trim spaces to avoid empty input with spaces

     

        // Check for empty input //DD
        if (userInput.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Input cannot be empty. Please enter a value.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Try to convert the input to a double
        try {
            double inputValue = Double.parseDouble(userInput);

            // Check for negative numbers
            if (inputValue < 0) {
                JOptionPane.showMessageDialog(this, "Input cannot be negative. Please enter a positive value.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            // Perform the currency conversion
            exchangeValue = convert(inputValue, srcCurrency, dstCurrency);//double

            // Display the result
            outputField.setText(Double.toString(exchangeValue));
            
             
        } catch (NumberFormatException e) {
            // Handle non-numeric input (letters or invalid characters)
            JOptionPane.showMessageDialog(this, "Please enter a valid number. Letters or other invalid characters are not allowed.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }


	//Convert to and from: "USD", "JPY", "GBP", "EURO"
        //Conversion rates obtained 11-14-24
    
    private double convert(double inputValue, String srcCurrency, String dstCurrency) {
        //Convert to and from: "USD", "JPY", "GBP", "EURO"
        //Conversion rates obtained 11-14-24

        double output = 0.0;

        //Check which input was selected
        switch(srcCurrency) {
            case "USD":
                //Check which output was selected and convert accordingly
                switch (dstCurrency) {
                    case "USD":
                        output = inputValue;
                        break;
                    case "JPY":
                        output = inputValue * 150.97794;
                        break;
                    case "GBP":
                        output = inputValue * 0.788798;
                        break;
                    case "EURO":
                        output = inputValue * 0.946505;
                        break;
                    default:
                        output = 0.0;
                        break;
                }
                break;

            case "JPY":
                switch (dstCurrency) {
                    case "USD":
                        output = inputValue * 0.00662177;
                        break;
                    case "JPY":
                        output = inputValue;
                        break;
                    case "GBP":
                        output = inputValue * 0.00522488;
                        break;
                    case "EURO":
                        output = inputValue * 0.00626880;
                        break;
                    default:
                        output = 0.0;
                        break;
                }
                break;

            case "GBP":
                switch (dstCurrency) {
                    case "USD":
                        output = inputValue * 1.26728;
                        break;
                    case "JPY":
                        output = inputValue * 191.402;
                        break;
                    case "GBP":
                        output = inputValue;
                        break;
                    case "EURO":
                        output = inputValue * 1.19982;
                        break;
                    default:
                        output = 0.0;
                        break;
                }
                break;

            case "EURO":
                switch(dstCurrency) {
                    case "USD":
                        output = inputValue * 1.05620;
                        break;
                    case "JPY":
                        output = inputValue * 159.519;
                        break;
                    case "GBP":
                        output = inputValue * 0.833450;
                        break;
                    case "EURO":
                        output = inputValue;
                        break;
                    default:
                        output = 0.0;
                        break;
                }
                break;
            default:
                output = 0.0;
                break;
        }

        return output;
    }
     ////mouseListener
   @Override
    public void mousePressed(MouseEvent e){
       Random rand = new Random();
       Color randomColor = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
       getContentPane().setBackground(randomColor);
       calcButton.setForeground(Color.PINK);     
   }
    
    @Override
    public void mouseClicked(MouseEvent evt){ }
    @Override
    public void mouseReleased(MouseEvent evt){ }
    @Override
    public void mouseEntered(MouseEvent evt){ }
    @Override
    public void mouseExited(MouseEvent evt){ }
        

    public static void main(String[] args) {       
        CurrencyConverter testApp = new CurrencyConverter("Currency Converter", 400, 200);
    }
}
