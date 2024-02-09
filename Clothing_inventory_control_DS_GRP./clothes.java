package Clothing_inventory_control;

import java.util.Scanner;

class Clothes_Manage {
    Scanner sc = new Scanner(System.in);
    Promocode code;

    public Clothes_Manage(Promocode code) {
        this.code = code;
    }

    class ClothingItem {
        int id;
        String name;
        String size;
        String color;
        String pattern;
        double price;
        int quantity;
        ClothingItem next;

        public ClothingItem(int id, String name, String size, String color, String pattern, double price,
                int quantity) {
            this.id = id;
            this.name = name;
            this.size = size;
            this.color = color;
            this.pattern = pattern;
            this.price = price;
            this.quantity = quantity;
            next = null;
        }
    }

    ClothingItem head = null;

    void addItemLast(ClothingItem clothingItem) {
        if (head == null) {
            head = clothingItem;
        } else {
            ClothingItem current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = clothingItem;
        }
    }

    void displayInventory() {
        ClothingItem current = head;
        while (current != null) {
            displayItemDetails(current);
            current = current.next;
        }
    }

    private void displayItemDetails(ClothingItem item) {
        System.out.println("ID: " + item.id + " | Name: " + item.name + " | Size: " + item.size +
                " | Color: " + item.color + " | Price: $" + item.price + " | Quantity: " + item.quantity);
    }

    void sortByPrice() {
        if (head == null || head.next == null) {
            return;
        }

        boolean swapped;
        do {
            swapped = false;
            ClothingItem current = head;
            ClothingItem previous = null;
            while (current.next != null) {
                ClothingItem nextNode = current.next;
                if (current.price > nextNode.price) {
                    if (previous == null) {
                        head = nextNode;
                    } else {
                        previous.next = nextNode;
                    }
                    current.next = nextNode.next;
                    nextNode.next = current;
                    swapped = true;
                }
                previous = current;
                current = nextNode;
            }
        } while (swapped);
    }

    void searchAndFilter(String keyword) {
        ClothingItem current = head;
        while (current != null) {
            if (current.name.equalsIgnoreCase(keyword) || current.size.equalsIgnoreCase(keyword)
                    || current.color.equalsIgnoreCase(keyword) || current.pattern.equalsIgnoreCase(keyword)) {
                displayItemDetails(current);
            }
            current = current.next;
        }
    }

    void makePurchase() {
        boolean continueShopping = true;
        Purchase P = new Purchase();
        while (continueShopping) {
            boolean found = false;
            System.out.println("Enter item id you want to purchase");
            int item_id = sc.nextInt();
            System.out.println("Enter quantity");
            int quantity = sc.nextInt();
            sc.nextLine();
            if (quantity < 1) {
                System.out.println("Enter valid quantity");
                return;
            }
            ClothingItem current = head;
            while (current != null) {
                if (current.id == item_id && current.quantity >= quantity) {
                    Purchase.Purchase_Item temp = P.first;
                    boolean foundInPurchase = false;
                    if (temp != null) {
                        while (temp != null) {
                            if (temp.id == item_id) {
                                temp.quantity = temp.quantity + quantity;
                                System.out.println("ffbbhfght");
                                foundInPurchase = true;
                                break;
                            }
                            temp = temp.next;
                        }
                    }
                    if (!foundInPurchase) {
                        P.addPurchaseItemLast(
                                P.new Purchase_Item(current.id, current.name, current.size, current.color,
                                        current.pattern, current.price, quantity));
                    }
                    current.quantity = current.quantity - quantity;
                    P.display();
                    found = true;
                    break;
                }
                current = current.next;
            }
            if (!found) {
                System.out.println("Item not found or quantity not available.");
            }
            System.out.println("Do you want to buy more things (y/n)");

            String buymore = sc.nextLine();
            if (buymore.equalsIgnoreCase("n")) {
                continueShopping = false;
                if (Purchase.first == null) {
                    System.out.println("Thank you visit again");
                } else if (Purchase.first != null) {
                    System.out.println("Do you want to remove anything (y/n)");
                    String r = sc.nextLine();
                    if (r.equalsIgnoreCase("y")) {
                        System.out.println("Enter item id ");
                        int id = sc.nextInt();
                        System.out.println("Enter quantity");
                        int r_quantity = sc.nextInt();
                        sc.nextLine();
                        P.setQuantity(id, r_quantity);
                    }
                    generateBill();

                }
            } else if (buymore.equalsIgnoreCase("y")) {
                continueShopping = true;
            } else {
                System.out.println("Enter valid input");
                continueShopping = false;
            }
        }

    }

    private void generateBill() {
        double disc = 0;
        Purchase.Purchase_Item temp = Purchase.first;
        System.out.println("Do you have any coupon-code (y/n)");
        String a = sc.nextLine();
        if (a.equalsIgnoreCase("y")) {
            System.out.println("Enter your code");
            String input = sc.nextLine();
            disc = code.get(input);
        }
        double totalCost = 0;
        System.out.println("Your Purchased ");
        while (temp != null) {
            System.out.println("ID: " + temp.id + " | Name: " + temp.name + " | Size: " + temp.size +
                    " | Color: " + temp.color + " | Price: $" + temp.price + " | Quantity: " + temp.quantity);
            totalCost = totalCost + (temp.price * temp.quantity);
            temp = temp.next;
        }
        System.out.println("Total amount will be (without code) $: " + totalCost);
        totalCost = totalCost - (totalCost * (disc / 100));
        System.out.println("Total amount after applying code $:" + totalCost);
    }

}
