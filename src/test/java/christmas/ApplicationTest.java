package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import Model.MenuDetail;
import View.InputView;
import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import java.util.List;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    @Test
    void 시작_타이틀_출력(){
        assertSimpleTest(()->{
            run("3","티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        });
    }
    @Test
    void 할인전_총_금액_확인(){
        assertSimpleTest(()->{
            run("3","티본스테이크-2,바비큐립-1,초코케이크-2,제로콜라-1");
            int totalPrice=0;
            InputView inputView=new InputView();
            inputView.validateMenu("티본스테이크-2,바비큐립-1,초코케이크-2,제로콜라-1");
            for(MenuDetail menuDetail:inputView.getMenuDetailList()){
                totalPrice+=menuDetail.price*menuDetail.num;
            }
            assertThat(output()).contains("<할인 전 총주문 금액>"+LINE_SEPARATOR+String.format("%,d원",totalPrice));
        });
    }
    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                "<주문 메뉴>",
                "<할인 전 총주문 금액>",
                "<증정 메뉴>",
                "<혜택 내역>",
                "<총혜택 금액>",
                "<할인 후 예상 결제 금액>",
                "<12월 이벤트 배지>"
            );
        });
    }

    @Test
    void 혜택_내역_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }
    @Test
    void 총_혜택_출력(){
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<총혜택 금액>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    void 날짜_예외_테스트() {
        assertSimpleTest(() -> {
            runException("a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_예외_테스트() {
        assertSimpleTest(()->{
            InputView inputView=new InputView();
            inputView.validateMenu("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            List<MenuDetail> menuDetailList=inputView.getMenuDetailList();
            assertThat(menuDetailList.size()).isEqualTo(4);
            assertThat(menuDetailList.get(0).name).isEqualTo("티본스테이크");
            assertThat(menuDetailList.get(0).num).isEqualTo(1);
            assertThat(menuDetailList.get(1).name).isEqualTo("바비큐립");
            assertThat(menuDetailList.get(1).num).isEqualTo(1);
            assertThat(menuDetailList.get(2).name).isEqualTo("초코케이크");
            assertThat(menuDetailList.get(2).num).isEqualTo(2);
            assertThat(menuDetailList.get(3).name).isEqualTo("제로콜라");
            assertThat(menuDetailList.get(3).num).isEqualTo(1);
        });
        assertSimpleTest(() -> {
            runException("3", "제로콜라-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
        assertSimpleTest(() -> {
            runException("3", "제로콜라-a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
        assertSimpleTest(() ->{
            //연속 overlap 확인
            runException("3","제로콜라-1,제로콜라-1,제로콜라-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
        assertSimpleTest(() -> {
            //overlap 확인
            runException("3", "제로콜라-1,제로콜라-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
        assertSimpleTest(() -> {
            //20개 넘는지 확인
            runException("3", "시저샐러드-3,양송이수프-4,티본스테이크-10,바비큐립-7,초코케이크-2,제로콜라-1");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 날짜_예외_테스트2(){
        assertSimpleTest(() -> {
            runException("88");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
        assertSimpleTest(() -> {
            runException("sk");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }
    @Test
    void 날짜_관련_할인_테스트(){
        assertSimpleTest(()->{
            run("5","티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains("크리스마스 디데이 할인: -1,400원");
        });
        assertSimpleTest(()->{
            run("5","티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains("평일 할인: -4,046원");
        });
        assertSimpleTest(()->{
            run("8","티본스테이크-2,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains("주말 할인: -6,069원");
        });
        assertSimpleTest(()->{
            run("29","티본스테이크-2,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR+"주말 할인: -6,069원");
        });
        assertSimpleTest(()->{
            run("25","티본스테이크-2,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains("특별 할인: -1,000원");
        });
        assertSimpleTest(()->{
            run("25","아이스크림-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR+"없음");
        });
    }

    @Test
    void 총괄_테스트(){
        assertSimpleTest(()->{
            run("3","티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!",
                    "<주문 메뉴>",
                    "티본스테이크 1개",
                    "바비큐립 1개",
                    "초코케이크 2개",
                    "제로콜라 1개",
                    "<할인 전 총주문 금액>",
                    "142,000원",
                    "<증정 메뉴>",
                    "샴페인 1개",
                    "<혜택 내역>",
                    "크리스마스 디데이 할인: -1,200원",
                    "평일 할인: -4,046원",
                    "특별 할인: -1,000원",
                    "증정 이벤트: -25,000원",
                    "<총혜택 금액>",
                    "-31,246원",
                    "<할인 후 예상 결제 금액>",
                    "135,754원",
                    "<12월 이벤트 배지>",
                    "산타");
        });
    }
    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
