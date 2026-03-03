package composition;
import java.util.ArrayList;

public class ScoreBoard {

    ArrayList<Player> players;

    public ScoreBoard(){
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void printTopTen(){
        System.out.println("=== Top 10 ===");

        ArrayList<Player> temp = new ArrayList<>(players);

        int max = Math.min(10, temp.size());

        for (int i = 1; i <= max; i++){
            int bestIndex = 0;
            for (int j = 1; j < temp.size(); j++){
                if(temp.get(j).getScore() < temp.get(bestIndex).getScore()){
                    bestIndex = j;
                }
            }

            Player bestPlayer = temp.remove(bestIndex);

            System.out.println(i + ". " + bestPlayer.getName() + "\t" + bestPlayer.getScore() + " gæt");
        }
    }

    public Player findPlayer(String name){
        for(Player p : players){

            if (p.getName().equalsIgnoreCase(name)){
                return p;
            }
        }

        return null;
    }

}
