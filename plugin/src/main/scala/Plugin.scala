package derive

import dotty.tools.dotc.plugins._

class DerivePlugin extends StandardPlugin {
  val name: String = "foo"

  override val description: String = "Apply derive annotation"

  def init(options: List[String]): List[PluginPhase] =
    val setting = Setting.parse(options)
    FooPhase() :: Nil
}
