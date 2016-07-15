/**
  * Created by sgedik on 7/14/16.
  */

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.fpm.FPGrowth
import org.apache.spark.rdd.RDD

object ItemTest {

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Market Association").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val data: RDD[Array[String]] = sc.textFile("file:///Users/sgedik/Spark/sample_fpgrowth.txt").map(s => s.trim.split(' '));




  }
}
