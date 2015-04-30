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

trait Card

case class Creature() extends Card { override def toString = "Creature" }


trait Player


trait HookBase[T]

case class StartTurn() extends HookBase[StartTurn] { override def toString = "StartTurn" }
case class EndTurn() extends HookBase[EndTurn] { override def toString = "EndTurn" }


case class World()



class CardAttribute[K, V](key: K, value: V) extends Map[K, V] {

  override def toString(): String = s"$key: $value"

  override def get(key: K): Option[V] = if (this.key == key) Some(value) else None

  override def +[B1 >: V](kv: (K, B1)): Map[K, B1] = ???

  override def iterator: Iterator[(K, V)] = ???

  override def -(key: K): Map[K, V] = ???
}

case class Name(name: String) extends CardAttribute("Card Name", name)
case class Image(image: String) extends CardAttribute("Image", image)
case class CastingCost(cc: List[Mana]) extends CardAttribute("Mana Cost", cc)
case class Color(colors: List[ColorBase]) extends CardAttribute("Color", colors)
case class ConvertedManaCost(cmc: Int) extends CardAttribute("Converted Mana Cost", cmc)
case class CardType(cardType: String) extends CardAttribute("Card Types", cardType)
case class Types(types: String) extends CardAttribute("Types", types)
case class Rules(rules: Map[HookBase[_], String]) extends CardAttribute("Rules", Rules)
case class Power(power: Int) extends CardAttribute("Power", power)
case class Toughness(toughness: String) extends CardAttribute("Toughness", toughness)
case class Explansion(expansion: String) extends CardAttribute("Expansion", expansion)
case class Rarity(rarity: String) extends CardAttribute("Rarity", rarity)
case class CardNumber(cardNumber: String) extends CardAttribute("Card Number", cardNumber)
case class Artist(artist: String) extends CardAttribute("Artist", artist)

object AttributeFactory {
  def apply[K, V](name: K, value: V): CardAttribute[K, V] = {
    name match {
      case "Card Name" => Name(value.asInstanceOf[String]).asInstanceOf[CardAttribute[K, V]]
      case "Image" => Image(value.asInstanceOf[String]).asInstanceOf[CardAttribute[K, V]]
      case "Mana Cost" => CastingCost(value.asInstanceOf[List[Mana]]).asInstanceOf[CardAttribute[K, V]]
    }
  }
}

//case class MagicCard(
//                      name: String = "Goblin Sparkmaster",
//                      image: String = "./assets/default.png",
//                      castingCost: List[Mana] = List(OneColorless(), Blue(), Red()),
//                      color: List[ColorBase] = List(Blue(), Red()),
//                      convertedManaCost: Int = 3,
//                      cardTypes: List[String] = List("Creature", "Enchantment"),
//                      subTypes: List[String]= List("Goblin", "Wizard"),
//                      rules: Map[HookBase[_], String] = Map[HookBase[_], String](StartTurn() -> "Goblin Sparkmaster StartTurn"), //World => World],
//                      power: Int = 2,
//                      toughness: Int = 2,
//                      expansion: String = "Alara Reborn",
//                      rarity: String = "Uncommon",
//                      cardNumber: Int = 47,
//                      artist: String
//                      ) {
//  override def toString = s"MagicCard($name, $castingCost, $cardTypes - $subTypes, $rules, $power/$toughness)"
//}

case class MagicCard(
                      name: String = MagicCard.ErrorOccured,
                      image: String = "./assets/default.png",
                      castingCost: List[Mana] = List(OneColorless(), Blue(), Red()),
                      color: List[ColorBase] = List(Blue(), Red()),
                      convertedManaCost: Int = 3,
                      cardTypes: List[String] = List("Creature", "Enchantment"),
                      subTypes: List[String]= List("Goblin", "Wizard"),
                      rules: List[String],
                      flavorText: Option[String],
                      power: Option[Int] = Some(2),
                      toughness: Option[Int] = Some(2),
                      expansions: List[String] = List("Alara Reborn"),
                      rarity: String = "Uncommon",
                      cardNumber: Int = 47,
                      multiverseId: Int = 179597,
                      artist: String
                      ) {
  override def toString = s"MagicCard($name, $castingCost, $cardTypes - $subTypes, $rules, $power/$toughness)"
}

object MagicCard {
  val CardNameKey = "Card Name:"
  val CastingCostKey = "Mana Cost:"
  val ConvertedManaCostKey = "Converted Mana Cost:"
  val CardTypesKey = "Types:"
  val RulesTextKey = "Card Text:"
  val FlavorTextKey = "Flavor Text:"
  val ExpansionKey = "Expansion:"
  val RarityKey = "Rarity:"
  val CardNumberKey = "Card Number:"
  val ArtistKey = "Artist:"

  val ErrorOccured = "Error Card Field!!!"
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

