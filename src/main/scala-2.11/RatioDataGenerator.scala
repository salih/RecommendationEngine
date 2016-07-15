import scala.collection.immutable.HashMap
import scala.util.Random

/**
  * Created by sgedik on 7/15/16.
  */
object RatioDataGenerator {


  def main(args: Array[String]): Unit = {

    val itemRatio = HashMap("kola" -> 0.4,
 "süt" -> 0.222,
 "yogurt" -> 0.3,
 "bira" -> 0.2,
 "bez" -> 0.222,
 "kalem" -> 0.5,
 "iphone telefon" -> 0.8,
 "ipad" -> 0.666,
 "macbook" -> 0.67,
 "gitar" -> 0.44,
 "ekmek" -> 0.44,
 "yumurta" -> 0.223,
 "cornflakes" -> 0.12121212 ,
 "cikolata" -> 0.323232,
 "samsung telefon" -> 0.87654656456,
 "tv" -> 0.5656565656 ->
 "kahve" -> 0.4567654,
 "pizza" ->  0.23232,
 "lg telefon" -> 0.7875432,
 "lg kılıf" -> 0.7654343
    )
  val random = new Random

    for(i <- 1 to 10) {

      val maxPerLine = random.nextInt(itemRatio.size)

      for(j <- 1 to maxPerLine) {

      val maxPer

      }

    }

  //  itemRatio.filter(k => k._2> 0.5).toArray.


  }

}
