package com.lyl.weblog

import org.apache.spark.sql.SparkSession

/**
  * Created by liuyanling on 2018/3/18
  * 运行Spark本地模式，直接在IDEA上跑就行了，无需打包。
  */
object StructuredStreaming {

  case class Weblog(datatime: String,
                    userid: String,
                    searchname: String,
                    retorder: String,
                    cliorder: String,
                    cliurl: String)

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .master("local[2]")
      .appName("streaming").getOrCreate()

    val df = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "master:9092")
      .option("subscribe", "weblogs")
      .load()

    import spark.implicits._
    val lines = df.selectExpr("CAST(value AS STRING)").as[String]
    val weblog = lines.map(_.split(",")).map(x => Weblog(x(0), x(1), x(2), x(3), x(4), x(5)))
    val titleCount = weblog.groupBy("searchname").count().toDF("titleName", "webcount")

    val url = "jdbc:mysql://master:3306/weblog?useSSL=false"
    val username = "root"
    val password = "123456"

    val writer = new JdbcSink(url, username, password)
    val weblogcount = titleCount.writeStream
      .foreach(writer)
      .outputMode("update")
      .start()

    weblogcount.awaitTermination()
  }

}
