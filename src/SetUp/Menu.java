package SetUp;

import static SetUp.FirstLevel.saveFile;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Menu extends BasicGameState{
    Image MenuS,Instruct;  
    String Line1;
    String Line2;
    String Line3;
    boolean instruct=false;
    
    
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.MenuS=new Image("MenuS.png");
        this.Instruct=new Image("Black.jpg");
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        
        if(gc.getInput().isKeyPressed(Input.KEY_1)){
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
            try{
                saveFile();
            } 
            catch(IOException ex) {
                Logger.getLogger(FirstLevel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(gc.getInput().isKeyPressed(Input.KEY_2)){
            try {
                getFile();
            } catch (IOException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            sbg.enterState(setup.level, new FadeOutTransition(), new FadeInTransition());
        }
        if(gc.getInput().isKeyPressed(Input.KEY_3)){
            instruct=true;
        }
        if(instruct==true){
            if(gc.getInput().isKeyDown(Input.KEY_BACK)){   
                instruct=false;
            }
        }       
        if(gc.getInput().isKeyPressed(Input.KEY_ESCAPE)){
            gc.exit();
        } 
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        MenuS.draw(0,75);       
        if(instruct==true){
                Instruct.draw(0,0);
                g.drawString("GREETINGS! WELCOME TO THE ADVENTURES OF QUDDUS!", 50, 50);
                g.drawString("Instructions:", 50, 75);
                g.drawString("Use 'A' key to move Quddus left and the 'D' key to move Quddus right", 50, 100);
                g.drawString("Use 'W' key to make Quddus jump up", 50, 125);
                g.drawString("Use SPACE BAR to shoot bullets in order to kill enemies", 50, 150);
                g.drawString("Make sure you don't touch the Bomb Blocks or you will lose a life", 50, 175);
                g.drawString("Don't forget to destroy the spawner portal", 50, 200);
                g.drawString("Collect Gems in order to get more lives", 50, 225);
                g.drawString("Make sure you save the game so you don't lose your progress", 50, 250);
                g.drawString("HOPE YOU ENJOY! - NSARS Community", 50, 275);
                g.drawString("Press 'Backspace' to go to pause menu", 50, 300);
        }
    }
    
    public void getFile() throws IOException{
        try {
            BufferedReader ReadFile = new BufferedReader(new FileReader("Save.txt"));
            Line1=ReadFile.readLine();
            Line2=ReadFile.readLine();
            Line3=ReadFile.readLine();
            setup.level=Integer.parseInt(Line1);
            setup.lives=Integer.parseInt(Line2);
            setup.gems=Integer.parseInt(Line3);
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
    
    public static void saveFile() throws IOException{
        BufferedWriter WriteFile = new BufferedWriter(new FileWriter("Save.txt"));
          WriteFile.write("1");           
          WriteFile.newLine();
          String live=Integer.toString(3);
          WriteFile.write(live);  
          WriteFile.newLine();
          String gems=Integer.toString(0);
          WriteFile.write(gems);           
          WriteFile.newLine();
          WriteFile.close();
    }
    public int getID() {
        return 0;
    }
    
}



