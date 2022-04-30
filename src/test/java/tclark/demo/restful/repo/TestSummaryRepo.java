package tclark.demo.restful.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import tclark.demo.restful.SummaryEntity;
import tclark.demo.restful.SummaryRepository;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource( properties = {
        "spring.main.banner-mode=off",
        "spring.datasource.platform=h2",
        "logging.level.org.hibernate.type=trace",
        "logging.level.org.hibernate.sql=debug",
        "logging.level.org.hibernate.type.BasicTypeRegistry=warn",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.h2.console.enabled=true"})
public class TestSummaryRepo {

    @Autowired
    private SummaryRepository summaryRepository;

    private SummaryEntity summaryEntity1;

    @BeforeEach
    public void setup(){
        summaryEntity1 = SummaryEntity.builder()
                .id("2020-01-01_NN")
                .country("Narnia")
                .iso2("NN")
                .iso3("NNA")
                .yy_mm(LocalDate.ofEpochDay(2020-01-01))
                .monthly_count(5L)
                .monthly_total(135L)
                .percentage((float) 0.015)
                .build();
    }

    @Test
    public void testIncidentSummaryRepository_saveEntity (){
        SummaryEntity savedSummaryEntity1 = summaryRepository.save(summaryEntity1);
        assertNotNull(savedSummaryEntity1);
        assertEquals("Narnia", savedSummaryEntity1.getCountry());
        assertEquals("NN", savedSummaryEntity1.getIso2());
        assertEquals("NNA", savedSummaryEntity1.getIso3());
        assertEquals(LocalDate.ofEpochDay(2020-01-01), savedSummaryEntity1.getYy_mm());
        assertEquals(5L, savedSummaryEntity1.getMonthly_count());
        assertEquals(135L, savedSummaryEntity1.getMonthly_total());
        assertEquals((float) 0.015, savedSummaryEntity1.getPercentage());
    }

    @Test
    public void testIncidentSummaryRepository_findAll(){
        SummaryEntity summaryEntity2 = SummaryEntity.builder()
                .id("2020-01-01_LL")
                .country("Lilliput")
                .iso2("LL")
                .iso3("LLP")
                .yy_mm(LocalDate.ofEpochDay(2020-01-01))
                .monthly_count(1L)
                .monthly_total(135L)
                .percentage((float) 0.007)
                .build();

        summaryRepository.save(summaryEntity1);
        summaryRepository.save(summaryEntity2);

        List<SummaryEntity> summaryEntityList = summaryRepository.findAll();
        assertEquals(2, summaryEntityList.size());
    }
}