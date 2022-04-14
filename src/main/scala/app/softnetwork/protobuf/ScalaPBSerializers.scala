package app.softnetwork.protobuf

import org.json4s.JsonAST.{JInt, JString}
import org.json4s._
import org.json4s.reflect.TypeInfo

import scala.reflect.ClassTag
import scalapb.{GeneratedEnum, GeneratedEnumCompanion}

/**
  * Created by smanciot on 02/04/2021.
  */
object ScalaPBSerializers {

  case class GeneratedEnumSerializer[T <: GeneratedEnum: ClassTag](companion: GeneratedEnumCompanion[T])(
    implicit m: Manifest[T]) extends Serializer[T] {

    import JsonDSL._

    lazy val EnumerationClass: Class[_] = m.runtimeClass

    override def deserialize(implicit format: Formats): PartialFunction[(TypeInfo, JValue), T] = {
      case (_@TypeInfo(EnumerationClass, _), json) if isValid(json) =>
        json match {
          case JString(value) => companion.fromName(value).get
          case JInt(value) => companion.fromValue(value.toInt)
          case value => throw new MappingException(s"Can't convert $value to $EnumerationClass")
        }
    }

    override def serialize(implicit format: Formats): PartialFunction[Any, JValue] = {
      case i: T if companion.values.exists(t => t.value == i.value) => i.name
    }

    private[this] def isValid(json: JValue) = json match {
      case JString(value) if companion.fromName(value).isDefined => true
      case JInt(value) if value.isValidInt && companion.values.exists(t => t.value == value.toInt) => true
      case _ => false
    }

  }
}
