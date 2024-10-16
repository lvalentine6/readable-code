package cleancode.studycafe.tobe.io;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

class InputHandlerTest {

	@DisplayName("사용자에 입력에 알맞는 스터디카페 이용권 타입을 반환한다.")
	@Test
	void getPassTypeSelectingUserAction() {
		//given
		String userInput = "1";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
		Scanner scanner = new Scanner(inputStream);
		InputHandler inputHandler = new InputHandler(scanner);

		StudyCafePassType expectPassType = StudyCafePassType.HOURLY;

		//when
		StudyCafePassType selectedPassType = inputHandler.getPassTypeSelectingUserAction();

		//then
		assertThat(selectedPassType).isEqualTo(expectPassType);
	}

	@DisplayName("사용자가 잘못된 입력시 AppException 예외를 던진다.")
	@Test
	void getPassTypeSelectingUserAction_throwsException() {
		//given
		String userInput = "A";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
		Scanner scanner = new Scanner(inputStream);
		InputHandler inputHandler = new InputHandler(scanner);

		//when & then
		assertThatThrownBy(inputHandler::getPassTypeSelectingUserAction)
			.isInstanceOf(AppException.class);
	}

	@DisplayName("사용자에 입력에 알맞는 스터디카페 이용권을 반환한다.")
	@Test
	void getSelectPass() {
		//given
		String userInput = "1";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
		Scanner scanner = new Scanner(inputStream);
		InputHandler inputHandler = new InputHandler(scanner);
		StudyCafePassType inputPassType = StudyCafePassType.HOURLY;
		StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
		List<StudyCafePass> filteredCafePasses = studyCafeFileHandler.selectedCafePasses(inputPassType);

		StudyCafePass expectedPass = filteredCafePasses.get(0);

		//when
		StudyCafePass selectedPass = inputHandler.getSelectPass(filteredCafePasses);

		//then
		assertThat(selectedPass).isEqualTo(expectedPass);
	}

	@DisplayName("사용자가 잘못된 입력시 Exception 예외를 던진다.")
	@Test
	void getSelectPass_throwsException() {
		//given
		String userInput = "B";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
		Scanner scanner = new Scanner(inputStream);
		InputHandler inputHandler = new InputHandler(scanner);
		StudyCafePassType inputPassType = StudyCafePassType.HOURLY;
		StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
		List<StudyCafePass> filteredCafePasses = studyCafeFileHandler.selectedCafePasses(inputPassType);

		//when & then
		assertThatThrownBy(() -> inputHandler.getSelectPass(filteredCafePasses))
			.isInstanceOf(Exception.class);
	}

	@DisplayName("사용자에 입력에 알맞는 라커 이용여부를 반환한다.")
	@Test
	void getLockerSelection() {
		//given
		String userInput = "1";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
		Scanner scanner = new Scanner(inputStream);
		InputHandler inputHandler = new InputHandler(scanner);

		boolean expectLockerUes = true;

		//when
		boolean isLockerUes = inputHandler.getLockerSelection();

		//then
		assertThat(isLockerUes).isEqualTo(expectLockerUes);
	}

	@DisplayName("사용자가 잘못된 입력시 AppException 예외를 던진다.")
	@Test
	void getLockerSelection_throwException() {
		//given
		String userInput = "1234";
		ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
		Scanner scanner = new Scanner(inputStream);
		InputHandler inputHandler = new InputHandler(scanner);

		//when & then
		assertThatThrownBy(inputHandler::getLockerSelection)
			.isInstanceOf(AppException.class);
	}
}