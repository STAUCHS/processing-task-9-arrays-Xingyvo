import processing.core.PApplet;

public class Sketch extends PApplet {

  // Related arrays for the (x, y) coordinates of the snowflakes
  float[] snowX = new float[42];
  float[] snowY = new float[42];
  boolean[] blnHideStatus = new boolean[42];
  int snowDiameter = 20;

  // Set x and y coordinates for circle
  float fltCircleX = 20;
  float fltCircleY = 380;

  // Intialize the number of lives for the player
  int intLives = 3;

  // Declare boolean variables for keys
  boolean blnWPressed = false;
  boolean blnSPressed = false;
  boolean blnAPressed = false;
  boolean blnDPressed = false;
  boolean blnUpPressed = false;
  boolean blnDownPressed = false;

  // Declare boolean variable for game over
  boolean blnGameOver = false;
 
  public void settings() {
    size(400, 400);
  }

  public void setup() {
    background(0);

    // Generate random x- and y-values for snowflakes
    for (int i = 0; i < snowX.length; i++) {
      snowX[i] = random(width);
      snowY[i] = random(height);
      blnHideStatus[i] = false;
    }
  }

  public void draw() {
    background(0);

    if (!blnGameOver) {
      // Shows lives
      for (int count = 1; count <= intLives; count++) {
        fill(139, 0, 0);
        rect(width - count * 30, height - 395, width / 16, height / 16);
      }

      // Draw snow and circle
      snow(); 
      player();
    
      // Move the player with the WASD key pressed
      if (blnWPressed) {
        fltCircleY--;
      }
      if (blnSPressed) {
        fltCircleY++;
      }
      if (blnAPressed) {
        fltCircleX--;
      }
      if (blnDPressed) {
        fltCircleX++;
      }
    } else if (blnGameOver = true) {
      // If the game is over, clear screen to white
      background(255);
    }
  }
  
  // All other defined methods are written below:
  public void snow() {
    for (int i = 0; i < snowX.length; i++) {
      if (!blnHideStatus[i]) {
      fill(255);
      ellipse(snowX[i], snowY[i], snowDiameter, snowDiameter);

        // Snow falls faster when up arrow is pressed. Snow falls slower when up arrow is pressed.
        if (blnUpPressed) {
          snowY[i] += 0.8;
        } else if (blnDownPressed) {
          snowY[i] += 4;
        } else {
          snowY[i] += 2;
        } 

        // Check if snowflake is clicked
        if (mousePressed) {
          if (mouseX >= snowX[i] - 20 && mouseX <= snowX[i] + 20 && mouseY >= snowY[i] - 20 && mouseY <= snowY[i] + 20) {
            // Hide snow
            blnHideStatus[i] = true; 
          }
        }  

        // If player collides with a snowflake, hide the snow flake and remove a life
        if (fltCircleX >= snowX[i] - 20 && fltCircleX <= snowX[i] + 20 && fltCircleY >= snowY[i] - 20 && fltCircleY <= snowY[i] + 20) {
          blnHideStatus[i] = true;
          intLives--;
          if (intLives <= 0) {
            // Game over if players has 0 lives
            blnGameOver = true;
          }
        }

        // Reset snowflakes if it reaches the bottom of the screen
        if (snowY[i] > height) {
          snowY[i] = 0;
        }
      } 
    }
  }

  // Draw a blue circle
  public void player() {
    fill(0, 0, 255);
    ellipse(fltCircleX, fltCircleY, 15, 15);

    // Collision detection for player. For ex: if players move to the bottom of the screen, the player will come out of the top. 
    if (fltCircleX + 15 <= 0) {
      fltCircleX = width;
    } else if (fltCircleX - 15 >= width) {
      fltCircleX = 0;
    } else if (fltCircleY + 15 <= 0) {
      fltCircleY = height;
    } else if (fltCircleY - 15 >= height) {
      fltCircleY = 0;
    }
  }

  // Determine which key was pressed 
  public void keyPressed() {
    if (key == 'w') {
      blnWPressed = true;
    }
    else if (key == 's') {
      blnSPressed = true;
    }
    else if (key == 'a') {
      blnAPressed = true;
    }
    else if (key == 'd') {
      blnDPressed = true;
    }
    else if (keyCode == UP) {
      blnUpPressed = true;
    }
    else if (keyCode == DOWN) {
      blnDownPressed = true;
    }
  }
    
  // Determine which key was released 
  public void keyReleased() {
    if (key == 'w') {
      blnWPressed = false;
    }
    else if (key == 's') {
      blnSPressed = false;
    }
    else if (key == 'a') {
      blnAPressed = false;
    }
    else if (key == 'd') {
      blnDPressed = false;
    } 
    else if (keyCode == UP) {
      blnUpPressed = false;
    }
    else if (keyCode == DOWN) {
      blnDownPressed = false;
    }
  } 
}