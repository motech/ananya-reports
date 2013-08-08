package org.motechproject.ananya.reports.testdata.contract;

import org.apache.commons.lang.math.RandomUtils;
import org.joda.time.DateTime;

import java.util.ArrayList;

public class SubscriptionData {
    private final String operator;
    private final String packname;
    private final long channel;
    private final DateTime startTime;

    private static final ArrayList<String> packs = new ArrayList<String>() {{
        add("BARI_KILKARI");
        add("NANHI_KILKARI");
        add("NAVJAAT_KILKARI");
    }};

    private static final ArrayList<String> operators = new ArrayList<String>() {{
        add("RELIANCEGSM");
        add("IDEA");
        add("VODAFONE");
        add("TATADOCOMO");
        add("AIRTEL");
        add("BSNL");
    }};

    public SubscriptionData(String operator, String packname, long channel, DateTime startTime) {
        this.operator = operator;
        this.packname = packname;
        this.channel = channel;
        this.startTime = startTime;
    }

    public String getOperator() {
        return operator;
    }

    public String getPackname() {
        return packname;
    }

    public long getChannel() {
        return channel;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public static SubscriptionData getRandomSubscriptionData(){
        long randomNumber = RandomUtils.nextInt();

        String operator = operators.get((int) (randomNumber % operators.size()));
        long channel = randomNumber % 2;
        String pack = packs.get((int) (randomNumber % packs.size()));

        DateTime now = DateTime.now();

        int months = RandomUtils.nextInt(6);
        DateTime startDate = now.minusMonths(months);
        System.out.println(operator+" "+channel+" "+pack);
        return new SubscriptionData(operator,pack,channel,startDate);
    }

}
