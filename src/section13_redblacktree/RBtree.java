package section13_redblacktree;

public class RBtree {
	RBnode nil;
	RBnode root;

	public RBtree(RBnode nil) {
		super();
		this.nil = nil;
		root = nil;
	}

	public void insert(RBnode z) {
		RBnode y = nil;
		RBnode x = root;
		while (x != nil) {
			y = x;
			if (z.key < x.key) {
				x = x.left;
			} else {
				x = x.right;
			}
		}
		z.p = y;
		if (y == nil) {
			root = z;
		} else {
			if (z.key < y.key) {
				y.left = z;
			} else {
				y.right = z;
			}
		}
		z.left = nil;
		z.right = nil;
		z.color = RBnode.Color.red;
		fixup_insert(z);
	}

	public void fixup_insert(RBnode z) {
		while (z.p.color == RBnode.Color.red) {
			// parent of z is left of it's parent
			if (z.p == z.p.p.left) {
				RBnode y = z.p.p.right;
				if (y.color == RBnode.Color.red) {
					z.p.color = RBnode.Color.black;
					y.color = RBnode.Color.black;
					z.p.p.color = RBnode.Color.red;
					z = z.p.p;
				} else {
					// z is right of parent
					if (z == z.p.right) {
						z = z.p;
						leftRotate(z);
					}
					z.p.color = RBnode.Color.black;
					z.p.p.color = RBnode.Color.red;
					rightRotate(z.p.p);
				}
			} else {
				RBnode y = z.p.p.left;
				if (y.color == RBnode.Color.red) {
					z.p.color = RBnode.Color.black;
					y.color = RBnode.Color.black;
					z.p.p.color = RBnode.Color.red;
					z = z.p.p;
				} else {
					if (z == z.p.left) {
						z = z.p;
						rightRotate(z);
					}
					z.p.color = RBnode.Color.black;
					z.p.p.color = RBnode.Color.red;
					leftRotate(z.p.p);
				}
			}
		}
		root.color = RBnode.Color.black;
	}

	

	void leftRotate(RBnode x) {
		RBnode y = x.right;
		x.right = y.left;
		y.left.p = x;
		y.p = x.p;
		// x is root
		if (x.p == nil) {
			root = y;
		} else {
			// x is left of parent
			if (x == x.p.left) {
				x.p.left = y;
			} else {
				x.p.right = y;
			}
		}
		y.left = x;
		x.p = y;
	}

	void rightRotate(RBnode x) {
		RBnode y = x.left;
		x.left = y.right;
		y.right.p = x;
		y.p = x.p;
		if (x.p == nil) {
			root = y;
		} else {
			// x is left of parent
			if (x == x.p.left) {
				x.p.left = y;
			} else {
				x.p.right = y;
			}
		}
		y.right = x;
		x.p = y;
	}

	public void firstShow(RBnode node) {
		System.out
				.print(String.format("%d[%s]", node.key, node.color.toString()));
		if (node.left != nil) {
			System.out.print(String.format(" %dleft(",node.key));
			firstShow(node.left);
			System.out.print(")");
		}
		if (node.right != nil) {
			System.out.print(String.format(" %dright(",node.key));
			firstShow(node.right);
			System.out.print(")");
		}
	}

	public RBnode root() {
		return root;
	}
}
