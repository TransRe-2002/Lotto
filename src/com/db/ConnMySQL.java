package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;

import com.model.Forecast;
import com.model.History;
public class ConnMySQL
{
    private final String dbDriver = "com.mysql.jdbc.Driver";//
    //
    private static final String URL = "jdbc:mysql://localhost:3307/db_lottery";
    private static final String USERNAME = "root";      //
    private static final String PASSWORD = "2718281828";//
    private static Connection con = null;
    public ConnMySQL()
    {
        try
        {
            Class.forName(dbDriver);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            System.out.println("数据库加载失败");
        }
    }
    public static boolean creatConnection()
    {
        try
        {
            //
            con = (Connection) DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return true;
    }
    public void closeConnection()       //
    {
        if (con != null)                //
        {
            try
            {
                con.close();            //
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            finally
            {
                con = null;             //
            }
        }
    }
    public ResultSet showAll(String sql)//
    {
        Statement statement = null;
        if (con == null)
        {
            creatConnection();
        }
        try
        {
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            return rs;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public int getABC(String abc, int number)
    {
        String sql = "select count(" + abc + ") from tb_history where " + abc + "=" + number;
        Statement statement = null;
        int i = 0;
        if (con == null)
            creatConnection();
        try
        {
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                i = rs.getInt(1);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            closeStatement(statement);
        }
        return i;
    }
    public static void closeStatement(Statement stat)
    {
        if (stat != null)
        {
            try
            {
                stat.close();
            }
            catch (SQLException e)
            {
                System.err.println("关闭数据库语句异常");
                e.printStackTrace();
            }
        }
    }

    public static List<History> getFirstTenData()       //
    {
        //
        String sql = "SELECT * FROM tb_history ORDER BY number DESC LIMIT 10";
        List<History> list = new ArrayList<>();         //
        Statement statement = null;                     //
        if (con == null)
        {
            creatConnection();
        }
        try
        {
            statement = con.createStatement();          //
            ResultSet rs = statement.executeQuery(sql); //
            while (rs.next())                           //
            {
                History history = new History();             //
                history.setNumber(rs.getInt(2));    //
                history.setA(rs.getInt(3));
                history.setB(rs.getInt(4));
                history.setC(rs.getInt(5));
                history.setD(rs.getInt(6));
                history.setE(rs.getInt(7));
                history.setF(rs.getInt(8));
                history.setG(rs.getInt(9));
                list.add(history);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            closeStatement(statement);
        }
        return list;                                        //
    }

    public int selectNumber(String sql) //
    {
        Statement statement = null;     //
        int i = 10001;                  //
        if (con == null)                //
        {
            creatConnection();          //
        }
        try
        {
            statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                i = rs.getInt(1);   //
            }
        }
        catch (SQLException e)
        {
            System.out.println("历史开奖号码添加失败！");
            e.printStackTrace();
        }
        finally
        {
            closeStatement(statement);
        }
        return i;
    }

    public Boolean addForecast(Forecast fr) //
    {
        if (con == null)            //
        {
            creatConnection();      //
        }
        try
        {
            PreparedStatement statement = con.prepareStatement("insert into tb_forecast(number,a,b,c,d,e,f,g,forecasttime) "
            + "values(?,?,?,?,?,?,?,?,?)");
            statement.setInt(1, fr.getNumber());
            statement.setInt(2, fr.getA());
            statement.setInt(3, fr.getB());
            statement.setInt(4, fr.getC());
            statement.setInt(5, fr.getD());
            statement.setInt(6, fr.getE());
            statement.setInt(7, fr.getF());
            statement.setInt(8, fr.getG());
            statement.setString(9, fr.getForecasttime());
            statement.executeUpdate();      //
            return true;
        }
        catch (SQLException e)
        {
            System.out.println("机选号码添加失败！");
            e.printStackTrace();
            return false;
        }
    }
}
