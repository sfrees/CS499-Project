public class Driver {
  public static void main(String[] args) {
    while(true) {
      StartScreen s = new StartScreen();
      while(s.active) {
        continue;
      }
      Room room = new Room();
      while(room.active) {
        continue;
      }
      EndScreen e;
      if (room.userWon) {
        System.out.println("Score: " + room.getScore());
        e = new EndScreen(true, room.getScore());
      } else
        System.out.println("Score: " + room.getScore());
        e = new EndScreen(false, room.getScore());
      while(e.active) {
        continue;
      }
      if (e.endGame)
        break;
    }
    System.exit(1);
  }
}