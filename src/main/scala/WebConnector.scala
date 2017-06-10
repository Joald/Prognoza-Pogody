import org.jsoup._
import org.json4s._
import org.json4s.native.JsonMethods._

/**
  WebConnector object serves as a web client to connect to the weather services.
 */
object WebConnector {

  /**
    * Case classes for JSON parsing.
    */
  case class Wind(speed: Option[Double], deg: Option[Int])
  case class Clouds(all: Option[Int])
  case class Main(temp: Option[Double], pressure: Option[Int], humidity: Option[Int])
  case class WeatherRequest(main: Main, wind: Wind, clouds: Clouds)
  case class Values(`PM2.5`: Option[Double], PM10: Option[Double])
  case class Station(stationName: String, values: Values)

  /**
    * Constant for unit conversion.
    */
  val kelvinMinusCelsius = 273.15

  /**
    * Converts an Option to a String to fit the program's criteria
    * @param x: Option
    * @tparam T: Option's parameter
    * @return x as String
    */
  def optionToString[T](x: Option[T]): String = x match {
    case Some(value) => value.toString
    case None => "-"
  }

  /**
    * Downloads weather data from Open Weather Map.
    * @return Array of String in order:
    *         temperature
    *         pressure
    *         cloudiness
    *         humidity
    *         wind speed
    *         wind direction
    */
  def importWeatherFromOpenWeather(): Array[String] = {
    implicit val formats = DefaultFormats

    val openWeather = "http://api.openweathermap.org/data/2.5/weather?q=Warsaw&APPID=f53ee38569d951e82bddb798929d6964"
    val response = Jsoup.connect(openWeather).ignoreContentType(true).execute().body()
    val json = parse(response)
    val weather = json.extract[WeatherRequest]

    Array(
      optionToString(weather.main.temp match {
        case Some(value) => Some(value - kelvinMinusCelsius)
        case None => None
      }),
      optionToString(weather.main.pressure),
      optionToString(weather.clouds.all),
      optionToString(weather.main.humidity),
      optionToString(weather.wind.speed),
      optionToString(weather.wind.deg)
    )
  }

  /**
    * Downloads weather data from Meteo.waw.pl.
    * @return Array of String in order:
    *         temperature
    *         pressure
    *         cloudiness
    *         humidity
    *         wind speed
    *         wind direction
    * @return
    */
  def importWeatherFromMeteo(): Array[String] = {
    val meteo = "http://www.meteo.waw.pl/"
    val response = Jsoup.connect(meteo)
    var toReturn = Array[String]()

    val document = response.get()
    toReturn :+= document.select("#PARAM_TA").text()
    toReturn :+= document.select("#PARAM_PR").text()
    toReturn :+= "-"
    toReturn :+= document.select("#PARAM_RH").text()
    toReturn :+= document.select("#PARAM_WP10").text()
    toReturn :+= document.select("#PARAM_WD").text()
    toReturn
  }

  /**
    * Downloads dust data.
    * @return Array of String in order:
    *         dust PM2.5
    *         dust PM10
    */
  def importDusts():Array[String] = {
    implicit val formats = DefaultFormats

    val airGov = "http://powietrze.gios.gov.pl/pjp/current/getAQIDetailsList?param=AQI"
    val response = Jsoup.connect(airGov).ignoreContentType(true).execute().body()
    val json = parse(response)
    val dustList = json.extract[List[Station]]

    val warsawDusts = dustList.filter(
      x => x.stationName.substring(0, 8) == "Warszawa"
        && (x.values.PM10 match {
          case Some(_) => true
          case _ => false
        })
        && (x.values.`PM2.5` match {
        case Some(_) => true
        case _ => false
        })
    )
    if (warsawDusts.isEmpty)
      throw new Exception
    val dust25 = warsawDusts match {
      case h::_ => h.values.`PM2.5`
      case _ => throw new Exception
    }
    val dust10 = warsawDusts match {
      case h::_ => h.values.PM10
      case _ => throw new Exception
    }

    Array(optionToString(dust25), optionToString(dust10))
  }


}