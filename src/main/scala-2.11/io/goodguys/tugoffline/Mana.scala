package io.goodguys.tugoffline

//import io.goodguys.tugoffline.ColorBase

import scala.annotation.tailrec
import scala.language.implicitConversions

/**
 * Created by wonko on 2015-04-28.
 */


case class Mana(colors: List[ColorBase]) {

  override def toString: String = {
    if (colors.size == 1)
      colors(0).toString
    else {
      "{" +
        colors.foldLeft("")((s: String, c: ColorBase) => {
          c match {
            case Nothing() => "Nothing" + s
            case c: ColorBase => (if (! s.isEmpty) s + "/" else "") + c.toString
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

object Mana {

  implicit def mana2list(mana: Mana): List[Mana] = List(mana)

  def apply(s: String): Mana = {
    s match {
      case "W" => Mana(White())
      case "U" => Mana(Blue())
      case "B" => Mana(Black())
      case "R" => Mana(Red())
      case "G" => Mana(Green())
      case "0" => Mana(ZeroColorless())
      case "1" => Mana(OneColorless())
      case "2" => Mana(TwoColorless())
      case _ => Mana(Nothing())
    }
  }

  val AltTextToMana: Map[String, Mana] = Map(
    "White" -> Mana(White()),
    "Blue" -> Mana(Blue()),
    "Black" -> Mana(Black()),
    "Red" -> Mana(Red()),
    "Green" -> Mana(Green()),

    "Variable Colorless" -> Mana(XColorless()),
    "0" -> Mana(ZeroColorless()),
    "1" -> Mana(OneColorless()),
    "2" -> Mana(TwoColorless()),
    "3" -> Mana(ThreeColorless()),
    "4" -> Mana(FourColorless()),
    "5" -> Mana(FiveColorless()),
    "6" -> Mana(SixColorless()),
    "7" -> Mana(SevenColorless()),
    "8" -> Mana(EightColorless()),
    "9" -> Mana(NineColorless()),
    "10" -> Mana(TenColorless()),
    "11" -> Mana(ElevenColorless()),
    "12" -> Mana(TwelveColorless()),
    "13" -> Mana(ThirteenColorless()),
    "14" -> Mana(FourteenColorless()),
    "15" -> Mana(FifteenColorless()),

    "Two or White" -> Mana(TwoColorless() ++ White()),
    "Two or Blue" -> Mana(TwoColorless() ++ Blue()),
    "Two or Black" -> Mana(TwoColorless() ++ Black()),
    "Two or Red" -> Mana(TwoColorless() ++ Red()),
    "Two or Green" -> Mana(TwoColorless() ++ Green()),

    "White or Blue" -> Mana(White() ++ Blue()),
    "White or Black" -> Mana(White() ++ Black()),
    "White or Red" -> Mana(White() ++ Red()),
    "White or Green" -> Mana(White() ++ Green()),

    "Blue or White" -> Mana(Blue() ++ White()),
    "Blue or Black" -> Mana(Blue() ++ Black()),
    "Blue or Red" -> Mana(Blue() ++ Red()),
    "Blue or Green" -> Mana(Blue() ++ Green()),

    "Black or White" -> Mana(Black() ++ White()),
    "Black or Blue" -> Mana(Black() ++ Blue()),
    "Black or Red" -> Mana(Black() ++ Red()),
    "Black or Green" -> Mana(Black() ++ Green()),

    "Red or White" -> Mana(Red() ++ White()),
    "Red or Blue" -> Mana(Red() ++ Blue()),
    "Red or Black" -> Mana(Red() ++ Black()),
    "Red or Green" -> Mana(Red() ++ Green()),

    "Green or White" -> Mana(Green() ++ White()),
    "Green or Blue" -> Mana(Green() ++ Blue()),
    "Green or Black" -> Mana(Green() ++ Black()),
    "Green or Red" -> Mana(Green() ++ Red())
  )

  def gathererParseOneMana(s: String): Option[Mana] = AltTextToMana.get(s)

  def parseMana(text: String): List[Mana] = {

    def parseOneMana(s: String): Option[Mana] = gathererParseOneMana(s)

    def loop(remainingText: String, result: List[Mana]): List[Mana] = {
      if (remainingText.isEmpty)
        result
      else {
        val color: Option[Mana] = parseOneMana(remainingText)//parseOneMana(remainingText(0).toString)
        color match {
          case None => result
          case _ => loop(remainingText.drop(remainingText.length), result ++ color) //loop(remainingText.drop(1), result ++ color)
        }
      }
    }

    loop(text, List())
  }
}

//class ManaList private(list: List[Mana]) extends List[Mana]() {
//  override def toString() = {
//    "[" +
//    this.foldLeft("")((s, mana) => s + mana.toString()) +
//    "]"
//  }
//
//  override def productElement(n: Int): Any = ???
//
//  override def productArity: Int = ???
//}