package gitRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
/**
 *  This is a class called "HexFrame".
 *  It is a concrete class that extends from CalculatorFrame can be presented
 *  as a window frame for user to actually input their desired "hexadecimal numbers" and its operations
 *  This class mainly deal with hexadecimal number calculation
 *  The result will be presented to the text field.
 *
 * @author Kevin Yang
 * @version Nov 20th, 2021
 * */
public class HexFrame extends CalculatorFrame
{
    /** An array of numberButtons that used for store 0 - 9 and A - F */
    private final JButton[] numberButtons = new JButton[16];

    /** A button that can make a number negative */
    private JButton negButton;

    /** num1 is used to store first number user input */
    private int num1 = 0;

    // constructor
    /**
     * Constructs a HexFrame
     * the features of HexFrame
     * Including a number of buttons from 0 to 9 and A to F, extra function buttons and extra calculations.
     *
     * @param name, a name for HexFrame is passed in during instantiating an instance
     * */
    public HexFrame(String name)
    {
        super(name);
        this.setButtonPanel();
        this.setDisplayPanel();
        this.addButtons();
    }

    // instance method
    /**
     * This is a class method to set HexFrame buttons panel
     * including buttons font, size, location on the frame
     * and assign some value to class fields
     *
     * */
    public void setButtonPanel()
    {
        super.setFunctionButtons();
        this.negButton = new JButton("(-)");

        for (JButton functionButton : super.getFunctionButtons())
        {
            functionButton.addActionListener(this);
            functionButton.setFont(super.myFont);
            functionButton.setFocusable(false);
        }
        this.negButton.addActionListener(this);
        this.negButton.setFont(super.myFont);
        for (int i = 0; i < this.numberButtons.length; i++)
        {
            if (i <= 9)
            {
                this.numberButtons[i] = new JButton(String.valueOf(i));
            }
            else
            {
                this.numberButtons[i] = new JButton(Integer.toHexString(i).toUpperCase());
            }
            this.numberButtons[i].addActionListener(this);
            this.numberButtons[i].setFocusable(false);
        }
        for (JButton numberButton : this.numberButtons)
        {
            numberButton.setFont(myFont);
        }
    }

    /**
     * This is a class method to set HexFrame display panel
     * including display panel color, size, and location on the frame
     *
     * */
    public void setDisplayPanel()
    {
        super.getDisplayPanel().setBounds(100, 100, 600, 600);
        super.getDisplayPanel().setLayout(new GridLayout(6, 4, 10, 10));
        super.getDisplayPanel().setBackground(Color.GREEN);
    }

    /**
     * This is a class method to add and set up buttons structure for HexFrame
     *
     * */
    public void addButtons()
    {
        super.addArithmeticButtons();
        for (JButton numberButton : this.numberButtons)
        {
            super.getDisplayPanel().add(numberButton);
        }
        super.addUtilButtons();
        super.getDisplayPanel().add(this.negButton);
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
                super.getTextField().setText(super.getTextField().getText().concat
                        (Integer.toHexString(i)).toUpperCase());
            }
        }
        if (e.getSource() == super.getFunctionButtons()[0])  // +
        {
            this.num1 = Integer.parseInt(super.getTextField().getText(), 16);
            super.setOperator('+');
            super.getTextField().setText("");
        }
        if (e.getSource() == super.getFunctionButtons()[1])  // -
        {
            this.num1 = Integer.parseInt(super.getTextField().getText(),16);
            super.setOperator('-');
            super.getTextField().setText("");
        }
        if (e.getSource() == super.getFunctionButtons()[2])
        {
            this.num1 = Integer.parseInt(super.getTextField().getText(),16);
            super.setOperator('*');
            super.getTextField().setText("");
        }
        if (e.getSource() == super.getFunctionButtons()[3])
        {
            this.num1 = Integer.parseInt(super.getTextField().getText(),16);
            super.setOperator('/');
            super.getTextField().setText("");
        }
        if (e.getSource() == getFunctionButtons()[5])  // =
        {
            int num2 = Integer.parseInt(super.getTextField().getText(), 16);
            int result = 0;
            if (super.getOperator() == '+')
            {
                result = this.num1 + num2;
                if (result >= 0)
                {
                    super.getTextField().setText(Integer.toHexString(result).toUpperCase());
                }
                else
                {
                    result *= -1;
                    super.getTextField().setText("-" + Integer.toHexString(result).toUpperCase());
                }
            }
            else if (super.getOperator() == '-')
            {
                result = this.num1 - num2;
                if (result >= 0)
                {
                    super.getTextField().setText(Integer.toHexString(result).toUpperCase());
                }
                else
                {
                    result *= -1;
                    super.getTextField().setText("-" + Integer.toHexString(result).toUpperCase());
                }
            }
            else if (super.getOperator() == '*')
            {
                result = this.num1 * num2;
                if (result >= 0)
                {
                    super.getTextField().setText(Integer.toHexString(result).toUpperCase());
                }
                else
                {
                    result *= -1;
                    super.getTextField().setText("-" + Integer.toHexString(result).toUpperCase());
                }
            }
            else
            {
                result = this.num1 / num2;
                int reminder = this.num1 % num2;
                if (reminder == 0)
                {
                    if (result >= 0)
                    {
                        super.getTextField().setText(Integer.toHexString(result).toUpperCase());
                    }
                    else
                    {
                        result *= -1;
                        super.getTextField().setText("-" + Integer.toHexString(result).toUpperCase());
                    }
                }
                else
                {
                    if (result >= 0)
                    {
                        String reminderStr = Integer.toHexString(reminder).toUpperCase();
                        super.getTextField().setText(Integer.toHexString(result).toUpperCase() +
                                " Remainder: " + reminderStr);
                    }
                    else
                    {
                        result *= -1;
                        String reminderStr = Integer.toHexString(reminder * -1).toUpperCase();
                        super.getTextField().setText("-" + Integer.toHexString(result).toUpperCase()
                                + " Remainder: -" + reminderStr);
                    }
                }
            }
            this.num1 = result;
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
            String temp = "-" + super.getTextField().getText();
            super.getTextField().setText(temp);
        }
    }
}
