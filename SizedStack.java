/*
 * Used for storing the last n values. Its like a value buffer with nice functions built in.
 * Written by GowanR
 */
import java.util.ArrayList;
import java.util.Collections;

class SizedStack {
  ArrayList<Double> data;
  int size;
  public SizedStack( int size ) {
    this.size = size;
    this.data = new ArrayList<Double>( size );

  }
  public double sum() {
    this.trim();
    double sum = 0;
    for ( int i = 0; i < data.size(); i ++ ) {
      sum += data.get(i);
    }
    return sum;
  }
  public double mean() {
    return this.sum()/data.size();
  }
  public double left_pop() {
    return data.remove( data.size()-1 );
  }
  private void trim() {
    while ( data.size() > this.size ) {
      this.left_pop();
    }
  }
  public void push( double a ) {
    data.add( 0, a );
    this.trim();
  }
  public void resize( int n ) {
    this.size = n;
  }
  public double stdev() {
    this.trim();
    double mean = this.mean();
    double sum = 0;
    for ( int i = 0; i < data.size(); i ++ ) {
      sum += Math.pow( data.get(i) - mean, 2 );
    }
    return Math.sqrt( sum/(data.size()-1) );
  }
  private void array_print( ArrayList<Double> array ) {
    System.out.print("[ ");
    for ( int i = 0; i < array.size() - 1; i ++ ) {
      System.out.print( array.get( i ) + " ,");
    }
    System.out.println( array.get( array.size()-1 ) + " ]\n");
  }
  public void debug_print() {
    this.trim();
    array_print( data );
  }
  // Work in progress Quartile Math
  public void IQR () {
    int range = Math.round(this.size / 4);
    System.out.println( "Range: " + range );
    ArrayList<Double> sorted = (ArrayList<Double>) data.clone();
    Collections.sort( sorted );
    System.out.println("Sorted");
    array_print( sorted );
    System.out.println("Data");
    array_print( data );
    double Q1 = sorted.get( range * 1 );
    double Q2 = sorted.get( range * 2 );
    double Q3 = sorted.get( range * 3 );
    double Q4 = sorted.get( range * 4 );
    System.out.println("Q1 " + Q1 );
    System.out.println("Q2 " + Q2 );
    System.out.println("Q3 " + Q3 );
    System.out.println("Q4 " + Q4 );
    System.out.println("IQR: " + (Q3 - Q1) );
  }
}
