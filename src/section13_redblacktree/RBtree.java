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
		System.out.print(String.format("%d[%s]", node.key,
				node.color.toString()));
		if (node.left != nil) {
			System.out.print(String.format(" %dleft(", node.key));
			firstShow(node.left);
			System.out.print(")");
		}
		if (node.right != nil) {
			System.out.print(String.format(" %dright(", node.key));
			firstShow(node.right);
			System.out.print(")");
		}
	}
	public void firstShowDerict(RBnode node) {
		System.out.print(String.format("%d%s", node.key,
				node.color.toString()));
		if (node.left != nil) {
			System.out.print(String.format("(", node.key));
			firstShowDerict(node.left);
			System.out.print(")");
		}
		if (node.right != nil) {
			System.out.print(String.format("(", node.key));
			firstShowDerict(node.right);
			System.out.print(")");
		}
	}

	public RBnode root() {
		return root;
	}

	public RBnode delete(RBnode z) {
		RBnode y;
		if (z.left == nil || z.right == nil) {
			y = z;
		} else {
			y = successor(z);
		}
		RBnode x;
		if (y.left != nil) {
			x = y.left;
		} else {
			x = y.right;
		}
		x.p = y.p;
		if (y.p == nil) {
			root = x;
		} else {
			if (y == y.p.left) {
				y.p.left = x;
			} else {
				y.p.right = x;
			}
		}
		if (y != z) {
			z.key = y.key;
			// copy y's satellite data into z
		}
		if (y.color == RBnode.Color.black) {
			fixup_delete(x);
		}
		return y;
	}

	public RBnode successor(RBnode z) {
		if (z.right != nil) {
			return min(z.right);
		}
		while (z.p != nil && z.p.right == z) {
			z = z.p;
		}
		return z.p;
	}

	public RBnode min(RBnode x) {
		while (x.left != nil) {
			x = x.left;
		}
		return x;
	}

	public void fixup_delete(RBnode x) {
		while (x != root && x.color == RBnode.Color.black) {
			RBnode w;
			if (x == x.p.left) {
				w = x.p.right;
				if (w.color == RBnode.Color.red) {
					//case1
					w.color = RBnode.Color.black;
					x.p.color = RBnode.Color.red;
					leftRotate(x.p);
					w = x.p.right;
					//case1 end
				}
				if (w.left.color == RBnode.Color.black
						&& w.right.color == RBnode.Color.black) {
					//case2
					w.color = RBnode.Color.red;
					x = x.p;
					//case2 end
				} else {
					if (w.right.color == RBnode.Color.black) {
						//case3
						w.left.color = RBnode.Color.black;
						w.color = RBnode.Color.red;
						rightRotate(w);
						w = x.p.right;
						//cae3 end
					}
					//case4
					w.color = x.p.color;
					x.p.color = RBnode.Color.black;
					w.right.color = RBnode.Color.black;
					leftRotate(x.p);
					x = root;
					//case4 end
				}
			}else{
				w = x.p.left;
				if (w.color == RBnode.Color.red) {
					//case1
					w.color = RBnode.Color.black;
					x.p.color = RBnode.Color.red;
					rightRotate(x.p);
					w = x.p.left;
					//case1 end
				}
				if (w.right.color == RBnode.Color.black
						&& w.left.color == RBnode.Color.black) {
					//case2
					w.color = RBnode.Color.red;
					x = x.p;
					//case2 end
				} else {
					if (w.left.color == RBnode.Color.black) {
						//case3
						w.right.color = RBnode.Color.black;
						w.color = RBnode.Color.red;
						leftRotate(w);
						w = x.p.left;
						//case3 end
					}
					//case4
					w.color = x.p.color;
					x.p.color = RBnode.Color.black;
					w.left.color = RBnode.Color.black;
					rightRotate(x.p);
					x = root;
					//case4 end
				}
			}
		}
		x.color = RBnode.Color.black;
	}
	public RBnode search(int key){
		RBnode curNode = root;
		while(curNode != nil){
			if(key < curNode.key){
				curNode = curNode.left;
			}else if(key > curNode.key){
				curNode = curNode.right;
			}else{
				return curNode;
			}
		}
		return curNode;
	}
}
