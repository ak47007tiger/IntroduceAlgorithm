package section18_btree;

public interface Key {
	boolean same(Key key);
	boolean lessThan(Key key);
	boolean moreThan(Key key);
}
