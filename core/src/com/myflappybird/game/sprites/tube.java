
package com.myflappybird.game.sprites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.awt.Rectangle;
import java.util.Random;

/**
 * Created by foolishzy on 2016/1/4.
 * <p/>
 * funcction:
 * <p/>
 * others:
 */
public class tube {

    private static final int FLUCTUATION=150;
    private static final int TUBE_GAP=155;
    private static final int LOWEST_OPENING=250;
    public static final int TUBE_WIDTH=86;
    private Random rand;
    private Vector2 topPosition;
    private Vector2 botPosition;
    private Texture topTube;
    private  Texture botTube;
    private Rectangle topBounds,botBounds;


    public tube(float x){
        topTube=new Texture("picture/bar_up.png");
        botTube=new Texture("picture/bar_down.png");
        rand=new Random();
        topPosition=new Vector2(x,rand.nextInt(FLUCTUATION)+TUBE_GAP+LOWEST_OPENING);
        botPosition=new Vector2(x, topPosition.y-TUBE_GAP-botTube.getHeight());
        topBounds=new Rectangle((int)topPosition.x,(int)topPosition.y,topTube.getWidth(),topTube.getHeight());
        botBounds=new Rectangle((int)botPosition.x,(int)botPosition.y,botTube.getWidth(), botTube.getHeight());
    }
    //useless method
//    public void reposition(float x){
//        topPosition=new Vector2(x,rand.nextInt(FLUCTUATION)+TUBE_GAP+LOWEST_OPENING);
//        botPosition=new Vector2(x,topPosition.y-TUBE_GAP-botTube.getHeight());
//
//    }
    public boolean collides(Rectangle players){
        return (new com.myflappybird.game.other.rectangleIsOverLad(players,botBounds).isOverLad()||
                new com.myflappybird.game.other.rectangleIsOverLad(players,topBounds).isOverLad());
    }
    public Vector2 getTopPosition() {
        return topPosition;
    }

    public Vector2 getBotPosition() {
        return botPosition;
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBotTube() {
        return botTube;
    }
    public void dispose(){
        topTube.dispose();
        botTube.dispose();

    }

}

