/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Girish
 */
import aarti.Trie;
//import static debwaste.bracket.isParenthesisMatch;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.util.Stack;
/**
 * @author David
 */
public class Test2 extends JFrame{
    static Stack<Character> stack = new Stack<Character>();
	static int pop_count = 0;
    public Test2() {
        
        

        JFrame frame = new JFrame("Wow Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
        try {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("res/doge.png")))));
        }
        catch(IOException e)
        {
            
        }
        */
      
        
         try {
                frame.setIconImage(ImageIO.read(new File("res/doge.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        
        Container c = frame.getContentPane();
                c.setBackground(Color.YELLOW);
                // adjust to need.
                Dimension d = new Dimension(500,700);
                c.setPreferredSize(d);
                frame.pack();
                frame.setResizable(false);
                frame.setVisible(true);

        JTextArea f = new JTextArea();
        
        ((AbstractDocument) f.getDocument()).setDocumentFilter(new DocumentFilter() {

            @Override
            public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                //string = string.replaceAll("\\{", "\\{\n      \n}");
                super.insertString(fb, offset, string, attr);
                //if(string.charAt(string.length()-1)== '}')
                //    f.setCaretPosition(f.getDocument().getLength()-2);
            }

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                //text = text.replaceAll("\\{", "\\{\n      \n}");
                //TODO must do something here
                super.replace(fb, offset, length, text, attrs);
                //if(text.charAt(text.length()-1)== '}')
                //    f.setCaretPosition(f.getDocument().getLength()-2);
            }
        });
       
        
        //f.setPreferredSize(new Dimension(300, 300));

        AutoSuggestor autoSuggestor = new AutoSuggestor(f, frame, null, Color.WHITE.brighter(), Color.BLUE, Color.RED, 0.75f) {
            @Override
            boolean wordTyped(String typedWord) {

                //create list for dictionary this in your case might be done via calling a method which queries db and returns results as arraylist
                ArrayList<String> words = new ArrayList<>();
                words.add("hello");
                words.add("heritage");
                words.add("happiness");
                words.add("goodbye");
                words.add("cruel");
                words.add("car");
                words.add("war");
                words.add("will");
                words.add("world");
                words.add("wall");


                setDictionary(words);
                //addToDictionary("bye");//adds a single word
                
                /*
                char c = typedWord.charAt(typedWord.length()-1);
        
        
                if(c=='{')
                {
                    f.append("\n");
                }
*/
                return super.wordTyped(typedWord);//now call super to check for any matches against newest dictionary
            }
        };

        JPanel p = new JPanel();
        
        JButton button = new JButton("Compile and Run");
        JButton New = new JButton("New");
        JButton Open = new JButton("Open");
        JButton Quit = new JButton("Quit");
        JButton Save = new JButton("Save");
        JButton Indent = new JButton("Indent");
        
        //button.setVisible(true);
        //frame.add(button);
        f.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane (f, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setViewportView(f);
        scroll.setAutoscrolls(true);
        scroll.setPreferredSize(new Dimension(400, 400));
        
        //f.setOpaque(false);
        //scroll.getViewport().setOpaque(false);
        //scroll.setBorder(null);
        //scroll.setViewportBorder(null);

        //f.setBorder(null);
        //f.setBackground(new Color(0, 0, 0, 0));
        
        p.add(scroll);
        p.add(button);
        p.add(New);
        p.add(Open);
        p.add(Quit);
        p.add(Save);
        p.add(Indent);
        //frame.pack();
        //frame.setVisible(true);
        
        try{
            p.add(new JLabel(new ImageIcon(ImageIO.read(new File("res/doge.png")))));
        }
        catch(IOException e)
        {
            
        }
        
        button.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    System.out.println("Clicked!");
                    
                    try {
                        FileWriter pw = new FileWriter ("Test.java");
                        f.write(pw);
                    }
                    catch(IOException e){
                    
                    }
                    
                    /*
                        final List<String> l = new ArrayList<String>();
                         final String cmd = "E:\\Trie2";
                            l.add("C:\\Windows\\system32\\cmd.exe ");
                            l.add("cd " + cmd);
                            l.add("javac filename.java");
                            l.add("java filename");
                            ProcessBuilder pb = new ProcessBuilder(l);
                            
                            try 
                            {
                                pb.start();
                                System.out.println("cmd started");
                            } 
                            catch (IOException e) 
                            {
                                System.out.println(e.getMessage());
                            }        
                    
                    */
                    
                    try {
                    runProcess("javac Test.java");
                    runProcess("java Test 10 20");
                    } catch (Exception e) {
                    e.printStackTrace();
                    }
                    
                }
            }
        );
        
        New.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    System.out.println("Clicked New!");
                    
                    f.setText("");
                }
            }
        );
        
        Open.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    System.out.println("Clicked Open!");
                    
                    JFileChooser chooser=new JFileChooser();
                    int chooserValue= chooser.showOpenDialog(null);
                    if(chooserValue==JFileChooser.APPROVE_OPTION){
                        try{
                            Scanner fin=new Scanner(chooser.getSelectedFile());
                            String buffer="";
                            while(fin.hasNext()){
                                buffer+= fin.nextLine() + "\n";
                            }
                            f.setText(buffer);
                            fin.close();
                            //statusField.setText("Loaded"+ chooser.getSelectedFile().getAbsolutePath());
                    
                        } catch(FileNotFoundException ex){
                        JOptionPane.showMessageDialog(null, "file not found");
                        }
                    }
                }
            }
        );
        
        Save.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    System.out.println("Clicked Save!");
                    JFileChooser chooser=new JFileChooser();
                    int chooserValue= chooser.showSaveDialog(null);
                    if(chooserValue==JFileChooser.APPROVE_OPTION){
                        try{
                            PrintWriter fout= new PrintWriter(chooser.getSelectedFile());
                            fout.print(f.getText());
                            fout.close();
                            //statusField.setText("Saved"+ chooser.getSelectedFile().getAbsolutePath());
                        }catch(FileNotFoundException ex){
                            //Logger.getLogger(TextEdit.class.getName()).log(Level,SEVERE,null, ex);
                        }
            
                    }
                }
            }
        );
        
        Quit.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    System.out.println("Clicked Quit!");
                    
                    System.exit(0);
                }
            }
        );
        
        Indent.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    System.out.println("Clicked Indent!");
                    
                    String str = f.getText();
                    String s="";
                    
                    int x = 0;
        boolean new_line_encountered=false;
        str = str.replace("\t","");
        for(int i=0;i<str.length()-pop_count;i++)
        {
             
            if(str.charAt(i)=='{'){
                for(int j=1;j<=stack.size();j++)
                    //System.out.print('\t');
                                    s+='\t';
                //System.out.print('{');
                                s+='{';
                modify_Stack(str.charAt(i));
            }
            else{
                if(str.charAt(i)=='}') modify_Stack(str.charAt(i));
                if(new_line_encountered)
                for(int j=1;j<=stack.size();j++)
                    //System.out.print('\t');
                                    s+='\t';
                new_line_encountered=(str.charAt(i)=='\n');
                //System.out.print(str.charAt(i));
                                s+=str.charAt(i);
            }
            

        }
                f.setText(s);
      
                }
            }
        );
        
        /*StringBuilder store= new StringBuilder();
        Indent.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    System.out.println("Clicked Indent!");
                    String str= f.getText();
                    for(int i=0;i<str.length();i++){
                        char ch=str.charAt(i);
                                
                    }
                        
                            
                    }
        */
        frame.add(p);

        frame.pack();
        frame.setVisible(true);
    }
    
    
    public static void modify_Stack(char ch) {
		if(ch=='{') stack.push('\t');
		else stack.pop();
	}
    private static void printLines(String name, InputStream ins) throws Exception {
    String line = null;
    BufferedReader in = new BufferedReader(
        new InputStreamReader(ins));
    while ((line = in.readLine()) != null) {
        System.out.println(name + " " + line);
    }
  }

  private static void runProcess(String command) throws Exception {
    Process pro = Runtime.getRuntime().exec(command);
    printLines(command + " stdout:", pro.getInputStream());
    printLines(command + " stderr:", pro.getErrorStream());
    pro.waitFor();
    System.out.println(command + " exitValue() " + pro.exitValue());
  }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Test2();
            }
        });
    }
}

class AutoSuggestor {

    private final JTextArea textField;
    private final Window container;
    private JPanel suggestionsPanel;
    private JWindow autoSuggestionPopUpWindow;
    private String typedWord;
    private final ArrayList<String> dictionary = new ArrayList<>();
    private int currentIndexOfSpace, tW, tH;
    private DocumentListener documentListener = new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent de) {
            checkForAndShowSuggestions();
        }

        @Override
        public void removeUpdate(DocumentEvent de) {
            checkForAndShowSuggestions();
        }

        @Override
        public void changedUpdate(DocumentEvent de) {
            checkForAndShowSuggestions();
        }
    };
    private final Color suggestionsTextColor;
    private final Color suggestionFocusedColor;

    public AutoSuggestor(JTextArea textField, Window mainWindow, ArrayList<String> words, Color popUpBackground, Color textColor, Color suggestionFocusedColor, float opacity) {
        this.textField = textField;
        this.suggestionsTextColor = textColor;
        this.container = mainWindow;
        this.suggestionFocusedColor = suggestionFocusedColor;
        this.textField.getDocument().addDocumentListener(documentListener);

        setDictionary(words);

        typedWord = "";
        currentIndexOfSpace = 0;
        tW = 0;
        tH = 0;

        autoSuggestionPopUpWindow = new JWindow(mainWindow);
        autoSuggestionPopUpWindow.setOpacity(opacity);

        suggestionsPanel = new JPanel();
        suggestionsPanel.setLayout(new GridLayout(0, 1));
        suggestionsPanel.setBackground(popUpBackground);

        addKeyBindingToRequestFocusInPopUpWindow();
    }

    private void addKeyBindingToRequestFocusInPopUpWindow() {
        textField.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "Down released");
        textField.getActionMap().put("Down released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {//focuses the first label on popwindow
                for (int i = 0; i < suggestionsPanel.getComponentCount(); i++) {
                    if (suggestionsPanel.getComponent(i) instanceof SuggestionLabel) {
                        ((SuggestionLabel) suggestionsPanel.getComponent(i)).setFocused(true);
                        autoSuggestionPopUpWindow.toFront();
                        autoSuggestionPopUpWindow.requestFocusInWindow();
                        suggestionsPanel.requestFocusInWindow();
                        suggestionsPanel.getComponent(i).requestFocusInWindow();
                        break;
                    }
                }
            }
        });
        suggestionsPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), "Down released");
        suggestionsPanel.getActionMap().put("Down released", new AbstractAction() {
            int lastFocusableIndex = 0;

            @Override
            public void actionPerformed(ActionEvent ae) {//allows scrolling of labels in pop window (I know very hacky for now :))

                ArrayList<SuggestionLabel> sls = getAddedSuggestionLabels();
                int max = sls.size();

                if (max > 1) {//more than 1 suggestion
                    for (int i = 0; i < max; i++) {
                        SuggestionLabel sl = sls.get(i);
                        if (sl.isFocused()) {
                            if (lastFocusableIndex == max - 1) {
                                lastFocusableIndex = 0;
                                sl.setFocused(false);
                                autoSuggestionPopUpWindow.setVisible(false);
                                setFocusToTextField();
                                checkForAndShowSuggestions();//fire method as if document listener change occured and fired it

                            } else {
                                sl.setFocused(false);
                                lastFocusableIndex = i;
                            }
                        } else if (lastFocusableIndex <= i) {
                            if (i < max) {
                                sl.setFocused(true);
                                autoSuggestionPopUpWindow.toFront();
                                autoSuggestionPopUpWindow.requestFocusInWindow();
                                suggestionsPanel.requestFocusInWindow();
                                suggestionsPanel.getComponent(i).requestFocusInWindow();
                                lastFocusableIndex = i;
                                break;
                            }
                        }
                    }
                } else {//only a single suggestion was given
                    autoSuggestionPopUpWindow.setVisible(false);
                    setFocusToTextField();
                    checkForAndShowSuggestions();//fire method as if document listener change occured and fired it
                }
            }
        });
    }

    private void setFocusToTextField() {
        container.toFront();
        container.requestFocusInWindow();
        textField.requestFocusInWindow();
    }

    public ArrayList<SuggestionLabel> getAddedSuggestionLabels() {
        ArrayList<SuggestionLabel> sls = new ArrayList<>();
        for (int i = 0; i < suggestionsPanel.getComponentCount(); i++) {
            if (suggestionsPanel.getComponent(i) instanceof SuggestionLabel) {
                SuggestionLabel sl = (SuggestionLabel) suggestionsPanel.getComponent(i);
                sls.add(sl);
            }
        }
        return sls;
    }

    private void checkForAndShowSuggestions() {
        typedWord = getCurrentlyTypedWord();

        suggestionsPanel.removeAll();//remove previos words/jlabels that were added

        //used to calcualte size of JWindow as new Jlabels are added
        tW = 0;
        tH = 0;

        boolean added = wordTyped(typedWord);

        if (!added) {
            if (autoSuggestionPopUpWindow.isVisible()) {
                autoSuggestionPopUpWindow.setVisible(false);
            }
        } else {
            showPopUpWindow();
            setFocusToTextField();
        }
    }

    protected void addWordToSuggestions(String word) {
        SuggestionLabel suggestionLabel = new SuggestionLabel(word, suggestionFocusedColor, suggestionsTextColor, this);

        calculatePopUpWindowSize(suggestionLabel);

        suggestionsPanel.add(suggestionLabel);
    }

    public String getCurrentlyTypedWord() {//get newest word after last white spaceif any or the first word if no white spaces
        String text = textField.getText();
        String wordBeingTyped = "";
        if (text.contains(" ")) {
            int tmp = text.lastIndexOf(" ");
            if (tmp >= currentIndexOfSpace) {
                currentIndexOfSpace = tmp;
                wordBeingTyped = text.substring(text.lastIndexOf(" "));
            }
        } else {
            wordBeingTyped = text;
        }
        return wordBeingTyped.trim();
    }

    private void calculatePopUpWindowSize(JLabel label) {
        //so we can size the JWindow correctly
        if (tW < label.getPreferredSize().width) {
            tW = label.getPreferredSize().width;
        }
        tH += label.getPreferredSize().height;
    }

    private void showPopUpWindow() {
        autoSuggestionPopUpWindow.getContentPane().add(suggestionsPanel);
        autoSuggestionPopUpWindow.setMinimumSize(new Dimension(textField.getWidth(), 30));
        autoSuggestionPopUpWindow.setSize(tW, tH);
        autoSuggestionPopUpWindow.setVisible(true);

        int windowX = 0;
        int windowY = 0;

        windowX = container.getX() + textField.getX() + 5;
        if (suggestionsPanel.getHeight() > autoSuggestionPopUpWindow.getMinimumSize().height) {
            windowY = container.getY() + textField.getY() + textField.getHeight() + autoSuggestionPopUpWindow.getMinimumSize().height;
        } else {
            windowY = container.getY() + textField.getY() + textField.getHeight() + autoSuggestionPopUpWindow.getHeight();
        }

        autoSuggestionPopUpWindow.setLocation(windowX, windowY);
        autoSuggestionPopUpWindow.setMinimumSize(new Dimension(textField.getWidth(), 30));
        autoSuggestionPopUpWindow.revalidate();
        autoSuggestionPopUpWindow.repaint();

    }

    public void setDictionary(ArrayList<String> words) {
        dictionary.clear();
        if (words == null) {
            return;//so we can call constructor with null value for dictionary without exception thrown
        }
        for (String word : words) {
            dictionary.add(word);
        }
    }

    public JWindow getAutoSuggestionPopUpWindow() {
        return autoSuggestionPopUpWindow;
    }

    public Window getContainer() {
        return container;
    }

    public JTextArea getTextField() {
        return textField;
    }

    public void addToDictionary(String word) {
        dictionary.add(word);
    }

    boolean wordTyped(String typedWord) {

        if (typedWord.isEmpty()) {
            return false;
        }
        
        
 /*
        ArrayList<String> matches = new ArrayList<String>();
        
        ArrayList<String> list = new ArrayList<String>();
        Collections.addAll(list, "abstract","continue","for","new","switch","assert","default","goto","package","synchronized","boolean","do","if","private","this","break","double","implements","protected","throw","byte","else","import","public","throws","case","enum","instanceof","return","transient","catch","extends","int","short","try","char","final","interface","static","void","class","finally","long","strictfp","volatile","const","float","native","super","while");
        
        Trie   trie = new Trie();
		trie.loadKeys(list);
                
        matches  = trie.getAllPrefixMatches(typedWord);
        
        */
        
        Trie t = new Trie();
        ArrayList<String> matches = new ArrayList<String>();
        
        List<String> words = t.getWords();
		//List is iterable
		t.loadArray();
        matches = t.displayOptions(typedWord);
        //System.out.println("Typed word: " + typedWord);

        boolean suggestionAdded = false;

        for (String word : matches) {//get words in the dictionary which we added
            boolean fullymatches = true;
            for (int i = 0; i < typedWord.length(); i++) {//each string in the word
                if (!typedWord.toLowerCase().startsWith(String.valueOf(word.toLowerCase().charAt(i)), i)) {//check for match
                    fullymatches = false;
                    break;
                }
            }
        
            if (fullymatches) {
                addWordToSuggestions(word);
                suggestionAdded = true;
            }
       }
        
        
        return suggestionAdded;
    }
}

class SuggestionLabel extends JLabel {

    private boolean focused = false;
    private final JWindow autoSuggestionsPopUpWindow;
    private final JTextArea textField;
    private final AutoSuggestor autoSuggestor;
    private Color suggestionsTextColor, suggestionBorderColor;

    public SuggestionLabel(String string, final Color borderColor, Color suggestionsTextColor, AutoSuggestor autoSuggestor) {
        super(string);

        this.suggestionsTextColor = suggestionsTextColor;
        this.autoSuggestor = autoSuggestor;
        this.textField = autoSuggestor.getTextField();
        this.suggestionBorderColor = borderColor;
        this.autoSuggestionsPopUpWindow = autoSuggestor.getAutoSuggestionPopUpWindow();

        initComponent();
    }

    private void initComponent() {
        setFocusable(true);
        setForeground(suggestionsTextColor);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                super.mouseClicked(me);

                replaceWithSuggestedText();

                autoSuggestionsPopUpWindow.setVisible(false);
            }
        });

        getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "Enter released");
        getActionMap().put("Enter released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                replaceWithSuggestedText();
                autoSuggestionsPopUpWindow.setVisible(false);
            }
        });
    }

    public void setFocused(boolean focused) {
        if (focused) {
            setBorder(new LineBorder(suggestionBorderColor));
        } else {
            setBorder(null);
        }
        repaint();
        this.focused = focused;
    }

    public boolean isFocused() {
        return focused;
    }

    private void replaceWithSuggestedText() {
        String suggestedWord = getText();
        String text = textField.getText();
        String typedWord = autoSuggestor.getCurrentlyTypedWord();
        String t = text.substring(0, text.lastIndexOf(typedWord));
        String tmp = t + text.substring(text.lastIndexOf(typedWord)).replace(typedWord, suggestedWord);
        textField.setText(tmp + " ");
    }
}