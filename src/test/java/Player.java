/*
this is the class to maintain different properties of a player
Creator: Abhishek Paul
Date: 17-01-2023
 */

public class Player {
    String playerName;
    String playerCountry;
    String playerRole;
    String playerPrice;

    public Player(String name, String country, String role, String price) {
        this.playerName=name;
        this.playerCountry=country;
        this.playerRole=role;
        this.playerPrice=price;
    }
}
