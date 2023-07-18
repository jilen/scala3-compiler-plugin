package example

object Foo

@main
def app(): Unit = {
  Foo.getClass.getDeclaredMethods().foreach(m => println(m.getName))

  // Foo.foo
}
