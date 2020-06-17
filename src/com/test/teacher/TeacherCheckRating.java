package com.test.teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.test.admin.DBUtil;

import oracle.jdbc.OracleTypes;

public class TeacherCheckRating {

   
   //교사 자신이 자신의 평가 점수 조회 (시작)
   public void procPrintTeacherMyRating(TeacherUser tuser) {

      Connection conn = null;
      CallableStatement stat = null;
      DBUtil util = new DBUtil();
      ResultSet rs = null;
      Scanner scan = new Scanner(System.in);
      
      Statement stat2 = null;
      ResultSet rs2 = null;
            
      try {
         
         
         String sql = "{ call procPrintTeacherMyRating(?,?) }";
         
         conn = util.open("211.63.89.64", "project", "java1234");
         stat = conn.prepareCall(sql);
               
         // 교사번호 get
         stat.setInt(1, tuser.getNum());
         
         stat.registerOutParameter(2, OracleTypes.CURSOR);
         
         stat.executeQuery();
                  
         rs = (ResultSet)stat.getObject(2); 
         
         
         String opencoursnum = "";
         String description = "";
         String score = "";
         String ocName = "";
         
         
         // opencoursenum, 과정명 찍기 위해 커서 next()
         
         
         String[] i_array = new String[20];
         String[] s_array = new String[20]; 
         String[] t_array = new String[20]; 
         
         // 과정명 가져오기
         try {
            stat2 = conn.createStatement();
            sql = String.format("select name from tblallcourse ac inner join tblopencourse oc on ac.num = oc.allcoursenum where oc.teachernum  = %s", tuser.getNum());            
            
            rs2 = stat2.executeQuery(sql);
            
            
            
            int k = 0;
            
            while(rs2.next()) {
               
               s_array[k] =  rs2.getString("name");
                        
               k++;
                                 
            }
            

            int i = 0;
            int j = 0;
            
            while(rs.next()) { 
               
               // 교사 평가 항목별 점수 출력
               t_array[j] = rs.getString("description");
               i_array[i] = rs.getString("score");
               
               i++;
               j++;
            }
            
            
            
            for(int a = 0; a < 1; a++) {
               System.out.println();
               System.out.print("\t\t\t[시행과정명]\t");   
               System.out.println("" + s_array[a]);
               System.out.print("\t\t\t----------------------------------");
               System.out.println("\n\t\t\t[평가항목]\t\t\t\t\t\t[점수]");
               for(int b = 0, c = 1; b < 7 & c<7; b++, c++) {
                  
                  System.out.print("\t\t\t" + c +"."+ t_array[b]);
                  System.out.printf("\t\t\t\t" + i_array[b] + '점' + "\n");
               }
               
               
            }
            
            for(int a = 1; a < 2; a++) {
               System.out.println();
               System.out.print("\t\t\t[시행과정명]\t");   
               System.out.println("" + s_array[a]);
               System.out.print("\t\t\t----------------------------------");
               System.out.println("\n\t\t\t[평가항목]\t\t\t\t\t\t[점수]");
               for(int b = 6, c = 1; b < 13 & c<7; b++, c++) {
                  
                  System.out.print("\t\t\t" + c +"."+ t_array[b]);
                  System.out.printf("\t\t\t\t" + i_array[b] + '점' + "\n");
               }
               
               
            }
         
      
            
            
            
            rs2.close();
         } catch (Exception e) {
            e.printStackTrace();
         }
         
         
         rs.close();
         stat.close();
         conn.close();
         
      
         
      } catch (Exception e) {
         System.out.println("교사 자신이 자신의 평가 점수 조회를 실패했습니다.");
         e.printStackTrace();
      }

      System.out.println();
      System.out.println("\t\t\t엔터를 입력하시면 이전 페이지로 돌아갑니다.");
      scan.nextLine();
      
      
   }//교사 자신이 자신의 평가 점수 조회(끝)  
   
   
}//class