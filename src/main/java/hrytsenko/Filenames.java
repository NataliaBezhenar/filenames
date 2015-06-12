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

	
	/**
	* Method containsBracketsWithNumber checks if filename contains construction "(number)"
	* returns boolean value
	* @param baseName
	*/
	public static boolean containsBracketsWithNumber (String baseName){
		
		boolean b = false;
		Pattern pat = Pattern.compile("\\s\\(\\d+\\)$");
		Matcher matcher = pat.matcher(baseName);
		if (matcher.find()){
		b=true;}
		
		return b;
	}
	
	
	/**
	* Method findNumbersInTheEndOfFilename finds number of file's version
	*@param baseName
	* @return integer value of file version
	*/
	
	public static int findNumbersInTheEndOfFilename(String baseName){
		
		Integer returnValue=0;
		Pattern pat = Pattern.compile("\\s\\(\\d+\\)$");
		Matcher matcher = pat.matcher(baseName);
		if (matcher.find()){
		String  i=matcher.group();
		i = i.replace(" (", "");
		i=i.replace(")", "");
		returnValue = Integer.parseInt(i);
		}
		

		return returnValue;
	}
	
	
/**
* Method findBaseNameWithoutNumber finds the part of base filename before it's number
*@param baseName
* @return String value of filename without number
*/
	public static String findBaseNameWithoutNumber(String baseName){
        
		String baseNameWithoutNumbers="";
		Pattern pat = Pattern.compile("\\s\\(\\d+\\)$");
		Matcher matcher = pat.matcher(baseName);
		if (matcher.find()){
		String  i=matcher.group();
		baseNameWithoutNumbers = baseName.replace(i, "");}
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
    public static String generateUniqueName(String originalName, List<String> knownNames) {
        //throw new UnsupportedOperationException("Not implemented.");
    	String finalName="";
    	
    	
    	Preconditions.checkArgument((!originalName.isEmpty()), "Original name is empty");
    	Preconditions.checkNotNull(originalName, "Original name is null");
    	
    	if (knownNames.isEmpty()) {
    		finalName = originalName;
    	}
    	
    	
    	if (!knownNames.contains(originalName)) {
    		finalName = originalName;
    	}
    	
    	String baseName = FilenameUtils.getBaseName(originalName);
		String extention = FilenameUtils.getExtension(originalName);
		
		String baseNameWithoutNumber=findBaseNameWithoutNumber(baseName);
    	
		
		//create List with basenames
		List <String> listWithbaseNames = new LinkedList <String>();
		for (String s:knownNames){
			listWithbaseNames.add(FilenameUtils.getBaseName(s));
		}
		
		
		//create List with file numbers
		List <Integer> listWithFilesNumbers = new LinkedList <Integer>();
		for (String s: listWithbaseNames){
			listWithFilesNumbers.add(findNumbersInTheEndOfFilename(s));
		}
		
		
	
		
		if (knownNames.contains(originalName))
		{		
			if (containsBracketsWithNumber(baseName)){
		
		Integer currentFileNumber = findNumbersInTheEndOfFilename(baseName);
		while (listWithFilesNumbers.contains(currentFileNumber)) ++currentFileNumber;
		finalName =  baseNameWithoutNumber+" ("+currentFileNumber+")."+extention;
		}
		
		
		
		if (!containsBracketsWithNumber(baseName)){
			
			Integer currentFileNumber =Collections.max(listWithFilesNumbers);
			while (listWithFilesNumbers.contains(currentFileNumber)) ++currentFileNumber;
			
			
			finalName =  baseName+" ("+currentFileNumber+")."+extention;
		}
		
		}
		
    	return finalName;
		
    	
    }

    private Filenames() {
    }

}