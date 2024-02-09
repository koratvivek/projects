package Clothing_inventory_control;

class Promocode {
    Promocode code[];
    int size = 10;
    static int top;
    String promocode;
    double discount;

    Promocode() {
        code = new Promocode[size];
        top = -1;
    }

    public Promocode(String promocode, double discount) {
        this.promocode = promocode;
        this.discount = discount;
    }

    void push(Promocode data) {
        if (top == size - 1) {
            System.out.println("Over-flow");
        } else {
            top++;
            code[top] = data;
        }
    }

    private void pop(int index) {
        if (index >= size || index < 0) {
            System.out.println("Enter valid index");
            return;
        }
        code[index] = null;
    }

    double get(String x) {
        if (top == -1) {
            System.out.println("Under flow");
            return 0;
        } else if (top != -1) {
            for (int i = top; i >= 0; i--) {
                if (code[i].promocode.equals(x) && x != null) {
                    System.out.println("Congratulation you get flat " + code[i].discount + "% " + "discount");
                    return code[i].discount;
                }
            }
        }
        System.out.println("Enter valid promo code");
        return 0;

    }

}
