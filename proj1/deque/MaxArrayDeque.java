package deque;
import java.util.Comparator;
public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> c){
        super();
        this.comparator=c;
    }
    public T max(){
        return max(this.comparator);
    }
    public T max(Comparator<T> c){
        if(size()==0){
            return null;
        }
        T max=get(0);
        for(int i=1;i<size();i++){
            if(c.compare(get(i),max)>0){
                max=get(i);
            }
        }
        return max;
    }
}
