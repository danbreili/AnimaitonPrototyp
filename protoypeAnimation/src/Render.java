import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.awt.TextureRenderer;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;
import com.jogamp.opengl.util.texture.TextureSequence;
import com.jogamp.opengl.util.texture.spi.TextureWriter;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.TimerTask;
import java.util.Timer;

public class Render implements GLEventListener {



    private GLU glu = new GLU();


    private float rquad;



    private int texture;



    public void drawCard(GLAutoDrawable drawable){
        final GL2 gl = drawable.getGL().getGL2();

        gl.glTranslatef(0.5f, 1.0f, -8.0f);
        gl.glRotatef(rquad, 0.0f, 1.0f, 0.0f);

        //material
        float mat_ambient[] = {0.24f, 0.19f, 0.07f, 1.0f};
        float mat_diffuse[] = {0.75f, 0.60f, 0.22f, 1.0f};
        float mat_specular[] = {0.62f, 0.55f, 0.36f, 1.0f};
        float mat_shininess = 51.2f;

        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK,GL2.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, mat_shininess);

        gl.glEnable(GL2.GL_TEXTURE_2D);
        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);

        //Quad
        gl.glBegin(GL2.GL_QUADS);                               //draws Quad
        gl.glVertex3f(-1.0f, 1.0f, 0.0f);           //top left
        gl.glTexCoord2f(1f, 0f);

        gl.glVertex3f(1.0f, 1.0f, 0.0f);            //top right
        gl.glTexCoord2f(1f, 1f);

        gl.glVertex3f(1.0f, -2.0f, 0.0f);           //bottom rigth
        gl.glTexCoord2f(0f, 1f);

        gl.glVertex3f(-1.0f, -2.0f, 0.0f);          //bottom left
        gl.glTexCoord2f(0f, 0f);
        gl.glEnd();

        gl.glDisable(GL2.GL_TEXTURE_2D);
        gl.glFlush();

        rquad += 1.0f;                                 //assigning the angle
    }

    public void drawFloor(GLAutoDrawable drawable){
        final GL2 gl = drawable.getGL().getGL2();
        gl.glTranslatef(0.5f, 1.0f, -8.0f);
        //material
        float mat_ambient[] = {0.25f, 0.20f, 0.20f, 0.9f};
        float mat_diffuse[] = {1.0f, 0.82f, 0.82f, 0.9f};
        float mat_specular[] = {0.29f, 0.29f, 0.29f, 0.9f};
        float mat_shininess = 11.3f;

        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK,GL2.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK, GL2.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS, mat_shininess);


        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex3f(-10.0f, -2.0f, 10.0f);
        gl.glVertex3f(-10.0f, -2.0f, -10.0f);
        gl.glVertex3f(10.0f, -2.0f, -10.0f);
        gl.glVertex3f(10.0f, -2.0f, 10.0f);
        gl.glEnd();
        gl.glFlush();

    }



    @Override
    public void display(GLAutoDrawable drawable) {
        // TODO Auto-generated method stub
        final GL2 gl = drawable.getGL().getGL2();


        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);


        gl.glLoadIdentity();  //reset view

        gl.glPushMatrix();
        drawCard(drawable);
        gl.glPopMatrix();

        gl.glPushMatrix();

        drawFloor(drawable);
        gl.glPopMatrix();

    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        // TODO Auto-generated method stub
        final GL2 gl = drawable.getGL().getGL2();
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        // TODO Auto-generated method stub
        final GL2 gl = drawable.getGL().getGL2();


       /* float light_pos[] = {0, 2, 5, 0};
        float light_color_am[] = {1, 1, 1, 1};
        float light_color_diff[] = {1, 1, 1, 1};
        float light_color_spec[] = {1, 1, 1, 1};


        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, light_pos, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, light_color_am, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, light_color_diff, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, light_color_spec, 0);
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glEnable(GL2.GL_SMOOTH);*/

        float light_pos2[] = {0, 0.25f, 0.25f, 1};
        float light_color_am2[] = {1, 1, 1, 1};
        float light_color_diff2[] = {1, 1, 1, 1};
        float light_color_spec2[] = {1, 1, 1, 1};

        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, light_pos2, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, light_color_am2, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, light_color_diff2, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_SPECULAR, light_color_spec2, 0);
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT1);
        gl.glEnable(GL.GL_DEPTH_TEST);
        gl.glEnable(GL2.GL_SMOOTH);

        //Texture
        gl.glEnable(GL2.GL_TEXTURE_2D);
        try{
            File im = new File("resources/karte.png");
            Texture t = TextureIO.newTexture(im, true);
            texture = t.getTextureObject(gl);
        }catch(IOException e){
            e.printStackTrace();
        }


    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        // TODO Auto-generated method stub
        final GL2 gl = drawable.getGL().getGL2();

        // renders objects on the screen
        if (height <= 0)
            height = 1;
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }


    public void Start() {


        //setup OpenGL version 2
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        //canvas
        GLCanvas glCanvas = new GLCanvas(capabilities);
        Render r = new Render();
        glCanvas.addGLEventListener(r);
        glCanvas.setSize(600, 600);         //screensize

        final FPSAnimator animator = new FPSAnimator(glCanvas, 60, true);      //sets FPS, max 300

        final JFrame frame = new JFrame("prototype Kartenanimation");

        frame.getContentPane().add(glCanvas);

        //exit program
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (animator.isStarted())
                    animator.stop();
                System.exit(0);
            }
        });

        frame.setSize(frame.getContentPane().getPreferredSize());               //gets GL canvas size

        frame.setLocationRelativeTo(null);                                  //centers the window on the screen

        frame.setVisible(true);

        animator.start();

                         /*   class TimedAnimationStop {                  //Notloesung
                                Timer timer = new Timer();
                                TimerTask stopAnimation = new TimerTask() {
                                    @Override
                                    public void run() {
                                        animator.stop();
                                    }
                                };

                                public void TimedAnimationStop() {
                                    timer.schedule(stopAnimation, new Date(System.currentTimeMillis() + 3 * 1000));
                                }

                            }

        TimedAnimationStop tas = new TimedAnimationStop();
        tas.TimedAnimationStop();*/


    }

}