/*a) Nikita Shetty
  b) 2350802
  c) nshetty@chapman.edu
  d) CPSC 231-01 - Dr. Elizabeth Stevens
  e) Mastery Project 5: Modern Warfare
  Player.Java */

import java.util.LinkedList;
import java.util.Random;

//creating Player class
public class Player{
  //declaring member variables
  private int m_playerNum;
  public LinkedList<Card> m_ownedCards;
  private int size;

  //Default Constructor
  public Player(){
    m_playerNum = 1;
    m_ownedCards = new LinkedList<Card>();
  }

  //Overloaded Constructor
  public Player(int playerNum, LinkedList<Card> ownedCards){
    m_playerNum = playerNum;
    m_ownedCards = ownedCards;
  }

  public int getPlayerNum() { return m_playerNum; }
  public void setPlayerNum(int playerNum) { m_playerNum = playerNum; }

  //flip() - plays one of the player's cards
  public Card flip(){
    return m_ownedCards.remove(0);
  }

  //collect() - adds all the given cards to player's hand
  public void collect(LinkedList<Card> newCards){
    Random rand = new Random();
    int randNum;
    size = newCards.size();
    for(int i = 0; i < size; i++){
      if(m_ownedCards.size() != 0)
        randNum = rand.nextInt(m_ownedCards.size());
      else
        randNum = 0;
      m_ownedCards.add(randNum, newCards.remove(0));
    }
  }

  //hasWon() - checks if the player has 52 cards and has won the game
  public boolean hasWon(){
    if(m_ownedCards.size() == 52){
      return true;
    }
    else{
      return false;
    }
  }

  //toString() - returns the player's deck as a string
  public String toString(){
    String playerDeck = "";
    for(int i = 0; i < m_ownedCards.size(); i++){
      playerDeck += m_ownedCards.get(i).toString();
    }
    return playerDeck;
  }
}
