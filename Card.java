/*a) Nikita Shetty
  b) 2350802
  c) nshetty@chapman.edu
  d) CPSC 231-01 - Dr. Elizabeth Stevens
  e) Mastery Project 5: Modern Warfare
  Card.Java */

//Creating Card class
public class Card{
  //Declaring member variables
  public static String[] m_values = new String[]{"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
  public static String[] m_suits = new String[]{"hearts", "spades", "diamonds", "clovers"};
  private String m_cardValue;
  private String m_cardSuit;

  //default Constructor
  public Card(){
    m_cardValue = "2";
    m_cardSuit = "hearts";
  }

  //Overloaded Constructor
  public Card(String cardValue, String cardSuit){
    m_cardValue = cardValue;
    m_cardSuit = cardSuit;
  }

  //accessors and mutators for member variables
  public int getCardValue() {
    if(m_cardValue.equals("J"))
      return 11;
    if(m_cardValue.equals("Q"))
      return 12;
    if(m_cardValue.equals("K"))
      return 13;
    if(m_cardValue.equals("A"))
      return 14;
    return Integer.parseInt(m_cardValue);
  }

  public void setCardValue(String cardValue) { m_cardValue = cardValue; }

  public String getCardSuit() { return m_cardSuit; }

  public void setCardSuit(String cardSuit) { m_cardSuit = cardSuit; }

  //toString() - returns the Card properties as a String
  public String toString(){
    return ("Value: " + m_cardValue + ", Suit: " + m_cardSuit + "\n");
  }

  //equals() - checks if two cards are equal
  public boolean equals(Card c){
    return(m_cardValue.equals(c.m_cardValue) && m_cardSuit.equals(c.m_cardSuit));
  }
}
