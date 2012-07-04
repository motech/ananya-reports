CREATE TABLE kilkari.channel_dimension(
    id serial NOT NULL,
    channel varchar(255) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE kilkari.location_dimension(
    id serial NOT NULL,
    district varchar(255),
    block varchar(255),
    panchayat varchar(255),
    PRIMARY KEY(id)
);

CREATE TABLE kilkari.operator_dimension(
    id serial NOT NULL,
    operator varchar(255) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE kilkari.time_dimension(
    id serial NOT NULL,
    day INTEGER NOT NULL,
    week INTEGER NOT NULL,
    month INTEGER NOT NULL,
    year INTEGER NOT NULL,
    date DATE NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE kilkari.subscription_pack_dimension(
    id serial NOT NULL,
    subscription_pack VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE kilkari.subscribers(
    id serial NOT NULL,
    msisdn varchar(255) NOT NULL,
    name varchar(255),
    age_of_beneficiary INTEGER,
    estimated_date_of_delivery DATE,
    date_of_birth DATE,
    channel_id INTEGER NOT NULL,
    location_id INTEGER,
    time_id INTEGER NOT NULL,
    operator_id INTEGER,
    PRIMARY KEY(id),

    CONSTRAINT fk_subscribers_channel_dimension
    FOREIGN KEY(channel_id) REFERENCES kilkari.channel_dimension(id),

    CONSTRAINT fk_subscribers_location_dimension
    FOREIGN KEY(location_id) REFERENCES kilkari.location_dimension(id),

    CONSTRAINT fk_subscribers_time_dimension
    FOREIGN KEY(time_id) REFERENCES kilkari.time_dimension(id),

    CONSTRAINT fk_subscribers_operator_dimension
    FOREIGN KEY(operator_id) REFERENCES kilkari.operator_dimension(id)
);

CREATE TABLE kilkari.subscriptions(
    id serial NOT NULL,
    subscriber_id INTEGER NOT NULL,
    subscription_pack_id INTEGER,
    channel_id INTEGER NOT NULL,
    operator_id INTEGER,
    location_id INTEGER,
    time_id INTEGER NOT NULL,
    subscription_id VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY(id),

    CONSTRAINT fk_subscriptions_subscribers
    FOREIGN KEY(subscriber_id) REFERENCES kilkari.subscribers(id),

    CONSTRAINT fk_subscriptions_subscription_pack_dimension
    FOREIGN KEY(subscription_pack_id) REFERENCES kilkari.subscription_pack_dimension(id),

    CONSTRAINT fk_subscriptions_channel_dimension
    FOREIGN KEY(channel_id) REFERENCES kilkari.channel_dimension(id),

    CONSTRAINT fk_subscriptions_operator_dimension
    FOREIGN KEY(operator_id) REFERENCES kilkari.operator_dimension(id),

    CONSTRAINT fk_subscriptions_location_dimension
    FOREIGN KEY(location_id) REFERENCES kilkari.location_dimension(id),

    CONSTRAINT fk_subscriptions_time_dimension
    FOREIGN KEY(time_id) REFERENCES kilkari.time_dimension(id)
);

CREATE TABLE kilkari.subscriber_call_measure(
    id serial NOT NULL,
    subscription_id INTEGER NOT NULL,
    call_status VARCHAR(255),
    retry_count INTEGER,
    duration INTEGER,
    ring_duration INTEGER,
    channel_id INTEGER NOT NULL,
    operator_id INTEGER,
    subscription_pack_id INTEGER,
    time_id INTEGER NOT NULL,

    CONSTRAINT fk_subscriber_call_measure_channel_dimension
    FOREIGN KEY(channel_id) REFERENCES kilkari.channel_dimension(id),

    CONSTRAINT fk_subscriber_call_measure_operator_dimension
    FOREIGN KEY(operator_id) REFERENCES kilkari.operator_dimension(id),

    CONSTRAINT fk_subscriber_call_measure_subscription_pack_dimension
    FOREIGN KEY(subscription_pack_id) REFERENCES kilkari.subscription_pack_dimension(id),

    CONSTRAINT fk_subscriber_call_measure_time_dimension
    FOREIGN KEY(time_id) REFERENCES kilkari.time_dimension(id)
);

CREATE TABLE kilkari.subscription_status_measure(
    id serial NOT NULL,
    subscription_id INTEGER NOT NULL,
    status VARCHAR(255),
    week_number INTEGER,
    channel_id INTEGER,
    operator_id INTEGER,
    subscription_pack_id INTEGER,
    time_id INTEGER NOT NULL,

    CONSTRAINT fk_subscription_status_measure_channel_dimension
    FOREIGN KEY(channel_id) REFERENCES kilkari.channel_dimension(id),

    CONSTRAINT fk_subscription_status_measure_operator_dimension
    FOREIGN KEY(operator_id) REFERENCES kilkari.operator_dimension(id),

    CONSTRAINT fk_subscription_status_measure_subscription_pack_dimension
    FOREIGN KEY(subscription_pack_id) REFERENCES kilkari.subscription_pack_dimension(id),

    CONSTRAINT fk_subscription_status_measure_time_dimension
    FOREIGN KEY(time_id) REFERENCES kilkari.time_dimension(id)
);

