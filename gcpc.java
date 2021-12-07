import java.io.*;

public class Gcpc {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);
        String[] details = bf.readLine().split(" ");
        int teams = Integer.parseInt(details[0]);
        int events = Integer.parseInt(details[1]);

        OwnAVLTree avlTree = new OwnAVLTree();
        Team[] teamsArr = new Team[teams + 1];

        for (int i = 1; i < teams + 1; i++) { //team start with 1
            teamsArr[i] = new Team(i);
        }

        avlTree.insert(teamsArr[1]);   // for comparison

        for (int i = 0; i < events; i++) {
            details = bf.readLine().split(" ");
            int teamNum = Integer.parseInt(details[0]);
            int penalty = Integer.parseInt(details[1]);

            avlTree.delete(teamsArr[teamNum]);
            teamsArr[teamNum].solvedProblem(penalty);
            avlTree.insert(teamsArr[teamNum]);

            //only need to print the rank of team 1
            writer.println(avlTree.getRank(teamsArr[1]));
        }
        writer.flush();
        writer.close();
        bf.close();
    }
}

class Team implements Comparable<Team> {
    int teamNum;
    int numSolvedProblems;
    int penalty;

    public Team(int teamNum) {
        this.teamNum = teamNum;
        this.numSolvedProblems = 0; //at first all team solve 0 prob
        this.penalty = 0; // at first all team penalty is 0
    }

    // increment numSolvedProblems and add penalty
    public void solvedProblem(int penalty) {
        this.numSolvedProblems++;
        this.penalty += penalty;
    }

    @Override
    public int compareTo(Team other) {
        if(this.numSolvedProblems > other.numSolvedProblems){
            return -1;
        } else if(this.numSolvedProblems < other.numSolvedProblems) {
            return 1;
        } else { //if equal check penalty
            if(this.penalty > other.penalty) {
                return 1;
            } else if(this.penalty < other.penalty) {
                return -1;
            } else { //same penalty return smaller team
                return this.teamNum - other.teamNum; //(Integer.compare(this.teamNum, other.teamNum)
            }
        }
    }
}

class AVLVertex { //node class for my AVL tree
    public AVLVertex parent, left, right;
    public Team key; //data for each node will be the team information
    public int height;
    public int size;

    AVLVertex(Team t) {
        key = t;
        parent = left = right = null;
        height = 0;
        size = 1;
    }

    public void updateInfo() {
        if(this.left == null && this.right == null){
            this.height = 0;
            this.size = 1;
        } else {
            if(this.left == null) { // no left child
                this.height = this.right.height + 1;
                this.size = this.right.size + 1;
            } else if(this.right == null) { //no right child
                this.height  = this.left.height + 1;
                this.size = this.left.size + 1;
            } else {
                this.height = Math.max(this.left.height, this.right.height) + 1;
                this.size = this.left.size + this.right.size + 1;
            }
        }
    }
}

class OwnAVLTree {
    public AVLVertex root;

    public OwnAVLTree() { this.root = null; }

    public Team findMin(AVLVertex T) {
        if (T.left == null) {
            return T.key; // this is the lowest ranked team
        }
        else  {
            return findMin(T.left); // go to the left recursively
        }
    }

    // public method called to insert a new key with value v into BST
    public void insert(Team team) { root = insert(root, team); }

    // helper recursive method to perform insertion of new vertex into BST
    protected AVLVertex insert(AVLVertex T, Team team) {
        if (T == null) {
            return new AVLVertex(team);          // insertion point is found
        } else if (T.key.compareTo(team) >= 0) {      // search to the left : team < T
            T.left = insert(T.left, team);
            T.left.parent = T;
        } else {
            T.right = insert(T.right, team); // search to the right
            T.right.parent = T;
        }
        T.updateInfo();
        T = balance(T); // need to balance since its AVL
        return T;  // return the updated AVL
    }

    // public method to delete a vertex containing key with value v from BST
    public void delete(Team team) { root = delete(root, team); }

    // helper recursive method to perform deletion
    protected AVLVertex delete(AVLVertex T, Team team) { //protected not used by the user
        if(T == null) { return null; }

        if (T.key.compareTo(team) > 0) {
            T.left = delete(T.left, team);      // search to the left
        } else if (T.key.compareTo(team) < 0) {     // search to the right
            T.right = delete(T.right, team);
        } else {   // this is the node to be deleted
            if (T.left == null && T.right == null) {// this is a leaf
                T = null;  // simply erase this node
            } else if (T.left == null && T.right != null) {   // only one child at right
                T.right.parent = T.parent;
                T = T.right;  // bypass T
            } else if (T.left != null && T.right == null) {    // only one child at left
                T.left.parent = T.parent;
                T = T.left;    // bypass T
            } else {   // has two children, find successor
                Team successorV = findMin(T.right);
                T.key = successorV;         // replace this key with the successor's key
                T.right = delete(T.right, successorV);      // delete the old successorV
            }
        }
        if(T != null) {
            T.updateInfo();
            T = balance(T);  // need to balance since its AVL
        }
        return T;  // return the updated BST
    }

    public AVLVertex leftRotate(AVLVertex T) {
        if(T == null) {
            return null;
        }
        AVLVertex w = T.right;
        w.parent = T.parent;
        T.parent = w;
        T.right = w.left;
        if (w.left != null) {
            w.left.parent = T;
        }
        w.left = T; // Update the height of T and then w

        T.updateInfo();
        w.updateInfo();
        return w;
    }

    //mirror of the leftRotate
    public AVLVertex rightRotate(AVLVertex T) {
        if(T == null) {
            return null;
        }
        AVLVertex w = T.left;
        w.parent = T.parent;
        T.parent = w;
        T.left = w.right;
        if (w.right != null) {
            w.right.parent = T;
        }
        w.right = T; // Update the height of T and then w
        T.updateInfo();
        w.updateInfo();
        return w;
    }

    public int getBalanceFactor(AVLVertex T) {
        if(T == null) {
            return 0;
        } else {
            int leftHeight = -1;
            int rightHeight = -1;
            if(T.left != null) { //must check for null in case no left child
                leftHeight = T.left.height;
            } // LOGIC ERROR HERE (ELSEIF WILL NOT TRIGGER IF "IF" TRIGGERS) - SPLIT THE CONDITIONS
            if(T.right != null) { //must check for null in case no right child
                rightHeight = T.right.height;
            }
            return leftHeight - rightHeight;
        }
    }

    public AVLVertex balance(AVLVertex T){
        int balanceFactor = getBalanceFactor(T);
        if(balanceFactor == 2) { //left something case
            if(getBalanceFactor(T.left) == -1) { //left right case
                T.left = leftRotate(T.left);
            }
            T = rightRotate(T); //left left case and left right case both need to rightRotate(T)
        } else if(balanceFactor == -2) { //right something case
            if(getBalanceFactor(T.right) == 1) { //right left case
                T.right = rightRotate(T.right);
            }
            T = leftRotate(T); //right right case and right left case both need to leftRotate(T)
        }
        return T;
    }

    //the rank of the team would be : total team - number of team lower ranked then this team + 1
    public int getRank(Team team) {
        return rank(this.root, team);
    }

    protected int rank(AVLVertex T, Team team) {
        int leftSize = 0;
        if(T.key.compareTo(team) < 0) { // team is on the right
            if(T.left != null) { //need to check null
                leftSize = T.left.size;
            }
            return rank(T.right, team) + leftSize + 1;
        } else if(T.key.compareTo(team) > 0) { //team is on the left
            return rank(T.left, team);
        } else { // team is equals
            if(T.left != null) { //need to check null
                leftSize = T.left.size;
            }
            return leftSize + 1;
        }
    }
}