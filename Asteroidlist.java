
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.List;

public class AsteroidList {

    private List<Asteroid> asteroids;
    private int numberOfAsteroids;
    private JFrame asteroidList;
    private JTable table;

//    public static void main(String[] args) {
//
//        AsteroidList window = new AsteroidList();
//        window.asteroidList.setVisible(true);
//    }
    public AsteroidList(List<Asteroid> asteroids, int numberOfAsteroids) {
        this.asteroids = asteroids;
        this.numberOfAsteroids = numberOfAsteroids;
        assert(asteroids.size() == numberOfAsteroids);

        initFrame();
        initDate();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 80, 458, 200);
        asteroidList.getContentPane().add(scrollPane);
        
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[] {
                        "Asteroid Name ", "Closest Approach (KM)", "Diameter (KM)"
                });

        table = new JTable(tableModel);
        
        for (int i=0;i<asteroids.size(); i++ ) {
            Asteroid asteroid = asteroids.get(i);
            Object rowData[] = {asteroid.getName(), asteroid.getMissing_approach(), asteroid.getDiameter()};
            tableModel.addRow(rowData);
        }
        
        HashMap<Integer, Integer> colWidths = getColWidths();
        for (int i = 0; i < 3; i++) {
            table.getColumnModel().getColumn(i).setResizable(false);
            table.getColumnModel().getColumn(i).setPreferredWidth(colWidths.get(i));
        }

        scrollPane.setViewportView(table);

        addHazardWarning();
    }

    // Initializes the frame by setting a title, layout, and other details.
    public void initFrame() {
        asteroidList = new JFrame();
        asteroidList.setTitle("Asteroid List");
        asteroidList.setBounds(0, 20, 500, 600);
        asteroidList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        asteroidList.getContentPane().setLayout(null);

        getColWidths();
    }

    public HashMap<Integer, Integer> getColWidths() {
        HashMap<Integer, Integer> colWidths = new HashMap<Integer, Integer>();
        colWidths.put(0, 142);
        colWidths.put(1, 147);
        colWidths.put(2, 159);
        return colWidths;
    }

    // Adds the date to the GUI.
    public void initDate() {
        String Date = new String("12/08/19");
        JLabel date = new JLabel("DATE : "+ Date);
        date.setBounds(78, 13, 304, 51);
        date.setHorizontalAlignment(SwingConstants.CENTER);
        date.setFont(new Font("Tahoma", Font.BOLD, 31));
        asteroidList.getContentPane().add(date);
    }

    public void addHazardWarning() {
        String hazard = new String("NONE");
        JLabel hazardous = new JLabel("HAZARDOUS ASTEROIDS : " + hazard);
        hazardous.setBounds(12, 354, 458, 81);
        hazardous.setHorizontalAlignment(SwingConstants.CENTER);
        hazardous.setFont(new Font("Tahoma", Font.BOLD, 20));
        asteroidList.getContentPane().add(hazardous);
    }
}
