
import java.util.*;

public class InvertiLista {

	public static void invertiLista(LinkedList<Integer> list) {
		if (list.size() == 0);
		else{
			int a = list.removeFirst();
			invertiLista(list);
			list.addLast(a);
		}
	}
}
