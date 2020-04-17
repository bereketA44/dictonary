class nodeLink {
     long position;
     nodeLink next;

    nodeLink() {}

    nodeLink (long position)
    {
        this.position=position;
        this.next=null;
    }
     nodeLink head = null;
     nodeLink tail= null;

    void addNode (long position)
    {
        nodeLink newNode= new nodeLink(position);

        if (head== null)
        {
            head= newNode;
            tail=newNode;
        }
        else
        {
            tail.next = newNode;
            tail=newNode;
        }
    }
    long[] positionCount ()
    {
        nodeLink newNode=head;
        nodeLink count=head;
        int counter=0;
        while (count!=null)
        {
            count = count.next;
            counter++;
        }

        long [] pos = new long[counter];
        int c=0;

        while (newNode != null)
        {
            pos[c] = newNode.position;
            newNode=newNode.next;
            c++;

        }

       return pos;
    }

//    nodeLink deleteNode (long pos)
//    {
//        nodeLink newNode=head;
//      //  nodeLink newnode1=head;
//        if (head==null)
//            return null;
//        if (head.position == pos)
//        {
//            head= head.next;
//        }
//        else {
//
//            while (newNode != null) {
//
//
//                if(newNode.position!=pos)
//
//                newNode = newNode.next;
//            }
//            if (newNode!=null)
//            {
//                newNode.next= newNode.next.next;
//            }
//        }
//
//return newNode;
//    }


    void display () {
        nodeLink newNode=head;
        //  nodeLink newnode1=head;
        if (head==null)
            System.out.println("empty ");

        else{
            while (newNode!=null)
            {
                System.out.println(newNode.position);
                newNode= newNode.next;
            }

        }

    }

}


