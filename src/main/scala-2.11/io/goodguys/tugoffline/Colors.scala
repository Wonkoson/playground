package io.goodguys.tugoffline

/**
 * Created by wonko on 2015-04-28.
 */

import scala.language.implicitConversions

trait ColorBase {
  override def toString = "x"
  def draw() = println("x")
}

object ColorBase {
  implicit def colorBase2List(colorBase: ColorBase): List[ColorBase] = List(colorBase)
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
case class Colorless() extends ColorBase {
  override def toString = "O"
  override def draw() = println("O")
}
case class Nothing() extends ColorBase {
  override def toString = "x"
  override def draw() = println("x")
}