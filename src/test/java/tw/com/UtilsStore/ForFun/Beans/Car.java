package tw.com.UtilsStore.ForFun.Beans;

public class Car extends Steel {
    private String color;
    private int size;
    private static int years = 0;

    static {
        System.out.println("1.此為class載入時的static區塊");
    }

    {
        System.out.println("2.這是物件創建時的區塊");
    }

    public Car(){
        System.out.println("3.非原生Default建構式");
    }

    public Car(String color, int size){
        this();
        System.out.println("4.指定物件創建時的建構式");
        this.color = color;
        this.size = size;
        full();
    }

    public Car(int i){
//        super(i);
        System.out.println("test繼承建構式");
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public static void addYears(int y){
        years += y;
    }

    @Override
    public void full() {
        System.out.println(this.hardness);
    }
}
