package app.softnetwork.protobuf

import java.time.Instant

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

}
