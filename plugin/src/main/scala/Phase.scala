package foo

import dotty.tools.dotc.ast.Trees.*
import dotty.tools.dotc.ast.tpd
import dotty.tools.dotc.core.Constants.Constant
import dotty.tools.dotc.core.Contexts.Context
import dotty.tools.dotc.core.Symbols.*
import dotty.tools.dotc.core.Types.*
import dotty.tools.dotc.core.Names.*
import dotty.tools.dotc.core.Flags.*
import dotty.tools.dotc.plugins.{PluginPhase, StandardPlugin}
import dotty.tools.dotc.report
import dotty.tools.dotc.parsing._
import dotty.tools.dotc.typer._
import dotty.tools.dotc.transform.*

class FooPhase() extends PluginPhase {
  import tpd._

  val phaseName = "FooPhase"

  override val runsAfter: Set[String] = Set(TyperPhase.name)
  override val runsBefore: Set[String] = Set(PostTyper.name)

  override def prepareForUnit(tree: Tree)(using ctx: Context): Context = {
    requiredClassRef("java.lang.String")
    ctx
  }


  override def transformTypeDef(tree: TypeDef)(using ctx: Context): Tree = {
    def newValSym(owner: Symbol) = newSymbol(
      owner,
      termName("foo"),
      Lazy,
      requiredClassRef("java.lang.String")
    )
    def newValDef(owner: Symbol) =
      ValDef(newValSym(owner).asTerm, Literal(Constant("foo")))
    val newRhs = tree.rhs match {
      case t: Template =>
        cpy.Template(t)(body = t.body :+ newValDef(tree.symbol))
    }
    val newTree = cpy.TypeDef(tree)(rhs = newRhs)
    println(newTree.show)
    newTree
  }

}
