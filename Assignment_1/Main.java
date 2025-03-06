import java.util.*;


class Item{
private String ItemId;
private String Name;
private String Description;
private double Price;

public Item(String itemid,String name,String des,double price){

this.ItemId=itemid;
this.Name=name;
this.Description=des;
this.Price=price;

}

public double getPrice(){
return Price;
}

public String getitemid(){
    return ItemId;
}

public String getName(){
    return Name;
}

}

class ShoppingCart{

private HashMap<Item,Integer>cart=new HashMap<Item,Integer>();


public void addToCart(Item item,int quantity){
if(quantity<=0){
    System.out.println("Enter quantity greater than 0");
    return;
}
boolean exist_item=cart.containsKey(item);
if (exist_item == true){
cart.put(item,cart.get(item)+quantity);
}
else{
cart.put(item,quantity);
}
System.out.println(item.getName()+" "+"added to cart");

}

public int displayQty(Item item){
if(cart.containsKey(item)==true)
return cart.get(item);
else
return 0;
}

public void updateQty(Item item,int quantity){
if(quantity<0){
    System.out.println("Quantity should be positive");
    return;
}
if(cart.containsKey(item)==false){
    System.out.println("item not present in cart");
    return;
}
int prevQty=cart.get(item);
cart.put(item,prevQty+quantity);
int updatedQty=prevQty+quantity;
System.out.println(" updated Item"+item.getitemid()+" to "+ updatedQty );

}

public void deleteItem(Item item){

if(cart.remove(item)!=null){
System.out.println("item deleted");
}

else{
System.out.println("item not present in cart");
}
}

public double displayBill(){

double total_bill=0;

for (Map.Entry<Item,Integer> i : cart.entrySet()){
int q=i.getValue();
double p=i.getKey().getPrice();

total_bill+=p*q;
}
return total_bill;
}

public void displayCart(){

if(cart.isEmpty()==true){
System.out.println("Cart is Empty");
}

else{
System.out.println("Shopping Cart");
for (HashMap.Entry<Item,Integer> i : cart.entrySet()){
System.out.println(i.getKey().getitemid()+"- Quantity "+i.getValue());
}
System.out.println("Total bill"+" - "+displayBill());
}
}
}

public class Main{
public static void main(String[] args){
ShoppingCart cart=new ShoppingCart();
Item item1=new Item("1","Apple","red colour",10);
Item item2=new Item("2","Banana","yellow colour",5);
Item item3=new Item("3","Oranges","orange colour",6);

Scanner sc=new Scanner(System.in);

HashMap<String,Item>items=new HashMap<>();
items.put("1",item1);
items.put("2",item2);
items.put("3",item3);

int choice;

do{
    System.out.println("Shopping Cart");
    System.out.println("1. Add item to cart");
    System.out.println("2. Display Cart");
    System.out.println("3. Update item quantity");
    System.out.println("4. Delete item ");
    System.out.println("5. Total Bill");
    System.out.println("6.Exit");
    System.out.println("Enter Choice");
    choice =sc.nextInt();
    sc.nextLine();

    switch(choice){
        case 1:
            System.out.println("Enter Item Id");
            String itemId=sc.nextLine();
            if(items.containsKey(itemId)==false){
                System.out.println("Invalid id");
                break;
            }
            
            System.out.println("Enter Qty");
            int qty=sc.nextInt();
            cart.addToCart(items.get(itemId), qty);
            break;
        

        case 2:
            cart.displayCart();
            break;

        case 3:
            System.out.println("Enter the itemid to update");
            itemId=sc.nextLine();
            if(items.containsKey(itemId)==false){
                System.out.println("Invalid id");
                break;
            }
            System.out.println("Enter new Quantity: ");
            qty=sc.nextInt();
            cart.updateQty(items.get(itemId), qty);
            break;

        case 4:
            System.out.println("Enter itemid to remove");
            itemId=sc.nextLine();
            if(items.containsKey(itemId)==false){
                System.out.println("Invalid id");
                break;
            }
            cart.deleteItem(items.get(itemId));
            break;

        case 5:
            System.out.println("Total Bill -" + cart.displayBill());
            break;
        
        case 6:
            System.out.println("Exit");
            break;
        
        default:
            System.out.println("Invalid Choice!");
        }
    }while(choice!=6);
        sc.close();
    }
}


/*cart.addToCart(item1,3);
cart.displayCart();
cart.addToCart(item2,4);
System.out.println("Quantity of banana:"+cart.displayQty(item2));
cart.updateQty(item1,2);
cart.displayCart();
cart.deleteItem(item2);
cart.displayCart();
cart.addToCart(item3,2);

cart.displayCart();

System.out.println("Total bill"+" "+cart.displayBill());*/





