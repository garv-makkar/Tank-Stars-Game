package com.mygdx.game.screens;

import com.badlogic.gdx.math.Vector2;

public class bullet1 {
    public Vector2 bulletcoord =new Vector2(0,0);
    public Vector2 bulletnewcoord =new Vector2(0,0);
    public bullet1(float x,float y,Vector2 sentvelocity){
        bulletcoord =new Vector2(x,y);
        bulletnewcoord =new Vector2(sentvelocity.x,sentvelocity.y);


    }
    public void Update(){
        bulletcoord.x= bulletcoord.x+ bulletnewcoord.x;
        bulletcoord.y = bulletcoord.y+  bulletnewcoord.y;
    }
}
