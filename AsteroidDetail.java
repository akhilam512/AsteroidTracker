import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class AsteroidDetail {

	private JFrame asteroid;
	private JTable table;
	public static void main(String[] args) {
		AsteroidDetail window = new AsteroidDetail();
		window.asteroid.setVisible(true);
		
	}

	public AsteroidDetail() {
		asteroid = new JFrame();
		String Name = new String("TEST 1");
		asteroid.setTitle(Name);
		asteroid.setBounds(0, 20, 500, 600);
		asteroid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		asteroid.getContentPane().setLayout(null);
		
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
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(152);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(155);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(115);
		scrollPane.setViewportView(table);
		
		JLabel hazard = new JLabel("Hazardous : True");
		hazard.setHorizontalAlignment(SwingConstants.CENTER);
		hazard.setFont(new Font("Tahoma", Font.BOLD, 23));
		hazard.setBounds(12, 363, 458, 45);
		asteroid.getContentPane().add(hazard);
	}
}
