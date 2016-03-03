package com.myflappybird.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.myflappybird.game.FlappyBirdGameDemo;
import com.myflappybird.game.sprites.Bird;
import com.myflappybird.game.sprites.tube;

/**
 * Created by foolishzy on 2016/1/3.
 * <p/>
 * funcction:
 * <p/>
 * others:
 */
public class PlayState extends State {
    private static final int COUNT=4;
    public static final int TUBE_SPACING=150;
    private Array<tube> tubes;
    private Bird bird;
    private Texture background;
    private Texture ground1,ground2;
    private Vector2 ground1pos,ground2pos;
    public static final int GROUND_OFFSET=-30;
    public static int GROUND_X;
    private Sound dieSound,scoredSound;


    public PlayState(GameStateManger gsm) {
        super(gsm);
        scoredSound= Gdx.audio.newSound(Gdx.files.internal("audio/mfx_point.ogg"));
        dieSound = Gdx.audio.newSound(Gdx.files.internal("audio/mfx_die.ogg"));
        ground1=new Texture(Gdx.files.internal("picture/game_floor.png"));
        ground2=ground1;
        ground1pos=new Vector2(cam.position.x-cam.viewportWidth/2,GROUND_OFFSET);
        ground2pos=new Vector2(cam.position.x-cam.viewportWidth/2+ground1.getWidth(),GROUND_OFFSET);
        GROUND_X=GROUND_OFFSET+ground1.getHeight();
        background=new Texture("picture/game_bg_day.png");
        tubes=new Array<tube>();
        for (int i = 0; i < COUNT; i++) {
            tubes.add(new tube((i+1)*(TUBE_SPACING+tube.TUBE_WIDTH)));
        }
        bird=new Bird(0,300);
//        cam.setToOrtho(false,FlappyBirdGameDemo.WIDTH/2,FlappyBirdGameDemo.HEIGHT/2);
        cam.setToOrtho(false,FlappyBirdGameDemo.WIDTH,FlappyBirdGameDemo.HEIGHT);

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched()) {
            bird.jump();
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
        cam.position.x=bird.getPosition().x+80;
        /*specified the value of tube from the array , just convey!!!
         no matter what you do ,array's member won't be change ,except using method array.remove();
         classic mistake,remember it!
        */
//        for (tube tube : tubes) {
//            if((cam.position.x-cam.viewportWidth/2)>(tube.getBotPosition().x+com.myflappybird.game.sprites.tube.TUBE_WIDTH))
//                { tube.reposition(tube.getBotPosition().x + COUNT*(com.myflappybird.game.sprites.tube.TUBE_WIDTH+TUBE_SPACING));}
//            else if(tube.collides(bird.getBounds())){
////                    gsm.set(new MenuState(gsm));
//            }
//        }
        for (tube tube : tubes) {
            if ((cam.position.x - cam.viewportWidth / 2) > (tube.getBotPosition().x + com.myflappybird.game.sprites.tube.TUBE_WIDTH)) {
                {
                    tubes.add(new tube(tube.getBotPosition().x + COUNT * (tube.TUBE_WIDTH + TUBE_SPACING)));
                    tubes.removeValue(tube, true);
                }
            } else if (tube.collides(bird.getBounds())||bird.getPosition().y<GROUND_X) {
                    dieSound.play(0.5f);
//                    System.out.println(cam.position.x + " " + cam.position.y);
                    gsm.set(new com.myflappybird.game.states.ScoredState(gsm, bird));
            }
        }
        if (bird.getPosition().x==((int) Math.floor((bird.getPosition().x -tube.TUBE_WIDTH -
                bird.getOriginXposition())/(tube.TUBE_WIDTH+PlayState.TUBE_SPACING))
                    +1)*(TUBE_SPACING+tube.TUBE_WIDTH))
        {
            bird.score++;
            scoredSound.play(0.5f);
        }
        groundUpdate();
        cam.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        Bird.STATETIME+=Gdx.graphics.getDeltaTime();
        TextureRegion birdtemp=bird.getBird().getKeyFrame(Bird.STATETIME,true);
        sb.begin();
        sb.draw(background, cam.position.x - cam.viewportWidth / 2, 0);
//        sb.draw(background, cam.position.x-cam.viewportWidth/2, 0, FlappyBirdGameDemo.WIDTH, FlappyBirdGameDemo.HEIGHT);
        sb.draw(ground1,ground1pos.x,ground1pos.y);
        sb.draw(ground2,ground2pos.x,ground2pos.y);
        sb.draw(birdtemp,bird.getPosition().x,bird.getPosition().y);
        for (tube tube : tubes) {
            sb.draw(tube.getTopTube(),tube.getTopPosition().x,tube.getTopPosition().y);
            sb.draw(tube.getBotTube(), tube.getBotPosition().x, tube.getBotPosition().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        ground1.dispose();
        ground2.dispose();
        scoredSound.dispose();
        dieSound.dispose();
        for (tube tube : tubes) {
            tube.dispose();
        }
    }
    public void groundUpdate(){
        if (ground1pos.x <cam.position.x-cam.viewportWidth/2-ground1.getWidth())
            ground1pos.add(2*ground1.getWidth(),0);

        if (ground2pos.x <cam.position.x-cam.viewportWidth/2-ground1.getWidth())
            ground2pos.add(2*ground1.getWidth(),0);
    }
}
