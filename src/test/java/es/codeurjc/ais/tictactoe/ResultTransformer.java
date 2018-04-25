package es.codeurjc.ais.tictactoe;

import cucumber.api.Transformer;

public class ResultTransformer extends Transformer<Results>{

    public Results transform(String result) {
        switch (result.toLowerCase()) {
            case "player_one wins" : return Results.WIN_X;
            case "player_two wins" : return Results.WIN_O;
            case "draw" : return Results.DRAW;
            default: throw new RuntimeException();
        }
    }
}
