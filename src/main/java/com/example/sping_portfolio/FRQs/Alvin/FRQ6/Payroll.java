
public class Payroll {
    private int[] itemsSold;
    private double[] wages;

    public double computeBonusThreshold() {
        double bonusThreshold = 0;
        for (int i = 0; i < itemsSold.length; i++) {
            if (itemsSold[i] > bonusThreshold) {
                bonusThreshold = itemsSold[i];
            }
        }
        return bonusThreshold;
    }

    public void computeWages(double fixedWage, double perItemWage) {
        for (int i = 0; i < itemsSold.length; i++) {
            if (itemsSold[i] > computeBonusThreshold()) {
                wages[i] = fixedWage + perItemWage * itemsSold[i];
            } else {
                wages[i] = fixedWage;
            }
        }
    }
}
