/*a) Nikita Shetty
  b) 2350802
  c) nshetty@chapman.edu
  d) CPSC 231-01 - Dr. Elizabeth Stevens
  e) Mastery Project 5: Modern Warfare
  Game.Java */

import java.util.*;
import java.lang.Math;

//creating Game class
public class Game{
  //member variable
  public Player m_p1;
  public Player m_p2;
  private Deck m_gameDeck;
  private LinkedList<Card> m_p1Cards;
  private LinkedList<Card> m_p2Cards;
  private LinkedList<Card> m_p1PlayedCards;
  private LinkedList<Card> m_p2PlayedCards;
  public String m_result;
  public int m_battleNum;
  public int m_warNum;
  public int m_doubleWarNum;

  //default constructor
  public Game(){
    m_gameDeck = new Deck();
    m_p1Cards = new LinkedList<Card>();
    m_p2Cards = new LinkedList<Card>();
    m_p1PlayedCards = new LinkedList<Card>();
    m_p2PlayedCards = new LinkedList<Card>();
    m_p1 = new Player(1, m_p1Cards);
    m_p2 = new Player(2, m_p2Cards);
    m_battleNum = 0;
    m_warNum = 0;
    m_doubleWarNum = 0;
    m_result = null;

    //dealing 26 cards to each player
    for(int i = 0; i <26; i++){
      m_p1Cards.add(m_gameDeck.deal());
      m_p2Cards.add(m_gameDeck.deal());
    }
  }

  //Overloaded Constructor
  public Game(Deck gameDeck, Player p1, Player p2){
    m_gameDeck = gameDeck;
    m_p1 = p1;
    m_p2 = p2;
  }

  //accessors and mutators for member variables
  public int getBattleNum() { return m_battleNum; }
  public void setBattleNum(int battleNum) { m_battleNum = battleNum; }
  public int getWarNum() { return m_warNum; }
  public void setWarNum(int warNum) { m_warNum = warNum; }
  public int getDoubleWarNum() { return m_doubleWarNum; }
  public void setDoubleWarNum(int doubleWarNum) { m_doubleWarNum = doubleWarNum; }

  //play() - plays a full game of War until a player has won all 52 cards
  public void play(){
    int p1Card, p2Card;
    //dealing cards to the players
    while (!m_p1.hasWon() && !m_p2.hasWon() && m_p1Cards.size() > 0 && m_p2Cards.size() > 0 ){
      m_battleNum++;

      //Playing 3 cards if the player has at least 3 in their deck
      for(int i = 0; i < 3; i++) {
        if (m_p1Cards.size() != 0) {
          m_p1PlayedCards.add(m_p1.flip());
        }
      }
      for(int j = 0; j < 3; j++) {
        if (m_p2Cards.size() != 0) {
          m_p2PlayedCards.add(m_p2.flip());
        }
      }

      //Creating Array versions of the playedCards for WarLogger
      Card[] p1Array = new Card[m_p1PlayedCards.size()];
      Card[] p2Array = new Card[m_p2PlayedCards.size()];
      for(int i = 0; i < m_p1PlayedCards.size(); i++){
        p1Array[i] = m_p1PlayedCards.get(i);
      }
      for(int i = 0; i < m_p2PlayedCards.size(); i++){
        p2Array[i] = m_p2PlayedCards.get(i);
      }
      WarLogger.getInstance().logBattle(m_battleNum, WarLogger.P1, p1Array);
      WarLogger.getInstance().logBattle(m_battleNum, WarLogger.P2, p2Array);

      //Finding the median of the 3 player 1 played cards
      if(m_p1PlayedCards.size() >= 3) {
        p1Card = Math.max(Math.min(m_p1PlayedCards.get(0).getCardValue(),
        m_p1PlayedCards.get(1).getCardValue()),
        Math.min(Math.max(m_p1PlayedCards.get(0).getCardValue(),
        m_p1PlayedCards.get(1).getCardValue()),m_p1PlayedCards.get(2).getCardValue()));
      }
      //finding the max of the cards if less than 3 are held
      else {
        p1Card = 0;
        for(Card c : m_p1PlayedCards){
          if(c.getCardValue() >= p1Card)
            p1Card = c.getCardValue();
        }
      }

      //Finding the median of the 3 player 2 played cards
      if(m_p2PlayedCards.size() >= 3) {
        p2Card = Math.max(Math.min(m_p2PlayedCards.get(0).getCardValue(),
        m_p2PlayedCards.get(1).getCardValue()),
        Math.min(Math.max(m_p2PlayedCards.get(0).getCardValue(),
        m_p2PlayedCards.get(1).getCardValue()),m_p2PlayedCards.get(2).getCardValue()));
      }

      //finding the max of the cards if less than 3 are held
      else {
        p2Card = 0;
        for(Card c : m_p2PlayedCards){
          if(c.getCardValue() >= p2Card)
            p2Card = c.getCardValue();
        }
      }

      //checking if player 1 won the battle
      if(p1Card > p2Card){
        m_result = "Player 1";
        m_p1.collect(m_p1PlayedCards);
        m_p1.collect(m_p2PlayedCards);
        WarLogger.getInstance().logBattleOutcome(m_battleNum, WarLogger.P1);
      }
      //checking if player 2 won the battle
      else if(p2Card > p1Card){
        m_result = "Player 2";
        m_p2.collect(m_p1PlayedCards);
        m_p2.collect(m_p2PlayedCards);
        WarLogger.getInstance().logBattleOutcome(m_battleNum, WarLogger.P2);
      }

      //checking if there is a tie
      else if(p1Card == p2Card){
        //if both players have cards, call war()
        if (m_p1Cards.size() >= 1 && m_p2Cards.size() >= 1){
          WarLogger.getInstance().logBattleOutcome(m_battleNum, WarLogger.WAR);
          m_result = war();
        }

        //if player 1 has no more cards, player 2 wins by default
        else if(m_p2.hasWon()){
          m_result = WarLogger.P2;
          WarLogger.getInstance().logBattleOutcome(m_battleNum, WarLogger.P2);
          break;
        }

        //if player 2 has no more cards, player 1 wins by default
        else if(m_p1.hasWon()){
          m_result = WarLogger.P1;
          WarLogger.getInstance().logBattleOutcome(m_battleNum, WarLogger.P1);
          break;
        }
      }
    }
  }

  //war() - takes one card from each player and returns the winner of the war (higher value)
  public String war(){
    m_warNum++;
    //declaring 2 cards for each player and adding it to their playedCard lists
    Card warP1Card = m_p1.flip();
    Card warP2Card = m_p2.flip();
    m_p1PlayedCards.add(warP1Card);
    m_p2PlayedCards.add(warP2Card);

    //if player 1 has the higher value, collect all played cards and return P1
    if (warP1Card.getCardValue() > warP2Card.getCardValue()){
      m_p1.collect(m_p1PlayedCards);
      m_p1.collect(m_p2PlayedCards);
      WarLogger.getInstance().logWarOutcome(m_warNum, WarLogger.P1);
      return WarLogger.P1;
    }

    //if player 2 has the higher value, collect all played cards and return P2
    else if(warP1Card.getCardValue() < warP2Card.getCardValue()){
      m_p2.collect(m_p1PlayedCards);
      m_p2.collect(m_p2PlayedCards);
      WarLogger.getInstance().logWarOutcome(m_warNum, WarLogger.P2);
      return WarLogger.P2;
    }

    //if both cards are equal and both players have cards, play a second war
    else if(warP1Card.getCardValue() == warP2Card.getCardValue()){
      if (m_p1Cards.size() >= 1 && m_p2Cards.size() >= 1){
        m_doubleWarNum++;
        WarLogger.getInstance().logWarOutcome(m_warNum, WarLogger.WAR);

        //initializing 2 cards for each player and adding it to their playedCard lists
        warP1Card = m_p1.flip();
        warP2Card = m_p2.flip();
        m_p1PlayedCards.add(warP1Card);
        m_p2PlayedCards.add(warP2Card);

        //if player 1 has the higher value, collect all played cards and return P1
        if (warP1Card.getCardValue() > warP2Card.getCardValue()){
          m_p1.collect(m_p1PlayedCards);
          m_p1.collect(m_p2PlayedCards);
          WarLogger.getInstance().logWarOutcome(m_warNum, WarLogger.P1);
          return WarLogger.P1;
        }

        //if player 2 has the higher value, collect all played cards and return P2
        else if(warP1Card.getCardValue() < warP2Card.getCardValue()){
          m_p2.collect(m_p1PlayedCards);
          m_p2.collect(m_p2PlayedCards);
          WarLogger.getInstance().logWarOutcome(m_warNum, WarLogger.P2);
          return WarLogger.P2;
        }
      }

      //if player 2 has no more cards, player 1 wins by default
      else if (m_p1.hasWon()){
        return WarLogger.P1;
      }

      //if player 1 has no more cards, player 2 wins by default
      else if (m_p2.hasWon()){
        return WarLogger.P1;
      }
    }
    return "";
  }

  //toString() - prints stats of the game to console
  public String toString() {
    return "\nNumber of Battles: " + m_battleNum +
    "\nNumber of Wars: " + m_warNum + "\nWinner: " + m_result;
  }
}
