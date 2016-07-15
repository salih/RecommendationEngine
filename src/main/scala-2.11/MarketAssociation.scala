
import org.apache.spark.mllib.fpm.AssociationRules
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.fpm.FPGrowth.FreqItemset

import scala.collection.immutable.HashMap
import scala.collection.mutable

/**
  * Created by sgedik on 7/13/16.
  */

/* cartesianProduct.foreach(k => {
      print("{")
      k._1.items.foreach(i => print(i+ " , "))
      print(k._1.freq +"}")
      print("{")
      k._2.items.foreach(i => print(i + " , "))
      println(k._2.freq + "}")

    }  )*/

/** mapByOccurence.foreach(k => {
  * print(k.freq+ " : ")
  * k.items.foreach(println)
  * }  ) */
object MarketAssociation {
  def main(args: Array[String]) {

    val items = Array("kola"
      ,"süt",
      "yogurt"
      , "bira"
      , "bez"
      ,
      "kalem"
      , "iphone telefon"
      , "ipad"->"apple", "macbook"
      , "gitar"
      , "ekmek", "yumurta"
      ,
      "cornflakes"
      , "cikolata"
      ,"samsung telefon"
      ,"tv",
      "kahve"
      ,"pizza","lg telefon"
      ,"lg kılıf"

    )
    val itemMap =  HashMap("kola"->"coca cola","süt"->"sütaş" , "yogurt"->"sütaş", "bira"->"miller", "bez"->"prima",
      "kalem"->"stabilo", "iphone telefon"->"apple", "ipad"->"apple", "macbook"->"apple", "gitar"->"fender", "ekmek"->"firinci", "yumurta"->"tavuk",
      "cornflakes"->"nestle", "cikolata"->"nestle","samsung telefon"->"samsung","tv"->"samsung"->
        "kahve"->"starbucks","pizza"->"papa johns","lg telefon"->"lg","lg kılıf"->"lg"



    )

    val conf = new SparkConf().setAppName("Market Association").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd = sc.textFile("file:///Users/sgedik/Spark/market.txt")
    val eachItem = rdd.flatMap(_.split(","))
      .map(k => (k, 1))
      .reduceByKey(_ + _).map(k =>
      new FreqItemset(Array(k._1), k._2.toLong))


    val mapByOccurence = rdd.flatMap(_.split(","))
      .map(k => {
        (k, 1)
      }).reduceByKey(_ + _).sortByKey(true, 1)
      .map(k => {
        new FreqItemset(Array(k._1), k._2.toLong)
      }).collect()



  /**  val finalRdd = mapByOccurence.union(cartesianProduct)
    val k = finalRdd
    val ar = new AssociationRules()
      .setMinConfidence(0.8)

    val sq = sc.parallelize(finalRdd).cache()
*/
  }


}
