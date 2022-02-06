
class Plot {
    private String cropType;
    private int cropYield;

    public Plot(String crop, int yield){}

    public String getCropType(){
        return cropType;
    }

    public int getCropYield(){
        return cropYield;
    }
}

public class ExperimentalFarm {
    private Plot[][] farmPlots;

    public Plot getHighestYield(String c) {
        int highestYield = 0;
        Plot highestYieldPlot = null;

        for(int i = 0; i < farmPlots.length; i++) {
            for(int j = 0; j < farmPlots[i].length; j++) {
                if(farmPlots[i][j].getCropType().equals(c)) {
                    if(farmPlots[i][j].getCropYield() > highestYield) {
                        highestYield = farmPlots[i][j].getCropYield();
                        highestYieldPlot = farmPlots[i][j];
                    }
                }
            }
        }

        return highestYieldPlot;
    }

    public boolean sameCrop(int col) {
        boolean same = true;
        String crop = farmPlots[0][col].getCropType();

        for(int i = 0; i < farmPlots.length; i++) {
            if(!farmPlots[i][col].getCropType().equals(crop)) {
                same = false;
            }
        }

        return same;
    }
}
