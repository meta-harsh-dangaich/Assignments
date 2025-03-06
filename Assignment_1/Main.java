import java.util.*;

class Item {
    private String itemId;
    private String name;
    private String description;// Camel case
    private double price;

    public Item(String itemId, String name, String des, double price) {
        this.itemId = itemId;
        this.name = name;
        this.description = des;
        this.price = price;
    }

    public double getPrice() {// Should be in pascal case
        return price;
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

}

// Class - ItemDetails
// ItemDetails - Quantity

class ShoppingCart {

    private HashMap<Item, Integer> cart = new HashMap<Item, Integer>();

    public boolean addToCart(Item item, int quantity) {
        if (quantity <= 0) {
            return false;
        }
        boolean exist_item = cart.containsKey(item);
        if (exist_item == true) {
            cart.put(item, cart.get(item) + quantity);
        } else {
            cart.put(item, quantity);
        }
        return true;

    }

    public int displayQty(Item item) {
        if (cart.containsKey(item) == true)
            return cart.get(item);
        else
            return 0;
    }

    public boolean updateQty(Item item, int quantity) {
        if (quantity < 0) {
            return false;
        }
        cart.put(item, quantity);
        return true;

    }

    public boolean deleteItem(Item item) {

        if (cart.remove(item) != null) {
            return true;
        }

        else {
            return false;
        }
    }

    // improve display also
    public double totalBill() {

        double total_bill = 0;

        for (Map.Entry<Item, Integer> i : cart.entrySet()) {
            int q = i.getValue();
            double p = i.getKey().getPrice();

            total_bill += p * q;
        }
        return total_bill;
    }

    public HashMap<Item,Integer>getCartItems(){
        return cart;
    }

    public boolean isCartEmpty(){
        return cart.isEmpty();
    }
}

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        Scanner sc = new Scanner(System.in);
        Item item1 = new Item("1", "Apple", "red colour", 10);
        Item item2 = new Item("2", "Banana", "yellow colour", 5);
        Item item3 = new Item("3", "Oranges", "orange colour", 6);

        HashMap<String, Item> items = new HashMap<>();
        items.put("1", item1);
        items.put("2", item2);
        items.put("3", item3);

        int choice;

        do {
            System.out.println("************** ------- *************");
            System.out.println("Shopping Cart");
            System.out.println("1. Add item to cart");
            System.out.println("2. Display Cart");
            System.out.println("3. Update item quantity");
            System.out.println("4. Delete item ");
            System.out.println("5. Total Bill");
            System.out.println("6.Exit");
            System.out.println("************** ------- *************");
            System.out.println("Enter Choice");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Available Items");
                    for(Item item:items.values()){
                        System.out.println(item.getItemId()+" "+item.getName()+" "+item.getPrice());
                    }
                    System.out.println("Enter Item Id");
                    String itemId = sc.nextLine();
                    if (items.containsKey(itemId) == false) {
                        System.out.println("Invalid id");
                        break;
                    }
                    System.out.println("Enter Qty");
                    int qty = sc.nextInt();
                    if(cart.addToCart(items.get(itemId), qty))
                    System.out.println(items.get(itemId).getName()+" added to cart ");
                    else{
                        System.out.println("Invalid ,must be greater than zero");
                    }
                    System.out.println("Press Any Number To Continue");
                    sc.nextInt();
                    break;

                case 2:
                    if(cart.isCartEmpty()){
                        System.out.println("Cart is Empty");
                    }
                    else{
                        System.out.println("Shopping Cart");
                        for(Map.Entry<Item,Integer> entry:cart.getCartItems().entrySet()){
                            System.out.println(entry.getKey().getItemId()+" - "+entry.getKey().getName()+" quantity : "+entry.getValue());
                        }
                        System.out.println("Total Bill: "+cart.totalBill());
                    }
                    System.out.println("Press Any Number To Continue");
                    sc.nextInt();
                    break;

                case 3:
                    System.out.println("Enter the itemid to update");
                    itemId = sc.nextLine();
                    if (items.containsKey(itemId) == false) {
                        System.out.println("Invalid id");
                        break;
                    }
                    System.out.println("Enter new Quantity: ");
                    qty = sc.nextInt();
                    if(cart.updateQty(items.get(itemId), qty)){
                        System.out.println("updated "+items.get(itemId).getName()+" to quantity: "+qty);
                    }
                    else{
                        System.out.println("Invalid quantity");
                    }
                    System.out.println("Press Any Number To Continue");
                    sc.nextInt();
                    break;

                case 4:
                    System.out.println("Enter itemid to remove");
                    itemId = sc.nextLine();
                    if (items.containsKey(itemId) == false) {
                        System.out.println("Invalid id");
                    }
                    if(cart.deleteItem(items.get(itemId))==true){
                        System.out.println("Item removed from cart. ");
                    }
                    System.out.println("Press Any Key To Continue");
                    sc.nextInt();
                    break;

                case 5:
                    System.out.println("Total Bill -" + cart.totalBill());
                    System.out.println("Press Any Number To Continue");
                    sc.nextInt();
                    break;

                case 6:
                    System.out.println("Exit");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }
        } while (choice != 6);
        sc.close();
    }
}

