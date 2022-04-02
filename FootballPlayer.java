// Creation method
/* replace constructors wiht Creation Methods
- which constructor should be called 
- which constructors had descriptive name
- constructors cant have the same attributes signatures
*/

public class FootballPlayer {

    private double passerRating; //Scpecific to QBs
    private int rushingYards; //Scpecific to RBs and QBs
    private int receivingYards; //Scpecific to RBs and WRs
    private int totalTackles; //Scpecific to DEF
    private int interceptions; //Scpecific to DEF
    private int fieldGoals; //Scpecific to Kickers
    private double avgPunt; //Scpecific to Punters
    private double avgKickoffReturn; //Scpecific to Special Teeams 
    private double avgPuntReturn; //Scpecific to Special Teams

/*
    FootballPlayer(double passerRating, int rushingYards){

        this.passerRating = passerRating;
        this.rushingYards = rushingYards;

    }

    FootballPlayer(int rushingYards){

        this.rushingYards = rushingYards;
        
    }

    // attribute signature is the same as the above code, both are using int -> FootballPlayer(int rushingYards)
    FootballPlayer(int receivingYards){

        this.receivingYards = receivingYards;
        
    }

*/

    // hence to solve this problem:
    private FootballPlayer(double passerRating, int rushingYards, int receivingYards, int totalTackles, 
                            int interceptions, int fieldGoals, double avgPunt, double avgKickoffReturn, 
                            double avgPuntReturn){

        this.passerRating = passerRating;
        this.rushingYards = rushingYards;
        this.receivingYards = receivingYards;
        this.totalTackles = totalTackles;
        this.interceptions = interceptions;
        this.fieldGoals = fieldGoals;
        this.avgPunt = avgPunt;
        this.avgKickoffReturn = avgKickoffReturn;
        this.avgPuntReturn = avgPuntReturn;

    }

    public double getPasserRating(){return passerRating;}

    public static FootballPlayer createQB(double passerRating, int rushingYards){

        return new FootballPlayer(passerRating, rushingYards, 0, 0, 0, 0, 0.0, 0.0, 0.0);

    }

    public static void main(String[] args){

        FootballPlayer aaronRodgers = FootballPlayer.createQB(108.0, 259);

        System.out.println("Aaron Rodgers Passer Rating: " + aaronRodgers.getPasserRating());
    }   

}