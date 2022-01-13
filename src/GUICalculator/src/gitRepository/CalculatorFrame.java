package gitRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *  This is a class called "CalculatorFrame".
 *  It is a super class that is a JFrame and implements ActionListener interface
 *  It assists its subclass to achieve certain functionalities by using polymorphism
 *
 * @author Kevin Yang
 * @version Nov 20th, 2021
 * */

public class CalculatorFrame extends JFrame implements ActionListener
{
    /** A panel that is for displaying buttons */
    private final JPanel displayPanel;

    /** A text field will be added to the frame*/
    private JTextField textField;

    /** An array of functionButtons that used for store arithmetic operator and common buttons */
    private final JButton[] functionButtons = new JButton[7];

    /** Add, subtract, multiply, divide, equal, delete, clear buttons are commonly used in subclass */
    private JButton addButton, minusButton, multipleButton, divideButton;
    private JButton equalButton, deleteButton, clearButton;

    /** A constant width to set the size of CalculatorFrame frame */
    private static final int DEFAULT_WIDTH = 800;

    /** A constant height to set the size of CalculatorFrame frame */
    private static final int DEFAULT_HEIGHT = 800;

    /** An operator stored here that is identified during input */
    private char operator;

    /** A commonly used font and font size for its subclass to use */
    Font myFont = new Font("Ink Free", Font.BOLD, 20);

    // constructor
    /**
     * Constructs a CalculatorFrame
     * the features of this frame are commonly used for its subclass
     *
     * */
    public CalculatorFrame(String title)
    {
        this.setFrame(title);
        this.setTextField();
        this.displayPanel = new JPanel();
        this.add(this.displayPanel);
        this.add(this.textField);
    }

    // instance method
    /**
     * This is a class method to set CalculatorFrame
     *
     * @param title, a name for CalculatorFrame subclasses
     *              is passed in during instantiating an instance
     *
     * */
    public void setFrame(String title)
    {
        this.setTitle(title);
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setLayout(null);
    }

    /**
     * This is a class method to set text field that is commonly used for
     * its subclasses.
     *
     * */
    public void setTextField()
    {
        this.textField = new JTextField();
        this.textField.setBounds(150, 25, 600, 50);
        this.textField.setFont(this.myFont);
        this.textField.setEditable(false);
    }

    /**
     * This is a class method to set functionButtons array
     *
     * */
    public void setFunctionButtons()
    {
        this.addButton = new JButton("+");
        this.minusButton = new JButton("-");
        this.multipleButton = new JButton("*");
        this.divideButton = new JButton("/");
        this.equalButton = new JButton("=");
        this.clearButton = new JButton("Clear");
        this.deleteButton = new JButton("Del.");

        this.functionButtons[0] = this.addButton;
        this.functionButtons[1] = this.minusButton;
        this.functionButtons[2] = this.multipleButton;
        this.functionButtons[3] = this.divideButton;
        this.functionButtons[4] = this.clearButton;
        this.functionButtons[5] = this.equalButton;
        this.functionButtons[6] = this.deleteButton;
    }

    /**
     * This is a class method that just add arithmetic buttons to the
     * display panel
     *
     * */
    public void addArithmeticButtons()
    {
        this.displayPanel.add(this.addButton);
        this.displayPanel.add(this.minusButton);
        this.displayPanel.add(this.multipleButton);
        this.displayPanel.add(this.divideButton);
    }

    /**
     * This is a class method that just add some utility buttons to the
     * display panel
     * */
    public void addUtilButtons()
    {
        this.displayPanel.add(this.clearButton);
        this.displayPanel.add(this.deleteButton);
        this.displayPanel.add(this.equalButton);
    }
    /**
     * This is a getter method that retrieve the operator
     *
     * @return a char type, an operator that will be returned
     * */
    public char getOperator() { return this.operator;}

    /**
     * This is a setter method that sets the operator
     *
     * @param operator, a char type operator will be passed
     * */
    public void setOperator(char operator) { this.operator = operator;}

    /**
     * This is a getter method to get functionButtons array
     *
     * @return a JButton[] type, it is easy for subclass to access the function button
     * */
    public JButton[] getFunctionButtons() { return this.functionButtons;}

    /**
     * This is a getter method for subclass to access its text field
     *
     * @return JTextField type
     * */
    public JTextField getTextField() {
        return this.textField;
    }

    /**
     * This is a getter method for subclass to access it display panel
     *
     * @return JPanel type
     * */
    public JPanel getDisplayPanel() { return this.displayPanel;}

    /**
     * This is a method that implements for actionListener interface
     *
     * @param e, it will trigger certain functions
     * */
    public void actionPerformed(ActionEvent e) {}
}
