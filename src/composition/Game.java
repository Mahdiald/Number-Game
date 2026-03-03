package composition;

import java.util.Random;

public class Game {
    private int answer;
    private boolean quit = false;
    private IO io = new IO();
    Player player;

    private ScoreBoard scoreBoard = new ScoreBoard();

    public void startGame(){
        //Velkommen til bruger (Regler: skriv "q" for at stoppe)
        io.sendMessage("velkommen til \n Gæt et tal");

        //spørg bruger om navn
        String playerName = io.promptString("Skriv dit navn");
        player = new Player(playerName);
        //Vi skal bruge composition.IO til dette

        boolean stillPlaying = true;


        //Vil du spille spil y/n
        String nytSpil = io.promptString("Nyt spil? \n y/n");
        //hvis bruger input er y så kør nyt spil
        if (nytSpil.equals("y")){
            while(stillPlaying) {
                io.sendMessage("Du skal gæt et tal mellem 1 til 100");
                //opret et answer
                Random random = new Random();
                answer = random.nextInt(100) + 1;
                playGame();
                String again = io.promptString("Vil du spille igen? \n y/n");
                if(!again.equalsIgnoreCase("y")){
                    stillPlaying = false;
                }
            }
            io.sendMessage("Tak for spillet!");
        }

    }


    private void playGame(){
        boolean guessed = false;
        int noOfGuesses = 0;

        while (!guessed){
            //Bed spiller om gæt
            //Brug composition.IO om dette
            int result = io.promptInt("Indtast dit gæt");
            //Hold styr på antal gæt
            noOfGuesses++;
            //Tre muligheder: korrekt, for højt, for lavt.
            //Hvis korrekt: Fortæl bruger det er korrekt
            //Tildel score til player
            //returner til startGame

            //Hvis det er for lavt eller for højt
            //Informere vi brugeren om dette
            //Giv brugeren mulighed for at gætte igen

            if (result == answer){
                guessed = true;
                player.setScore(noOfGuesses);
                io.sendMessage("\uD83C\uDF8A Tilykke " + player.getName() + ". Du brugte " + noOfGuesses + " gæt!");
            }

            else if (result > answer){
                io.sendMessage("Desværre, det var for højt.");
            } else if (result < answer) {
                io.sendMessage("Desværre, det var for lavt.");
            }


        }
        scoreBoard.addPlayer(player);
        scoreBoard.printTopTen();

    }
}