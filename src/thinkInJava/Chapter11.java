package thinkInJava;

import java.util.*;

import static java.lang.System.out;

/**
 * Created by liuda on 2016/10/5.
 */
public class Chapter11 {
    public static void main(String[] args){
        printCollection();
    }
    /* ���������תΪList
        new ArrayLIst<Integer>(Arrays.adList((2,3,4,5))
     */
    public static void collectionsAddAll(){
        Collection<Integer> collection = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6));
        Integer[] moreInts = {7,8,9,10};
        collection.addAll(Arrays.asList(moreInts));
        Collections.addAll(collection, 11, 12, 13, 14, 15);
    }
    /*
�����顢��������תΪList: Arrays.asList(T... a)
new ArrayLIst<Integer>(Arrays.asList((2,3,4,5))
new ArrayLIst<Integer>(Arrays.asList(Integer[])
���һ��Ԫ��
Collections.addAll(Collection<? super T> c, T... elements)//������Collections�ľ�̬����
Collection.addAll(Collection<? extends E> c); //Collectionʵ������

���ַ�ʽ�Ƚϣ�
    1.Collection.addAllΪ��ѡ���ɹ���һ��������Ԫ�ص�Collection�����ø÷������Ԫ�ء�
     2.       ʹ��Array.asList�����������ΪList,exp: List<Integer> list1 = Arrays.adList(15,5,6,7);
            ����֮�����ڣ���ײ��ʾΪ���飬�޷������ߴ磬��ͼ��add,delete���б�����Ԫ��ʱ�����п��ܻ������ı�����ĳߴ硣


 */
    public static void printCollection(){
        Collection<Integer> collection = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6));
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("11","q1");
        map.put("12","e1");
        out.println(collection);
        out.println(map);
    }
    public static void queue(){
        Queue<Integer> queue = new LinkedList<Integer>();
    }
    public static void prioryQueue(){
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
    }
}
