package hrytsenko;


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.FileFileFilter;


/**
 * Utilities for working with filenames.
 */
public final class Filenames {
	
    public static <T> T getLast(List<T> list) {
        return list != null && !list.isEmpty() ? list.get(list.size() - 1) : null;
    }
	
	public static int findNumbersInTheEndOfFilename(String filename){
		
		Integer ii=0;
		Pattern pat = Pattern.compile("\\d+$");
		Matcher matcher = pat.matcher(filename);
		if (matcher.find()){
		String  i=matcher.group();
		ii = Integer.parseInt(i);
		}
		return ii;
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
    	
        String filename=""; 
    



        if (knownNames.contains(originalName))
         {
        	 Integer fileNumber = findNumbersInTheEndOfFilename(originalName);
             String baseName = FilenameUtils.getBaseName(originalName);
             String extention = FilenameUtils.getExtension(originalName);
             Integer NewIndex=0;
             List<Integer> listOfIndexes = new ArrayList<Integer>();
             
             
             File f = new File(".");
             String[] files = f.list( FileFileFilter.FILE );
             for (String s:files){
            	 FilenameUtils.getBaseName(s);
            	 listOfIndexes.add(findNumbersInTheEndOfFilename(s));
            	 
             }
             Collections.sort(listOfIndexes);
             for(Integer i:listOfIndexes){
             if (i.equals(fileNumber)){
            	 NewIndex=getLast(listOfIndexes)+1;
             }
             filename=baseName+NewIndex+"."+extention;
            	 }
        }
       
        else filename=originalName;
     
        
       return filename;
     
      
    			
    }

    private Filenames() {
    }

}
