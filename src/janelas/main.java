package janelas;

import java.io.IOException;
import javax.swing.JFrame;

public class main {

    public static void main (String args[]) throws IOException {
        
        JFrame frame = new JFrame();
        frame.setVisible(true);
        
        JanelaMenu menu = new JanelaMenu();
        menu.menu(frame);
        
    }    
    
}
