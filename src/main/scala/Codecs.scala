package srpc

import scala.reflect.runtime.universe.TypeTag
import scodec.{Codec,Decoder,Encoder}

case class Codecs(decoders: Decoders,
                  encoders: Encoders) {

  def encoder[A:TypeTag:Encoder]: Codecs =
    this.copy(encoders = encoders.encoder[A])

  def decoder[A:TypeTag:Decoder]: Codecs =
    this.copy(decoders = decoders.decoder[A])

  def codec[A:TypeTag:Codec]: Codecs =
    Codecs(decoders.decoder[A], encoders.encoder[A])
}

object Codecs {

  val empty = Codecs(Decoders.empty, Encoders.empty)
}
