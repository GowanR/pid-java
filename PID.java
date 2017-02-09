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

	/* Prevent Integral windup with a SizedStack */
	private SizedStack integral_stack;
	/* Make derivative filter with SizedStack */
	private SizedStack derivative_stack;

	private boolean sp_ramp_enabled;
	PID sp_ramp_pid;

	public PID ( signal functions ){
		sp_ramp_enabled = false;
		this.functions = functions;
		integral = 0;
		setpoint = 0;
		integral_stack = new SizedStack( 10 );
		derivative_stack = new SizedStack( 3 );
	}
	public void set_integral_range( int n ) {
		integral_stack.resize( n );
	}
	public void set_derivative_range( int n ) {
		derivative_stack.resize( n );
	}
	public void set_setpoint( double s ){
		if ( sp_ramp_enabled ) {
			sp_ramp_pid.set_setpoint( s );
		}
		setpoint = s;
	}
	public void set_pid( double p, double i, double d ) {
		this.Kp = p;
		this.Ki = i;
		this.Kd = d;
	}
	public void set_sp_ramp( PID sp_pid ) {
		sp_ramp_enabled = true;
		sp_ramp_pid = sp_pid;
	}
	public void update( double dt ){
		if ( sp_ramp_enabled ) {
			sp_ramp_pid.update( dt );
			setpoint = sp_ramp_pid.get();
		}
		error = setpoint - functions.getValue();
		integral_stack.push( ( Ki * error * dt ) );

		derivative = ( error - prevError ) / dt;
		derivative_stack.push( derivative );
		prevError = error;
		output = ( Kp * error ) + ( integral_stack.sum() ) + ( Kd * derivative_stack.mean() ); // Note: Should we use mean derivative filter?
	}
	public double get() {
		return output;
	}
}
