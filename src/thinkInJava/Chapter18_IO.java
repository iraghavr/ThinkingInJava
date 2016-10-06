package thinkInJava;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by liuda on 2016/10/6.
 */
public class Chapter18_IO {
    public static String regulaExpression= "D:\\JD\\*.xml";
    public static void main(String[] args){
        test_directoryListUtil();
    }
    //Ŀ¼�б���
    public static void test_directoryListUtil(){
        File path = new File(".");
        String[] list;
        list = path.list(new DirFilter(filePath)); //File����.list(�ļ���������)
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER); //�ļ�������
        for(String dirItem : list){
            System.out.println(dirItem);
        }
    }
}

//������
class DirFilter implements FilenameFilter {       //jdk�Դ��ӿڣ�FilenameFilter
    private Pattern pattern;
    public DirFilter(String regex){
        pattern = Pattern.compile(regex);
    }
    public boolean accept(File dir, String name){
        return pattern.matcher(name).matches();
    }
}
/*
DirFilter��Ŀ�����ڣ�ʵ��Accept���������Խ�accept()�����ṩ��list�á�
 */

