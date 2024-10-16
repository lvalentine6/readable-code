package cleancode.studycafe.tobe;

import java.util.List;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

public class StudyCafePassMachine {

	private final InputHandler inputHandler;
	private final OutputHandler outputHandler;
	private final StudyCafeFileHandler studyCafeFileHandler;

	public StudyCafePassMachine(InputHandler inputHandler, OutputHandler outputHandler,
		StudyCafeFileHandler studyCafeFileHandler) {
		this.inputHandler = inputHandler;
		this.outputHandler = outputHandler;
		this.studyCafeFileHandler = studyCafeFileHandler;
	}

	public void run() {
		try {
			outputHandler.showInitializeMessage();

			StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();
			StudyCafePass selectedPass = selectPass(studyCafePassType);
			processOrder(selectedPass);

		} catch (AppException e) {
			outputHandler.showSimpleMessage(e.getMessage());
		} catch (Exception e) {
			outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
		}
	}

	private void processOrder(StudyCafePass selectedPass) {
		if (selectedPass.getPassType() == StudyCafePassType.FIXED) {
			processFixedPassOrder(selectedPass);
			return;
		}
		outputHandler.showPassOrderSummary(selectedPass, null);
	}

	private void processFixedPassOrder(StudyCafePass selectedPass) {
		StudyCafeLockerPass filteredLockerPass = studyCafeFileHandler.selectedLockerPass(selectedPass);
		if (filteredLockerPass != null && offerLockerPass(filteredLockerPass)) {
			outputHandler.showPassOrderSummary(selectedPass, filteredLockerPass);
		}
	}

	private boolean offerLockerPass(StudyCafeLockerPass lockerPass) {
		outputHandler.askLockerPass(lockerPass);
		return inputHandler.getLockerSelection();
	}

	private StudyCafePass selectPass(StudyCafePassType passType) {
		List<StudyCafePass> filteredCafePasses = studyCafeFileHandler.selectedCafePasses(passType);
		outputHandler.showPassListForSelection(filteredCafePasses);
		return inputHandler.getSelectPass(filteredCafePasses);
	}
}
