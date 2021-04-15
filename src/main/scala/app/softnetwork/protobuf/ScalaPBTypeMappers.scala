package app.softnetwork.protobuf

import java.time.{Instant, LocalDateTime, ZonedDateTime, ZoneId}

import com.google.protobuf.timestamp.Timestamp

import scala.language.implicitConversions
import scalapb.TypeMapper

/**
  * Created by smanciot on 02/04/2021.
  */
object ScalaPBTypeMappers extends TimestampTypeMappers

trait TimestampTypeMappers {
  implicit def instant2Timestamp(instant: Instant): Timestamp = {
    Timestamp(instant.getEpochSecond, instant.getNano)
  }

  implicit def timestamp2Instant(timestamp: Timestamp): Instant =
    Instant.ofEpochSecond(
      timestamp.seconds,
      timestamp.nanos
    )

  implicit val timestamp2InstantTypeMapper: TypeMapper[Timestamp, Instant] = new TypeMapper[Timestamp, Instant] {
    override def toBase(custom: Instant): Timestamp = custom
    override def toCustom(base: Timestamp): Instant = base
  }

  implicit def timestamp2LocalDateTime(timestamp: Timestamp): LocalDateTime = {
    LocalDateTime.ofInstant(
      Instant.ofEpochSecond(
        timestamp.seconds,
        timestamp.nanos
      ),
      ZoneId.systemDefault()
    )
  }
  implicit def localDateTime2Timestamp(localDateTime: LocalDateTime): Timestamp = {
    Timestamp(localDateTime.getSecond, localDateTime.getNano)
  }

  implicit val timestamp2LocalDateTimeTypeMapper: TypeMapper[Timestamp, LocalDateTime] = new TypeMapper[Timestamp, LocalDateTime] {
    override def toBase(custom: LocalDateTime): Timestamp = custom
    override def toCustom(base: Timestamp): LocalDateTime = base
  }

  implicit def timestamp2ZonedDateTime(timestamp: Timestamp): ZonedDateTime = {
    ZonedDateTime.ofInstant(
      Instant.ofEpochSecond(
        timestamp.seconds,
        timestamp.nanos
      ),
      ZoneId.systemDefault()
    )
  }
  implicit def zonedDateTime2Timestamp(zonedDateTime: ZonedDateTime): Timestamp = {
    Timestamp(zonedDateTime.getSecond, zonedDateTime.getNano)
  }

  implicit val timestamp2ZonedDateTimeTypeMapper: TypeMapper[Timestamp, ZonedDateTime] = new TypeMapper[Timestamp, ZonedDateTime] {
    override def toBase(custom: ZonedDateTime): Timestamp = custom
    override def toCustom(base: Timestamp): ZonedDateTime = base
  }

}
