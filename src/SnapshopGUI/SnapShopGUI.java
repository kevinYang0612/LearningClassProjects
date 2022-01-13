package gui;

import filters.*;
import image.PixelImage;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
/**
 *  This is a class called "SnapShopGUI".
 *  It is a class that extends JFrame mainly operating on opening, editing, saving, and closing an image
 *
 * @author Kevin Yang
 * @version Nov 28th, 2021
 * */
public class SnapShopGUI extends JFrame
{
    /** a JPanel that will be positioned at north of the frame, will contain 7 filter buttons */
    private JPanel northPanel;

    /** a JLabel that will be positioned at center of the frame, will contain the image when image is loaded */
    private JLabel centerLabel;

    /** a JPanel that will be positioned at south of the frame, will contain 3 options, "open, save, close" */
    private JPanel southPanel;

    /** a JButton array to save "Open..., Save As..., Close Image" buttons */
    private final JButton[] options = new JButton[3];

    /** a JFileChooser that will choose the file */
    private final JFileChooser chooser = new JFileChooser();

    /** a Filter arrays that contains 7 filters */
    private final Filter[] filters = new Filter[7];

    /** a JButton array that will save 7 filter buttons */
    private final JButton[] filterButtons = new JButton[7];

    /** a PixelImage is selected and ready to be edited */
    private PixelImage image;

    // constructor
    /**
     * Constructor a SnapShopGUI frame and set its title
     *
     * */
    public SnapShopGUI()
    {
        super("TCSS 305 - Programming Assignment 3(2020237 yang0612)");
        this.chooser.setCurrentDirectory(new File("."));
    }

    /**
     * This is a class method that initializing and organizing the frame components
     *
     * */
    public void start()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFrame();
        pack();
        this.setVisible(true);
    }

    /**
     * This is a private class method simply setting up the frame by including 3 parts,
     * north, center, south
     *
     * */
    private void setFrame()
    {
        setNorthPanel();
        setSouthPanel();
        setCenterLabel();
        this.add(this.northPanel, BorderLayout.NORTH);
        this.add(this.centerLabel, BorderLayout.CENTER);
        this.add(this.southPanel, BorderLayout.SOUTH);
    }

    /**
     * This is private class method that simply setting up the north position for the frame
     * by implementing 7 filter buttons
     * */
    private void setNorthPanel()
    {
        setFilters();
        this.northPanel = new JPanel(new FlowLayout());
        for (int i = 0; i < filterButtons.length; i++)
        {
            this.filterButtons[i] = createFilterButtons(this.filters[i]);
            this.northPanel.add(this.filterButtons[i]);
        }
    }

    /**
     * This is private class method that simply setting up the south position for the frame
     * by implementing "Open, save, and close image" buttons
     * */
    private void setSouthPanel()
    {
        this.southPanel = new JPanel(new FlowLayout());
        setOptions();
        this.options[0].addActionListener(e -> openFile());

        this.options[1].setEnabled(false);
        this.options[1].addActionListener(e -> saveFile());

        this.options[2].setEnabled(false);
        this.options[2].addActionListener(e -> close());
        for (JButton option : this.options)
        {
            this.southPanel.add(option);
        }
    }

    /**
     * This is a private class method that just set the center label where contain an image
     * */
    private void setCenterLabel()
    {
        this.centerLabel = new JLabel();
        this.centerLabel.setHorizontalAlignment(JLabel.CENTER);
        this.centerLabel.setVerticalAlignment(JLabel.CENTER);
    }

    /**
     * This is a private helper method that is helping to create filter buttons for
     * north panel and add action listen right after it is created.
     *
     * @param theFilter, it is a filter type that will deal with filtering the image
     * @return a JButton is being returned and added to the north panel
     * */
    private JButton createFilterButtons(final Filter theFilter)
    {
        final JButton button = new JButton(theFilter.getDescription());  // place holder
        button.setEnabled(false);
        button.addActionListener(e ->
        {
            theFilter.filter(this.image);
            this.centerLabel.setIcon(new ImageIcon(this.image));  // after filter, reset image at the center label
            this.pack();
        });
        return button;
    }

    /**
     * This is a private helper method that setting up the 3 buttons at south of the frame
     * adding an icon for each "Open...", "Save As...", "Close Image" buttons
     * */
    private void setOptions()
    {
        this.options[0] = new JButton("Open...");
        this.options[1] = new JButton("Save As...");
        this.options[2] = new JButton("Close Image");
        this.options[0].setIcon(new ImageIcon(
                "C:\\2021 TCSS 305 Java\\prga#03-2020237-snapshop\\icons\\open.gif"));
        this.options[1].setIcon(new ImageIcon(
                "C:\\2021 TCSS 305 Java\\prga#03-2020237-snapshop\\icons\\save.gif"));
        this.options[2].setIcon(new ImageIcon(
                "C:\\2021 TCSS 305 Java\\prga#03-2020237-snapshop\\icons\\close.gif"));
    }

    /**
     * This is a private helper method that is helping to instantiate 7 filters
     * */
    private void setFilters()
    {
        this.filters[0] = new EdgeDetectFilter();
        this.filters[1] = new EdgeHighlightFilter();
        this.filters[2] = new FlipHorizontalFilter();
        this.filters[3] = new FlipVerticalFilter();
        this.filters[4] = new GrayscaleFilter();
        this.filters[5] = new SharpenFilter();
        this.filters[6] = new SoftenFilter();
    }

    /**
     * This is a private helper method that is helping to implement
     * "Open..." button that is going to read a file, get the image,
     * and display image at center label
     * */
    private void openFile()
    {
        final int result = this.chooser.showOpenDialog(super.getContentPane());
        if (result == JFileChooser.APPROVE_OPTION)
        {
            this.chooser.setCurrentDirectory(chooser.getSelectedFile());
            try
            {
                this.image = PixelImage.load(this.chooser.getSelectedFile());
                this.centerLabel.setIcon(new ImageIcon(this.image));

                for (JButton filterButton : this.filterButtons)
                {
                    filterButton.setEnabled(true);
                }
                this.options[1].setEnabled(true);
                this.options[2].setEnabled(true);
                this.pack();
            }
            catch (final IOException e)
            {
                JOptionPane.showMessageDialog(null,
                        "The selected file did not contain an image!");
            }
        }
    }

    /**
     * This is a private helper method that is helping to implement "Save As" button,
     * the image after editing to save in the file
     * */
    private void saveFile()
    {
        final int result = this.chooser.showSaveDialog(super.getContentPane());
        if (result == JFileChooser.APPROVE_OPTION)
        {
            this.chooser.setCurrentDirectory(this.chooser.getCurrentDirectory());
            try
            {
                this.image.save(this.chooser.getSelectedFile());
            }
            catch (final IOException e)
            {
                JOptionPane.showMessageDialog(null,
                        "This file cannot be written");
            }
        }
    }

    /**
     * This is a private helper method that is helping to implement "Close Image" button
     * It just simply closes the image at the center label
     * */
    private void close()
    {
        this.centerLabel.setIcon(null);
        this.options[1].setEnabled(false);
        this.options[2].setEnabled(false);

        for (int i = 0; i < this.filters.length; i++)
        {
            this.filterButtons[i].setEnabled(false);
        }
    }
}