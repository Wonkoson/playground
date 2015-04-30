package io.goodguys.tugoffline

/**
 * Created by wonko on 2015-04-28.
 */

import scala.annotation.tailrec
import scala.language.implicitConversions

class ColorBase() {
  def numTimes(n: Int, s: String): String = {
    @tailrec
    def loop(i: Int, r: String): String = {
      if (i > 0) loop(i - 1, r ++ s) else r
    }
    loop(n, "")
  }
  override def toString = numTimes(1, "x")
  def draw() = println(this.to)
}

object ColorBase {

  implicit def colorbase2mana(colorBase: ColorBase): Mana = Mana(colorBase)

  implicit def colorbase2list(colorBase: ColorBase): List[ColorBase] = List(colorBase)
}

case class White() extends ColorBase {
  override def toString = "W"
  override def draw() = println("W")
}
case class Blue() extends ColorBase {
  override def toString = "U"
  override def draw() = println("U")
}
case class Black() extends ColorBase {
  override def toString = "B"
  override def draw() = println("B")
}
case class Red() extends ColorBase {
  override def toString = "R"
  override def draw() = println("R")
}
case class Green() extends ColorBase {
  override def toString = "G"
  override def draw() = println("G")
}
class Colorless() extends ColorBase {
  override def toString = "C"
  override def draw() = println(this.toString)
}

case class ZeroColorless() extends Colorless {
  override def toString = "O"
  override def draw() = println(this.toString)
}
case class OneColorless() extends Colorless {
  override def toString = "1"
  override def draw() = println(this.toString)
}
case class TwoColorless() extends Colorless {
  override def toString = "2"
  override def draw() = println(this.toString)
}
case class ThreeColorless() extends Colorless {
  override def toString = "3"
  override def draw() = println(this.toString)
}
case class FourColorless() extends Colorless {
  override def toString = "4"
  override def draw() = println(this.toString)
}
case class FiveColorless() extends Colorless {
  override def toString = "5"
  override def draw() = println(this.toString)
}
case class SixColorless() extends Colorless {
  override def toString = "6"
  override def draw() = println(this.toString)
}
case class SevenColorless() extends Colorless {
  override def toString = "7"
  override def draw() = println(this.toString)
}
case class EightColorless() extends Colorless {
  override def toString = "8"
  override def draw() = println(this.toString)
}
case class NineColorless() extends Colorless {
  override def toString = "9"
  override def draw() = println(this.toString)
}
case class TenColorless() extends Colorless {
  override def toString = "1O"
  override def draw() = println(this.toString)
}
case class ElevenColorless() extends Colorless {
  override def toString = "11"
  override def draw() = println(this.toString)
}
case class TwelveColorless() extends Colorless {
  override def toString = "12"
  override def draw() = println(this.toString)
}
case class ThirteenColorless() extends Colorless {
  override def toString = "13"
  override def draw() = println(this.toString)
}
case class FourteenColorless() extends Colorless {
  override def toString = "14"
  override def draw() = println(this.toString)
}
case class FifteenColorless() extends Colorless {
  override def toString = "15"
  override def draw() = println(this.toString)
}

case class XColorless() extends Colorless {
  override def toString = "X"
  override def draw() = println(this.toString)
}


case class Nothing() extends ColorBase {
  override def toString = "_"
  override def draw() = println(this.toString)
}
