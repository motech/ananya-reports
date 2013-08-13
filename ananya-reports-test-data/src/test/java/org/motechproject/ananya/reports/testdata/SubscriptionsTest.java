package org.motechproject.ananya.reports.testdata;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.ananya.reports.testdata.contract.SubscriptionData;
import org.springframework.test.context.ContextConfiguration;

@RunWith(RandomUnitTestRunner.class)
@ContextConfiguration("classpath:applicationReportsTestDataContext.xml")
public class SubscriptionsTest extends BaseTest {
    @Test
    public void shouldCreateActivatedWithSomeSuspensionsSubscription() {
        simulateActivationWithSuspension(SubscriptionData.getRandomSubscriptionData());
    }

    @Test
    public void shouldCompleteSubscription() {
        simulateCompleteFlow(SubscriptionData.getRandomSubscriptionData());
    }

    @Test
    public void shouldDeactivationSubscription() {
        simulateDeactivationFlow(SubscriptionData.getRandomSubscriptionData());
    }

    @Test
    public void shouldSuspendSubscription() {
        simulateSuspendSubscription(SubscriptionData.getRandomSubscriptionData());
    }

    @Test
    public void shouldChangeScheduleForSubscription(){
        simulateChangeSubscriptionSchedule(SubscriptionData.getRandomSubscriptionData());
    }

    @Test
    public void shouldChangePack(){
        simulateChangeSubscriptionPack(SubscriptionData.getRandomSubscriptionData());
    }
}

