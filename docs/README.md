<h2>개발 내용</h2>
- - -
<h3>View Part</h3>
ㄴInputView
- 사용자로부터 입력을 받는다.
- 먼저, 12월 중 언제 방문할 지 요일을 입력 받는다.
  - 입력 받은 요일을 검사 한다
  - 숫자 인지 확인 (아닐시 `IllegalArgumentException` 발생)
  - 1~31 사이의 숫자인지 확인 (아닐시 `IllegalArgumentException` 발생)
  - 숫자가 아니거나 1~31 사이의 숫자가 아니면 다시 입력 받는다.
- 그 다음, 어떤 메뉴를 몇 개 주문할 지 입력 받는다.
  - 입력 받은 메뉴를 검사 한다.
  - 입력 형식이 올바른 지 확인 ex) 초코케이크-a (아닐시 `IllegalArgumentException` 발생)
  - 해당 메뉴가 존재 하는지 확인 (아닐시 `IllegalArgumentException` 발생)
  - 총 메뉴의 수가 20이 넘지 않는지 확인 (아닐시 `IllegalArgumentException` 발생)
  - 시킨 메뉴가 음료만 존재 하는지 확인 (아닐시 `IllegalArgumentException` 발생)
  - 중복 되는 메뉴를 주문 하는지 확인 (아닐시 `IllegalArgumentException` 발생)
  - 위의 경우에는 `[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.`를 보여 주며 다시 입력 받는다.

ㄴOutputView
  - 사용자로부터 입력 받은 정보를 바탕으로 결과를 출력한다.
  - Model에서 계산한 결과를 받아 출력한다.
  - 주문메뉴, 할인전 총금액, 증정 메뉴, 혜택 내용, 총혜택 금액, 할인 후 예상 금액, 12월 이벤트 벳지의 내용을 출력한다

ㄴOutputComment
- OutputView에서 사용하는 문자열을 모아놓은 클래스
- `final`로 선언하여 상수로 사용한다.
- `static`으로 선언하여 객체를 생성하지 않고 사용한다.

---
<h3>Model Part</h3>
ㄴMenuDetail
- 메뉴의 상세 정보를 담고 있는 클래스(enum class)
- 메뉴의 이름, 가격을 가지고 있다.
- num 변수로 해당 메뉴의 수량을 확인한다.

ㄴMenuGroup
- 메뉴의 그룹을 담고 있는 클래스(enum class)
- 메뉴 그룹에 맞게 MenuDetail 객체를 list에 담는다.
- 메뉴의 그룹을 확인할 수 있다.

메뉴를 담는 두 개의 클래스는 `enum class`로 구현하였다.<br>

ㄴBenefit
- 할인 정보를 담고 있는 클래스
- 디데이 할인을 확인할 수 있는 메서드를 가지고 있다. (`getD_dayBenefit()`)
- 주중, 주말 할인을 확인할 수 있는 메서드를 가지고 있다. (`getWeekdayBenefit(), getWeekendBenefit`)
- 특별 할인을 확인할 수 있는 메서드를 가지고 있다. (`getSpecialBenefit()`)
- 증정 할인을 확인할 수 있는 메서드를 가지고 있다. (`getGiftBenefit()`)

ㄴCalculator/CalculateBenefit
- 할인 정보를 계산하는 클래스
- 모든 할인 정보를 계산하는 메서드를 가지고 있다. (`calculateAllBenefit()`)
- 만약, 구입한 모든 금액이 10000원 이상이 아닐 경우 할인 정보를 계산하지 않는다. --> `OutputView`에서 없음을 출력하게 처리해준다.
- 만약, 할인 금액의 합이 0인 경우 --> `OutputView`에서 없음을 출력하게 처리해준다.
- 주중, 주말 할인에는 각 디저트 갯수에 맞게 혹은 메인메뉴 갯수에 맞게 잘 계산한다.
- 해당 갯수를 구하는 메서드를 가지고 있다. (`calculateDessertNum(), calculateMainNum()`)

ㄴPrice
- 가격 정보를 담고 있는 클래스 -> 결과 출력을 위해 존재한다.
- 전체 금액, 할인 금액, 할인 후 금액을 가지고 있다.

ㄴDay
- 요일을 담고 있는 클래스(enum class)
- 일자별 요일을 확인할 수 있는 메서드를 가지고 있다. (`findByDate()`)
- 12월의 일수에 맞게 요일을 구분하였다.

ㄴWeek
- 주중, 주말을 담고 있는 클래스(enum class)
- 요일로 주중인지 주말인지 확인할 수 있는 메서드를 가지고 있다. (`findByDay()`)

Day, Week는 할인 정보를 계산할 때 사용하였다.

---
<h3>Controller Part</h3>
ㄴController
  - View와 Model을 연결하는 클래스
  - View에서 입력 받은 정보를 Model에 전달한다.
  - Model에서 계산한 결과를 View에 전달한다.
  - View 내역에 맞게 메소드를 구현한다.

---
<h3>Exception Part</h3>
ㄴIllegalArgumentException
  - View에서 입력 받은 정보를 검사하는 과정에서 발생하는 예외
  - View에서 입력 받은 정보가 유효하지 않을 경우 발생한다.
  - View에서 예외를 처리한다.
  - 예외 메시지를 출력하고 View에서 다시 입력을 받게 한다.

---
<h3>Application Part</h3>
ㄴApplication
  - 프로그램을 실행하는 클래스
  - `main()` 메서드를 가지고 있다.
  - Controller 객체를 생성하여 프로그램을 실행한다.