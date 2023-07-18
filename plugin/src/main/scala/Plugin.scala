package foo

import dotty.tools.dotc.plugins._

class FooPlugin extends StandardPlugin {
  val name: String = "foo"

  override val description: String = "Foo description"

  def init(options: List[String]): List[PluginPhase] =
    FooPhase() :: Nil
}
