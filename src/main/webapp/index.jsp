<%@page
        import="java.util.*,javax.naming.*,javax.sql.DataSource,java.sql.*"%>
<%
    DataSource ds = null;
    Connection con = null;
    Statement stmt = null;
    InitialContext ic;

    try {
        ic = new InitialContext();
        ds = (DataSource) ic.lookup("java:/PostgresDS");
        con = ds.getConnection();
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from myuser");

        while (rs.next()) {
            out.println("<br> " + rs.getString("st_name") + " | "
                    + rs.getString("st_lastName") + " | "
                    + rs.getString("st_password") + " | "
                    + rs.getString("st_school") + " | "
                    + rs.getString("id") + " | "
                    + rs.getString("st_schoolLatitude") + " | "
                    + rs.getString("st_schoolLongitude"));
        }

        for(int i = 0; i < 10; i++){
            stmt.executeUpdate("insert into myuser (st_name, st_lastname, st_password, st_school, st_schoollatitude, st_schoollongitude) values ('dasda', 'dadsa', 'dadsa', 'dadsa', 32.4324, 21.4324);");
        }

        rs.close();
        stmt.close();
    } catch (Exception e) {
        out.println("Exception thrown :/");
        e.printStackTrace();
    } finally {
        if (con != null) {
            con.close();
        }
    }
%>