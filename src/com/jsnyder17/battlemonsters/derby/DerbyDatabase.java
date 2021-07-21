package com.jsnyder17.battlemonsters.derby;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jsnyder17.battlemonsters.monster.Monster;
import com.jsnyder17.battlemonsters.move.DamageManager;
import com.jsnyder17.battlemonsters.move.Move;

public class DerbyDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;
	
	public ArrayList<Move> getMovesByMonsterId(int monsterId) {
		return executeTransaction(new Transaction<ArrayList<Move>>() {
			public ArrayList<Move> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement("select * from moves, movesMap where moves.move_id = movesMap.moves_id and movesMap.monster_id = ?");
					stmt.setInt(1, monsterId);
					
					resultSet = stmt.executeQuery();
					
					ArrayList<Move> moves = new ArrayList<Move>();
					
					boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Move move = new Move();
						loadMove(move, resultSet, 1);
						
						moves.add(move);
					}
					
					return moves;
				}
				finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public ArrayList<Monster> getMonsters() {
		return executeTransaction(new Transaction<ArrayList<Monster>>() {
			public ArrayList<Monster> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement("select * from monsters");
					
					resultSet = stmt.executeQuery();
					
					ArrayList<Monster> monsters = new ArrayList<Monster>();
					
					boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Monster monster = new Monster();
						loadMonster(monster, resultSet, 1);
						
						monsters.add(monster);
					}
					
					return monsters;
				}
				finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	public ArrayList<Move> getMoves() {
		return executeTransaction(new Transaction<ArrayList<Move>>() {
			public ArrayList<Move> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement("select * from moves");
					
					resultSet = stmt.executeQuery();
					
					ArrayList<Move> moves = new ArrayList<Move>();
					
					boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Move move = new Move();
						loadMove(move, resultSet, 1);
						
						moves.add(move);
					}
					
					return moves;
				}
				finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	
	// wrapper SQL transaction function that calls actual transaction function (which has retries)
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	// SQL transaction function which retries the transaction MAX_ATTEMPTS times before failing
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	// TODO: Here is where you name and specify the location of your Derby SQL database
	// TODO: Change it here and in SQLDemo.java under CS320_LibraryExample_Lab06->edu.ycp.cs320.sqldemo
	// TODO: DO NOT PUT THE DB IN THE SAME FOLDER AS YOUR PROJECT - that will cause conflicts later w/Git
	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:C:/Users/" + System.getProperty("user.name") + "/Documents/BattleMonsters.db;create=true");		
		
		// Set autocommit() to false to allow the execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	private void loadMove(Move move, ResultSet resultSet, int index) throws SQLException {
		move.setId(resultSet.getInt(index++));
		move.setName(resultSet.getString(index++));
		move.setType(resultSet.getInt(index++));
		move.setCategory(resultSet.getInt(index++));
		move.setPower(resultSet.getInt(index++));
		move.setAccuracy(resultSet.getInt(index++));
		move.setMaxPP(resultSet.getInt(index++));
		move.setCurrentPP(move.getMaxPP());
	}
	private void loadMonster(Monster monster, ResultSet resultSet, int index) throws SQLException {
		monster.setLevel(1);
		monster.setId(resultSet.getInt(index++));
		monster.setName(resultSet.getString(index++));
		monster.setType1(resultSet.getInt(index++));
		monster.setType2(resultSet.getInt(index++));
		monster.getHp().setBase(resultSet.getInt(index++));
		monster.getAttack().setBase(resultSet.getInt(index++));
		monster.getDefense().setBase(resultSet.getInt(index++));
		monster.getSpecialAttack().setBase(resultSet.getInt(index++));
		monster.getSpecialDefense().setBase(resultSet.getInt(index++));
		monster.getSpeed().setBase(resultSet.getInt(index++));
		
		monster.calculateStats();
		
		monster.setImageFrontPath(resultSet.getString(index++));
		monster.setImageBackPath(resultSet.getString(index++));
	}
	
	//  creates the Authors and Books tables
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				
				try {
					stmt1 = conn.prepareStatement("create table moves(move_id integer primary key generated always as identity(start with 1, increment by 1), name long varchar, type integer, category integer, power integer, accuracy integer, max_pp integer)");
					stmt1.executeUpdate();
					//System.out.println("Moves table created. ");
					
					stmt2 = conn.prepareStatement("create table monsters(monster_id integer primary key generated always as identity(start with 1, increment by 1), name long varchar, type_1 integer, type_2 integer, hp double, attack integer, defense integer, special_attack integer, special_defense integer, speed integer, image_front long varchar, image_back long varchar)");
					stmt2.executeUpdate();
					//System.out.println("Monsters table created. ");
					
					return true;
				} 
				finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}
	
	public void dropTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				
				try {
					stmt1 = conn.prepareStatement("drop table moves");
					stmt1.executeUpdate();
					
					stmt2 = conn.prepareStatement("drop table monsters");
					stmt2.executeUpdate();
					
					//System.out.println("Dropped all tables. ");
					
					return true;
				}
				finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}
	
	public void initializeData() {
		// Check if the database already exists on the user's system
		File file = new File("C:/Users/" + System.getProperty("user.name") + "/Documents/BattleMonsters.db");
		
		if (!file.isDirectory()) {
			System.out.println("Database not found. Creating ... ");
			
			createTables();
			loadInitialData();
		}
		else {
			System.out.println("Database already exists. ");
		}
		
		System.out.println("All data loaded successfully. ");
	}
	
	// loads data retrieved from CSV files into DB tables in batch mode
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<Move> moveList;
				List<Monster> monsterList;
				InitialData id = new InitialData();
				
				try {
					moveList = id.getMoves();
					monsterList = id.getMonsters();
				
				} 
				catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertMove = null;
				PreparedStatement insertMonster = null;

				try {
					insertMove = conn.prepareStatement("insert into moves(name, type, category, power, accuracy, max_pp) values(?, ?, ?, ?, ?, ?)");
					for (Move m : moveList) {
						insertMove.setString(1, m.getName());
						insertMove.setInt(2, m.getType());
						insertMove.setInt(3, m.getCategory());
						insertMove.setInt(4, m.getPower());
						insertMove.setInt(5, m.getAccuracy());
						insertMove.setInt(6, m.getMaxPP());
						insertMove.addBatch();
					}
					insertMove.executeBatch();
					
					insertMonster = conn.prepareStatement("insert into monsters(name, type_1, type_2, hp, attack, defense, special_attack, special_defense, speed, image_front, image_back) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for (Monster m : monsterList) {
						insertMonster.setString(1, m.getName());
						insertMonster.setInt(2, m.getType1());
						insertMonster.setInt(3, m.getType2());
						insertMonster.setDouble(4, m.getHp().getBase());
						insertMonster.setInt(5, m.getAttack().getBase());
						insertMonster.setInt(6, m.getDefense().getBase());
						insertMonster.setInt(7, m.getSpecialAttack().getBase());
						insertMonster.setInt(8, m.getSpecialDefense().getBase());
						insertMonster.setInt(9, m.getSpeed().getBase());
						insertMonster.setString(10, m.getImageFrontPath());
						insertMonster.setString(11, m.getImageBackPath());
						insertMonster.addBatch();
					}
					insertMonster.executeBatch();
					
					return true;
				} 
				finally {
					DBUtil.closeQuietly(insertMove);
					DBUtil.closeQuietly(insertMonster);
				}
			}
		});
	}
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.dropTables();
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("Battle Monsters DB successfully initialized!");
		
		testData(db);
	}
	
	public static void testData(DerbyDatabase db) {
		ArrayList<Monster> monsters = db.getMonsters();
		ArrayList<Move> moves = db.getMoves();
		
		System.out.println("========== MONSTERS ==========\n");
		if (monsters.size() > 0) {
			for (Monster m : monsters) {
				System.out.println(m.toString());
			}
		}
		else {
			System.out.println("No monsters found. ");
		}
		
		System.out.println("========== MOVES ==========\n");
		if (moves.size() > 0) {
			for (Move m : moves) {
				System.out.println(m.toString());
			}
		}
		else {
			System.out.println("No moves found. ");
		}
	}
}
