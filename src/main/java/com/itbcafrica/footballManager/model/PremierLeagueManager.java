package com.itbcafrica.footballManager.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class PremierLeagueManager implements LeagueManager{

    private final int numberOfClubs;
    private final List<FootballClub> league;
    private final Scanner scanner;
    private final List<Match> matches;

    public PremierLeagueManager(int numberOfClubs){
        this.numberOfClubs=numberOfClubs;
        league=new ArrayList<>();
        matches=new ArrayList<>();
        scanner=new Scanner(System.in);
        displayMenu();
    }

    private void displayMenu(){
        while(true){
            System.out.println("Premier League Menu: ");
            System.out.println("Create new team and add ut to league (press 1)");
            System.out.println("Delete existing team from league (press 2)");
            System.out.println("Display Statistic for team (press 3)");
            System.out.println("Display the Premier League Table(press 4)");
            System.out.println("Add a Player Match(press 5)");
            System.out.println("Display Calendar  and Find Match(press 6)");
            String line=scanner.nextLine();
            int command=0;
            try{
                command=Integer.parseInt(line);
            } catch(Exception e){

            }
            switch(command){
                case 1:
                    addTeam();
                    break;
                case 2:
                    deleteTeam();
                    break;

                case 3:
                    displayStatistic();
                    break;

                case 4:
                    displayLeagueTable();
                    break;

                case 5:
                    addPlayedMatch();
                    break;

                default:
                    System.out.println("Wrong Command: ");
            }
        }
    }


    private void addPlayedMatch(){
        System.out.println("Enter date( format mm-dd-yyyy ):");
        String line=scanner.nextLine();
        Date date=null;
        try{
            date=new SimpleDateFormat("MM-dd-yyyy").parse(line);
        } catch(ParseException e){
            System.out.println("you have to enter date in format mm-dd-yyyy");
        }
        System.out.println("Enter Home Team");
        line=scanner.nextLine();
        FootballClub home=null;
        for(FootballClub club : league){
            if(club.getName().equals(line)){
                home=club;
            }
        }
        if(home==null){
            System.out.println("Home team not exist in league");
            return;
        }
        System.out.println("Enter Receive Team");
        line=scanner.nextLine();
        FootballClub receipteTeam=null;
        for(FootballClub club : league){
            if(club.getName().equals(line)){
                receipteTeam=club;
            }
        }
        if(receipteTeam==null){
            System.out.println("Receive team not exist in league");
            return;
        }

        System.out.println("Enter Home team goals: ");
        line=scanner.nextLine();
        int homeGoals=-1;
        try{
            homeGoals=Integer.parseInt(line);
        } catch(Exception e){
            e.printStackTrace();
        }
        if(homeGoals==-1){
            System.out.println("You have to enter number of goals");
        }
        System.out.println("Enter received  team goals: ");
        line=scanner.nextLine();
        int receiveGoals=-1;
        try{
            receiveGoals=Integer.parseInt(line);
        } catch(Exception e){
            e.printStackTrace();
        }
        if(receiveGoals==-1){
            System.out.println("You have to enter number of goals");
        }
        Match match=new Match();
        match.setTeamB(receipteTeam);
        match.setTeamA(home);
        match.setDate(date);
        match.setTeamAScore(homeGoals);
        match.setTeamBScore(receiveGoals);
        matches.add(match);
        home.setScoredGoalsCount(home.getScoredGoalsCount()+homeGoals);
        receipteTeam.setReceivedGoalsCount(receipteTeam.getReceivedGoalsCount()+receiveGoals);
        home.setReceivedGoalsCount(home.getReceivedGoalsCount()+receiveGoals);
        receipteTeam.setScoredGoalsCount(receipteTeam.getScoredGoalsCount()+receiveGoals);
        home.setMatchesPlayed(home.getMatchesPlayed()+1);
        receipteTeam.setMatchesPlayed(receipteTeam.getMatchesPlayed()+1);
        if(homeGoals>receiveGoals){
            home.setPoints(home.getPoints()+3);
            home.setWinCount(home.getWinCount()+1);
            receipteTeam.setDefeatCount(receipteTeam.getDefeatCount()+1);
        } else if(homeGoals<receiveGoals){

            receipteTeam.setPoints(receipteTeam.getPoints()+3);
            receipteTeam.setWinCount(receipteTeam.getWinCount()+1);
            home.setDefeatCount(home.getDefeatCount()+1);

        } else{
            receipteTeam.setPoints(receipteTeam.getPoints()+1);
            home.setPoints(home.getPoints()+1);
            receipteTeam.setDrowCount(receipteTeam.getDrowCount()+1);
            home.setDrowCount(home.getDrowCount()+1);
        }
    }

    private void displayLeagueTable(){
        league.sort(new CustomComparator());
        for(FootballClub club : league){
            System.out.println("Club: "+club.getName()+" Points "+club.getPoints()+" Goal difference: "+(club.getScoredGoalsCount()-club.getReceivedGoalsCount()));
        }
    }

    private void displayStatistic(){
        System.out.println("Insert club name: ");
        String line=scanner.nextLine();
        for(FootballClub club1 : league){
            if(club1.getName().equals(line)){

                System.out.println("Club "+club1.getName()+" matches won: "+club1.getWinCount());
                System.out.println("Club "+club1.getName()+" matches lost: "+club1.getDefeatCount());
                System.out.println("Club "+club1.getName()+" matches draw: "+club1.getDrowCount());
                System.out.println("Club "+club1.getName()+" scored goals: "+club1.getScoredGoalsCount());
                System.out.println("Club "+club1.getName()+" received  goals: "+club1.getReceivedGoalsCount());
                System.out.println("Club "+club1.getName()+" points: "+club1.getPoints());
                System.out.println("Club "+club1.getName()+" matches played: "+club1.getMatchesPlayed());
                return;
            }
        }
        System.out.println("Club exist not in the League");
    }

    private void deleteTeam(){
        System.out.println("Insert Club Name: ");
        String line=scanner.nextLine();
        for(FootballClub club1 : league){
            if(club1.getName().equals(line)){
                league.remove(club1);
                System.out.println("Club "+club1.getName()+" was deleted");
                return;
            }
        }
        System.out.println("Club exist not in the League");


    }

    private void addTeam(){
        if(league.size()==numberOfClubs){
            System.out.println("Can not add more Clubs to League: ");
            return;
        }
        FootballClub club=new FootballClub();
        System.out.println("Insert Club Name: ");
        String line=scanner.nextLine();
        club.setName(line);
        for(FootballClub club1 : league){
            if(club1.getName().equals(line)){
                System.out.println("Club exist already in the League");
                return;
            }
        }

        System.out.println("Insert Club Location: ");
        line=scanner.nextLine();
        club.setLocation(line);
        league.add(club);

    }
}
