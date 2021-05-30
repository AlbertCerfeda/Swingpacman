package Game;

import AudioEngine.FunctionCallback;
import Entities.Ghost;
import Media.EAudio;

import java.util.ArrayList;

/**
 * Responsible of the Sprites moving and interacting with each other.
 */
class GameThread implements Runnable {
    
    private static boolean paused=false;
    
    @Override
    public void run(){
        try{
            performRoundIntro();
            
            while (true){
                // DONT REMOVE THIS. Apparently if you dont print here, everything breaks.
                // Java makes no sense
                // TODO: Investigate this paranormal behaviour
                System.out.print("");
                
                while (!paused){
                    stepEntities();
                    Thread.sleep(20);
                }
            }
        } catch (Exception ae){
            ae.printStackTrace();
        }
    }
    
    public void performRoundIntro(){
        pause();
        Game.audioengine().playOnce(EAudio.round_start, new FunctionCallback() {
            @Override
            public void callback(){
                System.out.println("Finished round start");
                unpause();
            }
        });
    }
    
    public void performDeathSequence(){
        pause();
        
        Game.audioengine().playOnce(EAudio.death_sound, new FunctionCallback() {
            @Override
            public void callback(){
                System.out.println("Finished death sound");
                Game.gamestate().reshuffleEntityPositions();
                performRoundIntro();
            }
        });
    }
    
    /**
     * Gathers all the MovingEntities and makes them step.
     */
    protected void stepEntities(){
        ArrayList<Ghost> entities=Game.gamestate().getGhosts();
        for (Ghost g : entities){
            g.step();
        }
        Game.gamestate().getPacman().step();
    }
    
    /**
     * Pauses the game thread
     */
    public void pause(){
        paused = true;
        Game.audioengine().pauseAll();
    }
    
    /**
     * Unpauses the game thread
     */
    public void unpause(){
        paused = false;
        Game.audioengine().resumeALl();
    }
}