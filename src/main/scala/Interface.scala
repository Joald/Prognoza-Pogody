import rx.lang.scala.Observable

/**
  * Created by Admin on 08.06.2017.
  */
object Interface {
  private var weather = new Weather(0,0,0,0,0,0)
  private var bank = Observable.just(1)
  def getBank:Observable[Int] = bank
  def getWeather: Weather = weather
  def updateWeather(a: Double, b: Int, c: Int, d: Int, e: Double, f: Int): Unit = {
    weather = new Weather(a, b, c, d, e, f)
  }
  def refreshWeather(): Unit = {
    print("test")
  }
}
