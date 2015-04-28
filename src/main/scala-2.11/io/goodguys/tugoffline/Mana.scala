package io.goodguys.tugoffline

import scala.annotation.tailrec

/**
 * Created by wonko on 2015-04-28.
 */


case class Mana(colors: List[ColorBase]) {

  override def toString: String = {
    if (colors.size == 1)
      colors.take(1).toString()
    else {
      "{" +
        colors.foldLeft("")((s: String, c: ColorBase) => {
          c match {
            case Nothing() => s
            case c: ColorBase => s + "," + c.toString
          }
        }) +
      "}"
    }
  }

  def pays(pay: List[ColorBase]): Boolean = {
    @tailrec
    def paysLoop(pay: List[ColorBase], remainingCost: List[ColorBase], fullCost: List[ColorBase]): Boolean = {
//      println (s"pay: $pay, remainingCost:$remainingCost, fullCost: $fullCost")
      if (remainingCost == Nil)
        true
      else {
        pay match {
          case Nil => remainingCost == Nil
          case Nothing() :: xs => paysLoop(xs, fullCost, fullCost)
          case x :: xs =>
            if (x == remainingCost.head) paysLoop(xs, fullCost, fullCost)
            else paysLoop(pay, remainingCost.tail, fullCost)
        }
      }
    }

    paysLoop(colors, pay, pay)
  }

  def pays(mana: Mana): Boolean = pays(mana.colors)

  def draw() = println(this)
}