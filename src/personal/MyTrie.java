package personal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * 构建一颗字典树，能够根据输入的中文词汇提示出后续的词汇
 * 这些词汇的来源是用户已经输入的
 */
public class MyTrie {

	public static void main(String[] args) {
		MyTrie myTrie = new MyTrie();
		String[] histroyWords = { "香蕉你个巴拉", "榴莲糖", "榴莲弹", "鱼类", "鱼肉", "鱼香肉丝",
				"高考内容", "高中老师", "高级货", "高达", "高老庄", "球形闪电", "球星" };
		for (String word : histroyWords) {
			myTrie.insert(word);
		}
		System.out.println(null != myTrie.contains("高达"));
		System.out.println(null != myTrie.contains("鱼肉"));
		List<String> result = myTrie.getWordsWithPre("高");
		System.out.println(result.size());
		for (String word : result) {
			System.out.println(word);
		}
	}

	public List<String> getWordsWithPre(String pre) {
		TrieNode last;
		if (null != (last = contains(pre))) {
			return last.allWords();
		} else {
			return null;
		}
	}

	public void insert(String words) {
		int position = 0;
		TrieNode node = search(words.charAt(position));
		if (null != node) {
			position++;
			TrieNode child = node.search(words.charAt(position));
			while (null != child) {
				node = child;
				position++;
				child = node.search(words.charAt(position));
			}
			node.insert(words.substring(position));
		} else {
			appendChild(words.charAt(position)).insert(words.substring(position + 1));;
		}
	}

	public TrieNode contains(String word) {
		int position = 0;
		int length = word.length();
		TrieNode root = search(word.charAt(position));
		TrieNode node = root;
		if (null != node) {
			position++;
			while (null != node && node.childSize() > 0 && position < length) {
				node = node.search(word.charAt(position));
				position++;
			}
			if (position == word.length()) {
				return root;
			}
		}
		return null;
	}

	public TrieNode appendChild(char key) {
		TrieNode node = new TrieNode();
		node.key = key;
		children.add(node);
		return node;
	}

	private TrieNode search(char key) {
		for(TrieNode child : children){
			if(child.key == key){
				return child;
			}
		}
		return null;
	}

	LinkedList<TrieNode> children = new LinkedList<TrieNode>();

	public MyTrie() {

	}

	class TrieNode {
		char key;
		LinkedList<TrieNode> children = new LinkedList<TrieNode>();
		Comparable<TrieNode> c = new Comparable<MyTrie.TrieNode>() {
			@Override
			public int compareTo(TrieNode o) {
				return 0;
			}
		};

		public List<String> allWords() {
			List<String> words = new ArrayList<String>();
			if(children.size() == 0){
				words.add(String.valueOf(key));
			}else{
				for (TrieNode child : children) {
					List<String> childWords = child.allWords();
					for (String childWrod : childWords) {
						char[] cbuf = new char[childWrod.length() + 1];
						cbuf[0] = key;
						System.arraycopy(childWrod.toCharArray(), 0, cbuf, 1,
								childWrod.length());
						words.add(new String(cbuf));
					}
				}
			}
			return words;
		}

		public void insert(String substring) {
			int position = 0;
			int length = substring.length();
			TrieNode parent = this;
			while (position < length) {
				TrieNode node = new TrieNode();
				node.key = substring.charAt(position);
				parent.children.add(node);
				parent = node;
				position++;
			}
		}

		public TrieNode search(char charAt) {
			for (TrieNode node : children) {
				if (charAt == node.key) {
					return node;
				}
			}
			return null;
		}

		public boolean contains(String word) {
			return false;
		}

		public int childSize() {
			return children.size();
		}
	}
}
