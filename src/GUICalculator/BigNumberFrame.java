package gitRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.math.BigInteger;
/**
 *  This is a class called "BigNumberFrame".
 *  It is a concrete class that extends from CalculatorFrame can be presented can be presented
 *  as a window frame for user to actually input their desired "Big numbers" and its operations
 *  This class mainly deal with big number calculation
 *  The result will be presented to the textArea.
 *
 * @author Kevin Yang
 * @version Nov 20th, 2021
 * */

public class BigNumberFrame extends CalculatorFrame
{
    // instance fields
    /** A textArea that only used for BigNumberFrame */
    private final JTextArea textArea = new JTextArea(1000, 200);

    /** An array of numberButtons that used for store 0 - 9 */
    private final JButton[] numberButtons = new JButton[10];

    /** An array of specialOpButtons that used for store a bunch operator */
    private final JButton[] specialOpButtons = new JButton[7];

    /** decimalButton and negButton are for decimal dot sign and negative sign */
    private JButton decimalButton, negButton;

    /** num1 is used to store first number user input */
    private long num1 = 0L;

    /** num2 is used to store second number user input */
    private long num2 = 0L;

    /** result is store a result after calculation */
    private BigInteger result;

    // constructor
    /**
     * Constructs a BigNumberFrame
     * the features of BigNumberFrame
     * Including a textArea, extra function buttons and extra calculations.
     *
     * @param name, a name for BigNumberFrame is passed in during instantiating an instance
     * */
    public BigNumberFrame(String name)
    {
        super(name);
        this.setSize(1000, 1000);
        this.setTextArea();
        this.setButtonPanel();
        this.setDisplayPanel();
        this.addButtons();
        this.addOtherButtons();
    }

    // instance method
    /**
     * This is a class method to set BigNumberFrame buttons
     * including buttons font, size, location on the frame
     * and assign some value to class fields
     *
     * */
    public void setButtonPanel()
    {
        super.setFunctionButtons();
        JButton exponentButton = new JButton("X ^ Y");
        JButton sqrtRootButton = new JButton("sqrt rt X");
        JButton sqrtButton = new JButton("X ^ 2");
        JButton facButton = new JButton("X!");
        JButton modButton = new JButton("MOD");
        JButton gcdButton = new JButton("GCD");
        JButton lcmButton = new JButton("LCM");
        decimalButton = new JButton(".");
        negButton = new JButton("(-)");

        this.specialOpButtons[0] = exponentButton;
        this.specialOpButtons[1] = sqrtRootButton;
        this.specialOpButtons[2] = sqrtButton;
        this.specialOpButtons[3] = facButton;
        this.specialOpButtons[4] = modButton;
        this.specialOpButtons[5] = gcdButton;
        this.specialOpButtons[6] = lcmButton;


        for (JButton functionButton : super.getFunctionButtons())
        {
            functionButton.addActionListener(this);
            functionButton.setFont(super.myFont);
            functionButton.setFocusable(false);
        }

        for (JButton specialOpButton : this.specialOpButtons)
        {
            specialOpButton.addActionListener(this);
            specialOpButton.setFont(super.myFont);
            specialOpButton.setFocusable(false);
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
        super.getFunctionButtons()[6].setBounds(330, 785, 150, 100);
        super.getFunctionButtons()[4].setBounds(480, 785, 150, 100);
    }

    /**
     * This is a class method to set BigNumberFrame display panel
     * including display panel font, size, location on the frame
     *
     * */
    public void setDisplayPanel()
    {
        super.getDisplayPanel().setBounds(80, 485, 800, 300);
        super.getDisplayPanel().setLayout(new GridLayout(4, 6, 4, 4));
    }

    /**
     * This is a class method to add and set up buttons structure for BigNumberFrame
     *
     * */
    public void addButtons()
    {
        for (JButton numberButton : this.numberButtons)
        {
            super.getDisplayPanel().add(numberButton);
        }
        this.addArithmeticButtons();
        for (JButton specialOpButton : specialOpButtons)
        {
            super.getDisplayPanel().add(specialOpButton);
        }
        super.getDisplayPanel().add(this.decimalButton);
        super.getDisplayPanel().add(this.negButton);
        super.getDisplayPanel().add(super.getFunctionButtons()[5]);
    }

    /**
     * This is a class method to add more buttons for BigNumberFrame
     *
     * */
    public void addOtherButtons()
    {
        super.add(super.getFunctionButtons()[6]);
        super.add(super.getFunctionButtons()[4]);
    }

    /**
     * This is a class method to setTextArea BigNumberFrame
     * The textArea is where the result is presented after BigNumber calculation
     *
     * */
    public void setTextArea()
    {
        this.textArea.setFont(myFont);
        this.textArea.setBounds(80, 75, 800, 400);
        this.textArea.setBackground(Color.LIGHT_GRAY);
        this.textArea.setVisible(true);
        this.textArea.setEditable(false);
        this.textArea.setLineWrap(true);
        super.add(this.textArea);
    }

    /**
     * This is an override class method to implement concrete method body
     * for "ActionListener" interface
     * Main purposes are getting num1, num2, operator, and identity "=" sign
     * Other functions are clear, negate, decimal dot functions
     * the result gets to display in textArea.
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
            this.num1 = Long.parseLong(super.getTextField().getText());
            super.setOperator('+');
            super.getTextField().setText("");
        }
        if (e.getSource() == super.getFunctionButtons()[1])
        {
            this.num1 = Long.parseLong(super.getTextField().getText());
            super.setOperator('-');
            super.getTextField().setText("");
        }
        if (e.getSource() == super.getFunctionButtons()[2])
        {
            this.num1 = Long.parseLong(super.getTextField().getText());
            super.setOperator('*');
            super.getTextField().setText("");
        }
        if (e.getSource() == super.getFunctionButtons()[3])
        {
            this.num1 = Long.parseLong(super.getTextField().getText());
            super.setOperator('/');
            super.getTextField().setText("");
        }
        if (e.getSource() == this.specialOpButtons[0])
        {
            this.num1 = Long.parseLong(super.getTextField().getText());
            super.setOperator('^');
            super.getTextField().setText("");
        }
        if (e.getSource() == this.specialOpButtons[1])
        {
            this.num1 = Long.parseLong(super.getTextField().getText());
            super.setOperator('R');
            super.getTextField().setText("");
        }
        if (e.getSource() == this.specialOpButtons[2])
        {
            this.num1 = Long.parseLong(super.getTextField().getText());
            super.setOperator('S');  // square 2
            super.getTextField().setText("");
        }
        if (e.getSource() == this.specialOpButtons[3])
        {
            this.num1 = Long.parseLong(super.getTextField().getText());
            super.setOperator('!');
            super.getTextField().setText("");
        }
        if (e.getSource() == this.specialOpButtons[4])
        {
            this.num1 = Long.parseLong(super.getTextField().getText());
            super.setOperator('%');
            super.getTextField().setText("");
        }
        if (e.getSource() == this.specialOpButtons[5])
        {
            this.num1 = Long.parseLong(super.getTextField().getText());
            super.setOperator('G');
            super.getTextField().setText("");
        }
        if (e.getSource() == this.specialOpButtons[6])
        {
            this.num1 = Long.parseLong(super.getTextField().getText());
            super.setOperator('L');
            super.getTextField().setText("");
        }
        if (e.getSource() == super.getFunctionButtons()[5])
        {
            this.num2 = Long.parseLong(0 + super.getTextField().getText());
            switch (super.getOperator())
            {
                case '+' -> this.result = BigInteger.valueOf(this.num1).add(BigInteger.valueOf(this.num2));
                case '-' -> this.result = BigInteger.valueOf(this.num1).subtract(BigInteger.valueOf(this.num2));
                case '*' -> this.result = BigInteger.valueOf(this.num1).multiply(BigInteger.valueOf(this.num2));
                case '/' -> this.result = BigInteger.valueOf(this.num1).divide(BigInteger.valueOf(this.num2));
            }
            this.textArea.setText(String.valueOf(this.result));
            this.result = null;
            if (super.getOperator() == '^')
            {
                this.result = pow(BigInteger.valueOf(this.num1), BigInteger.valueOf(this.num2));
                this.textArea.setText(String.valueOf(this.result));
            }
            if (super.getOperator() == 'R')
            {
                this.result = BigInteger.valueOf(this.num1).sqrt();
                this.textArea.setText(String.valueOf(this.result));
            }
            if (super.getOperator() == 'S')
            {
                this.result = BigInteger.valueOf(this.num1).multiply(BigInteger.valueOf(this.num1));
                this.textArea.setText(String.valueOf(this.result));
            }
            if (super.getOperator() == '!')
            {
                this.result = factorial(this.num1);
                this.textArea.setText(String.valueOf(this.result));
            }
            if (super.getOperator() == '%')
            {
                this.result = BigInteger.valueOf(this.num1).mod(BigInteger.valueOf(this.num2));
                this.textArea.setText("Result of mod operation is: " + this.result);
            }
            if (super.getOperator() == 'G')
            {
                this.result = gcdFinder(this.num1, this.num2);
                this.textArea.setText(String.valueOf(this.result));
            }
            if (super.getOperator() == 'L')
            {
                this.result = lcmFinder(this.num1, this.num2);
                this.textArea.setText(String.valueOf(this.result));
            }
        }

        if (e.getSource() == super.getFunctionButtons()[4])
        {
            super.getTextField().setText("");
            this.textArea.setText("");
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
            long temp = Long.parseLong(super.getTextField().getText());
            temp *= -1;
            super.getTextField().setText(String.valueOf(temp));
        }
    }

    /**
     *  This is a private helper method for BigInteger exponential calculation
     *
     * @param base, take the first number that is passing in a base
     * @param exponent, take the second number that is passing in a exponent
     * @return BigInteger type, it is a result of first number ^ second number.
     * */
    private BigInteger pow(BigInteger base, BigInteger exponent)
    {
        BigInteger res = BigInteger.ONE;
        while (exponent.signum() > 0)
        {
            if (exponent.testBit(0))
            {
                res = res.multiply(base);
            }
            base = base.multiply(base);
            exponent = exponent.shiftRight(1);
        }
        return res;
    }

    /**
     *  This is a private helper method for BigInteger factorial calculation
     *
     * @param n, take the number that is passing in to do a factorial calculation
     * @return BigInteger type, it is a result of first number factorial.
     * */
    private BigInteger factorial(long n)
    {
        BigInteger res = new BigInteger("1");
        for (long i = 2L; i <= n; i++)
        {
            res = res.multiply(BigInteger.valueOf(i));
        }
        return res;
    }

    /**
     *  This is a private helper method for BigInteger greatest common divisor calculation
     *
     * @param num1, take the first number that is passing in
     * @param num2, take the second number that is passing in
     * @return BigInteger type, a result is the greatest common divisor of num1 and num2.
     * */
    private BigInteger gcdFinder(long num1, long num2)
    {
        long gcd = 1L;
        for (int i = 1; i <= num1 && i <= num2; i++)
        {
            if (num1 % i == 0 && num2 % i == 0)
            {
                gcd = i;
            }
        }
        return BigInteger.valueOf(gcd);
    }
    /**
     *  This is a private helper method for BigInteger least common multiplier calculation
     *
     * @param num1, take the first number that is passing in
     * @param num2, take the second number that is passing in
     * @return BigInteger type, a result is the least common multiplier of num1 and num2.
     * */
    private BigInteger lcmFinder(long num1, long num2)
    {
        return BigInteger.valueOf(num1).multiply
                (BigInteger.valueOf(num2)).divide(gcdFinder(num1, num2));
    }
}
