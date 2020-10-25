
public class GasTank extends CarPart {

private float milesPerLitre;
	
	/* CONSTRUCTOR */
	
	public GasTank(float capacityLitre, float milesPerLitre) {
		super("gas tank", " Litre", capacityLitre);
		this.milesPerLitre = milesPerLitre;
	}
	
	/* GETTERS */

	public float getMPG() { return this.milesPerLitre; }
	
	/* SETTERS */
	
	public void fillTank(float numOfLitre) {
		float remainder = this.bestCondition - this.condition;
		if (this.condition < 0) {
			this.status("Nice try, but you can't steal my gas with a negative number! Adding "
					+ remainder + " gallons to reach capacity.");
			this.setCondition(this.bestCondition);
		} else if (numOfLitre > remainder) {
			this.status("Too much! Only adding " + remainder + " Litres to reach capacity.");
			this.setCondition(this.bestCondition);
		} else {
			this.status("Adding " + numOfLitre + " Litres. You now have "
					+ this.condition + numOfLitre + " Litres.");
			this.changeCondition(numOfLitre);
		}
	}
	
	public void function(float milesDriven) throws CarCrashException{
		super.function(milesDriven);
		float LitresConsumed = milesDriven / this.milesPerLitre;
		this.changeCondition(-1 * LitresConsumed);
		if (this.condition <= 0) {
			this.crashCar();
		} else if (this.condition <= (this.bestCondition / 4)) {
			this.status("Low on gas!");
			if (getBoolean("Refill?")) {
				this.fillTank(getFloat("How many Litres would you like to add?"));
			}
		}
		if ((this.condition / this.bestCondition) < 0.5) {
			this.status("Your gas tank is less than half full.");
		} else {
			this.status("You're good on gas.");
		}
	}
}
