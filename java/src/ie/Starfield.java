package ie;

//import processing.core.PApplet;
//import ie.Visual;
//import ie.VisualException;

public class Starfield extends Visual
{
    Star [] stars = new Star[5000];

    int which = 0;

    float z = width;

    double ir = 0.1;

    float speed;

    public void settings()
    {
        size(900, 900, P3D);
        
        for(int i = 0; i < stars.length; i++)
        {
            stars[i] = new Star(this);
        }
    }

    public void setup()
    {
        startMinim();
        loadAudio("after dawn.mp3");
        getAudioPlayer().play();
    }

    public void keyPressed()
    {
        if (keyCode >= '0' && keyCode <= '6') 
        {
            which = keyCode - '0';
        }
        if(key == ' ')
        {
            if (getAudioPlayer().isPlaying())
            {
                getAudioPlayer().pause();
            }
            else
            {
                getAudioPlayer().play();
            }
        }
        if(keyCode == ENTER)
        {
            getAudioPlayer().rewind();

            getAudioPlayer().play();
        }
 
    }
    
    public void draw()
    {
        calculateAverageAmplitude();
        try
        {
            calculateFFT();
        }
        catch(VisualException e)
        {
            e.printStackTrace();
        }
        calculateFrequencyBands();
        //noCursor();
        //camera(mouseX*-1, mouseY*-1, 0, 0, 0, 0, 0, 1, 0);

        //translate(map(mouseX, 0, width, 0.5 + ir, 0.5 - ir) * width, map(mouseY, 0, height, 0.5 + ir, 0.5 - ir) * height);

        translate(mouseX,mouseY);

        float[] bands = getSmoothedBands();
        
        for(int i = 0 ; i < bands.length ; i ++)
        {
            float h = bands[i];
            speed = map(h/10, 0, width, 0, 10);
        }
        
        background(0);
        //translate(width / 2, height /2, z/10);
        //camera(mouseX, mouseY, 900, 0, 0, 0, 0, 1, 0);
        
        for(int i = 0; i < stars.length; i++)
        {
            switch(which)
            {
                case 0:
                {
                    stars[i].update();
                    stars[i].show();
                    break;
                }
                case 1:
                {
                    stars[i].update();
                    stars[i].show2();
                    break;
                }

            }

        }
    }
}