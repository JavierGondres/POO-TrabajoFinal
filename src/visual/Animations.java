package visual;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;

import javax.swing.Timer;
import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public abstract class Animations {
	//pan con cachu
	public static void gotoXY(Component c, int x, int y) {
        
        int changeX;
        int changeY;
        
        if(c.getX() < x) {
        	System.out.println("Moving Right");
        	changeX = 1;
        }
        else {
        	System.out.println("Moving Left");
        	changeX = -1;
        }
        if(c.getY() < y) {
        	System.out.println("Moving Down");
        	changeY = 1;
        }
        else {
        	System.out.println("Moving Up");
        	changeY = -1;
        }
		
	    Timer timer = new Timer(1, new ActionListener() {   
	    	
	    	int ind = 0;
	    	int indX = c.getX();
	    	int indY = c.getY();
	        
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	
                if (indX != x) {
                	if(((indX + changeX * ind) <= x && changeX > 0) || ((indX + changeX * ind) >= x && changeX < 0)) indX += (changeX * ind);
                	else indX = x;
                } 
                if (indY != y) {
                	if(((indY + changeY * ind) <= y && changeY > 0) || ((indY + changeY * ind) >= y && changeY < 0)) indY += (changeY * ind);
                	else indY = y;
                }
                
	            c.setLocation(new Point(indX, indY));
	            
	            if (indX == x && indY == y){
	                ((Timer) e.getSource()).stop();
	            }
	            
	            ind++;
	        }
	    });
	    
	    timer.start();

	}
	
	public static void moveCARDINAL(Component c, int CONSTANT) {

		boolean xMov;
		boolean yMov;
		int changeX;
		int changeY;
		int goalX;
		int goalY;
        
        switch(CONSTANT) {
        	default:
        		System.out.println("BAD ARGUMENT: CONSTANT");
        		changeX = changeY = 0;
        		xMov = yMov = false;
        		goalX = c.getX();
        		goalY = c.getY();
        		break;
        
        	case SwingConstants.WEST:
        		System.out.println("Moving West");
	        	changeX = -1;
	        	changeY = 0;
	        	xMov = true;
	        	yMov = false;
	        	goalX = 0;
	        	goalY = c.getY();
	        	break;
	        	
        	case SwingConstants.EAST:
        		System.out.println("Moving East");
	        	changeX = 1;
	        	changeY = 0;
	        	xMov = true;
	        	yMov = false;
	        	goalX = c.getParent().getWidth() - c.getWidth();
	        	goalY = c.getY();
	        	break;
	        	
        	case SwingConstants.NORTH:
        		System.out.println("Moving North");
	        	changeY = -1;
	        	changeX = 0;
	        	xMov = false;
	        	yMov = true;
	        	goalX = c.getX();
	        	goalY = 0;
	        	break;
	        	
	        case SwingConstants.SOUTH:
	        	System.out.println("Moving South");
	        	changeY = 1;
	        	changeX = 0;
	        	xMov = false;
	        	yMov = true;
	        	goalX = c.getX();
	        	goalY = c.getParent().getHeight() - c.getHeight();
	        	break;
	        	
	        case SwingConstants.NORTH_WEST:
	        	System.out.println("Moving North West");
	        	changeX = changeY = -1;
	        	xMov = true;
	        	yMov = true;
	        	goalX = 0;
	        	goalY = 0;
	        	break;
	        	
	        case SwingConstants.NORTH_EAST:
	        	System.out.println("Moving North East");
	        	changeX = 1;
	        	changeY = -1;
	        	xMov = true;
	        	yMov = true;
	        	goalX = c.getParent().getWidth() - c.getWidth();
	        	goalY = 0;
	        	break;
	        	
	        case SwingConstants.SOUTH_WEST:
	        	System.out.println("Moving South West");
	        	changeX = -1;
	        	changeY = 1;
	        	xMov = true;
	        	yMov = true;
	        	goalX = 0;
	        	goalY = c.getParent().getHeight() - c.getHeight();
	        	break;
	        	
        	case SwingConstants.SOUTH_EAST:
        		System.out.println("Moving South East");
	        	changeX = 1;
	        	changeY = 1;
	        	xMov = true;
	        	yMov = true;
	        	goalX = c.getParent().getWidth() - c.getWidth();
	        	goalY = c.getParent().getHeight() - c.getHeight();
	        	break;
	        	
	        }
		
	    Timer timer = new Timer(1, new ActionListener() {   
	    	
	    	int ind = 0;
	    	int indX = c.getX();
	    	int indY = c.getY();
	        
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	
                if (xMov) {
                	if(((indX + changeX * ind) >= goalX && changeX < 0) || ((indX + changeX * ind) <= goalX && changeX > 0)) indX += changeX * ind;
                	else indX = goalX;
                } 
                if (yMov) {
                	if(((indY + changeY * ind) >= goalY && changeY < 0) || ((indY + changeY * ind) <= goalY && changeY > 0)) indY += changeY * ind;
                	else indY = goalY;
                }
                
	            c.setLocation(new Point(indX, indY));
	            
	            if (indX == goalX && indY == goalY){
	                ((Timer) e.getSource()).stop();
	            }
	            
	            ind++;
	            ind += (int)Math.pow(ind/100, 1.2);
	        }
	    });
	    
	    timer.start();

	}
	
	public static void fadeInOutLabel(JLabel label, boolean fadeIn) {
		
        Timer timer = new Timer(100, new ActionListener() {
            float alpha = label.getForeground().getAlpha()/255;

            @Override
            public void actionPerformed(ActionEvent e) {

                if (fadeIn) {
                    if(alpha + 0.2 < 1) alpha += 0.2F;
                    else alpha = 1;
                } 
                else {
                	if(alpha - 0.2 > 0) alpha -= 0.2F;
                	else alpha = 0;
                }
                
                label.setForeground(new Color(label.getForeground().getRed()/255F, label.getForeground().getGreen()/255F, label.getForeground().getBlue()/255F, alpha));
                
                if (alpha == 0 || alpha == 1) {
                	((Timer)e.getSource()).stop();
                    return;
                }

            }
        });
        timer.start();
        
	}
	
	public static void fadeIntoColorLabel(JLabel label, Color C) {
		
        Color startColor = label.getForeground();
        Color endColor = C;

        Timer timer = new Timer(1, new ActionListener() {
            float alpha = 0.0F;
            @Override
            public void actionPerformed(ActionEvent e) {
                alpha += 0.05F;
                if (alpha > 1.0) {
                    alpha = 1.0F;
                    ((Timer)e.getSource()).stop();
                }
                int red = (int) (startColor.getRed() + alpha * (endColor.getRed() - startColor.getRed()));
                int green = (int) (startColor.getGreen() + alpha * (endColor.getGreen() - startColor.getGreen()));
                int blue = (int) (startColor.getBlue() + alpha * (endColor.getBlue() - startColor.getBlue()));

                Color newColor = new Color(red, green, blue);
                label.setForeground(newColor);
            }
        });

        timer.start();
		
	}
	
	public static void fadeIntoColor(Component comp, Color C) {
		
        Color startColor = comp.getBackground();
        Color endColor = C;

        Timer timer = new Timer(1, new ActionListener() {
            float alpha = 0.0F;
            @Override
            public void actionPerformed(ActionEvent e) {
                alpha += 0.05F;
                if (alpha > 1.0) {
                    alpha = 1.0F;
                    ((Timer)e.getSource()).stop();
                }
                int red = (int) (startColor.getRed() + alpha * (endColor.getRed() - startColor.getRed()));
                int green = (int) (startColor.getGreen() + alpha * (endColor.getGreen() - startColor.getGreen()));
                int blue = (int) (startColor.getBlue() + alpha * (endColor.getBlue() - startColor.getBlue()));

                Color newColor = new Color(red, green, blue);
                comp.setBackground(newColor);
            }
        });

        timer.start();
		
	}
	
}