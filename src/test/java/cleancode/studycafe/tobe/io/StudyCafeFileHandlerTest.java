package cleancode.studycafe.tobe.io;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

class StudyCafeFileHandlerTest {

	@DisplayName("선택된 스터디카페 이용권 타입에 알맞는 이용권 목록을 반환한다.")
	@Test
	void selectedCafePasses() {
		//given
		StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
		StudyCafePassType inputPassType = StudyCafePassType.FIXED;
		List<StudyCafePass> expectedPassed = List.of(StudyCafePass.of(inputPassType, 4, 250000, 0.1),
			StudyCafePass.of(inputPassType, 12, 700000, 0.15));

		//when
		List<StudyCafePass> selectedPasses = studyCafeFileHandler.selectedCafePasses(inputPassType);

		//then
		assertThat(selectedPasses).containsExactlyElementsOf(expectedPassed);
	}

	@DisplayName("선택된 스터디카페 이용권 타입에 알맞는 라커 이용권 목록을 반환한다.")
	@Test
	void selectedLockerPass() {
		//given
		StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
		StudyCafePass inputPass = StudyCafePass.of(StudyCafePassType.FIXED, 4, 250000, 0.1);
		StudyCafeLockerPass expectedLockerPassed = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 10000);
		//when
		StudyCafeLockerPass selectedLockerPasses = studyCafeFileHandler.selectedLockerPass(inputPass);

		//then
		assertThat(selectedLockerPasses).isEqualTo(expectedLockerPassed);
	}

	@DisplayName("선택된 스터디카페 이용권 타입에 알맞는 라커 이용권이 없다면 null을 반환한다.")
	@Test
	void selectedLockerPass_null() {
		//given
		StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();
		StudyCafePass inputPass = StudyCafePass.of(StudyCafePassType.HOURLY, 6, 9000, 0.0);
		//when
		StudyCafeLockerPass selectedLockerPasses = studyCafeFileHandler.selectedLockerPass(inputPass);

		//then
		assertThat(selectedLockerPasses).isEqualTo(null);
	}
}