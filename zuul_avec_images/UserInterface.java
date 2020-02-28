import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author Michael Kolling
 * @version 1.0 (Jan 2003)
 */
public class UserInterface implements ActionListener
{
    private GameEngine engine;
    private JFrame myFrame;
    private JTextField entryField;
    private JTextArea log;
    private JLabel image;

    // ajout des boutons.

    private JPanel Bu;
    private JButton NorthBu, SouthBu, WestBu, EastBu;

    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param gameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface(GameEngine gameEngine)
    {
        engine = gameEngine;
        createGUI();
    }

    /**
     * Print out some text into the text area.
     */
    public void print(String text)
    {
        log.append(text);
        log.setCaretPosition(log.getDocument().getLength());
    }

    /**
     * Print out some text into the text area, followed by a line break.
     */
    public void println(String text)
    {
        log.append(text + "\n");
        log.setCaretPosition(log.getDocument().getLength());
    }

    /**
     * Show an image file in the interface.
     */
    public void showImage(String imageName)
    {
        URL imageURL = this.getClass().getClassLoader().getResource(imageName);
        if(imageURL == null)
            System.out.println("image not found");
        else {
            ImageIcon icon = new ImageIcon(imageURL);
            image.setIcon(icon);
            myFrame.pack();
        }
    }

    /**
     * Enable or disable input in the input field.
     */
    public void enable(boolean on)
    {
        entryField.setEditable(on);
        if(!on)
            entryField.getCaret().setBlinkRate(0);
    }

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        myFrame = new JFrame("Amaithor Game");
        entryField = new JTextField(34);

        log = new JTextArea();
        log.setEditable(false);
        JScrollPane listScroller = new JScrollPane(log);
        listScroller.setPreferredSize(new Dimension(200, 200));
        listScroller.setMinimumSize(new Dimension(100,100));

        JPanel panel = new JPanel();
        image = new JLabel();
        this.creer_bouttons();

        panel.setLayout(new BorderLayout());
        panel.add(image, BorderLayout.NORTH);
        panel.add(listScroller, BorderLayout.CENTER);
        panel.add(entryField, BorderLayout.SOUTH);
        // button
        panel.add(this.Bu,BorderLayout.EAST);

        myFrame.getContentPane().add(panel, BorderLayout.CENTER);

        // add some event listeners to some components
        myFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });

        entryField.addActionListener(this);

        myFrame.pack();
        myFrame.setVisible(true);
        entryField.requestFocus();
    }



    // méthode pour créer des bouttons.

    public void creer_bouttons()
    {
        Bu = new JPanel();
        Bu.setLayout(new GridLayout(0,1,3,5));

// boutton nord
        this.NorthBu = new JButton("north");
        this.NorthBu.addActionListener(this);

// boutton sud 
         this.SouthBu = new JButton("south");
        this.SouthBu.addActionListener(this);

// boutton ouest
        this.WestBu = new JButton("west");
        this.WestBu.addActionListener(this);
// boutton est
        this.EastBu = new JButton("east");
        this.EastBu.addActionListener(this);

        Bu.add(this.NorthBu);
        Bu.add(this.SouthBu);
        Bu.add(this.WestBu);
        Bu.add(this.EastBu);


    }

    /**
     * Actionlistener interface for entry textfield.
     */
    public void actionPerformed(ActionEvent e) 
    {
        // no need to check the type of action at the moment.
        // there is only one possible action: text entry
        if (e.getSource()==this.NorthBu) {this.engine.interpretCommand("go north");}
        if (e.getSource()==this.SouthBu) {this.engine.interpretCommand("go south");}
        if (e.getSource()==this.WestBu) {this.engine.interpretCommand("go west");}
        if (e.getSource()==this.EastBu) {this.engine.interpretCommand("go east");}
         if (e.getSource()==this.entryField) {processCommand();}
        //processCommand();
    }

    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand()
    {
        boolean finished = false;
        String input = entryField.getText();
        entryField.setText("");

        engine.interpretCommand(input);
    }
}
