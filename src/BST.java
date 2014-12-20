
  import java.util.Scanner;
     /* Class BSTNode */
     class BSTNode
     {
         BSTNode left, right;
         int data;
         String key;

         /* Constructor */
         public BSTNode()
         {
             left = null;
             right = null;
             data = 0;
         }

         /* Constructor */
         public BSTNode(int n)
         {
             left = null;
             right = null;
             data = n;
         }
         public BSTNode(int n,String m)
         {
             left = null;
             right = null;
             data = n;
             key=m;
         }


        /* Function to set left node */
         public void setLeft(BSTNode n)
         {
             left = n;
         }

         /* Function to set right node */ 
         public void setRight(BSTNode n)
         {
             right = n;
         }

         /* Function to get left node */
         public BSTNode getLeft()
         {
             return left;
         }

         /* Function to get right node */
         public BSTNode getRight()
         {
             return right;
         }

         /* Function to set data to node */
         public void setData(int d)
         {
             data = d;
         }

         /* Function to get data from node */
         public int getData()
         {
             return data;
         }     
     }

     

     /* Class BST */
     public class BST
     {
         private BSTNode root;

         /* Constructor */
         public BST()
         {
             root = null;
         }

         /* Function to check if tree is empty */
         public boolean isEmpty()
         {
             return root == null;
         }

         /* Functions to insert data */
         public void insert(int data,String key)
         {
             root = insert(root, data,key);
         }
       
         /* Function to insert data recursively */
         private BSTNode insert(BSTNode node, int data,String key)
         {
             if (node == null)
                 node = new BSTNode(data,key);
             else
             {
                 if (data <= node.getData())
                     node.left = insert(node.left, data,key);
                 else
                     node.right = insert(node.right, data,key);
             }

             return node;
         }

         /* Functions to delete data */
         

         private BSTNode delete(BSTNode root, int k)
         {
             BSTNode p, p2, n;
             if (root.getData() == k)
             {
                 BSTNode lt, rt;
                 lt = root.getLeft();
                 rt = root.getRight();
                 if (lt == null && rt == null)
                     return null;
                else if (lt == null)
                {
                     p = rt;
                     return p;
                 }

                 else if (rt == null)
                 {
                     p = lt;
                     return p;
                 }
                 else
                 {
                     p2 = rt;
                     p = rt;
                     while (p.getLeft() != null)
                         p = p.getLeft();
                     p.setLeft(lt);
                     return p2;
                 }
             }

            if (k < root.getData())
             {
                 n = delete(root.getLeft(), k);
                 root.setLeft(n);
             }
             else
             {
                 n = delete(root.getRight(), k);
                 root.setRight(n);             
             }
             return root;
         }

         /* Functions to count number of nodes */
         public int countNodes()
         {
             return countNodes(root);
         }

         /* Function to count number of nodes recursively */
         private int countNodes(BSTNode r)
         {
             if (r == null)
                 return 0;
             else
             {
                 int l = 1;
                 l += countNodes(r.getLeft());
                 l += countNodes(r.getRight());
                 return l;
             }
         }

         public int getLeafCount()
         {
             return getLeafCount(root);
         }

         public int getLeafCount(BSTNode node)
         {
            if(node == null)      
               return 0;
            if((node.getLeft() == null) && (node.getRight() == null))     
               return 1;           
            else
               return getLeafCount(node.getLeft())+ getLeafCount(node.getRight());     
         }

         /* Functions to search for an element */
         public String search(int val)
         {
             return search(root, val);
         }

         /* Function to search for an element recursively */
         private String search(BSTNode r, int val)
         {
             String found = "0";
             while ((r != null) && found.equalsIgnoreCase("0"))
             {
                 int rval = r.getData();
                 if (val < rval)
                     r = r.getLeft();
                 else if (val > rval)
                     r = r.getRight();
                 else
                 {
                     found = r.key;
                     break;
                 }
                 found = search(r, val);
             }
             return found;
         }

         /* Function for inorder traversal */
         public void inorder()
         {
             inorder(root);
         }

        private void inorder(BSTNode r)
         {
             if (r != null)
             {
                 inorder(r.getLeft());
                 System.out.print(r.getData() +" ");
                 inorder(r.getRight());
            }
         }

         /* Function for preorder traversal */
         public void preorder()
         {
             preorder(root);
         }

         private void preorder(BSTNode r)
         {
             if (r != null)
             {
                 System.out.print(r.getData() +" ");
                 preorder(r.getLeft());             
                 preorder(r.getRight());
             }
        }

         /* Function for postorder traversal */
         public void postorder()
         {
             postorder(root);
         }

         private void postorder(BSTNode r)
         {
             if (r != null)
             {
                 postorder(r.getLeft());             
                 postorder(r.getRight());
                 System.out.print(r.getData() +" ");
             }
         }     
     }
  
