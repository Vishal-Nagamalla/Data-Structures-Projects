import java.util.ArrayList;
import java.util.List;

public class BST<E extends Comparable<E>>{
	public static void main(String[]args){}
	private TreeNode<E> root;
	private int size;
	private String st; 

	public BST(){
		root = null;
		size = 0;
	}

	public int size(){
		return size;
	}

	public String inOrder() {
		if (size == 0) {
			return "[]";
		} else {
			List<E> list = new ArrayList<>();
			TinOrder(root, list);
			return list.toString();
		}
	}
	
	private void TinOrder(TreeNode<E> node, List<E> list) {
		if (node == null) {
			return;
		}
		TinOrder(node.left, list);
		list.add(node.value);
		TinOrder(node.right, list);
	}
	
	public String preOrder() {
		if (size == 0) {
			return "[]";
		} else {
			List<E> list = new ArrayList<>();
			TpreOrder(root, list);
			return list.toString();
		}
	}
	
	private void TpreOrder(TreeNode<E> node, List<E> list) {
		if (node == null) {
			return;
		}
		list.add(node.value);
		TpreOrder(node.left, list);
		TpreOrder(node.right, list);
	}
	
	public String postOrder() {
		if (size == 0) {
			return "[]";
		} else {
			List<E> list = new ArrayList<>();
			TpostOrder(root, list);
			return list.toString();
		}
	}
	
	private void TpostOrder(TreeNode<E> node, List<E> list) {
		if (node == null) {
			return;
		}
		TpostOrder(node.left, list);
		TpostOrder(node.right, list);
		list.add(node.value);
	}
	
	public boolean contains(E value) {
		TreeNode<E> node = root;
		while (node != null) {
			int cmp = value.compareTo(node.value);
			if (cmp < 0) {
				node = node.left;
			} else if (cmp > 0) {
				node = node.right;
			} else {
				return true;
			}
		}
		return false;
	}	

	public void add(E value) {
		TreeNode<E> parent = null;
		TreeNode<E> node = root;
	
		while (node != null) {
			int cmp = value.compareTo(node.value);
			if (cmp == 0) {
				return; 
			} else if (cmp < 0) {
				parent = node;
				node = node.left;
			} else {
				parent = node;
				node = node.right;
			}
		}
	
		TreeNode<E> newNode = new TreeNode<E>(value);
		if (parent == null) {
			root = newNode;  
		} else if (value.compareTo(parent.value) < 0) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}
		size++;
	}
	
	public void remove(E value) {
		root = remove(root, value);
	}
	
	private TreeNode<E> remove(TreeNode<E> node, E value) {
		if (node == null) {
			return null; 
		}
	
		int cmp = value.compareTo(node.value);
		if (cmp < 0) {
			node.left = remove(node.left, value);
		} else if (cmp > 0) {
			node.right = remove(node.right, value);
		} else {
			if (node.left == null) {
				return node.right; 
			} else if (node.right == null) {
				return node.left; 
			} else {
				TreeNode<E> successor = node.right;
				while (successor.left != null) {
					successor = successor.left;
				}
				node.value = successor.value;
				node.right = remove(node.right, successor.value);
			}
		}
		return node;
	}
	
	public void rotateRight() {
		if (root == null || root.left == null) {
			return;
		}
		TreeNode<E> old = root.left;
		root.left = old.right;
		old.right = root;
		root = old;
	}
	
	public void rotateLeft() {
		if (root == null || root.right == null) {
			return;
		}
		TreeNode<E> old = root.right;
		root.right = old.left;
		old.left = root;
		root = old;
	}
	
	public void print() {
		if (root == null) {
			System.out.println("Empty Tree");
		} else {
			print(root, 0, "");
		}
	}
	
	private void print(TreeNode<E> curr, int depth, String s) {
		if (curr == null) {
			return;
		}
		for (int i = 1; i <= depth - 1; i++) {
			System.out.print("|\t");
		}
		if (depth == 0) {
			System.out.println("[" + curr.value + "] <- root (L___ left, R___ right)");
		} else {
			System.out.print(s);
			System.out.println(curr.value);
		}
		print(curr.left, depth + 1, "L___");
		print(curr.right, depth + 1, "R___");
	}
	
	private class TreeNode<E extends Comparable<E>>{
		private E value;
		private TreeNode<E> left;
		private TreeNode<E> right;

		public TreeNode(E value){
			this.value = value;
			right = left = null;
		}

		public String toString(){
			return value.toString();
		}

	}
}