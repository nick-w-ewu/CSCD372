package com.witmer.nicholas.ashman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;


import java.util.Random;

/**
 * Created by nicho on 11/12/2015.
 */
public class Maze extends View implements View.OnClickListener, MediaPlayer.OnCompletionListener
{
    private int[][] maze = {{-1,2,2,2,2,2,1,2,2,2,2,1,1,1},
            {2,2,1,2,1,2,1,2,1,2,2,2,2,1},
            {1,2,1,1,1,2,2,2,1,2,1,1,2,1},
            {2,2,2,2,1,2,1,2,2,1,2,2,2,1},
            {2,2,1,2,2,2,2,2,2,1,2,1,1,1},
            {1,2,1,1,2,2,1,1,2,2,2,2,2,2},
            {2,1,2,2,2,2,1,1,1,1,2,1,2,1},
            {2,1,2,2,1,2,1,2,1,2,1,1,2,1},
            {2,2,2,1,2,2,2,2,2,2,1,1,2,1},
            {2,1,2,1,2,2,2,1,1,1,2,1,2,1},
            {2,1,2,2,1,1,2,2,2,2,2,2,2,1},
            {2,1,1,2,2,1,1,1,2,2,1,1,2,1},
            {2,2,2,1,2,2,1,2,2,2,2,1,2,2},
            {1,1,1,2,2,2,1,1,2,1,2,2,1,2}};

    private int blue = Color.rgb(51, 51, 255);
    private int cHight;
    private int cWidth;
    private float oHeigth = 14;
    private float oWidth = 14;
    private Character pacMan;
    private Character ghost1;
    private Character ghost2;
    private Character ghost3;
    private Character ghost4;
    private Character ghost5;
    private final int CAKE = 2;
    private final int WALL = 1;
    private int cakeCount;
    private UpdateGameStats mainActivity;
    private MediaPlayer sounds;
    private boolean isPaused;
    private boolean lost;
    private int level;
    private Handler pacManHandler = new Handler();
    private
    Runnable pacManRunner = new Runnable() {
        public int direction;

        @Override
        public void run()
        {
            direction = pacMan.getDirection();
            if(!isPaused && moveCharacter(direction, pacMan))
            {
                pacManHandler.postDelayed(this, 70);
            }
        }
    };
    private Handler ghost1Handler = new Handler();
    private Handler ghost2Handler = new Handler();
    private Handler ghost3Handler = new Handler();
    private Handler ghost4Handler = new Handler();
    private Handler ghost5Handler = new Handler();
    private Runnable ghost1Runner = new Runnable()
    {
        public int direction = 1;
        public int count = 0;
        Random r = new Random();
        @Override
        public void run()
        {
            if(!isPaused) {
                boolean canMove = true;
                if(level == 1)
                {
                    if (count == 0 || count % 2 == 0)
                    {
                        canMove = moveCharacter(direction, ghost1);
                    }
                }
                else
                {
                    canMove = moveCharacter(direction, ghost1);
                }
                if (canMove == false) {
                    int old = direction;
                    direction = r.nextInt(4) + 1;
                    if (old == direction) {
                        direction = r.nextInt(4) + 1;
                    }
                }
                count++;
                ghost1Handler.postDelayed(this, 70);
            }
        }
    };
    private Runnable ghost2Runner = new Runnable()
    {
        public int direction = 4;
        public int count = 0;
        Random r = new Random();
        @Override
        public void run()
        {
            if(!isPaused) {
                boolean canMove = true;
                if(level == 1)
                {
                    if (count == 0 || count % 2 == 0)
                    {
                        canMove = moveCharacter(direction, ghost2);
                    }
                }
                else
                {
                    canMove = moveCharacter(direction, ghost2);
                }
                if (canMove == false) {
                    int old = direction;
                    direction = r.nextInt(4) + 1;
                    if (old == direction) {
                        direction = r.nextInt(4) + 1;
                    }
                }
                count++;
                ghost1Handler.postDelayed(this, 70);
            }
        }
    };
    private Runnable ghost3Runner = new Runnable()
    {
        public int direction = 3;
        public int count = 0;
        Random r = new Random();
        @Override
        public void run()
        {
            if(!isPaused) {
                boolean canMove = true;
                if(level == 1)
                {
                    if (count == 0 || count % 2 == 0)
                    {
                        canMove = moveCharacter(direction, ghost3);
                    }
                }
                else
                {
                    canMove = moveCharacter(direction, ghost3);
                }
                if (canMove == false) {
                    int old = direction;
                    direction = r.nextInt(4) + 1;
                    if (old == direction) {
                        direction = r.nextInt(4) + 1;
                    }
                }
                count++;
                ghost1Handler.postDelayed(this, 70);
            }
        }
    };
    private Runnable ghost4Runner = new Runnable()
    {
        public int direction = 1;
        public int count = 0;
        Random r = new Random();
        @Override
        public void run()
        {
            if(!isPaused) {
                boolean canMove = true;
                canMove = moveCharacter(direction, ghost4);
                if (canMove == false) {
                    int old = direction;
                    direction = r.nextInt(4) + 1;
                    if (old == direction) {
                        direction = r.nextInt(4) + 1;
                    }
                }
                count++;
                ghost4Handler.postDelayed(this, 70);
            }
        }
    };
    private Runnable ghost5Runner = new Runnable()
    {
        public int direction = 1;
        public int count = 0;
        Random r = new Random();
        @Override
        public void run()
        {
            if(!isPaused) {
                boolean canMove = true;
                canMove = moveCharacter(direction, ghost5);
                if (canMove == false) {
                    int old = direction;
                    direction = r.nextInt(4) + 1;
                    if (old == direction) {
                        direction = r.nextInt(4) + 1;
                    }
                }
                count++;
                ghost5Handler.postDelayed(this, 70);
            }
        }
    };

    public Maze(Context context)
    {
        super(context);
        setUp();
    }

    public Maze(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        setUp();
    }

    public Maze(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        setUp();
    }

    public void cheat()
    {
        for(int i = 0; i < this.maze.length; i++)
        {
            for(int j = 0; j < this.maze[0].length; j++)
            {
                if(this.maze[i][j] == CAKE)
                {
                    this.maze[i][j] = 0;;
                }
            }
        }
        this.maze[2][1] = CAKE;
        this.cakeCount = 1;
        invalidate();
    }

    public void setUp()
    {
        if(!isInEditMode())
        {
            this.mainActivity = (UpdateGameStats) getContext();
        }
        this.pacMan = new Character(0, 0, Color.rgb(255, 255, 0), "player");
        this.ghost1 = new Character(13, 13, Color.RED, "ghost");
        this.ghost2 = new Character(5, 13, Color.RED, "ghost");
        this.ghost3 = new Character(13, 3, Color.RED, "ghost");
        countCakes();
        setOnClickListener(this);
        this.isPaused = true;
        this.level = 1;
    }

    public void setPaused()
    {
        this.isPaused = true;
    }

    private int generateDirection(Character character)
    {
        int i = character.getCurCol();
        int j = character.getCurRow();
        Random r = new Random();
        int direction = r.nextInt(4) + 1;
        while(true)
        {
            switch (direction)
            {
                case 1:
                    if(i-1 >= 0 && this.maze[i-1][j] != WALL)
                    {
                        return direction;
                    }
                    direction = r.nextInt(4) + 1;
                case 2:
                    if(i+1 > this.maze.length && this.maze[i+1][j] != WALL)
                    {
                        return direction;
                    }
                    direction = r.nextInt(4) + 1;
                case 3:
                    if(j+1 < this.maze.length && this.maze[i][j+1] != WALL)
                    {
                        return direction;
                    }
                    direction = r.nextInt(4) + 1;
                case 4:
                    if(j-1 >= 0 && this.maze[i][j-1] != WALL)
                    {
                        return direction;
                    }
                    direction = r.nextInt(4) + 1;
            }
        }
    }

    private boolean moveCharacter(int direction, Character mover)
    {
        switch (direction)
        {
            case 1:
                return moveUp(mover);
            case 2:
                return moveDown(mover);
            case 3:
                return moveRight(mover);
            case 4:
                return moveLeft(mover);
        }
        return false;
    }

    public void userMove(int direction)
    {
        if(!isPaused)
        {
            this.pacMan.setDirection(direction);
            this.pacManRunner.run();
        }
        else
        {
            Toast.makeText(getContext(), "The game is currently paused, please tap the game board to start", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean moveLeft(Character character)
    {
        int i = character.getCurCol();
        int j = character.getCurRow();

        if(j-1 >= 0 && this.maze[i][j-1] != 1)
        {
            character.setCurRow(j - 1);
            removeCake(character, i, j-1);
            if(crossedGhost(character, i, j))
            {
                this.lost = true;
                gameEnds();
            }
            invalidate();
        }
        if(character.getDirection() != 4)
        {
            return false;
        }
        if((j-2 >= 0 && this.maze[i][j-2] != 1))
        {
            return true;
        }
        return false;
    }

    private boolean moveRight(Character character)
    {
        int i = character.getCurCol();
        int j = character.getCurRow();

        if(j+1 < this.maze[0].length && this.maze[i][j+1] != 1)
        {
            character.setCurRow(j + 1);
            removeCake(character, i, j+1);
            if(crossedGhost(character, i, j))
            {
                this.lost = true;
                gameEnds();
            }
            invalidate();
        }
        if(character.getDirection() != 3)
        {
            return false;
        }
        if((j+2 < this.maze.length && this.maze[i][j+2] != 1))
        {
            return true;
        }
        return false;
    }

    private boolean moveDown(Character character)
    {
        int i = character.getCurCol();
        int j = character.getCurRow();

        if(i+1 < this.maze.length && this.maze[i+1][j] != 1 && character.getDirection() == 2)
        {
            character.setCurCol(i + 1);
            removeCake(character, i+1, j);
            if(crossedGhost(character, i, j))
            {
                this.lost = true;
                gameEnds();
            }
            invalidate();
        }
        if(character.getDirection() != 2)
        {
            return false;
        }
        if((i+2 < this.maze.length && this.maze[i+2][j] != 1))
        {
            return true;
        }
        return false;
    }

    private boolean moveUp(Character character) {
        int i = character.getCurCol();
        int j = character.getCurRow();
        if(i-1 >= 0 && this.maze[i-1][j] !=1)
        {
            character.setCurCol(i - 1);
            removeCake(character, i-1, j);
            if(crossedGhost(character, i, j))
            {
                this.lost = true;
                gameEnds();
            }
            invalidate();
        }
        if(character.getDirection() != 1)
        {
            return false;
        }
        if((i-2 >= 0 && this.maze[i-2][j] != 1))
        {
            return true;
        }
        return false;
    }

    private boolean crossedGhost(Character character, int i, int j)
    {
        if(character.getType().equals("ghost") && this.pacMan.checkLocation(i, j))
        {
            return true;
        }
        else if(character.getType().equals("player"))
        {
            if(this.ghost1.checkLocation(i, j) && this.ghost2.checkLocation(i, j) && this.ghost3.checkLocation(i, j))
            {
                return true;
            }
        }
        return false;
    }

    private void removeCake(Character character, int i, int j)
    {
        if(character.getType().equals("player") && this.maze[i][j] > 0)
        {
            this.maze[i][j] = 0;
            this.cakeCount--;
            playSound();
        }
        if(this.cakeCount == 0)
        {
            this.lost = false;
            gameEnds();
        }
    }

    private void playSound()
    {
        if(this.sounds == null)
        {
            this.sounds = MediaPlayer.create(getContext(), R.raw.eating);
            this.sounds.setOnCompletionListener(this);
            this.sounds.start();
        }
        else
        {
            this.sounds.pause();
            this.sounds.seekTo(0);
            this.sounds.start();
        }
    }

    private void gameEnds()
    {
        this.isPaused = true;
        stopAllCharacters();
        if(this.lost)
        {
            Toast.makeText(getContext(), "You lost the game", Toast.LENGTH_SHORT).show();
            this.level = 1;
            resetGame();
        }
        else
        {
            Toast.makeText(getContext(), "You won level " + this.level, Toast.LENGTH_SHORT).show();
            if(level == 2)
            {
                level = 1;
            }
            else
            {
                this.level = 2;
            }
            resetGame();
        }
    }

    public void stopAllCharacters()
    {
        this.pacManHandler.removeCallbacksAndMessages(this.pacManRunner);
        this.ghost1Handler.removeCallbacksAndMessages(this.ghost1Runner);
        this.ghost2Handler.removeCallbacksAndMessages(this.ghost2Runner);
        this.ghost3Handler.removeCallbacksAndMessages(this.ghost3Runner);
        if(this.level == 2)
        {
            this.ghost3Handler.removeCallbacksAndMessages(this.ghost4Runner);
            this.ghost3Handler.removeCallbacksAndMessages(this.ghost4Runner);
        }
    }

    private void resetGame()
    {
        if(!this.lost)
        {
            Toast.makeText(getContext(), "Tap on the gameboard to start level " + this.level, Toast.LENGTH_SHORT).show();
        }
        if(this.level == 1)
        {
            this.pacMan.setPosition(0, 0);
            this.ghost1.setPosition(13, 13);
            this.ghost2.setPosition(5, 13);
            this.ghost3.setPosition(13, 3);
        }
        else
        {
            this.pacMan.setPosition(0, 0);
            this.ghost1.setPosition(13, 13);
            this.ghost2.setPosition(5, 13);
            this.ghost3.setPosition(13, 3);
            this.ghost4 = new Character(13, 10, Color.RED, "ghost");
            this.ghost5 = new Character(5, 4, Color.RED, "ghost");
        }
        invalidate();
        countCakes();
    }


    @Override
    protected void onSizeChanged(int w, int h, int odlw, int oldh) {
        super.onSizeChanged(w, h, odlw, oldh);
        this.cHight = h;
        this.cWidth = w;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = 0;
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        if (width > height) {
            size = height;
        } else {
            size = width;
        }
        setMeasuredDimension(size, size);
    }

    private void countCakes()
    {
        this.cakeCount = 0;
        for(int i = 0; i < this.maze.length; i++)
        {
            for(int j = 0; j < this.maze[0].length; j++)
            {
                if(this.maze[i][j] == 0)
                {
                    this.maze[i][j] = CAKE;
                }
                if(this.maze[i][j] == CAKE)
                {
                    this.cakeCount++;
                }
            }
        }
        if(this.isPaused)
        {
            invalidate();
        }
    }

    public void onDraw(Canvas c)
    {
        super.onDraw(c);
        float wScale = cWidth/(float)this.oWidth;
        float hScale = cHight/(float)this.oHeigth;
        c.scale(wScale, hScale);
        c.drawColor(blue);
        if(!isInEditMode())
        {
            this.pacMan.drawCharacter(c);

            Path square = new Path();
            Paint p = new Paint();
            p.setColor(Color.WHITE);

            for(int i = 0; i < this.maze.length; i++)
            {
                for(int j = 0; j < this.maze[0].length; j++)
                {
                    if(this.maze[i][j] == CAKE)
                    {
                        c.drawCircle(j+.5f, i+0.5f, .25f, p);
                    }
                }
            }
            this.ghost1.drawCharacter(c);
            this.ghost2.drawCharacter(c);
            this.ghost3.drawCharacter(c);
            if(level == 2 && ghost4 != null && ghost5 != null)
            {
                this.ghost4.drawCharacter(c);
                this.ghost5.drawCharacter(c);
            }
            for(int i = 0; i < this.maze.length; i++)
            {
                for(int j = 0; j < this.maze[0].length; j++)
                {
                    if (this.maze[i][j] == WALL)
                    {
                        square.moveTo(j, i);
                        square.lineTo(j + 1, i);
                        square.lineTo(j + 1, i + 1);
                        square.lineTo( j, i + 1);
                        square.close();
                        c.clipPath(square, Region.Op.REPLACE);
                        c.drawColor(Color.BLACK);
                    }
                }
            }
            this.mainActivity.updateCakeCount(this.cakeCount);
            this.mainActivity.updateLevel(this.level);
        }
    }


    @Override
    public void onClick(View v)
    {
        if(isPaused)
        {
            this.isPaused = false;
            this.ghost1Runner.run();
            this.ghost2Runner.run();
            this.ghost3Runner.run();
            if (this.level == 2)
            {
                this.ghost4Runner.run();
                this.ghost5Runner.run();
            }
        }
        else
        {
            stopAllCharacters();
            this.isPaused = true;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp)
    {
        this.sounds.release();
        this.sounds = null;
    }
}
