package com.mygdx.game.screens;

import SpritesPackage.Tank;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGame;

import java.util.ArrayList;


public class GameScreen implements Screen {
    private float impulse = 5f;
    ArrayList<bullet1> bulletmanager = new ArrayList<>();
    int bspeed=30;
    Vector2 tankl=new Vector2(0,0);
    Vector2 tankc=new Vector2(0,0);
    Texture bstexture;
    private Texture mainGameBG;
    private MyGame game;
    private OrthographicCamera camera;
    private Viewport gamePort;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private TrajectoryActor trajectoryActor;
    private OrthogonalTiledMapRenderer renderer;
    private ImageButton button;
    private Stage stage;
    private Skin skin;
    private TextButton textButton;
    private World world;
    private Box2DDebugRenderer b2dr;
    private Tank tank1;
    private Tank tank2;
    private boolean turn;  // if turn is true, it's player 1's turn, if false, it's player 2's turn
    private Music music;
    private Sound sound;
    private FuelBar fuelBar;
    private FuelBar fuelBar2;
    private Texture player1;
    public SpriteBatch batch;
    private Texture img;
    private Texture backGround_Image;
    private Texture gameOver;

    private TextButton pauseButton;

    private Skin mySkin;

    private TextButton buttonPlay, buttonExit,next_button,previous_button;
    private Texture texture;
    public static int currentTank1;
    public static int currentTank2;
    private Texture gameovertexture;
    private Texture pauseTexture;

    public GameScreen(MyGame game) {
        this.setGame(game);
//        bullets = new ArrayList<Bullet>();
        bstexture=new Texture("bullet.png");
        gameovertexture=new Texture("gameover.png");
        pauseTexture=new Texture("pause_menu.png");
        stage = new Stage();

        turn = true;

        fuelBar = new FuelBar(200,50);
        fuelBar.setPosition(90, 100);
        fuelBar2 = new FuelBar(200,50);
        fuelBar2.setPosition(1100, 100);

        stage.addActor(fuelBar);
        stage.addActor(fuelBar2);

        //bg music
        music=MyGame.manager.get("AudioFiles/gameMusics/HEROICCC.mp3", Music.class);
        music.setVolume(0.2f);
        music.setLooping(true);
        music.play();
//        skin = new Skin(Gdx.files.internal("rainbow/skin/rainbow-ui.json"));
//        textButton = new TextButton("Pause", skin, "toggle");
//        textButton.setBounds(50,700,100,100);
//        stage.addActor(textButton);

//        button = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("badlogic.jpg"))));
//        button.setPosition(50, 50);
//        stage.addActor(button)

        camera = new OrthographicCamera();
        gamePort = new FitViewport(1600, 900, camera);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("TiledGround.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        Gdx.input.setInputProcessor(stage);

        world = new World(new Vector2(0, -150), true);
        if (TankSelectionScreen1.tankType ==1){
            tank1 = new Tank(world,new Texture("final tanks/iceTank.png"),1);

        }else if (TankSelectionScreen1.tankType ==2){
            tank1 = new Tank(world,new Texture("final tanks/armyTank.png"),1);
        }else if (TankSelectionScreen1.tankType ==3){
            tank1 = new Tank(world,new Texture("final tanks/fireTank.png"),1);
        }

        if (TankSelectionScreen2.tankType ==1){
            tank2 = new Tank(world,new Texture("final tanks/iceTank left.png"),2);
        }else if (TankSelectionScreen2.tankType ==2){
            tank2 = new Tank(world,new Texture("final tanks/armyTank left.png"),2);
        }else if (TankSelectionScreen2.tankType ==3){
            tank2 = new Tank(world,new Texture("final tanks/fireTank left.png"),2);
        }
        trajectoryActor = new TrajectoryActor(-100);
        trajectoryActor.setWidth(5f);
        trajectoryActor.setHeight(5f);
        stage.addActor(trajectoryActor);
        b2dr = new Box2DDebugRenderer();

        // healthbar of tanks
        tank1.healthbar = new Healthbar(300, 50);
        tank1.healthbar.setPosition(300, 800);
        tank2.healthbar = new Healthbar(300, 50);
        tank2.healthbar.setPosition(1000, 800);
        stage.addActor(tank1.healthbar);
        stage.addActor(tank2.healthbar);

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        // GROUND OBJECTS FROM TILED

        for (MapObject mapObject : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rectangle.getX() + rectangle.getWidth() / 2) , (rectangle.getY() + rectangle.getHeight() / 2));
            body = world.createBody(bdef);
            shape.setAsBox(rectangle.getWidth() / 2 , rectangle.getHeight() / 2 );
            fdef.shape = shape;
            body.createFixture(fdef);
        }


        //this.game=game;
//        this.game = game;
//        stage=new Stage();

        batch = new SpriteBatch();
        mySkin = new Skin(Gdx.files.internal("rainbow/skin/rainbow-ui.json"));
        pauseButton = new TextButton("Pause", mySkin, "default");
        pauseButton.setBounds(80, 800, 350, 65);

        stage.addActor(pauseButton);
        Gdx.input.setInputProcessor(stage);
    }

    public Texture getMainGameBG() {
        return mainGameBG;
    }

    public void setMainGameBG(Texture mainGameBG) {
        this.mainGameBG = mainGameBG;
    }

    public MyGame getGame() {
        return game;
    }

    public void setGame(MyGame game) {
        this.game = game;
    }

    public void update(float dt) {
        
        executeInput(dt);
        trajectoryActor.setPosition(tank1.b2body.getPosition().x+20, tank1.b2body.getPosition().y+20);
        world.step(1/60f, 6, 2);
        camera.update();
        renderer.setView(camera);
        tank1.update(dt);
        tank2.update(dt);

    }

    private void executeInput(float dt) {
        sound = MyGame.manager.get("AudioFiles/gameSounds/tankrunning.mp3", Sound.class);
        int c=0;
        int d=0;
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE)){
            tankStorage tankStorage = new tankStorage();
            tankStorage.x_coordinate = tank1.b2body.getPosition().x;
            tankStorage.y_coordinate = tank1.b2body.getPosition().y;
//            tankStorage.health = tank1.health;
        }

//        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
//            bullet1 mb =new bullet1(tank1.b2body.getPosition().x, tank1.b2body.getPosition().y, new Vector2(0,bspeed));
//            bulletmanager.add(mb);
//        }

        if (turn){


//                int x_tank2 = (int) tank2.b2body.getPosition().x;
//                int x_bullet = (int) bullet.getX();
//                healthbar2.setCurrentHealth(healthbar2.getCurrentHealth() - Math.abs(x_tank2 - x_bullet)*bullet.getDamage());
            if (Gdx.input.isKeyJustPressed(Input.Keys.E)){
                tank1.healthbar.setValue((float)(tank1.healthbar.getCurrentHealth()/ tank1.healthbar.maxHealth) - 0.1f);
                tank1.healthbar.setCurrentHealth(tank1.healthbar.getCurrentHealth() - 100);
            }




        }
        else{
//                int x_tank1 = (int) tank1.b2body.getPosition().x;
//                int x_bullet = (int) bullet.getX();
//                healthbar1.setCurrentHealth(healthbar1.getCurrentHealth() - Math.abs(x_tank1 - x_bullet)*bullet.getDamage());
            if (Gdx.input.isKeyJustPressed(Input.Keys.R)){
                tank2.healthbar.setValue((float)(tank2.healthbar.getCurrentHealth() / tank2.healthbar.maxHealth) - 0.1f);
                tank2.healthbar.setCurrentHealth(tank2.healthbar.getCurrentHealth() - 100);
            }
        }

//        if ((tank1.healthbar.getCurrentHealth() <= 0)    ||  (tank2.healthbar.getCurrentHealth() <= 0)){
//            game.setScreen(new GameOverScreen(game));
//        }

        if (tank1.healthbar.getCurrentHealth()<=0 || tank2.healthbar.getCurrentHealth()<=0){
            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
                game.setScreen(new GameMenuScreen(game));
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.P)){
            game.setScreen(new PauseGame(game));
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {

            if (turn) {
                bullet1 mb = new bullet1(tank1.b2body.getPosition().x, tank1.b2body.getPosition().y, new Vector2(bspeed,0));
                bulletmanager.add(mb);
                c++;
            }else{
                bullet1 mb = new bullet1(tank2.b2body.getPosition().x, tank2.b2body.getPosition().y, new Vector2(-bspeed,0));
                bulletmanager.add(mb);
                d++;
            }

            for (int i=0;i<c;i++){
//                if (Gdx.input.isKeyJustPressed(Input.Keys.E)){
                tank2.healthbar.setValue((float)(tank2.healthbar.getCurrentHealth()/ tank2.healthbar.maxHealth) - 0.1f);
                tank2.healthbar.setCurrentHealth(tank2.healthbar.getCurrentHealth() - 100);
                //                }
            }

            for (int i=0;i<d;i++){
//                if (Gdx.input.isKeyJustPressed(Input.Keys.E)){

                tank1.healthbar.setValue((float)(tank1.healthbar.getCurrentHealth()/ tank1.healthbar.maxHealth) - 0.1f);
                tank1.healthbar.setCurrentHealth(tank1.healthbar.getCurrentHealth() - 100);
            }

            turn = !turn;
            fuelBar.setValue(1);
            fuelBar2.setValue(1);

            // fire the bullet
//            if (turn) {
//                bullets.add(new Bullet(tank1.b2body.getPosition().x + 20, tank1.b2body.getPosition().y + 20, 45, 1));
//            } else {
//                bullets.add(new Bullet( tank2.b2body.getPosition().x + 20, tank2.b2body.getPosition().y + 20, 45, 2));
//            }

            //bullet collides with ground
            //we get the bullet's position on the ground

            if (turn){
//                int x_tank2 = (int) tank2.b2body.getPosition().x;
//                int x_bullet = (int) bullet.getX();
//                healthbar2.setCurrentHealth(healthbar2.getCurrentHealth() - Math.abs(x_tank2 - x_bullet)*bullet.getDamage());
                if (Gdx.input.isKeyJustPressed(Input.Keys.E)){
                    tank1.healthbar.setValue((float)(tank1.healthbar.getCurrentHealth()/ tank1.healthbar.maxHealth) - 0.1f);
                }
            }
            else{
//                int x_tank1 = (int) tank1.b2body.getPosition().x;
//                int x_bullet = (int) bullet.getX();
//                healthbar1.setCurrentHealth(healthbar1.getCurrentHealth() - Math.abs(x_tank1 - x_bullet)*bullet.getDamage());
                if (Gdx.input.isKeyJustPressed(Input.Keys.R)){
                    tank2.healthbar.setValue((float)(tank2.healthbar.getCurrentHealth() / tank2.healthbar.maxHealth) - 0.1f);
                }
            }

            // if health is 0, game over
            if (tank1.healthbar.getCurrentHealth() <= 0 || tank2.healthbar.getCurrentHealth() <= 0) {
                game.setScreen(new GameOverScreen(game));
            }

            // back button
            if (Gdx.input.getX() > 50 && Gdx.input.getX() < 130 && Gdx.input.getY() > 50 && Gdx.input.getY() < 130) {
                if (Gdx.input.justTouched()) {
                    dispose();
                    getGame().setScreen(new PauseGame(getGame()));
                }
            }

        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            trajectoryActor.angle += 1;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            trajectoryActor.angle -= 1;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            trajectoryActor.power -= 1;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            trajectoryActor.power += 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && tank1.b2body.getLinearVelocity().x <= 50 && turn) {
                if(fuelBar.getValue() > 0) {
                    tank1.b2body.applyLinearImpulse(new Vector2(impulse, 0), tank1.b2body.getWorldCenter(), true);
                    sound.play(1000f);
                    fuelBar.setValue(fuelBar.getValue() - 0.01f);
                }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && tank1.b2body.getLinearVelocity().x <= 50 && turn) {
                if (fuelBar.getValue() > 0) {
                    tank1.b2body.applyLinearImpulse(new Vector2(-(impulse), 0), tank1.b2body.getWorldCenter(), true);
                    sound.play(1000f);
                    fuelBar.setValue(fuelBar.getValue() - 0.01f);
                }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && tank2.b2body.getLinearVelocity().x <= 50 && !turn) {
                if (fuelBar2.getValue() > 0) {
                    tank2.b2body.applyLinearImpulse(new Vector2(-(impulse), 0), tank2.b2body.getWorldCenter(), true);
                    sound.play(1000f);
                    fuelBar2.setValue(fuelBar2.getValue() - 0.01f);
                }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && tank2.b2body.getLinearVelocity().x <= 50 && !turn) {
                if (fuelBar2.getValue() > 0) {
                    tank2.b2body.applyLinearImpulse(new Vector2(impulse, 0), tank2.b2body.getWorldCenter(), true);
                    sound.play(1000f);
                    fuelBar2.setValue(fuelBar2.getValue() - 0.01f);
                }
            }
        }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        //shooting

        //update bullets
//        ArrayList<Bullet> bulletsToRemove = new ArrayList<Bullet>();


        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        game.batch.setProjectionMatrix(camera.combined);
        stage.draw();
//        stage.act();
        stage.act(delta);
//        button.addListener(new ClickListener(){
//            @Override
//            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
//                System.out.println("yes");
//            }
//        });

        pauseButton.addListener(new ClickListener(){
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x,float y) {
                getGame().setScreen(new PauseGame(getGame()));
            }
        });

        b2dr.render(world, camera.combined);

        game.batch.begin();

        tank1.draw(game.batch);
        if (tank1.healthbar.getCurrentHealth() <= 0) {
            game.batch.draw(gameovertexture, 0, 0, 1600, 900);
        }
        tank2.draw(game.batch);
        if (tank2.healthbar.getCurrentHealth() <= 0) {
            game.batch.draw(gameovertexture, 0, 0, 1600, 900);
        }
//        if (Gdx.input.isKeyJustPressed(Input.Keys.P)){
//            getGame().setScreen(new PauseGame(getGame()));
//        }

        game.batch.end();

        game.batch.begin();
        int count=0;
        while(count<bulletmanager.size()){
            bullet1 cb = bulletmanager.get(count);
            cb.Update();
            game.batch.draw(bstexture,cb.bulletcoord.x,cb.bulletcoord.y);

            count++;
        }
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        stage.dispose();
    }
}
