import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.Timer;

public class LifeModel implements ActionListener
{

	/*
	 *  This is the Model component.
	 */

   private static int SIZE = 60;
   private LifeCell[][] grid;   	
   private LifeView myView;
   private Timer timer;

	/** Construct a new model using a particular file */
   public LifeModel(LifeView view, String fileName) 
   {       
      int r, c;
      grid = new LifeCell[SIZE][SIZE];
      for ( r = 0; r < SIZE; r++ )
         for ( c = 0; c < SIZE; c++ )
            grid[r][c] = new LifeCell();
   
      if ( fileName == null ) //use random population
      {                                           
         for ( r = 0; r < SIZE; r++ )
         {
            for ( c = 0; c < SIZE; c++ )
            {
               if ( Math.random() > 0.85) //15% chance of a cell starting alive
                  grid[r][c].setAliveNow(true);
            }
         }
      }
      else
      {                 
         Scanner input = null;
         try {      
            input = new Scanner(new File(fileName));
         } catch (IOException e) { System.out.println("bad filename"); }
         int numInitialCells = input.nextInt();
         for (int count=0; count<numInitialCells; count++)
         {
            r = input.nextInt();
            c = input.nextInt();
            grid[r][c].setAliveNow(true);
         }
         input.close();
      }
   
      myView = view;
      myView.updateView(grid);
   
   }

	/** Constructor a randomized model */
   public LifeModel(LifeView view) throws IOException
   {
      this(view, null);
   }

	/** pause the simulation (the pause button in the GUI */
   public void pause()
   {
      timer.stop();
   }
	
	/** resume the simulation (the pause button in the GUI */
   public void resume()
   {
      timer.restart();
   }
	
	/** run the simulation (the pause button in the GUI */
   public void run()
   {
      timer = new Timer(50, this);
      timer.setCoalesce(true);
      timer.start();
   }

	/** called each time timer fires */
   public void actionPerformed(ActionEvent e)
   {
      oneGeneration();
      myView.updateView(grid);
   }

	/** main logic method for updating the state of the grid / simulation */
   private void oneGeneration()
   {
   	/* 
   	 * student code here 
   	 */
       
       for(int r = 0; r < SIZE; r++)
         {
            for(int c = 0; c < SIZE; c++)
               {
                  if(grid[r][c].isAliveNow())
                  {
                        if(numberOfAlive(r,c) < 2 || numberOfAlive(r,c) > 3)
                           {
                              grid[r][c].setAliveNext(false);
                           }
                        else
                           {
                              grid[r][c].setAliveNext(true);
                           }
                   }
                  else if (grid[r][c].isAliveNow() == false)
                     {
                        if(numberOfAlive(r,c) == 3)
                           {
                              grid[r][c].setAliveNext(true);
                           }
                        else
                           {
                              grid[r][c].setAliveNext(false);
                           }
                     }
               }
         } 
         
       for(int r = 0; r < SIZE; r++)
         {
            for(int c = 0; c < SIZE; c++)
               {
                 if(grid[r][c].isAliveNext())
                     {
                        grid[r][c].setAliveNow(true);
                     }
                 else
                     {
                       grid[r][c].setAliveNow(false);
                     }
               }
         }
   }
   
   public int numberOfAlive(int r, int c)
      {
         int numberOfAlive = 0;
         
         //top left  
         if (r == 0 && c == 0)
            {
               if(grid[r][c+1].isAliveNow())
                  {
                    numberOfAlive++;
                  }
                  
              if(grid[r+1][c+1].isAliveNow())
                  {
                    numberOfAlive++;
                  }
              
              if(grid[r+1][c].isAliveNow())
                  {
                    numberOfAlive++;
                  }
            }
         //top right     
         else if(r == 0 && c == SIZE - 1)
            {
               if(grid[r][c-1].isAliveNow())
                  {
                    numberOfAlive++;
                  }
               
               if(grid[r+1][c-1].isAliveNow())
                  {
                    numberOfAlive++;
                  }
                  
               if(grid[r+1][c].isAliveNow())
                  {
                   numberOfAlive++;
                  }   
            }
         
         //bottom left
         else if(r == SIZE - 1 && c == 0)
            {
               if(grid[r-1][c].isAliveNow())
                  {
                     numberOfAlive++;
                  }
                  
               if(grid[r-1][c+1].isAliveNow())
                  {
                     numberOfAlive++;
                  }
                  
               if(grid[r][c+1].isAliveNow())
                  {
                     numberOfAlive++;
                  }
            }
            
         //bottom right    
         else if(r == SIZE - 1 && c == SIZE - 1)
            {
               if(grid[r-1][c-1].isAliveNow())
                  {
                    numberOfAlive++;    
                  }
               
               if(grid[r-1][c].isAliveNow())
                  {
                    numberOfAlive++;
                  }
                  
               if(grid[r][c-1].isAliveNow())
                  {
                     numberOfAlive++;
                  }
            }
            
         //top 
         else if(r - 1 < 0)
            {
               if(grid[r][c-1].isAliveNow())
                  {
                     numberOfAlive++;
                  }
               
               if(grid[r+1][c].isAliveNow())
                  {
                     numberOfAlive++;
                  }
                  
               if(grid[r][c+1].isAliveNow())
                  {
                     numberOfAlive++;
                  }   
            }
         //left   
         else if(c - 1 < 0)
            {
                if(grid[r-1][c].isAliveNow())
                  {
                     numberOfAlive++;
                  }

               if(grid[r][c+1].isAliveNow())
                  {
                     numberOfAlive++;
                  }
                  
               if(grid[r+1][c].isAliveNow())
                  {
                     numberOfAlive++;
                  }
            }
            
         //right
         else if(c + 1 >= SIZE)
            {
               if(grid[r-1][c].isAliveNow())
                  {
                     numberOfAlive++;
                  }
                  
               if(grid[r][c-1].isAliveNow())
                  {
                     numberOfAlive++;
                  }
                  
               if(grid[r+1][c].isAliveNow())
                  {
                     numberOfAlive++;
                  }  
            }
            
         //bottom
         else if(r + 1 >= SIZE)
            {
               if(grid[r][c-1].isAliveNow())
                  {
                     numberOfAlive++;
                  }
               
               if(grid[r-1][c].isAliveNow())
                  {
                    numberOfAlive++;
                  }
                  
               if(grid[r][c+1].isAliveNow())
                  {
                     numberOfAlive++;
                  }
            }   
            
         //normal
         else 
         {
         if(grid[r+1][c+1].isAliveNow())
            {
               numberOfAlive++;
            }
            
         if(grid[r+1][c].isAliveNow())
         {
            numberOfAlive++;
         }
         
         if(grid[r][c+1].isAliveNow())
         {
            numberOfAlive++;
         }

         if(grid[r-1][c-1].isAliveNow())
         {
            numberOfAlive++;    
         }
         
         if(grid[r-1][c].isAliveNow())
         {
            numberOfAlive++;
         }
         
         if(grid[r][c-1].isAliveNow())
         {
             numberOfAlive++;
         }

         if(grid[r+1][c-1].isAliveNow())
         {
            numberOfAlive++;
         }
         
         if(grid[r-1][c+1].isAliveNow())
         {
            numberOfAlive++;
         }
         
         }
         
         return numberOfAlive;
      }
}

