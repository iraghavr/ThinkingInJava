package thinkInJava;

/**
 * Created by liuda on 2016/10/5.
 */
class Person{
        public String name;
        }

public class Chapter7 {
    public static void main(String[] args){
        Girl girl = new Girl();
        System.out.println(girl.getFirstName());
    }
    public static void finalParam(final Person person1, Person person2){
        person1.name = "fdf ";
       // person1 = new Person();
        person2 = new Person();
    }
}

/*
�̳����ʼ����
   1.�����˽�����ԣ������޷����ʵ�����������̳��˻������е����ԣ��ͷ����� ��debug���Կ�֪��
   2.�������������ʱ����û�����ɻ�����󣬶��ǵ��û���Ĺ��췽�����̳еõ���
   3 ��ʼ��˳�� ���ྲ̬�򡢵����ྲ̬�� ����ʵ�����������๹�췽����������ʵ�������������๹�췽����
  4 һ����������private�ģ�ֻ�л���Ĺ������ž���ǡ����֪ʶ��Ȩ�������Լ���Ԫ�ؽ��г�ʼ������˱��������й��������õ����á�
 */
class Father{
    private String first_name;
    public Father(){
        this.first_name = "Liu";
    }
    public String getFirstName(){
        return first_name;
    }

}

class Girl extends Father {

}

