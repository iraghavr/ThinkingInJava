package thinkInJava;

import static java.lang.System.out;

/**
 * Created by liuda on 2016/10/5.
 */

//  ��Ҫ���ɶ��ⲿ���������ã�.this
class DotThis{
    DotThis(){}
    public void f(){out.println("DotThis.f()");}
    public class Inner{
        Inner(){}
        public DotThis outer(){
            return DotThis.this;
        }
    }
    public Inner inner(){return new Inner();}
}

//��֪ĳЩ����ȥ������ĳ���ڲ���Ķ���,���� ��new��̬ʽ���ṩ�����ⲿ������ã�.new
public class Chapter9 {
    public static void main(String[] args){
        anonymouseClass();

    }
    public static void innerClass(){
        DotThis dt = new DotThis();
        DotThis.Inner dti = dt.inner();
        DotThis.Inner dti2 = dt.new Inner();
        dti.outer().f();
        dti2.outer().f();
    }
    public static void anonymouseClass(){
        Base base = AnonymousConstructor.getBase(47);
        base.f();
    }
}

abstract  class Base{
    public Base(int i){
        out.println("Base contructor,i = " + i);
    }
    public abstract void f();
}

class AnonymousConstructor{
    public static Base getBase(int i){
        return new Base(i){
            {out.println("Inside instance initizlizer");} //��������ע�������಻������������������Ϊ����û������
            public  void f(){
                out.println("In anonymous f()");
            }
        };
    }
}