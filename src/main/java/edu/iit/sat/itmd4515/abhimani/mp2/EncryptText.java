package edu.iit.sat.itmd4515.abhimani.mp2;

/**
 *
 * @author Ankit Bhimani (abhimani) on edu.iit.sat.itmd4515.abhimani.mp2
 */
public class EncryptText{

    public static String base64encode(String text){
	int offset=2%26+26;
	StringBuilder encoded=new StringBuilder();
	for(char i:text.toCharArray())
	    if(Character.isLetter(i))
		if(Character.isUpperCase(i))
		    encoded.append((char)('A'+(i-'A'+offset)%26));
		else
		    encoded.append((char)('a'+(i-'a'+offset)%26));
	    else
		encoded.append(i);
	return encoded.toString();
    }

    public static String base64decode(String text){
	int offset=2%26+26;
	StringBuilder encoded=new StringBuilder();
	for(char i:text.toCharArray())
	    if(Character.isLetter(i))
		if(Character.isUpperCase(i))
		    encoded.append((char)('A'-(i-'A'+offset)%26));
		else
		    encoded.append((char)('a'-(i-'a'+offset)%26));
	    else
		encoded.append(i);
	return encoded.toString();
    }
}
