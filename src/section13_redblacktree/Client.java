package section13_redblacktree;

public class Client {

	public static void main(String[] args) {
		int[] keys = {6,3,8,4,5,9,2,1,10};
		testInsert(keys);
		int[] keys1 = {5,10,8,9,6,4,2,1,3};
		testInsert(keys1);
		testDelete();
	}
	public static void testDelete(){
		int[] keys = {6,3,8,4,5,9,2,1,10};
		RBnode nil = new RBnode();
		nil.color = RBnode.Color.black;
		RBtree rBtree = new RBtree(nil);
		for(int i = 0; i < keys.length; i++){
			RBnode z = new RBnode();
			z.key = keys[i];
			rBtree.insert(z);			
		}
//		rBtree.firstShow(rBtree.root());
		RBnode node = rBtree.delete(rBtree.search(8));
		rBtree.firstShowDerict(rBtree.root);
		System.out.println();
		rBtree.insert(node);
		rBtree.firstShowDerict(rBtree.root);
		System.out.println();
	}
	public static void testInsert(int[] keys){
		RBnode nil = new RBnode();
		nil.color = RBnode.Color.black;
		RBtree rBtree = new RBtree(nil);
		for(int i = 0; i < keys.length; i++){
			RBnode z = new RBnode();
			z.key = keys[i];
			rBtree.insert(z);			
		}
//		rBtree.firstShow(rBtree.root());
		rBtree.firstShowDerict(rBtree.root);
		System.out.println();
	}
}
//6black(4red(2black(1red)(3red))(5black))(9black(8red)(10red))