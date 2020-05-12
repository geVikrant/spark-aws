import org.apache.spark.{SparkConf, SparkContext}
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import java.io._



@RunWith(classOf[JUnitRunner])
object DemoClassSpec extends org.specs2.mutable.Specification {

  val conf = new SparkConf().setAppName("Simple Application").setMaster("local[*]")
  val sc = SparkContext.getOrCreate(conf)

  "this is a simple specification" >> {
    "where example 1 must be 1" >> {
      val file = new File("src/test/resources/result")
      Option(file.listFiles).map(_.toList).getOrElse(Nil).foreach(_.delete())
      file.delete
      DemoClass.processfile(sc, "src/test/resources/sample.txt", "src/test/resources/result")
      new File("src/test/resources/result").listFiles().size must_== 8
    }
  }







    }