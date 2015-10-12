package section13_redblacktree;

public class Client {

	public static void main(String[] args) {
		testInsert();
	}
	public static void testInsert(){
		int[] keys = {6,3,8,4,5,9,2,1,10};
		RBnode nil = new RBnode();
		nil.color = RBnode.Color.black;
		RBtree rBtree = new RBtree(nil);
		for(int i = 0; i < keys.length; i++){
			RBnode z = new RBnode();
			z.key = keys[i];
			rBtree.insert(z);			
		}
		rBtree.firstShow(rBtree.root());
	}
}
//6black(4red(2black(1red)(3red))(5black))(9black(8red)(10red))