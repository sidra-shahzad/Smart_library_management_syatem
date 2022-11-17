
import java.util.Scanner;
import java.util.regex.Pattern;


public class Validator {

    public static final String RED="\u001B[31m";
    public static final String RESET="\u001B[0m";
    private static Pattern ID_PATTERN = Pattern.compile("^\\d{1,4}$");
    private static Pattern AuthorTitle_Pattern=Pattern.compile("^[a-zA-Z ]+$");
    private static Pattern Uname_Pattern=Pattern.compile("^[a-zA-Z ]+$");
    private static Pattern Email_Pattern=Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"); 
    private static Pattern PublishYear_Pattern=Pattern.compile("^\\d{4}$");
    Scanner sc = new Scanner(System.in);

    public String validateUId() {
        String Uid;
        while (true) {
            System.out.println("Enter User ID ");
            Uid = sc.nextLine();
            if (!ID_PATTERN.matcher(Uid).matches()) {
                System.out.println(RED+"SORRY ! PLEASE ENTER VALID User ID "+RESET);
            } else {
                break;
            }
        }
        return Uid;
    }
    

    public String validateId() {
        String bookid;
        while (true) {
            System.out.println("Enter Book ID ");
            bookid = sc.nextLine();
            if (!ID_PATTERN.matcher(bookid).matches()) {
                System.out.println(RED+"SORRY ! PLEASE ENTER VALID Book ID "+RESET);
            } else {
                break;
            }
        }
        return bookid;
    }
    
    public String Uname(String input){
        String result;
        while (true){
            if(input=="Name"){
                System.out.println("Enter Name");
            }
            result=sc.nextLine();
            if(!Uname_Pattern.matcher(result).matches()){
                System.out.println(RED+"Please Enter Valid "+input+RESET);
            }
            else{
                break;
            }

        }
        return result;
    }

    public String Depart(String input){
        String result;
        while (true){
            if(input=="Department"){
                System.out.println("Enter Department Name");
            }
            result=sc.nextLine();
            if(!Uname_Pattern.matcher(result).matches()){
                System.out.println(RED+"Please Enter Valid "+input+RESET);
            }
            else{
                break;
            }

        }
        return result;
    }

    public String Email(String input){
        String result;
        while (true){
            if(input=="Email"){
                System.out.println("Enter Email");
            }
            result=sc.nextLine();
            if(!Email_Pattern.matcher(result).matches()){
                System.out.println(RED+"Please Enter Valid "+input+RESET);
            }
            else{
                break;
            }

        }
        return result;
    }


    public String validateAuthorTitle(String input){
        String result;
        while (true){
            if(input=="Title"){
                System.out.println("Enter Title");
            }else{
                System.out.println("Enter Author");
            }
            result=sc.nextLine();
            if(!AuthorTitle_Pattern.matcher(result).matches()){
                System.out.println(RED+"Please Enter Valid "+input+RESET);
            }
            else{
                break;
            }

        }
        return result;
    }
    public String validatePublishYear(){
        String year;
        while(true){
            System.out.println("Enter Publish Year of Book ");
            year=sc.nextLine();
            if(!PublishYear_Pattern.matcher(year).matches()){
                System.out.println(RED+"Enter valid Publish Year"+RESET);
            }
            else{
                break;
            }
        }
        return year;
    }

}





