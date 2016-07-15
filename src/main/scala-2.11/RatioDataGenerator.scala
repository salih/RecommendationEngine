import scala.collection.immutable.HashMap
import scala.collection.mutable
import scala.util.Random

/**
  * Created by sgedik on 7/15/16.
  */
object RatioDataGenerator {


  def main(args: Array[String]): Unit = {

    val itemRatio = HashMap[String, Double]("kola" -> 0.4,
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
      "cornflakes" -> 0.12121212,
      "cikolata" -> 0.323232,
      "samsung telefon" -> 0.87654656456,
      "tv" -> 0.5656565656,
      "kahve" -> 0.4567654,
      "pizza" -> 0.23232,
      "lg telefon" -> 0.7875432,
      "lg kılıf" -> 0.7654343
    )

    val itemsByRemain = mutable.HashMap()
    val maxTransactionToGenerate = 100000
    val rowItemSize = Array[Int](maxTransactionToGenerate)
    val itemsAsArray = itemRatio.toArray
    val total = itemRatio.values.sum
    val random = new Random
    var counter =0
    // println(itemsAsArray)


    for (i <- 0 until maxTransactionToGenerate) {
      val item = 1 + random.nextInt(itemRatio.size-1)
      rowItemSize(i) = item
      counter += item
    }
    val creditPerItem: Double = maxTransactionToGenerate*counter / total
    var itemsByCredit = mutable.Map[String, Int]()
    for (k <- itemRatio.keys) {
      itemsByCredit += k -> (creditPerItem * itemRatio(k)).toInt
    }
    for (k <- itemsByCredit.keys)
      println(k + " : " + itemsByCredit(k))


    for (i <- 1 to 100000) {
      var str = new StringBuilder

      val maxPerLine = 1 + random.nextInt(itemRatio.size - 1)
      var inserted: mutable.HashSet[Int] = new mutable.HashSet
      for (j <- 1 to maxPerLine) {
        var id = random.nextInt(itemRatio.size)
        while (inserted.contains(id)) {
          id = random.nextInt(itemRatio.size)
        }
        inserted += id
        val item = itemsAsArray(id)._1
        str ++= item
        if (j != maxPerLine)
          str += ','
        else
          str += '\n'
      }
      print(str)

    }






  }

}
