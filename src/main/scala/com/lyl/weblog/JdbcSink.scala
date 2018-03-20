package com.lyl.weblog

import java.sql._

import org.apache.spark.sql.{ForeachWriter, Row}

/**
  * Created by liuyanling on 2018/3/18   
  */
class JdbcSink(url: String, user: String, pwd: String) extends ForeachWriter[Row] {
  var statement: Statement = _ //_的含义 初始化对象
  var resultSet: ResultSet = _
  var connection: Connection = _

  override def open(partitionId: Long, version: Long): Boolean = {
    connection = DriverManager.getConnection(url, user, pwd)
    statement = connection.createStatement()
    return true
  }

  override def process(value: Row): Unit = {

    val titleName = value.getAs[String]("titleName").replaceAll("[\\[\\]]", "")
    val webcount = value.getAs[Long]("webcount");
    //mysql中文乱码的解决：
    //vim /etc/my.cnf
    /*
    [client]
    socket=/var/lib/mysql/mysql.sock
    default-character-set=utf8
    [mysqld]
    character-set-server=utf8
    datadir=/var/lib/mysql
    socket=/var/lib/mysql/mysql.sock
    */
    //create table weblog (titleName varchar(255) character set utf8 default null,webcount int(11))ENGINE=InnoDB default charset=utf8;
    val querySql = "select * from weblog where titleName = '" + titleName + "'"

    val updateSql = "update weblog set webcount = " + webcount + " where titleName = '" + titleName + "'"

    val insertSql = "insert into weblog(titleName,webcount)" + "values('" + titleName + "'," + webcount + ")"

    try {
      var resultSet = statement.executeQuery(querySql)
      if (resultSet.next()) {
        statement.executeUpdate(updateSql)
      } else {
        statement.execute(insertSql)
      }
    } catch {
      case ex: SQLException => {
        println("SQLException")
      }
      case ex: Exception => {
        println("Exception")
      }
      case ex: RuntimeException => {
        println("RuntimeException")
      }
      case ex: Throwable => {
        println("Throwable")
      }
    }
  }

  override def close(errorOrNull: Throwable): Unit = {

    if (statement == null) {
      statement.close()
    }
    if (connection == null) {
      connection.close()
    }
  }
}

