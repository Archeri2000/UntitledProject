package main.com.entities;

/** This class holds the values of cinema type
 * @author SS1 Group 6
 * @version 13
 */
public enum CinemaType {
	Gold(1.3f),Platinum(1.0f);
	
    /** Multiplier to store the multiplier value for cinema
     */
	private final float multiplier;
	
    /** Get multiplier
     * @return multiplier value
     */
	public float getMultiplier() {
		return multiplier;
	}
	
    /** Assign multiplier
     */
	CinemaType(float f){
		this.multiplier = f;
	}
		
}