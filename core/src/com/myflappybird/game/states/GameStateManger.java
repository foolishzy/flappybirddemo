package com.myflappybird.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by foolishzy on 2016/1/3.
 * <p/>
 * funcction:
 * <p/>
 * others:
 */
public class GameStateManger {
    private Stack<State> states;

    public  GameStateManger(){

        states=new Stack<State>();
    }
    public void push(State state){
        states.push(state);
    }
    public void pop(){
        states.pop().dispose();
    }
    public void set(State state){
        states.pop();
        states.push(state);
    }
    public void  update(float dt){
        states.peek().update(dt);
    }
    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}