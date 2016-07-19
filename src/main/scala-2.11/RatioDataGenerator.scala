import java.io.{BufferedWriter, File, FileWriter}

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
      "yogurt" -> 0.1,
      "bira" -> 0.2,
      "bez" -> 0.1111122,
      "kalem" -> 0.5,
      "iphone telefon" -> 0.8,
      "iphone kılıf" -> 0.76,
      "ipad" -> 0.366,
      "macbook" -> 0.27,
      "gitar" -> 0.14,
      "ekmek" -> 0.44,
      "yumurta" -> 0.223,
      "cornflakes" -> 0.12121212,
      "cikolata" -> 0.323232,
      "samsung telefon" -> 0.87654656456,
      "samsung kılıf" -> 0.805555,
      "tv" -> 0.1656565656,
      "kahve" -> 0.1567654,
      "pizza" -> 0.23232,
      "lg telefon" -> 0.7875432,
      "lg kılıf" -> 0.7654343
    )
    val file = new File("/Users/sgedik/Spark/market.txt")
    val bw = new BufferedWriter(new FileWriter(file))

    val maxTransactionToGenerate = 10000000
    val rowItemSize = new Array[Int](maxTransactionToGenerate)
    val itemsAsArray = itemRatio.toArray
    val totalRatio = itemRatio.values.sum
    val random = new Random
    var counter = 0

    for (i <- 0 until maxTransactionToGenerate) {
      val prob = 1 + random.nextInt(1000)
      var item=0
      if(prob>0 && prob <=20)
         item = 1
      else if(prob>20 && prob<=850)
        item = 2
      else
      item = 3
      rowItemSize(i) = item
      counter += item
    }
    val creditPerItem: Double = counter / totalRatio
    var itemsByCredit = new mutable.HashMap[String, Int]()
    for (k <- itemRatio.keys) {
      itemsByCredit += k -> (creditPerItem * itemRatio(k)).toInt
    }
  // var sort = itemsByCredit.toList.sortWith((k, v) => k._2 > v._2)
    for (i <- 0 until maxTransactionToGenerate) {
      var str = new StringBuilder
      val inserted: mutable.HashSet[Int] = new mutable.HashSet
      var insKey = new mutable.HashSet[String]()

      for (j <- 0 until rowItemSize(i)) {
        var id = random.nextInt(itemRatio.size)
        var lookup = itemsAsArray(id)._1
        var ctr = 0
        var broken = false
        while (inserted.contains(id) && itemsByCredit(lookup)>0  &&ctr < itemRatio.size) {
          ctr = ctr + 1
          id = random.nextInt(itemRatio.size)
          lookup = itemsAsArray(id)._1

        }
        var item = itemsAsArray(id)._1.trim()
        if(insKey.contains(item) == false) {
          insKey = insKey + item
          str ++= item
          itemsByCredit(lookup) = itemsByCredit(lookup)-1
          if(j != rowItemSize(i))
            str +=','
          else
            str += '\n'
        }
      }
      if(str.charAt(str.length-1) == ',') {
        str.deleteCharAt(str.length-1)
        str+= '\n'
      }
    bw.write(str.toString())
      insKey.clear()
    }
    bw.close()
  }
}
