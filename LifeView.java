import java.awt.*;
import javax.swing.*;

/** The view (graphical) component */
public class LifeView extends JPanel
{
	private static final long serialVersionUID = 1L;
	private static int SIZE = 60;

	/** store a reference to the current state of the grid */
    private LifeCell[][] grid;
    private boolean notColored;

    public LifeView()
    {
        grid = new LifeCell[SIZE][SIZE];
        notColored = true;
    }

    /** entry point from the model, requests grid be redisplayed */
    public void updateView( LifeCell[][] mg )
    {
        grid = mg;
        repaint();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int testWidth = getWidth() / (SIZE+1);
        int testHeight = getHeight() / (SIZE+1);
        // keep each life cell square
        int boxSize = Math.min(testHeight, testWidth);

        for ( int r = 0; r < SIZE; r++ )
        {
            for (int c = 0; c < SIZE; c++ )
            {
                if (grid[r][c] != null)
                {
                    if ( grid[r][c].isAliveNow() && notColored == true)
                        g.setColor( Color.BLUE );
                    else if (grid[r][c].isAliveNow() && notColored == false)
                        {
                          int r1 = (int)(Math.random()*(255-0))+0;
                          int r2 = (int)(Math.random()*(255-0))+0;     
                          int r3 = (int)(Math.random()*(255-0))+0;
                          g.setColor( new Color(r1,r2,r3) );     
     
                        }
                    else
                        g.setColor( new Color(235,235,255) );

                    g.fillRect( (c+1)*boxSize, (r+1)* boxSize, boxSize-2, boxSize-2);
                }
            }
        }
    }
    
    public void changeColor()
      {
         notColored = (!notColored); 
      }
}
