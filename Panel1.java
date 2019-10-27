import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;


public class Panel1 extends JPanel implements ActionListener {
    private JButton submit_button;
    private JButton asteroid_data_btn;
    private JButton closest_ast_btn;
    private JLabel asteroid_tracker;
    private JLabel date_label;
    private JTextArea date_text_area;

    private static List<Asteroid> asteroids = null;
    private static int asteroid_count;
    Image background;

    public Panel1() {
        background = Toolkit.getDefaultToolkit().getImage("./background.png");

        //construct components
        submit_button = new JButton("SUBMIT");
        asteroid_data_btn = new JButton("Asteroid Data");
        closest_ast_btn = new JButton("Closest Asteroid");
        asteroid_tracker = new JLabel("Asteroid Tracker");
        date_label = new JLabel("Enter Date: ");

        date_text_area = new JTextArea(5, 5);

        //adjust size and set layout
        setPreferredSize(new Dimension(497, 496));
        setLayout(null);

        // add fonts
        asteroid_tracker.setFont(new Font("Tahoma", Font.BOLD, 23));
        date_label.setFont(new Font("Tahoma", Font.BOLD, 15));

        //font colors
        asteroid_tracker.setForeground(Color.WHITE);
        date_label.setForeground(Color.WHITE);

        // Add Event Listeners to buttons
        submit_button.setActionCommand("submit");
        submit_button.addActionListener(this);

        asteroid_data_btn.setActionCommand("ast_data_btn");
        asteroid_data_btn.addActionListener(this);

        closest_ast_btn.setActionCommand("closest_ast_btn");
        closest_ast_btn.addActionListener(this);

        //add components
        add(submit_button);
        add(asteroid_data_btn);
        add(closest_ast_btn);
        add(asteroid_tracker);
        add(date_label);
        add(date_text_area);

        //set component bounds (absolute positioning)
        submit_button.setBounds(150, 250, 200, 25);
        asteroid_data_btn.setBounds(50, 350, 150, 50);
        closest_ast_btn.setBounds(300, 350, 150, 50);
        asteroid_tracker.setBounds(150, 50, 400, 80);
        date_label.setBounds(150, 140, 100, 45);
        date_text_area.setBounds(150, 180, 200, 25);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
    }

    /**
     * @return List<Asteroid>
     * @throws IOException
     * @throws JSONException
     * @throws ParseException
     */
    public static List<Asteroid> getAsteroids(String date) throws IOException, JSONException, ParseException {
        List<Asteroid> asteroids = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.nasa.gov/neo/rest/v1/feed?start_date=" + date + "&api_key=BzB2orclxfvtyCtkKwX3PVr5xgdTQKSjo0JYQeVv")
                .get()
                .build();
        Response response = client.newCall(request).execute();

        String jsonData = response.body().string();
        JSONObject json = new JSONObject(jsonData);
        JSONObject data = (JSONObject) json.get("near_earth_objects");

        //////////	

        asteroid_count = json.getInt("element_count");

        for (Iterator iterator = data.keySet().iterator(); iterator.hasNext(); ) {
            String key = (String) iterator.next();
            JSONArray day = (JSONArray) data.get(key);

            for (int i = 0; i < day.length(); i++) {
                JSONObject asteroidJson = day.getJSONObject(i);
                String nameOfAsteroid = asteroidJson.getString("name");
                boolean isHazardous = asteroidJson.getBoolean("is_potentially_hazardous_asteroid");

                JSONObject diameterObject = asteroidJson.getJSONObject("estimated_diameter").getJSONObject("kilometers");
                Double diameter = diameterObject.getDouble("estimated_diameter_min") + diameterObject.getDouble("estimated_diameter_max");
                diameter /= 2;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateOfApproach = (String) asteroidJson
                        .getJSONArray("close_approach_data")
                        .getJSONObject(0)
                        .get("close_approach_date");
                Date approach_date = formatter.parse(dateOfApproach);
                Double closest_approach = asteroidJson.getJSONArray("close_approach_data")
                        .getJSONObject(0)
                        .getJSONObject("miss_distance")
                        .getDouble("kilometers");

                Asteroid asteroid = new Asteroid(nameOfAsteroid, approach_date, diameter, closest_approach, isHazardous);
                asteroids.add(asteroid);

            }
        }
        for (Asteroid a : asteroids) {
            System.out.println(a.toString());
        }
        return asteroids;
    }

    public static void main(String[] args) throws IOException, ParseException {
        JFrame frame = new JFrame("Main Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Panel1());
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        switch (actionCommand) {
            case "submit":
                System.out.println("Submit button clicked.");
                try {
                    String date1 = getDateFromTextArea();
                    System.out.println(date1);
                    asteroids = getAsteroids(date1);
                    AsteroidList window = new AsteroidList(asteroids, asteroid_count);
                    window.asteroidList.setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case "ast_data_btn":
                System.out.println("Asteroid tracker clicked.");
                break;
            case "closest_ast_btn":
                System.out.println("Closest Asteroid btn clicked.");
                break;
        }
    }

    private String getDateFromTextArea() throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(date_text_area.getText().trim());
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date1 = simpleDateFormat.format(date);
        return date1;
    }
}
