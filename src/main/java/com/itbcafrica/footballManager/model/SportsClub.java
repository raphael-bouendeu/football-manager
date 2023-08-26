package com.itbcafrica.footballManager.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public abstract class SportsClub{
    private String name;
    private String location;
    private  String statistics;

}
