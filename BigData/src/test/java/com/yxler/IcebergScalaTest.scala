package com.yxler

import org.apache.spark.SparkConf
import org.apache.spark.sql.types.{LongType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}
import org.scalatest.{FunSuite, Matchers}


class IcebergScalaTest extends FunSuite with Matchers {
  test("iceberg-spark-insert") {
    val sparkConf = new SparkConf().setAppName("test").setIfMissing("spark.master", "local")
    sparkConf.set("spark.sql.catalog.hadoop_prod", "org.apache.iceberg.spark.SparkCatalog")
    sparkConf.set("spark.sql.catalog.hadoop_prod.type", "hadoop")
//    sparkConf.set("spark.sql.catalog.hadoop_prod.warehouse", "hdfs://localhost:9000/iceberg")
    sparkConf.set("spark.sql.catalog.hadoop_prod.warehouse", "/home/yxler/Desktop/data/iceberg")
    val spark = SparkSession.builder.config(sparkConf).getOrCreate
    spark.sparkContext.hadoopConfiguration.set("ipc.client.fallback-to-simple-auth-allowed", "true")
    val sqlContext = spark.sqlContext
    val schema = StructType(List(
      StructField("level", StringType, nullable = false),
      StructField("event_time", LongType, nullable = false),
      StructField("message", StringType, nullable = false),
    ))
    val insertData = spark.sparkContext.parallelize(Seq(
      Row("error", System.currentTimeMillis(), "null"),
      Row("info", System.currentTimeMillis(), "ok"),
      Row("warn", System.currentTimeMillis(), "warn")
    ))
    val insertDf = spark.sqlContext.createDataFrame(insertData, schema)

    spark.sql("use hadoop_prod");
    spark.sql("select * from hadoop_prod.logging.logs").show()
    //    val tables = sqlContext.sql("show logging.tables").collect()
    //    tables.foreach(r => println(r))
//    insertDf.writeTo("hadoop_prod.logging.logs").append()
  }


  test("select") {

    val sparkConf = new SparkConf().setAppName("test").setIfMissing("spark.master", "local")
    sparkConf.set("spark.sql.catalog.hadoop_prod", "org.apache.iceberg.spark.SparkCatalog")
    sparkConf.set("spark.sql.catalog.hadoop_prod.type", "hadoop")
    sparkConf.set("spark.sql.catalog.hadoop_prod.warehouse", "hdfs://localhost:9000/iceberg")
    val spark = SparkSession.builder.config(sparkConf).getOrCreate
    spark.sparkContext.hadoopConfiguration.set("ipc.client.fallback-to-simple-auth-allowed", "true")
    val sqlContext = spark.sqlContext
    sqlContext.sql("select * from hadoop_prod.logging.logs").show()
  }
}
