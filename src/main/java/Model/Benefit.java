package Model;

import christmas.MenuDetail;

import java.util.List;

public class Benefit {
    private final List<MenuDetail> menuDetailList;
    private int d_dayBenefit;
    private int weekdayBenefit;
    private int weekendBenefit;
    private int specialStarBenefit;
    private int giftBenefit;
    private int totalBenefit;

    public Benefit(List<MenuDetail> menuDetailList) {
        this.d_dayBenefit = 0;
        this.weekdayBenefit = 0;
        this.weekendBenefit = 0;
        this.specialStarBenefit = 0;
        this.giftBenefit = 0;
        this.totalBenefit = 0;
        this.menuDetailList = menuDetailList;
    }

    public void setD_dayBenefit(int d_dayBenefit) {
        this.d_dayBenefit = d_dayBenefit;
    }

    public void setWeekdayBenefit(int weekdayBenefit) {
        this.weekdayBenefit = weekdayBenefit;
    }

    public void setWeekendBenefit(int weekendBenefit) {
        this.weekendBenefit = weekendBenefit;
    }

    public void setSpecialStarBenefit(int specialStarBenefit) {
        this.specialStarBenefit = specialStarBenefit;
    }

    public void setGiftBenefit(int giftBenefit) {
        this.giftBenefit = giftBenefit;
    }

    public void setTotalBenefit() {
        this.totalBenefit = this.d_dayBenefit + this.weekdayBenefit + this.weekendBenefit + this.specialStarBenefit + this.giftBenefit;
    }

    public int getTotalBenefit() {
        return totalBenefit;
    }

    public int getD_dayBenefit() {
        return d_dayBenefit;
    }

    public int getWeekdayBenefit() {
        return weekdayBenefit;
    }

    public int getWeekendBenefit() {
        return weekendBenefit;
    }

    public int getSpecialStarBenefit() {
        return specialStarBenefit;
    }

    public int getGiftBenefit() {
        return giftBenefit;
    }
}
