import scala.util.Random

/**
 * Created by alkozko on 13.08.2014.
 */
class Field(width:Int, height:Int, initialCount: Int) {

  var state = Array.ofDim[Boolean](width, height);
  for (i <- 0 until initialCount)
  {
    val position = Random.nextInt(width*height);
    val cellX = position / width;
    val cellY = position % height;

    state(cellX)(cellY) = true;
  }


  def toNextState: Unit = {
    val nextState = Array.ofDim[Boolean](width,height);
    for (x <- 0 until width; y <- 0 until height)
    {
      val alive = state(x)(y);
      val neighborsCount = getNeighborsCount(x, y);
      if (alive == false && neighborsCount == 3)
        nextState(x)(y) = true;
      else if (alive && (neighborsCount > 3 || neighborsCount < 2))
        nextState(x)(y) = false;
      else if (alive)
        nextState(x)(y) = true;
    }
    state = nextState;
  }

  def getNeighborsCount(x:Int,y:Int) = {
    var count = 0;

    for (i <- Array(1,0,-1); j <- Array(1,0,-1))
    {
      val newX = if (x + i >= width) 0 else if (x + i < 0) width - 1 else x + i;
      val newY = if (y + j >= height) 0 else if (y + j < 0) height - 1 else y + j;
      if (state(newX)(newY) && (newX != x || newY != y))
        count += 1;
    }

    count;
  }


}