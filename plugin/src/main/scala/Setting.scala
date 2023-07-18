package derive

case class Setting(
  deriveRules: Seq[Setting.Rule]
)

object Setting {

  case class Rule(
    annot: String,
    typeclass: String,
    deriveModule: String
  )

  def parse(s: List[String]): Setting = {
    val rules = s.map(_.split('|')).collect { case Array(a, t, d) =>
      Rule(a, t, d)
    }
    Setting(rules)
  }
}
