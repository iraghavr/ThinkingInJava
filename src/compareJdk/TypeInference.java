package compareJdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liuda on 2016/10/4.
 */
public class TypeInference {
    public static void main(String[] args){
       // List<String> stringList = new ArrayList<>();
        //stringList.add("A");
        //stringList.addAll(Arrays.asList()); jdk6,jdk7����ԭ��stringListΪ�������ͣ�Arrays.asList()���ͱ�����. jdk8֧�֣�ԭ�������ƶ���ǿ
       // stringList.addAll(Arrays.<String>.asList()); jdk7��ʹ�÷�ʽ

    }
}
