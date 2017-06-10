import rx.subjects.PublishSubject

/**
  * Created by joald_000 on 08-Jun-17.
  */
class Weather(){

  private var temperature = ""
  private var pressure = ""
  private var cloudiness = ""
  private var humidity = ""
  private var windSpeed = ""
  private var windDirection = ""
  private var dust2 = ""
  private var dust10 = ""

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

  private def updateWeather(newTemperature: String,
                    newPressure: String,
                    newCloudiness: String,
                    newHumidity: String,
                    newWindSpeed: String,
                    newWindDirection: String,
                    newDust2: String,
                    newDust10: String): Unit = {
    temperature = newTemperature
    pressure = newPressure
    cloudiness = newCloudiness
    humidity = newHumidity
    windSpeed = newWindSpeed
    windDirection = newWindDirection
    dust2 = newDust2
    dust10 = newDust10

    temperaturePipe onNext temperature + " °C"
    pressurePipe onNext pressure + " Pa"
    cloudinessPipe onNext cloudiness + "%"
    humidityPipe onNext humidity + "%"
    windSpeedPipe onNext windSpeed + " km/h"
    windDirectionPipe onNext windDirection + "°"
    dust2Pipe onNext dust2
    dust10Pipe onNext dust10
    updatePipe onNext Time.getCurrent
  }

  def updateWeather(weather: Array[String]): Unit = {
    updateWeather(weather(0), weather(1), weather(2), weather(3), weather(4), weather(5), weather(6), weather(7))
  }

  /*def this() {
    this()
    updateWeather(0,0,0,0,0,0)
  }*/

}
