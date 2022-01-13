
package gui;

import java.awt.EventQueue;
import java.io.IOException;


public final class SnapShopMain {

    /**
     * Private constructor, to prevent instantiation of this class.
     */
    private SnapShopMain() {
        throw new IllegalStateException();
    }

    /**
     * The main method, invokes the SnapShop GUI. Command line arguments are
     * ignored.
     * 
     * @param theArgs Command line arguments.
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SnapShopGUI().start();
            }
        });
    }
}
