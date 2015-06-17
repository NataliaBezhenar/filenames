package hrytsenko;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;

import com.google.common.base.Preconditions;

/**
 * Utilities for working with filenames.
 */
public final class Filenames {
	
	static Matcher getMatch (String baseName){
		 Matcher matcher = Pattern.compile("\\s\\(\\d+\\)$").matcher(baseName);
		 return matcher;
	}

	public static boolean isMatchesParenthesesWithNumber(String baseName) {

		boolean b = false;
		Matcher matcher = getMatch(baseName);
		if (matcher.find()) {
			b = true;
		}

		return b;
	}

	public static int getNumbers(String baseName) {

		Integer returnValue = 0;
		Matcher matcher = getMatch(baseName);
		if (matcher.find()) {
			String i = matcher.group();
			i = i.replace(" (", "");
			i = i.replace(")", "");
			returnValue = Integer.parseInt(i);
		}

		return returnValue;
	}

	public static String getBaseNameWithoutNumber(String baseName) {

		String baseNameWithoutNumbers = "";
		Matcher matcher = getMatch(baseName);
		if (matcher.find()) {
			String i = matcher.group();
			baseNameWithoutNumbers = baseName.replace(i, "");
		}
		return baseNameWithoutNumbers;
	}

	/**
	 * Generates the unique name to avoid duplication with the known names.
	 * 
	 * <p>
	 * To generate unique name, we add serial number to the end of name.
	 * 
	 * For example: "logo.png", "logo (1).png" and so on.
	 * 
	 * @param originalName
	 *            the original name.
	 * @param knownNames
	 *            the list of known names.
	 * 
	 * @return the generated unique name.
	 */
	public static String generateUniqueName(String originalName,
			List<String> knownNames) {

		Preconditions.checkNotNull(originalName, "Original name is null");
		Preconditions.checkArgument((!originalName.isEmpty()),
				"Original name is empty");

		if (knownNames.isEmpty()) {
			return originalName;
		}

		if (!knownNames.contains(originalName)) {
			return originalName;
		}

		String baseName = FilenameUtils.getBaseName(originalName);
		String extention = FilenameUtils.getExtension(originalName);
		if (extention.isEmpty()) {
			System.out
					.println("File without extention, try to enter filename once more time");
			generateUniqueName(originalName, knownNames);
		}

		LinkedList<String> listWithbaseNames = new LinkedList<>();
		for (String s : knownNames) {
			listWithbaseNames.add(FilenameUtils.getBaseName(s));
		}

		List<Integer> listWithFilesNumbers = new LinkedList<>();
		for (String s : listWithbaseNames) {
			listWithFilesNumbers.add(getNumbers(s));
		}

		if (knownNames.contains(originalName)) {
			if (isMatchesParenthesesWithNumber(baseName)) {

				Integer currentFileNumber = getNumbers(baseName);
				while (listWithFilesNumbers.contains(currentFileNumber))
					++currentFileNumber;
				originalName = getBaseNameWithoutNumber(baseName) + " ("
						+ currentFileNumber + ")." + extention;
			}

			Integer currentFileNumber = Collections.max(listWithFilesNumbers);
			while (listWithFilesNumbers.contains(currentFileNumber))
				++currentFileNumber;

			originalName = baseName + " (" + currentFileNumber + ")."
					+ extention;

		}

		return originalName;

	}

	private Filenames() {
	}

}