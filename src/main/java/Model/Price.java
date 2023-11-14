package Model;

public class Price {
    private final int originalTotalPrice;
    private final int finalPrice;
    private final int totalBenefitPrice;
    public Price(int originalTotalPrice,int finalPrice,int totalBenefitPrice){
        this.originalTotalPrice=originalTotalPrice;
        this.finalPrice=finalPrice;
        this.totalBenefitPrice=totalBenefitPrice;
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
