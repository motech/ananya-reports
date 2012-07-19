Alter table report.subscription_status_measure drop column time_id;
Alter table report.subscription_status_measure add column date_id integer not null;
Alter table report.subscription_status_measure add column time_id integer not null;

Alter table report.subscription_status_measure add constraint fk_subscription_status_measure_time_dimension
    FOREIGN KEY(date_id) REFERENCES report.time_dimension(id);

Alter table report.subscription_status_measure add constraint fk_subscription_status_measure_hour_dimension
    FOREIGN KEY(time_id) REFERENCES report.hour_dimension(id);


