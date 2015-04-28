package wonko.macros

import wonko.tugoffline._

import scala.language.experimental.macros
import scala.reflect.macros.blackbox.Context

/**
 * Created by wonko on 2015-04-26.
 */
object Macros {

  object Color {
    def ColorWhite(c: Context): c.Expr[Mana] = c.Expr[Mana](c.universe.reify(Mana(White())).tree)
    def ColorBlue(c: Context): c.Expr[Mana] = c.Expr[Mana](c.universe.reify(Mana(Blue())).tree)
    def ColorBlack(c: Context): c.Expr[Mana] = c.Expr[Mana](c.universe.reify(Mana(Black())).tree)
    def ColorRed(c: Context): c.Expr[Mana] = c.Expr[Mana](c.universe.reify(Mana(Red())).tree)
    def ColorGreen(c: Context): c.Expr[Mana] = c.Expr[Mana](c.universe.reify(Mana(Green())).tree)
    def ColorColorless(c: Context): c.Expr[Mana] = c.Expr[Mana](c.universe.reify(Mana(Colorless())).tree)
    def ColorNothing(c: Context): c.Expr[Mana] = c.Expr[Mana](c.universe.reify(Mana(Nothing())).tree)
  }
}