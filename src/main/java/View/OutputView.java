package View;

import Model.MenuDetail;

import java.util.List;

import static View.OutputComment.*;


public class OutputView {
    private final Integer date;
    private final List<MenuDetail> menuDetailList;

    public OutputView(Integer date, List<MenuDetail> menuDetailList) {
        this.date = date;
        this.menuDetailList = menuDetailList;
    }

    public void printBlankLine() {
        System.out.println();
    }

    public void printOpeningTitle() {
        System.out.printf((OPENING_TITLE) + "%n", date);
    }

    public void printMenuTitle() {
        System.out.println(MENU_TITLE);
    }

    public void printOriginalPriceTitle() {
        System.out.println(ORIGINAL_PRICE_TITLE);
    }

    public void printGiftMenuTitle() {
        System.out.println(GIFT_MENU_TITLE);
    }

    public void printBenefitDetailsTitle() {
        System.out.println(BENEFIT_DETAILS_TITLE);
    }

    public void printTotalBenefitPriceTitle() {
        System.out.println(TOTAL_BENEFIT_PRICE_TITLE);
    }

    public void printFinalPriceTitle() {
        System.out.println(FINAL_PRICE_TITLE);
    }

    public void printDecemberEventBadgeTitle() {
        System.out.println(DECEMBER_EVENT_BADGE_TITLE);
    }

    public void printMenuDetail() {
        for (MenuDetail menuDetail : menuDetailList) {
            System.out.println(menuDetail.name + " " + menuDetail.num + "개");
        }
    }

    public void printOriginalTotalPrice(int originalTotalPrice) {
        System.out.printf("%,d원%n", originalTotalPrice);
    }

    public void printGift(int originalTotalPrice) {
        if (originalTotalPrice > 120000) {
            System.out.println("샴페인 1개");
            return;
        }
        System.out.println("없음");
    }

    public void printDiscountDetails(boolean hasBenefit, int d_dayBenefit, int weekdayBenefit, int weekendBenefit, int specialStarBenefit, int giftBenefit) {
        if (hasBenefit) {
            printD_dayBenefit(d_dayBenefit);
            printWeekdayBenefit(weekdayBenefit);
            printWeekendBenefit(weekendBenefit);
            printSpecialStarBenefit(specialStarBenefit);
            printGiftBenefit(giftBenefit);
            return;
        }
        System.out.println("없음");
    }

    public void printTotalBenefitPrice(int totalBenefit) {
        if (totalBenefit != 0) {
            System.out.printf("-%,d원%n", totalBenefit);
            return;
        }
        System.out.println("없음");
    }

    public void printFinalPrice(int finalPrice) {
            System.out.printf("%,d원%n", finalPrice);
    }

    public void printDecemberEventBadge(int totalBenefit) {
        if (totalBenefit != 0) {
            System.out.println(getBadge(totalBenefit));
            return;
        }
        System.out.println("없음");
    }

    public String getBadge(int price) {
        if (price >= 5000 && price < 10000) {
            return "별";
        }
        if (price >= 10000 && price < 20000) {
            return "트리";
        }
        if (price >= 20000) {
            return "산타";
        }
        return "없음";

    }

    private void printD_dayBenefit(int d_dayBenefit) {
        if (d_dayBenefit != 0) {
            System.out.printf(CHRISTMAS_D_DAY_DISCOUNT + "%n", d_dayBenefit);
        }
    }

    private void printWeekdayBenefit(int weekdayBenefit) {
        if (weekdayBenefit != 0) {
            System.out.printf(WEEKDAY_DISCOUNT + "%n", weekdayBenefit);
        }
    }

    private void printWeekendBenefit(int weekendBenefit) {
        if (weekendBenefit != 0) {
            System.out.printf(WEEKEND_DISCOUNT + "%n", weekendBenefit);
        }
    }

    private void printSpecialStarBenefit(int specialStarBenefit) {
        if (specialStarBenefit != 0) {
            System.out.printf(SPECIAL_STAR_DISCOUNT + "%n", specialStarBenefit);
        }
    }

    private void printGiftBenefit(int giftBenefit) {
        if (giftBenefit != 0) {
            System.out.printf(GIFT_EVENT_DISCOUNT + "%n", giftBenefit);
        }
    }
}
