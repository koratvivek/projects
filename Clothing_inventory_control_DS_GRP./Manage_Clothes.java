package Clothing_inventory_control;

import java.util.*;

class Manage_Clothing_System {
    static Promocode code = new Promocode();
    static Clothes_Manage T_shirt = new Clothes_Manage(code);
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        T_shirt.addItemLast(T_shirt.new ClothingItem(7, "V-neck Shirt", "S", "White", "Striped", 24.99, 3));
        T_shirt.addItemLast(T_shirt.new ClothingItem(4, "Slim Fit T-shirt", "XL", "Gray", "Solid", 22.50, 10));
        T_shirt.addItemLast(T_shirt.new ClothingItem(6, "Long Sleeve T-shirt", "M", "Green", "Solid", 21.99, 4));
        T_shirt.addItemLast(T_shirt.new ClothingItem(10, "Oversized T-shirt", "XL", "White", "Solid", 18.75, 9));
        T_shirt.addItemLast(T_shirt.new ClothingItem(1, "Straight Fit Jeans", "32", "Blue", "Solid", 59.99, 5));
        T_shirt.addItemLast(T_shirt.new ClothingItem(2, "Skinny Jeans", "28", "Black", "Solid", 49.99, 3));
        T_shirt.addItemLast(T_shirt.new ClothingItem(3, "Slim Fit Jeans", "30", "Gray", "Washed", 54.99, 7));
        code.push(new Promocode("CODE1", 10));
        code.push(new Promocode("CODE2", 15));
        code.push(new Promocode("CODE3", 20));
        code.push(new Promocode("CODE4", 25));
        code.push(new Promocode("CODE5", 50));
        code.push(new Promocode("CODE6", 30));
        code.push(new Promocode("CODE7", 12));
        code.push(new Promocode("CODE8", 18));
        code.push(new Promocode("CODE9", 8));
        code.push(new Promocode("CODE10", 22));
        int choice;
        do {
            System.out.println("\n1. Add Item");
            System.out.println("2. View Inventory");
            System.out.println("3. Search and Filter");
            System.out.println("4. Sort by Price");
            System.out.println("5. MakePurchase");
            System.out.println("6 - Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    addItem();
                    break;
                case 2:
                    T_shirt.displayInventory();
                    break;
                case 3:
                    System.out.print("Enter keyword to search: ");
                    String keyword = sc.nextLine();
                    T_shirt.searchAndFilter(keyword);
                    break;
                case 4:
                    T_shirt.sortByPrice();
                    T_shirt.displayInventory();
                    break;
                case 5:
                    T_shirt.makePurchase();
                    break;
                case 6:
                    System.out.println("Thank you");
                    break;
                default:
                    System.out.println("Enter valid choice");
            }
        } while (choice != 6);
    }

    static void addItem() {
        System.out.println("Enter your product id");
        int id = sc.nextInt();
        Clothes_Manage.ClothingItem temp = T_shirt.head;
        while (temp != null) {
            if (temp.id == id) {
                System.out.println("This product id is already exists");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Enter product name");
        String name = sc.nextLine();
        sc.nextLine();
        System.out.println("Enter size");
        String size = sc.nextLine();
        System.out.println("Enter color");
        String color = sc.nextLine();
        System.out.println("Enter pattern");
        String pattern = sc.nextLine();
        System.out.println("Enter price");
        double price = sc.nextDouble();
        System.out.println("Enter quantity");
        int quantity = sc.nextInt();
        T_shirt.addItemLast(T_shirt.new ClothingItem(id, name, size, color, pattern, price, quantity));
    }
}
