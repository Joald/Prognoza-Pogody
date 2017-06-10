import java.util.Calendar

/**
  * Created by joald_000 on 09-Jun-17.
  */
object Time {
  def getCurrent: String = {
    var toReturn = ""
    toReturn += Calendar.getInstance().getTime
    toReturn
  }

}
