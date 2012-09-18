package org.motechproject.ananya.reports.kilkari.repository;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationKilkariReportsContext.xml")
@Transactional
public abstract class SpringIntegrationTest {

    @Autowired
    @Qualifier("dataAccessTemplate")
    protected DataAccessTemplate template;

    @Before
    public void setUp() {
        template.setAlwaysUseNewSession(true);
    }

    private List<Object> toDelete = new ArrayList<Object>();

    protected void markForDeletion(Object entity) {
        toDelete.add(entity);
    }

    protected void tearDown() {
        template.deleteAll(toDelete);
    }
}