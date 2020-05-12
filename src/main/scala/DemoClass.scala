
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import java.util.UUID

object DemoClass {


  def main(args: Array[String]): Unit = {
    val logFile = args(0) // Should be some file on your system
    val destination = args(1)
    val conf = new SparkConf().setAppName("SAMPLE Application")
    val sc = SparkContext.getOrCreate(conf)
    processfile(sc, logFile, destination)
  }

  def processfile(sc: SparkContext, logFile:String, destination:String) : Unit = {
    val textFile = sc.textFile(logFile)
    val counts = textFile.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)
    counts.saveAsTextFile(destination)
  }


}

