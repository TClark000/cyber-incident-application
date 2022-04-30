package tclark.demo.restful.repo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tclark.demo.restful.SummaryEntity;
import tclark.demo.restful.SummaryRepository;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class IntegrationTestSummaryRepo {

    @Autowired
    private SummaryRepository summaryRepository;

    @Test
    public void testSummaryRepository (){
        List<SummaryEntity> summaryEntityList = summaryRepository.findAll();
        assertEquals(218, summaryEntityList.size());
    }
}