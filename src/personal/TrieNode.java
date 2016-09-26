package personal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TrieNode {
	char key;
	LinkedList<TrieNode> children = new LinkedList<TrieNode>();

	public List<String> allWords() {
		List<String> words = new ArrayList<String>();
		if (children.size() == 0) {
			words.add(String.valueOf(key));
		} else {
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

	public int childSize() {
		return children.size();
	}
}