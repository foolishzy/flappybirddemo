package com.myflappybird.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.myflappybird.game.states.GameStateManger;
import com.myflappybird.game.states.MenuState;
import com.sun.java.swing.plaf.windows.WindowsDesktopManager;

public class FlappyBirdGameDemo extends ApplicationAdapter {
	public static final int WIDTH=400;
	public static final int HEIGHT=800;
	public static final String TITLE= "FlappyBird";
	private GameStateManger gsm;
	private SpriteBatch batch;
	private Music bgm;

	@Override
	public void create () {
		bgm= Gdx.audio.newMusic(Gdx.files.internal("audio/Kalimba.mp3"));
		bgm.setLooping(true);
		bgm.setVolume(0.2f);
		bgm.play();
		gsm= new GameStateManger();
		batch = new SpriteBatch();
		Gdx.gl.glClearColor(1, 0, 0, 1);
//		create the frist state----menustate-----
		gsm.push(new MenuState(gsm));

	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void dispose() {
		super.dispose();
		bgm.dispose();
	}
}
