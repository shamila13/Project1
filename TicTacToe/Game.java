package game;
import player.Player;
import board.Board;
import java.util.Scanner;

public class Game {
    Player [] players;
    Board board;
    int turn;
    int noOfMoves;
    boolean gameOver;
    String zero;
    String cross;

    public Game(Player [] players,Board board){
        this.players=players;
        this.board=board;
        this.turn=0;
        this.noOfMoves=0;
        this.gameOver=false;

        StringBuilder z=new StringBuilder();
        StringBuilder c=new StringBuilder();
        
        for(int i=0;i<board.size;i++){
            z.append('O');
            c.append('X');
        }
        this.zero=z.toString();
        this.cross=c.toString();
    }
    public void printBoardConfig(){
        int sz=this.board.size;
        for(int i=0;i<sz;i++){
            for(int j=0;j<sz;j++){
                System.out.print(board.matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
    public void play(){
        printBoardConfig();
        int sz=board.size;

        while(!gameOver){
            noOfMoves++;
            int idx=getIndex();

            int row=idx/sz;
            int column=idx%sz;

            board.matrix[row][column] = players[turn].getPlayerSymbol();
            if(noOfMoves >=sz*sz){
                System.out.println("Game Draw");
                return;
            }
            if(noOfMoves >=2*sz-1 && checkCombinations()==true){
                System.out.println("Winner is: "+players[turn].getPlayerName());
                return;
            }
            turn=(turn+1)%2;
            printBoardConfig();
        }
    }
    public int getIndex(){
        while(true){
            System.out.println("Player: " + players[turn].getPlayerName() + " give one position");
            Scanner sc=new Scanner(System.in);
            int pos = sc.nextInt()-1;

            int sz=board.size;
            int row=pos/sz;
            int column=pos%sz;

            if(row<0 || row>=sz || column<0 || column>=sz){
                System.out.print("Invalid position");
                continue;
            }
            if(board.matrix[row][column]!='-'){
                System.out.print("Position already occupied");
                continue;
            }
            return pos;
        }
    }
    public boolean checkCombinations(){
        int sz=board.size;
        for(int i=0;i<sz;i++){
            StringBuilder sb=new StringBuilder();
            for(int j=0;j<sz;j++){
                sb.append(board.matrix[i][j])
            }
        String pattern=sb.toString();
        if(pattern.equals(zero) || pattern.equals(cross))
             return true;
            }
        for(int i=0;i<sz;i++){
            StringBuilder sb=new StringBuilder();
            for(int j=0;j<sz;j++){
                sb.append(board.matrix[j][i])
            }
        String pattern=sb.toString();
        if(pattern.equals(zero) || pattern.equals(cross))
             return true;
            }
        int i=0,j=0;
        StringBuilder sb=new StringBuilder();
        while(i<sz){
            sb.append(board.matrix[i][j]);
            i++;
            j++;
        }
        String pattern=sb.toString();
        if(pattern.equals(zero) || pattern.equals(cross)){
             return true;    
        }
        i=0;
        j=sz-1;
        sb=new StringBuilder();
        while(i<sz){
            sb.append(board.matrix[i][j]);
            i++;
            j--;
        }
        pattern=sb.toString();
        if(pattern.equals(zero) || pattern.equals(cross)){
             return true;    
        }
        return false;
    }
   
 
}
