//Fazakas Edina-Szylvia
//Csoport: 522/1
//Azonosito: feim1911

public class Timer{
    private int min;
    private int sec;
    private boolean gameOver;

    public Timer(int min, int sec) {
        this.min = min;
        this.sec = sec;
        gameOver = false;
    }

    @Override
    public String toString() {
        return min + " : " + sec ;
    }

    public void countTime(){
          if (min == 0 && sec == 0){
              gameOver = true;
          }
          else{
              if (sec == 0){
                  sec = 59;
                  min --;
              }
              else {
                  sec--;
              }
          }

    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver){
        this.gameOver = gameOver;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }
}
