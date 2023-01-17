package Common;
/*
This is the class to maintain different properties of IPL team
name: to store team name
location: to store team location
playerList: to store list of all players of a team
Creator: Abhishek Paul
Date: 17-01-2023
 */

import java.util.ArrayList;
import java.util.List;

public class IPLTeam {
    String name, location;
    List<Player> playerList;

    public void addPlayer(String name, String coutry, String role, String price) {
        Player player = new Player(name, coutry, role, price);
        playerList.add(player);
    }

    public IPLTeam(String teamName, String teamLocation) {
        this.name=teamName;
        this.location=teamLocation;
        playerList=new ArrayList<>();
    }
}
