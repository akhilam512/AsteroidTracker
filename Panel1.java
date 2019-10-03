import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Panel1 extends JPanel implements ActionListener {
    private JButton submit_button;
    private JButton asteroid_data_btn;
    private JButton closest_ast_btn;
    private JLabel asteroid_tracker;
    private JLabel date_label;
    private JTextArea date_text_area;

    public Panel1() {
        //construct components
        submit_button = new JButton ("SUBMIT");
        asteroid_data_btn = new JButton ("Asteroid Data");
        closest_ast_btn = new JButton ("Closest Asteroid");
        asteroid_tracker = new JLabel ("Asteroid Tracker");
        date_label = new JLabel ("Enter Date: ");

        date_text_area = new JTextArea (5, 5);

        //adjust size and set layout
        setPreferredSize (new Dimension (497, 496));
        setLayout (null);

        // add fonts 
        asteroid_tracker.setFont(new Font("Tahoma", Font.BOLD, 23));
        date_label.setFont(new Font("Tahoma", Font.BOLD, 15));

        // Add Event Listeners to buttons 
        submit_button.setActionCommand("submit");
        submit_button.addActionListener(this);

        asteroid_data_btn.setActionCommand("ast_data_btn");
        asteroid_data_btn.addActionListener(this);

        closest_ast_btn.setActionCommand("closest_ast_btn");
        closest_ast_btn.addActionListener(this);
        
        //add components
        add (submit_button);
        add (asteroid_data_btn);
        add (closest_ast_btn);
        add (asteroid_tracker);
        add (date_label);
        add (date_text_area);

        //set component bounds (absolute positioning)
        submit_button.setBounds (150, 250, 200, 25);
        asteroid_data_btn.setBounds (50, 350, 150, 50);
        closest_ast_btn.setBounds (300, 350, 150, 50);
        asteroid_tracker.setBounds (150, 50, 400, 80);
        date_label.setBounds (150, 140, 100, 45);
        date_text_area.setBounds (150, 180, 200, 25);
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("Main Panel");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new Panel1());
        frame.pack();
        frame.setVisible (true);
    }

    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        switch (actionCommand) {
            case "submit":
                System.out.println("Submit button clicked.");
                break;
            case "ast_data_btn":
                System.out.println("Asteroid tracker clicked.");
                break;
            case "closest_ast_btn":
                System.out.println("Closest Asteroid btn clicked.");
                break;
        }
    }
}
