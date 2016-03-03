package com.myflappybird.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.myflappybird.game.FlappyBirdGameDemo;

/**
 * Created by foolishzy on 2016/1/3.
 * <p/>
 * funcction:
 * <p/>
 * others:
 */
public class MenuState extends State{
    private Texture background;
    private Texture ready;
    private Texture playButton;
    private Sound click;
    public MenuState(GameStateManger gsm) {
        super(gsm);

        ready=new Texture("picture/game_ready.png");
        click= Gdx.audio.newSound(Gdx.files.internal("audio/mfx_swooshing.ogg"));
        background=new Texture(Gdx.files.internal("picture/game_bg_day.png"));
        playButton=new Texture(Gdx.files.internal("picture/game_start_01.png"));

    }

    @Override
    public void handleInput() {

        if (Gdx.input.isTouched()) {
//		gsm load the seconed state----playstate-----
            click.play();
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0, FlappyBirdGameDemo.WIDTH,FlappyBirdGameDemo.HEIGHT);
        sb.draw(playButton,Gdx.graphics.getWidth()/2-playButton.getWidth()/2,
                Gdx.graphics.getHeight()/2-playButton.getHeight()/2);
        sb.draw(ready,Gdx.graphics.getWidth()/2-playButton.getWidth()/2-(ready.getWidth()-playButton.getWidth())/2, Gdx.graphics.getHeight()/2+playButton.getHeight()/2);
        sb.end();
    }

    @Override
    public void dispose() {
        click.dispose();
        playButton.dispose();
        background.dispose();
    }
}