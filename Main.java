import java.io.Console;
 import java.util.Scanner;
public class Main {
   
    public static final String RED="\u001B[31m";
    public static final String RESET="\u001B[0m";
    public static final String BLUE="\u001B[34m";
    public static final String GREEN="\u001B[32m";
    public static final String CYAN="\u001B[36m";
    public static final String BLACK="\u001B[30m";
    public static void main(String[] args) {
        Console console = System.console();
        MembersService Uservice=new Memberserviceimplt();
        BooksService Bservice=new Bookserviceimplt();
        Scanner sc=new Scanner(System.in);
        System.out.println(BLUE+"\n----------------------------------------------------------------------------------------------");
        System.out.println("********** Welcome to Library Management Application **********"+RESET);
        System.out.println("\n----------------------------------------------------------------------------------------------"+RESET);
        System.out.println(CYAN+"     1 for ADMIN \n     2 for STUDENT");
        System.out.println("Enter your choice"+RESET);
        int result=sc.nextInt();
        switch (result){
            case 1: 
            System.out.println(CYAN+"Enter Admin username...."+RESET);
            String uname=sc.next();
            System.out.println(CYAN+"Enter Admin password...."+RESET);
            char[] password = console.readPassword();
            if(uname.equals("Admin") && (new String(password).equalsIgnoreCase("adminadmin")))
            {  System.out.println(BLUE+"\n********** Welcome to Admin Panel **********"+RESET);
                System.out.println("\nEnter your choice");  
           do{  
            System.out.println(BLUE+ "\n1.Show All Registerd User\n"+
               "2.Add Book\n"+
              "3.Show All Books\n"+
              "4.Show Available Books\n"+
              "5.Show Reserved Books\n"+
              "6.Delete Books\n"+
              "7.Delete Users\n"+
              "8.Library Report\n"+
              "9.Exit\n"
              );
              System.out.println("\nEnter Your Choice !"+RESET);
               int ch = sc.nextInt();

               switch (ch){
                case 1:
                       Uservice.showAllUsers();;
                       break;
                case 2:
                       Bservice.addBook();
                       break;
                   case 3:
                       Bservice.showAllBooks();
                       break;
                   case 4:
                       Bservice.showAllAvailableBooks();
                       break;
                   case 5:
                       Bservice.showAllReservedBooks();
                       break;
                   case 6:
                       Bservice.DeleteBook();
                       break;
                   case 7:
                       Uservice.Deleteuser();
                       break;
                       case 8:
                       Bservice.libraryreport();
                       break;
                   case 9:
                   System.out.println(BLUE+"\n----------------------------------------------------------------------------------------------");
                       System.out.println("          Thank you for Using Application !!");
                       System.out.println("\n----------------------------------------------------------------------------------------------"+RESET);
                       System.exit(0);
                       break;
                   default:
                       System.out.println(RED+"Please Enter Valid Choice !"+RESET);
               }
           }
           while(true);
        }
        else {
            System.out.println(RED+"Sorry.... You Enter wrong user name or password!!"+RESET);
        }
        break;
           
            case 2:
            System.out.println(BLUE+"********** Welcome to Student Panel **********"+RESET);
            do{  System.out.println(BLUE+
                  
               "\n1.User Registration\n"+
                "2.Check Available Books\n"+
                "3.Search Books\n"+
                "4.Borrow Books\n"+
                "5.Return Books\n"+
                "6.Exit\n");
                System.out.println("Enter Your Choice !"+RESET);
                 int ch1 = sc.nextInt();
  
                 switch (ch1){
                  case 1:
                         Uservice.registerUser();
                         break;
                  case 2:
                         Bservice.showAllAvailableBooks();
                         break;
                     case 3:
                         Bservice.searchbook();
                         break;
                     case 4:
                         Bservice.borrowBook();
                         break;
                         case 5:
                         Bservice.returnBook();
                         break;
                     case 6:
                     System.out.println(BLUE+"\n----------------------------------------------------------------------------------------------");
                         System.out.println("Thank you for Using Application !!");
                         System.out.println("\n----------------------------------------------------------------------------------------------"+RESET);
                         System.exit(0);
                         break;
                     default:
                         System.out.println(RED+"Please Enter Valid Choice !"+RESET);
                 }
             }
             while(true);
             case 3:
                         System.exit(0);
                         break;
                     default:
                         System.out.println(RED+"Please Enter Valid Choice !"+RESET);
        }
}
}