package SetUp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class FirstLevel2 extends BasicGameState{
    
    private Shape ground;
    private Shape Bomb1;
    private Shape Bomb2;
    public String mouse;
    private Polygon playerPoly;
    private Polygon bulletPoly;
    private Polygon enemyPoly1;
    private Polygon enemyPoly2;
    private Polygon enemyPoly3;
    private Polygon enemyPoly4;
    private Polygon portalPoly;
    Image FScreen,Quddus,QuddusL,Bullet,Gem;
    Image Pipe,BBrick,Portal;
    Image VBird,VCrow,Lives;
    Image VCrow1,VCrow2;
    Image Pause,Instruct;
    float qx=20,qy=367;
    float bx=0,by=1000;
    float e1x=300,e1y=367;
    float e2x=500,e2y=375;
    float e3x=625,e3y=367;
    float e4x=600,e4y=367;
    float lx=225;
    int counter1=0;
    int counter2=0;
    int counter3=0;
    float px=725,py=355;
    int g1x=415,g1y=410;
    int g2x=700,g2y=410;
    int timer=100;
    
    boolean jumping = false; 
    boolean shot = false;
    boolean left = false;
    boolean turn = false;
    boolean turn2 = false;
    boolean turn3 = false;
    boolean destroyed = false;
    boolean pause = false;
    boolean instruct = false;
    boolean die = true;
    float verticalSpeed = 0.0f;
    float bulletSpeed = 0.0f;

            
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.FScreen=new Image("FScreen.png");
        this.Quddus=new Image("Quddus.gif");
        this.QuddusL=new Image("QuddusL.png");
        this.Bullet=new Image("Bullet.png");
        this.BBrick=new Image("BBrick.png");
        this.VBird=new Image("VBird.png");
        this.VCrow1=new Image("VCrow.png");
        this.VCrow2=new Image("VCrow.png");
        this.Lives=new Image("Lives.png");
        this.Portal=new Image("Portal.png");
        this.Pause=new Image("Pause1.png");
        this.Instruct=new Image("Black.jpg");
        this.Gem=new Image("Gem.png");
        
        ground = new Line(0,434,800,434);
        Bomb1 = new Rectangle (150,421,10,10);
        Bomb2 = new Rectangle (450,421,10,10);

        
        playerPoly = new Polygon(new float[]{
                qx,qy,
                qx+65,qy,
                qx+65,qy+65,
                qx,qy+65
        });
        
        bulletPoly = new Polygon(new float[]{
                bx,by,
                bx+24,by,
                bx+24,by+8,
                bx,by+8
        });
         
        enemyPoly1 = new Polygon(new float[]{
                e1x,e1y,
                e1x+40,e1y,
                e1x+40,e1y+70,
                e1x,e1y+70
        });
        enemyPoly2 = new Polygon(new float[]{
                e2x,e2y,
                e2x+69,e2y,
                e2x+69,e2y+65,
                e2x,e2y+65
        });
        enemyPoly3 = new Polygon(new float[]{
                e3x,e3y,
                e3x+40,e3y,
                e3x+40,e3y+70,
                e3x,e3y+70
        });
        portalPoly = new Polygon( new float[]{
            px,py,
            px+40,py,
            px+40,py+70,
            px,py+70
        });


    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        FScreen.draw(0,75);
        BBrick.draw(150,410);
        BBrick.draw(450,410);
        Gem.draw(g1x,g1y);
        Gem.draw(g2x,g2y);
        Portal.draw(px,py);
        
        if (setup.lives==1){
        Lives.draw(20,80);
        }
        if (setup.lives==2){
        Lives.draw(20,80);
        Lives.draw(55,80);
        }
        if (setup.lives==3){
        Lives.draw(20,80);
        Lives.draw(55,80);
        Lives.draw(90,80);
        }
        if (setup.lives==4){
        Lives.draw(20,80);
        Lives.draw(55,80);
        Lives.draw(90,80);
        Lives.draw(125,80);
        
        }
        if (setup.lives==5){
        Lives.draw(20,80);
        Lives.draw(55,80);
        Lives.draw(90,80);
        Lives.draw(125,80);
        Lives.draw(160,80);
        }
        
        // Quddus Turning/Moving Left 
        if(left==false){
            g.drawImage(Quddus,qx,qy);
            Bullet.draw(bx,by);
        }
        if(left==true){
            Quddus.getFlippedCopy(true, false).draw(qx,qy);
            Bullet.getFlippedCopy(true, false).draw(bx,by);            
        }
        
        // Enemies Turning Following Movement Limit
        if(die==false){
        if(turn==false){
            VCrow1.draw(e1x,e1y);
            e1x+=0.5;
            enemyPoly1.setX(e1x);
        }
        if(turn==true){
            VCrow1.getFlippedCopy(true, false).draw(e1x,e1y);
            e1x-=0.5;
            enemyPoly1.setX(e1x);
        }
        if(turn2==false){
            VBird.draw(e2x,e2y);
            e2x-=0.5;
            enemyPoly2.setX(e2x);
        }
        if(turn2==true){
            VBird.getFlippedCopy(true, false).draw(e2x,e2y);
            e2x+=0.5;
            enemyPoly2.setX(e2x);
        }
        if(turn3==false){
            VCrow2.draw(e3x,e3y);
            e3x+=0.5;
            enemyPoly3.setX(e3x);
        }
        if(turn3==true){
            VCrow2.getFlippedCopy(true, false).draw(e3x,e3y);
            e3x-=0.5;
            enemyPoly3.setX(e3x);
        }
        }
        // Pause
        if(pause==true){
            g.drawImage(Pause, 0, 75);
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

    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        
        //Quddus Moving
        if (qy+65>=434) qy--;
        
        if(qx>20|| qx<20) die=false;
        
        if(input.isKeyDown(Input.KEY_A) && qx-1>0){
            left=true;
            qx-=0.5;
            playerPoly.setX(qx);
        }
        if(input.isKeyDown(Input.KEY_D) && qx+1<gc.getWidth()-Quddus.getWidth()){
            left=false;
            qx+=0.5;
            playerPoly.setX(qx);
        }
        
        //Enemies Moving
        if(e1x==px-VCrow1.getWidth()){
            turn=true;
        }
        if(e1x<=(lx)){
            turn=false;
        }
        if(e2x==px){
            turn2=false;
        }
        if(e2x<=lx){
            turn2=true;
        }
        if(e3x==px){
            turn3=true;
        }
        if(e3x<=(lx)){
            turn3=false;
        }
        
        //Jumping/Gravity
        if (gc.getInput().isKeyPressed(Input.KEY_W) && !jumping) { 
             verticalSpeed = -3.0f;
             jumping = true;
        }

        if (jumping) { 
             verticalSpeed += .01f * i;
        } 

        qy += verticalSpeed;
        playerPoly.setY(qy);
        
        if (entityCollisionWith()){
            jumping = false;
            verticalSpeed = 0;
            qy-=2;
            playerPoly.setY(qy);
        }
        
        //Bullet Shooting
        if(input.isKeyDown(Input.KEY_SPACE)&&!shot&&timer==100){
            //bx=qx+70;
            by=qy+35;
            if(left==true){
                bx=qx-70;
                bulletPoly.setX(qx-70);
                bulletPoly.setY(qy+35);
                bulletSpeed=0.5f*-i;
            }
            else{
                bx=qx+70;
                bulletPoly.setX(qx+70);
                bulletPoly.setY(qy+35);
                bulletSpeed=0.5f*i;
            }
            shot=true; 
        }
        
        bx+=bulletSpeed;
        bulletPoly.setX(bx);
        
        //Bullet timer between kills
        if (timer!=0)timer--;
        if (timer==0)timer=100;
        //if bullet off screen        
        if (bx>800 || bx+Bullet.getWidth()<0){
            bulletSpeed=0;
            shot=false;
        }
        
        //Bullet Hitting Enemies
        if (bulletPoly.intersects(enemyPoly1)) {
            e1y=1000;
            enemyPoly1.setY(1000);
            by=2000;
            bulletPoly.setY(2000);
            bulletSpeed=0;
            shot=false;
        }
        if (bulletPoly.intersects(enemyPoly2)) {
            e2y=1000;
            enemyPoly2.setY(1000);
            by=2000;
            bulletPoly.setY(2000);
            bulletSpeed=0;
            shot=false;
        }
        if (bulletPoly.intersects(enemyPoly3)) {
            e3y=2000;
            enemyPoly3.setY(1000);
            by=2000;
            bulletPoly.setY(2000);
            bulletSpeed=0;
            shot=false;
        }

        //Enemy & Player Collisions
        if (playerPoly.intersects(enemyPoly1)) {
            qx=20;
            qy=367;
            playerPoly.setX(qx);
            playerPoly.setY(qy);
            e1x=300; e1y=367;
            enemyPoly1.setX(e1x);
            enemyPoly1.setY(e1y);
            e2x=500; e2y=375;
            enemyPoly2.setX(e2x);
            enemyPoly2.setY(e2y);
            e3x=650; e3y=367;
            enemyPoly3.setX(e3x);
            enemyPoly3.setY(e3y);
            setup.lives--;
            die=true;
            sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
        }
        if (playerPoly.intersects(enemyPoly2)) {
            qx=20;
            qy=367;
            playerPoly.setX(qx);
            playerPoly.setY(qy);
            e1x=300; e1y=367;
            enemyPoly1.setX(e1x);
            enemyPoly1.setY(e1y);
            e2x=500; e2y=375;
            enemyPoly2.setX(e2x);
            enemyPoly2.setY(e2y);
            e3x=650; e3y=367;
            enemyPoly3.setX(e3x);
            enemyPoly3.setY(e3y);
            setup.lives--;
            die=true;
            sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
        }
        if (playerPoly.intersects(enemyPoly3)) {
            qx=20;
            qy=367;
            playerPoly.setX(qx);
            playerPoly.setY(qy);
            setup.lives--;
            e1x=300; e1y=367;
            enemyPoly1.setX(e1x);
            enemyPoly1.setY(e1y);
            e2x=500; e2y=375;
            enemyPoly2.setY(e2y);
            e3x=625; e3y=367;
            enemyPoly3.setX(e3x);
            enemyPoly3.setY(e3y);
            die=true;
            sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
        }
        
        //Bomb & Player Collisions
        if (playerPoly.intersects(Bomb1)||playerPoly.intersects(Bomb2)) {
            qx=20;
            qy=367;
            playerPoly.setX(qx);
            playerPoly.setY(qy);
            setup.lives--;
            die=true;
            sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
        }
        
        //Portal Respawning        
        if (!destroyed){
            if(e1y>600)counter1++;
            if(e2y>600)counter2++;
            if(e3y>600)counter3++;
            System.out.println(counter1);
            if(counter1==1000){
                counter1=0;
                e1x=570;
                e1y=367;
                enemyPoly1.setX(e1x);
                enemyPoly1.setY(e1y);
            }
            if(counter2==1000){
                counter2=0;
                e2x=530;
                e2y=375;
                enemyPoly2.setX(e2x);
                enemyPoly2.setY(e2y);
            }
            if(counter3==1000){
                counter3=0;
                e3x=600;
                e3y=367;
                enemyPoly3.setX(e3x);
                enemyPoly3.setY(e3y);
            }
        } 
        
        //Portal Destruction
        if (bulletPoly.intersects(portalPoly)){
            py=2000;
            portalPoly.setY(1000);
            by=2000;
            bulletPoly.setY(2000);
            bulletSpeed=0;
            shot=false;
            destroyed=true;
        }
        
        //Gem Grab
        if(qx==g1x-Quddus.getWidth()){
            setup.gems++;
            g1x=2000;           
        }
        if(qx==g2x-Quddus.getWidth()){
            setup.gems++;
            g2x=2000;           
        }
        
        //Pause
        if(input.isKeyDown(Input.KEY_P)){
            pause=true;   
        }
        if(pause==true){
            if(input.isKeyDown(Input.KEY_1)){
            pause=false;   
            }
             if(input.isKeyDown(Input.KEY_2)){
                 instruct=true;
            }
             if(input.isKeyDown(Input.KEY_3)){
                 pause=false;
                 qx=20; qy=367;
                 px=725; py=355;
                 portalPoly.setY(py);
                 e1x=300; e1y=367;
                 enemyPoly1.setX(e1x);
                 enemyPoly1.setY(e1y);
                 e2x=500; e2y=375;
                 enemyPoly2.setY(e2y);
                 e3x=650; e3y=367;
                 enemyPoly3.setY(e3y);
                 g1x=415; g1y=410;
                 g2x=700; g2y=410;
                 destroyed=false;
                 setup.lives=3;
            }
             if(input.isKeyDown(Input.KEY_4)){
                 pause=false;
                 qx=20; qy=367;
                 px=725; py=355;
                 portalPoly.setY(py);
                 e1x=300; e1y=367;
                 enemyPoly1.setX(e1x);
                 enemyPoly1.setY(e1y);
                 e2x=500; e2y=375;
                 enemyPoly2.setY(e2y);
                 e3x=650; e3y=367;
                 enemyPoly3.setY(e3y);
                 g1x=415; g1y=410;
                 g2x=700; g2y=410;
                 destroyed=false;
                 setup.lives=3;
                 die=true;
                 sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
            }
             if(input.isKeyDown(Input.KEY_5)){
                 try{
                    saveFile();
                 } 
                 catch(IOException ex) {
                    Logger.getLogger(FirstLevel.class.getName()).log(Level.SEVERE, null, ex);
                 }
            }
             if(input.isKeyDown(Input.KEY_6)){
                 gc.exit();
            }
        }
        
        if(instruct==true){
            if(input.isKeyDown(Input.KEY_BACK)){
                pause=true;   
                instruct=false;
            }
        }
        
        
        //Screen Shift
        if(setup.lives==0){
            qx=20; qy=367;
            px=725; py=355;
            portalPoly.setY(py);
            e1x=300; e1y=367;
            enemyPoly1.setX(e1x);
            enemyPoly1.setY(e1y);
            e2x=500; e2y=375;
            enemyPoly2.setY(e2y);
            e3x=625; e3y=367;
            enemyPoly3.setY(e3y);
            g1x=415; g1y=410;
            g2x=700; g2y=410;
            destroyed=false;
            die=true;
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }
        if(qx==px){
            if(setup.lives<=5) setup.lives++;
            sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
        }
        
    }
    
    //Ground/gravity collision
    public boolean entityCollisionWith() throws SlickException {
        return playerPoly.intersects(ground);
    }
    
    //Saving
    public static void saveFile() throws IOException{
    BufferedWriter WriteFile = new BufferedWriter(new FileWriter("Save.txt"));
          WriteFile.write("1");           
          WriteFile.newLine();
          String live=Integer.toString(setup.lives);
          WriteFile.write(live);  
          WriteFile.newLine();
          String dia=Integer.toString(setup.gems);
          WriteFile.write(dia);           
          WriteFile.newLine();
          WriteFile.close();
    }
    
    @Override
    public int getID() {
        return 2;
    }
}