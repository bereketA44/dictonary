import java.io.*;
import java.util.Arrays;


class wordInfo implements Serializable  {
    char[] word = new char[30];
    char[] type = new char[20];
    char[] pronunciation = new char[30];
    char[] meaning = new char[150];
    nodeLink poosiitiion;
}

public class fileHandler implements Serializable {

    char[] convert_to_char(String str, char[] file) {
        char[] temp = str.toCharArray();
        file = new char[file.length];
        file = Arrays.copyOf(temp, file.length);
        //    System.out.println("lenght of name " + file.length);
        return file;
    }

    /* *********************************************************************************************** **/
    FileInputStream readO = null;
    FileOutputStream writeO = null;
    ObjectOutputStream out = null;
    ObjectInputStream in = null;
    private final String filename = "Dictionary.abn";

    boolean writer(String[] str) {
        wordInfo myword = new wordInfo();
        myword.word = convert_to_char(str[0], myword.word);
        myword.type = convert_to_char(str[1], myword.type);
        myword.pronunciation = convert_to_char(str[2], myword.pronunciation);
        myword.meaning = convert_to_char(str[3], myword.meaning);
        try {
            writeO = new FileOutputStream(filename, true);
            out = new ObjectOutputStream(writeO);
            out.writeObject(myword);
            out.flush();
            out.close();
            writeO.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println(" FILE NOT FOUND ON WRITE");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    wordInfo reader_load(long pos) {
        wordInfo sendback = new wordInfo();
        StringBuilder wordbuild = new StringBuilder();
        String wordString = new String();
        try {
            RandomAccessFile read = new RandomAccessFile(filename, "rw");
            String garbage = "";
            int x = (int) pos;
            read.seek(x);
            for (int j = 0; j < 30; j++) {
                garbage = String.valueOf((char) read.readByte());
                wordbuild.append(String.valueOf((char) read.readByte()));
                //    garbage=String.valueOf((char)read.readByte());
                // file starts at maning:- 140 and jumps to 450 for pron 520 for type  and 571 (570 with no garbgee) for the word
                // next word add 630 to get meaning subtract 430
                //1st word is at 570 second word is at 1200 nd 3rd 1830 ....
            }
//
            read.close();
            wordString = String.valueOf(wordbuild);
            wordString = wordString.trim();

            //    System.out.println(meaning1);

        } catch (FileNotFoundException e) {
            System.out.println("error at read");
        } catch (IOException e) {
            System.out.println("error at cast");
        }

        sendback.word = convert_to_char(wordString, sendback.word);
        return sendback;
    }


    wordInfo reader_members(long pos) {
        wordInfo sendback = new wordInfo();
        StringBuilder meaningBuild = new StringBuilder();
        StringBuilder pronBuild = new StringBuilder();
        StringBuilder typeBuild = new StringBuilder();
        String wordString = new String();
        try {
            RandomAccessFile read = new RandomAccessFile(filename, "rw");
            String garbage = "";
            int x = (int) pos;
            read.seek(x - 430);
            for (int j = 0; j < 150; j++) {
                //
                meaningBuild.append(String.valueOf((char) read.readByte()));
                garbage = String.valueOf((char) read.readByte());

                // file starts at maning:- 140 and jumps to 450 for pron 520 for type  and 571 (570 with no garbgee) for the word
                // next word add 630 to get meaning subtract 430
                //1st word is at 570 second word is at 1200 nd 3rd 1830 ....
            }
            wordString = String.valueOf(meaningBuild);
            wordString = wordString.trim();
            sendback.meaning = convert_to_char(wordString, sendback.meaning);

            read.seek(x - 120);
            for (int j = 0; j < 30; j++) {
                garbage = String.valueOf((char) read.readByte());
                pronBuild.append(String.valueOf((char) read.readByte()));


                // file starts at maning:- 140 and jumps to 450 for pron 520 for type  and 571 (570 with no garbgee) for the word
                // next word add 630 to get meaning subtract 430
                //1st word is at 570 second word is at 1200 nd 3rd 1830 ....
            }
            wordString = String.valueOf(pronBuild);
            wordString = wordString.trim();
            sendback.pronunciation = convert_to_char(wordString, sendback.pronunciation);


            read.seek(x - 50);
            for (int j = 0; j < 30; j++) {
                garbage = String.valueOf((char) read.readByte());
                typeBuild.append(String.valueOf((char) read.readByte()));

                // file starts at maning:- 140 and jumps to 450 for pron 520 for type  and 571 (570 with no garbgee) for the word
                // next word add 630 to get meaning subtract 430
                //1st word is at 570 second word is at 1200 nd 3rd 1830 ....
            }
            wordString = String.valueOf(typeBuild);
            wordString = wordString.trim();
            sendback.type = convert_to_char(wordString, sendback.type);

            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("error at read");
        } catch (IOException e) {
            System.out.println("error at cast");
        }
        return sendback;
    }


    String meaning_reader(long pos) {
        wordInfo sendback = new wordInfo();
        StringBuilder meaningBuild = new StringBuilder();
        String wordString = new String();
      //  System.out.println("potions :- " + pos);
        try {
            RandomAccessFile read = new RandomAccessFile(filename, "rw");
            String garbage = "";
            int x = (int) pos;
            read.seek(x-430);
            for (int j = 0; j < 150; j++) {

                meaningBuild.append(String.valueOf((char) read.readByte()));
                garbage = String.valueOf((char) read.readByte());
            }
            wordString = String.valueOf(meaningBuild);
            wordString = wordString.trim();
            return wordString;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return " ERROR NO OTHER MEANING";
    }


//
}
