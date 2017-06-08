

import com.sun.javaws.jnl.JavaFXRuntimeDesc;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import rx.Observable;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text warsawLabel = new Text("Warsaw, Poland");
        warsawLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
        grid.add(warsawLabel, 0, 0, 2, 1);

        Label temperatureLabel = new Label("Temperature:");
        Text temperatureText = new Text("- °C");
        grid.add(temperatureLabel, 0, 1);
        grid.add(temperatureText, 1, 1);

        Label pressureLabel = new Label("Atmospheric pressure:");
        Text pressureText = new Text("- Pa");
        grid.add(pressureLabel, 0, 2);
        grid.add(pressureText, 1, 2);

        Label cloudLabel = new Label("Clouds:");
        Text cloudText = new Text("how cloudy it is");
        grid.add(cloudLabel, 0, 3);
        grid.add(cloudText, 1, 3);

        Label windStrengthLabel = new Label("Wind strength:");
        Text windStrengthText = new Text("- km/h");
        grid.add(windStrengthLabel, 0, 4);
        grid.add(windStrengthText, 1, 4);

        Label windDirectionLabel = new Label("Wind direction:");
        Text windDirectionText = new Text("- °NE");
        grid.add(windDirectionLabel, 0, 5);
        grid.add(windDirectionText, 1, 5);

        Label moistureLabel = new Label("Moisture:");
        Text moistureText = new Text("- %");
        grid.add(moistureLabel, 0, 6);
        grid.add(moistureText, 1, 6);

        Label dust2Label = new Label("PM 2.5 dust level:");
        Text dust2Text = new Text("-");
        grid.add(dust2Label, 0, 7);
        grid.add(dust2Text, 1, 7);

        Label dust10Label = new Label("PM 10 dust level:");
        Text dust10Text = new Text("-");
        grid.add(dust10Label, 0, 8);
        grid.add(dust10Text, 1, 8);

        Text updateText = new Text("Last updated: never");
        grid.add(updateText, 0, 9);
        int fieldCount = 9;

        Button refresh = new Button("Refresh");

        grid.add(refresh, 2, fieldCount + 1);

        Scene scene = new Scene(grid, 800, 600);

        primaryStage.setTitle("Weather in Warsaw");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
