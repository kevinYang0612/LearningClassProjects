package gitRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
/**
 *  This is a class called "BinaryFrame".
 *  It is a concrete class that extends from CalculatorFrame can be presented can be presented
 *  as a window frame for user to actually input their desired "binary numbers" and its operations
 *  This class mainly deal with basic binary number calculation
 *  The result will be presented to the text field.
 *
 * @author Kevin Yang
 * @version Nov 20th, 2021
 * */

public class BinaryFrame extends CalculatorFrame
{
    /** An array of numberButtons that used for store only 0 and 1 */
    private final JButton[] numberButtons = new JButton[2];

    /** num1 is used to store first binary number user input */
    private int num1 = 0;

    // constructor
    /**
     * Constructs a BigNumberFrame
     *
     * @param name, a name for BinaryFrame is passed in during instantiating an instance
     * */
    public BinaryFrame(String name)
    {
        super(name);
        this.setButtonPanel();
        this.setDisplayPanel();
        this.addButtons();
        this.addOtherButtons();
    }

    // instance method
    /**
     * This is a class method to set BinaryFrame buttons panel
     * including buttons font, size, location on the frame
     * and assign some value to class fields
     *
     * */
    public void setButtonPanel()
    {
        super.setFunctionButtons();

        for (JButton functionButton : super.getFunctionButtons())
        {
            functionButton.addActionListener(this);
            functionButton.setFont(super.myFont);
            functionButton.setFocusable(false);
        }
        for (int i = 0; i < this.numberButtons.length; i++)
        {
            this.numberButtons[i] = new JButton(String.valueOf(i));
            this.numberButtons[i].addActionListener(this);
            this.numberButtons[i].setFont(super.myFont);
            this.numberButtons[i].setFocusable(false);
        }
        getFunctionButtons()[4].setBounds(335, 400, 120, 100);
    }

    /**
     * This is a class method to set BinaryFrame display panel
     * including display panel font, size, location on the frame
     *
     * */
    public void setDisplayPanel()
    {
        super.getDisplayPanel().setBounds(150, 150, 500, 250);
        super.getDisplayPanel().setLayout(new GridLayout(2, 4, 8, 8));
        super.getDisplayPanel().setBackground(Color.PINK);
    }

    /**
     * This is a class method to add and set up buttons structure for BinaryFrame
     *
     * */
    public void addButtons()
    {
        super.addArithmeticButtons();
        super.getDisplayPanel().add(super.getFunctionButtons()[6]);
        super.getDisplayPanel().add(this.numberButtons[0]);
        super.getDisplayPanel().add(this.numberButtons[1]);
        super.getDisplayPanel().add(super.getFunctionButtons()[5]);
    }

    /** This is a class method to just add one button which is the clear button*/
    public void addOtherButtons() {super.add(getFunctionButtons()[4]);}

    /**
     * This is an override class method to implement concrete method body
     * for "ActionListener" interface
     * Main purposes are getting num1, num2, operator, and identify "=" sign
     * Other functions are clear and delete
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
        if (e.getSource() == super.getFunctionButtons()[0])
        {
            this.num1 = Integer.parseInt(super.getTextField().getText(), 2);
            super.setOperator('+');
            super.getTextField().setText("");
        }
        if (e.getSource() == super.getFunctionButtons()[1])
        {
            this.num1 = Integer.parseInt(super.getTextField().getText(),2);
            super.setOperator('-');
            super.getTextField().setText("");
        }
        if (e.getSource() == super.getFunctionButtons()[2])
        {
            this.num1 = Integer.parseInt(super.getTextField().getText(),2);
            super.setOperator('*');
            super.getTextField().setText("");
        }
        if (e.getSource() == super.getFunctionButtons()[3])
        {
            this.num1 = Integer.parseInt(super.getTextField().getText(),2);
            super.setOperator('/');
            super.getTextField().setText("");
        }
        if (e.getSource() == super.getFunctionButtons()[5])
        {
            int num2 = Integer.parseInt(super.getTextField().getText(), 2);
            int result;
            if (super.getOperator() == '+')
            {
                result = this.num1 + num2;
                super.getTextField().setText(Integer.toBinaryString(result));
            }
            else if (super.getOperator() == '-')
            {
                result = this.num1 - num2;
                if (result > 0)
                {
                    super.getTextField().setText(Integer.toBinaryString(result));
                }
                else
                {
                    result *= -1;
                    super.getTextField().setText("-" + Integer.toBinaryString(result));
                }
            }
            else if (super.getOperator() == '*')
            {
                result = this.num1 * num2;
                super.getTextField().setText(Integer.toBinaryString(result));
            }
            else
            {
                result = this.num1 / num2;
                String reminder = Integer.toBinaryString(this.num1 % num2);
                super.getTextField().setText(Integer.toBinaryString(result) +
                        " Remainder: " + reminder);
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
    }
}
