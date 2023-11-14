package Controller;

import Model.Benefit;
import Model.Calculator.CalculateBenefit;
import Model.Price;
import View.InputView;
import View.OutputView;
import Model.MenuDetail;

import java.util.List;

public class ChristmasEvent {
    private Integer date;
    private List<MenuDetail> menuDetailList;
    private Benefit benefit;
    private Price price;
    public ChristmasEvent() {

    }
    public void christmasEventStart() {
        InputView inputView = new InputView();
        userInput(inputView);

        int originalTotalPrice = findOriginalTotalPrice();

        OutputView outputView = new OutputView(date, menuDetailList);

        openingStep(outputView);
        showMenuStep(outputView);
        showOriginalPriceStep(outputView, originalTotalPrice);
        showGiftStep(outputView,originalTotalPrice);
        showBenefitStep(outputView, originalTotalPrice);
        showTotalBenefitPriceStep(outputView);
        showFinalPriceStep(outputView);
        showDecemberEventBadgeStep(outputView);
    }
    private void userInput(InputView inputView) {
        inputDate(inputView);
        inputMenu(inputView);
    }
    private void inputDate(InputView inputView) {
        inputView.readDate();
        date = inputView.getDate();
    }
    private void inputMenu(InputView inputView) {
        inputView.readMenu();
        menuDetailList = inputView.getMenuDetailList();
    }
    private int findOriginalTotalPrice() {
        int totalPrice = 0;
        for (MenuDetail menuDetail : menuDetailList) {
            totalPrice += menuDetail.price * menuDetail.num;
        }
        return totalPrice;
    }
    private void showBenefitStep(OutputView outputView, int originalTotalPrice) {
        boolean hasBenefit = setBenefit(date, menuDetailList, originalTotalPrice);
        int total_benefit = benefit.getTotalBenefit();
        this.price = new Price(originalTotalPrice, total_benefit);
        outputView.printBenefitDetailsTitle();
        outputView.printDiscountDetails(hasBenefit, benefit.getD_dayBenefit(), benefit.getWeekdayBenefit(), benefit.getWeekendBenefit(), benefit.getSpecialStarBenefit(), benefit.getGiftBenefit());
        outputView.printBlankLine();
    }
    private boolean setBenefit(int date, List<MenuDetail> menuDetailList, int originalTotalPrice) {
        CalculateBenefit calculateBenefit = new CalculateBenefit(date, menuDetailList, originalTotalPrice);
        this.benefit = calculateBenefit.getBenefit();
        return calculateBenefit.calculateAllBenefit();
    }
    private void openingStep(OutputView outputView) {
        outputView.printOpeningTitle();
        outputView.printBlankLine();
    }
    private void showMenuStep(OutputView outputView) {
        outputView.printMenuTitle();
        outputView.printMenuDetail();
        outputView.printBlankLine();
    }
    private void showOriginalPriceStep(OutputView outputView, int originalTotalPrice) {
        outputView.printOriginalPriceTitle();
        outputView.printOriginalTotalPrice(originalTotalPrice);
        outputView.printBlankLine();
    }
    private void showGiftStep(OutputView outputView, int originalTotalPrice) {
        outputView.printGiftMenuTitle();
        outputView.printGift(originalTotalPrice);
        outputView.printBlankLine();
    }
    private void showTotalBenefitPriceStep(OutputView outputView) {
        outputView.printTotalBenefitPriceTitle();
        outputView.printTotalBenefitPrice(price.getTotalBenefitPrice());
        outputView.printBlankLine();
    }
    private void showFinalPriceStep(OutputView outputView) {
        outputView.printFinalPriceTitle();
        outputView.printFinalPrice(price.getFinalPrice(),price.getOriginalTotalPrice());
        outputView.printBlankLine();
    }
    private void showDecemberEventBadgeStep(OutputView outputView) {
        outputView.printDecemberEventBadgeTitle();
        outputView.printDecemberEventBadge(benefit.getTotalBenefit());
        outputView.printBlankLine();
    }
}
