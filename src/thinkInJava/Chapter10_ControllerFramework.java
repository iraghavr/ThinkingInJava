package thinkInJava;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * Created by liuda on 2016/10/5.
 */
public class Chapter10_ControllerFramework {
    public static void main(String[] args){
        GreenhouseController.test();
    }
}

/*
�ڲ��� & ���ƿ��
 */
//�¼�
abstract  class  Event{
    private long eventTime;
    protected  final long delayTime;
    //����Event��������start(),start��һ������������δ�����ڹ������ڣ��ʿ������¼����к�����������ʱ���������ظ�ʹ��Event����������Ҫ�ظ�һ���¼���ֻ��Ҫ����action()�е���start()
    public Event(long delayTime){
        this.delayTime = delayTime;
        start();
    }
    public void start(){
        eventTime = System.nanoTime() + delayTime;
    }
    public boolean ready(){     //���ߺ�ʱ��������acton��������Ȼready()���Ա����ǣ�ʹ��Event�ܹ�����ʱ�����µ��������ض�������
        return System.nanoTime()>=eventTime;
    }
    public abstract  void action();              //���󷽷�
}

//�������������¼���ʵ�ʿ��ƿ��
class Controller{
    private List<Event> eventList = new ArrayList<Event>();          //��̬ List-ArrayList
    public void addEvent(Event c){ eventList.add(c);}
    public void run(){
        while(eventList.size()>0){
            for(Event e : new ArrayList<Event>(eventList)){          //��Ҫʹ��eventList, ʹ��ʵ������
                if(e.ready()){
                    out.println(e);
                    e.action();
                    eventList.remove(e);
                }
            }
        }
    }
}

/* ��Ŀǰ��ƣ�����֪��Event��ʲô����������ƵĹؼ����ڣ� ��ʹ�仯 �������벻���������롰
  ���� ���仯������ �Ǹ��ֲ�ͬ��Event���������еĲ�ͬ��Ϊ������ͨ��������ͬ��Event���������ֲ�ͬ����Ϊ��
  �������ڲ���Ҫ���ģ� �ڲ�������
     1. ���ƿ�ܵ�����ʵ�����ɵ������ഴ���ģ��Ӷ�ʹ��ʵ�ֵ�ϸ�ڱ���װ�������ڲ���������ʾ �����������Ҫ�ĸ��ֲ�ͬ��action()
     2. �ڲ����ܹ������׵ط�����Χ��������Ա �����Կ��Ա�������ʵ�ֱ�ñ�׾��
 */
// exp: ���ƿ�ܵ�һ���ض�ʵ�� �� �������ҵĶ��������Ƶƹ⣬ˮ���¶ȵ��������أ����壬����ϵͳ
// ���ƿ�ܵ����ʹ��Щ��ͬ�����÷ǳ����ף� ʹ���ڲ��࣬�����ڵ�һ�������������ͬһ������Event�Ķ��ֵ����汾����������ϵͳ��ÿһ����Ϊ�����̳�һ���µ�Event�ڲ��࣬����ʵ�ֵ�action�б�д���ƴ���
class GreenhouseControls extends Controller {
    private  boolean light = false;
    public class LightOn extends Event {
        public LightOn(long delayTime){super(delayTime);}
        public void action(){light = true;}
        public String toString(){return "Light is on";}
    }
    public class LightOff extends Event {
        public LightOff(long delayTime){super(delayTime);}
        public void action(){light = false;}
        public String toString(){return "Light is off";}
    }
    private boolean water = false;
    public class WaterOn extends Event {
        public WaterOn(long delayTime){super(delayTime);}
        public void action(){water = true;}
        public String toString(){return "Water is on";}
    }
    public class WaterOff extends Event {
        public WaterOff(long delayTime){super(delayTime);}
        public void action(){water = false;}
        public String toString(){return "Water is off";}
    }
    private boolean bell = false;
    public class Bell extends Event {
        public Bell(long delayTime){super(delayTime);}
        public void action(){addEvent(new Bell(delayTime));}
        public String toString(){return "Bing";}
    }
    public class Restart extends Event {                        //�ڲ���Bell,Restart��Event�����з����������ƺ�Ҳӵ����Χ��GreenhouseContronls�����з���
        private  Event[] eventsList;
        public Restart(long delayTime, Event[] eventsList){            //������ �����б�
            super(delayTime);
            this.eventsList = eventsList;
            for(Event e :eventsList){
                addEvent(e);                                          //�������
            }
        }
        public void action(){
            for(Event e: eventsList){
                e.start();
                addEvent(e);
            }
            start();
            addEvent(this);  //RestartҲ��һ��Event��������ͬ�����Խ�Restart������ӵ�action()�У���ʹϵͳ�й��ɵ����������Լ�
        }
        public String toString(){
            return "Restarting system";
        }
    }
    public static class Terminate extends Event {
        public Terminate(long delayTime){super(delayTime);}
        public void action(){System.exit(0);}
        public String toString(){
            return "Terminating";
        }
    }



}

class GreenhouseController{                  //������Ӳ�ͬ��Event���������ø�ϵͳ���������ģʽ
    public static void test(){
        GreenhouseControls gc = new GreenhouseControls();
        gc.addEvent(gc.new Bell(900) );
        Event[] eventList = {
                gc.new LightOn(200),  //����
                gc.new LightOff(400),
                gc.new WaterOn(600), //��ˮ
                gc.new WaterOff(800)
        };
        gc.addEvent(gc.new Restart(2000,eventList));
        //ע�� gc.new  ��ʱ���½��ڲ����������Χ�������gc,����Restart���췽���У�����������ʼ����restart����
        // eventListͨ��forѭ������addEvent(),Ҳ�����뵽��gc��eventListβ��
        gc.addEvent(new GreenhouseControls.Terminate(new Integer(10000)));
        gc.run();
        /*
        ��� ������ Terminater�¼�������ѭ���������Զ���ֹ��ԭ�����ڣ�
            ��������run�����������������е�eventListִ�������ɾ������
            ������Restart��ÿ��ִ��ʱ�Ὣ���ѳ���ventList �� �Լ��� ���ص��������С�
         */
    }
}