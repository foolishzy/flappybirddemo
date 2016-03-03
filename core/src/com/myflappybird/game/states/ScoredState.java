package com.myflappybird.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.myflappybird.game.FlappyBirdGameDemo;
import com.myflappybird.game.sprites.Bird;

import javax.swing.plaf.basic.BasicFormattedTextFieldUI;
import javax.swing.text.Position;

/**
 * Created by foolishzy on 2016/1/7.
 * <p/>
 * funcction:
 * <p/>
 * others:
 */
public class ScoredState extends State {
    private static float screenCenterx,screenCentery;
    private static Vector2 scorePos,bestPos,medalPos,scoreboardPos;
    private static Vector2 offset_scoreboard_medal =new Vector2(43,45);
    private static Vector2 offset_scoreboard_score=new Vector2(260,120);
    private static Vector2 offset_scoredboard_best=new Vector2(240,50);
    private  int score;
    private static BitmapFont bft_score,bft_best;
    private static Texture background,scoreboard,medal1,medal2,medal3,medal4,gameover,replayButton;
    public ScoredState(GameStateManger gsm,Bird bird) {
        super(gsm);
        this.score=bird.score;
        replayButton=new Texture(Gdx.files.internal("picture/game_start_02.png"));
        medal1= new Texture(Gdx.files.internal("picture/game_medal_01.png"));
        medal2= new Texture(Gdx.files.internal("picture/game_medal_02.png"));
        medal3= new Texture(Gdx.files.internal("picture/game_medal_03.png"));
        medal4= new Texture(Gdx.files.internal("picture/game_medal_04.png"));
        gameover=new Texture(Gdx.files.internal("picture/game_over.png"));
        scoreboard=new Texture(Gdx.files.internal("picture/game_result_bg.png"));
        background=new Texture(Gdx.files.internal("picture/game_bg_day.png"));
        bft_score =new BitmapFont(Gdx.files.internal("bitmapfont/char.fnt"));
        bft_best=new BitmapFont(Gdx.files.internal("bitmapfont/nicai.fnt"));
        initPosition();

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched())
            gsm.set(new PlayState(gsm));
    }

    @Override
    public void update(float dt) {
    handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        sb.draw(background,
                cam.position.x - background.getWidth() / 2,
                cam.position.y - background.getHeight() / 2
                );

        sb.draw(scoreboard, cam.position.x - scoreboard.getWidth() / 2,
                cam.position.y - scoreboard.getHeight() / 2);

        sb.draw(replayButton,
                cam.position.x - scoreboard.getWidth() / 2 +(scoreboard.getWidth() -
                        replayButton.getWidth()) / 2,
                cam.position.y - scoreboard.getHeight() / 2 - replayButton.getHeight());

        sb.draw(gameover,
                cam.position.x - scoreboard.getWidth() / 2 +
                        (scoreboard.getWidth() - gameover.getWidth()) / 2,
                cam.position.y + scoreboard.getHeight() / 2 );

        sb.draw(medal1,medalPos.x, medalPos.y);

        bft_score.draw(sb, String.valueOf(score), scorePos.x, scorePos.y);
        bft_best.draw(sb,"你猜",bestPos.x,bestPos.y);

        sb.end();
    }

    @Override
    public void dispose() {
        medal1.dispose();
        medal2.dispose();
        medal3.dispose();
        medal4.dispose();
        replayButton.dispose();
        scoreboard.dispose();
        background.dispose();
    }
    private void initPosition(){
        screenCenterx=cam.position.x ;
        screenCentery=cam.position.y;
        scoreboardPos=new  Vector2(screenCenterx  - scoreboard.getWidth() / 2, screenCentery - scoreboard.getHeight()/2);
        medalPos=new Vector2(scoreboardPos.x+offset_scoreboard_medal.x,scoreboardPos.y+offset_scoreboard_medal.y);
        scorePos=new Vector2(scoreboardPos.x+offset_scoreboard_score.x,scoreboardPos.y+offset_scoreboard_score.y);
        bestPos=new Vector2(scoreboardPos.x+offset_scoredboard_best.x,scoreboardPos.y+offset_scoredboard_best.y);
    }

}
