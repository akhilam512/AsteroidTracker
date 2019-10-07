import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;

public class AsteroidDetail {

    private JFrame asteroid;
    private JTable table;

//    public static void main(String[] args) {
//        AsteroidDetail window = new AsteroidDetail();
//        window.asteroid.setVisible(true);
//
//    }

    public AsteroidDetail() {
        initFrame();
        JLabel name = new JLabel("NAME :");
        name.setHorizontalAlignment(SwingConstants.CENTER);
        name.setFont(new Font("Tahoma", Font.BOLD, 23));
        name.setBounds(12, 13, 458, 45);
        asteroid.getContentPane().add(name);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 126, 458, 136);
        asteroid.getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][] {
                        {"10,00000", "10", "5000"},
                },
                new String[] {
                        "Relative Velocity (m/s)", "Closest Approach (KM)", "Diameter (KM)"
                }
        ));

        HashMap<Integer, Integer> colWidths = getColWidths();
        for (int i = 0; i < 3; i++) {
            table.getColumnModel().getColumn(i).setResizable(false);
            table.getColumnModel().getColumn(i).setPreferredWidth(colWidths.get(i));
        }

        scrollPane.setViewportView(table);

        addHazardLabel();
    }

    public void initFrame() {
        asteroid = new JFrame();
        String Name = new String("Asteroid Detail");
        asteroid.setTitle(Name);
        asteroid.setBounds(0, 20, 500, 600);
        asteroid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        asteroid.getContentPane().setLayout(null);
    }

    public void addHazardLabel() {
        JLabel hazard = new JLabel("Hazardous : True");
        hazard.setHorizontalAlignment(SwingConstants.CENTER);
        hazard.setFont(new Font("Tahoma", Font.BOLD, 23));
        hazard.setBounds(12, 363, 458, 45);
        asteroid.getContentPane().add(hazard);
    }

    public HashMap<Integer, Integer> getColWidths() {
        HashMap<Integer, Integer> colWidths = new HashMap<Integer, Integer>();
        colWidths.put(0, 152);
        colWidths.put(1, 155);
        colWidths.put(2, 115);
        return colWidths;
    }

}
