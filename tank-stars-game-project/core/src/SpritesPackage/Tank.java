package SpritesPackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.MyGame;
import com.mygdx.game.screens.Healthbar;

import java.io.Serializable;

public class Tank extends Sprite implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    public World world;
    public Body b2body;
    private TextureRegion tankStand;
    public int playerNumber;
    public Healthbar healthbar;
    private float x_coordinate;
    private float y_coordinate;
    private int health;


    public Tank(World world, Texture texture, int playerNumber) {
        super.setTexture(texture);
        this.world = world;
        this.playerNumber = playerNumber;
        defineTank();
        setBounds(0, 0, 110f, 60f);
        setRegion(texture);

//        if (playerNumber == 1){
//            health=
//        }


    }

    public void defineTank(){
        BodyDef bdef = new BodyDef();
        if(playerNumber == 1){
            bdef.position.set(100, 220);
            x_coordinate = 100;
            y_coordinate = 220;

        }
        else if(playerNumber == 2){
            bdef.position.set(1500, 220);
            x_coordinate = 1500;
            y_coordinate = 220;

        }
//        bdef.position.set(100,300);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(22);

        fdef.shape = shape;
        fdef.friction=0.95f;
        fdef.restitution = 0f;
        b2body.createFixture(fdef);
    }


    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }


}