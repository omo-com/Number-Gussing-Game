/*
 * Michael & Oliver
 * 12/01/17
 * Item class
 * Allows account to recieve items
 */
public class Item{
  private String itemName;//Creates item name
  private int itemNum;//Creates item purpose index
  public Item(String nam, int num){
    //Constructs item from name, and index
    itemName=nam;
    itemNum=num;
  }
  public boolean setItemName(String iName){
    //Sets item name
    itemName=iName;
    return true;
  }
  public boolean setItemNum(int iNum){
    //Sets index for item
    itemNum=iNum;
    return true;
  }
  public String getItemName(){
    //Returns item name
    return itemName;
  }
  public int getItemNum(){
    //Returns item index
    return itemNum;
  }
  public String toString(){
    //Returns attributes
    return getItemName()+" "+getItemNum();
  }
}