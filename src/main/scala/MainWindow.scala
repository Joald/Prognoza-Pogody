/**
  * Created by joald_000 on 09-Jun-17.
  */

import javafx.application.Application
import javafx.geometry.{Insets, Pos}
import javafx.scene.Scene
import javafx.scene.control.{Button, Label, ToggleButton}
import javafx.scene.layout.GridPane
import javafx.scene.text.{Font, FontWeight, Text}
import javafx.stage.Stage

import rx.observables.JavaFxObservable
class MainWindow extends Application{
  override def start(primaryStage: Stage): Unit = {
    val grid = new GridPane()
    grid setAlignment Pos.CENTER
    grid setHgap 10
    grid setVgap 10
    grid setPadding new Insets(25, 25, 25, 25)

    val warsawLabel = new Text("Warsaw, Poland")
    warsawLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40))
    grid.add(warsawLabel, 0, 0, 2, 1)

    val errorText = new Text("")
    Interface.getError subscribe (x => errorText setText x)
    grid.add(errorText, 0, 1, 2, 1)

    val propertiesStart = 1
    val temperatureLabel = new Label("Temperature:")
    val temperatureText = new Text("- °C")
    Interface.getTemperature subscribe(x => temperatureText setText x)

    grid.add(temperatureLabel, 0, propertiesStart + 1)
    grid.add(temperatureText, 1, propertiesStart + 1)

    val pressureLabel = new Label("Atmospheric pressure:")
    val pressureText = new Text("- Pa")
    Interface.getPressure subscribe(x => pressureText setText x)
    grid.add(pressureLabel, 0, propertiesStart + 2)
    grid.add(pressureText, 1, propertiesStart + 2)

    val cloudinessLabel = new Label("Clouds:")
    val cloudinessText = new Text("how cloudy it is")
    Interface.getCloudiness subscribe(x => cloudinessText setText x)
    grid.add(cloudinessLabel, 0, propertiesStart + 3)
    grid.add(cloudinessText, 1, propertiesStart + 3)

    val windSpeedLabel = new Label("Wind speed:")
    val windSpeedText = new Text("- km/h")
    Interface.getWindSpeed subscribe(x => windSpeedText setText x)
    grid.add(windSpeedLabel, 0, propertiesStart + 4)
    grid.add(windSpeedText, 1, propertiesStart + 4)

    val windDirectionLabel = new Label("Wind direction:")
    val windDirectionText = new Text("-°")
    Interface.getWindDirection subscribe(x => windDirectionText setText x)
    grid.add(windDirectionLabel, 0, propertiesStart + 5)
    grid.add(windDirectionText, 1, propertiesStart + 5)

    val humidityLabel = new Label("Humidity:")
    val humidityText = new Text("- %")
    Interface.getHumidity subscribe(x => humidityText setText x)
    grid.add(humidityLabel, 0, propertiesStart + 6)
    grid.add(humidityText, 1, propertiesStart + 6)

    val dust2Label = new Label("PM 2.5 dust level:")
    val dust2Text = new Text("-")
    Interface.getDust25.subscribe(x => dust2Text setText x)
    grid.add(dust2Label, 0, propertiesStart + 7)
    grid.add(dust2Text, 1, propertiesStart + 7)

    val dust10Label = new Label("PM 10 dust level:")
    val dust10Text = new Text("-")
    Interface.getDust10.subscribe(x => dust10Text setText x)
    grid.add(dust10Label, 0, propertiesStart + 8)
    grid.add(dust10Text, 1, propertiesStart + 8)

    val updateLabel = new Label("Last updated: ")
    val updateText = new Text("never")
    Interface.getUpdateTime subscribe(x => updateText setText x)
    grid.add(updateLabel, 0, propertiesStart + 9)
    grid.add(updateText, 1, propertiesStart + 9)
    val fieldCount = propertiesStart + 9

    val refresh = new Button("Refresh")
    JavaFxObservable.fromActionEvents(refresh).subscribe(_ => Interface.refreshWeather())
    grid.add(refresh, 2, fieldCount + 1)

    val toggleButton = new ToggleButton("Switch from Open Weather Map to Meteo.waw.pl?")
    JavaFxObservable.fromActionEvents(toggleButton).subscribe(_ => Interface.toggleSource())
    Interface.getToggle.subscribe(x => toggleButton setText x)
    grid.add(toggleButton, 0, fieldCount + 1, 2, 1)

    val scene = new Scene(grid, 800, 600)

    primaryStage setTitle "Weather in Warsaw"
    primaryStage setScene scene
    primaryStage show ()

    Interface refreshWeather ()
  }


}

