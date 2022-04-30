package tclark.demo.restful.data;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tclark.demo.restful.config.ApplicationPropertiesConfig;
import tclark.demo.restful.config.DBQueriesConfig;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationPropertiesConfig.class, DBQueriesConfig.class })
@TestPropertySource(value = {"classpath:application.properties"})
public class IntegrationTestDBView {

    Connection connection;
    Statement statement;

    @Autowired
    ApplicationPropertiesConfig applicationPropertiesConfig;

    @Autowired
    DBQueriesConfig dbQueriesConfig;

    @BeforeEach
    void setup() throws SQLException {
        connection = DriverManager.getConnection(applicationPropertiesConfig.getUrl(),applicationPropertiesConfig.getUsername(),applicationPropertiesConfig.getPassword());
        statement = connection.createStatement();
    }

    @AfterEach
    void teardown() throws SQLException {
        connection.close();
    }

    @Test
    void testIncidentSummary_existenceViewSchema() throws SQLException {
        String query = dbQueriesConfig.getQueryExistenceViewSchema();
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        assertTrue(resultSet.getBoolean("exists"));
    }

    @Test
    void testIncidentSummary_totalCount() throws SQLException {
        String query = dbQueriesConfig.getQueryTotalCount();
        ResultSet resultSet = statement.executeQuery(query);
        resultSet.next();
        assertEquals(218, resultSet.getInt("count"));
    }

    @Test
    void testIncidentSummary_unitedKingdomSummary() throws SQLException, ParseException {
        String query = dbQueriesConfig.getQueryUnitedKingdomSummary();

        PreparedStatement pstmt = connection.prepareStatement(query,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet resultSet = pstmt.executeQuery();

        SimpleDateFormat getYearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Integer monthlyTotal = 0;
        Date sampleDate = sdf.parse("2020-03-01");

        while(resultSet.next()){
            String country = resultSet.getString("country");
            Date date = resultSet.getDate("yy_mm");
            monthlyTotal += resultSet.getInt(3);

            if(date.compareTo(sampleDate) == 0){
                assertEquals(9, resultSet.getInt(3));
            }
            assertEquals("United Kingdom", country);
            assertEquals("2020", getYearFormat.format(date));
        }
        assertEquals(44, monthlyTotal);

        resultSet.last();
        int rowCount = resultSet.getRow();
        assertEquals(7, rowCount);
    }
}