package jdbc;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class JDBCCrudOperations 
{
	static Scanner sc=new Scanner(System.in);
	PreparedStatement pst;
	static Connection con;
	Statement st;
	ResultSet rs;
	public void insertData()throws SQLException
	{
		String query1 = "INSERT INTO product(name,category,price,brand,rating) VALUES (?, ?, ?, ?, ?)";
	    pst=con.prepareStatement(query1);
		for(int i=1;i<=14;i++)
		 {
		 System.out.println("Enter product Name:");
		 pst.setString(1,sc.next());
		 System.out.println("Enter product category:");
		 pst.setString(2,sc.next());
		 System.out.println("Enter product price:");
		 pst.setInt(3,sc.nextInt());
		 System.out.println("Enter product brand:");
		 pst.setString(4,sc.next());
		 System.out.println("Enter product rating:");
		 pst.setFloat(5,sc.nextFloat());
		 int rows=pst.executeUpdate();
		 if(rows>0)
		 {
			 System.out.println("Data inserted succesfully");
		 }
		 else
		 {
			 System.out.println("Data is not inserted");
		 }
		}
     }
	public void selectData() throws SQLException
		 {
		   String query2 =("Select * from product where price<=1000");
		   System.out.println("The products whose price is less than or equal to 1000 are:");
		   pst=con.prepareStatement(query2);
	       rs=pst.executeQuery(query2);
		   while(rs.next())
			{
				 System.out.println(rs.getString("name"));
			  // System.out.println(rs.getString("category"));
				 System.out.println(rs.getInt("price"));
			  // System.out.println(rs.getString("brand"));
			  // System.out.println(rs.getFloat("rating"));
				 
			}
		   System.out.println("The gadgets whose rating is greater than 4.5 are:");
           String query3 =("SELECT * FROM product WHERE category='Gadgets' AND rating>4.5");
			pst=con.prepareStatement(query3);
			rs=pst.executeQuery(query3);
			while(rs.next())
			{
			   System.out.println(rs.getString("name"));
			   System.out.println(rs.getString("category"));
			// System.out.println(rs.getInt("price"));
		    // System.out.println(rs.getString("brand"));
			   System.out.println(rs.getFloat("rating"));
			}
			
		 }
		 
	public  void updateData() throws SQLException
	{
		System.out.println("Enter no. of the product that you want to update");
		int no=sc.nextInt();
		String query4=("update product set price= ? where no="+no);
		pst=con.prepareStatement(query4);
		System.out.println("Enter price of the given no");
		pst.setInt(1,sc.nextInt());
		int rows=pst.executeUpdate();
		 if(rows!=0)
		 {
			 System.out.println("Data Updated succesfully");
		 }
		 else
		 {
			 System.out.println("Data is not updated");
		 }
	}
	public void deleteData()throws SQLException
	  {
		String query5 = "DELETE FROM product WHERE price<100";
		st = con.createStatement();
		int rows=st.executeUpdate(query5);
		 if(rows!=0)
		 {
			 System.out.println("Data deleted succesfully");
		 }
		 else
		 {
			 System.out.println("Data is not deleted");
		 }
	}
	public static void main(String[] args) 
		{
		   JDBCCrudOperations jco=new JDBCCrudOperations();
		   try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				String Url="jdbc:mysql://localhost:3306/database1";
				String Username="root";
				String Password="root";
				con=DriverManager.getConnection(Url,Username,Password);
				int choice=4;
				while(true)
				 {
				   System.out.println("Enter your choice:");
				   System.out.println("1)Insert");
			   	   System.out.println("2)Select");
				   System.out.println("3)Update");
			       System.out.println("4)Delete");
				   choice=sc.nextInt();
				   switch(choice)
					{
					  case 1:
					        jco.insertData();
						  break;
					  case 2:
					        jco.selectData();
						  break;
					  case 3:
					        jco.updateData();
						  break;
					  case 4:
					        jco.deleteData();
						  break;
					 }
				  }
			}
			catch(ClassNotFoundException |SQLException e)
			{
				e.printStackTrace();

			}
			finally
			{
				try
				{
					con.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}	
		    }
	}
}

/*
OutPut:
Enter your choice:
1)Insert
2)Select
3)Update
4)Delete
1
Enter product Name:
BlueShirt
Enter product category:
Clothing
Enter product price:
750
Enter product brand:
Denim
Enter product rating:
3.8
Data inserted succesfully
Enter product Name:
BlueJeans
Enter product category:
Clothing
Enter product price:
800
Enter product brand:
Puma
Enter product rating:
3.6
Data inserted succesfully
Enter product Name:
BlackJeans
Enter product category:
Clothing
Enter product price:
750
Enter product brand:
Denim
Enter product rating:
4.5
Data inserted succesfully
Enter product Name:
BlueShirt
Enter product category:
Clothing
Enter product price:
1000
Enter product brand:
Puma
Enter product rating:
4.3
Data inserted succesfully
Enter product Name:
ChocolateCake
Enter product category:
Food
Enter product price:
25
Enter product brand:
Britannia
Enter product rating:
3.7
Data inserted succesfully
Enter product Name:
StrawberryCake
Enter product category:
Food
Enter product price:
60
Enter product brand:
Cadbury
Enter product rating:
4.1
Data inserted succesfully
Enter product Name:
ChocolateCake
Enter product category:
Food
Enter product price:
60
Enter product brand:
Cadbury
Enter product rating:
2.5
Data inserted succesfully
Enter product Name:
StrawberryCake
Enter product category:
Food
Enter product price:
10
Enter product brand:
Britannia
Enter product rating:
4.6
Data inserted succesfully
Enter product Name:
SmartWatch
Enter product category:
Gadgets
Enter product price:
17000
Enter product brand:
Apple
Enter product rating:
4.9
Data inserted succesfully
Enter product Name:
SmartCam
Enter product category:
Gadgets
Enter product price:
2600
Enter product brand:
Realme
Enter product rating:
4.7
Data inserted succesfully
Enter product Name:
SmartTV
Enter product category:
Gadgets
Enter product price:
40000
Enter product brand:
Sony
Enter product rating:
4.7
Data inserted succesfully
Enter product Name:
SmartBand
Enter product category:
Gadgets
Enter product price:
3000
Enter product brand:
Realme
Enter product rating:
4.6
Data inserted succesfully
Enter product Name:
RawCashew
Enter product category:
Food
Enter product price:
370
Enter product brand:
Absa
Enter product rating:
3.9
Data inserted succesfully
Enter product Name:
CashewNuts
Enter product category:
Food
Enter product price:
550
Enter product brand:
Upcrop
Enter product rating:
4.3
Data inserted succesfully
Enter your choice:
1)Insert
2)Select
3)Update
4)Delete
2
The products whose price is less than or equal to 1000 are:
BlueShirt
750
BlueJeans
800
BlackJeans
750
BlueShirt
1000
ChocolateCake
25
StrawberryCake
60
ChocolateCake
60
StrawberryCake
10
RawCashew
370
CashewNuts
550
The gadgets whose rating is greater than 4.5 are:
SmartWatch
Gadgets
4.9
SmartCam
Gadgets
4.7
SmartBand
Gadgets
4.6
Enter your choice:
1)Insert
2)Select
3)Update
4)Delete
3
Enter no. of the product that you want to update
9
Enter price of the given no
15000
Data Updated succesfully
Enter your choice:
1)Insert
2)Select
3)Update
4)Delete
4
Data deleted succesfully
Enter your choice:
1)Insert
2)Select
3)Update
4)Delete

 */
