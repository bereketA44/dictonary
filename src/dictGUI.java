import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class dictGUI {
        private JFrame mainFrame = new JFrame();
        private Dimension di = new Dimension(1200, 700);
        private JPanel searchpan = new JPanel();
        private JPanel addpan = new JPanel();

        loader load;
        avl_Tree loaderAVL;
        StringBuilder wordList;

    dictGUI(avl_Tree loader, StringBuilder wordlist, boolean stat) {
        mainFrame.getContentPane();
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                JOptionPane pane = new JOptionPane();
                Object[] o = {"yes", " No"};
                int check=pane.showOptionDialog(null,"Are You Sure You Want To Exit? ", "EXIT?", 2, 2, null,o, o[1] );
                if (check==0){
                 //   mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                    mainFrame.dispose();}

            }
        });
        mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        mainFrame.setBackground(Color.white);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setSize(di);
        mainFrame.setTitle("Dictionary Application by Bereket");
        mainFrame.setVisible(true);
        menuBar();
        itemsGUI();
        search_interface_GUI("Search Here");
        loaderAVL=loader;
        this.wordList=wordlist;
        if (!stat)
            add_word_interface_GUI();
    }

    private void menuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.GREEN);
        JMenu menu1 = new JMenu("Dictionary Options");

        JMenuItem menuItem = new JMenuItem("Add Word  ");
        menuItem.setIcon(getImage("\\icons\\add.png"));
        menuItem.setAccelerator(KeyStroke.getKeyStroke("F5"));
        menu1.add(menuItem);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addpan.removeAll();
                mainFrame.setSize(new Dimension(1200,700));
                add_word_interface_GUI();
            }
        });

        /* ************************************** */

        menuItem = new JMenuItem("Search Word  ");
        menuItem.setIcon(getImage("\\icons\\search.gif"));
        menuItem.setAccelerator(KeyStroke.getKeyStroke("F6"));
        menu1.add(menuItem);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchpan.removeAll();
                mainFrame.setSize(new Dimension(1200,700));
                search_interface_GUI("Search Here");
            }
        });

        /* ************************************** */

        menuItem = new JMenuItem("List Word  ");
        menuItem.setIcon(getImage("\\icons\\list1.gif"));
        menuItem.setAccelerator(KeyStroke.getKeyStroke("F7"));
        menu1.add(menuItem);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rows = String.valueOf(wordList);
                JFrame listFrme;
                listFrme = new JFrame("Word List");
                listFrme.getContentPane();
                listFrme.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                listFrme.setPreferredSize(new Dimension(200, 400));


                JTextArea wordstable = new JTextArea (" Words Available \n" + rows);
                JScrollPane sp = new JScrollPane(wordstable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                listFrme.add(sp);
                listFrme.setVisible(true);
                listFrme.setVisible(true);
                listFrme.pack();

            }
        });

        /* ************************************** */

        menuItem = new JMenuItem("Exit Application  ");
        menuItem.setIcon(getImage("\\icons\\exit.png"));
        menuItem.setAccelerator(KeyStroke.getKeyStroke("F12"));
        menu1.add(menuItem);
        JMenuItem finalMenuItem1 = menuItem;
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = new JOptionPane();
                Object[] o = {"yes", " No"};
                int check=pane.showOptionDialog(finalMenuItem1,"Are You Sure You Want To Exit? ", "EXIT?", 2, 2, null,o, o[1] );
                if (check==0)
                    mainFrame.dispose();
            }
        });

        /* ************************************** */


        JMenu menu2 = new JMenu("Dictionary Documents");
        menuItem = new JMenuItem("Java Code Documentation ");
        menuItem.setIcon(getImage("\\icons\\java.GIF"));
        menuItem.setAccelerator(KeyStroke.getKeyStroke("F11"));
        menu2.add(menuItem);
        JMenuItem finalMenuItem = menuItem;
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String directory = System.getProperty("user.dir");
                try {
                    File file = new File(directory + "\\src\\Documents\\Java Files\\avl_Tree.java.html");
                    Desktop.getDesktop().open(file);
                     file = new File(directory + "\\src\\Documents\\Java Files\\dictGUI.java.html");
                    Desktop.getDesktop().open(file);
                     file = new File(directory + "\\src\\Documents\\Java Files\\fileHandler.java.html");
                    Desktop.getDesktop().open(file);
                     file = new File(directory + "\\src\\Documents\\Java Files\\loader.java.html");
                    Desktop.getDesktop().open(file);
                     file = new File(directory + "\\src\\Documents\\Java Files\\nodeLink.java.html");
                    Desktop.getDesktop().open(file);
                }catch (Exception errs)
                {
                    System.out.println(" Error opening file ");
                    JOptionPane.showMessageDialog(finalMenuItem, "File Not Found!","Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JMenu menu3 = new JMenu("Window");
        menuItem = new JMenuItem("Maximaize ");
        menuItem.setAccelerator(KeyStroke.getKeyStroke("ALT + m"));
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setExtendedState(mainFrame.getExtendedState() | mainFrame.MAXIMIZED_BOTH);
            }
        });
        menu3.add(menuItem);
        menuItem = new JMenuItem("Minimize ");
        menuItem.setAccelerator(KeyStroke.getKeyStroke("ALT + n"));
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setExtendedState(mainFrame.ICONIFIED);
            }
        });
        menu3.add(menuItem);
        menuItem = new JMenuItem("Exit ");
        menuItem.setIcon(getImage("\\icons\\exit.png"));
        menuItem.setAccelerator(KeyStroke.getKeyStroke("F12"));
        JMenuItem finalMenuItem2 = menuItem;
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = new JOptionPane();
                Object[] o = {"yes", " No"};
                int check=pane.showOptionDialog(finalMenuItem2,"Are You Sure You Want To Exit? ", "EXIT?", 2, 2, null,o, o[1] );
                if (check==0)
                mainFrame.dispose();
            }
        });
        menu3.add(menuItem);

        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        mainFrame.setJMenuBar(menuBar);
        mainFrame.setVisible(true);
    }

    public ImageIcon getImage(String filename) {
        try {
            return new ImageIcon(
                    this.getClass().getResource(filename));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    private void itemsGUI(){
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2, 0));
        panel1.setPreferredSize(new Dimension(680,100));


        JPanel panel2 = new JPanel();
        JLabel label = new JLabel();
        Font f = new Font("Footlight MT Light", Font.BOLD, 32);
        panel2.setBackground(Color.lightGray);
        label.setFont(f);
        label.setText("                               ENGLISH DICTIONARY APPLICATION                               ");
        label.setFont(label.getFont().deriveFont(32.0f));
        panel2.add(label);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(0, 5));
      //  panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        Dimension di= new Dimension(400,100);
        panel3.setPreferredSize(di);
        panel3.setBackground(Color.LIGHT_GRAY);


        JButton addWord = new JButton("Add word");
        JButton searchALL = new JButton("Search word");
        JButton searchprefix = new JButton("*Prefix");
        searchprefix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                all_fix_srch(0);
            }
        });
        JButton searchinfix = new JButton("Infix/postFix Search");
        searchinfix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                all_fix_srch(1);
            }
        });
//        JButton searchpostfix = new JButton("Postfix* Search");
//        searchpostfix.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                all_fix_srch(2);
//            }
//        });
        JButton list = new JButton("List all words");

        addWord.setIcon(getImage("\\icons\\add1.png"));
        addWord.setBorderPainted(true);
        addWord.setBackground(Color.cyan);
        addWord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addpan.removeAll();
                mainFrame.setSize(new Dimension(1200,700));
                add_word_interface_GUI();
            }
        });
        panel3.add(addWord);

        searchALL.setIcon(getImage("\\icons\\search1.gif"));
        searchALL.setBackground(Color.cyan);
        searchALL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchpan.removeAll();
                mainFrame.setSize(new Dimension(1200,700));
                search_interface_GUI("Search Here");
            }
        });
        panel3.add(searchALL);

        searchprefix.setIcon(getImage("\\icons\\search1.gif"));
        panel3.add(searchprefix);

        searchinfix.setIcon(getImage("\\icons\\search1.gif"));
        panel3.add(searchinfix);

//        searchpostfix.setIcon(getImage("\\icons\\search1.gif"));
//        panel3.add(searchpostfix);

        list.setIcon(getImage("\\icons\\list.gif"));
        JScrollPane sp = new JScrollPane();
        list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rows = String.valueOf(wordList);
                JFrame listFrme;
                listFrme = new JFrame("Word List");
                listFrme.getContentPane();
                listFrme.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                listFrme.setPreferredSize(new Dimension(200, 400));


                JTextArea wordstable = new JTextArea (" Words Available \n" + rows);
                JScrollPane sp = new JScrollPane(wordstable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

                listFrme.add(sp);
                listFrme.setVisible(true);
                listFrme.setVisible(true);
                listFrme.pack();

            }
        });
        panel3.add(list);

        panel1.add(panel2);
        panel1.add(panel3);
        mainFrame.add(panel1, "North");
      //  mainFrame.pack();
        mainFrame.setVisible(true);
    }

    long [] list_meanings = null;
    wordInfo info_meaning = new wordInfo();

    void search_interface_GUI(String defaultstr){

        mainFrame.remove(addpan);
        mainFrame.setVisible(true);
        searchpan.setBackground(Color.lightGray);
        searchpan.setLayout(new GridLayout(0,2));
        searchpan.setSize(1200,500);

        JPanel search = new JPanel();
        search.setLayout(new FlowLayout(1));
        JTextField entry = new JTextField(40);
        entry.setText(defaultstr);
        entry.setPreferredSize(new Dimension(15,45));
        entry.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                entry.setText("");
            }
        });
        JButton searchbtn = new JButton("Go ");
        searchbtn.setIcon(getImage("\\icons\\search2.png"));

        JPanel display = new JPanel();
        display.setLayout(new BorderLayout());

        JPanel d1= new JPanel();
        d1.setLayout(new FlowLayout(FlowLayout.LEFT));
        d1.setBackground(Color.LIGHT_GRAY);
        JPanel d2= new JPanel();
        d2.setLayout(new FlowLayout(FlowLayout.LEFT));
        d2.add(new JLabel("Meaning: - "));
        d2.setBackground(Color.lightGray);

        JTextArea meaning = new JTextArea("Meaning is shown here ");
        meaning.setBackground(Color.white);
        meaning.setColumns(30);
        meaning.setRows(8);
        JScrollPane sp = new JScrollPane(meaning, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JLabel pron = new JLabel("pronunciation :- ");
        //.....AL
        JLabel type = new JLabel("Type :- ");
        //.....AL


        JTextField typeText = new JTextField("Type is shown here", 15);


        JTextField pronText = new JTextField("Pronunciation is shown here", 20);


        searchbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wordInfo myWord = new wordInfo();
                wordInfo myWord1 = new wordInfo();
                String check = entry.getText();
                if (entry.getText() == null || ( entry.getText().equalsIgnoreCase("Search here"))) {
                    JOptionPane.showMessageDialog( searchbtn, "Entry empty Please enter a word!","Error", JOptionPane.ERROR_MESSAGE);

                } else {
                    try {
                        myWord = loaderAVL.search(loaderAVL.root, (entry.getText().trim()), true);

                    } catch (Exception err) {
                        JOptionPane.showMessageDialog( searchbtn, "Word not found :-( ","Error", JOptionPane.ERROR_MESSAGE);
                        String set = infix_suggstion(entry.getText());
                        entry.setText(set);
                        typeText.setText("");
                        pronText.setText("");
                        meaning.setText("");
                    }
                    try {
                        meaning.setText("");
                        meaning.setText("**** " + (entry.getText().trim())+ " **** \n ______________________________\n" + String.valueOf(myWord.meaning).trim());
                    }catch (Exception ex)
                    {
                        meaning.setText("");
                   //     if((loaderAVL.root.list.positionCount()).length >1);
                        meaning.setText("**** " + (entry.getText().trim())+ " ****\n ______________________________\n"  + String.valueOf(loaderAVL.builder));

                    }
                    loaderAVL.builder=new StringBuilder();
                    try {

                        typeText.setText(String.valueOf(myWord.type).trim());
                        pronText.setText(String.valueOf(myWord.pronunciation).trim());
                    }catch (Exception exx)
                    {
                        myWord1=loaderAVL.search(loaderAVL.root, entry.getText().trim(), false);
                        if (myWord1!=null) {
                        typeText.setText(String.valueOf(myWord1.type).trim());
                        pronText.setText(String.valueOf(myWord1.pronunciation).trim());
                      }
                      else {
                            JOptionPane.showMessageDialog(searchbtn, "Word not found :-( ", "Error", JOptionPane.ERROR_MESSAGE);
                            String set = infix_suggstion(entry.getText());
                            entry.setText(set);
                            typeText.setText("");
                            pronText.setText("");
                            meaning.setText("");
                        }
                    }
                try {
                    list_meanings = myWord1.poosiitiion.positionCount();
                    info_meaning = myWord1;
                }catch (Exception k)
                    {
                        try {
                            list_meanings = myWord.poosiitiion.positionCount();
                        }catch (Exception x)
                        {
                            System.out.println("error word not found @ 411");
                        }
                        info_meaning = myWord;
                        System.out.println("error word not found @ 411 ");
                    }
                    meaning.setFont(meaning.getFont().deriveFont(14.0f));
                }
            }
        });

        JButton delete = new JButton("Delete Word");
        delete.setPreferredSize(new Dimension(152,32));
        delete.setIcon(getImage("\\icons\\Delete .png"));

        /* *********************** Delete From File ************************************************ */

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (entry.getText().equals("") || entry.getText().equals("Search here"))
                        JOptionPane.showMessageDialog(searchbtn, "Field Empty Please Enter A word ", "Error", JOptionPane.ERROR_MESSAGE);
                    else{
                    loaderAVL.deleteNode(loaderAVL.root, entry.getText());



                     System.out.println("lenfth " + entry.getText().length());
                     String str = entry.getText();
                     int j=0;
                     for (int i = 0; i < wordList.length(); i++) {
                            int k = wordList.indexOf(str);
                            if (k !=-1)
                            wordList.delete(k, k+str.length());
                        }
                        FileOutputStream writeO = null;
                        ObjectOutputStream out = null;
                        final String filename = "RECYCLE_BIN.BIN";
                        fileHandler converter = new fileHandler();
                        wordInfo toDelete = new wordInfo();
                        try {
                            writeO = new FileOutputStream(filename, true);
                            out = new ObjectOutputStream(writeO);

                            toDelete.word=converter.convert_to_char(entry.getText().trim(),toDelete.word);

                            out.writeObject(toDelete.word);
                            out.flush();
                            out.close();
                            writeO.close();
                        } catch (FileNotFoundException edell) {
                            System.out.println(" FILE NOT FOUND ON WRITE");
                        } catch (IOException eDel) {
                            eDel.printStackTrace();
                        }
                    JOptionPane.showMessageDialog(searchbtn, "Successfully deleted ", "Deleted", JOptionPane.INFORMATION_MESSAGE);
                    entry.setText("");
                    meaning.setText("");
                    type.setText("");}
                }catch (Exception ee)
                {
                    JOptionPane.showMessageDialog(searchbtn, "Word not found or Already deleted ", "Error", JOptionPane.ERROR_MESSAGE);
                    entry.setText("Search here");
                    typeText.setText("");
                    pronText.setText("");
                    meaning.setText("");

                }

            }
        });


        /* **********************************************************  DEL MEANING     ************************************************ */
            JButton delmeaning = new JButton("Delete Meaning");
            delmeaning.setIcon(getImage("\\icons\\Delete 1.png"));
            delmeaning.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame delFrame = new JFrame("Delete meaning");
                    delFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            super.windowClosing(e);
                            mainFrame.dispose();
                            load= new loader();
                            load.mainLoader(true);
                        }
                    });
                    JPanel panel = new JPanel();
                    JLabel label=  new JLabel(" Choose a Meaning To Delete");
                    JButton del = new JButton("Delete");
                    panel.setLayout(new FlowLayout());
                    delFrame.getContentPane();
                    delFrame.setSize(420, 300);
                    delFrame.setAlwaysOnTop(false);
                    delFrame.setLocation(600,100);

                try {
                    fileHandler file = new fileHandler();
                    String[] strArr = new String[list_meanings.length];
                    for (int i = 0; i < list_meanings.length; i++) {

                        strArr[i] = file.meaning_reader(list_meanings[i]);
                    }
                    JList list = new JList();
                    list.setListData(strArr);
                  //  list.remove(1);
                    list.setVisibleRowCount(8);
                    JScrollPane sp = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    sp.setPreferredSize(new Dimension(300, sp.getPreferredSize().height));

                    del.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                          //  System.out.println("+_+_+_+_+_+_+_+_ " + info_meaning.poosiitiion.display());
                          //  info_meaning.poosiitiion.display();

                            FileOutputStream writeO = null;
                            ObjectOutputStream out = null;
                            final String filename = "POS_BIN.BIN";
                            fileHandler converter = new fileHandler();
                            wordInfo toDelete = new wordInfo();
                            try {
                                writeO = new FileOutputStream(filename, true);
                                out = new ObjectOutputStream(writeO);
                                int index = list.getSelectedIndex();
                                toDelete.word= converter.convert_to_char(String.valueOf((list_meanings[index]-430)), toDelete.word);
                               // info_meaning.poosiitiion.deleteNode(list_meanings[index]);
                                out.writeObject(toDelete.word);
                                out.flush();
                                out.close();
                                writeO.close();

                            } catch (FileNotFoundException edell) {
                                System.out.println(" FILE NOT FOUND ON WRITE");
                            } catch (IOException eDel) {
                                eDel.printStackTrace();
                            }

                            JOptionPane.showMessageDialog(searchbtn, "Successfully deleted ", "Deleted", JOptionPane.INFORMATION_MESSAGE);

                        }
                    });

                    panel.add(label, FlowLayout.LEFT);
                    panel.add(sp, FlowLayout.CENTER);
                    panel.add(del);
                    delFrame.add(panel);
                    delFrame.setVisible(true);

                }catch (Exception we){
                    JOptionPane.showMessageDialog(searchbtn, "Empty Field, Please Enter a word to search for", "Error", JOptionPane.ERROR_MESSAGE);
                }
                }
            });


        /* ****************************************************************************              ************************************************ */
        search.add(entry);
        search.add(searchbtn);
        d2.add(sp);
     //   d2.add(meaning);
        d1.add(type);
        d1.add(typeText);
        d1.add(pron);
        d1.add(pronText);

        JPanel forDel = new JPanel();
        forDel.setBackground(Color.lightGray);
        forDel.setPreferredSize(new Dimension(150, 700));
        forDel.setLayout(new FlowLayout());
        forDel.add(delete);
        forDel.add(delmeaning);
      //  display.add(sp);

        display.add(d1,BorderLayout.NORTH);
        display.add(d2, BorderLayout.CENTER);
        display.add(forDel, BorderLayout.EAST);
        searchpan.add(search);
        searchpan.add(display);
        mainFrame.add(searchpan, "Center");
        mainFrame.setVisible(true);
    }


    void add_word_interface_GUI(){

        mainFrame.remove(searchpan);
        mainFrame.setVisible(true);
        addpan.setPreferredSize(new Dimension(1200, 400));

        JTextField getWord = new JTextField(30);
        getWord.setPreferredSize(new Dimension(15,30));

        JTextField gettypee = new JTextField(30);
        gettypee.setPreferredSize(new Dimension(15,30));

        JTextField getpron = new JTextField(30);
        getpron.setPreferredSize(new Dimension(15,30));

        JTextArea getMeaning = new JTextArea();
        getMeaning.setRows(4);
        getMeaning.setColumns(32);
        JScrollPane sp = new JScrollPane(getMeaning, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        JButton addbtn = new JButton("Add Word");

        addpan.setLayout(new GridLayout(5, 0));
        JPanel pan1, pan2, pan3, pan4, pan5;

        pan1= new JPanel();
        pan1.setSize(1200,100);
        pan1.setLayout(new FlowLayout());
        pan1.add(new JLabel("Enter word           "), FlowLayout.LEFT);
        pan1.add(getWord, FlowLayout.CENTER);
        addpan.add(pan1);

        pan2= new JPanel();
        pan2.setLayout(new FlowLayout());
        pan2.add(new JLabel("Enter Type           "), FlowLayout.LEFT);
        pan2.add(gettypee, FlowLayout.CENTER);
        addpan.add(pan2);

        pan3= new JPanel();
        pan3.setLayout(new FlowLayout());
        pan3.add(new JLabel("Enter Meaning           "), FlowLayout.LEFT);
        pan3.add(sp, FlowLayout.CENTER);
        addpan.add(pan3);

        pan4= new JPanel();
        pan4.setLayout(new FlowLayout());
        pan4.add(new JLabel("pronunciation      "), FlowLayout.LEFT);
        pan4.add(getpron, FlowLayout.CENTER);
        addpan.add(pan4);

        pan5= new JPanel();
        pan5.setLayout(new FlowLayout());
        pan5.add(addbtn);
        addpan.add(pan5);

        addbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String [] send = new String[4];

                if (getWord.getText().equalsIgnoreCase(""))
                {
                    JOptionPane.showMessageDialog( addbtn, "Field Empty","Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                send [0]= (getWord.getText().trim());
                send [1]= (gettypee.getText().trim());
                send [2]= (getpron.getText().trim());
                send [3]= (getMeaning.getText().trim());


                fileHandler file = new fileHandler();
                boolean check= file.writer(send);
                if (check)
                    JOptionPane.showMessageDialog( addbtn, "Added Word Successfully!","Add Word", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog( addbtn, "Adding Word Failed File Error!","Add Word", JOptionPane.ERROR_MESSAGE);
                int seek =570;

                getWord.setText("");
                gettypee.setText("");
                getpron.setText("");
                getMeaning.setText("");
                mainFrame.dispose();
                load= new loader();
                load.mainLoader(false);

            }
        });
        mainFrame.add(addpan);
        mainFrame.setVisible(true);

    }

    String[] set = new String[1];
    String infix_suggstion(String str)
    {
        JFrame sugFrame = new JFrame("Suggested Words");
        JPanel panel = new JPanel();
        JLabel label=  new JLabel(" Choose a Word To Search");
        JButton choose = new JButton("Choose");
        panel.setLayout(new FlowLayout());
        sugFrame.getContentPane();
        sugFrame.setSize(420, 300);
        sugFrame.setAlwaysOnTop(false);
        sugFrame.setLocation(600,100);
        JList list = new JList();

        String [] strArr;
        //  list.remove(1);
        list.setVisibleRowCount(8);
        JScrollPane sp = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setPreferredSize(new Dimension(300, sp.getPreferredSize().height));
        strArr = loaderAVL.infix(loaderAVL.root, str);

        list.setListData(strArr);
        list.setSelectedIndex(0);
        choose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                set[0]= strArr[index];
                searchpan.removeAll();
                mainFrame.setSize(new Dimension(1200,700));
                search_interface_GUI(set[0]);
                mainFrame.setVisible(true);
                sugFrame.dispose();
            }
        });

        panel.add(label, FlowLayout.LEFT);
        panel.add(sp, FlowLayout.CENTER);
        panel.add(choose);
        sugFrame.add(panel);
        sugFrame.setVisible(true);
        return "";

    }
    String [] strArr;
    String all_fix_srch (int caSe)
    {
        JFrame frame = new JFrame("infix search");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        frame.setSize(420, 600);
        frame.setAlwaysOnTop(false);
        frame.setLocation(600,100);
        JTextField entry = new JTextField(20);
        entry.setPreferredSize(new Dimension(15,45));
        JButton searchbtn = new JButton("Go ");
        searchbtn.setIcon(getImage("\\icons\\search2.png"));
        JButton go = new JButton("Search");

        int c=0;
        for (int i = 0; i <wordList.length() ; i++) {
            if (wordList.charAt(i) == '\n')
                c++;
        }
        System.out.println("len " + c);

        strArr = new String[c];
        int j=0;
        for (int i = 0; i <strArr.length ; i++) {

            strArr[i]="";
            while (wordList.charAt(j)!= '\n')// && wordList.charAt(j)!= '\uF0E8' )
            {
                if (wordList.charAt(j)!= '\uF0E8' && wordList.charAt(j)!= ' ')
                strArr[i] += wordList.charAt(j);
                j++;
            }
            j+=1;
        }
        Set<String>set =new HashSet<String>(Arrays.asList(strArr));
        strArr=set.toArray(new String[set.size()]);

//        for (int i = 0; i <strArr.length ; i++) {
//            System.out.println("+++++++ " + strArr[i]);
//        }


        if (caSe == 0) {
            final String[] word = new String[1];
            frame.setTitle("PreFix Search");
            searchbtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String s ="";

                    for (int i = 0; i <2 ; i++) {
                        s+=(entry.getText().charAt(i));
                    }
                 //   System.out.println(" entey  ++ " + entry.getText());
                    word [0]=s;
                 //   System.out.println(" word  ++ " + word[0].charAt(0));

                    JList list = new JList();

                    list.setVisibleRowCount(8);
                    JScrollPane sp = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                    sp.setPreferredSize(new Dimension(300, sp.getPreferredSize().height));

                    String [] getString = new String[strArr.length];
                try {
                    for (int i = 1; i < strArr.length; i++) {
                        if (String.valueOf(word[0].charAt(0)).equalsIgnoreCase(String.valueOf(strArr[i].charAt(0)) ))// && strArr[i]!=null)
                            if (word[0].charAt(1) == strArr[i].charAt(1))
                                getString[i - 1] = strArr[i];
                    }
                }catch (Exception ew)
                {
                    JOptionPane.showMessageDialog( null, "Word Not Found","Add Word", JOptionPane.INFORMATION_MESSAGE);
                }
                    go.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int index = list.getSelectedIndex();
                            String s= getString[index];
                            searchpan.removeAll();
                            mainFrame.setSize(new Dimension(1200,700));
                            search_interface_GUI(s);
                            mainFrame.setVisible(true);
                            frame.dispose();
                        }
                    });
                    list.setListData(getString);
                    panel.add(sp);
                    panel.add(go);
                    frame.setVisible(true);
                }
            });

       }




        if (caSe == 1) {
            frame.setTitle("InFix Search");
                final String[] word = new String[1];

                searchbtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String s ="";
                        int len=entry.getText().length();
                        for (int i = 0; i <len ; i++) {
                            s+=(entry.getText().charAt(i));
                        }
                        System.out.println(" entey  ++ " + entry.getText());
                        word [0]=s;
                        System.out.println(" word  ++ " + word[0].charAt(0));

                        JList list = new JList();

                        list.setVisibleRowCount(8);
                        JScrollPane sp = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                        sp.setPreferredSize(new Dimension(300, sp.getPreferredSize().height));

                        String [] getString = new String[strArr.length];
                        for (int i = 1; i <strArr.length ; i++) {
                            //if ((word[0].charAt(0) == strArr[i].indexOf(word][0])))// && strArr[i]!=null)
                                if (strArr[i].indexOf(word[0] )>= 0)
                                    getString[i-1] = strArr[i];
                        }
                        go.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int index = list.getSelectedIndex();
                                String s= getString[index];
                                searchpan.removeAll();
                                mainFrame.setSize(new Dimension(1200,700));
                                search_interface_GUI(s);
                                mainFrame.setVisible(true);
                                frame.dispose();
                            }
                        });
                        list.setListData(getString);
                        panel.add(sp);
                        panel.add(go);
                        frame.setVisible(true);
                    }
                });

            }

        panel.add(entry);
        panel.add(searchbtn);
        frame.add(panel);
        frame.setVisible(true);

return "a";
    }



}

