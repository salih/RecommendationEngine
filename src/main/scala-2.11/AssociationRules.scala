/**
  * Created by sgedik on 7/13/16.
  */
import org.apache.spark.mllib.fpm.AssociationRules
import org.apache.spark.mllib.fpm.FPGrowth.FreqItemset
// $example off$

import org.apache.spark.{SparkConf, SparkContext}
object AssociationRules {

    def main(args: Array[String]) {
      val conf = new SparkConf().setAppName("AssociationRulesExample").setMaster("local[*]")
      val sc = new SparkContext(conf)

      // $example on$
      val freqItemsets = sc.parallelize(Seq(
        new FreqItemset(Array("a"), 25L),
        new FreqItemset(Array("b"), 35L),

        new FreqItemset(Array("a", "b"), 28L)
      ))

      val ar = new AssociationRules()
        .setMinConfidence(0.80)
      val results = ar.run(freqItemsets)

      results.collect().foreach { rule =>

        println("[" + rule.antecedent.mkString(",")
          + "=>"
          + rule.consequent.mkString(",") + "]," + rule.confidence)
      }
      // $example off$
    }
}
