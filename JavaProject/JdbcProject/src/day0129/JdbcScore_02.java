package day0129;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import oracleDb.DbConnect;

public class JdbcScore_02 {

	DbConnect db = new DbConnect();
	Scanner sc = new Scanner(System.in);

	public void process() {
		while (true) {
			System.out.println("1.학생성적 추가\t2.학생정보 전체출력\t3.학생정보 삭제\t4.학생정보 수정\t9.종료");

			int num = Integer.parseInt(sc.nextLine());

			switch (num) {
			case 1:
				this.insertSungjuk();
				break;
			case 2:
				this.writeAll();
				break;
			case 3:
				this.deleteSungjuk();
				break;
			case 4:
				this.updateSungjuk();
				break;
			case 9:
				System.out.println("**성적프로그램종료**");
				System.exit(0);
			}
		}
	}

	// 입력
	public void insertSungjuk() {
		System.out.println("학생명");
		String name = sc.nextLine();
		System.out.println("자바점수");
		int java = Integer.parseInt(sc.nextLine());
		System.out.println("오라클점수");
		int oracle = Integer.parseInt(sc.nextLine());

		Connection conn = db.getOracle();
		PreparedStatement pstmt = null;

		String sql = "insert into sungjuk (num,name,java,oracle) values (seq_score.nextval,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);

			// 바인딩
			pstmt.setString(1, name);
			pstmt.setInt(2, java);
			pstmt.setInt(3, oracle);

			pstmt.execute();
			System.out.println("추가되었습니다~~");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(pstmt, conn);
		}
	}

	// 총점,평균 구하는 매서드
	public void calcTotalAverage() {
		Connection conn = db.getOracle();
		Statement stmt = null;

		String sql = "update sungjuk set total=java+oracle,average=(java+oracle)/2";

		try {
			stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(stmt, conn);
		}
	}

	// 출력
	public void writeAll() {
		// 총점평균 먼저 계산후 출력
		this.calcTotalAverage();

		Connection conn = db.getOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from sungjuk order by num";
		System.out.println("\t\t**학생성적 출력**\t\t");
		System.out.println();
		System.out.println("번호\t이름\t자바\t오라클\t총점\t평균");
		System.out.println("--------------------------------------------------");

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getInt("num") + "\t" + rs.getString("name") + "\t" + rs.getInt("java") + "\t"
						+ rs.getInt("oracle") + "\t" + rs.getInt("total") + "\t" + rs.getDouble("average"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}

	}

	// 삭제
	public void deleteSungjuk() {
		// 시퀀스를 입력받아 해당 데이터 지운후 삭제되엇습니다 출력
		Connection conn = db.getOracle();
		PreparedStatement pstmt = null;

		System.out.println("삭제할 학생번호를 입력하세요");
		int num = Integer.parseInt(sc.nextLine());

		String sql = "delete from sungjuk where num=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, num);

			int n = pstmt.executeUpdate();

			if (n == 1)
				System.out.println("삭제되었습니다");
			else
				System.out.println(num + "번 학생은 존재하지 않습니다");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(pstmt, conn);
		}
	}

	public boolean isOneData(String num) {
		boolean flag = false;

		Connection conn = db.getOracle();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from sungjuk where num=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();

			if (rs.next())
				flag = true;
			else
				flag = false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}

		return flag;
	}

	public void updateSungjuk() {
		Connection conn = db.getOracle();
		PreparedStatement pstmt = null;

		System.out.println("업데이트할 학생정보의 번호를 입력하세요");
		String num = sc.nextLine();

		if (!this.isOneData(num)) {
			System.out.println("없는 번호입니다");
			return;
		}

		System.out.println("수정할 이름을 입력하세요");
		String name = sc.nextLine();
		System.out.println("수정할 java 점수를 입력하세요");
		int java = Integer.parseInt(sc.nextLine());
		System.out.println("수정할 oracle 점수를 입력하세요");
		int oracle = Integer.parseInt(sc.nextLine());

		String sql = "update sungjuk set name=?,java=?,oracle=? where num=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, java);
			pstmt.setInt(3, oracle);
			pstmt.setString(4, num);

			pstmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(pstmt, conn);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JdbcScore_02 score = new JdbcScore_02();
		score.process();

	}

}
