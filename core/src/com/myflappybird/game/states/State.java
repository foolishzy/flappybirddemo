package com.myflappybird.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.myflappybird.game.FlappyBirdGameDemo;

/**
 * Created by foolishzy on 2016/1/3.
 * <p/>
 * funcction:
 * <p/>
 * others:
 */
public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManger gsm;
    public State(GameStateManger gsm){
        this.gsm =gsm;
        cam=new OrthographicCamera(FlappyBirdGameDemo.WIDTH,FlappyBirdGameDemo.HEIGHT);
        mouse=new Vector3();

    }
    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();

    public OrthographicCamera getCam() {
        return cam;
    }
}
