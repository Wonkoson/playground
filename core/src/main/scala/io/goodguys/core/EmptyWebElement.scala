package io.goodguys.tugoffline

import java.util

import org.openqa.selenium.{Dimension, Point, By, WebElement}

/**
 * Created by wonko on 2015-04-28.
 */
object EmptyWebElement extends WebElement {
  override def sendKeys(keysToSend: CharSequence*): Unit = ???

  override def findElements(by: By): util.List[WebElement] = ???

  override def getSize: Dimension = ???

  override def isEnabled: Boolean = ???

  override def getAttribute(name: String): String = ???

  override def clear(): Unit = ???

  override def getCssValue(propertyName: String): String = ???

  override def click(): Unit = ???

  override def getLocation: Point = ???

  override def getText: String = ???

  override def isDisplayed: Boolean = ???

  override def getTagName: String = ???

  override def submit(): Unit = ???

  override def isSelected: Boolean = ???

  override def findElement(by: By): WebElement = ???
}
