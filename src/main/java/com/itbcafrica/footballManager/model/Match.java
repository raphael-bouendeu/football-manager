package com.itbcafrica.footballManager.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Match{
    private FootballClub TeamA;
    private FootballClub TeamB;
    private int teamAScore;
    private int teamBScore;
    private Date date;

}
