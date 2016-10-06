package thinkInJava;
import static java.lang.System.out;

/**
 * Created by liuda on 2016/10/5.
 */

/*
�κ����﷢��֮ǰ �������������Ĵ洢�ռ��ʼ���ɶ����Ƶ���
���û��๹��������ʱ���ø��Ǻ��draw()������
��������˳����ó�Ա�ĳ�ʼ������
���õ�����Ĺ���������
 */
class Glyph{
    void draw(){
        out.println("GlyPh.draw()");
    }
    public Glyph(){
        out.println("Glyph() before draw()");
        draw();
        out.println("Glyph() after draw()");
    }
}

class RoundGlyph extends Glyph {
    private int radius = 1;
    public RoundGlyph(int r){
        radius =r;
        out.println("RoundGlyph.RoundGlyph(),radius = "+ radius);
    }
    void draw(){
        out.println("RoundGlyph.draw(), radius = "+ radius);
    }
}

public class Chapter8 {
    public static void main(String[] args){
        new RoundGlyph(5);
    }
}
