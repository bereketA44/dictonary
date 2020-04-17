class nodeAVL {
    String word;
    nodeLink list = new nodeLink();
    int height;
    nodeAVL left;
    nodeAVL right;
    nodeAVL(){}
    nodeAVL(String str, long pos) {
        this.word = str;
        this.list.addNode(pos);
        left = null;
        right = null;
        height =0;
    }
}

class avl_Tree {
    nodeAVL root;
    loader load = new loader();
    StringBuilder builder = new StringBuilder();
    private int heightAVL(nodeAVL node) {
        if (node == null)
            return 0;
        return node.height;
    }

    int compareHeight(int node1, int node2) {
        return (node1>node2)? node1:node2;
    }
    private int getBalance(nodeAVL root) {
        if (root == null)
            return 0;
        return heightAVL(root.left)- heightAVL(root.right);
    }


    private nodeAVL rightRotator(nodeAVL root) {
        nodeAVL newROOT =root.left;
        nodeAVL temp2=newROOT.right;

        newROOT.right=root;
        root.left= temp2;

        root.height = compareHeight(heightAVL(root.left), heightAVL(root.right)) + 1 ;
        newROOT.height = compareHeight(heightAVL(newROOT.left), heightAVL(newROOT.right))+ 1;

        return newROOT;
    }

    private nodeAVL leftRotator(nodeAVL root) {
        nodeAVL newROOT =root.right;
        nodeAVL temp2=newROOT.left;

        newROOT.left=root;
        root.right= temp2;

        root.height = compareHeight(heightAVL(root.left), heightAVL(root.right)) + 1 ;
        newROOT.height = compareHeight(heightAVL(newROOT.left), heightAVL(newROOT.right))+ 1;

        return newROOT;
    }


    nodeAVL insertNode(nodeAVL root, String word, long pos) {
        if (root == null)
            return (new nodeAVL(word, pos));
        if (word.compareToIgnoreCase(root.word) <= -1)
            root.left = insertNode(root.left, word, pos);
        else if (word.compareToIgnoreCase(root.word) >= 1)
            root.right=insertNode(root.right, word, pos);
        else if (word.equalsIgnoreCase(root.word)) {
            if (load.setPosList(pos-430) ==true)
            {
                root.list.addNode(pos);

            }
        }

        root.height =  1 + compareHeight(heightAVL(root.left), heightAVL(root.right));

        int balance= getBalance(root);

        if(balance > 1 && (word.compareToIgnoreCase(root.left.word) <= -1) )
            return rightRotator(root);
        if(balance < -1 && (word.compareToIgnoreCase(root.right.word) >= 1) )
            return leftRotator(root);

        if(balance > 1 && (word.compareToIgnoreCase(root.left.word) >= 1) ){
            root.left = leftRotator(root.left);
            return rightRotator(root);
        }
        if(balance < -1 && (word.compareToIgnoreCase(root.right.word) <= -1) ){
            root.right = rightRotator(root.right);
            return leftRotator(root);
        }

        return root;

    }
    nodeAVL minn(nodeAVL node)
    {
        nodeAVL trav = node;

        while (trav.left != null)
            trav = trav.left;

        return trav;
    }


    nodeAVL deleteNode (nodeAVL root, String word)
    {
        if (root == null)
            return root;

        if (word.compareToIgnoreCase(root.word)<=-1)
            return deleteNode(root.left, word);
        else if (word.compareToIgnoreCase(root.word)>=1)
            return deleteNode(root.right, word);
        else if (word.compareToIgnoreCase(root.word)==0)
        {
            if ((root.left==null)|| (root.right== null))
            {
                nodeAVL temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                if (temp == null)
                {
                    temp=root;
                    root.word=null;
                    root=null;

                }
                else {
                    root.word=null;
                    root=null;
                    root=temp;
                }

            }
            else
            {
                nodeAVL temp=minn(root.right);

                root.word=temp.word;

                root.right=deleteNode(root.right,temp.word);


            }
        }
        if (root ==null)
            return root;
        root.height=compareHeight(heightAVL(root.left), heightAVL(root.right)) + 1 ;
        int balance= getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotator(root);

        if (balance > 1 && getBalance(root.left) < 0)
        {
            root.left = leftRotator(root.left);
            return rightRotator(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotator(root);

        if (balance < -1 && getBalance(root.right) > 0)
        {
            root.right = rightRotator(root.right);
            return leftRotator(root);
        }
        return root;
    }

    void inorder(nodeAVL root) {
        if (root !=null )
        {

            inorder(root.left);
            if (root.word !=null)
            System.out.println(root.word);
            inorder(root.right);
        }
    }

    /* to check the final main root */
    void checkRoot (nodeAVL root)
    {
        System.out.println( " The root is :" +  root.word);
    }


    wordInfo search (nodeAVL root, String word, boolean stat)
    {
        nodeLink link= new nodeLink();

        if (root.word== null)
            return null;
        if (word.compareToIgnoreCase(root.word)==0 ) {
            System.out.println("Word : " + word);
            long[] pos = root.list.positionCount();
         //       System.out.println("position : " + def_positions[0]);
            fileHandler file = new fileHandler();

            if (pos.length==1 && stat) {
                wordInfo myWord = file.reader_members(pos[0]);
                for (int i = 0; i <pos.length ; i++) {

                    link.addNode(pos[i]);
                }
                myWord.poosiitiion=link;

                System.out.println("myWord.word " + word);
                System.out.println("myWord.type " + String.valueOf(myWord.type).trim());
                System.out.println("myWord.pron " + String.valueOf(myWord.pronunciation).trim());
                System.out.println("myWord.meaning " + String.valueOf(myWord.meaning).trim());
                return myWord;
            }
            else if (pos.length> 1 && !stat) {
                wordInfo myWord = file.reader_members(pos[0]);
                for (int i = 0; i <pos.length ; i++) {

                    link.addNode(pos[i]);
                }
                myWord.poosiitiion=link;

                System.out.println("myWord.word " + word);
                System.out.println("myWord.type " + String.valueOf(myWord.type).trim());
                System.out.println("myWord.pron " + String.valueOf(myWord.pronunciation).trim());
                System.out.println("myWord.meaning " + String.valueOf(myWord.meaning).trim());
                return myWord;
            }
             if (pos.length > 1 )
            {
                this.builder= new StringBuilder();
                String str;
                for (int i = 0; i < pos.length; i++) {
                    str=file.meaning_reader(pos[i]);
                    builder.append("\n").append(str);
                }
            }
        }
        else if (word.compareToIgnoreCase(root.word)<=-1)
            return search(root.left, word, stat);
        else if (word.compareToIgnoreCase(root.word)>=1)
            return search(root.right,word, stat);
        return null;
    }
    String result = "";
    String [] str= null;

    String [] infix (nodeAVL root, String str)
    {
        String  s = searchChars(root, str);
        int c=0;
        for (int i = 0; i <s.length() ; i++) {
            if (s.charAt(i) == '\n')
                c++;
        }
        String [] strArr = new String[c];
        int j=0;
        for (int i = 0; i <strArr.length ; i++) {

            strArr[i]="";
            while (s.charAt(j)!= '\n' )
            {
                strArr[i] += s.charAt(j);
                j++;
            }
            j+=1;

        }

        for (int i = 0; i <strArr.length-1 ; i++) {
            for (int k = i+1; k <strArr.length ; k++) {
                if (strArr[i].compareToIgnoreCase(strArr[k]) >= -1) {
                    String temp = strArr[i];
                    strArr[i] = strArr[k];
                    strArr[k] = temp;
                }
            }
        }
        result = "";
        return  strArr;
    }


    String  searchChars (nodeAVL root, String str) {

     //  System.out.println(" result " + result);
        if (root== null) {
        //    System.out.println("ro null");
            return result;
        }
        if (String.valueOf(root.word.charAt(0)).equalsIgnoreCase(String.valueOf(str.charAt(0)))) {
         //   System.out.println( " c1" + root.word);
             result += root.word;
            result += "\n";

             searchChars(root.left, str);
             searchChars(root.right, str);
        }
        if (String.valueOf(root.word.charAt(0)).compareToIgnoreCase(String.valueOf(str.charAt(0))) <= -1  ) {
         //   System.out.println( " c2" + root.word);
             searchChars(root.right, str);
        }
        if (String.valueOf(root.word.charAt(0)).compareToIgnoreCase(String.valueOf(str.charAt(0))) >= 1 ) {
        //    System.out.println( " c3" + root.word);
             searchChars(root.left, str);
        }
        return result;

    }


}