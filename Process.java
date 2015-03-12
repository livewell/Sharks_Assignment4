/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Owner
 */
public class Process implements Comparable<Process>
{
   int id;
   int size;
   int duration;
   int position;
   boolean started;
   boolean hole;



/**
    * @param id
    * @param size
    * @param duration
    * @param position
    * @param started
    */
   public Process(int id, int size, int duration, int position, boolean started, boolean hole)
   {
      this.id = id;
      this.size = size;
      this.duration = duration;
      this.position = position;
      this.started = started;
      this.hole = hole;
   }

   public Process()
   {
      this.id = 0;
      this.size = 100;
      this.duration = 0;
      this.position = 0;
      this.started = false;
      this.hole = true;
   }

   /**
    * @return the id
    */
   public int getId()
   {
      return id;
   }

   /**
    * @param id
    * g the id to set
    */
   public void setId(int id)
   {
      this.id = id;
   }

   /**
    * @return the size
    */
   public int getSize()
   {
      return size;
   }

   /**
    * @param size
    *           the size to set
    */
   public void setSize(int size)
   {
      this.size = size;
   }

   /**
    * @return the duration
    */
   public int getDuration()
   {
      return duration;
   }

   /**
    * @param duration
    *           the duration to set
    */
   public void setDuration(int duration)
   {
      this.duration = duration;
   }

   /**
    * @return the position
    */
   public int getPosition()
   {
      return position;
   }

   /**
    * @param position
    *           the position to set
    */
   public void setPosition(int position)
   {
      this.position = position;
   }

   /**
    * @return the started
    */
   public boolean isStarted()
   {
      return started;
   }

   /**
    * @param started
    *           the started to set
    */
   public void setStarted(boolean started)
   {
      this.started = started;
   }
   
   public boolean isHole() 
   {
	   return hole;
   }

   public void setHole(boolean hole) 
   {
	   this.hole = hole;
   }

   @Override
   public int compareTo(Process o)
   {
      float comparedFrom = o.getSize();

      if (this.getSize() > o.getSize())
      {
         return 1;
      }
      else if (this.getSize() == o.getSize())
      {
         return 0;
      }
      else
      {
         return -1;
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return "Process [id=" + id + ", size=" + size + "MB, duration=" + duration + "sec, position=" + position + ", started="
            + started + "]";
   }

}