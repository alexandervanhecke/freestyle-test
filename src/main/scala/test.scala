import cats.{Id, Traverse}
import freestyle._
import cats.instances.list._


object test {

  @free trait Generator {
    def getInt: FS[Int]
  }

  @module trait App {
    val generator: Generator
  }

  implicit val handler = new Generator.Handler[Id] {
    override def getInt: Id[Int] = 42
  }

  def getInt[F[_]]()(implicit module: App[F]): FreeS[F, Int] = {
    module.generator.getInt
  }

  def program[F[_]]()(implicit module: App[F]): FreeS[F, Int] = {
    val l: List[FreeS[F, Int]] = 1.to(10).toList.map(_ => getInt[F])
    // does not work
    Traverse[List].traverse(1.to(10).toList)(_ => getInt[F])
    // does work
//    Traverse[List].traverse(1.to(10).toList)(_ => module.generator.getInt)
    FreeS.pure(1)
  }
}