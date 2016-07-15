/**
  * Created by sgedik on 7/13/16.
  */

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.fpm.FPGrowth
import org.apache.spark.rdd.RDD

import scala.collection.immutable.HashMap
import scala.collection.mutable

object FreqMarket {
  def main(args: Array[String]) {
    val items = Array("kola",
      "süt",
      "yogurt",
      "bira",
      "bez",
      "kalem",
      "iphone telefon", "ipad", "macbook"
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
    val itemSet = mutable.HashSet()

    val itemMap = HashMap("kola" -> "coca cola", "süt" -> "sütaş", "yogurt" -> "sütaş", "bira" -> "miller", "bez" -> "prima",
      "kalem" -> "stabilo", "iphone telefon" -> "apple", "ipad" -> "apple", "macbook" -> "apple", "gitar" -> "fender", "ekmek" -> "firinci", "yumurta" -> "tavuk",
      "cornflakes" -> "nestle", "cikolata" -> "nestle", "samsung telefon" -> "samsung", "tv" -> "samsung" ->
        "kahve" -> "starbucks", "pizza" -> "papa johns", "lg telefon" -> "lg", "lg kılıf" -> "lg"


    )

    val conf = new SparkConf().setAppName("Market Association").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val data = sc.textFile("file:///Users/sgedik/Spark/market.txt")

    val transactions: RDD[Array[String]] = data.map(s => s.trim.split(','))

    val fpg = new FPGrowth()
      .setMinSupport(0.2)
    val model = fpg.run(transactions)

    /*  model.freqItemsets.collect().foreach { itemset =>
        println(itemset.items.mkString("[", ",", "]") + ", " + itemset.freq)
      }*/
    /**
      * .foreach { rule =>
      * println(
      * rule.antecedent.mkString("[", ",", "]")
      * + " => " + rule.consequent.mkString("[", ",", "]")
      * + ", " + rule.confidence)
      * *
      * }
      */

    /**
      * foreach { rule =>
      * if (rule.antecedent.filter(k => {
      * itemMap(k) == itemMap(rule.consequent)
      * }).size > 0) {
      * println("yes")
      * }
      * *
      *
      * }
      */
    val minConfidence = 0.3

    /**
      *
      *  if keyword is existing in itemlist,
      *
      */
    model.generateAssociationRules(minConfidence).collect().foreach { rule =>
      if (rule.antecedent.filter(k => {
        val keywords = k.split(" ")
        keywords.count(keyword => {
          // TODO fix contains
          rule.consequent.mkString.contains(keyword)

        }) > 0
      }).size > 0) {


        println(
          rule.antecedent.mkString("[", ",", "]")
            + " => " + rule.consequent.mkString("[", ",", "]")
            + ", " + rule.confidence)


      }


    }


  }


}
