/*a) Nikita Shetty
  b) 2350802
  c) nshetty@chapman.edu
  d) CPSC 231-01 - Dr. Elizabeth Stevens
  e) Mastery Project 5: Modern Warfare
  Deck.Java */

import java.util.LinkedList;
import java.util.Random;
import java.lang.Integer;

//creating Deck class
public class Deck{
  //member variables
  public LinkedList<Card> m_cardDeck;

  //Default Constructor
  public Deck(){
    m_cardDeck = new LinkedList<Card>();
    //populating the deck with each Card possible
    for (int i = 0; i < Card.m_suits.length; i++) {
      for (int j = 0; j < Card.m_values.length; j++) {
        m_cardDeck.add(new Card(Card.m_values[j], Card.m_suits[i]));
      }
    }
  }

  //Overloaded Constructor
  public Deck(LinkedList<Card> cardDeck){
    m_cardDeck = cardDeck;
  }

  //deal() - removes a Card from the deck and returns it
  public Card deal(){
    Random rand = new Random();
    int randNum = rand.nextInt(m_cardDeck.size());
    return m_cardDeck.remove(randNum);
  }

  //toString() - returns the deck as a string
  public String toString(){
    String deck = "";
    for(int i = 0; i < m_cardDeck.size(); i++){
      deck += m_cardDeck.get(i).toString();
    }
    return deck;
  }

  public boolean equals(Deck d){
    boolean equals;
    for(int i = 0; i < m_cardDeck.size(); i++){
      if(m_cardDeck.get(i).equals(d.m_cardDeck.get(i)))
        equals = true;
      else
        return false;
    }
    return true;
  }

  public static void main(String[] args) {
    Deck deck1 = new Deck();
    Card tempCard = deck1.deal();
    // System.out.println(deck1);
    System.out.println(tempCard);
    System.out.println(tempCard.getCardValue());
  }
}
