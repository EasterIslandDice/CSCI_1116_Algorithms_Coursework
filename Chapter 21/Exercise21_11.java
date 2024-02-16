import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.*;

public class Exercise21_11 extends Application {
  private Map<String, Integer>[] mapForBoy = getNames(1);
  private Map<String, Integer>[] mapForGirl = getNames(3);
  
  private Button btFindRanking = new Button("Find Ranking");
  private ComboBox<Integer> cboYear = new ComboBox<>();
  private ComboBox<String> cboGender = new ComboBox<>();
  private TextField tfName = new TextField();
  private Label lblResult = new Label();
  
  @Override 
  public void start(Stage primaryStage) {

    Scene scene = new Scene(getPane(), 370, 160);
    primaryStage.setTitle("Exercise21_11");
    primaryStage.setScene(scene); 
    primaryStage.show(); 
  }

    private void displayRank() {
      lblResult.setText(getGender() + " name " 
        + tfName.getText() + " is ranked #" + getRank()
        + " in year " + cboYear.getValue());
    }
    
    private String getGender() {
      return cboGender.getValue().equals("Male") ? "Boy" : "Girl";
    }
    
    private String getRank() {
      int year = cboYear.getValue() - 2001;
      
      if (cboGender.getValue().equals("Male")) {
        return mapForBoy[year].get(tfName.getText()) + "";
      }
      else
        return mapForGirl[year].get(tfName.getText()) + "";
    }
    

    private BorderPane getPane() {
      for (int i = 2001; i <= 2010; i++)
        cboYear.getItems().add(i);
      

      cboGender.getItems().addAll("Male", "Female");	
      

      GridPane gridPane = new GridPane();
      gridPane.setVgap(5);
      gridPane.setPadding(new Insets(5, 0, 5, 0));
      gridPane.setAlignment(Pos.CENTER);
      gridPane.add(new Label("Select a year: "), 0, 0);
      gridPane.add(cboYear, 1, 0);
      gridPane.add(new Label("Boy or girl?: "), 0, 1);
      gridPane.add(cboGender, 1, 1);
      gridPane.add(new Label("Enter a name: "), 0, 2);
      gridPane.add(tfName, 1, 2);
      gridPane.add(btFindRanking, 1, 3);
      

      BorderPane pane = new BorderPane();
      pane.setCenter(gridPane);
      pane.setBottom(lblResult);
      pane.setAlignment(lblResult, Pos.CENTER);
      
      return pane;
    }
    

    private Map[] getNames(int gender) {
      Map[] array = new Map[10];
      
      for (int year = 2001, i = 0; year <= 2010 && i < 10; year++, i++) {
        Map<String, String> map = new HashMap<>();
        // Read data from url
        try {
          java.net.URL url = new java.net.URL(
          "http://www.cs.armstrong.edu/liang/data/babynamesranking" 
            + year + ".txt");
          

          Scanner input = new Scanner(url.openStream());
          while (input.hasNext()) {
            ArrayList<String> list = new ArrayList<>();
            for (int w = 0; w < 5; w++) {
              list.add(w, input.next());
            }
            map.put(list.get(gender), list.get(0));
          } 
        }
        catch (java.net.MalformedURLException ex) {
          System.out.println("Invalid URL");
        }
        catch (java.io.IOException ex) {
          System.out.println("I/0 Errors: no such file");
        }
        array[i] = map;
      }
      
      return array;
    }
    

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
  