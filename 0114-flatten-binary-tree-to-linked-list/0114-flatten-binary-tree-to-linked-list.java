class Solution {
    public void flatten(TreeNode root) {
        TreeNode curr = root;

        while (curr != null) {
            if (curr.left != null) {
                TreeNode rightmost = curr.left;

                while (rightmost.right != null) {
                    rightmost = rightmost.right;
                }

                rightmost.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }

            curr = curr.right;
        }
    }
}