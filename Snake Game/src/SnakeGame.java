import java.awt.*;
import java.awt.event.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;



public class SnakeGame extends JPanel implements ActionListener,KeyListener {
    int boardHeight;
    int boardWIDTH;
    int tileSize = 25;
    //snake
    Tile SnakeHead;
    ArrayList<Tile> snakeBody;
    //food
    Tile food;
    Random random;
    // Game Logic
    Timer gameLoop;
    //Velocity is the speed of an object plus its direction. Speed is called a scalar quantity and velocity is a vector quantity. The fastest possible speed in the universe is the speed of light. The speed of light is 299,792,458 meters per second. In physics this number is represented by the letter "c.
    int velocityX;
    int velocityY;    
    boolean gameOver = false;
    private class  Tile {
        int x;
        int y;
        Tile(int x, int y){
            this.x = x;
            this.y = y;
        }
    
        
    }
    //contructor with parameters
    SnakeGame(int boardHeight, int boardWIDTH){
        this.boardHeight = boardHeight;
        this.boardWIDTH = boardWIDTH;
        setPreferredSize(new Dimension(this.boardHeight , this.boardWIDTH));
        setBackground(Color.green);
        addKeyListener(this);
        setFocusable(false);
        SnakeHead = new Tile(10,10);
        snakeBody = new ArrayList<Tile>();
        food = new Tile(10, 10);
        random = new Random();
        placeFood();
        velocityX = 0;
        velocityY = 0;
        gameLoop = new Timer(150, this);
        gameLoop.start();

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        //Grid
        for (int i=0;i<boardWIDTH/tileSize;i++){
            g.drawLine(i*tileSize, 0, i*tileSize, boardHeight);
            g.drawLine(0, i*tileSize,boardWIDTH,  i*tileSize);

        }
        //foood
        g.setColor(Color.red);
        g.fillRect(food.x*tileSize, food.y*tileSize, tileSize, tileSize);
        //snake head
        g.setColor(Color.yellow);
        g.fillRect(SnakeHead.x*tileSize,SnakeHead.y*tileSize, tileSize, tileSize );
        // snake body
        for (int i=0;i<snakeBody.size();i++){
            Tile snakePart = snakeBody.get(i);
            g.fillRect(snakePart.x*tileSize, snakePart.y*tileSize, tileSize, tileSize);
        }
        g.setFont(new Font("Arial" , Font.PLAIN,100));
        if (gameOver){
            g.setColor(Color.pink);
            g.drawString("Game Over : " +  String.valueOf(snakeBody.size()), tileSize-16, tileSize);
        }
        else
        {
                        g.drawString("Score : " +  String.valueOf(snakeBody.size()), tileSize-16, tileSize);


        }
// next 1

    }
    public  void placeFood(){
        food.x = random.nextInt(boardWIDTH/tileSize);
        food.y = random.nextInt(boardHeight/tileSize);
    }
    public boolean collision(Tile tile1, Tile tille2){
        return tile1.x==tille2.x && tile1.y==tille2.y;
    }
    public void move(){
        //eat food
        if (collision(food, SnakeHead)){
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }
        //snake body
        for (int i=snakeBody.size()-1;i>=0;i--){
            Tile snakePart = snakeBody.get(i);
            if (i==0){
                snakePart.x = SnakeHead.x;
                snakePart.y = SnakeHead.y;
            }
            else{
                Tile prevsnakePart = snakeBody.get(i-1);
                snakePart.x = prevsnakePart.x;
                snakePart.y = prevsnakePart.y;

            }
        }
        //snake head
        SnakeHead.x += velocityX;
        SnakeHead.y += velocityY;
        // gameover k conditions
        for (int i=0;i<snakeBody.size();i++){
            Tile snakePart = snakeBody.get(i);
            // collision with the snake
            if (collision(SnakeHead, snakePart)){
                gameOver = true;
            }
        } 
         if (SnakeHead.x*tileSize <0 || SnakeHead.x*
         tileSize> boardWIDTH || SnakeHead.y*tileSize<0||
         SnakeHead.y*tileSize>boardHeight){
        gameOver = true;
    }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
       repaint();
       if (gameOver){
        gameLoop.stop();
       }
    }
   
      @Override
    public void keyPressed(KeyEvent e) {
       if (e.getKeyCode()== KeyEvent.VK_UP && velocityY != 1){
        velocityX =0;
        velocityY =-1;
       }
       else if (e.getKeyCode()==KeyEvent.VK_DOWN && velocityY != -1){
        velocityX =0;
        velocityY =1;
       }
       else if (e.getKeyCode()==KeyEvent.VK_LEFT && velocityX != 1){
        velocityX =-1;
        
        velocityY =0;
       }
       else if (e.getKeyCode()==KeyEvent.VK_RIGHT && velocityX != -1){
        velocityX =1;
        velocityY =0;
       }
       
       
    }

    // DO NOT NEED
    @Override
    public void keyTyped(KeyEvent e) {}
  
    @Override
    public void keyReleased(KeyEvent e) {}
    
}
