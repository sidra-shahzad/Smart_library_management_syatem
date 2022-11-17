import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import java.time.LocalDate;

//import java.util.Date;;

public class Bookserviceimplt implements BooksService {
    public static final String RED="\u001B[31m";
    public static final String RESET="\u001B[0m";
    public static final String BLUE="\u001B[34m";
    public static final String GREEN="\u001B[32m";
    public static final String CYAN="\u001B[36m";
    public static final String BLACK="\u001B[30m";
    Scanner sc=new Scanner(System.in);
    Validator validator=new Validator();
    static final String DB_URL="jdbc:mysql://localhost/project";
    static final String User="root";
    static final String Pass="malamala124";


    //addbook function
    @Override
    public void addBook()
    {
        String bookid= validator.validateId();
        boolean flag=false;
        try(Connection conn = DriverManager.getConnection(DB_URL, User, Pass);
        Statement stmt=conn.createStatement();)
     { PreparedStatement p=null;
         String sql2="Select * from Books where bookid='"+bookid+"'";
         p=conn.prepareStatement(sql2);
    ResultSet re;
    re=p.executeQuery();
    while (re.next())
    {
     String  id = re.getString("bookid");
            if (bookid.equals(id)){
                flag=true;
                System.out.println(RED+"The Book of this Id already exist in the Library"+RESET);
            }
    } 
     }catch (SQLException e)
     {
     e.printStackTrace();
     }   
if (flag==false){
        String Author=validator.validateAuthorTitle("Author");
        String Title=validator.validateAuthorTitle("Title");
        String year=validator.validatePublishYear();
         String status="Available";
         try(Connection conn = DriverManager.getConnection(DB_URL, User, Pass);
       Statement stmt=conn.createStatement();)
       {
        String sql1="Insert into Books (bookid, title, author, pubyear, status) values ('"+bookid+"','"+Title+"', '"+Author+"', '"+year+"', '"+status+"')";
        stmt.executeUpdate(sql1);
        System.out.println(GREEN+"Book Added Successfully !!!"+RESET);
        System.out.println("\n----------------------------------------------------------------------------------------------");
    }
    catch (SQLException e)
{
e.printStackTrace();
}   
}
    }


    //show all books whether reserved or returned function
    @Override
    public void showAllBooks() {
        boolean flag=false;
        try(Connection conn = DriverManager.getConnection(DB_URL, User, Pass);
           Statement stmt=conn.createStatement();)
        { PreparedStatement p=null;
            String sql2="Select * from Books";
            p=conn.prepareStatement(sql2);
       ResultSet re;
       re=p.executeQuery();
      System.out.println("-----------------------------------------------------------------------------------------------------------------");
     System.out.println(GREEN+"Bookid                  Title                  Author                  Pub Year                  Status"+RESET);
     System.out.println("-------------------------------------------------------------------------------------------------------------------");
       while (re.next())
       {
        String  id = re.getString("bookid");
                String author = re.getString("author");
                String title = re.getString("title");
                String pubyr = re.getString("pubyear");
                String status = re.getString("status");
flag=true;
                System.out.println(id+ "                  " +title+ "                  " +author+ "                  " +pubyr+ "                  "+status); 
       } 
       System.out.println("-------------------------------------------------------------------------------------------------------------------");
    
        }
        catch( SQLException e ) 
   {
    System.out.println(e); 
   }   
       if(flag==false)
           System.out.println(RED+"There are no Books in Library"+RESET);
    }



    //show available books function
    @Override
    public void showAllAvailableBooks(){
        boolean flag=false;
           try(Connection conn = DriverManager.getConnection(DB_URL, User, Pass);
           Statement stmt=conn.createStatement();)
        { PreparedStatement p=null;
            String sql2="Select * from Books where status='Available'";
            p=conn.prepareStatement(sql2);
       ResultSet re;
       re=p.executeQuery();
      System.out.println("----------------------------------------------------------------------------------------------");
     System.out.println(GREEN+"Bookid          Title           Author          Pub Year          Status"+RESET);
     System.out.println("----------------------------------------------------------------------------------------------");
       while (re.next())
       {
        int  id = re.getInt("bookid");
                String author = re.getString("author");
                String title = re.getString("title");
                String pubyr = re.getString("pubyear");
                String status = re.getString("status");
flag=true;
                System.out.println(id+ "          " +title+ "          " +author+ "          " +pubyr+ "          "+status); 
       } 
       System.out.println("----------------------------------------------------------------------------------------------");
    
        }
        catch( SQLException e ) 
   {
    System.out.println(e); 
   }   
        if(flag==false)
            System.out.println(RED+"There are no books with status Available"+RESET);

    }


    //show reserved books function 
    @Override
    public void showAllReservedBooks(){
        boolean flag=false;
        try(Connection conn = DriverManager.getConnection(DB_URL, User, Pass);
        Statement stmt=conn.createStatement();)
     { PreparedStatement p=null;
         String sql2="Select * from reserved where status='Reserved'";
         p=conn.prepareStatement(sql2);
    ResultSet re;
    re=p.executeQuery();
   System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
  System.out.println(GREEN+"Userid        Username         Department        Useremail        Bookid        Booktitle        issuedate        status        returndate"+RESET);
  System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
    while (re.next())
    {
     String   uid = re.getNString("userid");
     String   uname = re.getNString("username");
     String   udep = re.getNString("department");
     String   uemail = re.getNString("email");
             String bid = re.getString("bookid");
             String title = re.getString("bookname");
            String idate=re.getString("issuedate");
            String status=re.getString("status");
            String rdate=re.getString("returndate");
             flag=true;
             System.out.println(uid+ "        " +uname+ "        " +udep+ "        " +uemail+ "        "+bid+ "        " +title+ "        "+idate+"        " +status+ "        "+rdate); 
            ; 
    } 
    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
 
     }
     catch( SQLException e ) 
{
 System.out.println(e); 
}   
     if(flag==false)
         System.out.println(RED+"There are no books with status Reserved"+RESET);

    }

    //function to borrow the book or reserve the book
 @Override
     public void borrowBook(){
        String UID=validator.validateUId();
        boolean user=false;
        try(Connection conn = DriverManager.getConnection(DB_URL, User, Pass);
               Statement stmt=conn.createStatement();)
            { PreparedStatement p=null;
                String sql2="Select * from Users where userid='"+UID+"'";
                p=conn.prepareStatement(sql2);
           ResultSet re;
           re=p.executeQuery();
           while (re.next())
           {
            String  id = re.getString("userid");
            String uname=re.getString("username");
            String dept=re.getString("department");
            String uemail=re.getString("useremail");

                   if (UID.equals(id)){
                       user=true;
                       String bookid= validator.validateId(); 
                       boolean flag=false;
                       boolean cond=false;
        
                       try(Connection conn1 = DriverManager.getConnection(DB_URL, User, Pass);
                       Statement stmt1=conn1.createStatement();)
                    { PreparedStatement p1=null;
                        String sql3="Select * from Books where bookid='"+bookid+"'";
                        p1=conn1.prepareStatement(sql3);
                   ResultSet re1;
                   re1=p1.executeQuery();
                   while (re1.next())
                   {
                    String  bid = re1.getString("bookid");
                    String bname=re1.getString("title");
                           if (bookid.equals(bid)){
                               cond=true;
                               try(Connection conn2 = DriverManager.getConnection(DB_URL, User, Pass);
                               Statement stmt2=conn2.createStatement();)
                            { PreparedStatement p2=null;
                                String sql1="Select * from Books where bookid='"+bookid+"'";
                                p2=conn2.prepareStatement(sql1);
                           ResultSet re2;
                           re2=p2.executeQuery();
                           while (re2.next())
                           {
                            String  id1 = re2.getString("bookid");
                                    String status = re2.getString("status");
                                    
                         if(id1.equalsIgnoreCase(bookid)&&(status.equalsIgnoreCase("Available")))
                         {flag=true;
                            System.out.println(GREEN+"Book Borrow Successfully"+RESET);
                            try(Connection conn3 = DriverManager.getConnection(DB_URL, User, Pass);
                       Statement stmt3=conn3.createStatement();)
                       {
                        String sql="Update Books set status='Reserved' where bookid="+bookid+"";
                        stmt3.executeUpdate(sql);
                        System.out.println(GREEN+"Book status updated successfully........"+RESET);

                        try(Connection conn4 = DriverManager.getConnection(DB_URL, User, Pass);
                        Statement stmt4=conn4.createStatement();)
                        {
                         String sql4="Insert into reserved (userid, username, department, email, bookid, bookname, issuedate, status) values ('"+UID+"','"+uname+"','"+dept+"','"+uemail+"','"+bid+"','"+bname+"', curdate(), 'Reserved')";
                         stmt4.executeUpdate(sql4);
                         System.out.println(GREEN+"Book RESERVED successfully........"+RESET);
                     }

                              catch( SQLException e ) 
                              {
                               System.out.println(e); 
                              }   
                    }
                             catch( SQLException e ) 
                             {
                              System.out.println(e); 
                             }   
                         }
                         if(flag==false)
                             System.out.println(RED+"This book is not available to borrow"+RESET);
                         
                        } } catch( SQLException e ) 
                             {
                              System.out.println(e); 
                             }   
                           }    
                   } 
                   if(cond==false)
                           {
                               System.out.println(
                                 RED+  "Sorry.... the book of this Id is not in the Library"
                               +RESET);
                           } 
                    }catch (SQLException e)
                    {
                    e.printStackTrace();
                    }  
                    }   
                    }if(user==false)
                    {
                        System.out.println(
                          RED+  "Sorry.... You are not a registered user\n First register yourself"
                        +RESET);
                    } 
                }catch (SQLException e)
                    {
                    e.printStackTrace();
                    }
                }

    
// function to return the book and make book available for reservation
    @Override
    public void returnBook()
    {
        String UID=validator.validateUId();
        boolean user=false;
        try(Connection conn = DriverManager.getConnection(DB_URL, User, Pass);
               Statement stmt=conn.createStatement();)
            { PreparedStatement p=null;
                String sql2="Select * from Users where userid='"+UID+"'";
                p=conn.prepareStatement(sql2);
           ResultSet re;
           re=p.executeQuery();
           while (re.next())
           {
            String  id = re.getString("userid");
                   if (UID.equals(id)){
                       user=true;
                       String bookid= validator.validateId(); 
                       boolean flag=false;
                       boolean cond=false;
        
                       try(Connection conn1 = DriverManager.getConnection(DB_URL, User, Pass);
                       Statement stmt1=conn1.createStatement();)
                    { PreparedStatement p1=null;
                        String sql3="Select * from Books where bookid='"+bookid+"'";
                        p1=conn1.prepareStatement(sql3);
                   ResultSet re1;
                   re1=p1.executeQuery();
                   while (re1.next())
                   {
                    String  bid = re1.getString("bookid");
                           if (bookid.equals(bid)){
                               cond=true;
                               try(Connection conn2 = DriverManager.getConnection(DB_URL, User, Pass);
                               Statement stmt2=conn2.createStatement();)
                            { PreparedStatement p2=null;
                                String sql1="Select * from reserved  where bookid='"+bookid+"'";
                                p2=conn2.prepareStatement(sql1);
                           ResultSet re2;
                           re2=p2.executeQuery();
                           while (re2.next())
                           {
                            String  id1 = re2.getString("bookid");
                                    String status = re2.getString("status");
                         if(id1.equalsIgnoreCase(bookid)&&(status.equalsIgnoreCase("Reserved")))
                         {flag=true;
                            System.out.println(GREEN+"Book Return Successfully"+RESET);
                            try(Connection conn3 = DriverManager.getConnection(DB_URL, User, Pass);
                       Statement stmt3=conn3.createStatement();)
                       {
                        String sql="Update Books set status='Available' where bookid="+bookid+"";
                        stmt3.executeUpdate(sql);
                        System.out.println(GREEN+"Book status updated successfully........"+RESET);
                        try(Connection conn4 = DriverManager.getConnection(DB_URL, User, Pass);
                        Statement stmt4=conn4.createStatement();)
                        {
                         String sql4="Update reserved set returndate=curdate(), status='Returned' where bookid='"+bookid+"'";
                         stmt4.executeUpdate(sql4);
                         System.out.println(GREEN+"Book RETURNED successfully........"+RESET);
                     }  catch( SQLException e ) 
                              {
                               System.out.println(e); 
                              }   

                    }
                             catch( SQLException e ) 
                             {
                              System.out.println(e); 
                             }   
                         }
                         if(flag==false)
                             System.out.println(RED+"This book cannot be borrow"+RESET);
                         
                        } } catch( SQLException e ) 
                             {
                              System.out.println(e); 
                             }   
                           }    
                   } 
                   if(cond==false)
                           {
                               System.out.println(
                                 RED+  "Sorry.... the book of this Id is not in the Library"
                               +RESET);
                           } 
                    }catch (SQLException e)
                    {
                    e.printStackTrace();
                    }  
                    
                    }   
                    }if(user==false)
                    {
                        System.out.println(
                          RED+  "Sorry.... You are not a registered user\n First register yourself"
                        +RESET);
                    } 
                }catch (SQLException e)
                    {
                    e.printStackTrace();
                    }
                }



        //function to delete the boook if not required
        @Override
           public void DeleteBook(){
               String bid= validator.validateId();
               boolean flag=false;
               try(Connection conn = DriverManager.getConnection(DB_URL, User, Pass);
        Statement stmt=conn.createStatement();)
     { PreparedStatement p=null;
         String sql2="Select * from Books where bookid='"+bid+"'";
         p=conn.prepareStatement(sql2);
    ResultSet re;
    re=p.executeQuery();
    while (re.next())
    {
     String  id = re.getString("bookid");
            if (bid.equals(id)){
                flag=true;
                try(Connection conn1 = DriverManager.getConnection(DB_URL, User, Pass);
                Statement stmt1=conn1.createStatement();)
                {
                 String sql1="Delete from Books where bookid='"+bid+"'";
                 stmt1.executeUpdate(sql1);
                 System.out.println(GREEN+"Book deleted successfully........"+RESET);
             }
             catch (SQLException e)
         {
         e.printStackTrace();
         }   
            }}
            if (flag==false)
{
    System.out.println(RED+"There is no Book With This Id In the library"+RESET);
}
     }catch (SQLException e)
     {
     e.printStackTrace();
     }   
           }

// function to search the book 
           @Override
           public void searchbook()
           {Boolean flag=false;
            String bid= validator.validateId();
            try(Connection conn = DriverManager.getConnection(DB_URL, User, Pass);
            Statement stmt=conn.createStatement();)
         { PreparedStatement p=null;
             String sql2="Select * from Books where bookid='"+bid+"'";
             p=conn.prepareStatement(sql2);
        ResultSet re;
        re=p.executeQuery();
        System.out.println("----------------------------------------------------------------------------------------------");
  System.out.println(GREEN+"Bookid          Title           Author          Pub Year          Status"+RESET);
  System.out.println("----------------------------------------------------------------------------------------------");
        while (re.next())
        {
         String  id = re.getString("bookid");
                 String author = re.getString("author");
                 String title = re.getString("title");
                 String pubyr = re.getString("pubyear");
                 String status = re.getString("status");
                 flag=true;
      if(id.equalsIgnoreCase(bid)){
        System.out.println(id+ "          " +title+ "          " +author+ "          " +pubyr+ "          "+status);
      }
    }
    System.out.println("----------------------------------------------------------------------------------------------"); }        
    catch (SQLException e)
           {
           e.printStackTrace();
           } 
           if(flag==false) 
           {System.out.println(RED+"There is No book Available with the given Bookid!!"+RESET);}
           }


// function to generate library report to check which book is reserved and when
//and which book returned and when and by which userid
           @Override
           public void libraryreport()
           {
            try(Connection conn = DriverManager.getConnection(DB_URL, User, Pass);
        Statement stmt=conn.createStatement();)
     { PreparedStatement p=null;
         String sql2="Select * from reserved";
         p=conn.prepareStatement(sql2);
    ResultSet re;
    re=p.executeQuery();
   System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
  System.out.println(GREEN+"Userid        Username         Department        Useremail        Bookid        Booktitle        issuedate        status        returndate"+RESET);
  System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
    while (re.next())
    {
     String   uid = re.getNString("userid");
     String   uname = re.getNString("username");
     String   udep = re.getNString("department");
     String   uemail = re.getNString("email");
             String bid = re.getString("bookid");
             String title = re.getString("bookname");
            String idate=re.getString("issuedate");
            String status=re.getString("status");
            String rdate=re.getString("returndate");
             System.out.println(uid+ "        " +uname+ "        " +udep+ "        " +uemail+ "        "+bid+ "        " +title+ "        "+idate+"        " +status+ "        "+rdate); 
            ; 
    } 
    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
 
     }
     catch( SQLException e ) 
{
 System.out.println(e); 
}    
           }
}



