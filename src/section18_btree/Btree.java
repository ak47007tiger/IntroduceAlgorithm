package section18_btree;


public class Btree {
	Btnode root;

	public void creaKeye(Key key,int m) {
		root = new Btnode(m);
		root.addKey(key);
	}

	public void inserKey(Key key) {

	}

	public Btnode search(Btnode x, Key key) {
		return null;
	}

	public void deleKeye(Key key) {
		
	}
}
