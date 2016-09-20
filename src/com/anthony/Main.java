package com.anthony;
// todo Allow computer to make a play also and allow more than one round to be had.
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;

public class Main {
    private static Scanner numberScanner = new Scanner(System.in);
    private static Scanner stringScanner = new Scanner(System.in);
    private static Random randomNumber = new Random();
    public static void main(String[] args) {
        /* Go Fish Game 2 player*/
        // Create arrays for player, computer, and cards in the deck
        /* Starting hand size */
        int handSize = 12;
        // Create deck array
        ArrayList<String> deck = new ArrayList<String>();
        // Create deck
        createDeck(deck);
        // Create hands
        HashMap<String, String[]> hands = new HashMap<String, String[]>();
        String userHandArray[] = new String[52];
        String computerHandArray[] = new String[52];
        // Initial draw for each hand
        userHandArray = drawCards(userHandArray, deck, handSize);
        computerHandArray = drawCards(computerHandArray, deck, handSize);
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
    private static void getHandString(String[] userHandArray) {
        String cardString = "";
        for (String anUserHandArray : userHandArray) {
            if (anUserHandArray != null) {
                // Create string of what is in the users hand
                cardString += anUserHandArray + ", ";
            }
        }
        System.out.println("Here is what you have: " + cardString);
    }

    // Method for when a user asks another what card they have //
    private static int askForCards(String[] askHandArray, String[] askedCardArray, String askedCard) {
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
    private static String[] drawCards(String[] drawingArray, ArrayList<String> deckArray, int drawAmount) {
        // Create user hand
        String userHand[] = new String[52];
        // Draw cards for user
        for (int i = 0; i < drawAmount; i++) {
            // Keep trying to draw a card until an available random one is chosen
            while (true) {
                int randomCard = randomNumber.nextInt(deckArray.size());
                // Check if card has been drawn already
                if ( ! deckArray.get(randomCard).equalsIgnoreCase("")) {
                    userHand[i] = deckArray.get(randomCard);
                    deckArray.remove(randomCard);
                    break;
                }
            }
        }
        return userHand;
    }
    // Method to create deck //
    private static void createDeck(ArrayList<String> deck) {
        // Generate suit and card
        int suit = 0;
        int card = 1;
        while (true) {
            // Get random suit
            String suitLetter;
            if (suit == 0) {
                suitLetter = "Hearths";
            } else if (suit == 1) {
                suitLetter = "Diamonds";
            } else if (suit == 2) {
                suitLetter = "Clubs";
            } else {
                suitLetter = "Spades";
            }
            while (true) {
                // Get random card value
                String cardFace;
                if (card == 1) {
                    cardFace = "Ace";
                    card ++;
                } else if (card > 1 && card < 11) {
                    cardFace = "" + card;
                    card ++;
                } else if (card == 11) {
                    cardFace = "Jack";
                    card ++;
                } else if (card == 12) {
                    cardFace = "Queen";
                    card ++;
                } else {
                    cardFace = "King";
                    card = 1;
                    suit ++;
                }
                deck.add(cardFace);
                if (cardFace.equalsIgnoreCase("King")){
                    break;
                }
            }
            if (suit == 4){
                break;
            }
        }
    }

}
