package personal;

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
				"高考内容", "高中老师", "高级货", "高达","高级货贵", "高老庄", "球形闪电", "球星","a1","a2","a3" };
		for (String word : histroyWords) {
			myTrie.insert(word);
		}
		System.out.println(null != myTrie.contains("高达"));
		System.out.println(null != myTrie.contains("鱼肉"));
		TrieNode gaoji = myTrie.contains("高级");
		System.out.println(gaoji.key);
		List<String> result = myTrie.getWordsWithPre("高级");
		System.out.println(result.size());
		for (String word : result) {
			System.out.println(word);
		}
	}

	LinkedList<TrieNode> children = new LinkedList<TrieNode>();

	public List<String> getWordsWithPre(String pre) {
		TrieNode last;
		if (null != (last = contains(pre))) {
			return last.allWords();
		} else {
			return null;
		}
	}
	
	public List<String> allWords(){
		List<String> words = new LinkedList<String>();
		for(TrieNode child : children){
			words.addAll(child.allWords());
		}
		return words;
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
				return node;
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
	
}
