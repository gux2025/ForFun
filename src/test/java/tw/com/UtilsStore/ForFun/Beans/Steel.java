package tw.com.UtilsStore.ForFun.Beans;

public abstract class Steel {

    static {
        System.out.println("steel類別 1.此為class載入時的static區塊");
    }

    {
        System.out.println("steel類別 2.這是物件創建時的區塊");
    }

    protected String hardness = "good steel class";

    public Steel(){
        System.out.println("Steel '無' 參數建構式");
    }

    public Steel(int i){
        System.out.println("Steel '有' 參數建構式");
    }


    public abstract void full();

}
