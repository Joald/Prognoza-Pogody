import rx.subjects.PublishSubject

/**
  * Weather class stores weather data and updates it.
  */
class Weather(){
  /**
    * Variables storing weather properties.
    */
  private var temperature = ""
  private var pressure = ""
  private var cloudiness = ""
  private var humidity = ""
  private var windSpeed = ""
  private var windDirection = ""
  private var dust2 = ""
  private var dust10 = ""

  /**
    * PublishSubjects used for updating the Model.
    */
  private val temperaturePipe = PublishSubjectFactory.stringPipe()
  def getTemperaturePipe: PublishSubject[String] = temperaturePipe

  private val pressurePipe = PublishSubjectFactory.stringPipe()
  def getPressurePipe: PublishSubject[String] = pressurePipe

  private val cloudinessPipe = PublishSubjectFactory.stringPipe()
  def getCloudinessPipe: PublishSubject[String] = cloudinessPipe

  private val humidityPipe = PublishSubjectFactory.stringPipe()
  def getHumidityPipe: PublishSubject[String] = humidityPipe

  private val windSpeedPipe = PublishSubjectFactory.stringPipe()
  def getWindSpeedPipe: PublishSubject[String] = windSpeedPipe

  private val windDirectionPipe = PublishSubjectFactory.stringPipe()
  def getWindDirectionPipe: PublishSubject[String] = windDirectionPipe

  private val dust2Pipe = PublishSubjectFactory.stringPipe()
  def getDust2Pipe: PublishSubject[String] = dust2Pipe

  private val dust10Pipe: PublishSubject[String] = PublishSubjectFactory.stringPipe()
  def getDust10Pipe: PublishSubject[String] = dust10Pipe

  private val updatePipe = PublishSubjectFactory.stringPipe()
  def getUpdatePipe: PublishSubject[String] = updatePipe

  /**
    * Updates weather parameters
    * @param weather: Array of strings that need to be
    *               in that particular order:
    *               temperature
    *               pressure
    *               cloudiness
    *               humidity
    *               windSpeed
    *               windDirection
    *               dust2
    *               dust10
    */
  def updateWeather(weather: Array[String]): Unit = {
    temperature = weather(0)
    pressure = weather(1)
    cloudiness = weather(2)
    humidity = weather(3)
    windSpeed = weather(4)
    windDirection = weather(5)
    dust2 = weather(6)
    dust10 = weather(7)

    temperaturePipe onNext temperature + (if(temperature == "-") "" else " °C")
    pressurePipe onNext pressure + (if(pressure == "-") "" else " hPa")
    cloudinessPipe onNext cloudiness + (if(cloudiness == "-") "" else "%")
    humidityPipe onNext humidity + (if(humidity == "-") "" else "%")
    windSpeedPipe onNext windSpeed + (if(windSpeed == "-") "" else " m/s")
    windDirectionPipe onNext windDirection + (if(windDirection == "-") "" else "°")
    dust2Pipe onNext dust2
    dust10Pipe onNext dust10
    updatePipe onNext Time.getCurrent
  }
}
