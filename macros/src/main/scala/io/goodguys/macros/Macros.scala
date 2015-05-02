package io.goodguys.macros

import scala.language.experimental.macros
import scala.reflect.macros.blackbox.Context

/**
 * Created by wonko on 2015-04-26.
 */
object Macros {

  object ManaImpl {
    def ManaWhite(c: Context): c.Expr[Mana] = c.Expr[Mana](c.universe.reify(Mana(White())).tree)
    def ManaBlue(c: Context): c.Expr[Mana] = c.Expr[Mana](c.universe.reify(Mana(Blue())).tree)
    def ManaBlack(c: Context): c.Expr[Mana] = c.Expr[Mana](c.universe.reify(Mana(Black())).tree)
    def ManaRed(c: Context): c.Expr[Mana] = c.Expr[Mana](c.universe.reify(Mana(Red())).tree)
    def ManaGreen(c: Context): c.Expr[Mana] = c.Expr[Mana](c.universe.reify(Mana(Green())).tree)
    def ManaColorless(c: Context): c.Expr[Mana] = c.Expr[Mana](c.universe.reify(Mana(new Colorless())).tree)
    def ManaNothing(c: Context): c.Expr[Mana] = c.Expr[Mana](c.universe.reify(Mana(Nothing())).tree)
  }

  def W: Mana = macro ManaImpl.ManaWhite
  def U: Mana = macro ManaImpl.ManaBlue
  def B: Mana = macro ManaImpl.ManaBlack
  def R: Mana = macro ManaImpl.ManaRed
  def G: Mana = macro ManaImpl.ManaGreen
  def C: Mana = macro ManaImpl.ManaColorless
  def N: Mana = macro ManaImpl.ManaNothing
}