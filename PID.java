/**
 * General purpose PID loop.
 * @author Gowan Rowland
 * @version 9/8/2016
 */
public class PID {
	private double Kp;
	private double Ki;
	private double Kd;
	private double prevError;
	private double setpoint;
	private double integral;
	private double error;
	private double derivative;
	private signal functions;
	private double output;
	private double m_setpoint;
	public PID ( signal functions ){
		this.functions = functions;
		integral = 0;
		setpoint = 0;
		m_setpoint = functions.getValue();
	}
	public void set_setpoint( double s ){
		setpoint = s;
	}
	public void set_pid( double p, double i, double d ) {
		this.Kp = p;
		this.Ki = i;
		this.Kd = d;
	}
	/*
	 * Experimental linear interpolation for setpoint.
	 */
	static double lerp( double a, double b, double t ){
		return a + (b - a) * t;
	}
	/*
	 * Experimental update function for filtering pv, co, and ramping sp.
	 * 	TODO:
	 * 		second derivative
	 * 		co filter
	 * 		pv filter
	 * 		sp ramp
	 */
	public void xupdate( double dt ){
		m_setpoint = lerp( setpoint, m_setpoint, dt);
		error =  m_setpoint - functions.getValue();
		integral += ( Ki * error * dt );
		derivative = ( prevError - error ) / dt;
		prevError = error;
		output = ( Kp * error ) + ( integral ) + ( Kd * derivative );
	}
	public void update( double dt ){
		error = setpoint - functions.getValue();
		integral += ( Ki * error * dt );
		derivative = ( prevError - error ) / dt;
		prevError = error;
		output = ( Kp * error ) + ( integral ) + ( Kd * derivative );
	}
	public double get() {
		return output;
	}
}
