package compareJdk;

/**
 * Created by liuda on 2016/10/4.
 */
interface Car{
    public double getHeght();
    public double getWidth();
    /*  java8 �����ԣ� �ڽӿ�������Ĭ�Ϸ���������ʵ������и÷�����ʵ���಻����ȥ�̳������ࡣ
    default String getCompayName(){
        return "Foter";
    }


    static public String getCompany() {
        return "Foster";
    }
        */
}

class MiniCar implements Car {

    @Override
    public double getHeght() {
        return 0;
    }

    @Override
    public double getWidth() {
        return 0;
    }

}

public class Inteface_Default_Method {

}
