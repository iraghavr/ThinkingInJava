package thinkInJava;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuda on 2016/10/6.
 */
public class Chapter15_ClassTypeCapture {
    public static void main(String[] args){
        ClassTypeCapture.test();
        NonCovariantGenerics.test2();
    }
}

//�����Ĳ���
class ClassTypeCapture<T>{
    Class<T> kind;
    public ClassTypeCapture(Class<T> kind){
        this.kind = kind;
    }
    public boolean f(Object arg){
        return kind.isInstance(arg);
    }
    public static void test(){
        ClassTypeCapture<Cat> ctt1 = new ClassTypeCapture<Cat>(Cat.class);
        System.out.println(ctt1.f(new Cat()));
        System.out.println(ctt1.f(new Dog()));
    }
}

//ͨ���
class Fruit{
}

class Apple extends Fruit {

}

class NonCovariantGenerics{
   // List<Fruit> flist = new ArrayList<Apple>();
   // ��������������������ת�͡� apple��List����Fruit��List�� Apple��List�����κ�Apple��Apple�������ͣ���Fruit��List�����κ����͵�Fruit,f�����������ڣ����������ͣ��������������е�����

    //��������� ͨ�������������֮�佨��ĳ�����͵�����ת�͹�ϵ
    List<? extends Fruit> flist = new ArrayList<Apple>();
    /*���ڣ�flist��������List<? extends Fruit> ���������κδ�Fruit�̳��������͵��б����Ⲣ����ζ�����List�������κ����͵�Fruit��
       ͨ������õ�����ȷ�����ͣ����mean"ĳ��flist����û��ָ���ľ������͡�
     */
    public void test(){
        //flist.add(new Apple());//�޷������κ����͵Ķ���
        flist.add(null);
        Fruit f = flist.get(0);
    }
    /*<? extend Fruit> ���������ͣ�����get,������set����Ϊ���������κ�������������޷���֤�κ�����İ�ȫ��
    ˼�� ��public ArrayList(Collection<? extends E> c) Ϊ���������
    */


    //�����ͱ߽�����˿����򷽷����ݵĲ���������������
    List<? super Fruit> flist2  = new ArrayList<Fruit>();

    static List<Apple> apples = new ArrayList<Apple>();
    static List<Fruit> fruit = new ArrayList<Fruit>();
    static <T> void writeWithWildCard(List<? super T> list,T item){
        list.add(item);

    }
    static <T> void writeWithWildCard2(List<T> list,T item){
        list.add(item);

    }
     static <T> T readExact(List<T> list){
         return list.get(0);
     }
    public static void test2(){
        writeWithWildCard(apples,new Apple());
        writeWithWildCard(fruit, new Apple());

        writeWithWildCard2(apples, new Apple());
        writeWithWildCard2(fruit, new Apple());

        //apples.add(new Fruit());
        Fruit fruit = readExact(apples);
    }
/*
List<T> ��ͨ������о�ȷ���ͣ� ����д��Ͷ�ȡ�������
List<? extend T>:��ͨ����� ֻ�ܶ�ȡ������д�롣
 */
    /*
    ʹ��<? extend T>��һ��������
    List<Apple>���� ���� ����Fruit����
     */
}

class Holder<T>{
    T item;
    public void set(T item ){this.item = item;}
    public T get(){return this.item;}
}

//�޽�ͨ���
class Wildcards{
    static void rawArgs(Holder holder, Object arg){
        holder.set(arg); //��������ԭ�����ͣ�������֪����set()����һ��Object�ǲ���ȫ�ģ�t���Խ��κ����͵Ķ��󴫵ݸ�set,�������������ת��ΪObjec
        Object obj =holder.get();
    }
    static void rawArg2s(Holder<?> holder, Object arg){
        // holder.set(arg);  ���� Holder<?>������ĳ�־������͵�ͬ�����ϣ���˲���ֻ�������д���Object
        Object obj =holder.get();

    }
    static <T> T exact(Holder<T> holder){
        T t = holder.get();
        return t;
    }
    static <T> T exact2(Holder<T> holder, T arg){
        holder.set(arg);
        T t = holder.get();
        return t;
    }
    /*
     //���ɣ�������չ��T�����Holder, mean: ��TΪFruit, holder������Holder<Apple>
     Ϊ�˷�ֹ��Orange���õ�Holder<Apple>�У���set�����ĵ����ǲ�����ġ�  ������֪��������Holder<? extend Fruit>�Ķ���������Fruit�����get�������
     */
    static <T> T wildSubtype(Holder<? extends  T> holder, T arg){
       // holder.set(arg); error    set(capture of ? extends T ) cann't be applied to (T)
        return holder.get();

    }
    //��ǰ���෴����Ϊ
    /*
    holder�����ǳ����κ�T�Ļ����͵���������ˣ�set()���Խ���T,��Ϊ�κοɹ����ڻ���Ķ��󶼿��Զ�̬�������ڵ����࣬����
    ����get()��û���õģ������Holder���еľ�Ӣ�������κγ����ͣ� ���Ψһ��ȫ�����;���Object
     */
    static <T> void wildSupertype(Holder<? super T> holder, T arg){
        holder.set(arg); //error
      // T t = holder.get(); ���Ͳ�����, ���ص���Object���ͣ���t��T����

    }
}

//���� Э��
class Basea{
}

class Drived extends Basea {

}

interface  OrdinaryGetter{
    Basea get();
}

interface DerivedGetter extends OrdinaryGetter {
    Drived get();
}

/*
OrdinaryGetter�ĵ�����DerviedGetter�����Ƿ���get(),����������Baseda�ĵ�����
 */
// ���޶�����: ������ȷ�еĵ�������Ϊ�䷴��ֵ
interface  GenericGetter<T extends GenericGetter<T>>{
    T get();
}

interface  Getter extends GenericGetter<Getter> {

}

class GenericsAndReturnTypes{
    void test(Getter g, DerivedGetter d){
        Getter result =  g.get();
        GenericGetter gg = g.get();
        Drived drived  = d.get();
    }
}