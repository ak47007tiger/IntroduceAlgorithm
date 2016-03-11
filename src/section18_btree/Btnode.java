package section18_btree;

import java.util.Arrays;
import java.util.Comparator;

public class Btnode {
	Key[] keys;
	Btnode[] children;
	int position;
	int limite;
	boolean leaf;
	public static Comparator<Key> c = new Comparator<Key>() {
		@Override
		public int compare(Key o1, Key o2) {
			if (o1.lessThan(o2)) {
				return -1;
			} else if (o1.same(o2)) {
				return 0;
			}
			return 1;
		}
	};

	public Btnode(int m) {
		assert m > 0;
		keys = new Key[m];
		children = new Btnode[m + 1];
		limite = m;
		position = 0;
	}

	public void addKey(Key key) {
		assert !full();
		keys[position++] = key;
		Arrays.sort(keys, c);
	}

	public boolean full() {
		return position == limite;
	}

	public void addChild(Btnode child) {
		
	}
	public Btnode split(Key key){
		return null;
	}
}
