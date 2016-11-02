package ml.combust.mleap.runtime.serialization

import ml.combust.bundle.util.ClassLoaderUtil
import ml.combust.mleap.runtime.types.StructType
import ml.combust.mleap.runtime.Row

/**
  * Created by hollinwilkins on 11/1/16.
  */
object RowReader {
  def apply(schema: StructType,
            format: String = Defaults.format,
            classLoader: Option[ClassLoader] = None): RowReader = {
    ClassLoaderUtil.resolveClassLoader(classLoader).
      loadClass(s"$format.DefaultRowReader").
      getConstructor(classOf[StructType]).
      newInstance(schema).
      asInstanceOf[RowReader]
  }
}

trait RowReader {
  val schema: StructType

  def fromBytes(bytes: Array[Byte]): Row
}