package cleancode.studycafe.tobe;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;

class StudyCafePassMachineTest {

	@DisplayName("사용자가 2시간 자유석 이용권을 선택했을때 올바른 값을 출력한다.")
	@Test
	void run() {
		//given
		String userInput = "1\n1\n";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
		Scanner scanner = new Scanner(inputStream);

		InputHandler inputHandler = new InputHandler(scanner);
		OutputHandler outputHandler = new OutputHandler();
		StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

		StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(inputHandler, outputHandler,
			studyCafeFileHandler);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);
		System.setOut(printStream);

		//when
		studyCafePassMachine.run();

		//then
		String expectedOutput =
			"""
				*** 프리미엄 스터디카페 ***
				* 사물함은 고정석 선택 시 이용 가능합니다. (추가 결제)
				* !오픈 이벤트! 2주권 이상 결제 시 10% 할인, 12주권 결제 시 15% 할인! (결제 시 적용)
				
				사용하실 이용권을 선택해 주세요.
				1. 시간 이용권(자유석) | 2. 주단위 이용권(자유석) | 3. 1인 고정석
				
				이용권 목록
				1. 2시간권 - 4000원
				2. 4시간권 - 6500원
				3. 6시간권 - 9000원
				4. 8시간권 - 11000원
				5. 10시간권 - 12000원
				6. 12시간권 - 13000원
				
				이용 내역
				이용권: 2시간권 - 4000원
				총 결제 금액: 4000원
				
				""";

		String actualOutput = outputStream.toString().replaceAll("\r\n", "\n");
		assertThat(actualOutput).isEqualTo(expectedOutput);

		System.setOut(System.out);
	}
}