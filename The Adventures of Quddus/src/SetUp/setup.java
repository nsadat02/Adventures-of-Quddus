package SetUp;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class setup extends StateBasedGame{

    public setup(String name) {
        super(name);
    }
    
    public static int lives=3;
    public static int gems=0;
    public static int level=1;
    
    public static void main(String[] args) throws SlickException {
         AppGameContainer app = new AppGameContainer(new setup("The Adventures of Quddus"));
         app.setDisplayMode(800, 600, false);
         app.setAlwaysRender(true);
         app.setTargetFrameRate(10000);
         app.start();      
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        
	this.addState(new Menu());
	this.addState(new FirstLevel());
        this.addState(new FirstLevel2());
        this.addState(new FirstLevel3());
        this.addState(new SecondLevel());
        this.addState(new SecondLevel2());
        this.addState(new SecondLevel3());
    }
    
}

