package fi.game.Zoambie;

import android.graphics.*;

public class Saw {
        
        private Bitmap bitmap;      // Hahmon kuva
        private float x;            // X-sijainti
        private float y;            // Y-sijainti
        //private Rect sourceRect;  // Animaatioissa tullaan tarvitsemaan
        //private Rect destRect;    // Animaatioissa tullaan tarvitsemaan
        private double direction;
        private int width;
        private int height;
        private Point killPoint;
        private double targetDirection;
        
        
		// Konstruktori
        public Saw(Bitmap saw, float x, float y){
                this.bitmap = saw;
                this.x = x;
                this.y = y;
                this.width = saw.getWidth();
                this.height = saw.getHeight();
                this.killPoint = new Point(0, 0);
        }     
        

        
        public int getWidth() {
			return width;
		}


		public void setWidth(int width) {
			this.width = width;
		}


		public int getHeight() {
			return height;
		}


		public void setHeight(int height) {
			this.height = height;
		}          
        
        public Bitmap getBitmap() {
                return this.bitmap;
        }

        public void setBitmap(Bitmap bitmap) {
                this.bitmap = bitmap;
        }

        public float getX() {
                return x;
        }

        public void setX(float x) {
                this.x = x;
        }

        public float getY() {
                return y;
        }

        
        public void setKillPoint(float x, float y) {
        	
        	double xx = (double)x;
        	double yy = (double)y;
        	//int x = ((int) (getX() + (Math.cos(asd ) *20 ) ) );
			//int y = ((int) (getY() + (Math.sin(asd ) *20) ) );
        	
        	xx = xx + (Math.cos(Math.toRadians(getDirection())) * -40);
        	yy = yy + (Math.sin(Math.toRadians(getDirection())) * -40);
        	
        	this.killPoint.set((int)xx, (int)yy);
			
        }
        public Point getKillPoint() {
        	return this.killPoint;
        }

	public void setY(float y) {
		this.y = y;
	}
	
	public void setDirection(double direction) {
		this.direction = direction;
	}
	
	public double getDirection() {
		return this.direction;
	}
	
	public void updateTargetDirection(float x1, float y1, float x2, float y2) {
		float targetDirection = 0;
		targetDirection = (float) Math.toDegrees( Math.atan2(y2-y1, x2-x1) );
		//setKillPoint(Math.atan2(-y2-y1, x2-x1)  + (float)Math.PI );
		this.targetDirection = targetDirection;
		
	}
	
	public void updateDirection() {
		
		if (this.targetDirection < -90 && this.direction > 90) {
			if (this.direction + 8 > 90)
				setDirection(-180);
			else
				setDirection(direction + 8);
		}
		else if (this.targetDirection > 90 && this.direction < -90) {
			if (this.direction - 8 < -180)
				setDirection(180);
			else
				setDirection(direction - 8);
		}
		else {
			double temp = this.direction - this.targetDirection;
			if (temp < 25 || temp > -25) {
				if (temp < 0)
					setDirection(direction + 8);
				else
					setDirection(direction - 8);
			}
		}
	
	}
	
	public void draw(Canvas canvas, Character player) {
		Matrix m = new Matrix();
    	Bitmap bmp = Bitmap.createBitmap(getBitmap(), 0, 0, getWidth(), getHeight());
    	m.reset();
    	m.setRotate((float)getDirection(), 50f, 20f);
    	m.postTranslate(player.getX()-30, player.getY()+5);
    	canvas.drawBitmap(bmp, m, null);
    	
    	setX(player.getX()-30);
    	setY(player.getY()+5);
	}
}