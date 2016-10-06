package thinkInJava;

import static java.lang.System.out;

/**
 * Created by liuda on 2016/10/5.
 */
//�ڲ���
class OuterClass{
    public void f(){}
    public class InnerClass{
        private String key;
        private String value;
        //private static String other; �����ڲ��಻����static�ֶκͷ���
        public void h(){
            f();                   //�ڲ������۱�Ƕ�׶��ٲ㣬���ɷ����ⲿ�����г�Ա
        }
    }
}

//Ƕ����
class OuterClass2{
    public void f(){}
    public static void e(){}
    public static class NestClass{
        private String key;
        private String value;
        private static String other;  //��ȷ��Ƕ�������ӵ��static�ֶκͷ���
        public void h(){
           // f();                   //Ƕ����������ⲿ�࣬�޷������ⲿ�Ǿ�̬��Ա��
            e();
        }
    }
}

public class Chapter10 {
    public  static void main(String[] args){
        Callbacks.test();
    }
    public static void InnerClass_and_NestClass(){
        OuterClass outer  = new OuterClass();
        OuterClass.InnerClass  inner = outer.new InnerClass();       //�ڲ������Ĵ�����ʽ
        // OuterClass.InnerClass  inner2 = new OuterClass.InnerClass();

        OuterClass2 outer2 = new OuterClass2();
        OuterClass2.NestClass nest = new OuterClass2.NestClass();    //Ƕ�������Ĵ�����ʽ
        // OuterClass2.NestClass nest2 = outer2.new NestClass();
    }
}

//�հ���ص�
interface  Incrementable {
    void increment();
}

class MyIncrement{
    public void increment(){ out.println("Other operation");}
    static void f(MyIncrement mi){ mi.increment();}
}

class Callee2 extends MyIncrement {
    private  int i = 0;
    public void increment(){
        super.increment();
        i++;
        out.println(i);
    }
    private  class  Closure implements Incrementable {
        public  void  increment(){
            Callee2.this.increment();                  //ͨ��.this������Χ����,  ����
        }
    }
    Incrementable getCallbackReference(){              //�����ڲ���ClosureΪprivate�� ��������Χ�ഴ���ڲ������
        return new Closure();
    }
}

class Caller{
    private Incrementable callbackReference ;
    Caller(Incrementable cbn){callbackReference = cbn;} //Caller�Ĺ�������Ҫһ��Incremetable��������Ϊ������ Ȼ�����Ժ��ĳ��ʱ�̣�Caller�������ʹ�ô����ûص�Caller�ࡣ
    void go(){callbackReference.increment();}
}

class Callbacks{
    public static void test(){
        Callee2 c2 =  new Callee2();
        MyIncrement.f(c2);             //���������������෽�� -�ص���ʽһ
        Caller caller1 = new Caller(c2.getCallbackReference());
        caller1.go();
        caller1.go();

    }
}
/*
����Callee2�̳���MyIncrement,������increment������ʵ�֣�������Incremetable�ӿ�������increment()��ȫ����ء�
��Callee2�̳���MyIncrement,�Ͳ���Ϊ��Incrementable����;������increment()����������ֻ�ܺ����ڲ��������ʵ��Incrementable��
ע�� ����һ���ڲ���ʱ����û������Χ��Ľӿ���Ӷ�����Ҳû���޸���Χ��Ľӿڡ�
����չʾ �ˣ� interface���������ӿ���ӿڵ�ʵ����ȫ�����ġ�
�ص���ֵ�� ����������ʱ��̬�ؾ��� ��Ҫ����ʲô������
 */
