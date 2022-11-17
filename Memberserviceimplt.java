
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.awt.Container;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Memberserviceimplt implements MembersService {

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
    
    //function to register the user for borrow and return book
    @Override
    public void registerUser()
    {
        String userid= validator.validateUId();
        boolean flag=false;
        try(Connection conn = DriverManager.getConnection(DB_URL, User, Pass);
        Statement stmt=conn.createStatement();)
     { PreparedStatement p=null;
         String sql2="Select * from Users where userid='"+userid+"'";
         p=conn.prepareStatement(sql2);
    ResultSet re;
    re=p.executeQuery();
    while (re.next())
    {
     String  id = re.getString("userid");
            if (userid.equals(id)){
                flag=true;
                System.out.println(RED+"The user of this Id already registered in the Library"+RESET);
            }
    } 
     }catch (SQLException e)
     {
     e.printStackTrace();
     }   
if (flag==false){
       String Uname=validator.Uname("Name");
       String depart=validator.Depart("Department");
       String email=validator.Email("Email");
       try(Connection conn = DriverManager.getConnection(DB_URL, User, Pass);
       Statement stmt=conn.createStatement();)
       {
        String sql1="Insert into Users (userid, username, department, useremail) values ('"+userid+"','"+Uname+"', '"+depart+"', '"+email+"')";
        stmt.executeUpdate(sql1);
        System.out.println(GREEN+"User registered successfully........"+RESET);
    }
    catch (SQLException e)
{
e.printStackTrace();
}   }   
    }


    //function to generate the report of all registered users
     @Override
     public void showAllUsers() {
         boolean flag=false;
         try(Connection conn = DriverManager.getConnection(DB_URL, User, Pass);
         Statement stmt=conn.createStatement();)
      { PreparedStatement p=null;
          String sql2="Select * from Users";
          p=conn.prepareStatement(sql2);
     ResultSet re;
     re=p.executeQuery();
    System.out.println("----------------------------------------------------------------------------------------------");
   System.out.println(GREEN+"Userid            Name             Departmrnt            Email"+RESET);
   System.out.println("----------------------------------------------------------------------------------------------");
     while (re.next())
     {
      String  id = re.getString("userid");
      String unme = re.getString("username");
      String udep = re.getString("department");
       String ueml = re.getString("useremail");
 flag=true;
              System.out.println(id+ "            " +unme+ "            " +udep+ "            " +ueml); 
    } 
     System.out.println("----------------------------------------------------------------------------------------------");
  
      }
      catch( SQLException e ) 
 {
  System.out.println(e); 
 }     
       if(flag==false)
           System.out.println(RED+"There are no Registered Users in Library"+RESET);
}


//function to delete the user if not required
@Override
public void Deleteuser(){
    String uid= validator.validateUId();
    boolean flag=false;
    try(Connection conn = DriverManager.getConnection(DB_URL, User, Pass);
Statement stmt=conn.createStatement();)
{ PreparedStatement p=null;
String sql2="Select * from Users where userid='"+uid+"'";
p=conn.prepareStatement(sql2);
ResultSet re;
re=p.executeQuery();
while (re.next())
{
String  id = re.getString("userid");
 if (uid.equals(id)){
     flag=true;
     try(Connection conn1 = DriverManager.getConnection(DB_URL, User, Pass);
     Statement stmt1=conn1.createStatement();)
     {
      String sql1="Delete from Users where userid='"+uid+"'";
      stmt1.executeUpdate(sql1);
      System.out.println(GREEN+"User deleted successfully........"+RESET);
  }
  catch (SQLException e)
{
e.printStackTrace();
}   
 }}
 if (flag==false)
{
System.out.println(RED+"There is no User registered With This Id In the library"+RESET);
}

}catch (SQLException e)
{
e.printStackTrace();
}   
}
}


