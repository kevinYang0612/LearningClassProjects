package gitRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
/**
 *  This is a class called "DecimalFrame".
 *  It is a concrete class that extends from CalculatorFrame can be presented can be presented
 *  as a window frame for user to actually input their desired "decimal numbers" and its operations
 *  This class mainly deal with floating decimal number calculation
 *  The result will be presented to the text field.
 *
 * @author Kevin Yang
 * @version Nov 20th, 2021
 * */

public class DecimalFrame extends CalculatorFrame
{
    /** An array of numberButtons that used for store 0 - 9 */
    private final JButton[] numberButtons = new JButton[10];

    /** negButton can make negate a number and decimalButton can make floating point number */
    private JButton negButton, decimalButton;

    /** num1 is used to store first number user input */
    private double num1 = 0;

    /** result is store a result after calculation */
    private double result = 0;

    // constructor
    /**
     * Constructs a DecimalFrame
     * the features of DecimalFrame
     * Including dealing with floating point numbers
     *
     * @param name, a name for DecimalFrame is passed in during instantiating an instance
     * */
    public DecimalFrame(String name)
    {
        super(name);
        this.setButtonPanel();
        this.setDisplayPanel();
        this.addButtons();
        this.addOtherButtons();
    }

    // instance method
    /**
     * This is a class method to set DecimalFrame buttons panel
     * including buttons font, size, location on the frame
     * and assign some value to class fields
     *
     * */
    public void setButtonPanel()
    {
        super.setFunctionButtons();
        this.negButton = new JButton("(-)");
        this.decimalButton = new JButton(".");

        for (JButton functionButton : super.getFunctionButtons())
        {
            functionButton.addActionListener(this);
            functionButton.setFont(super.myFont);
            functionButton.setFocusable(false);
        }
        this.negButton.addActionListener(this);
        this.decimalButton.addActionListener(this);
        this.negButton.setFont(super.myFont);
        this.decimalButton.setFont(super.myFont);
        for (int i = 0; i < this.numberButtons.length; i++)
        {
            this.numberButtons[i] = new JButton(String.valueOf(i));
            this.numberButtons[i].addActionListener(this);
            this.numberButtons[i].setFont(super.myFont);
            this.numberButtons[i].setFocusable(false);
        }
        this.negButton.setBounds(150, 600, 150, 100);
        super.getFunctionButtons()[6].setBounds(300, 600, 150, 100);
        super.getFunctionButtons()[4].setBounds(450, 600, 150, 100);
    }

    /**
     * This is a class method to set DecimalFrame display panel
     * including display panel color, size, and location on the frame
     *
     * */
    public void setDisplayPanel()
    {
        super.getDisplayPanel().setBounds(150, 100, 500, 500);
        super.getDisplayPanel().setLayout(new GridLayout(4, 4, 10, 10));
        super.getDisplayPanel().setBackground(Color.ORANGE);
    }

    /**
     * This is a class method to add and set up buttons structure for decimalFrame
     *
     * */
    public void addButtons()
    {
        super.getDisplayPanel().add(this.numberButtons[1]);
        super.getDisplayPanel().add(this.numberButtons[2]);
        super.getDisplayPanel().add(this.numberButtons[3]);
        super.getDisplayPanel().add(super.getFunctionButtons()[0]);
        super.getDisplayPanel().add(this.numberButtons[4]);
        super.getDisplayPanel().add(this.numberButtons[5]);
        super.getDisplayPanel().add(this.numberButtons[6]);
        super.getDisplayPanel().add(super.getFunctionButtons()[1]);
        super.getDisplayPanel().add(this.numberButtons[7]);
        super.getDisplayPanel().add(this.numberButtons[8]);
        super.getDisplayPanel().add(this.numberButtons[9]);
        super.getDisplayPanel().add(super.getFunctionButtons()[2]);
        super.getDisplayPanel().add(this.decimalButton);
        super.getDisplayPanel().add(this.numberButtons[0]);
        super.getDisplayPanel().add(super.getFunctionButtons()[5]);
        super.getDisplayPanel().add(super.getFunctionButtons()[3]);
    }

    /** This is a class method that just add some utility buttons on the frame*/
    public void addOtherButtons()
    {
        super.add(this.negButton);
        super.add(super.getFunctionButtons()[6]);
        super.add(super.getFunctionButtons()[4]);
    }

    /**
     * This is an override class method to implement concrete method body
     * for "ActionListener" interface
     * Main purposes are getting num1, num2, operator, and identify "=" sign
     * Other functions are clear, delete, and negative button
     * the result gets to display in textField.
     *
     * @param e, an ActionEvent that will trigger its certain function.
     * */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        for (int i = 0; i < this.numberButtons.length; i++)
        {
            if (e.getSource() == this.numberButtons[i])
            {
                super.getTextField().setText(super.getTextField().getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == this.decimalButton)
        {
            super.getTextField().setText(super.getTextField().getText().concat("."));
        }
        if (e.getSource() == super.getFunctionButtons()[0])
        {
            this.num1 = Double.parseDouble(super.getTextField().getText());
            super.setOperator('+');
            super.getTextField().setText("");
        }
        if (e.getSource() == super.getFunctionButtons()[1])
        {
            this.num1 = Double.parseDouble(super.getTextField().getText());
            super.setOperator('-');
            super.getTextField().setText("");
        }
        if (e.getSource() == super.getFunctionButtons()[2])
        {
            this.num1 = Double.parseDouble(super.getTextField().getText());
            super.setOperator('*');
            super.getTextField().setText("");
        }
        if (e.getSource() == super.getFunctionButtons()[3])
        {
            this.num1 = Double.parseDouble(super.getTextField().getText());
            super.setOperator('/');
            super.getTextField().setText("");
        }
        if (e.getSource() == super.getFunctionButtons()[5])
        {
            double num2 = Double.parseDouble(super.getTextField().getText());
            switch (super.getOperator())
            {
                case '+' -> this.result = this.num1 + num2;
                case '-' -> this.result = this.num1 - num2;
                case '*' -> this.result = this.num1 * num2;
                case '/' -> this.result = this.num1 / num2;
            }
            super.getTextField().setText(String.valueOf(this.result));
            this.num1 = this.result;
        }
        if (e.getSource() == super.getFunctionButtons()[4])
        {
            super.getTextField().setText("");
        }
        if (e.getSource() == super.getFunctionButtons()[6])
        {
            String str = super.getTextField().getText();
            super.getTextField().setText("");
            for (int i = 0; i < str.length() - 1; i++)
            {
                super.getTextField().setText(super.getTextField().getText() + str.charAt(i));
            }
        }
        if (e.getSource() == this.negButton)
        {
            double temp = Double.parseDouble(super.getTextField().getText());
            temp *= -1;
            super.getTextField().setText(String.valueOf(temp));
        }
    }
}
