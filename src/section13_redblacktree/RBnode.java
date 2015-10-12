package section13_redblacktree;

public class RBnode {
	public static enum Color{
		red,black
	}
	int key;
	Color color;
	RBnode p;
	RBnode left;
	RBnode right;
}
