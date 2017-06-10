import rx.subjects.PublishSubject

/**
  * Created by Admin on 08.06.2017.
  */
object Interface {
  private val weather = new Weather()
  private var source = WeatherSource.OPEN_WEATHER
  private val togglePipe = PublishSubjectFactory.stringPipe()
  private val errorPipe = PublishSubjectFactory.stringPipe()
  private val numberOfWeatherProperties = 8
  def getTemperature: PublishSubject[String] = weather.getTemperaturePipe
  def getPressure: PublishSubject[String] = weather.getPressurePipe
  def getCloudiness: PublishSubject[String] = weather.getCloudinessPipe
  def getHumidity: PublishSubject[String] = weather.getHumidityPipe
  def getWindSpeed: PublishSubject[String] = weather.getWindSpeedPipe
  def getWindDirection: PublishSubject[String] = weather.getWindDirectionPipe
  def getDust25: PublishSubject[String] = weather.getDust2Pipe
  def getDust10: PublishSubject[String] = weather.getDust10Pipe
  def getToggle: PublishSubject[String] = togglePipe
  def getUpdateTime: PublishSubject[String] = weather.getUpdatePipe
  def getError: PublishSubject[String] =  errorPipe

  def toggleSource(): Unit = {
    if(source == WeatherSource.OPEN_WEATHER) {
      source = WeatherSource.METEO
      togglePipe.onNext("Meteo")
    }
    else {
      source = WeatherSource.OPEN_WEATHER
      togglePipe.onNext("Open Weather Map")
    }
    refreshWeather()
  }

  def refreshWeather(): Unit = {
    var weatherArray = new Array[String](0)
    var errorOccured = false
    try {
      if (source == WeatherSource.OPEN_WEATHER) {
        weatherArray ++= WebConnector.importWeatherFromOpenWeather()
      }
      else {
        weatherArray ++= WebConnector.importWeatherFromMeteo()
      }
      errorPipe onNext ""
    }
    catch {
      case _ : Exception =>
        errorOccured = true
        errorPipe onNext "Error. Unable to connect to weather server."
        weatherArray ++= Array("-", "-", "-", "-", "-", "-")
    }
    try {
      weatherArray ++= WebConnector.importDusts()
      if(!errorOccured)
        errorPipe onNext ""
    }
    catch {
      case _ : Exception =>
        errorPipe onNext "Error. Unable to connect to dust server."
        weatherArray ++= Array("-", "-")
    }

    for(_ <- 0 to numberOfWeatherProperties - weatherArray.length) {
      weatherArray ++= Array("-")
    }

    weather updateWeather weatherArray
  }
}
