import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.ArrayList;

public class SizedStackTest {
  @Test
  public void testSum() {
    SizedStack stack = new SizedStack( 6 );
    stack.push( 1.0 );
    stack.push( 2.0 );
    stack.push( 3.0 );
    stack.push( 4.0 );
    stack.push( 5.0 );
    stack.push( 6.0 );

    assertEquals( 21.0, stack.sum(), 0.0 );
  }
  @Test
  public void testMean() {
    SizedStack stack = new SizedStack( 6 );
    stack.push( 1.0 );
    stack.push( 2.0 );
    stack.push( 3.0 );
    stack.push( 4.0 );
    stack.push( 5.0 );
    stack.push( 6.0 );

    assertEquals( 3.5, stack.mean(), 0.0 );
  }
  @Test
  public void testOverPushing() {
    SizedStack stack = new SizedStack( 6 );
    stack.push( 1.0 );
    stack.push( 2.0 );
    stack.push( 3.0 );
    stack.push( 4.0 );
    stack.push( 5.0 );
    stack.push( 6.0 );
    stack.push( 7.0 );

    ArrayList a = new ArrayList();
    a.add( 7.0 );
    a.add( 6.0 );
    a.add( 5.0 );
    a.add( 4.0 );
    a.add( 3.0 );
    a.add( 2.0 );


    assertEquals( a, stack.get_data() );
  }
  @Test
  public void testResizeTrim() {
    SizedStack stack = new SizedStack( 6 );
    stack.push( 1.0 );
    stack.push( 2.0 );
    stack.push( 3.0 );
    stack.push( 4.0 );
    stack.push( 5.0 );
    stack.push( 6.0 );
    stack.push( 7.0 );

    ArrayList a = new ArrayList();
    a.add( 7.0 );
    a.add( 6.0 );
    a.add( 5.0 );

    stack.resize( 3 );

    assertEquals( a, stack.get_data() );
  }
  @Test
  public void testStdev() {
    SizedStack stack = new SizedStack( 6 );
    stack.push( 1.0 );
    stack.push( 2.0 );
    stack.push( 3.0 );
    stack.push( 4.0 );
    stack.push( 5.0 );
    stack.push( 6.0 );

    assertEquals( 1.87083, stack.stdev(), 0.00001 );
  }
  @Test public void toStringTest() {
    SizedStack stack = new SizedStack( 6 );
    stack.push( 1.0 );
    stack.push( 2.0 );
    stack.push( 3.0 );

    assertEquals( "[ 3.0 ,2.0 ,1.0 ]\n", stack.toString() );
  }
}
