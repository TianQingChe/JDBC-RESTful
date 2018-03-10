package com.jpa.dao.movie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.jpa.model.movie.Movie;

public class MovieDao {
	public static MovieDao instance=null;
	public static MovieDao getInstance() {
		if (instance==null) {
			instance=new MovieDao();
		}
		return instance;
	}
	private MovieDao() {};//把构造函数写成private，使得外界不能直接创建MoviedDao类而只能通过静态方法来创建，这样就不用反复的创建对象
	//来获得MovieDao的功能。
	public List<Movie> findAllMovies(){
		List<Movie> movies=new ArrayList<Movie>();
		Connection conn=null;
		PreparedStatement statement=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Properties props=new Properties();
			props.setProperty("user", "root");
			props.setProperty("password", "Ok*64818503");
			props.setProperty("useSSL", "false");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/spring_2018_movie",props);
			String sql="select * from movies";
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery(sql);
			while (rs.next()) {
				String imdbId=rs.getString("imdbId");
				String title=rs.getString("title");
				String plot=rs.getString("plot");
				String poster=rs.getString("poster");
				Movie movie=new Movie(imdbId,title,plot,poster);
				movies.add(movie);
			}
			rs.close();
			statement.close();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return movies;
	}
	public Movie findMovieByImdb(String id) {
		Connection conn=null;
		PreparedStatement statement=null;
        ResultSet rs=null;
        Movie movie=null;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	String url="jdbc:mysql://localhost:3306/spring_2018_movie";
        	Properties props=new Properties();
        	props.setProperty("user", "root");
        	props.setProperty("password", "Ok*64818503");
        	props.setProperty("useSSL", "false");
        	conn=DriverManager.getConnection(url,props);
        	String sql="select * from movies where imdbId=?";
        	statement=conn.prepareStatement(sql);
        	statement.setString(1, id);
        	rs=statement.executeQuery();
        	if (rs.next()) {
				String imdbId=rs.getString("imdbId");
				String title=rs.getString("title");
				String plot=rs.getString("plot");
				String poster=rs.getString("poster");
				movie=new Movie(imdbId,title,plot,poster);
			} 	
        }catch (Exception e) {
        	e.printStackTrace();
        }finally{
        	try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return movie;
	}
	public int createMovie(Movie m) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement statement=null;
		int ret=0;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	String url="jdbc:mysql://localhost:3306/spring_2018_movie";
        	Properties props=new Properties();
        	props.setProperty("user", "root");
        	props.setProperty("password", "Ok*64818503");
        	props.setProperty("useSSL", "false");
        	conn=DriverManager.getConnection(url,props);
        	String sql="insert into movies (imdbId,title,plot,poster) values (?,?,?,?)";
        	statement=conn.prepareStatement(sql);
        	statement.setString(1, m.getImdbId());
        	statement.setString(2, m.getTitle());
        	statement.setString(3, m.getPlot());
        	statement.setString(4, m.getPoster());
        	ret=statement.executeUpdate();
        	statement.close();     	
        }catch (Exception e) {
        	e.printStackTrace();
        }finally{
        	try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return ret;
	}
	public int deleteMovie(String imdbId) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement statement=null;
		int r=0;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	String url="jdbc:mysql://localhost:3306/spring_2018_movie";
        	Properties props=new Properties();
        	props.setProperty("user", "root");
        	props.setProperty("password", "Ok*64818503");
        	props.setProperty("useSSL", "false");
        	conn=DriverManager.getConnection(url,props);
        	String sql="delete from movies where imdbId=?";
        	statement=conn.prepareStatement(sql);
        	statement.setString(1, imdbId);
        	r=statement.executeUpdate();
        	statement.close();     	
        }catch (Exception e) {
        	e.printStackTrace();
        }finally{
        	try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return r;
	}
	public int updateMovie(String id, Movie m) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement statement=null;
		int r=0;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	String url="jdbc:mysql://localhost:3306/spring_2018_movie";
        	Properties props=new Properties();
        	props.setProperty("user", "root");
        	props.setProperty("password", "Ok*64818503");
        	props.setProperty("useSSL", "false");
        	conn=DriverManager.getConnection(url,props);
        	String sql="update movies set imdbId=?,title=?,plot=?,poster=? where imdbId=?";
        	statement=conn.prepareStatement(sql);
        	statement.setString(1, m.getImdbId());
        	statement.setString(2, m.getTitle());
        	statement.setString(3, m.getPlot());
        	statement.setString(4, m.getPoster());
        	statement.setString(5, id);
        	r=statement.executeUpdate();
        	statement.close();     	
        }catch (Exception e) {
        	e.printStackTrace();
        }finally{
        	try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return r;
	}
}
