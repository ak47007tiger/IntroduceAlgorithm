package section12_binarysearchtree;

public class Client {
	public static void test1() {
		int[] keys = { 3, 7, 5, 12, 8, 22, 16, 14, 9 };

		Node root = new Node();
		root.key = 13;

		BinarySearchTree searchTree = new BinarySearchTree();

		for (int i = 0; i < keys.length; i++) {
			Node node = new Node();
			node.key = keys[i];
			searchTree.insert(root, node);
		}
		searchTree.firstShow(root);

		System.out.println("-----");
		System.out.println(searchTree.min(root).key);
		System.out.println(searchTree.max(root).key);
		System.out
				.println(searchTree.successor(searchTree.search(root, 7)).key);
		System.out
				.println(searchTree.successor(searchTree.search(root, 16)).key);
		System.out
				.println(searchTree.predesessor(searchTree.search(root, 16)).key);
		System.out.println("-----");
	}

	public static void test2() {
		int[] keys = { 5, 7, 3, 12, 8, 14, 16, 22, 9 };

		Node root = new Node();
		root.key = 13;

		BinarySearchTree searchTree = new BinarySearchTree();

		for (int i = 0; i < keys.length; i++) {
			Node node = new Node();
			node.key = keys[i];
			searchTree.insert(root, node);
		}
		searchTree.firstShow(root);
		System.out.println();
		System.out.println("-----");
		System.out.println(searchTree.min(root).key);
		System.out.println(searchTree.max(root).key);
		System.out
				.println(searchTree.successor(searchTree.search(root, 7)).key);
		System.out
				.println(searchTree.predesessor(searchTree.search(root, 5)).key);
		System.out
				.println(searchTree.predesessor(searchTree.search(root, 16)).key);
		System.out.println("-----");
		searchTree.firstShow(root);
		System.out.println();
		Node nodeKey12 = searchTree.search(root, 12); 
		searchTree.root(nodeKey12);
		searchTree.firstShow(root);
		System.out.println();
	}

	public static void main(String[] args) {
//		test1();
		System.out.println("*************************");
		test2();
	}
}
