import java.util.Scanner;


public class Main {

    static Scanner input = new Scanner(System.in);


    static void mainMenu() {

        System.out.println("iFace - Main Menu");
        System.out.println("Chooose an option:");
        System.out.println("(1)Signup");
        System.out.println("(2)Login");
        System.out.println("(3)Quit");
    }

    static void loggedMenu() {

        System.out.println("iFace - Logged Menu");
        System.out.println("Chooose an option:");
        System.out.println("(1)Edit profile");
        System.out.println("(2)Add friend");
        System.out.println("(3)Check friend requests");
        System.out.println("(4)Send message");
        System.out.println("(5)Check sent messages");
        System.out.println("(6)Check received messages");
        System.out.println("(7)Create community");
        System.out.println("(8)Join community");
        System.out.println("(9)Get profile info");
        System.out.println("(10)Exclude account");
        System.out.println("(11)Log out");
        System.out.println("(0)Quit");
    }

    public static void main (String [] args) {
        //booleans and maximum array size
        final int max = 1000;
        boolean unlogged = true;
        boolean turnedOn = true;

        int lastUserPosition = 0;
        int currentPosition = 0;
        int lastMessagePosition = 0;
        int lastFriendshipPosition = 0;
        int lastCommunity = 0;
        String currentUser = null;
        int statisticsCounter = 0;

        String[] userNames = new String[max];
        String[] logins = new String[max];
        String[] passwords = new String[max];
        String[][]friends = new String[max*10][3];//10000 friendships at most,[i][0]is the sender,[i][1]is the recipient
        //[i][2] is the status of the friendship, is either "friends" or "waiting"
        String[][] messagesArray = new String[max*5][3];//5000 messages,[i][0] is the message,[i][1] is the sender,
        // [i][2] is the recipient
        String[][] communitiesArray = new String[max][max];//1000 communities with at most, 998 members,[i][0] is the name,
        // [i][1] is the description,[i][2] is the owner, from then on, member names

        //input variables
        String option;
        String editOption;
        String emailInput;
        String passwordInput;
        String nameInput;
        String sender;
        String recipient;
        String message;
        String friendshipRequestOption;
        String communityName;
        String communityDescription;


        while(turnedOn) {
            while(unlogged) {
                mainMenu();
                option = input.next();
                switch (option) {
                    case "1":
                        System.out.println("iFace - Signup Menu");
                        System.out.println("Email:");
                        emailInput = input.next();
                        System.out.println("Password:");
                        passwordInput = input.next();
                        System.out.println("Profile name");
                        nameInput = input.next();
                        logins[lastUserPosition] = emailInput;
                        passwords[lastUserPosition] = passwordInput;
                        userNames[lastUserPosition] = nameInput;
                        lastUserPosition++;
                        break;

                    case "2":
                        System.out.println("iFace - Login Menu");
                        System.out.println("Email:");
                        emailInput = input.next();
                        System.out.println("Password:");
                        passwordInput = input.next();
                        for (int iterator = 0; iterator <= lastUserPosition; iterator++)
                        {
                            if (emailInput.equals(logins[iterator])){
                                if (passwordInput.equals(passwords[iterator]))
                                {
                                    currentPosition = iterator;
                                    currentUser = userNames[currentPosition];
                                    unlogged = false;
                                    System.out.println("Logged in as " + userNames[currentPosition] + "!");
                                }
                                else System.out.println("Wrong credentials, returning to main menu.");
                                break;
                            }
                            else if(iterator == lastUserPosition)
                            {
                                System.out.println("User not found, returning to main menu.");
                                break;
                            }

                        }
                        break;

                    case "3":
                        System.out.println("Goodbye!");
                        unlogged = turnedOn = false;
                        break;

                    default:
                        System.out.println("Invalid option!\nChoose again.");
                        break;
                }
            }
            while (!unlogged && turnedOn){
                loggedMenu();
                option = input.next();
                switch (option) {
                    case "1":
                        System.out.println("Choose what you want to edit:");
                        System.out.println("(1)Login");
                        System.out.println("(2)Password");
                        System.out.println("(3)Profile name");
                        editOption = input.next();
                        switch (editOption)
                        {
                            case "1":
                                System.out.println("Type the new login you want to use:");
                                emailInput = input.next();
                                logins[currentPosition] = emailInput;
                                System.out.println("Login changed!");
                                break;

                            case "2":
                                System.out.println("Type the new password you want to use:");
                                passwordInput = input.next();
                                passwords[currentPosition] = passwordInput;
                                System.out.println("Password changed!");
                                break;

                            case "3":
                                System.out.println("Type the new name you want to use:");
                                nameInput = input.next();
                                userNames[currentPosition] = nameInput;//changes name in userNames array
                                if(friends[0][0]!= null)
                                {
                                    for (int i = 0; i <= lastFriendshipPosition; i++){
                                        if((friends[i][0].equals(currentUser)))
                                        {
                                            friends[i][0] = nameInput;//changes name in sender position of friendships
                                        }
                                    }

                                    for (int i = 0; i <= lastFriendshipPosition; i++){
                                        if((friends[i][1].equals(currentUser)))
                                        {
                                            friends[i][1] = nameInput;//changes name in recipient position of friendships
                                        }
                                    }
                                }

                                if(messagesArray[0][0] != null) {
                                    for (int i = 0; i <= lastMessagePosition; i++) {
                                        if (messagesArray[i][1].equals(currentUser)) {
                                            messagesArray[i][1] = nameInput;//changes name in sender position of messages
                                            break;
                                        }
                                    }


                                    for (int i = 0; i <= lastMessagePosition; i++){
                                        if(messagesArray[i][2].equals(currentUser))
                                        {
                                            messagesArray[i][2] = nameInput;//changes name in recipient position of messages
                                            break;
                                        }
                                    }

                                }
                                if (communitiesArray[0][0] != null) {
                                    for (int i = 0; i <= lastCommunity; i++){
                                        for (int j = 2; j<1000; j++){
                                            if (communitiesArray[i][j].equals(currentUser))
                                            {
                                                communitiesArray[i][j] = nameInput;//changes name in communities
                                                break;
                                            }
                                        }
                                    }
                                }

                                currentUser = nameInput;//changes current user user name
                                System.out.println("Profile name changed!");
                                break;

                            default:
                                System.out.println("Invalid option!\nGoing back to main menu.");
                                break;
                        }
                        break;

                    case "2":
                        System.out.println("Type the recipient of the friendship request");
                        recipient = input.next();
                        sender = currentUser;
                        for (int i = 0; i <= lastUserPosition; i++)
                        {
                            if (recipient.equals(userNames[i]))
                            {
                                friends[lastFriendshipPosition][0] = sender;
                                friends[lastFriendshipPosition][1] = recipient;
                                friends[lastFriendshipPosition][2] = "waiting";
                                lastFriendshipPosition++;
                                System.out.println("Request sent succesfully!");
                                break;
                            }
                            else if (i == lastUserPosition){
                                System.out.println("Recipient not found, request not sent.");
                            }
                        }
                        break;

                    case "3":
                        if (friends[0][0]!=null) {
                            for (int i = 0; i < lastFriendshipPosition; i++) {
                                if (friends[i][1].equals(currentUser) && friends[i][2].equals("waiting")) {
                                    System.out.println("You have a friendship request from " + friends[i][0] + ". Accept?");
                                    System.out.println("Type (1) for yes, and (2) for no.");
                                    friendshipRequestOption = input.next();
                                    switch (friendshipRequestOption) {
                                        case "1":
                                            friends[i][2] = "friends";
                                            System.out.println("Friendship confirmed!");
                                            break;

                                        case "2":
                                            friends[i][0] = null;
                                            friends[i][1] = null;
                                            friends[i][2] = null;
                                            System.out.println("Friendship request removed");

                                        default:
                                            System.out.println("Invalid option!\nGoing back to menu.");
                                            break;
                                    }
                                    break;
                                }
                                else if (i == lastUserPosition-1) {
                                    System.out.println("No friendship requests found.");
                                }
                            }
                        }
                        break;

                    case "4":
                        System.out.println("Type your message:");
                        message = input.next();
                        System.out.println("Type the recipient of the message");
                        recipient = input.next();
                        sender = currentUser;
                        for (int i = 0; i <= lastUserPosition; i++)
                        {
                            if (recipient.equals(userNames[i]))
                            {
                                messagesArray[lastMessagePosition][0] = message;
                                messagesArray[lastMessagePosition][1] = sender;
                                messagesArray[lastMessagePosition][2] = recipient;
                                lastMessagePosition++;
                                System.out.println("Message sent succesfully!");
                                break;
                            }
                            else if (i == lastUserPosition){
                                System.out.println("Recipient not found, message not sent.");
                            }
                        }
                        break;

                    case "5":
                        System.out.println("These are the messages you sent:");
                        if(messagesArray[0][0] != null) {
                            for (int i = 0; i < lastMessagePosition; i++)
                            {
                                if(messagesArray[i][1].equals(currentUser))
                                {
                                    System.out.println("You sent the following message to " + messagesArray[i][2] + ":"
                                            + messagesArray[i][0]);
                                }
                            }
                        }
                        break;

                    case "6":
                        System.out.println("These are the messages you received:\n");
                        if(messagesArray[0][0] != null) {
                            for (int i = 0; i < lastMessagePosition; i++) {
                                if (messagesArray[i][2].equals(currentUser)) {
                                    System.out.println("You received the following message from " + messagesArray[i][1] + ":"
                                            + messagesArray[i][0]);
                                }
                            }
                        }
                        break;

                    case "7":
                        System.out.println("Type the name of the community you wish to create:");
                        communityName = input.next();

                        if(communitiesArray[0][0] != null) {
                            for (int i = 0; i <= lastCommunity; i++) {
                                if(communitiesArray[i][0]!=null) {
                                    if (communitiesArray[i][0].equals(communityName)) {
                                        System.out.println("Community already exists. Going back to menu");
                                        break;
                                    }
                                }
                                if(i == lastCommunity) {
                                    System.out.println("Type the description of your community:");
                                    communityDescription = input.next();
                                    communitiesArray[i][0] = communityName;
                                    communitiesArray[i][1] = communityDescription;
                                    communitiesArray[i][2] = currentUser;
                                    lastCommunity++;
                                    System.out.println("Community created successfully!");
                                    break;
                                }
                            }
                        }
                        else {
                            System.out.println("Type the description of your community:");
                            communityDescription = input.next();
                            communitiesArray[0][0] = communityName;
                            communitiesArray[0][1] = communityDescription;
                            communitiesArray[0][2] = currentUser;
                            lastCommunity++;
                            System.out.println("Community created successfully!");
                        }
                        break;

                    case "8":
                        System.out.println("Type the name of the community you wish to join:");
                        communityName = input.next();
                        if(communitiesArray[0][0] != null) {
                            for (int i = 0; i < lastCommunity; i++) {
                                if (communitiesArray[i][0].equals(communityName)) {
                                    System.out.println("Community Found. Joining!");
                                    for (int iterator = 3; iterator <= 999; iterator++) {
                                        if (communitiesArray[i][iterator] == null) {
                                            communitiesArray[i][iterator] = currentUser;
                                            System.out.println("You joined the community! Going back to main menu.");
                                            break;
                                        } else if (iterator == 999) {
                                            System.out.println("You couldn't join the community because the are no places left." +
                                                    "\nGoing back to main menu");
                                            break;
                                        }
                                    }
                                    break;
                                } else if (i == lastCommunity) {
                                    System.out.println("Community not found. Going back to main menu.");
                                    break;
                                }
                            }
                        }
                        else {
                            System.out.println("No communities found.");
                        }
                        break;

                    case "9":
                        System.out.println("Profile statistics:");

                        if(friends[0][0] != null) {
                            System.out.println("Friends:");
                            for (int i = 0; i < lastFriendshipPosition; i++) {
                                if (friends[i][2].equals("friends") && friends[i][0].equals(currentUser)) {
                                    System.out.println(friends[i][1]);
                                    statisticsCounter++;
                                }
                                if (friends[i][2].equals("friends") && friends[i][1].equals(currentUser)) {
                                    System.out.println(friends[i][0]);
                                    statisticsCounter++;
                                }
                            }
                            System.out.println(statisticsCounter + " friends");
                            statisticsCounter = 0;
                        }
                        else{
                            System.out.println("Friends:0");
                        }


                        if(messagesArray[0][0] != null) {
                            System.out.print("Sent messages:");
                            for (int i = 0; i < lastMessagePosition; i++) {
                                if (messagesArray[i][1].equals(currentUser)) {
                                    statisticsCounter++;
                                }
                            }
                            System.out.println(statisticsCounter);
                            statisticsCounter = 0;

                            System.out.print("Received messages:");
                            for (int i = 0; i < lastMessagePosition; i++) {
                                if (messagesArray[i][2].equals(currentUser)) {
                                    statisticsCounter++;
                                }
                            }
                            System.out.println(statisticsCounter);
                            statisticsCounter = 0;
                        }
                        else {
                            System.out.println("Sent messages:0\nReceived messages:0");
                        }

                        if(communitiesArray[0][0] != null) {
                            System.out.print("Communities owned:");
                            for (int i = 0; communitiesArray[i][0]!=null ; i++) {
                                if (communitiesArray[i][2].equals(currentUser)) {
                                    statisticsCounter++;
                                }
                            }
                            System.out.println(statisticsCounter);
                            statisticsCounter = 0;

                            System.out.print("Communities you're a member of:");
                            for (int i = 0; communitiesArray[i][0]!=null; i++) {
                                for (int j = 2; communitiesArray[i][j]!=null; j++) {
                                    if (communitiesArray[i][j].equals(currentUser)) {
                                        statisticsCounter++;
                                    }
                                }
                            }
                            System.out.println(statisticsCounter);
                            statisticsCounter = 0;
                        }
                        else
                        {
                            System.out.print("Communities owned:0\nCommunities you're a member of:0\n");
                        }
                        break;

                    case "10":

                        logins[currentPosition] = null;
                        passwords[currentPosition] = null;
                        userNames[currentPosition] = null;

                        if(friends[0][0] != null) {
                            for (int i = 0; i < lastFriendshipPosition; i++) {
                                if (friends[i][0].equals(currentUser) || friends[i][1].equals(currentUser)) {
                                    friends[i][0] = null;
                                    friends[i][1] = null;
                                    friends[i][2] = null;
                                }
                                //excludes friendships
                            }
                        }

                        if(messagesArray[0][0] != null) {
                            for (int i = 0; i < lastMessagePosition; i++) {
                                if (messagesArray[i][1].equals(currentUser) || messagesArray[i][2].equals(currentUser)) {
                                    messagesArray[i][0] = null;
                                    messagesArray[i][1] = null;
                                    messagesArray[i][2] = null;
                                }
                                //excludes sent and received messages
                            }
                        }
                        if(communitiesArray[0][0] != null) {
                            for (int i = 0; i <= lastCommunity; i++) {
                                for (int j = 2; j < 1000; j++) {
                                    if (communitiesArray[i][j].equals(currentUser)) {
                                        if (j == 2 && communitiesArray[i][3] != null) {
                                            communitiesArray[i][j] = communitiesArray[i][3];
                                            communitiesArray[i][3] = null;
                                        } else {
                                            communitiesArray[i][j] = null;
                                        }
                                        //excludes communities membership
                                    }
                                }
                            }
                        }
                        System.out.println("Account excluded. Goodbye!");
                        currentUser = null;
                        unlogged = turnedOn = true;
                        break;

                    case "11":
                        System.out.println("Logging off");
                        currentUser = null;
                        unlogged = turnedOn = true;
                        break;

                    case "0":
                        System.out.println("Goodbye!");
                        unlogged = turnedOn = false;
                        break;

                    default:
                        System.out.println("Invalid option!\nChoose again.");
                        break;
                }
            }
        }
    }

}
