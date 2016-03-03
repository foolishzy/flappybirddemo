package com.myflappybird.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.myflappybird.game.FlappyBirdGameDemo;
import com.myflappybird.game.states.PlayState;

import java.awt.Rectangle;

/**
 * Created by foolishzy on 2016/1/3.
 * <p/>
 * funcction:
 * <p/>
 * others:
 */
public class Bird extends Sprite {
    private static boolean isLive;
    private static final int MOVESPEED=2;
    private Vector3 position;
    private Vector3 velocity;
    public static  float STATETIME=0f;
    private Animation bird;
    public static final float GRAVITY=-15;
    public Rectangle bounds;
    private static  int birdWidth,birdHeight;
    public static int score;
    private static int originXposition;//起始位置
    private Sound soundjump;
    public Bird (int x ,int y){
        originXposition=x;
        score=0;
        soundjump= Gdx.audio.newSound(Gdx.files.internal("audio/mfx_jump.ogg"));
        TextureRegion[] birdsheet=new TextureRegion[3];
        birdsheet[0]=new TextureRegion(new Texture("picture/brid_yellow_01.png"));
        birdsheet[1]=new TextureRegion(new Texture("picture/brid_yellow_02.png"));
        birdsheet[2]=new TextureRegion(new Texture("picture/brid_yellow_03.png"));
        birdWidth=birdsheet[0].getRegionWidth();
//        System.out.println("birdWidth:"+birdWidth+"  "+"birdHeight:"+birdHeight);
        birdHeight=birdsheet[0].getRegionHeight();
        bird=new Animation(1f/30f,birdsheet);
        bird.setPlayMode(Animation.PlayMode.NORMAL);
        this.position=new Vector3(x,y,0);
        this.velocity=new Vector3(0,0,0);
        bounds=new Rectangle(x,y,birdWidth,birdHeight);
//        System.out.println("boundsx:"+bounds.getX()+"  "+"boundsy"+bounds.getY()+"  "+"boundswidth:"+bounds.getWidth()+"  boundsheight:"+bounds.getHeight());
    }
    public void update(float dt){
       //velocity.y:GRAVITY ---> GRAVITY*dt ---> GRAVITY --->
        if(position.y>0)
        {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(dt);
        position.add(MOVESPEED, velocity.y, 0);
        bounds.setLocation((int) position.x, (int) position.y);
        velocity.scl(1 / dt);
        if(position.y<0){
            position.y=0;
        }
        if (position.y+birdHeight>= FlappyBirdGameDemo.HEIGHT)
            position.y=FlappyBirdGameDemo.HEIGHT-birdHeight;
//        if(position.y-birdHeight>= Gdx.graphics.getHeight())
//            position.y= Gdx.graphics.getHeight()+birdHeight;
    }
    public void jump(){
        soundjump.play();
        velocity.set(0,250,0);

    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public Animation getBird() {
        return bird;
    }

    public static boolean isLive() {
        return isLive;
    }

    public static void setIsLive(boolean isLive) {
        Bird.isLive = isLive;
    }

    public Rectangle getBounds() {
        return bounds;
    }
    public void  dispose(){
        soundjump.dispose();
    }

    public static int getOriginXposition() {
        return originXposition;
    }
}
