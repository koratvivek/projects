package Clothing_inventory_control;

class Purchase {
    static Purchase_Item first = null;

    class Purchase_Item {
        int id;
        String name;
        String size;
        String color;
        String pattern;
        double price;
        int quantity;
        Purchase_Item next;

        public Purchase_Item(int id, String name, String size, String color, String pattern, double price,
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

    void addPurchaseItemLast(Purchase_Item item) {

        if (first == null) {
            first = item;
        } else {
            Purchase_Item current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.next = item;
        }
    }

    void display() {
        Purchase_Item temp = first;
        System.out.println("Item added to Cart : ");
        while (temp != null) {
            System.out.println("ID: " + temp.id + " | Name: " + temp.name + " | Size: " + temp.size +
                    " | Color: " + temp.color + " | Price: $" + temp.price + " | Quantity: " + temp.quantity);
            temp = temp.next;
        }
    }

    void removeItem(int item) {
        boolean found = false;
        if (first.id == item && first.next == null) {
            first = null;
            return;
        }
        if (first.id == item && first.next != null) {
            first = first.next;
            return;
        }
        Purchase_Item temp = first;
        while (temp.next != null) {
            if (temp.next.id == item) {
                Purchase_Item temp2 = temp.next;
                temp.next = temp2.next;
                found = true;
                break;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("Item not found");
        }
    }

    void setQuantity(int id, int setQuantity) {
        Purchase_Item temp = first;
        boolean flag = false;
        while (temp != null) {
            if (temp.id == id && temp.quantity >= setQuantity) {
                temp.quantity = temp.quantity - setQuantity;
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (!flag) {
            System.out.println("Invalid item id or quantity");
        } else {

            if (temp.quantity == 0) {
                removeItem(id);

            }
        }

    }
}
