package rapture.uri

object UriCapable {
  class Capability[Res: UriCapable](res: Res) {
    def uri: Uri = implicitly[UriCapable[Res]].uri(res)
  }
}

case class Uri(scheme: String, schemeSpecificPart: String) {
  override def toString: String = s"$scheme:$schemeSpecificPart"
}

trait UriCapable[Res] {
  def uri(res: Res): Uri
}


object Linkable {
  class Capability[Res: Linkable](res: Res) {
    def link: Link = implicitly[Linkable[Res]].link(res)
  }

  implicit def linkableUri[Res: UriCapable]: Linkable[Res] = new Linkable[Res] {
    def link(res: Res): Link = Link(implicitly[UriCapable[Res]].uri(res).toString)
  }
}

case class Link(link: String) {
  override def toString: String = link
}

trait Linkable[Res] {
  def link(res: Res): Link
}
