package com.zupandroid.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {


    private SpriteBatch batch;
    private Texture[] birds;
    private Texture fundo;
    private Texture pipeBottom;
    private Texture pipeTop;
    private Random numerosAleatorios;
    private BitmapFont fonte;
    private Circle birdCiclo;
    private Rectangle retanguloPipeTop;
    private Rectangle retanguloPipeBottom;
    private ShapeRenderer shape;

    // Atributos de configuração
    private int larguraDevice;
    private int alturaDevice;
    private int statsGame = 0; // 0 = Jogo não iniciado
    private int pontuacao = 0;

    private float verify = 0;
    private float veloityDown = 0;
    private float positionStartVertical;
    private float positionMovePipeHorizontal;
    private float spaceEntrePipe;
    private float deltaTime;
    private float alturaEntrePipe;
    private boolean makePoint = false;

    //...
    public void create() {

        batch = new SpriteBatch();
        numerosAleatorios = new Random();
        birdCiclo = new Circle();
        retanguloPipeTop = new Rectangle();
        retanguloPipeBottom = new Rectangle();
        shape = new ShapeRenderer();
        birds = new Texture[3];
        fonte = new BitmapFont();
        fonte.setColor(Color.WHITE);
        fonte.getData().setScale(10);

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

        if (verify > 2) verify = 0;

        if (statsGame == 0) { //Não iniciado
            if (Gdx.input.justTouched()) {
                statsGame = 1;
            }

        } else {

            positionMovePipeHorizontal -= deltaTime * 200;
            veloityDown++;


            if (Gdx.input.justTouched()) {

                veloityDown = -15;

            }
            if (positionStartVertical > 100 || veloityDown < 0)
                positionStartVertical = positionStartVertical - veloityDown;

            // Verifica se o pipe saiu completamente.
            if (positionMovePipeHorizontal < -pipeTop.getWidth()) {
                positionMovePipeHorizontal = larguraDevice;
                alturaEntrePipe = numerosAleatorios.nextInt(400) - 200;
                makePoint = false;
            }

            //Verificar pontuação

            if (positionMovePipeHorizontal < 120) {
                if (!makePoint) {
                    pontuacao++;
                    makePoint = true;
                }

            }
        }

        batch.begin();

        batch.draw(fundo, 0, 0, larguraDevice, alturaDevice);
        batch.draw(pipeTop, positionMovePipeHorizontal, alturaDevice / 2 + spaceEntrePipe / 2 + alturaEntrePipe);
        batch.draw(pipeBottom, positionMovePipeHorizontal, alturaDevice / 2 - pipeBottom.getHeight() - spaceEntrePipe / 2 - alturaEntrePipe);

        //Posição inicial do passaro
        batch.draw(birds[(int) verify], 120, positionStartVertical);

        fonte.draw(batch, String.valueOf(pontuacao), larguraDevice / 2, alturaDevice - 50);


//        if (positionMovePipeHorizontal )


        batch.end();

        birdCiclo.set(120, 400, 30);


        // desenhar formas
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.circle(birdCiclo.x, birdCiclo.y, birdCiclo.radius);
        shape.end();
    }
}
