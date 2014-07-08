package u11;

//depends on jogl.jar and gluegen-rt.jar
import java.awt.*;
import java.awt.event.*;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.FPSAnimator;
import com.sun.opengl.util.Animator;

class Scene implements GLEventListener {

    static GLU glu = new GLU();
    static GLCanvas canvas = new GLCanvas();
    float theta = 0.0f;
    
    /** Called by the drawable immediately after the OpenGL context is
     * initialized for the first time. Can be used to perform one-time OpenGL
     * initialization such as setup of lights and display lists.
     * @param gLDrawable The GLAutoDrawable object.
     */
    public void init(GLAutoDrawable gLDrawable) {
        final GL2 gl = gLDrawable.getGL().getGL2();
        
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);    // clear the background with black
                                                //FLAT = 1 color per face, SMOOTH = one color per pixel (smooth shading)
        gl.glClearDepth(1.0f);                  //depth buffer
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        //gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST); // best perspective correction
        gl.glShadeModel(GL2.GL_SMOOTH);           // shading
    }
    
    
    /** Called by the drawable to initiate OpenGL rendering by the client.
     * After all GLEventListeners have been notified of a display event, the
     * drawable will swap its buffers if necessary.
     * @param gLDrawable The GLAutoDrawable object.
     */
    public void display(GLAutoDrawable gLDrawable) {
        final GL2 gl = gLDrawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT); //clear the screen (and depth buffer)
        //gl.glLoadIdentity();    //reset the view
        
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glPushMatrix();
        theta += 0.5f;
        if(theta == 360.0f) theta = 0.0f;
        gl.glTranslatef(0.0f, 0.0f, -4.5f);
        gl.glRotatef(theta, 0.0f, 1.0f, 0.0f);
        gl.glBegin(GL2.GL_TRIANGLES);
            //Triangle 1
            gl.glColor3f(1.0f,0.0f,0.0f); gl.glVertex3f( 0.0f, 1.0f, 0.0f);   //V0(red)
            gl.glColor3f(0.0f,1.0f,0.0f); gl.glVertex3f(-1.0f,-1.0f, 1.0f);   //V1(green)
            gl.glColor3f(0.0f,0.0f,1.0f); gl.glVertex3f( 1.0f,-1.0f, 1.0f);   //V2(blue)
            //Triangle 2
            gl.glColor3f(1.0f,0.0f,0.0f); gl.glVertex3f( 0.0f, 1.0f, 0.0f);   //V0(red)
            gl.glColor3f(0.0f,0.0f,1.0f); gl.glVertex3f( 1.0f,-1.0f, 1.0f);   //V2(blue)
            gl.glColor3f(0.0f,1.0f,0.0f); gl.glVertex3f( 1.0f,-1.0f,-1.0f);   //V3(green)
            //Triangle 3
            gl.glColor3f(1.0f,0.0f,0.0f); gl.glVertex3f( 0.0f, 1.0f, 0.0f);   //V0(red)
            gl.glColor3f(0.0f,1.0f,0.0f); gl.glVertex3f( 1.0f,-1.0f,-1.0f);   //V3(green)
            gl.glColor3f(0.0f,0.0f,1.0f); gl.glVertex3f(-1.0f,-1.0f,-1.0f);   //V4(blue)
            //Triangle 4
            gl.glColor3f(1.0f,0.0f,0.0f); gl.glVertex3f( 0.0f, 1.0f, 0.0f);   //V0(red)
            gl.glColor3f(0.0f,0.0f,1.0f); gl.glVertex3f(-1.0f,-1.0f,-1.0f);   //V4(blue)
            gl.glColor3f(0.0f,1.0f,0.0f); gl.glVertex3f(-1.0f,-1.0f, 1.0f);   //V1(green)
        gl.glEnd();
        gl.glBegin(GL2.GL_QUADS);
            gl.glVertex3f(-1.0f,-1.0f, 1.0f);   //V2
            gl.glVertex3f( 1.0f,-1.0f, 1.0f);   //V1
            gl.glVertex3f( 1.0f,-1.0f,-1.0f);   //V3
            gl.glVertex3f(-1.0f,-1.0f,-1.0f);   //V4
        gl.glEnd();
        gl.glPopMatrix();
    }


    /** Called when the display mode has been changed. <B>!! CURRENTLY UNIMPLEMENTED IN JOGL !!</B>
     * @param gLDrawable      The GLAutoDrawable object.
     * @param modeChanged     Indicates if the video mode has changed.
     * @param deviceChanged   Indicates if the video device has changed.
     */
    public void displayChanged(GLAutoDrawable gLDrawable, boolean modeChanged, boolean deviceChanged) { }
        
    /** Called by the drawable during the first repaint after the component has
     * been resized. The client can update the viewport and view volume of the
     * window appropriately, for example by a call to
     * GL.glViewport(int, int, int, int); note that for convenience the component
     * has already called GL.glViewport(int, int, int, int)(x, y, width, height)
     * when this method is called, so the client may not have to do anything in
     * this method.
     * @param gLDrawable The GLAutoDrawable object.
     * @param x         The X coordinate of the viewport rectangle.
     * @param y         The Y coordinate of the viewport rectangle.
     * @param width     The new width of the window.
     * @param height    The new height of the window.
     */
    public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height) {
        final GL2 gl = gLDrawable.getGL().getGL2();
        //perspective
        if (height > 0) {
            final float h = (float)width / (float)height;
            gl.glMatrixMode(GL2.GL_PROJECTION);
            gl.glLoadIdentity();
            glu.gluPerspective(45.0f, h, 1.0, 20.0);
            //field of vision is 45 degrees, depth clipping between 1 and 20
            gl.glMatrixMode(GL2.GL_MODELVIEW);
            gl.glLoadIdentity();
        }
    }


    @Override
    public void dispose(GLAutoDrawable arg0) { ; }
    
    
    public static void main(String [] args) {
        final Frame frame = new Frame("OpenGL in Java");
        canvas.addGLEventListener( new Scene() );
        frame.add(canvas);
        frame.setSize(640, 480);
        final FPSAnimator animator = new FPSAnimator(canvas, 60, true);
        //our main loop which calls display() in a loop is done by the Animator
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if(animator.isStarted()) animator.stop();
                System.exit(0);
            }
            });
        frame.setVisible(true);
        animator.start();
        canvas.requestFocus();
    }
}