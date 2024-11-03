import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
// import javax.swing.plaf.FontUIResource;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class fp extends JPanel implements ActionListener, KeyListener {

    int brdwd = 560;
    int brdht = 640;

    Image bgimg;
    Image birdimg;
    Image topimg;
    Image dwnimg;

    int birdX = brdwd / 4;
    int birdY = brdht / 4;
    int birdwd = 50;
    int birdht = 50;

    public class Bird {
        int x = birdX;
        int y = birdY;
        int wd = birdwd;
        int ht = birdht;
        Image ig;

        Bird(Image ig) {
            this.ig = ig;
        }
    }

    int pipeX = brdwd;
    int pipeY = 0;
    int pipeWd = 64;
    int pipeht = 512;

    public class Pipe {
        int x = pipeX;
        int y = pipeY;
        int wd = pipeWd;
        int ht = pipeht;
        Image imgy;
        boolean pass = false;

        Pipe(Image imgy) {
            this.imgy = imgy;
        }
    }

    Bird bird;
    int veloX = -4;
    int veloY = 0;
    int gravi = 1;

    ArrayList<Pipe> pipes = new ArrayList<>();
    Random random = new Random();

    Timer gameloop;
    Timer palceTimer;
    boolean gover = false;
    double scr = 0;

    fp() {
        setPreferredSize(new Dimension(brdwd, brdht));
        setFocusable(true);
        addKeyListener(this);

        bgimg = new ImageIcon(getClass().getResource("./gundam1.jpg")).getImage();
        birdimg = new ImageIcon(getClass().getResource("./mysize.png")).getImage();
        topimg = new ImageIcon(getClass().getResource("./pipe.jpg")).getImage();
        dwnimg = new ImageIcon(getClass().getResource("./lwpipe.jpg")).getImage();

        bird = new Bird(birdimg);

        palceTimer = new Timer(1200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                palcpipe();
            }
        });
        palceTimer.start();

        gameloop = new Timer(1000 / 60, this);
        gameloop.start();
    }

    public void palcpipe() {
        int randompY = (int) (pipeY - pipeht / 4 - Math.random() * (pipeht / 2));
        int opensp = brdht / 4;

        Pipe tpp = new Pipe(topimg);
        tpp.y = randompY;
        pipes.add(tpp);

        Pipe btmpp = new Pipe(dwnimg);
        btmpp.y = tpp.y + pipeht + opensp;
        pipes.add(btmpp);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        //meri background

        g.drawImage(bgimg, 0, 0, brdwd, brdht, null);

        // birdki

        g.drawImage(bird.ig, bird.x, bird.y, bird.wd, bird.ht, null);

        //pipes
        for (Pipe pippy : pipes) {
            g.drawImage(pippy.imgy, pippy.x, pippy.y, pipeWd, pipeht, null);
        }

        //score like a hero
        g.setColor(Color.cyan);
        g.setFont(new Font("Ink Free",Font.ITALIC, 32) );

        if (gover) {
            g.drawString("Game overr" +  String.valueOf((int) scr),12,30);
            System.out.println("you lose");
        }
        else{
            g.drawString(String.valueOf((int) scr), 10, 35);
        }
    }

    public void moove() {

        //bird 

        veloY += gravi;
        bird.y += veloY;
        bird.y = Math.max(bird.y, 0);

        // pipes
        for (Pipe pippy : pipes) {
            pippy.x += veloX;

            if (!pippy.pass && bird.x > pippy.x +pippy.wd) {
                pippy.pass= true;
                scr+= 0.5; //since maths tells us 0.5*2 (pipes)= 1 so score is 1
            }
            if (collosion(bird, pippy)) {
                gover = true;
            }

        }

        if (bird.y > brdht) {
            gover = true;
        }
    }

    public boolean collosion(Bird a, Pipe b) {
        return a.x < b.x + b.wd &&
                a.x + a.wd > b.x &&
                a.y < b.y + b.ht &&
                a.y + a.ht > b.y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        moove();
        if (gover) {
            palceTimer.stop();
            gameloop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            veloY = -10;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
