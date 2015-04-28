package io.goodguys.tugoffline

/**
 * Created by wonko on 2015-04-25.
 */

import scala.language.experimental.macros


//trait BaseColor {
//  override def toString = "x"
//  def draw() = println("x")
//}
//
//case class White() extends BaseColor {
//  override def toString = "W"
//  override def draw() = println("W")
//}
//case class Blue() extends BaseColor
//case class Black() extends BaseColor
//case class Red() extends BaseColor
//case class Green() extends BaseColor
//case class Colorless() extends BaseColor
//case class Nothing() extends BaseColor
//
//trait MagicColor[T <: BaseColor]
//
//case class OneColor(a: BaseColor) extends MagicColor[a.type] {
//  override def toString = ""
//  def draw() = a.draw()
//}



//case class OneColor(color: BaseColor) extends MagicColor {
//  override def toString = color.toString
//  def draw() = color.draw()
//  def isColorless() =
//}

trait CardType

case class Creature() extends CardType { override def toString = "Creature" }


case class MagicCard(
                      name: String,
                      image: String,
                      castingCost: List[Mana],
                      color: ColorBase,
                      convertedManaCost: Int,
                      types: List[String]
) {
  override def toString = "MagicCard"
}

//Error:(74, 29) macro implementation has incompatible shape:
//required: (c: scala.reflect.macros.blackbox.Context): c.Expr[io.goodguys.tugoffline.MagicColor]
//or      : (c: scala.reflect.macros.blackbox.Context): c.Tree
//found   : (): io.goodguys.tugoffline.MagicColor
//parameter lists have different length, required extra parameter c: scala.reflect.macros.blackbox.Context
//def W: MagicColor = macro ColorWhite
//^

object Macros {
  import io.goodguys.macros.Macros._
  def W: Mana = macro ManaImpl.ColorWhite
  def U: Mana = macro ManaImpl.ColorBlue
  def B: Mana = macro ManaImpl.ColorBlack
  def R: Mana = macro ManaImpl.ColorRed
  def G: Mana = macro ManaImpl.ColorGreen
  def C: Mana = macro ManaImpl.ColorColorless
  def N: Mana = macro ManaImpl.ColorNothing
}

