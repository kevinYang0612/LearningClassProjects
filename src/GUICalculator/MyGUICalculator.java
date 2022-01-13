package gitRepository;

import javax.swing.*;
/**
 *  This is a class called "MyGUICalculator".
 *  It is a concrete class that is a JFrame
 *  It assists its calculatorFrame class and its subclasses to present in actual window
 *
 * @author Kevin Yang
 * @version Nov 20th, 2021
 * */
class MyGUICalculator extends JFrame
{
    /** A constant width to set the size of MyGUICalculator frame */
    private static final int DEFAULT_WIDTH = 800;

    /** A constant height to set the size of MyGUICalculator frame */
    private static final int DEFAULT_HEIGHT = 800;

    // constructor
    /**
     * Constructs a MyGUICalculator
     * It performs a few functions including triggering some buttons.
     *
     * */
    public MyGUICalculator()
    {
        this.setTitle("My GUI");
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();


        DecimalFrame decimalFrame = new DecimalFrame("Kevin's Decimal Calculator");
        BinaryFrame binaryFrame = new BinaryFrame("Kevin's Binary Calculator");
        HexFrame hexFrame = new HexFrame("Kevin's Hexadecimal Calculator");
        BigNumberFrame bigNumberFrame = new BigNumberFrame("Kevin's Big Number Calculator");

        decimalFrame.setVisible(false);
        binaryFrame.setVisible(false);
        hexFrame.setVisible(false);
        bigNumberFrame.setVisible(false);

        this.addFrame(menuBar, binaryFrame, "Binary Calculator");
        this.addFrame(menuBar, decimalFrame, "Decimal Calculator");
        this.addFrame(menuBar, hexFrame, "Hexadecimal Calculator");
        this.addFrame(menuBar, bigNumberFrame, "Big Number Calculator");
        this.setVisible(true);
    }

    /**
     * This is a private helper method to assist this MyGUICalculator frame to present
     * some calculator frames
     *
     * @param menuBar, a menu bar that will add this current menu
     * @param calculatorFrame, add the calculator frame to be visible and ready to perform
     * @param name, a String name what indicate what calculator it is.
     * */
    private void addFrame(JMenuBar menuBar, CalculatorFrame calculatorFrame, String name)
    {
        JMenu menu = new JMenu(name);
        menuBar.add(menu);
        JMenuItem start = new JMenuItem("Start",'S');
        menu.add(start);
        start.addActionListener(e -> calculatorFrame.setVisible(true));
        this.setJMenuBar(menuBar);
    }
}
