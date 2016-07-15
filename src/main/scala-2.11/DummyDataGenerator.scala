import scala.collection.mutable
import scala.util.Random
import java.io._

import scala.collection.immutable.HashMap

/**
  * Created by sgedik on 7/13/16.
  */
object DummyDataGenerator {
  def main(args: Array[String]) {
    val items = Array("kola",
      "süt",
      "yogurt",
      "bira",
      "bez",
      "kalem",
      "iphone telefon", "ipad" , "macbook"
      , "gitar"
      , "ekmek", "yumurta"
      ,
      "cornflakes"
      , "cikolata"
      , "samsung telefon"
      , "tv",
      "kahve"
      , "pizza", "lg telefon"
      , "lg kılıf"

    )

    val itemMap = HashMap("kola" -> "coca cola", "süt" -> "sütaş", "yogurt" -> "sütaş", "bira" -> "miller", "bez" -> "prima",
      "kalem" -> "stabilo", "iphone telefon" -> "apple", "ipad" -> "apple", "macbook" -> "apple", "gitar" -> "fender", "ekmek" -> "firinci", "yumurta" -> "tavuk",
      "cornflakes" -> "nestle", "cikolata" -> "nestle", "samsung telefon" -> "samsung", "tv" -> "samsung" ->
        "kahve" -> "starbucks", "pizza" -> "papa johns", "lg telefon" -> "lg", "lg kılıf" -> "lg"


    )

    val rand = new Random()
    val maxInline = items.length
    val file = new File("/Users/sgedik/Spark/market.txt")
    val bw = new BufferedWriter(new FileWriter(file))
    for (i <- 1 to 100000) {
      val line = new StringBuilder

      val existing: mutable.HashSet[Int] = new mutable.HashSet
      val rowLen = rand.nextInt(maxInline - 1) + 1
      for (j <- 0 to rowLen) {

        var id = rand.nextInt(maxInline)

        if (existing.contains(id)) {
          while (existing.contains(id))
            id = rand.nextInt(maxInline)
        }
        line ++= items(id)
        existing.add(id)
        if (j != rowLen)
          line += ','
        else
          line += '\n'


      }
      bw.write(line.toString())
    }

    bw.close()

    //  println(items(rand.nextInt(items.length)))

  }
}
