package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;

	private Map<String, Graphic> aniMap = new HashMap<>();

	private TextureAtlas atlas;

	private float time;

	private int SCALE = 70;

	private Fight fight;

	private BitmapFont font;

	@Override
	public void create () {
		font = new BitmapFont();
		font.setColor(Color.YELLOW);
		font.getData().setScale(1, 1);

		batch = new SpriteBatch();
		img = new Texture("night.jpg");
		atlas = new TextureAtlas("unnamed.atlas");
		aniMap.put("stay", new Graphic(atlas, "stay", 1, Animation.PlayMode.LOOP));
		aniMap.put("walk", new Graphic(atlas, "run", 12, Animation.PlayMode.LOOP));
		aniMap.put("shot", new Graphic(atlas, "shot", 1, Animation.PlayMode.NORMAL));

		int step = 0;
		int teamSize = 10;
		int fieldSize = 10;
		String [] request = new String [] {"Light", "Peasant", "Robber", "Sniper", "Warlock"};
		String [] request1 = new String [] {"Dark", "Peasant", "Spearman", "Xbowman", "Monk"};

		fight = new Fight(teamSize, request, request1, fieldSize);
		fight.round(step);

	}

	Random r = new Random();

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		float mx = Gdx.input.getX();
		float my = Gdx.graphics.getHeight() - Gdx.input.getY();

		batch.begin();
		this.time += Gdx.graphics.getDeltaTime();

		batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		for (int i = 0; i < fight.getMembers().size(); i++) {

			if ((r.nextInt(-1000000, 1000000) > 980000) && (this.time > 0.4f)) {
				fight.getMembers().get(i).setDamageValue(1);
				this.time = 0.0f;

			}
			if (fight.getMembers().get(i).getDamageValue() > 0) {
				aniMap.get("shot").setTime(Gdx.graphics.getDeltaTime());
				batch.draw(aniMap.get("shot").getFrame(),
						(fight.getMembers().get(i).getPosition().getX()) * SCALE,
						(fight.getMembers().get(i).getPosition().getY()) * SCALE
				);

			} else {
				aniMap.get("stay").setTime(Gdx.graphics.getDeltaTime());
				batch.draw(aniMap.get("stay").getFrame(),
						(fight.getMembers().get(i).getPosition().getX()) * SCALE,
						(fight.getMembers().get(i).getPosition().getY()) * SCALE
				);

			}

			if ((fight.getMembers().get(i).getDamageValue() > 0) && aniMap.get("shot").isEnd()) {
				fight.getMembers().get(i).setDamageValue(0);
				aniMap.get("shot").zTime();
			}

		}

		batch.end();
	}


	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
