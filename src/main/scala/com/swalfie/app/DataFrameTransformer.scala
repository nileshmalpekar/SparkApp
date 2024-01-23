package com.swalfie.app

import org.apache.spark.sql.DataFrame

trait DataFrameTransformer {
  def transform(df: DataFrame): DataFrame
}