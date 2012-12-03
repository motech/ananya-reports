package org.motechproject.ananya.kilkari.reports.datasource.domain;

public class SubscriptionsPerPack {

    private String packName;
    private final long pendingActivation;
    private final long activated;
    private final long suspended;
    private final long pendingDeactivation;
    private final long deactivated;
    private final long pendingCompletion;
    private final long completed;

    public SubscriptionsPerPack(String packName, long pendingActivation, long activated, long suspended,
                                long pendingDeactivation, long deactivated, long pendingCompletion,
                                long completed) {
        this.packName = packName;
        this.pendingActivation = pendingActivation;
        this.activated = activated;
        this.suspended = suspended;
        this.pendingDeactivation = pendingDeactivation;
        this.deactivated = deactivated;
        this.pendingCompletion = pendingCompletion;
        this.completed = completed;
    }

    public long getPendingActivation() {
        return pendingActivation;
    }

    public long getActivated() {
        return activated;
    }

    public long getSuspended() {
        return suspended;
    }

    public long getPendingDeactivation() {
        return pendingDeactivation;
    }

    public long getDeactivated() {
        return deactivated;
    }

    public long getPendingCompletion() {
        return pendingCompletion;
    }

    public long getCompleted() {
        return completed;
    }

    public String getPackName() {
        return packName;
    }
}
