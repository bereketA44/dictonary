import java.io.*;


public class loader {
    avl_Tree loaderAVL;
    StringBuilder wordList;

    void mainLoader(boolean stat){
        this. loaderAVL = new avl_Tree();
        fileHandler loadFile = new fileHandler();
        this. wordList = new StringBuilder();


        File file = new File("Dictionary.abn");
//        if(file.exists())
//        {
//            System.out.println(file.length());
//        }
//        else
//        {
//            System.out.println("error");
//        }

        long fileNum = (file.length()/630);
        System.out.println(fileNum);
        int memoManager=1;
        long seek =570;
        for (int i = 0; i < fileNum; i++) {
            wordInfo word = loadFile.reader_load(seek);
            memoManager= setWordList(word);
            if (memoManager !=0)
            loaderAVL.root= loaderAVL.insertNode(loaderAVL.root, String.valueOf(word.word).trim(), seek);
            seek +=630;

        }

  //      loaderAVL.inorder(loaderAVL.root);

        dictGUI run = new dictGUI(loaderAVL, wordList, stat);
        loaderAVL.checkRoot(loaderAVL.root);
    }

    int setWordList (wordInfo word ){
        final String filename = "RECYCLE_BIN.BIN";
        String garbage = new String();
        File file = new File (filename);
        long fileNum = (file.length()/87);
        String [] check  = new String[(int) fileNum];
        try {
            RandomAccessFile read = new RandomAccessFile(filename, "rw"); //27 //114
            for (int i = 0; i < fileNum; i++) {
                int x = 27;
                read.skipBytes(x);
                check [i]="";
                for (int j = 0; j < 30; j++) {
                    garbage = String.valueOf((char) (read.readByte()));
                    check [i]+= String.valueOf((char) (read.readByte()));
                }
                x += 87;
            }
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        } catch (IOException e) {
            System.out.println("Error file");
        }
            if (String.valueOf(word.word).trim().equalsIgnoreCase("")) {
                wordList.append("");
                return 1;
            }

            else {
                boolean go = true;
                for (int i = 0; i <fileNum ; i++) {
                    if (String.valueOf(word.word).trim().equalsIgnoreCase(check[i].trim()))
                    {
                        go = false;
                        fileNum=i;
                        return 0;
                    }
                }
                if (go) {
                    wordList.append("\n \uF0E8").append(String.valueOf(word.word).trim());
                    return 1;
                }
            }
            return 1;
    }

    boolean setPosList (long pos){

        final String filename = "POS_BIN.BIN";
        String garbage = new String();
        File file = new File (filename);
        long fileNum = (file.length()/87);
        String [] check  = new String[(int) fileNum];
        try {
            RandomAccessFile read = new RandomAccessFile(filename, "rw"); //27 //114
            for (int i = 0; i < fileNum; i++) {
                int x = 27;
                read.skipBytes(x);
                check [i]="";
                for (int j = 0; j < 30; j++) {
                    garbage = String.valueOf((char) (read.readByte()));
                    check [i]+= String.valueOf((char) (read.readByte()));
                }
                x += 87;
            }
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        } catch (IOException e) {
            System.out.println("Error file");
        }

        boolean go = true;
        for (int i = 0; i <fileNum ; i++) {
            if (String.valueOf(pos).trim().equalsIgnoreCase(check[i].trim()))
            {
                go = false;
                fileNum=i;
                return go;
            }
        }
        return go;
    }

    public static void main (String [] args) {
        loader load = new loader();
        load.mainLoader(true);
    }


}
