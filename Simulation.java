/*a) Nikita Shetty
  b) 2350802
  c) nshetty@chapman.edu
  d) CPSC 231-01 - Dr. Elizabeth Stevens
  e) Mastery Project 5: Modern Warfare
  Simulation.Java */

import java.lang.Math;

//Creating Simulation class
public class Simulation{
  //member variables
  private int m_numSims;
  private double m_totalBattles;
  private double m_avgNumBattles;
  private double m_totalWars;
  private double m_avgNumWars;
  private double m_totalDoubleWars;
  private double m_avgNumDoubleWars;
  private int m_maxBattles;
  private int m_minBattles;
  private int m_maxWars;
  private int m_minWars;

  //default Constructor
  public Simulation(){
    m_numSims = 1;
    m_totalBattles = 0;
    m_totalWars = 0;
    m_totalDoubleWars = 0;
    m_avgNumWars = 0;
    m_avgNumBattles = 0;
    m_avgNumDoubleWars = 0;
    m_maxBattles = 0;
    m_maxWars = 0;
    simulate(5);
  }

  //Overloaded Constructor
  public Simulation(int numSims){
    m_numSims = numSims;
    m_minBattles = 5000;
    m_minWars = 5000;
    simulate(numSims);
  }

  //accessors and mutators for member variables
  public int getNumSims() { return m_numSims; }
  public void setNumSims(int numSims) { m_numSims = numSims; }

  public double getTotalBattles() { return m_totalBattles; }
  public void setTotalBattles(double totalBattles) { m_totalBattles = totalBattles; }

  public double getAvgNumBattles() { return m_avgNumBattles; }
  public void setNumSims(double avgNumBattles) { m_avgNumBattles = avgNumBattles; }

  public double getTotalWars() { return m_totalWars; }
  public void setTotalWars(double totalWars) { m_totalWars = totalWars; }

  public double getAvgNumWars() { return m_avgNumWars; }
  public void setAvgNumWars(double avgNumWars) { m_avgNumWars = avgNumWars; }

  public double getTotalDoubleWars() { return m_totalDoubleWars; }
  public void setTotalDoubleWars(double totalDoubleWars) { m_totalDoubleWars = totalDoubleWars; }

  public double getAvgNumDoubleWars() { return m_avgNumDoubleWars; }
  public void setAvgNumDoubleWars(double avgNumDoubleWars) { m_avgNumDoubleWars = avgNumDoubleWars; }

  public int getMaxBattles() { return m_maxBattles; }
  public void setMaxBattles(int maxBattles) { m_maxBattles = maxBattles; }

  public int getMinBattles() { return m_minBattles; }
  public void setMinBattles(int minBattles) { m_minBattles = minBattles; }

  public int getMaxWars() { return m_maxWars; }
  public void setMaxWars(int maxWars) { m_maxWars = maxWars; }

  public int getMinWars() { return m_minWars; }
  public void setMinWars(int minWars) { m_minWars = minWars; }

  //simulate() - creates a simulation of number of games given
  public void simulate(int numSims){
    for(int i = 0; i < numSims; ++i){
      //System.out.print(i + " ");
      Game game = new Game();
      game.play();
      calculate(game);
    }
  }

  //calculate() - finds the statistics for the games
  public void calculate(Game game){
    m_totalBattles += game.getBattleNum();
    m_avgNumBattles = m_totalBattles / m_numSims;

    m_totalWars += game.getWarNum();
    m_avgNumWars = m_totalWars / m_numSims;

    m_totalDoubleWars += game.getDoubleWarNum();
    m_avgNumDoubleWars = m_totalDoubleWars / m_numSims;

    if(game.getBattleNum() > m_maxBattles){
       m_maxBattles = game.getBattleNum();
    }
    if(game.getBattleNum() < m_minBattles){
       m_minBattles = game.getBattleNum();
    }
    if(game.getWarNum() > m_maxWars){
       m_maxWars = game.getWarNum();
    }
    if(game.getWarNum() < m_minWars){
       m_minWars = game.getWarNum();
    }
  }

  //report() - Prints the statistics to the console nicely
  public void report(){
    System.out.println("------------------------------------------");
    System.out.println("\nTotal Battles: " + m_totalBattles);
    System.out.println("Average number of battles per game: " + Math.round(m_avgNumBattles * 100.0) / 100.0);
    System.out.println("Average number of wars per game: " + Math.round(m_avgNumWars * 100.0) / 100.0);
    System.out.println("Average number of double wars per game: " + Math.round(m_avgNumDoubleWars * 100.0) / 100.0);
    System.out.println("Max number of battles in a game: " + m_maxBattles);
    System.out.println("Min number of battles in a game: " + m_minBattles);
    System.out.println("Max number of wars in a game: " + m_maxWars);
    System.out.println("Min number of wars in a game: " + m_minWars);
    System.out.println("\n------------------------------------------");
  }

  /* Main method - takes in command line number for amount of games,
  makes an instance of simulation and calls report()*/
  public static void main(String[] args) {
    int numSims = Integer.parseInt(args[0]);
    Simulation s1 = new Simulation(numSims);
    s1.report();
    WarLogger.getInstance().release();
  }

}
