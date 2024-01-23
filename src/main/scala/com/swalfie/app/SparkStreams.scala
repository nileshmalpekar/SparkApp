package com.swalfie.app

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{FloatType, LongType, StringType, TimestampType}

object SparkStreams {

  def apply(spark: SparkSession): SparkStreams = {
    new SparkStreams(spark)
  }
}

@SerialVersionUID(100L)
class SparkStreams(spark: SparkSession) extends DataFrameTransformer with Serializable {

    import spark.implicits._

    override def transform(df: DataFrame): DataFrame = df
        .transform(abc)

    def abc(df: DataFrame): DataFrame = {
        df
        .withColumn("_tmp", split(col("value"), ",")) // value is typically num,word
        .select(
            unix_timestamp().cast(TimestampType).as("ts_origin"), 
            $"_tmp".getItem(1).as("key"),
            // converting num seconds to num minutes
            (($"_tmp".getItem(0).cast(FloatType)*60).cast(LongType)).cast(TimestampType).as("ts_event")
        )
    }
}