/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
       // Room outside, theatre, pub, lab, office;
        /*
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        // initialise room exits
        outside.setExits(null, theatre, lab, pub);
        theatre.setExits(null, null, null, outside);
        pub.setExits(null, outside, null, null);
        lab.setExits(outside, office, null, null);
        office.setExits(null, null, null, lab);

        currentRoom = outside;  // start game outside */

        /* pour notre jeu : Treize lieux(pièces).*/
/* création des pièces.*/
        Room Le_port_du_Nil, Le_village,La_pyramide,L_entree_pyramide,
        La_salle_du_culte, La_frichti, La_salle_de_bain,La_salle_du_choix,
        La_salle_de_la_mort,La_salle_pre_tombeau,Le_tombeau,La_chambre_de_la_reine,
        Le_paradis;

        Le_port_du_Nil = new Room("sur le port du Nil. ");
        Le_village = new Room("au village.");
        La_pyramide = new Room("devant la pyramide d'Amaithor.");
        L_entree_pyramide = new Room("à l'entrée de la pyramide.");
        La_salle_du_culte = new Room("dans la salle du Culte.");
        La_frichti = new Room("dans la frichti (cuisine).");
        La_salle_de_bain = new Room("dans la salle de bain.");
        La_salle_du_choix = new Room("Vous êtes dans la salle du choix. ");
        La_salle_de_la_mort = new Room("Vous êtes dans la salle de la mort... quittez pour retenter votre chance");
        La_salle_pre_tombeau = new Room(" dans la salle du pré-tombeau");
        Le_tombeau = new Room("dans la salle du tombeau.");
        La_chambre_de_la_reine = new Room("dans la chambre de la Reine.");
        Le_paradis = new Room("dans le paradis. Jeu fini.");

        /* initialisation des sorties des pièces */
        // Nord Est Sud Ouest
       /* Le_port_du_Nil.setExits(null,Le_village,null,null); // 1


        Le_village.setExits(null,null,La_pyramide,Le_port_du_Nil); // 2

        La_pyramide.setExits(Le_village,L_entree_pyramide,null,null); // 3 

        L_entree_pyramide.setExits(null,La_salle_du_culte,null,La_pyramide); //  4 
        
        La_salle_du_culte.setExits(La_frichti,La_salle_de_bain,La_salle_du_choix,L_entree_pyramide); // 5 
        
        La_frichti.setExits(null,La_salle_de_bain,La_salle_du_culte,null); // 6
        
        


        La_salle_de_bain.setExits(La_frichti,null,La_salle_du_culte,null); //7
        


       


        La_salle_du_choix.setExits(La_salle_du_culte,La_salle_de_la_mort,null,La_salle_pre_tombeau);//8
       
        
      
      //  La_chambre_de_la_reine.setExits(null,null,Le_paradis,null); // 12
        */

        // début du jeu : Le jeu commence au port du Nil.
        currentRoom = Le_port_du_Nil;

       
        Le_port_du_Nil.setExit("east",Le_village);

        Le_village.setExit("south",La_pyramide);

        La_pyramide.setExit("north",Le_village);
        La_pyramide.setExit("east", L_entree_pyramide);

        L_entree_pyramide.setExit("east",La_salle_du_culte);
        L_entree_pyramide.setExit("west",La_pyramide);

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
// La_salle_de_la_mort.setExits(null,null,null,null); //9

//La_salle_pre_tombeau.setExits(null,La_salle_du_choix,Le_tombeau,null);//10

       La_salle_pre_tombeau.setExit("east",La_salle_du_choix);
       La_salle_pre_tombeau.setExit("south",Le_tombeau);

//  Le_tombeau.setExits(La_chambre_de_la_reine,null,null,null);//11

       Le_tombeau.setExit("north", La_chambre_de_la_reine);

//  La_chambre_de_la_reine.setExits(null,null,Le_paradis,null); // 12

       La_chambre_de_la_reine.setExit("south",Le_paradis);

//Le_paradis.setExits(null,null,null,null);





    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
       // System.out.println("Vous êtes " + currentRoom.getDescription());
        //System.out.print("Exits: ");
      /*  if(currentRoom.northExit != null)
            System.out.print("north ");
        if(currentRoom.eastExit != null)
            System.out.print("east ");
        if(currentRoom.southExit != null)
            System.out.print("south ");
        if(currentRoom.westExit != null)
            System.out.print("west ");
        System.out.println();*/
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);

        else if (commandWord.equals("look"))
            look();

        else if (commandWord.equals("eat"))
            eat();


        else if (commandWord.equals("quit"))
            wantToQuit = quit(command);

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        //System.out.println("You are lost. You are alone. You wander");
        //System.out.println("around at the university.");
        //System.out.println();
        //System.out.println("Your command words are:");
       // System.out.println("   go quit help look");
        //parser.showCommands();
        System.out.println(parser.getCommandList());
    }

    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
       // Room nextRoom = null;
        Room nextRoom = currentRoom.getExit(direction);

        if(direction.equals("north")) {
            nextRoom = currentRoom.getExit("north");
        }
        if(direction.equals("east")) {
            nextRoom = currentRoom.getExit("east");
        }
        if(direction.equals("south")) {
            nextRoom = currentRoom.getExit("south");
        }
        if(direction.equals("west")) {
            nextRoom = currentRoom.getExit("west");
        }

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            //System.out.println("Vous êtes " + currentRoom.getDescription());
            //System.out.print("Exits: ");
            printLocationInfo();
          /*  if(currentRoom.northExit != null)
                System.out.print("north ");
            if(currentRoom.eastExit != null)
                System.out.print("east ");
            if(currentRoom.southExit != null)
                System.out.print("south ");
            if(currentRoom.westExit != null)
                System.out.print("west ");
            System.out.println();*/
        }
    }


private void printLocationInfo()
{
	 System.out.println("Vous êtes " + currentRoom.getDescription());
     System.out.print("Exits: ");
    /*  if(currentRoom.northExit != null)
                System.out.print("north ");
            if(currentRoom.eastExit != null)
                System.out.print("east ");
            if(currentRoom.southExit != null)
                System.out.print("south ");
            if(currentRoom.westExit != null)
                System.out.print("west ");*/
                //System.out.print(currentRoom.getExitString());
                System.out.println(currentRoom.getLongDescription());
            System.out.println();
}



private void look()
{
	System.out.println("look button");
	System.out.println(currentRoom.getLongDescription());
}


/* rajouter une fonction pour manger eat()
 Il faut faire la même chose que pour look()
 Pour le moment cette fonction ne fait pas 
 l'action de manger, elle se contente juste
 d'écrire "tu viens de manger !"
*/

 private void eat()
 {
 	System.out.println("tu viens de manger !");
	System.out.println(currentRoom.getLongDescription());

 }


    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}



