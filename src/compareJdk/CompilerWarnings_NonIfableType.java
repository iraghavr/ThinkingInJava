package compareJdk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuda on 2016/10/4.
 */
public class CompilerWarnings_NonIfableType {
    public static void main(String[] args){
        List l = new ArrayList<Number>();
        List<String> ls = l;       // unchecked warning
        l.add(0, new Integer(42)); // another unchecked warning
        String s = ls.get(0);      // ClassCastException is thrown
        //�������Ͳ�������������ڱ����ھ����ᱨ����ΪList<Number>, List<String>����̽��ΪList����
        /*
        String s = ls.get(0);  ������ʱ�ᱨ����Ϊ������Ϣ�ڱ�����ǰ���Ѿ�������Ӧ�Ķ������ļ���
         */

    }

}
