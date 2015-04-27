package io.goodguys.macros

import scala.language.experimental.macros
import scala.reflect.macros.blackbox
import io.goodguys.tugoffline.{Mana, White, Blue, Black, Red, Green, Colorless, Nothing}

/**
 * Created by wonko on 2015-04-26.
 */
object Macros {

  object Impl {
    def ColorWhite(c: blackbox.Context): c.Expr[Mana] = c.Expr[Mana](c.universe.reify(Mana(White())).tree)
    def ColorBlue(c: blackbox.Context): c.Expr[Mana] = c.Expr[Mana](c.universe.reify(Mana(Blue())).tree)
    def ColorBlack(c: blackbox.Context): c.Expr[Mana] = c.Expr[Mana](c.universe.reify(Mana(Black())).tree)
    def ColorRed(c: blackbox.Context): c.Expr[Mana] = c.Expr[Mana](c.universe.reify(Mana(Red())).tree)
    def ColorGreen(c: blackbox.Context): c.Expr[Mana] = c.Expr[Mana](c.universe.reify(Mana(Green())).tree)
    def ColorColorless(c: blackbox.Context): c.Expr[Mana] = c.Expr[Mana](c.universe.reify(Mana(Colorless())).tree)
    def ColorNothing(c: blackbox.Context): c.Expr[Mana] = c.Expr[Mana](c.universe.reify(Mana(Nothing())).tree)
  }
}