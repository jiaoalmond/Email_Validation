//import java.util.ArrayList; // Only used to create a list of prefix, domain and email to test the method. 
//import java.util.List;		// Only used to create a list of prefix, domain and email to test the method. 
import java.util.Scanner;

public class EmailValidation {

	//1a) Method to check if a character is alphanumeric
	
	public static boolean isAlphanumeric(char alphabumeric) {
		char c = alphabumeric;
		if((c>='A' && c<='Z') || (c>='a' && c<='z') || (c>='0' && c<='9')) {
			//System.out.print(true);
			return true;
		}else {
			//System.out.print(false);
			return false;
		}		
	}
	//2b) Methods to check if a character is a valid prefix/domain character
	// use isAlphanumeric() method to define 
	public static boolean isValidPrefixChar(char prefixChar) {
		char c = prefixChar;
		if(isAlphanumeric(c)==true ||(c=='.')||(c=='_')||(c=='-')) {
			return true;
		}else {
			return false;
		}				
	}	
	
	public static boolean isValidDomainChar(char domainChar) {
		char c = domainChar;
		if(isAlphanumeric(c)==true ||(c=='.')||(c=='-')) {
			return true;
		}else {
			return false;
		}	
	}	
	
	//1c) Method to check if a String contains exactly one `@'
	
	public static boolean exactlyOneAt(String oneAt) {
		//create counter to count '@'
		int counter =0;
		for(int i = 0; i < oneAt.length(); i++) {
			char c = oneAt.charAt(i);
			if(c=='@') {
				++counter;
			}
		}
		if (counter ==1) {
			return true;
		}else {
			return false;
		}
		//System.out.print("The number of "+"@" +" is "+ counter);
	}
	
	//1d) Method to get the prefix of a possible email address
	
	public static String getPrefix(String preFix) {
		String newPrefix = new String();
		for(int i = 0; i < preFix.length(); i++) {
			char c = preFix.charAt(i);
			if(c=='@') {
				//System.out.println(preFix.substring(0,i));
				newPrefix=preFix.substring(0,i);
				break;
			}
		}
		return newPrefix;
	}
	
	//1e) Method to get the domain of a possible email address
	public static String getDomain(String domainStr) {
		String newDomanStr = new String();
		for(int i = 0; i < domainStr.length(); i++) {
			char c = domainStr.charAt(i);
			if(c=='@') {
				//System.out.println(domainStr.substring((i+1),domainStr.length()));
				//System.out.println("getDomain i " + i);
				newDomanStr = domainStr.substring((i+1),domainStr.length());
				break;
			}
		}
		return newDomanStr;
	}
	
	//1f) Methods to check if the prefix and the domain are valid
	
	public static boolean isValidPrefix(String prefixString) {
		boolean success = false;
		char[] chArry = prefixString.toCharArray();
		int lastCh = prefixString.length()-1;
		// condition-A) the first and last character must be alphanumeric
		if(isAlphanumeric(chArry[0])==false || isAlphanumeric(chArry[lastCh])==false) {
			return success; 
		// condition-B) cannot be empty, contain at lease one character
		}else if(prefixString == " "){
			return success;
		}else{
		// condition-C) an underscore, a period, a dash must follow by one alphanumeric
			for (int i=0; i <prefixString.length(); i++) {
				if(isValidPrefixChar(chArry[i])== false) {
					return false;
				}else if(isValidPrefixChar(chArry[i])== true){
					success =true;
					if(chArry[i]=='.' || chArry[i]=='-' || chArry[i]=='_') {
						int secondCheck =(i+1);
						if(isAlphanumeric(chArry[secondCheck])==true){
							//System.out.println("secondcheck" + i);
							success =true;
						}else{
							return false;
					}
				
				}
			}
		}
		}
		return success;
	}
	
	public static boolean isValidDomain(String validDomainString) {
		boolean success = false;
		char[] chArry = validDomainString.toCharArray();
		int lastCh = validDomainString.length()-1;
		// condition-A)&B) first portion contains at least one character, the second portion contains at least two characters
		if((isAlphanumeric(chArry[0])==isAlphanumeric(chArry[lastCh])==isAlphanumeric(chArry[lastCh-1])==false)) {
			//System.out.println("chArrycheck" + (lastCh-1));
			return success;
		}else if(validDomainString == " "){
			return success;
		}else{
		// condition-C) the whole string contains only alphanumeric, dash or period.  
			for (int i=0; i <validDomainString.length(); i++) {
				if(isValidDomainChar(chArry[i])==false) {
					//System.out.println("isValidDomainChar false");
					return success;
			}else if(isValidDomainChar(chArry[i])== true) {
		// condition-D) find the period to separate the string to two portion. a period or a dash must follow by one or more alphanumeric
				if(chArry[i] =='.' && chArry[i+1]=='.'){
					return success;
				}else if(chArry[i] =='.' && chArry[i+1]=='-') {
					return success;
				}else if(chArry[i] =='-' && chArry[i+1]=='-') {
					return success;
				}else if(chArry[i] =='-' && chArry[i+1]=='.') {
					return success;
				}else if(chArry[i]=='.') {
					int checkPoint=i;
					for(int j=checkPoint+1; j<=lastCh; j++) {
						if(isValidDomainChar(chArry[j])==true && !(validDomainString.charAt(j)>='0' && validDomainString.charAt(j)<='9')) {
							//System.out.println("checkPoint " + j +" lastCh " + lastCh + " i :" + i);
							success = true;
						}else {
							//System.out.println("else");
							return false;
						}
					}
					
				}
			}
			}
		}
			
		
		return success;
	}
	//1g) Methods to check if a string is a valid email address
	
	public static boolean isValidEmail(String validEmailString) {
		boolean success = false;
		if(exactlyOneAt(validEmailString) ==true) {
			//System.out.println("isValidEmail @ " + exactlyOneAt(validEmailString));	
			String prefix = getPrefix(validEmailString);
			String domain= getDomain(validEmailString);
			//System.out.println(prefix + " "+ domain);
			if(isValidPrefix(prefix)==true && isValidDomain(domain) ==true ) {
				success =true;			
			}else {
				return false;
			}
		}else{
			return false;			
		}return success;
		
	}
	//test the examples and print out the result. 
	public static void main(String[] args) {
		///*
		Scanner sc = new Scanner(System.in);
		System.out.print("isValidEmail :");
		String email= sc.nextLine();
		System.out.print("return "+ isValidEmail(email));
		
		sc.close();
		//*/
		//test at every method
		/*
		char alphabumeric = '#';
		System.out.println("isAlphanumeric "+"'" +alphabumeric + "'"+" returns : " + isAlphanumeric(alphabumeric));
		
		char prefixChar = '-';
		System.out.println("isValidPrefixChar "+"'" +prefixChar + "'"+" returns : " + isValidPrefixChar(prefixChar) );
		
		char domainChar = '.';
		System.out.println("isValidDomainChar "+"'" +domainChar + "'"+" returns : " +isValidDomainChar(domainChar) );
		
		String oneAt = "@ppl@";
		System.out.println("exactlyOneAt "+"'" +oneAt + "'"+" returns : " +exactlyOneAt(oneAt));
		

		String preFix = "cats 32b@nd dogs";
		System.out.println("getPrefix "+"'" +preFix + "'"+" returns : " + getPrefix(preFix));
		
		String domainStr = "cats 32b@nd dogs";
		System.out.println("getDomain "+"'" +domainStr + "'"+" returns : " + getDomain(domainStr) );
		
		// create a list of prefix, domain and email address, and test
		System.out.println();
		List<String> prefixToValid = new ArrayList<String>();
		prefixToValid.add("abc-d");
		prefixToValid.add("abc.def");
		prefixToValid.add("abc");
		prefixToValid.add("abc_def");
		prefixToValid.add("abc-");
		prefixToValid.add(".abc");
		prefixToValid.add("abc#def");
		prefixToValid.add("abc def");
		prefixToValid.add("mail.cc");
		prefixToValid.add("abc..d");
		prefixToValid.add("abc#d");
		
		for (String value : prefixToValid) {
	           System.out.println("isValidPrefix " +"(" + value + ")"+ " is " + isValidPrefix(value));
	       }
		
		System.out.println();
		List<String> domainToValid = new ArrayList<String>();
	       // valid email addresses
		domainToValid.add("mail.cc");
		domainToValid.add("mail-archive.com");
		domainToValid.add("mail.org");
		domainToValid.add("mail.mcgill.ca");
		domainToValid.add("ma-il.com");
		domainToValid.add("abc-def.ghi");
		
	       //invalid email addresses
		domainToValid.add("mail.c");
		domainToValid.add("mail#archive.com");
		domainToValid.add("mail");
		domainToValid.add("mail..com");
		domainToValid.add(".com");
		domainToValid.add("mail.c9");
		domainToValid.add("mail.8s");
		domainToValid.add("mail.ab0v");
		domainToValid.add("abc..d");
		domainToValid.add(".com.com");

		for (String value : domainToValid) {
	           System.out.println("isValidDomain " + "(" + value + ")" + " is " + isValidDomain(value));
	       }
		
		System.out.println();
		List<String> emailToValid = new ArrayList<String>();
	       // valid email addresses
		emailToValid.add("abc..def@mail.com");
		emailToValid.add("abc#def@mail.com");
		emailToValid.add("abc.def@mail");
		emailToValid.add("abc.def@mail..com");
		emailToValid.add("abc_d@mail.com");
		emailToValid.add("abc.def@mail.com");
		emailToValid.add("abc@mail.com");
		emailToValid.add("abc.def@mail-archive.com");
		emailToValid.add("tzu-yang.yu@mail.mcgill.ca");
		emailToValid.add("a@b.ca");
		emailToValid.add("a@b.c");
		emailToValid.add("a@b.c1");
		emailToValid.add("-a@b.ca");
		emailToValid.add("a@b.-ca");
		emailToValid.add("a@b-.ca");
		emailToValid.add("a@b-");
		emailToValid.add("a.@b");
		
		for (String value : emailToValid) {
	           System.out.println("The Email address " +"(" + value + ")" + " is " + isValidEmail(value));
	       }
		*/
		}
	}
