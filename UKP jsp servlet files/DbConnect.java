package db;
import java.io.InputStream;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DbConnect{
    private Connection con;
    private Statement st;
    private PreparedStatement
            adminall,insertinStock,updateStock,checkinStock,insertData,insertinWarehouse,getUser,checkUser,validateUser,
            totalall,totalwise,insertUser,admintotal,getStock,getphoto,insertPhoto,getUidPhoto;
    public DbConnect(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","teksystem","teksystem");
            st=con.createStatement();
            
            getUidPhoto = con.prepareStatement("select userid from users where email=?");
            getUser=con.prepareStatement("select * from Users where email=?");
            checkUser=con.prepareStatement("select * from Users where email=? and pass=?");
            validateUser=con.prepareStatement("select * from Users where EMAIL=?");
            insertUser=con.prepareStatement("insert into Users (EMAIL,MOBILE,PASS,NAME,AGE) values (?,?,?,?,?)");
            insertPhoto=con.prepareStatement("insert into photo values(?,?)");
           
            insertinStock=con.prepareStatement("insert into Books (Bookname,Stock) values (?,?)");
            checkinStock=con.prepareStatement("Select * from Books where Bookname = ?");
            updateStock=con.prepareStatement("update Books set stock= stock + ? where bid = ?");
            getStock = con.prepareStatement("select stock from books where bid = ?");
            
            
            insertinWarehouse=con.prepareStatement("insert into Warehouse(Bookname,Bid,userid,Quantity) values (?,?,?,?)");
            
            totalall=con.prepareStatement("select SUM(Quantity) from Warehouse where Userid=?");
            totalwise=con.prepareStatement("select BOOKNAME,SUM(Quantity) from Warehouse where Userid=? group by BOOKNAME");
            
            adminall=con.prepareStatement("select * from Books order by bid");
            admintotal = con.prepareStatement("select SUM(STOCK) from BOOKS");
            
            getphoto=con.prepareStatement("select photo from photo where userid=?");
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public ResultSet checkUser(String e,String p){
        try{
            checkUser.setString(1,e);
            checkUser.setString(2, p);
            ResultSet rs=checkUser.executeQuery();
            return rs;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public String getUser(String e){
        try{
            getUser.setString(1,e);
            ResultSet rs=getUser.executeQuery();
            if(rs.next())
                return rs.getString(4);
            else
                return null;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public int getUidPhoto(String e){
        try{
            getUidPhoto.setString(1,e);
            ResultSet rs=getUidPhoto.executeQuery();
            if(rs.next())
                return rs.getInt(1);
            else
                return 0;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return 0;
        }
    }
    
    public String validateUser(String e){
        try{
            validateUser.setString(1,e);
            ResultSet rs=validateUser.executeQuery();
            if(rs.next())
                return "Email Already Exist!";
            else
                return "Done";
        }
        catch(Exception ex){
            ex.printStackTrace();
            return ""+ex.getMessage();
        }
    }
    
    public String setUser(String n,String e,long m,int a,String p){
        try{
            insertUser.setString(1,e);
            insertUser.setLong(2,m);
            insertUser.setString(3,p);
            insertUser.setString(4,n);
            insertUser.setInt(5,a);
            int x=insertUser.executeUpdate();
            if(x==1){
                return "Done";
            }else{
                return "Something Went Wrong, Please try again";
            }
        }
        catch(Exception e1){
            e1.printStackTrace();
            return ""+e1.getMessage();
        }
    }
    
    public ResultSet checkinStock(String bn){
        try{
            checkinStock.setString(1,bn);
            ResultSet rs=checkinStock.executeQuery();
            return rs;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public String insertinStock(String bn,int s){
        try{
            insertinStock.setString(1,bn);
            insertinStock.setInt(2,s);
            int x=insertinStock.executeUpdate();
            if(x==1){
                return "Done";
            }else{
                return "Something went wrong, please try again";
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
    public String updateStock(int s, int bid){
        try{
            updateStock.setInt(1,s);
            updateStock.setInt(2, bid);
            int x=updateStock.executeUpdate();
            if(x==1){
                return "Done";
            }else{
                return "Something went wrong, please try again";
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
    public String insertinWarehouse(String bn,int bid,int uid,int q){
        try{
            insertinWarehouse.setString(1,bn);
            insertinWarehouse.setInt(2,bid);
            insertinWarehouse.setInt(3,uid);
            insertinWarehouse.setInt(4,q);
            int x=insertinWarehouse.executeUpdate();
            if(x==1){
                return "Done";
            }else{
                return "Something Went Wrong, Please try again";
            }
        }
        catch(Exception e1){
            e1.printStackTrace();
            return e1.getMessage();
        }
    }
    public ResultSet totalall(int uid){
        try{
            totalall.setInt(1,uid);
            ResultSet rs=totalall.executeQuery();
            return rs;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public ResultSet totalwise(int uid){
        try{
            totalwise.setInt(1,uid);
            ResultSet rs=totalwise.executeQuery();
            return rs;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public ResultSet adminall(){
        try{
            ResultSet rs=adminall.executeQuery();
            return rs;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public ResultSet admintotal(){
        try{
            ResultSet rs=admintotal.executeQuery();
            return rs;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    
    public int getStock(int bid){
        try{
            getStock.setInt(1, bid);
            ResultSet rs=getStock.executeQuery();
            if(rs.next())
                return rs.getInt(1);
            else
                return -1;
        }
        catch(Exception ex){
            ex.printStackTrace();
            return -2;
        }
    }
    
    public byte[] getphoto(int e){
        try{
            getphoto.setInt(1,e);
            ResultSet rs=getphoto.executeQuery();
            if(rs.next()){
                byte[] b=rs.getBytes("photo");
                if(b.length!=0)
                    return b;
                else
                    return null;
            }else
                return null;
        }catch(Exception ex){
            return null;
        }
    }
    
    public String insertPhoto(int uid, InputStream p){
        try {
            insertPhoto.setInt(1, uid);
            insertPhoto.setBinaryStream(2,p);
            int x=insertPhoto.executeUpdate();
            System.out.println(x);
            if(x!=0)
                return "Success";
            else
                return "failed";
            }
        catch (SQLException ex) {
                ex.printStackTrace();
                return "failed";
        }
    }
}