CREATE OR REPLACE VIEW report.status_transition_view AS
    (WITH subscription_status AS
        (SELECT   row_number() OVER (PARTITION BY subscription_id ORDER BY last_modified_time) RowID,
                  subscription_id,
                  status,
                  last_modified_time
            FROM report.subscription_status_measure
            ORDER BY last_modified_time)

    SELECT old.subscription_id subscription_id,
           old.status old_status,
           new.status new_status,
           old.last_modified_time old_modified_time,
           new.last_modified_time new_modified_time,
           DATE (new.last_modified_time) - DATE(old.last_modified_time) num_of_days
    FROM subscription_status old
    LEFT OUTER JOIN subscription_status new ON old.RowID = new.RowID - 1 AND old.subscription_id = new.subscription_id
    ORDER BY old.subscription_id,
             old.last_modified_time);

