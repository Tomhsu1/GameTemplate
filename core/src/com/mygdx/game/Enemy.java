package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Ryan on 7/5/2016.
 */
public class Enemy {
    private static AssetManager manager;
    protected static final int NUM_ENEMIES = 10;
    private float speed;
    private Vector2 position, velocity;
    private Rectangle bounds;
    public Animation enemies;
    public float enemiesStateTime;
    private static String img1, img2, img3, img4, img5, img6;

    static {
        //memory management -- loading image into manager
        img1 = "images/ZombieFirstStep.png";
        img2 = "images/ZombieSecondStep.png";
        img3 = "images/ZombieThirdStep.png";
        img4 = "images/ZombieFourthStep.png";
        img5 = "images/ZombieFifthStep.png";
        img6 = "images/ZombieSixthStep.png";
        manager = new AssetManager();
        manager.load(img1, Texture.class);
        manager.load(img2, Texture.class);
        manager.load(img3, Texture.class);
        manager.load(img4, Texture.class);
        manager.load(img5, Texture.class);
        manager.load(img6, Texture.class);
        manager.finishLoading();
    }

    public Enemy(float x, float y) {
//        Texture frame1 = new Texture("images/ZombieFirstStep.png");
//        Texture frame2 = new Texture("images/ZombieSecondStep.png");
//        Texture frame3 = new Texture("images/ZombieThirdStep.png");
//        Texture frame4 = new Texture("images/ZombieFourthStep.png");
//        Texture frame5 = new Texture("images/ZombieFifthStep.png");
//        Texture frame6 = new Texture("images/ZombieSixthStep.png");

        enemies = new Animation (0.05f,
                new TextureRegion(manager.get(img1, Texture.class)),
                new TextureRegion(manager.get(img2, Texture.class)),
                new TextureRegion(manager.get(img3, Texture.class)),
                new TextureRegion(manager.get(img4, Texture.class)),
                new TextureRegion(manager.get(img5, Texture.class)),
                new TextureRegion(manager.get(img6, Texture.class)));
//                new TextureRegion(frame3),
//                new TextureRegion(frame4),
//                new TextureRegion(frame5),
//                new TextureRegion(frame6));
        enemies.setPlayMode(Animation.PlayMode.LOOP);
        enemiesStateTime = 0;
        //sprite.setSize(YOUR WIDTH, YOUR HEIGHT);
        //enemies.setScale(enemies.getWidth(), enemies.getHeight());
        position = new Vector2();
        setPosition(x + 600, y);
        velocity = new Vector2();
        bounds = new Rectangle();
        setSpeed(200);
    }

    public void move() {}

    public void followPlayer(Player player) {
        float deltaTime = Gdx.graphics.getDeltaTime();
        float deltaX = player.getPosition().x - getPosition().x;
        float deltaY = player.getPosition().y - getPosition().y;
        float rotation = MathUtils.atan2(deltaY, deltaX) / MathUtils.PI * 180;

        setVelocity(MathUtils.cos(rotation / 180 * MathUtils.PI) * getSpeed(),
                MathUtils.sin(rotation / 180 * MathUtils.PI) * getSpeed());
        getPosition().mulAdd(getVelocity(), deltaTime);
    }

    public void update() {
        setBounds();
    }

    public void setSpeed(float spd) {speed = spd;}

    public float getSpeed() {return speed;}

    public void setPosition(float x, float y) {position.set(x, y);}

    public Vector2 getPosition() {return position;}

    public void setVelocity(float x, float y) {velocity.set(x, y);}

    public Vector2 getVelocity() {return velocity;}

    public void setBounds() {bounds.set(getPosition().x, getPosition().y, enemies.getKeyFrame(0).getRegionWidth(), enemies.getKeyFrame(0).getRegionHeight());}

    public Rectangle getBounds() {return bounds;}

    public void draw(SpriteBatch batch) { batch.draw(enemies.getKeyFrame(enemiesStateTime), position.x, position.y);
    }
}
