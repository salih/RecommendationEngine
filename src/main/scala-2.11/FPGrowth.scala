import org.apache.spark.mllib.fpm.FPGrowth
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by sgedik on 7/13/16.
  */
object FPGrowth {
  def main(args: Array[String]) {

      val conf = new SparkConf().setAppName("Algorithms ").setMaster("local[*]")

      val sc = new SparkContext(conf)

    //  val rdd = sc.textFile("file:///Users/sgedik/teamid.csv").flatMap(_.split("\n")).cache()

   // val model = new FPGrowth().setMinSupport(0.05).
     // setNumPartitions(1).run(rdd)

 //   println(model.freqItemsets.count())
 // println(rdd.first())
  //  model.freqItemsets.collect().foreach(println)
  }
}
