package DAO;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.Dto;

public class Dao {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public static Connection getConnection () throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection
				("jdbc:oracle:thin:@//localhost:1521/xe","ksol46","0406");
		return con;
	}
	
	
	public String search (HttpServletRequest request, HttpServletResponse response) {
		
		ArrayList<Dto> list = new ArrayList<Dto>();
		
		try {
			
			conn = getConnection();
			String sql = "select"
					+ "	m.m_no, m.m_name, p.p_name,"
					+ "	decode(m.p_school,'1','고졸','2','학사','3','석사','4','박사') as 학력,"
					+ "	substr(m.m_jumin,1,6) || '-' || substr(m.m_jumin,7,7) as 주민번호,"
					+ "	m.m_city as 지역구,"
					+ "	p.p_tel1 || '-' || p.p_tel2 ||  '-'"
					+ " || substr(p.p_tel3,4,1)|| substr(p.p_tel3,4,1) || substr(p.p_tel3,4,1) || substr(p.p_tel3,4,1) as 대표전화"
					+ " from tbl_member_202005 m, tbl_party_202005 p"
					+ " where m.p_code = p.p_code";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Dto dto = new Dto();
				
				dto.setM_no(rs.getString(1));
				dto.setM_name(rs.getString(2));
				dto.setP_name(rs.getString(3));
				dto.setP_school(rs.getString(4));
				dto.setM_jumin(rs.getString(5));
				dto.setM_city(rs.getString(6));
				dto.setP_tel(rs.getString(7));

				list.add(dto);
			}
			
			request.setAttribute("list", list);
			
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "search.jsp";
	}
	
	public int vote (HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		String v_jumin = request.getParameter("v_jumin");
		String v_name = request.getParameter("v_name");
		String m_no = request.getParameter("m_no");
		String v_time = request.getParameter("v_time");
		String v_area = request.getParameter("v_area");
		String v_confirm = request.getParameter("v_confirm");
		
		try {
			conn = getConnection();
					
			String sql = "insert into tbl_vote_202005 values (?,?,?,?,?,?)";
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, v_jumin);
			ps.setString(2, v_name);
			ps.setString(3, m_no);
			ps.setString(4, v_time);
			ps.setString(5, v_area);
			ps.setString(6, v_confirm);
			
			result = ps.executeUpdate();
			
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			
		}
		
		return result;
	}
	
	public String inquire (HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Dto> ilist = new ArrayList<Dto>();
		
		try {
			conn = getConnection();
			String sql = "select"
					+ "	v_name,"
					+ "	'19'||substr((v_jumin),1,2)||'년'||substr((v_jumin),3,2)||'월'||substr((v_jumin),5,2)||'일생' as v_birth,"
					+ "    '만'||to_char(to_number(to_char(sysdate,'yyyy'))-to_number('19'||substr(v_jumin,1,2)))||'세' as v_age,"
					+ "    decode((substr(v_jumin,7,1)),'1','남','2','여','3','남','4','여') as 성별,"
					+ "	m_no,"
					+ "	substr(v_time,1,2)||':'||substr(v_time,3,2) as v_time,"
					+ "	decode(v_confirm,'Y','확인','N','미확인') as 유권자확인"
					+ " from tbl_vote_202005"
					+ " where v_area='제1투표장'";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Dto dto = new Dto();
				dto.setV_name(rs.getString(1));
				dto.setM_birth(rs.getString(2));
				dto.setM_age(rs.getString(3));
				dto.setM_gender(rs.getString(4));
				dto.setM_no(rs.getString(5));
				dto.setV_time(rs.getString(6));
				dto.setV_confirm(rs.getString(7));
				
				ilist.add(dto);
			}
			request.setAttribute("ilist", ilist);
			
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "inquire.jsp";
	}
	
	public String grade (HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Dto> glist = new ArrayList<Dto>();
		try {
			conn = getConnection();
			String sql = "select"
					+ "	m.m_no,"
					+ "	m.m_name,"
					+ "	count(*) as v_total"
					+ " from tbl_vote_202005 v, tbl_member_202005 m"
					+ " where v.m_no = m.m_no and v.v_confirm='Y'"
					+ " group by m.m_no, m.m_name"
					+ " order by v_total desc";
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Dto dto = new Dto();
				dto.setM_no(rs.getString(1));
				dto.setM_name(rs.getString(2));
				dto.setV_total(rs.getString(3));
				
				glist.add(dto);
			}
			
			request.setAttribute("glist", glist);
			
			conn.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "grade.jsp";
	}
}
