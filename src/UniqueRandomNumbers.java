import java.util.ArrayList;
import java.util.Collections;

public class UniqueRandomNumbers {
	static ArrayList<Integer> list = new ArrayList<Integer>();
	static int j =0;
    public static void setup(int max) {
        
        for (int i=0; i<max; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
    }
    
    public static int getNumber(){
    	if(j>=list.size())
    	{
    		j = 0;
    	}
    	int result = list.get(j);
    	j++;
    	return result;
    				
    }
}