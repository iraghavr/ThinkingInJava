package compareJdk;

/**
 * Created by liuda on 2016/10/4.
 */
import static java.lang.System.out;
public class UseEnumTypeSafe {
    public static void main(String[] args){
        for(EnumTypeSafe e : EnumTypeSafe.values()){
            out.printf("this is %s , and its degress is %d, name is %s%n",e,e.degree(),e.season());
        }
    }
}

// System.out.printf(���ɸ�ʽ�������䣬���� + ���Ŵ�ӡ���ɶ��Ը��á�