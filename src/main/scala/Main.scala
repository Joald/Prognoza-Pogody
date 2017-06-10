import javafx.application.Application

/**
  * Main class used for launching the app.
  */
object Main {
  def main(args: Array[String]): Unit = {
    Application.launch(classOf[MainWindow], args: _*)
  }
}
