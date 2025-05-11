class BST:
    def __init__(self, val):
        self.left = None
        self.right = None
        self.val = val

    # Insert a value into the BST
    def insert(self, val):
        if val < self.val:
            if self.left is None:
                self.left = BST(val)
            else:
                self.left.insert(val)
        else:
            if self.right is None:
                self.right = BST(val)
            else:
                self.right.insert(val)

    # Delete a value from the BST
    def delete(self, val):
        if val < self.val:
            if self.left:
                self.left = self.left.delete(val)
        elif val > self.val:
            if self.right:
                self.right = self.right.delete(val)
        else:
            # Node with one child or no child
            if self.left is None:
                return self.right
            elif self.right is None:
                return self.left
            # Node with two children, get the smallest value from the right subtree
            min_larger_node = self.right
            while min_larger_node.left:
                min_larger_node = min_larger_node.left
            self.val = min_larger_node.val
            self.right = self.right.delete(min_larger_node.val)
        return self

    # Print values in sorted order (in-order traversal)
    def inorder(self):
        if self.left:
            self.left.inorder()
        print(self.val, end=" ")
        if self.right:
            self.right.inorder()

# Example usage
root = BST(10)
root.insert(5)
root.insert(15)
root.insert(3)
root.insert(7)
root.insert(12)
root.insert(18)

# Print the tree before deletion
print("Inorder before deletion:")
root.inorder()

# Delete value 5
root.delete(5)

# Print the tree after deletion
print("\nInorder after deleting 5:")
root.inorder()
