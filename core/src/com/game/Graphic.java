package com.game;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Graphic {
    private Animation<TextureAtlas.AtlasRegion> anim;
    private float time;

    public Graphic(TextureAtlas atlas, String animName, int fps, Animation.PlayMode mode) {
        anim = new Animation<>(1.0f/fps, atlas.findRegions(animName));
        anim.setPlayMode(mode);
    }

    public void setTime(float dTime) {
        this.time+=dTime;
    }

    public TextureRegion getFrame(){return anim.getKeyFrame(this.time);}

    public boolean isEnd() {
        return anim.isAnimationFinished(this.time);
    }
    public void zTime () {
        this.time = 0;
    }
}
