package thinkInJava;

import sun.rmi.runtime.Log;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * Created by liuda on 2016/10/6.
 */
public class Chapter18_IO {
    public static String regulaExpression= ".*\\.xml";    //�����ַ� 0����������\.xml��β
    public static void main(String[] args){

        //test_directoryListUtil();
        try{
            Logon.test();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    //��ʽ�����ڴ�����
    public static void formatOutput(){
        try {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(read("D:\\test.txt").getBytes()));
            System.out.println(in.readByte());
        }catch (IOException e){
            e.printStackTrace();
        }
        //���
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("")));
        }catch (Exception e){

        }
    }
    //ʹ�ð�װIO��
    //���������ļ�
    public static String read(String fileName) throws IOException{
            BufferedReader br  =  new BufferedReader(new FileReader(fileName));

            String s;
            StringBuilder sb = new StringBuilder();
            while(( s = br.readLine())!=null)
                sb.append(s + "\n");
        return sb.toString();

    }
    //Ŀ¼�б��� �� ����ĳ
    public static void test_directoryListUtil(){
        File path = new File("D:\\JD");
        String[] list;
        list = path.list(new DirFilter(regulaExpression)); //File����.list(�ļ���������)
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
DirFilter��Ŀ�����ڣ�ʵ��Accept���������Խ�accept()�����ṩ��list�á� �����ص�
 */


class Logon implements Serializable{
    private Date date = new Date();
    private String username;
    private transient String password;
    public Logon(String name, String pwd){
        username = name;
        password = pwd;
    }
    public String toString(){
        return "Logon info: \n   username :"+username+"\n  date:"+date+"\n   password:"+password;
    }
    public static void test() throws  Exception{
        Logon a = new Logon("Hulk","myLittlePony");
        System.out.println("logon a =" + a);
        ObjectOutputStream o = new ObjectOutputStream((new FileOutputStream("Logon.out")));
        o.writeObject(a);
        o.close();
        TimeUnit.SECONDS.sleep(1); //�ӳ�
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Logon.out"));
        System.out.println("Recovering object at "+ new Date() );
        a = (Logon)in.readObject();
        System.out.println("logon a =" + a);
    }
}