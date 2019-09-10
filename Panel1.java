import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Panel1 extends JPanel {
    private JButton jcomp1;
    private JButton jcomp2;
    private JButton jcomp3;
    private JLabel jcomp4;
    private JLabel jcomp5;
    private JTextArea jcomp6;

    public Panel1() {
        //construct components
        jcomp1 = new JButton ("SUBMIT");
        jcomp2 = new JButton ("Asteroid Data");
        jcomp3 = new JButton ("Closest Asteroid");
        jcomp4 = new JLabel ("                                  Asteroid Tracker");
        jcomp5 = new JLabel ("Enter Date -");
        jcomp6 = new JTextArea (5, 5);

        //adjust size and set layout
        setPreferredSize (new Dimension (497, 496));
        setLayout (null);

        //add components
        add (jcomp1);
        add (jcomp2);
        add (jcomp3);
        add (jcomp4);
        add (jcomp5);
        add (jcomp6);

        //set component bounds (absolute positioning)
        jcomp1.setBounds (150, 250, 200, 25);
        jcomp2.setBounds (50, 350, 150, 50);
        jcomp3.setBounds (300, 350, 150, 50);
        jcomp4.setBounds (50, 50, 400, 80);
        jcomp5.setBounds (150, 160, 100, 45);
        jcomp6.setBounds (150, 205, 200, 25);
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("Main Panel");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new Panel1());
        frame.pack();
        frame.setVisible (true);
    }
}
