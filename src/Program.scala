import com.sun.jmx.snmp.Timestamp

/**
 * Created by alkozko on 13.08.2014.
 */
object Program {
  def main(args: Array[String]): Unit = {
    val field = new Field(10,10,15);
    while (true)
    {
      printField(field);
      //      io.StdIn.readLine();
      field.toNextState;
      Thread.sleep(500);
    }
  }

  def printField(field: Field) {
    for (row <- field.state) {
      println(row.map(x => if (x) "■ " else "□ ").mkString(""));
    }
    println("==" * 20)
  }
}