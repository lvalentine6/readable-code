package cleancode.studycafe.tobe.io;

import java.util.List;
import java.util.Scanner;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

public class InputHandler {

	private final Scanner SCANNER;

	public InputHandler(Scanner SCANNER) {
		this.SCANNER = SCANNER;
	}

	public StudyCafePassType getPassTypeSelectingUserAction() {
		String userInput = SCANNER.nextLine();

		if ("1".equals(userInput)) {
			return StudyCafePassType.HOURLY;
		}
		if ("2".equals(userInput)) {
			return StudyCafePassType.WEEKLY;
		}
		if ("3".equals(userInput)) {
			return StudyCafePassType.FIXED;
		}
		throw new AppException("잘못된 입력입니다.");
	}

	public StudyCafePass getSelectPass(List<StudyCafePass> passes) {
		String userInput = SCANNER.nextLine();
		int selectedIndex = Integer.parseInt(userInput) - 1;
		return passes.get(selectedIndex);
	}

	public boolean getLockerSelection() {
		String userInput = SCANNER.nextLine();
		if (!(userInput.equals("1") || userInput.equals("2"))) {
			throw new AppException("잘못된 입력입니다.");
		}
		return "1".equals(userInput);
	}
}
