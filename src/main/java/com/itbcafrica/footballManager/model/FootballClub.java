package com.itbcafrica.footballManager.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FootballClub extends SportsClub{
    private int winCount;
    private int drowCount;
    private int defeatCount;
    private int scoredGoalsCount;
    private int receivedGoalsCount;
    private int points;
    private int matchesPlayed;
}
