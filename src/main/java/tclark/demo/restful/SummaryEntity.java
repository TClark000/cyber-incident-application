package tclark.demo.restful;

import lombok.Builder;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Immutable;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Immutable
@Table(name = "incident_summary")
@AllArgsConstructor
@Builder
public class SummaryEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "country")
    private String country;

    @Column(name = "iso2")
    private String iso2;

    @Column(name = "iso3")
    private String iso3;

    @Column(name = "yy_mm", columnDefinition = "DATE")
    private LocalDate yy_mm;

    @Column(name = "monthly_count")
    private Long monthly_count;

    @Column(name = "monthly_total")
    private Long monthly_total;

    @Column(name = "percentage", columnDefinition = "NUMERIC(4, 3)")
    private Float percentage;

    protected SummaryEntity(){
    }

    public String getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getIso2() {
        return iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public LocalDate getYy_mm() {
        return yy_mm;
    }

    public Long getMonthly_count() {
        return monthly_count;
    }

    public Long getMonthly_total() {
        return monthly_total;
    }

    public Float getPercentage() {
        return percentage;
    }
}