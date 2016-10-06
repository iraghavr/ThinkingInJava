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
    public static String regulaExpression= ".*\\.xml";    //任意字符 0个或多个，以\.xml结尾
    public static void main(String[] args){

        //test_directoryListUtil();
        try{
            Logon.test();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    //格式化的内存输入
    public static void formatOutput(){
        try {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(read("D:\\test.txt").getBytes()));
            System.out.println(in.readByte());
        }catch (IOException e){
            e.printStackTrace();
        }
        //输出
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("")));
        }catch (Exception e){

        }
    }
    //使用包装IO类
    //缓冲输入文件
    public static String read(String fileName) throws IOException{
            BufferedReader br  =  new BufferedReader(new FileReader(fileName));

            String s;
            StringBuilder sb = new StringBuilder();
            while(( s = br.readLine())!=null)
                sb.append(s + "\n");
        return sb.toString();

    }
    //目录列表器 ： 搜索某
    public static void test_directoryListUtil(){
        File path = new File("D:\\JD");
        String[] list;
        list = path.list(new DirFilter(regulaExpression)); //File对象.list(文件名过滤器)
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER); //文件名排序
        for(String dirItem : list){
            System.out.println(dirItem);
        }
    }
}

//过滤器
class DirFilter implements FilenameFilter {       //jdk自带接口，FilenameFilter
    private Pattern pattern;
    public DirFilter(String regex){
        pattern = Pattern.compile(regex);
    }
    public boolean accept(File dir, String name){
        return pattern.matcher(name).matches();
    }
}
/*
DirFilter的目的在于，实际Accept方法，可以将accept()方法提供给list用。 ——回调
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
        TimeUnit.SECONDS.sleep(1); //延迟
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Logon.out"));
        System.out.println("Recovering object at "+ new Date() );
        a = (Logon)in.readObject();
        System.out.println("logon a =" + a);
    }
}