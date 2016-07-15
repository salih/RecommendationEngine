/**
  * Created by sgedik on 7/14/16.
  */
class Item() {
  private var name = "Empty"
  private var category ="Empty"

    def this(names:String,category:String)={
      this()
      this.name = name
      this.category = category

    }

    def getName(): String = {
      return this.name

    }

    def getCategory(): String = {
      return this.category
    }





}
