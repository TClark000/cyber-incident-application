package tclark.demo.restful.config;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class DBQueriesConfig {

    private final String queryExistenceViewSchema = "SELECT EXISTS (SELECT FROM pg_views WHERE viewname = 'incident_summary')";
    private final String queryTotalCount = "SELECT COUNT(*) FROM incident_summary";
    private final String queryUnitedKingdomSummary = "SELECT incident_summary.country, incident_summary.yy_mm, incident_summary.monthly_count FROM incident_summary WHERE incident_summary.country = 'United Kingdom'";
}