import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;

public class Asteroidlist {

	private JFrame asteroidList;
	private JTable table;
	
	public static void main(String[] args) {

		Asteroidlist window = new Asteroidlist();
		window.asteroidList.setVisible(true);
	}
	public Asteroidlist() {
		initFrame();
		initDate();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 80, 458, 200);
		asteroidList.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"TEST 1", "123", "1234"},
				{"TEST 2", "123", "1234"},
				{"TEST 3", "123", "1234"},
			},
			new String[] {
				"Asteroid Name ", "Closest Approach (KM)", "Diameter (KM)"
			}
			) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
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
