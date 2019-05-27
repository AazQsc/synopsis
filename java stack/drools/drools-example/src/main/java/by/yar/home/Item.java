package by.yar.home;

// POJO
public class Item {
    private int cost;
    private Priority itemPrioryty;
    private Object ob;

    public Item(int cost) {
        this.cost = cost;
        this.itemPrioryty = Priority.NO_PRIORITY;
    }

    public Object getOb() {
        return ob;
    }

    public void setOb(Object ob) {
        this.ob = ob;
    }

    public Priority getItemPrioryty() {
        return itemPrioryty;
    }

    public void setItemPrioryty(Priority itemPrioryty) {
        this.itemPrioryty = itemPrioryty;
    }

    /*
     * Правило получит значение поля через геттер.
     * Метод геттера обязателен.
     */
    public int getCost() {
        return cost;
    }
}
