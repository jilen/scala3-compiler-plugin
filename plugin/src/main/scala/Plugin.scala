package derive

import dotty.tools.dotc.plugins._

class DerivePlugin extends StandardPlugin {
  val name: String = "foo"

  override val description: String = "Foo description"

  def init(options: List[String]): List[PluginPhase] =
    FooPhase() :: Nil
}
