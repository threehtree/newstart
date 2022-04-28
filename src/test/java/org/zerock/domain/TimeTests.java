package org.zerock.domain;

import lombok.Cleanup;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TimeTests {

    @Test
    public void testNow() throws Exception {
        // jdbc 드라이브 호출
        Class.forName("org.mariadb.jdbc.Driver");

        //db ID,PW,Path 설정
        @Cleanup Connection con= DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/webdb",
                "webuser",
                "webuser");
        //prepared statement 방식으로 sql 작성
        @Cleanup PreparedStatement pstmt =
                con.prepareStatement("select now()");
        //sql실행
        @Cleanup ResultSet resultSet = pstmt.executeQuery();
        //인덱스의 시작점을 가르키기위해 next 로 +1
        resultSet.next();

        System.out.println(resultSet.getString(1));



    }
}
