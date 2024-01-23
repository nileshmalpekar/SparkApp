package com.swalfie.app

import org.apache.log4j.Logger
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{FloatType, IntegerType, LongType, StringType, StructField, StructType}
import org.apache.spark.sql.streaming.{DataStreamReader, DataStreamWriter, StreamingQuery}

object MainApp extends SparkApplication {
  val logger: Logger = Logger.getLogger("com.swalfie.app.MainApp")

  override def validateConfig()(implicit sparkSession: SparkSession): Boolean = {
    true
  }

  // data stream source reader
  lazy val inputStream: DataStreamReader = {
    sparkSession.readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", 9999)
  }

  override def run(): Unit = {
    super.run()

    val writer: DataStreamWriter[Row] = SparkStreams(sparkSession)
      .transform(inputStream.load())
      .writeStream
      .queryName("orders")
      .format("console")

    startAndAwaitApp(writer.start())
  }

  def startAndAwaitApp(query: StreamingQuery): Unit = {
    query.awaitTermination()
  }

  run()
}