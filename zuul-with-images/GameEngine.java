import java.util.HashMap;
import java.lang.String;
import java.util.*;

/**
 *  This class is part of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.
 * 
 *  This class creates all rooms, creates the parser and starts
 *  the game.  It also evaluates and executes the commands that 
 *  the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (Jan 2003)
 */
public class GameEngine
{
    private Parser parser;
    private Room currentRoom;
    private UserInterface gui;

    //la hashmap pour toutes les pièces.

    private HashMap<String,Room> All_rooms;

    /**
     * Constructor for objects of class GameEngine
     */
    public GameEngine()
    {
        parser = new Parser();
        createRooms();
    }

    public void setGUI(UserInterface userInterface)
    {
        gui = userInterface;
        printWelcome();
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        gui.print("\n");
        gui.println("Welcome to the World of Zuul!");
        gui.println("World of Zuul is a new, incredibly boring adventure game.");
        gui.println("Type 'help' if you need help.");
        gui.print("\n");
        gui.println(currentRoom.getLongDescription());
        gui.showImage(currentRoom.getImageName());
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
       // Room outside, theatre, pub, lab, office;
        Room Le_port_du_Nil, Le_village,
        La_pyramide,L_entree_pyramide,
        La_salle_du_culte, La_frichti, La_salle_de_bain,La_salle_du_choix,
        La_salle_de_la_mort,La_salle_pre_tombeau,Le_tombeau,La_chambre_de_la_reine,
        Le_paradis;

        // create the rooms
      /*  outside = new Room("outside the main entrance of the university", "outside.gif");
        theatre = new Room("in a lecture theatre", "castle.gif");
        pub = new Room("in the campus pub", "courtyard.gif");
        lab = new Room("in a computing lab", "stairs.gif");
        office = new Room("the computing admin office", "dungeon.gif");*/

        Le_port_du_Nil = new Room("au port du nil","lenil.jpg");
        Le_village = new Room("au village.","levilage.jpg"); 
      L_entree_pyramide = new Room("dans la pyramide d'Amaithor.","entreepy.jpg");
        La_pyramide = new Room("à l'entrée de la pyramide.","interieur_py.jpg");
        La_salle_du_culte = new Room("dans la salle du Culte.","culte.jpg");
        La_frichti = new Room("dans la frichti (cuisine).","frich.jpeg");
        La_salle_de_bain = new Room("dans la salle de bain.","bain.jpg");
        La_salle_du_choix = new Room("Vous êtes dans la salle du choix. ","choix.jpg");
        La_salle_de_la_mort = new Room("Vous êtes dans la salle de la mort... quittez pour retenter votre chance","mort.jpg");
        La_salle_pre_tombeau = new Room(" dans la salle du pré-tombeau.","pretombeau.jpg");
        Le_tombeau = new Room("dans la salle du tombeau.","tombeau.jpg");
        La_chambre_de_la_reine = new Room("dans la chambre de la Reine.","chambre.jpg");
        Le_paradis = new Room("dans le paradis. Jeu fini.","paradis.jpg");

        All_rooms = new HashMap();

        this.All_rooms.put("au port du nil",Le_port_du_Nil);
        this.All_rooms.put("au village",Le_village);
        this.All_rooms.put("dans la pyramide d'Amaithor.",La_pyramide);
        this.All_rooms.put("à l'entrée de la pyramide.",L_entree_pyramide);
        this.All_rooms.put("dans la salle du Culte.",La_salle_du_culte);
        this.All_rooms.put("dans la frichti (cuisine).",La_frichti);
        this.All_rooms.put("dans la salle de bain.",La_salle_de_bain);
        this.All_rooms.put("Vous êtes dans la salle du choix.",La_salle_du_choix);
        this.All_rooms.put("Vous êtes dans la salle de la mort... quittez pour retenter votre chance",La_salle_de_la_mort);
        this.All_rooms.put("dans la salle du pré-tombeau.",La_salle_pre_tombeau);
        this.All_rooms.put("dans la salle du tombeau.",Le_tombeau);
        this.All_rooms.put("dans la chambre de la Reine.",La_chambre_de_la_reine);
        this.All_rooms.put("dans le paradis. Jeu fini.",Le_paradis);




        currentRoom = Le_port_du_Nil;  // start game outside


         Le_port_du_Nil.setExit("east",Le_village);

         Le_village.setExit("west",Le_port_du_Nil);
        Le_village.setExit("south",L_entree_pyramide);

       L_entree_pyramide.setExit("north",Le_village);
        L_entree_pyramide.setExit("east", La_pyramide);

        La_pyramide.setExit("east",La_salle_du_culte);
        La_pyramide.setExit("west",L_entree_pyramide);

        La_salle_du_culte.setExit("north",La_frichti);
        La_salle_du_culte.setExit("east",La_salle_de_bain);
        La_salle_du_culte.setExit("south",La_salle_du_choix);
        La_salle_du_culte.setExit("west",L_entree_pyramide);


        La_frichti.setExit("east",La_salle_de_bain);
       La_frichti.setExit("south",La_salle_du_culte);

       La_salle_de_bain.setExit("north",La_frichti);
       La_salle_de_bain.setExit("south",La_salle_du_culte);

       La_salle_du_choix.setExit("north",La_salle_du_culte);
       La_salle_du_choix.setExit("east",La_salle_de_la_mort);
       La_salle_du_choix.setExit("west",La_salle_pre_tombeau);

       La_salle_pre_tombeau.setExit("east",La_salle_du_choix);
       La_salle_pre_tombeau.setExit("south",Le_tombeau);

       // ici il y aura la téléportation. 
        // on laisse cette direction pour l'instant.       
       Le_tombeau.setExit("north", La_chambre_de_la_reine);

        La_chambre_de_la_reine.setExit("south",Le_paradis);



    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    public void interpretCommand(String commandLine) 
    {
        gui.println(commandLine);
        Command command = parser.getCommand(commandLine);

        if(command.isUnknown()) {
            gui.println("I don't know what you mean...");
            return;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("quit")) {
            if(command.hasSecondWord())
                gui.println("Quit what?");
            else
                endGame();
        }
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        gui.println("You are lost. You are alone. You wander");
        gui.println("around at Monash Uni, Peninsula Campus." + "\n");
        gui.print("Your command words are: " + parser.showCommands());
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gui.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null)
            gui.println("There is no door!");
        else {
            currentRoom = nextRoom;
            gui.println(currentRoom.getLongDescription());
            if(currentRoom.getImageName() != null)
                gui.showImage(currentRoom.getImageName());
        }
    }

    private void endGame()
    {
        gui.println("Thank you for playing.  Good bye.");
        gui.enable(false);
    }

}
