package com.betobeto.events.service

import doobie._
import cats.effect._
import scala.concurrent.ExecutionContext
import javax.sql.DataSource
import org.apache.commons.dbcp2.BasicDataSource
import org.flywaydb.core.Flyway

object PhoenixDatabase {

  def initialize[M[_] : Async](
    transactor: Transactor.Aux[M, DataSource]
  ): M[Unit] = transactor.configure { dataSource =>
    Async[M].delay {
      Flyway.configure().dataSource(dataSource).load().migrate()
    }
  }

  def resource[M[_] : Async](
    config: PhoenixConfig,
    connectEC: ExecutionContext
  ): Resource[M, Transactor.Aux[M, DataSource]] = {
    val alloc = Sync[M].delay(buildPhoenixDataSource(config))
    val free  = (dataSource: BasicDataSource) => Sync[M].delay(dataSource.close())
    for {
      dataSource <- Resource.make(alloc)(free)
    } yield Transactor.fromDataSource[M](dataSource, connectEC)
  }

  private def buildJdbcUrl(config: PhoenixConfig) = s"jdbc:phoenix:${config.ip}:${config.port}"

  private def buildPhoenixDataSource(config: PhoenixConfig) = {
    val phoenixDataSource = new BasicDataSource()
    phoenixDataSource.setUrl(buildJdbcUrl(config))
    phoenixDataSource.setDriverClassName("org.apache.phoenix.jdbc.PhoenixDriver")
    phoenixDataSource
  }

}

object HbaseDataBase {

  def initialize[M[_] : Async](
    transactor: Transactor.Aux[M, DataSource]
  ): M[Unit] = transactor.configure { dataSource =>
    Async[M].delay {
      Flyway.configure().dataSource(dataSource).load().migrate()
    }
  }

  def resource[M[_] : Async](
    config: PhoenixConfig,
    connectEC: ExecutionContext
  ): Resource[M, Transactor.Aux[M, DataSource]] = {
    val alloc = Sync[M].delay(buildPhoenixDataSource(config))
    val free  = (dataSource: BasicDataSource) => Sync[M].delay(dataSource.close())
    for {
      dataSource <- Resource.make(alloc)(free)
    } yield Transactor.fromDataSource[M](dataSource, connectEC)
  }

}
