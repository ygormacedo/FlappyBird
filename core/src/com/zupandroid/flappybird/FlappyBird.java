package com.zupandroid.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {


    private SpriteBatch batch;
    private Texture[] birds;
    private Texture fundo;
    private Texture pipeBottom;
    private Texture pipeTop;
    private Random numerosAleatorios;

    // Atributos de configuração
    private int larguraDevice;
    private int alturaDevice;

    private float verify = 0;
    private float veloityDown = 0;
    private float positionStartVertical;
    private float positionMovePipeHorizontal;
    private float spaceEntrePipe;
    private float deltaTime;
    private float alturaEntrePipe;
//...
    public void create() {

        batch = new SpriteBatch();
        numerosAleatorios = new Random();
        birds = new Texture[3];
        birds[0] = new Texture("passaro1.png");
        birds[1] = new Texture("passaro2.png");
        birds[2] = new Texture("passaro3.png");
        pipeBottom = new Texture("cano_baixo_maior.png");
        pipeTop = new Texture("cano_topo_maior.png");

        fundo = new Texture("fundo.png");

        larguraDevice = Gdx.graphics.getWidth();
        alturaDevice = Gdx.graphics.getHeight();
        positionStartVertical = alturaDevice / 2;
        positionMovePipeHorizontal = larguraDevice;
        spaceEntrePipe = 450;


    }

    @Override
    public void render() {
        deltaTime = Gdx.graphics.getDeltaTime();

        verify += Gdx.graphics.getDeltaTime() * 10;
        positionMovePipeHorizontal -= deltaTime * 200;
        veloityDown++;


        if (verify > 2) verify = 0;

        if (Gdx.input.justTouched()) {

            veloityDown = -15;

        }

        // Verifica se o pipe saiu completamente.
        if (positionMovePipeHorizontal < -pipeTop.getWidth()) {
            positionMovePipeHorizontal = larguraDevice;
            alturaEntrePipe = numerosAleatorios.nextInt(400) - 200;
        }

        if (positionStartVertical > 100 || veloityDown < 0)
            positionStartVertical = positionStartVertical - veloityDown;

        batch.begin();

        batch.draw(fundo, 0, 0, larguraDevice, alturaDevice);
        batch.draw(pipeTop, positionMovePipeHorizontal, alturaDevice / 2 + spaceEntrePipe / 2 + alturaEntrePipe);
        batch.draw(pipeBottom, positionMovePipeHorizontal, alturaDevice / 2 - pipeBottom.getHeight() - spaceEntrePipe / 2 - alturaEntrePipe);
        batch.draw(birds[(int) verify], 100, positionStartVertical);


//        if (positionMovePipeHorizontal )


        batch.end();


    }
}
