package section12_binarysearchtree;

public class Test {

	public static void main(String[] args) {
		Node root = new Node();
		root.key = 0;
		Node node1 = new Node();
		node1.key = 1;
		Node node2 = new Node();
		node2.key = 2;
		Node node3 = new Node();
		node3.key = 3;
		Node node4 = new Node();
		node4.key = 4;

		root.left = node1;
		root.right = node2;
		node1.left = node3;
		node1.right = node4;
		Test test = new Test();
		test.prentlnNode(root);
		test.change(root);
		test.prentlnNode(root);
		StringBuffer stringBuffer = new StringBuffer("01234");
		System.out.println(stringBuffer.toString());
		test.change(stringBuffer);
		System.out.println(stringBuffer.toString());
	}

	public void change(Node root) {
		root = root.left;
		/*while(root.left != null){
			root = root.left;
			System.out.println(root.key);
		}*/
	}
	public void change(StringBuffer stringBuffer){
		stringBuffer.replace(1, 2, "c");
	}

	public void prentlnNode(Node node) {
		System.out.println(node.key);
	}
}
