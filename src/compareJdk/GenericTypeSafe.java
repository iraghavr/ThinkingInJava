package compareJdk;

import java.util.*;
import static java.lang.System.out;
/**
 * Created by liuda on 2016/10/2.
 */
/*
 ����
   ����ͨ�����Ͳ���ʵ�֡�������Ϣֻ�ڱ������У�֮�󱻲�����
   �ŵ㣺 ������ԭ�����Ϳɹ�ͬʹ�ã�
   ȱ�㣺�����ڲ��ܻ��������Ϣ���޷���֤���Ͱ�ȫ�� ʾ��test1();
   ��������� test2
   java.util.Conllections��ͨ����װ�࣬�����������������Ͱ�ȫ����debuggingʱ��Щ���ܼ��ļ��ϰ�װ�����ǳ����á����磬
һ���ַ������ϣ���������һ��integer���͡�
   ���Ҫ��֤���Ͱ�ȫ���Խ���
     Set<String> s = new HashSet<String>();
   �滻Ϊ
     Set<String> s = Collections.checkSet(new HashSet<String>(),String.class);
   ����ͼ����һ��integer����ʱ���ü��ϻ��׳�һ��ClassCastException
 */
public class GenericTypeSafe {


        public static void main(String args[]){
            Set<String> ss = new HashSet<String>();
            ss.add("am");
            //ss.add(22);                    //������Ϊ���ͼ����ڡ�  ss�Ǿ������͡�
            test1(ss);
            Set<String> sx = Collections.checkedSet(new HashSet<String>(),String.class);
            sx.add("bm");
            test2(sx);

            Set sy = new HashSet<String>();
            sy.add("bm");
            sy.add(22);                       //��������Ϊsy����ʱ�����������ͱ�������sy�Ƿ������͡�
        }
        public static void test1(Set ss){
            ss.add(11);                    //��������Ϊ����ʱ��������Ϣ��������ss�Ƿ������͡�
            for(Object ob : ss){
                out.println(ob);
            }
        }
        public static void test2(Set sx){
            sx.add(11);
            for(Object ob : sx){
                out.println(ob);
            }
        }


}
