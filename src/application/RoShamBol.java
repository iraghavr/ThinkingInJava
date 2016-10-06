package application;

import java.util.Random;

/**
 * Created by liuda on 2016/10/6.
 */
// 剪 头 ，石头， 布 游戏

public class RoShamBol {
    static final int SIZE = 20;
    private static Random rand = new Random(47);
    public static Item newItem(){
        switch (rand.nextInt(3)){
            default:
            case 0 : return new Scissor();
            case 1 : return new Paper();
            case 2 : return new Rock();
        }
    }
    public static void match(Item a, Item b){
        System.out.println(a + " vs." + b+ ":"+a.compete(b));
    }
    public static void main(String[] args){
        for( int i = 0 ;i <SIZE; i++){
            match(newItem(), newItem());
        }
    }


}
enum OUTCOME{ WIN, DRAW, LOSE;}
interface Item{
    OUTCOME compete(Item it);
    OUTCOME eval(Rock r);
    OUTCOME eval(Paper p);
    OUTCOME eval(Scissor s);
}
class Paper implements  Item{
    public OUTCOME compete(Item it){
        return it.eval(this);
    }
    public OUTCOME eval(Paper p){
        return OUTCOME.DRAW;
    }
    public OUTCOME eval(Scissor s){
        return OUTCOME.WIN;
    }
    public OUTCOME eval(Rock r){
        return OUTCOME.LOSE;
    }
    public String toString(){ return "Paper";}
}
class Scissor implements  Item{
   public OUTCOME compete(Item it){
       return it.eval(this);
   }
    public OUTCOME eval(Paper p){
        return OUTCOME.LOSE;
    }
    public OUTCOME eval(Scissor s){
        return OUTCOME.DRAW;
    }
    public OUTCOME eval(Rock r){
        return OUTCOME.WIN;
    }
    public String toString(){ return "Scissor";}

}

class Rock implements Item{
    public OUTCOME compete(Item it){
        return it.eval(this);
    }
    public  OUTCOME eval(Paper p){
        return OUTCOME.WIN;
    }
    public  OUTCOME eval(Scissor s){
        return OUTCOME.LOSE;
    }
    public OUTCOME eval(Rock r){
        return OUTCOME.DRAW;
    }
    public String toString(){ return "Rock";}
}