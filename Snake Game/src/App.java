import javax.swing.*;



public class App {
    public static void main(String[] args) throws Exception {
        int boardWIDTH = 600;
        int boardHeight = boardWIDTH;
        JFrame frame = new JFrame("Snake");
        frame.setVisible(true);
        frame.setSize(boardWIDTH , boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SnakeGame snakeGame = new SnakeGame(boardHeight, boardWIDTH);
        frame.add(snakeGame);
        frame.pack();
        snakeGame.requestFocus();
    }
}
