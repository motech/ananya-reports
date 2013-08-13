Alter table report.subscriptions add column old_subscription_id integer;

Alter table report.subscriptions add constraint fk_subscriptions_old_subscription
    FOREIGN KEY(old_subscription_id) REFERENCES report.subscriptions(id);

