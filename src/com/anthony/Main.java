package com.anthony;
// todo Allow computer to make a play also and allow more than one round to be had.
import java.util.Scanner;
import java.util.Random;

public class Main {
    static Scanner numberScanner = new Scanner(System.in);
    static Scanner stringScanner = new Scanner(System.in);
    static Random randomNumber = new Random();
    public static void main(String[] args) {
        /* Go Fish Game 2 player*/
        // Create arrays for player, computer, and cards in the deck
        /* Starting hand size */
        int handSize = 12;
        // Create deck
        String deckArray[] = createDeck();
        // Create hands
        String userHandArray[] = new String[52];
        String computerHandArray[] = new String[52];
        // Initial draw for each hand
        userHandArray = drawCards(userHandArray, deckArray, handSize);
        computerHandArray = drawCards(computerHandArray, deckArray, handSize);
        // Create variables
        // todo Implement scoring system
        int userBooks = 0;
        int computerBooks = 0;
        String userAsk;
        int cardsHad = 0;

        String compString = "";

        // Show what the user has
        getHandString(userHandArray);


//        // Testing -----
//        for (int i = 0; i < computerHandArray.length; i++) {
//            if (computerHandArray[i] != null) {
//                compString += computerHandArray[i] + ", ";
//            }
//        }
//        System.out.println("Here's what the computer has: " + compString);

        //What will the user do?
        System.out.println("What do you think the computer has? ");
        userAsk = stringScanner.nextLine().toLowerCase();

        // Check if the computer has that card
        cardsHad = askForCards(userHandArray, computerHandArray, userAsk);
        System.out.println("The computer had " + cardsHad + " " + userAsk + "s");

        // Close scanners
        numberScanner.close();
        stringScanner.close();
    }

    // Method to display what a user has //
    public static void getHandString(String[] userHandArray) {
        String cardString = "";
        for (int i = 0; i < userHandArray.length; i++) {
            if (userHandArray[i] != null) {
                // Create string of what is in the users hand
                cardString += userHandArray[i] + ", ";
            }
        }
        System.out.println("Here is what you have: " + cardString);
    }

    // Method for when a user asks another what card they have //
    public static int askForCards(String[] askHandArray, String[] askedCardArray, String askedCard) {
        int numberOfCardsHad = 0;
        for (int i = 0; i < askedCardArray.length; i++) {
            // Checks if the card the user asked is in the other hand
            if (askedCard.equalsIgnoreCase((String)askedCardArray[i])){
                // Finds the first open slot in the users hand
                for (int j = 0; j < askHandArray.length; j++) {
                    // If the slot of empty
                    if (askHandArray[j] == null){
                        // Set that empty spot to the asked card
                        askHandArray[j] = askedCard;
                        // Remove card from other hand
                        askedCardArray[i] = null;
                        numberOfCardsHad ++;
                        break;
                    }
                }
            }
        }
        return numberOfCardsHad;
    }

    // Method to draw initial cards //
    private static String[] drawCards(String[] drawingArray, String[] deckArray, int drawAmount) {
        // Create user hand
        String userHand[] = new String[52];
        // Draw cards for user
        for (int i = 0; i < drawAmount; i++) {
            // Keep trying to draw a card until an available random one is chosen
            while (true) {
                int randomCard = randomNumber.nextInt(52);
                // Check if card has been drawn already
                if ( !deckArray[randomCard].equalsIgnoreCase("")) {
                    userHand[i] = deckArray[randomCard];
                    deckArray[randomCard] = "";
                    break;
                }
            }
        }
        return userHand;
    }
    // Method to create the deck //
    public static String[] createDeck() {

        // Create array to track each card in the deck
        String deckCreatorArray[] = new String[52];
        // Current card slot
        int cardSlot = 0;
        // todo Make game work with capitalised card names
        // todo Capitalisation involves: http://stackoverflow.com/questions/5725892/how-to-capitalize-the-first-letter-of-word-in-a-string-using-java
        String cardNames[] = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
        for (String card:cardNames) {
            int iteration = 0;
            while(iteration < 4){
                deckCreatorArray[cardSlot] = card;
                cardSlot ++;
                iteration ++;
            }
        }
        return deckCreatorArray;
    }

}
