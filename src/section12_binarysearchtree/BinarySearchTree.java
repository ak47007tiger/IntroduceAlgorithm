package section12_binarysearchtree;

public class BinarySearchTree {
	public void insert(Node root, Node insertNode) {
		Node parent = null;
		Node curNode = root;
		while (null != curNode) {
			parent = curNode;
			if (insertNode.key < curNode.key) {
				curNode = curNode.left;
			} else {
				curNode = curNode.right;
			}
		}
		insertNode.parent = parent;
		if (insertNode.key < parent.key) {
			parent.left = insertNode;
		} else {
			parent.right = insertNode;
		}
	}

	public void delete(Node deleteNode) {
		boolean isLeft = deleteNode.parent.left == deleteNode;
		Node parent = deleteNode.parent;
		Node deleteNodeLeft = deleteNode.left;
		Node deleteNodeRight = deleteNode.right;
		if (null == deleteNodeLeft && null == deleteNodeRight) {
			if (isLeft) {
				parent.left = null;
			} else {
				parent.right = null;
			}
		} else if (null == deleteNodeLeft) {
			deleteNodeRight.parent = parent;
			if (isLeft) {
				parent.left = deleteNodeRight;
			} else {
				parent.right = deleteNodeRight;
			}
		} else if (null == deleteNodeRight) {
			deleteNodeLeft.parent = parent;
			if (isLeft) {
				parent.left = deleteNodeLeft;
			} else {
				parent.right = deleteNodeLeft;
			}
		} else {
			Node successor = successor(deleteNode);
			delete(successor);
			successor.parent = parent;
			if (isLeft) {
				parent.left = successor;
			} else {
				parent.right = successor;
			}
			successor.left = deleteNodeLeft;
			successor.right = deleteNodeRight;
		}
	}

	public Node root(Node node) {
		while (null != node.parent) {
			node = node.parent;
		}
		return node;
	}

	public Node search(Node rootNode, int key) {
		if (null == rootNode || rootNode.key == key) {
			return rootNode;
		}
		if (key < rootNode.key) {
			return search(rootNode.left, key);
		} else {
			return search(rootNode.right, key);
		}
	}

	public Node interativeSearch(Node rootNode, int key) {
		while (rootNode != null && rootNode.key != key) {
			if (key < rootNode.key) {
				rootNode = rootNode.left;
			} else {
				rootNode = rootNode.right;
			}
		}
		return rootNode;
	}

	public Node min(Node root) {
		while (null != root.left) {
			root = root.left;
		}
		return root;
	}

	public Node max(Node root) {
		while (null != root.right) {
			root = root.right;
		}
		return root;
	}

	public Node successor(Node node) {
		if (null != node.right) {
			return min(node.right);
		}
		while (null != node.parent && node.parent.right == node) {
			node = node.parent;
		}
		// node == null || node.parent.left == node (node.parent.key > node.key)
		return node.parent;
	}

	public Node predesessor(Node node) {
		if (null != node.left) {
			return max(node.left);
		}
		while (null != node.parent && node.parent.left == node) {
			node = node.parent;
		}
		// node == null || node.parent.right == node
		return node.parent;
	}

	public void firstShow(Node root) {
		if (null != root) {
			System.out.print(root.key + " ");
			firstShow(root.left);
			firstShow(root.right);
		}
	}
}
