package Model;

public class Price {
    private final int originalTotalPrice;
    private final int finalPrice;
    private final int totalBenefitPrice;
    public Price(int originalTotalPrice,int totalBenefitPrice){
        this.originalTotalPrice=originalTotalPrice;
        this.totalBenefitPrice=totalBenefitPrice;
        this.finalPrice=originalTotalPrice-totalBenefitPrice;
    }
    public int getOriginalTotalPrice(){
        return originalTotalPrice;
    }
    public int getFinalPrice(){
        return finalPrice;
    }
    public int getTotalBenefitPrice(){
        return totalBenefitPrice;
    }
}
