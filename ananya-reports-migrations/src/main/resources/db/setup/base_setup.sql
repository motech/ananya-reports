--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
--SET client_encoding = 'SQL_ASCII';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: report; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA report;


ALTER SCHEMA report OWNER TO postgres;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = report, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: call_duration_measure; Type: TABLE; Schema: report; Owner: postgres; Tablespace: 
--

CREATE TABLE call_duration_measure (
    id integer NOT NULL,
    flw_id bigint,
    call_id character varying(255),
    duration integer,
    type character varying(30),
    location_id bigint,
    start_time timestamp with time zone,
    end_time timestamp with time zone,
    called_number bigint,
    time_id bigint
);


ALTER TABLE report.call_duration_measure OWNER TO postgres;

--
-- Name: call_duration_measure_id_seq; Type: SEQUENCE; Schema: report; Owner: postgres
--

CREATE SEQUENCE call_duration_measure_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE report.call_duration_measure_id_seq OWNER TO postgres;

--
-- Name: call_duration_measure_id_seq; Type: SEQUENCE OWNED BY; Schema: report; Owner: postgres
--

ALTER SEQUENCE call_duration_measure_id_seq OWNED BY call_duration_measure.id;


--
-- Name: call_duration_measure_id_seq; Type: SEQUENCE SET; Schema: report; Owner: postgres
--

SELECT pg_catalog.setval('call_duration_measure_id_seq', 205283, true);


--
-- Name: course_item_dimension; Type: TABLE; Schema: report; Owner: postgres; Tablespace: 
--

CREATE TABLE course_item_dimension (
    id integer NOT NULL,
    name character varying(255),
    content_id character varying(255),
    type character varying(30),
    parent_id integer,
    file_name character varying(255),
    duration integer
);


ALTER TABLE report.course_item_dimension OWNER TO postgres;

--
-- Name: course_item_dimension_id_seq; Type: SEQUENCE; Schema: report; Owner: postgres
--

CREATE SEQUENCE course_item_dimension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE report.course_item_dimension_id_seq OWNER TO postgres;

--
-- Name: course_item_dimension_id_seq; Type: SEQUENCE OWNED BY; Schema: report; Owner: postgres
--

ALTER SEQUENCE course_item_dimension_id_seq OWNED BY course_item_dimension.id;


--
-- Name: course_item_dimension_id_seq; Type: SEQUENCE SET; Schema: report; Owner: postgres
--

SELECT pg_catalog.setval('course_item_dimension_id_seq', 334, true);


--
-- Name: course_item_measure; Type: TABLE; Schema: report; Owner: postgres; Tablespace: 
--

CREATE TABLE course_item_measure (
    id integer NOT NULL,
    flw_id bigint,
    course_item_id integer,
    time_id integer,
    score integer,
    event character varying(30),
    location_id bigint,
    "timestamp" timestamp with time zone,
    percentage smallint,
    call_id character varying(255),
    duration integer
);


ALTER TABLE report.course_item_measure OWNER TO postgres;

--
-- Name: course_item_measure_id_seq; Type: SEQUENCE; Schema: report; Owner: postgres
--

CREATE SEQUENCE course_item_measure_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE report.course_item_measure_id_seq OWNER TO postgres;

--
-- Name: course_item_measure_id_seq; Type: SEQUENCE OWNED BY; Schema: report; Owner: postgres
--

ALTER SEQUENCE course_item_measure_id_seq OWNED BY course_item_measure.id;


--
-- Name: course_item_measure_id_seq; Type: SEQUENCE SET; Schema: report; Owner: postgres
--

SELECT pg_catalog.setval('course_item_measure_id_seq', 43890, true);


--
-- Name: databasechangelog; Type: TABLE; Schema: report; Owner: postgres; Tablespace: 
--

CREATE TABLE databasechangelog (
    id character varying(63) NOT NULL,
    author character varying(63) NOT NULL,
    filename character varying(200) NOT NULL,
    dateexecuted timestamp with time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20)
);


ALTER TABLE report.databasechangelog OWNER TO postgres;

--
-- Name: databasechangeloglock; Type: TABLE; Schema: report; Owner: postgres; Tablespace: 
--

CREATE TABLE databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp with time zone,
    lockedby character varying(255)
);


ALTER TABLE report.databasechangeloglock OWNER TO postgres;

--
-- Name: front_line_worker_dimension; Type: TABLE; Schema: report; Owner: postgres; Tablespace: 
--

CREATE TABLE front_line_worker_dimension (
    id integer NOT NULL,
    msisdn bigint,
    operator character varying(30),
    name character varying(255),
    status character varying(30),
    designation character varying(30),
    circle character varying(30)
);


ALTER TABLE report.front_line_worker_dimension OWNER TO postgres;

--
-- Name: front_line_worker_dimension_id_seq; Type: SEQUENCE; Schema: report; Owner: postgres
--

CREATE SEQUENCE front_line_worker_dimension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE report.front_line_worker_dimension_id_seq OWNER TO postgres;

--
-- Name: front_line_worker_dimension_id_seq; Type: SEQUENCE OWNED BY; Schema: report; Owner: postgres
--

ALTER SEQUENCE front_line_worker_dimension_id_seq OWNED BY front_line_worker_dimension.id;


--
-- Name: front_line_worker_dimension_id_seq; Type: SEQUENCE SET; Schema: report; Owner: postgres
--

SELECT pg_catalog.setval('front_line_worker_dimension_id_seq', 34267, true);


--
-- Name: job_aid_content_dimension; Type: TABLE; Schema: report; Owner: postgres; Tablespace: 
--

CREATE TABLE job_aid_content_dimension (
    id integer NOT NULL,
    content_id character varying(255),
    parent_id bigint,
    name character varying(255),
    file_name character varying(255),
    type character varying(255),
    duration integer,
    short_code bigint
);


ALTER TABLE report.job_aid_content_dimension OWNER TO postgres;

--
-- Name: job_aid_content_dimension_id_seq; Type: SEQUENCE; Schema: report; Owner: postgres
--

CREATE SEQUENCE job_aid_content_dimension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE report.job_aid_content_dimension_id_seq OWNER TO postgres;

--
-- Name: job_aid_content_dimension_id_seq; Type: SEQUENCE OWNED BY; Schema: report; Owner: postgres
--

ALTER SEQUENCE job_aid_content_dimension_id_seq OWNED BY job_aid_content_dimension.id;


--
-- Name: job_aid_content_dimension_id_seq; Type: SEQUENCE SET; Schema: report; Owner: postgres
--

SELECT pg_catalog.setval('job_aid_content_dimension_id_seq', 184, true);


--
-- Name: job_aid_content_measure; Type: TABLE; Schema: report; Owner: postgres; Tablespace: 
--

CREATE TABLE job_aid_content_measure (
    id integer NOT NULL,
    flw_id bigint,
    call_id character varying(255),
    location_id bigint,
    job_aid_content_id integer,
    "timestamp" timestamp with time zone,
    percentage smallint,
    time_id bigint,
    duration integer
);


ALTER TABLE report.job_aid_content_measure OWNER TO postgres;

--
-- Name: job_aid_content_measure_id_seq; Type: SEQUENCE; Schema: report; Owner: postgres
--

CREATE SEQUENCE job_aid_content_measure_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE report.job_aid_content_measure_id_seq OWNER TO postgres;

--
-- Name: job_aid_content_measure_id_seq; Type: SEQUENCE OWNED BY; Schema: report; Owner: postgres
--

ALTER SEQUENCE job_aid_content_measure_id_seq OWNED BY job_aid_content_measure.id;


--
-- Name: job_aid_content_measure_id_seq; Type: SEQUENCE SET; Schema: report; Owner: postgres
--

SELECT pg_catalog.setval('job_aid_content_measure_id_seq', 84458, true);


--
-- Name: location_dimension; Type: TABLE; Schema: report; Owner: postgres; Tablespace: 
--

CREATE TABLE location_dimension (
    id integer NOT NULL,
    location_id character varying(30),
    district character varying(255),
    block character varying(255),
    panchayat character varying(255)
);


ALTER TABLE report.location_dimension OWNER TO postgres;

--
-- Name: location_dimension_id_seq; Type: SEQUENCE; Schema: report; Owner: postgres
--

CREATE SEQUENCE location_dimension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE report.location_dimension_id_seq OWNER TO postgres;

--
-- Name: location_dimension_id_seq; Type: SEQUENCE OWNED BY; Schema: report; Owner: postgres
--

ALTER SEQUENCE location_dimension_id_seq OWNED BY location_dimension.id;


--
-- Name: location_dimension_id_seq; Type: SEQUENCE SET; Schema: report; Owner: postgres
--

SELECT pg_catalog.setval('location_dimension_id_seq', 2442, true);


--
-- Name: registration_measure; Type: TABLE; Schema: report; Owner: postgres; Tablespace: 
--

CREATE TABLE registration_measure (
    id integer NOT NULL,
    location_id smallint,
    time_id integer,
    flw_id bigint
);


ALTER TABLE report.registration_measure OWNER TO postgres;

--
-- Name: registration_measure_id_seq; Type: SEQUENCE; Schema: report; Owner: postgres
--

CREATE SEQUENCE registration_measure_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE report.registration_measure_id_seq OWNER TO postgres;

--
-- Name: registration_measure_id_seq; Type: SEQUENCE OWNED BY; Schema: report; Owner: postgres
--

ALTER SEQUENCE registration_measure_id_seq OWNED BY registration_measure.id;


--
-- Name: registration_measure_id_seq; Type: SEQUENCE SET; Schema: report; Owner: postgres
--

SELECT pg_catalog.setval('registration_measure_id_seq', 34267, true);


--
-- Name: sms_sent_measure; Type: TABLE; Schema: report; Owner: postgres; Tablespace: 
--

CREATE TABLE sms_sent_measure (
    id integer NOT NULL,
    flw_id bigint,
    time_id integer,
    course_attempt integer,
    sms_sent boolean,
    sms_reference_number character varying(30),
    location_id bigint
);


ALTER TABLE report.sms_sent_measure OWNER TO postgres;

--
-- Name: sms_sent_measure_id_seq; Type: SEQUENCE; Schema: report; Owner: postgres
--

CREATE SEQUENCE sms_sent_measure_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE report.sms_sent_measure_id_seq OWNER TO postgres;

--
-- Name: sms_sent_measure_id_seq; Type: SEQUENCE OWNED BY; Schema: report; Owner: postgres
--

ALTER SEQUENCE sms_sent_measure_id_seq OWNED BY sms_sent_measure.id;


--
-- Name: sms_sent_measure_id_seq; Type: SEQUENCE SET; Schema: report; Owner: postgres
--

SELECT pg_catalog.setval('sms_sent_measure_id_seq', 11, true);


--
-- Name: time_dimension; Type: TABLE; Schema: report; Owner: postgres; Tablespace: 
--

CREATE TABLE time_dimension (
    id integer NOT NULL,
    day smallint,
    week smallint,
    month smallint,
    year smallint,
    date date
);


ALTER TABLE report.time_dimension OWNER TO postgres;

--
-- Name: time_dimension_id_seq; Type: SEQUENCE; Schema: report; Owner: postgres
--

CREATE SEQUENCE time_dimension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE report.time_dimension_id_seq OWNER TO postgres;

--
-- Name: time_dimension_id_seq; Type: SEQUENCE OWNED BY; Schema: report; Owner: postgres
--

ALTER SEQUENCE time_dimension_id_seq OWNED BY time_dimension.id;


--
-- Name: time_dimension_id_seq; Type: SEQUENCE SET; Schema: report; Owner: postgres
--

SELECT pg_catalog.setval('time_dimension_id_seq', 732, true);


--
-- Name: id; Type: DEFAULT; Schema: report; Owner: postgres
--

ALTER TABLE call_duration_measure ALTER COLUMN id SET DEFAULT nextval('call_duration_measure_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: report; Owner: postgres
--

ALTER TABLE course_item_dimension ALTER COLUMN id SET DEFAULT nextval('course_item_dimension_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: report; Owner: postgres
--

ALTER TABLE course_item_measure ALTER COLUMN id SET DEFAULT nextval('course_item_measure_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: report; Owner: postgres
--

ALTER TABLE front_line_worker_dimension ALTER COLUMN id SET DEFAULT nextval('front_line_worker_dimension_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: report; Owner: postgres
--

ALTER TABLE job_aid_content_dimension ALTER COLUMN id SET DEFAULT nextval('job_aid_content_dimension_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: report; Owner: postgres
--

ALTER TABLE job_aid_content_measure ALTER COLUMN id SET DEFAULT nextval('job_aid_content_measure_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: report; Owner: postgres
--

ALTER TABLE location_dimension ALTER COLUMN id SET DEFAULT nextval('location_dimension_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: report; Owner: postgres
--

ALTER TABLE registration_measure ALTER COLUMN id SET DEFAULT nextval('registration_measure_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: report; Owner: postgres
--

ALTER TABLE sms_sent_measure ALTER COLUMN id SET DEFAULT nextval('sms_sent_measure_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: report; Owner: postgres
--

ALTER TABLE time_dimension ALTER COLUMN id SET DEFAULT nextval('time_dimension_id_seq'::regclass);


--
-- Data for Name: course_item_dimension; Type: TABLE DATA; Schema: report; Owner: postgres
--

COPY course_item_dimension (id, name, content_id, type, parent_id, file_name, duration) FROM stdin;
2	Chapter 1	5fc654d8ec2bac6c906be72af6704519	CHAPTER	1	\N	\N
3	Chapter 1	5fc654d8ec2bac6c906be72af6704519	QUIZ	1	\N	\N
4	Chapter 1 Lesson 1	5fc654d8ec2bac6c906be72af6705d3a	LESSON	3	\N	\N
6	Chapter 1 Lesson 3	5fc654d8ec2bac6c906be72af670967e	LESSON	3	\N	\N
7	Chapter 1 Lesson 4	5fc654d8ec2bac6c906be72af670b62f	LESSON	3	\N	\N
8	Chapter 1 Question 1	5fc654d8ec2bac6c906be72af670e5c9	QUIZ	3	\N	\N
9	Chapter 1 Question 2	5fc654d8ec2bac6c906be72af670f95c	QUIZ	3	\N	\N
10	Chapter 1 Question 3	5fc654d8ec2bac6c906be72af671130c	QUIZ	3	\N	\N
11	Chapter 1 Question 4	5fc654d8ec2bac6c906be72af67136d7	QUIZ	3	\N	\N
12	Chapter 2	5fc654d8ec2bac6c906be72af6718932	CHAPTER	1	\N	\N
13	Chapter 2	5fc654d8ec2bac6c906be72af6718932	QUIZ	1	\N	\N
15	Chapter 2 Lesson 2	5fc654d8ec2bac6c906be72af671c27d	LESSON	13	\N	\N
16	Chapter 2 Lesson 3	5fc654d8ec2bac6c906be72af671d8e7	LESSON	13	\N	\N
17	Chapter 2 Lesson 4	5fc654d8ec2bac6c906be72af671ef5f	LESSON	13	\N	\N
18	Chapter 2 Question 1	5fc654d8ec2bac6c906be72af6721797	QUIZ	13	\N	\N
19	Chapter 2 Question 2	5fc654d8ec2bac6c906be72af672346a	QUIZ	13	\N	\N
20	Chapter 2 Question 3	5fc654d8ec2bac6c906be72af6725bae	QUIZ	13	\N	\N
21	Chapter 2 Question 4	5fc654d8ec2bac6c906be72af6726ee9	QUIZ	13	\N	\N
22	Chapter 3	5fc654d8ec2bac6c906be72af672af54	CHAPTER	1	\N	\N
23	Chapter 3	5fc654d8ec2bac6c906be72af672af54	QUIZ	1	\N	\N
25	Chapter 3 Lesson 2	5fc654d8ec2bac6c906be72af672d399	LESSON	23	\N	\N
26	Chapter 3 Lesson 3	5fc654d8ec2bac6c906be72af672e7f5	LESSON	23	\N	\N
27	Chapter 3 Lesson 4	5fc654d8ec2bac6c906be72af6730283	LESSON	23	\N	\N
28	Chapter 3 Question 1	5fc654d8ec2bac6c906be72af6731d84	QUIZ	23	\N	\N
29	Chapter 3 Question 2	5fc654d8ec2bac6c906be72af67342ad	QUIZ	23	\N	\N
30	Chapter 3 Question 3	5fc654d8ec2bac6c906be72af673599d	QUIZ	23	\N	\N
32	Chapter 4	5fc654d8ec2bac6c906be72af673aebd	CHAPTER	1	\N	\N
33	Chapter 4	5fc654d8ec2bac6c906be72af673aebd	QUIZ	1	\N	\N
34	Chapter 4 Lesson 1	5fc654d8ec2bac6c906be72af673c60c	LESSON	33	\N	\N
35	Chapter 4 Lesson 2	5fc654d8ec2bac6c906be72af673e84b	LESSON	33	\N	\N
36	Chapter 4 Lesson 3	5fc654d8ec2bac6c906be72af6740435	LESSON	33	\N	\N
37	Chapter 4 Lesson 4	5fc654d8ec2bac6c906be72af6741048	LESSON	33	\N	\N
38	Chapter 4 Question 1	5fc654d8ec2bac6c906be72af67436c5	QUIZ	33	\N	\N
39	Chapter 4 Question 2	5fc654d8ec2bac6c906be72af6744e93	QUIZ	33	\N	\N
41	Chapter 4 Question 4	5fc654d8ec2bac6c906be72af67489a3	QUIZ	33	\N	\N
42	Chapter 5	5fc654d8ec2bac6c906be72af674d4a6	CHAPTER	1	\N	\N
43	Chapter 5	5fc654d8ec2bac6c906be72af674d4a6	QUIZ	1	\N	\N
44	Chapter 5 Lesson 1	5fc654d8ec2bac6c906be72af674f674	LESSON	43	\N	\N
45	Chapter 5 Lesson 2	5fc654d8ec2bac6c906be72af6750f48	LESSON	43	\N	\N
46	Chapter 5 Lesson 3	5fc654d8ec2bac6c906be72af6751886	LESSON	43	\N	\N
47	Chapter 5 Lesson 4	5fc654d8ec2bac6c906be72af67538c9	LESSON	43	\N	\N
48	Chapter 5 Question 1	5fc654d8ec2bac6c906be72af6756469	QUIZ	43	\N	\N
50	Chapter 5 Question 3	5fc654d8ec2bac6c906be72af675a632	QUIZ	43	\N	\N
51	Chapter 5 Question 4	5fc654d8ec2bac6c906be72af675bf44	QUIZ	43	\N	\N
52	Chapter 6	5fc654d8ec2bac6c906be72af6761079	CHAPTER	1	\N	\N
53	Chapter 6	5fc654d8ec2bac6c906be72af6761079	QUIZ	1	\N	\N
54	Chapter 6 Lesson 1	5fc654d8ec2bac6c906be72af6763544	LESSON	53	\N	\N
55	Chapter 6 Lesson 2	5fc654d8ec2bac6c906be72af676508e	LESSON	53	\N	\N
56	Chapter 6 Lesson 3	5fc654d8ec2bac6c906be72af676743c	LESSON	53	\N	\N
57	Chapter 6 Lesson 4	5fc654d8ec2bac6c906be72af6768c8d	LESSON	53	\N	\N
59	Chapter 6 Question 2	5fc654d8ec2bac6c906be72af676d0da	QUIZ	53	\N	\N
60	Chapter 6 Question 3	5fc654d8ec2bac6c906be72af676ebfd	QUIZ	53	\N	\N
61	Chapter 6 Question 4	5fc654d8ec2bac6c906be72af677137c	QUIZ	53	\N	\N
62	Chapter 7	5fc654d8ec2bac6c906be72af6774dd0	CHAPTER	1	\N	\N
63	Chapter 7	5fc654d8ec2bac6c906be72af6774dd0	QUIZ	1	\N	\N
64	Chapter 7 Lesson 1	5fc654d8ec2bac6c906be72af6775f85	LESSON	63	\N	\N
65	Chapter 7 Lesson 2	5fc654d8ec2bac6c906be72af6777b74	LESSON	63	\N	\N
66	Chapter 7 Lesson 3	5fc654d8ec2bac6c906be72af6778f3a	LESSON	63	\N	\N
68	Chapter 7 Question 1	5fc654d8ec2bac6c906be72af677d2fa	QUIZ	63	\N	\N
69	Chapter 7 Question 2	5fc654d8ec2bac6c906be72af677f96f	QUIZ	63	\N	\N
70	Chapter 7 Question 3	5fc654d8ec2bac6c906be72af6781ec3	QUIZ	63	\N	\N
71	Chapter 7 Question 4	5fc654d8ec2bac6c906be72af6783612	QUIZ	63	\N	\N
72	Chapter 8	5fc654d8ec2bac6c906be72af6786731	CHAPTER	1	\N	\N
73	Chapter 8	5fc654d8ec2bac6c906be72af6786731	QUIZ	1	\N	\N
74	Chapter 8 Lesson 1	5fc654d8ec2bac6c906be72af678756e	LESSON	73	\N	\N
75	Chapter 8 Lesson 2	5fc654d8ec2bac6c906be72af67887f4	LESSON	73	\N	\N
77	Chapter 8 Lesson 4	5fc654d8ec2bac6c906be72af678b7f4	LESSON	73	\N	\N
78	Chapter 8 Question 1	5fc654d8ec2bac6c906be72af678e2ac	QUIZ	73	\N	\N
79	Chapter 8 Question 2	5fc654d8ec2bac6c906be72af678f8d8	QUIZ	73	\N	\N
80	Chapter 8 Question 3	5fc654d8ec2bac6c906be72af6791489	QUIZ	73	\N	\N
81	Chapter 8 Question 4	5fc654d8ec2bac6c906be72af6792cf1	QUIZ	73	\N	\N
82	Chapter 9	5fc654d8ec2bac6c906be72af67972a5	CHAPTER	1	\N	\N
83	Chapter 9	5fc654d8ec2bac6c906be72af67972a5	QUIZ	1	\N	\N
84	Chapter 9 Lesson 1	5fc654d8ec2bac6c906be72af679821d	LESSON	83	\N	\N
86	Chapter 9 Lesson 3	5fc654d8ec2bac6c906be72af6799d1c	LESSON	83	\N	\N
87	Chapter 9 Lesson 4	5fc654d8ec2bac6c906be72af679bd3d	LESSON	83	\N	\N
88	Chapter 9 Question 1	5fc654d8ec2bac6c906be72af679f0ce	QUIZ	83	\N	\N
89	Chapter 9 Question 2	5fc654d8ec2bac6c906be72af67a1101	QUIZ	83	\N	\N
90	Chapter 9 Question 3	5fc654d8ec2bac6c906be72af67a257b	QUIZ	83	\N	\N
1	CertificationCourse	5fc654d8ec2bac6c906be72af6700403	COURSE	\N	\N	\N
92	menu:0029_ch1_end_op.wav	7a823ae22badc42018c6542c597c46ef	AUDIO	3	0029_ch1_end_op.wav	25399
93	quizHeader:0011_ch1_qp.wav	7a823ae22badc42018c6542c597c4d3b	AUDIO	3	0011_ch1_qp.wav	96289
94	score 0:0011_ch1_qp.wav	7a823ae22badc42018c6542c597c5d02	AUDIO	3	0011_ch1_qp.wav	96289
95	score 1:0011_ch1_qp.wav	7a823ae22badc42018c6542c597c6a4d	AUDIO	3	0011_ch1_qp.wav	96289
96	score 2:0011_ch1_qp.wav	7a823ae22badc42018c6542c597c79a0	AUDIO	3	0011_ch1_qp.wav	96289
97	score 3:0011_ch1_qp.wav	7a823ae22badc42018c6542c597c8673	AUDIO	3	0011_ch1_qp.wav	96289
98	score 4:0011_ch1_qp.wav	7a823ae22badc42018c6542c597c8dd9	AUDIO	3	0011_ch1_qp.wav	96289
99	lesson:0003_ch1_l1.wav	7a823ae22badc42018c6542c597c9520	AUDIO	4	0003_ch1_l1.wav	197685
100	menu:0004_ch1_l1_op.wav	7a823ae22badc42018c6542c597ca259	AUDIO	4	0004_ch1_l1_op.wav	21935
5	Chapter 1 Lesson 2	5fc654d8ec2bac6c906be72af67081e0	LESSON	3	\N	\N
101	lesson:0005_ch1_l2.wav	7a823ae22badc42018c6542c597ca530	AUDIO	5	0005_ch1_l2.wav	191776
102	menu:0006_ch1_l2_op.wav	7a823ae22badc42018c6542c597cb156	AUDIO	5	0006_ch1_l2_op.wav	21190
103	lesson:0007_ch1_l3.wav	7a823ae22badc42018c6542c597cbb0e	AUDIO	6	0007_ch1_l3.wav	160491
104	menu:0008_ch1_l3_op.wav	7a823ae22badc42018c6542c597cc1d2	AUDIO	6	0008_ch1_l3_op.wav	19914
105	lesson:0009_ch1_l4.wav	7a823ae22badc42018c6542c597cced8	AUDIO	7	0009_ch1_l4.wav	156892
106	menu:0010_ch1_l4_op.wav	7a823ae22badc42018c6542c597ccf1c	AUDIO	7	0010_ch1_l4_op.wav	58201
107	question:0012_ch1_q1.wav	7a823ae22badc42018c6542c597cdcdd	AUDIO	8	0012_ch1_q1.wav	39079
108	correct:0013_ch1_q1_ca.wav	7a823ae22badc42018c6542c597cdea4	AUDIO	8	0013_ch1_q1_ca.wav	17906
109	incorrect:0014_ch1_q1_wa.wav	7a823ae22badc42018c6542c597ce60b	AUDIO	8	0014_ch1_q1_wa.wav	20596
110	question:0015_ch1_q2.wav	7a823ae22badc42018c6542c597ce839	AUDIO	9	0015_ch1_q2.wav	35596
111	correct:0016_ch1_q2_ca.wav	7a823ae22badc42018c6542c597cf360	AUDIO	9	0016_ch1_q2_ca.wav	23624
112	incorrect:0017_ch1_q2_wa.wav	7a823ae22badc42018c6542c597d01aa	AUDIO	9	0017_ch1_q2_wa.wav	21631
113	question:0018_ch1_q3.wav	7a823ae22badc42018c6542c597d0ca2	AUDIO	10	0018_ch1_q3.wav	36360
114	correct:0019_ch1_q3_ca.wav	7a823ae22badc42018c6542c597d13db	AUDIO	10	0019_ch1_q3_ca.wav	19012
115	incorrect:0020_ch1_q3_wa.wav	7a823ae22badc42018c6542c597d1984	AUDIO	10	0020_ch1_q3_wa.wav	20951
116	question:0021_ch1_q4.wav	7a823ae22badc42018c6542c597d1d84	AUDIO	11	0021_ch1_q4.wav	43188
117	correct:0022_ch1_q4_ca.wav	7a823ae22badc42018c6542c597d2971	AUDIO	11	0022_ch1_q4_ca.wav	22018
118	incorrect:0023_ch1_q4_wa.wav	7a823ae22badc42018c6542c597d3209	AUDIO	11	0023_ch1_q4_wa.wav	20714
119	menu:0056_ch2_end_op.wav	7a823ae22badc42018c6542c597d3cfc	AUDIO	13	0056_ch2_end_op.wav	26144
120	quizHeader:0038_ch2_qp.wav	7a823ae22badc42018c6542c597d4732	AUDIO	13	0038_ch2_qp.wav	16660
121	score 0:0038_ch2_qp.wav	7a823ae22badc42018c6542c597d4c7f	AUDIO	13	0038_ch2_qp.wav	16660
122	score 1:0038_ch2_qp.wav	7a823ae22badc42018c6542c597d58e1	AUDIO	13	0038_ch2_qp.wav	16660
123	score 2:0038_ch2_qp.wav	7a823ae22badc42018c6542c597d604b	AUDIO	13	0038_ch2_qp.wav	16660
124	score 3:0038_ch2_qp.wav	7a823ae22badc42018c6542c597d6791	AUDIO	13	0038_ch2_qp.wav	16660
125	score 4:0038_ch2_qp.wav	7a823ae22badc42018c6542c597d685e	AUDIO	13	0038_ch2_qp.wav	16660
14	Chapter 2 Lesson 1	5fc654d8ec2bac6c906be72af671a686	LESSON	13	\N	\N
126	lesson:0030_ch2_l1.wav	7a823ae22badc42018c6542c597d6d71	AUDIO	14	0030_ch2_l1.wav	220007
127	menu:0031_ch2_l1_op.wav	7a823ae22badc42018c6542c597d6e43	AUDIO	14	0031_ch2_l1_op.wav	19992
128	lesson:0032_ch2_l2.wav	7a823ae22badc42018c6542c597d7ad1	AUDIO	15	0032_ch2_l2.wav	189085
129	menu:0033_ch2_l2_op.wav	7a823ae22badc42018c6542c597d87f4	AUDIO	15	0033_ch2_l2_op.wav	20893
130	lesson:0034_ch2_l3.wav	7a823ae22badc42018c6542c597d9024	AUDIO	16	0034_ch2_l3.wav	192135
131	menu:0035_ch2_l3_op.wav	7a823ae22badc42018c6542c597d9f87	AUDIO	16	0035_ch2_l3_op.wav	19288
132	lesson:0036_ch2_l4.wav	7a823ae22badc42018c6542c597daf55	AUDIO	17	0036_ch2_l4.wav	178044
133	menu:0037_ch2_l4_op.wav	7a823ae22badc42018c6542c597dbf38	AUDIO	17	0037_ch2_l4_op.wav	47654
134	question:0039_ch2_q1.wav	7a823ae22badc42018c6542c597dcedf	AUDIO	18	0039_ch2_q1.wav	31222
135	correct:0040_ch2_q1_ca.wav	7a823ae22badc42018c6542c597ddb78	AUDIO	18	0040_ch2_q1_ca.wav	12995
136	incorrect:0041_ch2_q1_wa.wav	7a823ae22badc42018c6542c597de1c4	AUDIO	18	0041_ch2_q1_wa.wav	14627
137	question:0042_ch2_q2.wav	7a823ae22badc42018c6542c597de46c	AUDIO	19	0042_ch2_q2.wav	55277
138	correct:0043_ch2_q2_ca.wav	7a823ae22badc42018c6542c597de983	AUDIO	19	0043_ch2_q2_ca.wav	22222
139	incorrect:0044_ch2_q2_wa.wav	7a823ae22badc42018c6542c597df229	AUDIO	19	0044_ch2_q2_wa.wav	20271
140	question:0045_ch2_q3.wav	7a823ae22badc42018c6542c597df60d	AUDIO	20	0045_ch2_q3.wav	42057
141	correct:0046_ch2_q3_ca.wav	7a823ae22badc42018c6542c597e05de	AUDIO	20	0046_ch2_q3_ca.wav	20601
142	incorrect:0047_ch2_q3_wa.wav	7a823ae22badc42018c6542c597e1454	AUDIO	20	0047_ch2_q3_wa.wav	19625
143	question:0048_ch2_q4.wav	7a823ae22badc42018c6542c597e178f	AUDIO	21	0048_ch2_q4.wav	43491
144	correct:0049_ch2_q4_ca.wav	7a823ae22badc42018c6542c597e1805	AUDIO	21	0049_ch2_q4_ca.wav	20304
145	incorrect:0050_ch2_q4_wa.wav	7a823ae22badc42018c6542c597e1dbf	AUDIO	21	0050_ch2_q4_wa.wav	19014
146	menu:0083_ch3_end_op.wav	7a823ae22badc42018c6542c597e2671	AUDIO	23	0083_ch3_end_op.wav	24853
147	quizHeader:0065_ch3_qp.wav	7a823ae22badc42018c6542c597e26dd	AUDIO	23	0065_ch3_qp.wav	16448
148	score 0:0065_ch3_qp.wav	7a823ae22badc42018c6542c597e3382	AUDIO	23	0065_ch3_qp.wav	16448
149	score 1:0065_ch3_qp.wav	7a823ae22badc42018c6542c597e433a	AUDIO	23	0065_ch3_qp.wav	16448
150	score 2:0065_ch3_qp.wav	7a823ae22badc42018c6542c597e4a1f	AUDIO	23	0065_ch3_qp.wav	16448
151	score 3:0065_ch3_qp.wav	7a823ae22badc42018c6542c597e53e8	AUDIO	23	0065_ch3_qp.wav	16448
152	score 4:0065_ch3_qp.wav	7a823ae22badc42018c6542c597e6206	AUDIO	23	0065_ch3_qp.wav	16448
24	Chapter 3 Lesson 1	5fc654d8ec2bac6c906be72af672be56	LESSON	23	\N	\N
153	lesson:0057_ch3_l1.wav	7a823ae22badc42018c6542c597e66a6	AUDIO	24	0057_ch3_l1.wav	221080
154	menu:0058_ch3_l1_op.wav	7a823ae22badc42018c6542c597e68a9	AUDIO	24	0058_ch3_l1_op.wav	20324
155	lesson:0059_ch3_l2.wav	7a823ae22badc42018c6542c597e73f6	AUDIO	25	0059_ch3_l2.wav	193301
156	menu:0060_ch3_l2_op.wav	7a823ae22badc42018c6542c597e7c24	AUDIO	25	0060_ch3_l2_op.wav	19201
157	lesson:0061_ch3_l3.wav	7a823ae22badc42018c6542c597e8403	AUDIO	26	0061_ch3_l3.wav	175288
158	menu:0062_ch3_l3_op.wav	7a823ae22badc42018c6542c597e9198	AUDIO	26	0062_ch3_l3_op.wav	17841
159	lesson:0063_ch3_l4.wav	7a823ae22badc42018c6542c597e9e13	AUDIO	27	0063_ch3_l4.wav	176505
160	menu:0064_ch3_l4_op.wav	7a823ae22badc42018c6542c597eaa01	AUDIO	27	0064_ch3_l4_op.wav	48744
161	question:0066_ch3_q1.wav	7a823ae22badc42018c6542c597eaccc	AUDIO	28	0066_ch3_q1.wav	34394
162	correct:0067_ch3_q1_ca.wav	7a823ae22badc42018c6542c597eb850	AUDIO	28	0067_ch3_q1_ca.wav	18549
163	incorrect:0068_ch3_q1_wa.wav	7a823ae22badc42018c6542c597ebbbc	AUDIO	28	0068_ch3_q1_wa.wav	20761
164	question:0069_ch3_q2.wav	7a823ae22badc42018c6542c597ec980	AUDIO	29	0069_ch3_q2.wav	38239
165	correct:0070_ch3_q2_ca.wav	7a823ae22badc42018c6542c597ed927	AUDIO	29	0070_ch3_q2_ca.wav	23309
166	incorrect:0071_ch3_q2_wa.wav	7a823ae22badc42018c6542c597ee6d7	AUDIO	29	0071_ch3_q2_wa.wav	20636
167	question:0072_ch3_q3.wav	7a823ae22badc42018c6542c597eec4c	AUDIO	30	0072_ch3_q3.wav	37874
168	correct:0073_ch3_q3_ca.wav	7a823ae22badc42018c6542c597ef54f	AUDIO	30	0073_ch3_q3_ca.wav	18430
169	incorrect:0074_ch3_q3_wa.wav	7a823ae22badc42018c6542c597ef75f	AUDIO	30	0074_ch3_q3_wa.wav	21308
31	Chapter 3 Question 4	5fc654d8ec2bac6c906be72af6737906	QUIZ	23	\N	\N
170	question:0075_ch3_q4.wav	7a823ae22badc42018c6542c597f024c	AUDIO	31	0075_ch3_q4.wav	34574
171	correct:0076_ch3_q4_ca.wav	7a823ae22badc42018c6542c597f07e5	AUDIO	31	0076_ch3_q4_ca.wav	22968
172	incorrect:0077_ch3_q4_wa.wav	7a823ae22badc42018c6542c597f1378	AUDIO	31	0077_ch3_q4_wa.wav	20352
173	menu:0110_ch4_end_op.wav	7a823ae22badc42018c6542c597f1714	AUDIO	33	0110_ch4_end_op.wav	23961
174	quizHeader:0092_ch4_qp.wav	7a823ae22badc42018c6542c597f1c9e	AUDIO	33	0092_ch4_qp.wav	16800
175	score 0:0092_ch4_qp.wav	7a823ae22badc42018c6542c597f1ede	AUDIO	33	0092_ch4_qp.wav	16800
176	score 1:0092_ch4_qp.wav	7a823ae22badc42018c6542c597f2146	AUDIO	33	0092_ch4_qp.wav	16800
177	score 2:0092_ch4_qp.wav	7a823ae22badc42018c6542c597f2916	AUDIO	33	0092_ch4_qp.wav	16800
178	score 3:0092_ch4_qp.wav	7a823ae22badc42018c6542c597f3036	AUDIO	33	0092_ch4_qp.wav	16800
179	score 4:0092_ch4_qp.wav	7a823ae22badc42018c6542c597f3665	AUDIO	33	0092_ch4_qp.wav	16800
180	lesson:0084_ch4_l1.wav	7a823ae22badc42018c6542c597f4107	AUDIO	34	0084_ch4_l1.wav	232450
181	menu:0085_ch4_l1_op.wav	7a823ae22badc42018c6542c597f4413	AUDIO	34	0085_ch4_l1_op.wav	20321
182	lesson:0086_ch4_l2.wav	7a823ae22badc42018c6542c597f47c5	AUDIO	35	0086_ch4_l2.wav	183627
183	menu:0087_ch4_l2_op.wav	7a823ae22badc42018c6542c597f4ef2	AUDIO	35	0087_ch4_l2_op.wav	19141
184	lesson:0088_ch4_l3.wav	7a823ae22badc42018c6542c597f5126	AUDIO	36	0088_ch4_l3.wav	181909
185	menu:0089_ch4_l3_op.wav	7a823ae22badc42018c6542c597f5b63	AUDIO	36	0089_ch4_l3_op.wav	17751
186	lesson:0090_ch4_l4.wav	7a823ae22badc42018c6542c597f6af1	AUDIO	37	0090_ch4_l4.wav	165642
187	menu:0091_ch4_l4_op.wav	7a823ae22badc42018c6542c597f79c3	AUDIO	37	0091_ch4_l4_op.wav	48262
188	question:0093_ch4_q1.wav	7a823ae22badc42018c6542c597f84df	AUDIO	38	0093_ch4_q1.wav	35006
189	correct:0094_ch4_q1_ca.wav	7a823ae22badc42018c6542c597f91b6	AUDIO	38	0094_ch4_q1_ca.wav	19572
190	incorrect:0095_ch4_q1_wa.wav	7a823ae22badc42018c6542c597f9a04	AUDIO	38	0095_ch4_q1_wa.wav	20879
191	question:0096_ch4_q2.wav	7a823ae22badc42018c6542c597fa1b8	AUDIO	39	0096_ch4_q2.wav	45343
192	correct:0097_ch4_q2_ca.wav	7a823ae22badc42018c6542c597fa907	AUDIO	39	0097_ch4_q2_ca.wav	23501
193	incorrect:0098_ch4_q2_wa.wav	7a823ae22badc42018c6542c597fb145	AUDIO	39	0098_ch4_q2_wa.wav	21368
40	Chapter 4 Question 3	5fc654d8ec2bac6c906be72af6746678	QUIZ	33	\N	\N
194	question:0099_ch4_q3.wav	7a823ae22badc42018c6542c597fb632	AUDIO	40	0099_ch4_q3.wav	39408
195	correct:0100_ch4_q3_ca.wav	7a823ae22badc42018c6542c597fbbf8	AUDIO	40	0100_ch4_q3_ca.wav	19825
196	incorrect:0101_ch4_q3_wa.wav	7a823ae22badc42018c6542c597fc3be	AUDIO	40	0101_ch4_q3_wa.wav	21947
197	question:0102_ch4_q4.wav	7a823ae22badc42018c6542c597fd235	AUDIO	41	0102_ch4_q4.wav	34364
198	correct:0103_ch4_q4_ca.wav	7a823ae22badc42018c6542c597fd49c	AUDIO	41	0103_ch4_q4_ca.wav	20436
199	incorrect:0104_ch4_q4_wa.wav	7a823ae22badc42018c6542c597fd9d5	AUDIO	41	0104_ch4_q4_wa.wav	18643
200	menu:0137_ch5_end_op.wav	7a823ae22badc42018c6542c597fe698	AUDIO	43	0137_ch5_end_op.wav	23577
201	quizHeader:0119_ch5_qp.wav	7a823ae22badc42018c6542c597ff463	AUDIO	43	0119_ch5_qp.wav	16471
202	score 0:0119_ch5_qp.wav	7a823ae22badc42018c6542c597fff83	AUDIO	43	0119_ch5_qp.wav	16471
203	score 1:0119_ch5_qp.wav	7a823ae22badc42018c6542c59800d9c	AUDIO	43	0119_ch5_qp.wav	16471
204	score 2:0119_ch5_qp.wav	7a823ae22badc42018c6542c5980138e	AUDIO	43	0119_ch5_qp.wav	16471
205	score 3:0119_ch5_qp.wav	7a823ae22badc42018c6542c59801c1d	AUDIO	43	0119_ch5_qp.wav	16471
206	score 4:0119_ch5_qp.wav	7a823ae22badc42018c6542c5980268d	AUDIO	43	0119_ch5_qp.wav	16471
207	lesson:0111_ch5_l1.wav	7a823ae22badc42018c6542c5980296e	AUDIO	44	0111_ch5_l1.wav	233424
208	menu:0112_ch5_l1_op.wav	7a823ae22badc42018c6542c598031e0	AUDIO	44	0112_ch5_l1_op.wav	20194
209	lesson:0113_ch5_l2.wav	7a823ae22badc42018c6542c59803a3a	AUDIO	45	0113_ch5_l2.wav	200158
210	menu:0114_ch5_l2_op.wav	7a823ae22badc42018c6542c59803f30	AUDIO	45	0114_ch5_l2_op.wav	21624
211	lesson:0115_ch5_l3.wav	7a823ae22badc42018c6542c5980473b	AUDIO	46	0115_ch5_l3.wav	185452
212	menu:0116_ch5_l3_op.wav	7a823ae22badc42018c6542c59805369	AUDIO	46	0116_ch5_l3_op.wav	23171
213	lesson:0117_ch5_l4.wav	7a823ae22badc42018c6542c59805716	AUDIO	47	0117_ch5_l4.wav	185430
214	menu:0118_ch5_l4_op.wav	7a823ae22badc42018c6542c59805965	AUDIO	47	0118_ch5_l4_op.wav	47596
215	question:0120_ch5_q1.wav	7a823ae22badc42018c6542c598064ef	AUDIO	48	0120_ch5_q1.wav	39382
216	correct:0121_ch5_q1_ca.wav	7a823ae22badc42018c6542c59806ebd	AUDIO	48	0121_ch5_q1_ca.wav	18438
217	incorrect:0122_ch5_q1_wa.wav	7a823ae22badc42018c6542c598070a4	AUDIO	48	0122_ch5_q1_wa.wav	20182
49	Chapter 5 Question 2	5fc654d8ec2bac6c906be72af6758971	QUIZ	43	\N	\N
218	question:0123_ch5_q2.wav	7a823ae22badc42018c6542c598079b8	AUDIO	49	0123_ch5_q2.wav	41745
219	correct:0124_ch5_q2_ca.wav	7a823ae22badc42018c6542c5980816c	AUDIO	49	0124_ch5_q2_ca.wav	22265
220	incorrect:0125_ch5_q2_wa.wav	7a823ae22badc42018c6542c59808e75	AUDIO	49	0125_ch5_q2_wa.wav	20671
221	question:0126_ch5_q3.wav	7a823ae22badc42018c6542c59809ad7	AUDIO	50	0126_ch5_q3.wav	35093
222	correct:0127_ch5_q3_ca.wav	7a823ae22badc42018c6542c59809bdb	AUDIO	50	0127_ch5_q3_ca.wav	20637
223	incorrect:0128_ch5_q3_wa.wav	7a823ae22badc42018c6542c5980a0a6	AUDIO	50	0128_ch5_q3_wa.wav	19806
224	question:0129_ch5_q4.wav	7a823ae22badc42018c6542c5980aa0e	AUDIO	51	0129_ch5_q4.wav	43208
225	correct:0130_ch5_q4_ca.wav	7a823ae22badc42018c6542c5980b2c3	AUDIO	51	0130_ch5_q4_ca.wav	21248
226	incorrect:0131_ch5_q4_wa.wav	7a823ae22badc42018c6542c5980b6b5	AUDIO	51	0131_ch5_q4_wa.wav	21167
227	menu:0164_ch6_end_op.wav	7a823ae22badc42018c6542c5980c47f	AUDIO	53	0164_ch6_end_op.wav	24102
228	quizHeader:0146_ch6_qp.wav	7a823ae22badc42018c6542c5980ccb1	AUDIO	53	0146_ch6_qp.wav	16969
229	score 0:0146_ch6_qp.wav	7a823ae22badc42018c6542c5980cfce	AUDIO	53	0146_ch6_qp.wav	16969
230	score 1:0146_ch6_qp.wav	7a823ae22badc42018c6542c5980d47e	AUDIO	53	0146_ch6_qp.wav	16969
231	score 2:0146_ch6_qp.wav	7a823ae22badc42018c6542c5980de85	AUDIO	53	0146_ch6_qp.wav	16969
232	score 3:0146_ch6_qp.wav	7a823ae22badc42018c6542c5980e7d0	AUDIO	53	0146_ch6_qp.wav	16969
233	score 4:0146_ch6_qp.wav	7a823ae22badc42018c6542c5980ea7c	AUDIO	53	0146_ch6_qp.wav	16969
234	lesson:0138_ch6_l1.wav	7a823ae22badc42018c6542c5980ef99	AUDIO	54	0138_ch6_l1.wav	243223
235	menu:0139_ch6_l1_op.wav	7a823ae22badc42018c6542c5980fb4e	AUDIO	54	0139_ch6_l1_op.wav	22481
236	lesson:0140_ch6_l2.wav	7a823ae22badc42018c6542c59810949	AUDIO	55	0140_ch6_l2.wav	201211
237	menu:0141_ch6_l2_op.wav	7a823ae22badc42018c6542c598111d8	AUDIO	55	0141_ch6_l2_op.wav	18670
238	lesson:0142_ch6_l3.wav	7a823ae22badc42018c6542c59811b9f	AUDIO	56	0142_ch6_l3.wav	193318
239	menu:0143_ch6_l3_op.wav	7a823ae22badc42018c6542c59811cd0	AUDIO	56	0143_ch6_l3_op.wav	22100
240	lesson:0144_ch6_l4.wav	7a823ae22badc42018c6542c598124ad	AUDIO	57	0144_ch6_l4.wav	186703
241	menu:0145_ch6_l4_op.wav	7a823ae22badc42018c6542c59813014	AUDIO	57	0145_ch6_l4_op.wav	48141
58	Chapter 6 Question 1	5fc654d8ec2bac6c906be72af676a4f6	QUIZ	53	\N	\N
242	question:0147_ch6_q1.wav	7a823ae22badc42018c6542c598139b1	AUDIO	58	0147_ch6_q1.wav	36123
243	correct:0148_ch6_q1_ca.wav	7a823ae22badc42018c6542c5981487e	AUDIO	58	0148_ch6_q1_ca.wav	19681
244	incorrect:0149_ch6_q1_wa.wav	7a823ae22badc42018c6542c5981515c	AUDIO	58	0149_ch6_q1_wa.wav	21139
245	question:0150_ch6_q2.wav	7a823ae22badc42018c6542c59815181	AUDIO	59	0150_ch6_q2.wav	42184
246	correct:0151_ch6_q2_ca.wav	7a823ae22badc42018c6542c59815c47	AUDIO	59	0151_ch6_q2_ca.wav	21850
247	incorrect:0152_ch6_q2_wa.wav	7a823ae22badc42018c6542c59816500	AUDIO	59	0152_ch6_q2_wa.wav	20341
248	question:0153_ch6_q3.wav	7a823ae22badc42018c6542c5981718a	AUDIO	60	0153_ch6_q3.wav	38004
249	correct:0154_ch6_q3_ca.wav	7a823ae22badc42018c6542c59817adf	AUDIO	60	0154_ch6_q3_ca.wav	20214
250	incorrect:0155_ch6_q3_wa.wav	7a823ae22badc42018c6542c59818152	AUDIO	60	0155_ch6_q3_wa.wav	19493
251	question:0156_ch6_q4.wav	7a823ae22badc42018c6542c59818272	AUDIO	61	0156_ch6_q4.wav	39670
252	correct:0157_ch6_q4_ca.wav	7a823ae22badc42018c6542c59818e07	AUDIO	61	0157_ch6_q4_ca.wav	20325
253	incorrect:0158_ch6_q4_wa.wav	7a823ae22badc42018c6542c59819ca1	AUDIO	61	0158_ch6_q4_wa.wav	19583
254	menu:0191_ch7_end_op.wav	7a823ae22badc42018c6542c5981abb5	AUDIO	63	0191_ch7_end_op.wav	24044
255	quizHeader:0173_ch7_qp.wav	7a823ae22badc42018c6542c5981b548	AUDIO	63	0173_ch7_qp.wav	16769
256	score 0:0173_ch7_qp.wav	7a823ae22badc42018c6542c5981b977	AUDIO	63	0173_ch7_qp.wav	16769
257	score 1:0173_ch7_qp.wav	7a823ae22badc42018c6542c5981c20b	AUDIO	63	0173_ch7_qp.wav	16769
258	score 2:0173_ch7_qp.wav	7a823ae22badc42018c6542c5981c88b	AUDIO	63	0173_ch7_qp.wav	16769
259	score 3:0173_ch7_qp.wav	7a823ae22badc42018c6542c5981d4f6	AUDIO	63	0173_ch7_qp.wav	16769
260	score 4:0173_ch7_qp.wav	7a823ae22badc42018c6542c5981db4b	AUDIO	63	0173_ch7_qp.wav	16769
261	lesson:0165_ch7_l1.wav	7a823ae22badc42018c6542c5981e888	AUDIO	64	0165_ch7_l1.wav	222500
262	menu:0166_ch7_l1_op.wav	7a823ae22badc42018c6542c5981f411	AUDIO	64	0166_ch7_l1_op.wav	20362
263	lesson:0167_ch7_l2.wav	7a823ae22badc42018c6542c5982022a	AUDIO	65	0167_ch7_l2.wav	229438
264	menu:0168_ch7_l2_op.wav	7a823ae22badc42018c6542c5982107f	AUDIO	65	0168_ch7_l2_op.wav	18832
265	lesson:0169_ch7_l3.wav	7a823ae22badc42018c6542c598218de	AUDIO	66	0169_ch7_l3.wav	183399
266	menu:0170_ch7_l3_op.wav	7a823ae22badc42018c6542c5982223e	AUDIO	66	0170_ch7_l3_op.wav	22085
67	Chapter 7 Lesson 4	5fc654d8ec2bac6c906be72af677a9d3	LESSON	63	\N	\N
267	lesson:0171_ch7_l4.wav	7a823ae22badc42018c6542c59823158	AUDIO	67	0171_ch7_l4.wav	171653
268	menu:0172_ch7_l4_op.wav	7a823ae22badc42018c6542c598235f1	AUDIO	67	0172_ch7_l4_op.wav	47131
269	question:0174_ch7_q1.wav	7a823ae22badc42018c6542c59824463	AUDIO	68	0174_ch7_q1.wav	36899
270	correct:0175_ch7_q1_ca.wav	7a823ae22badc42018c6542c59824caa	AUDIO	68	0175_ch7_q1_ca.wav	19554
271	incorrect:0176_ch7_q1_wa.wav	7a823ae22badc42018c6542c59824ecc	AUDIO	68	0176_ch7_q1_wa.wav	20686
272	question:0177_ch7_q2.wav	7a823ae22badc42018c6542c5982578f	AUDIO	69	0177_ch7_q2.wav	37809
273	correct:0178_ch7_q2_ca.wav	7a823ae22badc42018c6542c59825d2d	AUDIO	69	0178_ch7_q2_ca.wav	23640
274	incorrect:0179_ch7_q2_wa.wav	7a823ae22badc42018c6542c5982648e	AUDIO	69	0179_ch7_q2_wa.wav	21601
275	question:0180_ch7_q3.wav	7a823ae22badc42018c6542c59826eb6	AUDIO	70	0180_ch7_q3.wav	39554
276	correct:0181_ch7_q3_ca.wav	7a823ae22badc42018c6542c598270bb	AUDIO	70	0181_ch7_q3_ca.wav	19850
277	incorrect:0182_ch7_q3_wa.wav	7a823ae22badc42018c6542c598272d0	AUDIO	70	0182_ch7_q3_wa.wav	21605
278	question:0183_ch7_q4.wav	7a823ae22badc42018c6542c59827616	AUDIO	71	0183_ch7_q4.wav	35113
279	correct:0184_ch7_q4_ca.wav	7a823ae22badc42018c6542c59827781	AUDIO	71	0184_ch7_q4_ca.wav	20352
280	incorrect:0185_ch7_q4_wa.wav	7a823ae22badc42018c6542c59827db6	AUDIO	71	0185_ch7_q4_wa.wav	18389
281	menu:0218_ch8_end_op.wav	7a823ae22badc42018c6542c5982875e	AUDIO	73	0218_ch8_end_op.wav	22418
282	quizHeader:0200_ch8_qp.wav	7a823ae22badc42018c6542c598292fd	AUDIO	73	0200_ch8_qp.wav	16493
283	score 0:0200_ch8_qp.wav	7a823ae22badc42018c6542c59829d7f	AUDIO	73	0200_ch8_qp.wav	16493
284	score 1:0200_ch8_qp.wav	7a823ae22badc42018c6542c5982a3bc	AUDIO	73	0200_ch8_qp.wav	16493
285	score 2:0200_ch8_qp.wav	7a823ae22badc42018c6542c5982ac5b	AUDIO	73	0200_ch8_qp.wav	16493
286	score 3:0200_ch8_qp.wav	7a823ae22badc42018c6542c5982b69d	AUDIO	73	0200_ch8_qp.wav	16493
287	score 4:0200_ch8_qp.wav	7a823ae22badc42018c6542c5982c09f	AUDIO	73	0200_ch8_qp.wav	16493
288	lesson:0192_ch8_l1.wav	7a823ae22badc42018c6542c5982cfab	AUDIO	74	0192_ch8_l1.wav	241264
289	menu:0193_ch8_l1_op.wav	7a823ae22badc42018c6542c5982d686	AUDIO	74	0193_ch8_l1_op.wav	20368
290	lesson:0194_ch8_l2.wav	7a823ae22badc42018c6542c5982de41	AUDIO	75	0194_ch8_l2.wav	190396
291	menu:0195_ch8_l2_op.wav	7a823ae22badc42018c6542c5982e312	AUDIO	75	0195_ch8_l2_op.wav	18707
76	Chapter 8 Lesson 3	5fc654d8ec2bac6c906be72af6789fbf	LESSON	73	\N	\N
292	lesson:0196_ch8_l3.wav	7a823ae22badc42018c6542c5982eaaa	AUDIO	76	0196_ch8_l3.wav	201015
293	menu:0197_ch8_l3_op.wav	7a823ae22badc42018c6542c5982f6af	AUDIO	76	0197_ch8_l3_op.wav	22164
294	lesson:0198_ch8_l4.wav	7a823ae22badc42018c6542c5982fa67	AUDIO	77	0198_ch8_l4.wav	171713
295	menu:0199_ch8_l4_op.wav	7a823ae22badc42018c6542c5983091e	AUDIO	77	0199_ch8_l4_op.wav	47699
296	question:0201_ch8_q1.wav	7a823ae22badc42018c6542c59830e44	AUDIO	78	0201_ch8_q1.wav	40916
297	correct:0202_ch8_q1_ca.wav	7a823ae22badc42018c6542c59831d56	AUDIO	78	0202_ch8_q1_ca.wav	23419
298	incorrect:0203_ch8_q1_wa.wav	7a823ae22badc42018c6542c598328c8	AUDIO	78	0203_ch8_q1_wa.wav	21284
299	question:0204_ch8_q2.wav	7a823ae22badc42018c6542c59832a54	AUDIO	79	0204_ch8_q2.wav	43199
300	correct:0205_ch8_q2_ca.wav	7a823ae22badc42018c6542c59832daf	AUDIO	79	0205_ch8_q2_ca.wav	20147
301	incorrect:0206_ch8_q2_wa.wav	7a823ae22badc42018c6542c598334a6	AUDIO	79	0206_ch8_q2_wa.wav	19963
302	question:0207_ch8_q3.wav	7a823ae22badc42018c6542c59833caf	AUDIO	80	0207_ch8_q3.wav	38308
303	correct:0208_ch8_q3_ca.wav	7a823ae22badc42018c6542c59834115	AUDIO	80	0208_ch8_q3_ca.wav	20400
304	incorrect:0209_ch8_q3_wa.wav	7a823ae22badc42018c6542c598341a0	AUDIO	80	0209_ch8_q3_wa.wav	20425
305	question:0210_ch8_q4.wav	7a823ae22badc42018c6542c598347c0	AUDIO	81	0210_ch8_q4.wav	38507
306	correct:0211_ch8_q4_ca.wav	7a823ae22badc42018c6542c59834ac3	AUDIO	81	0211_ch8_q4_ca.wav	20177
307	incorrect:0212_ch8_q4_wa.wav	7a823ae22badc42018c6542c59834f8c	AUDIO	81	0212_ch8_q4_wa.wav	19059
308	menu:0245_ch9_end_op.wav	7a823ae22badc42018c6542c5983540c	AUDIO	83	0245_ch9_end_op.wav	15284
309	quizHeader:0227_ch9_qp.wav	7a823ae22badc42018c6542c598358f8	AUDIO	83	0227_ch9_qp.wav	16668
310	score 0:0227_ch9_qp.wav	7a823ae22badc42018c6542c59835abe	AUDIO	83	0227_ch9_qp.wav	16668
311	score 1:0227_ch9_qp.wav	7a823ae22badc42018c6542c59836070	AUDIO	83	0227_ch9_qp.wav	16668
312	score 2:0227_ch9_qp.wav	7a823ae22badc42018c6542c59836887	AUDIO	83	0227_ch9_qp.wav	16668
313	score 3:0227_ch9_qp.wav	7a823ae22badc42018c6542c598374f5	AUDIO	83	0227_ch9_qp.wav	16668
314	score 4:0227_ch9_qp.wav	7a823ae22badc42018c6542c598375f9	AUDIO	83	0227_ch9_qp.wav	16668
315	lesson:0219_ch9_l1.wav	7a823ae22badc42018c6542c59838013	AUDIO	84	0219_ch9_l1.wav	229848
316	menu:0220_ch9_l1_op.wav	7a823ae22badc42018c6542c59838bbc	AUDIO	84	0220_ch9_l1_op.wav	20445
85	Chapter 9 Lesson 2	5fc654d8ec2bac6c906be72af6799238	LESSON	83	\N	\N
317	lesson:0221_ch9_l2.wav	7a823ae22badc42018c6542c59839b2f	AUDIO	85	0221_ch9_l2.wav	201977
318	menu:0222_ch9_l2_op.wav	7a823ae22badc42018c6542c5983a13c	AUDIO	85	0222_ch9_l2_op.wav	18689
319	lesson:0223_ch9_l3.wav	7a823ae22badc42018c6542c5983b09e	AUDIO	86	0223_ch9_l3.wav	192115
320	menu:0224_ch9_l3_op.wav	7a823ae22badc42018c6542c5983bb42	AUDIO	86	0224_ch9_l3_op.wav	21948
321	lesson:0225_ch9_l4.wav	7a823ae22badc42018c6542c5983bce7	AUDIO	87	0225_ch9_l4.wav	176411
322	menu:0226_ch9_l4_op.wav	7a823ae22badc42018c6542c5983bd1d	AUDIO	87	0226_ch9_l4_op.wav	49067
323	question:0228_ch9_q1.wav	7a823ae22badc42018c6542c5983c012	AUDIO	88	0228_ch9_q1.wav	30631
324	correct:0229_ch9_q1_ca.wav	7a823ae22badc42018c6542c5983c473	AUDIO	88	0229_ch9_q1_ca.wav	19529
325	incorrect:0230_ch9_q1_wa.wav	7a823ae22badc42018c6542c5983cfc7	AUDIO	88	0230_ch9_q1_wa.wav	19360
326	question:0231_ch9_q2.wav	7a823ae22badc42018c6542c5983d091	AUDIO	89	0231_ch9_q2.wav	37557
327	correct:0232_ch9_q2_ca.wav	7a823ae22badc42018c6542c5983dbd7	AUDIO	89	0232_ch9_q2_ca.wav	19824
328	incorrect:0233_ch9_q2_wa.wav	7a823ae22badc42018c6542c5983e0c6	AUDIO	89	0233_ch9_q2_wa.wav	21520
329	question:0234_ch9_q3.wav	7a823ae22badc42018c6542c5983e8f3	AUDIO	90	0234_ch9_q3.wav	35521
330	correct:0235_ch9_q3_ca.wav	7a823ae22badc42018c6542c5983ec66	AUDIO	90	0235_ch9_q3_ca.wav	21341
331	incorrect:0236_ch9_q3_wa.wav	7a823ae22badc42018c6542c5983ee32	AUDIO	90	0236_ch9_q3_wa.wav	19825
91	Chapter 9 Question 4	5fc654d8ec2bac6c906be72af67a3a93	QUIZ	83	\N	\N
332	question:0237_ch9_q4.wav	7a823ae22badc42018c6542c5983fd0d	AUDIO	91	0237_ch9_q4.wav	41818
333	correct:0238_ch9_q4_ca.wav	7a823ae22badc42018c6542c59840a8c	AUDIO	91	0238_ch9_q4_ca.wav	20043
334	incorrect:0239_ch9_q4_wa.wav	7a823ae22badc42018c6542c598410ad	AUDIO	91	0239_ch9_q4_wa.wav	19098
\.


--
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: report; Owner: postgres
--

COPY databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase) FROM stdin;
9	ThoughtWorks-version1.1	/sql/schema-definition-log.xml	2012-05-23 00:20:05.729611+05:30	9	EXECUTED	3:e536159b4573e84f769c6ab2de46621b	Add Column (x3), Add Foreign Key Constraint		\N	2.0.3
10	ThoughtWorks-version1.1	/sql/schema-definition-log.xml	2012-05-23 00:20:05.769056+05:30	10	EXECUTED	3:8890a27d663f39f8b141a77a0f92721c	Add Column (x4), Add Foreign Key Constraint		\N	2.0.3
11	ThoughtWorks-version1.1	/sql/schema-definition-log.xml	2012-05-23 00:20:05.792586+05:30	11	EXECUTED	3:462406e1cf9a7dad9d3b2288c4d56fa1	Add Column, Add Foreign Key Constraint		\N	2.0.3
12	ThoughtWorks-version1.1	/sql/schema-definition-log.xml	2012-05-23 00:20:05.809774+05:30	12	EXECUTED	3:0a7596b159f5044420481fc592425182	Add Column		\N	2.0.3
13	ThoughtWorks-version1.1	/sql/schema-definition-log.xml	2012-05-23 00:20:05.841676+05:30	13	EXECUTED	3:5f8afd880184dc5b318d5a459a430365	Add Column (x3), Add Foreign Key Constraint		\N	2.0.3
14	ThoughtWorks-version1.1	/sql/schema-definition-log.xml	2012-05-23 00:20:05.911896+05:30	14	EXECUTED	3:aeb9f1d8e429ae4e6720f7552e0a9e1f	Create Table, Add Foreign Key Constraint		\N	2.0.3
15	ThoughtWorks-version1.1	/sql/schema-definition-log.xml	2012-05-23 00:20:06.233109+05:30	15	EXECUTED	3:7d38c39fb714b10a524f2bc717a50e40	Create Table, Add Foreign Key Constraint (x3)		\N	2.0.3
16	ThoughtWorks-version1.1	/sql/schema-definition-log.xml	2012-05-23 00:20:08.912602+05:30	16	EXECUTED	3:3a552b1b59b4c43365f6ccdbe6926305	Add Column		\N	2.0.3
17	ThoughtWorks-version1.1	/sql/schema-definition-log.xml	2012-05-23 00:20:09.024308+05:30	17	EXECUTED	3:0c0a68bf4ff1dd3312c74303ad477d8a	Add Column, Add Foreign Key Constraint		\N	2.0.3
18	ThoughtWorks-version1.1	/sql/schema-definition-log.xml	2012-05-23 00:20:09.074353+05:30	18	EXECUTED	3:3ca38d291a7c1002365322fd3bdb162f	Add Column		\N	2.0.3
19	ThoughtWorks-version1.1	/sql/schema-definition-log.xml	2012-05-23 00:20:09.105167+05:30	19	EXECUTED	3:abb1b2c79ed8d74a901b8cb7426f104e	Add Column (x2)		\N	2.0.3
20	ThoughtWorks-version1.1	/sql/schema-definition-log.xml	2012-05-23 00:20:09.33129+05:30	20	EXECUTED	3:7f3803f008226c17acc34ceba67233ae	Create Index (x5)		\N	2.0.3
21	ThoughtWorks-version1.1	/sql/schema-definition-log.xml	2012-05-23 00:20:09.358752+05:30	21	EXECUTED	3:d212790809679352ccea6a1124af2043	Add Column (x2), Add Foreign Key Constraint		\N	2.0.3
22	ThoughtWorks-version1.1	/sql/schema-definition-log.xml	2012-05-23 00:20:09.375513+05:30	22	EXECUTED	3:0788a779c9232c303304d0466729e821	Add Column		\N	2.0.3
23	ThoughtWorks-version1.1	/sql/schema-definition-log.xml	2012-05-23 00:20:09.446842+05:30	23	EXECUTED	3:955689cadb1cd37aec099b0dc2eaa1bb	Create Index		\N	2.0.3
1	ThoughtWorks	/sql/schema-definition-log.xml	2012-05-07 00:13:33.354453+05:30	1	EXECUTED	3:0d46416148a225b1d714080fc8ab720e	Create Table		\N	2.0.3
2	ThoughtWorks	/sql/schema-definition-log.xml	2012-05-07 00:13:33.565498+05:30	2	EXECUTED	3:3e5bcc4340537ba216f52fac79a77674	Create Table, Add Unique Constraint		\N	2.0.3
3	ThoughtWorks	/sql/schema-definition-log.xml	2012-05-07 00:13:33.625848+05:30	3	EXECUTED	3:04521030d2e13022cacd1946951494b3	Create Table		\N	2.0.3
4	ThoughtWorks	/sql/schema-definition-log.xml	2012-05-07 00:13:33.779861+05:30	4	EXECUTED	3:035b90dda59b160aea41ae49eeb25557	Create Table, Create Index (x3), Add Foreign Key Constraint (x3)		\N	2.0.3
5	ThoughtWorks	/sql/schema-definition-log.xml	2012-05-07 00:13:33.85282+05:30	5	EXECUTED	3:5f725220de210473832df0d2d8b6ce94	Create Table, Add Unique Constraint		\N	2.0.3
6	ThoughtWorks	/sql/schema-definition-log.xml	2012-05-07 00:13:33.979361+05:30	6	EXECUTED	3:bf8239cbe991e740897de433ac2f37be	Create Table, Create Index (x3), Add Foreign Key Constraint (x3)		\N	2.0.3
7	ThoughtWorks	/sql/schema-definition-log.xml	2012-05-07 00:13:34.066494+05:30	7	EXECUTED	3:1ce67c0fe85724617caafa6066129340	Create Table, Create Index, Add Foreign Key Constraint		\N	2.0.3
8	ThoughtWorks	/sql/schema-definition-log.xml	2012-05-07 00:13:34.181448+05:30	8	EXECUTED	3:41947a6ec1ad5cc1edb4a4228a18ee68	Create Table, Create Index (x2), Add Foreign Key Constraint (x2)		\N	2.0.3
1	ThoughtWorks-version1.1	/sql/schema-update-log.xml	2012-05-23 00:20:16.402122+05:30	24	EXECUTED	3:0d4889a62a54e547a824395af6d77e7d	Custom SQL (x3)		\N	2.0.3
\.


--
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: report; Owner: postgres
--

COPY databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
1	f	\N	\N
\.



--
-- Data for Name: job_aid_content_dimension; Type: TABLE DATA; Schema: report; Owner: postgres
--

COPY job_aid_content_dimension (id, content_id, parent_id, name, file_name, type, duration, short_code) FROM stdin;
1	7a823ae22badc42018c6542c59098473	\N	JobAidCourse	\N	Level	\N	\N
2	7a823ae22badc42018c6542c590975d0	1	menu	0002_select_level.wav	Audio	42787	\N
3	7a823ae22badc42018c6542c5909944a	1	level 1	\N	Level	\N	\N
4	7a823ae22badc42018c6542c590988e4	3	menu	0003_select_chapter_1.1.wav	Audio	33652	\N
5	7a823ae22badc42018c6542c5909a819	3	Level 1 Chapter 1	\N	Chapter	\N	\N
6	7a823ae22badc42018c6542c59099d87	5	introduction	0007_lessons_1.1.1.wav	Audio	32979	\N
7	7a823ae22badc42018c6542c5909a6e5	5	menu	0008_select_lesson_1.1.1.wav	Audio	23671	\N
8	7a823ae22badc42018c6542c5909b78e	5	Level 1 Chapter 1 Lesson1	\N	Lesson	\N	5771111
9	7a823ae22badc42018c6542c5909ad90	8	lesson	0001_prep_for_child_birth.wav	Audio	96734	5771111
10	7a823ae22badc42018c6542c5909cfbc	5	Level 1 Chapter 1 Lesson2	\N	Lesson	\N	5771112
11	7a823ae22badc42018c6542c5909c51d	10	lesson	0002_ifa_and_tt.wav	Audio	120453	5771112
12	7a823ae22badc42018c6542c5909de46	5	Level 1 Chapter 1 Lesson3	\N	Lesson	\N	5771113
13	7a823ae22badc42018c6542c5909d224	12	lesson	0003_planning.wav	Audio	104822	5771113
14	7a823ae22badc42018c6542c5909f456	5	Level 1 Chapter 1 Lesson4	\N	Lesson	\N	5771114
15	7a823ae22badc42018c6542c5909ec03	14	lesson	0004_planning_and_saving.wav	Audio	106859	5771114
16	7a823ae22badc42018c6542c590a1241	3	Level 1 Chapter 2	\N	Chapter	\N	\N
17	7a823ae22badc42018c6542c5909f91d	16	introduction	0009_lessons_1.1.2.wav	Audio	32323	\N
18	7a823ae22badc42018c6542c590a0409	16	menu	0010_select_lesson_1.1.2.wav	Audio	22253	\N
19	7a823ae22badc42018c6542c590a1c37	16	Level 1 Chapter 2 Lesson1	\N	Lesson	\N	5771121
20	7a823ae22badc42018c6542c590a1352	19	lesson	0005_inst_delivery.wav	Audio	107713	5771121
21	7a823ae22badc42018c6542c590a30aa	16	Level 1 Chapter 2 Lesson2	\N	Lesson	\N	5771122
22	7a823ae22badc42018c6542c590a296c	21	lesson	0006_six_cleans.wav	Audio	120188	5771122
23	7a823ae22badc42018c6542c590a3f47	16	Level 1 Chapter 2 Lesson3	\N	Lesson	\N	5771123
24	7a823ae22badc42018c6542c590a36b6	23	lesson	0007_emereg_for_mother.wav	Audio	110428	5771123
25	7a823ae22badc42018c6542c590a5fef	3	Level 1 Chapter 3	\N	Chapter	\N	\N
26	7a823ae22badc42018c6542c590a46de	25	introduction	0011_lessons_1.1.3.wav	Audio	36244	\N
27	7a823ae22badc42018c6542c590a5517	25	menu	0012_select_lesson_1.1.3.wav	Audio	32771	\N
28	7a823ae22badc42018c6542c590a65e9	25	Level 1 Chapter 3 Lesson1	\N	Lesson	\N	5771124
29	7a823ae22badc42018c6542c590a639a	28	lesson	0008_handling_newborn.wav	Audio	115458	5771124
30	7a823ae22badc42018c6542c590a83e9	25	Level 1 Chapter 3 Lesson2	\N	Lesson	\N	5771131
31	7a823ae22badc42018c6542c590a7503	30	lesson	0009_cord_care.wav	Audio	121834	5771131
32	7a823ae22badc42018c6542c590a9c58	25	Level 1 Chapter 3 Lesson3	\N	Lesson	\N	5771132
33	7a823ae22badc42018c6542c590a9337	32	lesson	0010_thermal_care.wav	Audio	106064	5771132
34	7a823ae22badc42018c6542c590ab165	25	Level 1 Chapter 3 Lesson4	\N	Lesson	\N	5771133
35	7a823ae22badc42018c6542c590aaae4	34	lesson	0011_early_initiation_bf.wav	Audio	120354	5771133
36	7a823ae22badc42018c6542c590acec0	3	Level 1 Chapter 4	\N	Chapter	\N	\N
37	7a823ae22badc42018c6542c590abab7	36	introduction	0013_lessons_1.1.4.wav	Audio	26718	\N
38	7a823ae22badc42018c6542c590ac5b9	36	menu	0014_select_lesson_1.1.4.wav	Audio	27730	\N
39	7a823ae22badc42018c6542c590ae1d9	36	Level 1 Chapter 4 Lesson1	\N	Lesson	\N	5771151
40	7a823ae22badc42018c6542c590ad321	39	lesson	0017_need_fp.wav	Audio	117012	5771151
41	7a823ae22badc42018c6542c590af2bb	36	Level 1 Chapter 4 Lesson2	\N	Lesson	\N	5771152
42	7a823ae22badc42018c6542c590aefae	41	lesson	0018_spacing_and_y.wav	Audio	124719	5771152
43	7a823ae22badc42018c6542c590aff8d	36	Level 1 Chapter 4 Lesson3	\N	Lesson	\N	5771153
44	7a823ae22badc42018c6542c590afa21	43	lesson	0019_limiting_and_y.wav	Audio	105442	5771153
45	7a823ae22badc42018c6542c590b13ed	36	Level 1 Chapter 4 Lesson4	\N	Lesson	\N	5771154
46	7a823ae22badc42018c6542c590b0d0d	45	lesson	0020_options_ppfp.wav	Audio	115268	5771154
47	7a823ae22badc42018c6542c590b225f	1	level 2	\N	Level	\N	\N
48	7a823ae22badc42018c6542c590b1e62	47	menu	0004_select_chapter_1.2.wav	Audio	41841	\N
49	7a823ae22badc42018c6542c590b43ed	47	Level 2 Chapter 1	\N	Chapter	\N	\N
50	7a823ae22badc42018c6542c590b2a77	49	introduction	0015_lessons_1.2.1.wav	Audio	39279	\N
51	7a823ae22badc42018c6542c590b349e	49	menu	0016_select_lesson_1.2.1.wav	Audio	34554	\N
52	7a823ae22badc42018c6542c590b5556	49	Level 2 Chapter 1 Lesson1	\N	Lesson	\N	5771131
53	7a823ae22badc42018c6542c590b4b2b	52	lesson	0009_cord_care.wav	Audio	121834	5771131
54	7a823ae22badc42018c6542c590b67a9	49	Level 2 Chapter 1 Lesson2	\N	Lesson	\N	5771132
55	7a823ae22badc42018c6542c590b5ce2	54	lesson	0010_thermal_care.wav	Audio	106064	5771132
56	7a823ae22badc42018c6542c590b73f8	49	Level 2 Chapter 1 Lesson3	\N	Lesson	\N	5771133
57	7a823ae22badc42018c6542c590b6f50	56	lesson	0011_early_initiation_bf.wav	Audio	120354	5771133
58	7a823ae22badc42018c6542c590b7dc2	49	Level 2 Chapter 1 Lesson4	\N	Lesson	\N	5771134
59	7a823ae22badc42018c6542c590b7c93	58	lesson	0012_pnc_visits.wav	Audio	123584	5771134
60	7a823ae22badc42018c6542c590b90be	47	Level 2 Chapter 2	\N	Chapter	\N	\N
61	7a823ae22badc42018c6542c590b7f0b	60	introduction	0017_lessons_1.2.2.wav	Audio	40193	\N
62	7a823ae22badc42018c6542c590b81cf	60	menu	0018_select_lesson_1.2.2.wav	Audio	38046	\N
63	7a823ae22badc42018c6542c590b95ad	60	Level 2 Chapter 2 Lesson1	\N	Lesson	\N	5771141
64	7a823ae22badc42018c6542c590b9421	63	lesson	0013_emerg_for_mother_after_child_birth.wav	Audio	118369	5771141
65	7a823ae22badc42018c6542c590ba59d	60	Level 2 Chapter 2 Lesson2	\N	Lesson	\N	5771142
66	7a823ae22badc42018c6542c590b999d	65	lesson	0014_danger_signs_preterm_baby.wav	Audio	118361	5771142
67	7a823ae22badc42018c6542c590baecd	60	Level 2 Chapter 2 Lesson3	\N	Lesson	\N	5771143
68	7a823ae22badc42018c6542c590ba7d9	67	lesson	0015_kangaroo_care_preterm_baby.wav	Audio	115630	5771143
69	7a823ae22badc42018c6542c590bbffd	60	Level 2 Chapter 2 Lesson4	\N	Lesson	\N	5771144
70	7a823ae22badc42018c6542c590bb183	69	lesson	0016_danger_signs_sepsis.wav	Audio	108069	5771144
71	7a823ae22badc42018c6542c590bd897	47	Level 2 Chapter 3	\N	Chapter	\N	\N
72	7a823ae22badc42018c6542c590bc85f	71	introduction	0019_lessons_1.2.3.wav	Audio	36740	\N
73	7a823ae22badc42018c6542c590bd441	71	menu	0020_select_lesson_1.2.3.wav	Audio	33398	\N
74	7a823ae22badc42018c6542c590be65a	71	Level 2 Chapter 3 Lesson1	\N	Lesson	\N	5771174
75	7a823ae22badc42018c6542c590bda30	74	lesson	0028_exclusive_bf.wav	Audio	106275	5771174
76	7a823ae22badc42018c6542c590bfd42	71	Level 2 Chapter 3 Lesson2	\N	Lesson	\N	5771181
77	7a823ae22badc42018c6542c590bf426	76	lesson	0029_howto_bf.wav	Audio	118255	5771181
78	7a823ae22badc42018c6542c590c0bb8	71	Level 2 Chapter 3 Lesson3	\N	Lesson	\N	5771171
79	7a823ae22badc42018c6542c590c0b00	78	lesson	0025_immonization_imp_and_res.wav	Audio	120467	5771171
80	7a823ae22badc42018c6542c590c2514	71	Level 2 Chapter 3 Lesson4	\N	Lesson	\N	5771172
81	7a823ae22badc42018c6542c590c167f	80	lesson	0026_immunization_doses.wav	Audio	135660	5771172
82	7a823ae22badc42018c6542c590c3b0c	47	Level 2 Chapter 4	\N	Chapter	\N	\N
83	7a823ae22badc42018c6542c590c31f2	82	introduction	0021_lessons_1.2.4.wav	Audio	33267	\N
84	7a823ae22badc42018c6542c590c3aa2	82	menu	0022_select_lesson_1.2.4.wav	Audio	28985	\N
85	7a823ae22badc42018c6542c590c4eac	82	Level 2 Chapter 4 Lesson1	\N	Lesson	\N	5771151
86	7a823ae22badc42018c6542c590c410a	85	lesson	0017_need_fp.wav	Audio	117012	5771151
87	7a823ae22badc42018c6542c590c6188	82	Level 2 Chapter 4 Lesson2	\N	Lesson	\N	5771154
88	7a823ae22badc42018c6542c590c5c47	87	lesson	0020_options_ppfp.wav	Audio	115268	5771154
89	7a823ae22badc42018c6542c590c6c1e	82	Level 2 Chapter 4 Lesson3	\N	Lesson	\N	5771193
90	7a823ae22badc42018c6542c590c619f	89	lesson	0035_hand_washing_risk_perc.wav	Audio	115264	5771193
91	7a823ae22badc42018c6542c590c7675	82	Level 2 Chapter 4 Lesson4	\N	Lesson	\N	5771194
92	7a823ae22badc42018c6542c590c72fa	91	lesson	0036_hand_washing_when_and_how.wav	Audio	110353	5771194
93	7a823ae22badc42018c6542c590c8e7b	1	level 3	\N	Level	\N	\N
94	7a823ae22badc42018c6542c590c7f68	93	menu	0005_select_chapter_1.3.wav	Audio	36591	\N
95	7a823ae22badc42018c6542c590c9f65	93	Level 3 Chapter 1	\N	Chapter	\N	\N
96	7a823ae22badc42018c6542c590c9e5e	95	introduction	0023_lessons_1.3.1.wav	Audio	31245	\N
97	7a823ae22badc42018c6542c590c9ec1	95	menu	0024_select_lesson_1.3.1.wav	Audio	28405	\N
98	7a823ae22badc42018c6542c590cb23e	95	Level 3 Chapter 1 Lesson1	\N	Lesson	\N	5771151
99	7a823ae22badc42018c6542c590caeb0	98	lesson	0017_need_fp.wav	Audio	117012	5771151
100	7a823ae22badc42018c6542c590cc4fb	95	Level 3 Chapter 1 Lesson2	\N	Lesson	\N	5771152
101	7a823ae22badc42018c6542c590cbfad	100	lesson	0018_spacing_and_y.wav	Audio	124719	5771152
102	7a823ae22badc42018c6542c590cc9ce	95	Level 3 Chapter 1 Lesson3	\N	Lesson	\N	5771153
103	7a823ae22badc42018c6542c590cc52c	102	lesson	0019_limiting_and_y.wav	Audio	105442	5771153
104	7a823ae22badc42018c6542c590cd2b3	95	Level 3 Chapter 1 Lesson4	\N	Lesson	\N	5771154
105	7a823ae22badc42018c6542c590ccdec	104	lesson	0020_options_ppfp.wav	Audio	115268	5771154
106	7a823ae22badc42018c6542c590ceba6	93	Level 3 Chapter 2	\N	Chapter	\N	\N
107	7a823ae22badc42018c6542c590cdc08	106	introduction	0025_lessons_1.3.2.wav	Audio	25863	\N
108	7a823ae22badc42018c6542c590cdf61	106	menu	0026_select_lesson_1.3.2.wav	Audio	22579	\N
109	7a823ae22badc42018c6542c590d0692	106	Level 3 Chapter 2 Lesson1	\N	Lesson	\N	5771161
110	7a823ae22badc42018c6542c590cfb95	109	lesson	0021_tubal_ligation.wav	Audio	118300	5771161
111	7a823ae22badc42018c6542c590d0ead	106	Level 3 Chapter 2 Lesson2	\N	Lesson	\N	5771162
113	7a823ae22badc42018c6542c590d2ce6	106	Level 3 Chapter 2 Lesson3	\N	Lesson	\N	5771163
114	7a823ae22badc42018c6542c590d1d54	113	lesson	0023_injectable.wav	Audio	115630	5771163
115	7a823ae22badc42018c6542c590d45b0	106	Level 3 Chapter 2 Lesson4	\N	Lesson	\N	5771164
116	7a823ae22badc42018c6542c590d3c1a	115	lesson	0024_condoms_and_ocp.wav	Audio	108069	5771164
117	7a823ae22badc42018c6542c590d580d	93	Level 3 Chapter 3	\N	Chapter	\N	\N
118	7a823ae22badc42018c6542c590d4b51	117	introduction	0027_lessons_1.3.3.wav	Audio	37041	\N
119	7a823ae22badc42018c6542c590d52a9	117	menu	0028_select_lesson_1.3.3.wav	Audio	32801	\N
120	7a823ae22badc42018c6542c590d64ed	117	Level 3 Chapter 3 Lesson1	\N	Lesson	\N	5771174
121	7a823ae22badc42018c6542c590d61be	120	lesson	0028_exclusive_bf.wav	Audio	106275	5771174
122	7a823ae22badc42018c6542c590d7ba8	117	Level 3 Chapter 3 Lesson2	\N	Lesson	\N	5771181
123	7a823ae22badc42018c6542c590d71ba	122	lesson	0029_howto_bf.wav	Audio	118255	5771181
124	7a823ae22badc42018c6542c590d929a	117	Level 3 Chapter 3 Lesson3	\N	Lesson	\N	5771182
125	7a823ae22badc42018c6542c590d8434	124	lesson	0030_benefits_mother_bf.wav	Audio	124482	5771182
126	7a823ae22badc42018c6542c590da0ea	117	Level 3 Chapter 3 Lesson4	\N	Lesson	\N	5771183
127	7a823ae22badc42018c6542c590d9b85	126	lesson	0031_comp_feeding.wav	Audio	113575	5771183
128	7a823ae22badc42018c6542c590db2db	93	Level 3 Chapter 4	\N	Chapter	\N	\N
129	7a823ae22badc42018c6542c590da671	128	introduction	0029_lessons_1.3.4.wav	Audio	37428	\N
130	7a823ae22badc42018c6542c590daa12	128	menu	0030_select_lesson_1.3.4.wav	Audio	32944	\N
131	7a823ae22badc42018c6542c590dc496	128	Level 3 Chapter 4 Lesson1	\N	Lesson	\N	5771171
112	7a823ae22badc42018c6542c590d08a7	111	lesson	0022_iud.wav	Audio	138742	5771162
132	7a823ae22badc42018c6542c590dbbc6	131	lesson	0025_immonization_imp_and_res.wav	Audio	120467	5771171
133	7a823ae22badc42018c6542c590dcfd7	128	Level 3 Chapter 4 Lesson2	\N	Lesson	\N	5771172
134	7a823ae22badc42018c6542c590dccec	133	lesson	0026_immunization_doses.wav	Audio	135660	5771172
135	7a823ae22badc42018c6542c590de281	128	Level 3 Chapter 4 Lesson3	\N	Lesson	\N	5771173
136	7a823ae22badc42018c6542c590dda95	135	lesson	0027_immunization_comp.wav	Audio	120683	5771173
137	7a823ae22badc42018c6542c590de617	128	Level 3 Chapter 4 Lesson4	\N	Lesson	\N	5771194
138	7a823ae22badc42018c6542c590de4ce	137	lesson	0036_hand_washing_when_and_how.wav	Audio	106275	5771194
139	7a823ae22badc42018c6542c590def0e	1	level 4	\N	Level	\N	\N
140	7a823ae22badc42018c6542c590de660	139	menu	0006_select_chapter_1.4.wav	Audio	39226	\N
141	7a823ae22badc42018c6542c590e0d74	139	Level 4 Chapter 1	\N	Chapter	\N	\N
142	7a823ae22badc42018c6542c590df8ea	141	introduction	0031_lessons_1.4.1.wav	Audio	31184	\N
143	7a823ae22badc42018c6542c590e0532	141	menu	0032_select_lesson_1.4.1.wav	Audio	28484	\N
144	7a823ae22badc42018c6542c590e22fb	141	Level 4 Chapter 1 Lesson1	\N	Lesson	\N	5771151
145	7a823ae22badc42018c6542c590e1975	144	lesson	0017_need_fp.wav	Audio	117012	5771151
146	7a823ae22badc42018c6542c590e3a9e	141	Level 4 Chapter 1 Lesson2	\N	Lesson	\N	5771152
147	7a823ae22badc42018c6542c590e2ffd	146	lesson	0018_spacing_and_y.wav	Audio	124719	5771152
148	7a823ae22badc42018c6542c590e4836	141	Level 4 Chapter 1 Lesson3	\N	Lesson	\N	5771153
149	7a823ae22badc42018c6542c590e40d0	148	lesson	0019_limiting_and_y.wav	Audio	105442	5771153
150	7a823ae22badc42018c6542c590e5515	141	Level 4 Chapter 1 Lesson4	\N	Lesson	\N	5771154
151	7a823ae22badc42018c6542c590e4851	150	lesson	0020_options_ppfp.wav	Audio	115268	5771154
152	7a823ae22badc42018c6542c590e6795	139	Level 4 Chapter 2	\N	Chapter	\N	\N
153	7a823ae22badc42018c6542c590e590a	152	introduction	0033_lessons_1.4.2.wav	Audio	25821	\N
154	7a823ae22badc42018c6542c590e5e19	152	menu	0034_select_lesson_1.4.2.wav	Audio	22342	\N
155	7a823ae22badc42018c6542c590e6e3f	152	Level 4 Chapter 2 Lesson1	\N	Lesson	\N	5771161
156	7a823ae22badc42018c6542c590e6811	155	lesson	0021_tubal_ligation.wav	Audio	118300	5771161
157	7a823ae22badc42018c6542c590e8910	152	Level 4 Chapter 2 Lesson2	\N	Lesson	\N	5771162
159	7a823ae22badc42018c6542c590e9ae1	152	Level 4 Chapter 2 Lesson3	\N	Lesson	\N	5771163
160	7a823ae22badc42018c6542c590e96f3	159	lesson	0023_injectable.wav	Audio	121198	5771163
161	7a823ae22badc42018c6542c590eab4d	152	Level 4 Chapter 2 Lesson4	\N	Lesson	\N	5771164
162	7a823ae22badc42018c6542c590eaa8d	161	lesson	0024_condoms_and_ocp.wav	Audio	122935	5771164
163	7a823ae22badc42018c6542c590ec342	139	Level 4 Chapter 3	\N	Chapter	\N	\N
164	7a823ae22badc42018c6542c590eb083	163	introduction	0035_lessons_1.4.3.wav	Audio	37446	\N
165	7a823ae22badc42018c6542c590ebed2	163	menu	0036_select_lesson_1.4.3.wav	Audio	34845	\N
166	7a823ae22badc42018c6542c590ed0d4	163	Level 4 Chapter 3 Lesson1	\N	Lesson	\N	5771183
167	7a823ae22badc42018c6542c590ecfe6	166	lesson	0031_comp_feeding.wav	Audio	113575	5771183
168	7a823ae22badc42018c6542c590ee04f	163	Level 4 Chapter 3 Lesson2	\N	Lesson	\N	5771184
169	7a823ae22badc42018c6542c590ed2ae	168	lesson	0032_active_feeding.wav	Audio	110351	5771184
170	7a823ae22badc42018c6542c590eee98	163	Level 4 Chapter 3 Lesson3	\N	Lesson	\N	5771191
171	7a823ae22badc42018c6542c590ee92b	170	lesson	0033_quality_food_hand_washing.wav	Audio	130363	5771191
172	7a823ae22badc42018c6542c590efefa	163	Level 4 Chapter 3 Lesson4	\N	Lesson	\N	5771192
173	7a823ae22badc42018c6542c590ef504	172	lesson	0034_quantity_food_hand_washing.wav	Audio	131009	5771192
174	7a823ae22badc42018c6542c590f16ee	139	Level 4 Chapter 4	\N	Chapter	\N	\N
175	7a823ae22badc42018c6542c590f0c4e	174	introduction	0037_lessons_1.4.4.wav	Audio	33971	\N
176	7a823ae22badc42018c6542c590f10c4	174	menu	0038_select_lesson_1.4.4.wav	Audio	32952	\N
177	7a823ae22badc42018c6542c590f22b4	174	Level 4 Chapter 4 Lesson1	\N	Lesson	\N	5771171
178	7a823ae22badc42018c6542c590f1cd1	177	lesson	0025_immonization_imp_and_res.wav	Audio	120467	5771171
179	7a823ae22badc42018c6542c590f3abd	174	Level 4 Chapter 4 Lesson2	\N	Lesson	\N	5771173
180	7a823ae22badc42018c6542c590f31af	179	lesson	0027_immunization_comp.wav	Audio	120683	5771173
181	7a823ae22badc42018c6542c590f47b4	174	Level 4 Chapter 4 Lesson3	\N	Lesson	\N	5771193
182	7a823ae22badc42018c6542c590f4541	181	lesson	0035_hand_washing_risk_perc.wav	Audio	115264	5771193
183	7a823ae22badc42018c6542c590f5fc5	174	Level 4 Chapter 4 Lesson4	\N	Lesson	\N	5771194
184	7a823ae22badc42018c6542c590f4fec	183	lesson	0036_hand_washing_when_and_how.wav	Audio	110353	5771194
158	7a823ae22badc42018c6542c590e79ab	157	lesson	0022_iud.wav	Audio	138742	5771162
\.



--
-- Data for Name: location_dimension; Type: TABLE DATA; Schema: report; Owner: postgres
--

COPY location_dimension (id, location_id, district, block, panchayat) FROM stdin;
1	S01D000B000V000	C00	C00	
2	S01D001B001V001	Begusarai	Bachhwara	Rashidpur
3	S01D001B001V002	Begusarai	Bachhwara	Charanjivipur
4	S01D001B001V003	Begusarai	Bachhwara	Fateha
5	S01D001B001V004	Begusarai	Bachhwara	Govindpur3
6	S01D001B001V005	Begusarai	Bachhwara	Chamtha 1
7	S01D001B001V006	Begusarai	Bachhwara	Chamtha 2
8	S01D001B001V007	Begusarai	Bachhwara	Chamtha 3
9	S01D001B001V008	Begusarai	Bachhwara	Bisanpur
10	S01D001B001V009	Begusarai	Bachhwara	Dadupur
11	S01D001B001V010	Begusarai	Bachhwara	Rani 1
12	S01D001B001V011	Begusarai	Bachhwara	Rani 2
13	S01D001B001V012	Begusarai	Bachhwara	Rani 3
14	S01D001B001V013	Begusarai	Bachhwara	Bachhwara
15	S01D001B001V014	Begusarai	Bachhwara	Bhikhamchak
16	S01D001B001V015	Begusarai	Bachhwara	Araba
17	S01D001B001V016	Begusarai	Bachhwara	Rudauli
18	S01D001B001V017	Begusarai	Bachhwara	Kadarabad
19	S01D001B001V018	Begusarai	Bachhwara	Godhana
20	S01D001B002V001	Begusarai	Bakhri	Aakha
21	S01D001B002V002	Begusarai	Bakhri	Bagban
22	S01D001B002V003	Begusarai	Bakhri	Bahuwara
23	S01D001B002V004	Begusarai	Bakhri	Bakhari East
24	S01D001B002V005	Begusarai	Bakhri	Bhhyaara
25	S01D001B002V006	Begusarai	Bakhri	Bakhari West
26	S01D001B002V007	Begusarai	Bakhri	Chak Hameed
27	S01D001B002V008	Begusarai	Bakhri	Ghaghara
28	S01D001B002V009	Begusarai	Bakhri	Lauchai
29	S01D001B002V010	Begusarai	Bakhri	Nishara
30	S01D001B002V011	Begusarai	Bakhri	Sahkno
31	S01D001B002V012	Begusarai	Bakhri	Rampur
32	S01D001B002V013	Begusarai	Bakhri	Makkhachak
33	S01D001B002V014	Begusarai	Bakhri	Mohanpur
34	S01D001B002V015	Begusarai	Bakhri	Parihara
35	S01D001B002V016	Begusarai	Bakhri	Ratan
36	S01D001B002V017	Begusarai	Bakhri	Sakarpura
37	S01D001B002V018	Begusarai	Bakhri	Sakhun
38	S01D001B002V019	Begusarai	Bakhri	Sahudharmshala
39	S01D001B002V020	Begusarai	Bakhri	Salauna
40	S01D001B003V001	Begusarai	Balia	Balia Lakhaminia1
41	S01D001B003V002	Begusarai	Balia	Balia Lakhaminia2
42	S01D001B003V003	Begusarai	Balia	Balia Lakhaminia3
43	S01D001B003V004	Begusarai	Balia	Balia Lakhaminia4
44	S01D001B003V005	Begusarai	Balia	Bari Balia North
45	S01D001B003V006	Begusarai	Balia	Bari Balia South
46	S01D001B003V007	Begusarai	Balia	Bariarpur
47	S01D001B003V008	Begusarai	Balia	Bhagatpur
48	S01D001B003V009	Begusarai	Balia	Bhawanandpur
49	S01D001B003V010	Begusarai	Balia	Danauli Fulwari
50	S01D001B003V011	Begusarai	Balia	Fatehpur
51	S01D001B003V012	Begusarai	Balia	Gokhalenagar Bisanpur
52	S01D001B003V013	Begusarai	Balia	Majhanpur
53	S01D001B003V014	Begusarai	Balia	Sahpur
54	S01D001B003V015	Begusarai	Balia	Manserpur
55	S01D001B003V016	Begusarai	Balia	Noorjamapur
56	S01D001B003V017	Begusarai	Balia	Paharpur
57	S01D001B003V018	Begusarai	Balia	Parmanandpur
58	S01D001B003V019	Begusarai	Balia	Pokharia
59	S01D001B003V020	Begusarai	Balia	Rahatpur
60	S01D001B003V021	Begusarai	Balia	Salehchak
61	S01D001B003V022	Begusarai	Balia	Tajpur
62	S01D001B004V001	Begusarai	Barauni	Babhangama
63	S01D001B004V002	Begusarai	Barauni	Bihat1
64	S01D001B004V003	Begusarai	Barauni	Bihat2
65	S01D001B004V004	Begusarai	Barauni	Bihat3
66	S01D001B004V005	Begusarai	Barauni	Bihat4
67	S01D001B004V006	Begusarai	Barauni	Malhipur North
68	S01D001B004V007	Begusarai	Barauni	Malhipur South
69	S01D001B004V008	Begusarai	Barauni	Papraur
70	S01D001B004V009	Begusarai	Barauni	Garahara1
71	S01D001B004V010	Begusarai	Barauni	Garahara2
72	S01D001B004V011	Begusarai	Barauni	Simaria1
73	S01D001B004V012	Begusarai	Barauni	Simaria2
74	S01D001B004V013	Begusarai	Barauni	Rajwara
75	S01D001B004V014	Begusarai	Barauni	Meda Babhangama
76	S01D001B004V015	Begusarai	Barauni	Sahuri
77	S01D001B004V016	Begusarai	Barauni	Peepra Dewas
78	S01D001B004V017	Begusarai	Barauni	Neenga
79	S01D001B004V018	Begusarai	Barauni	Keshawe
80	S01D001B004V019	Begusarai	Barauni	Noorpur
81	S01D001B004V020	Begusarai	Barauni	Mahna
82	S01D001B004V021	Begusarai	Barauni	Bathauli
83	S01D001B004V022	Begusarai	Barauni	Mosadpur
84	S01D001B004V023	Begusarai	Barauni	Hajipur
85	S01D001B004V024	Begusarai	Barauni	Amarpur
86	S01D001B005V001	Begusarai	Begusarai	Ajhaur
87	S01D001B005V002	Begusarai	Begusarai	Amraur Kiratpur
88	S01D001B005V003	Begusarai	Begusarai	Bahadarpur
89	S01D001B005V004	Begusarai	Begusarai	Bandwar
90	S01D001B005V005	Begusarai	Begusarai	Bhairwar
91	S01D001B005V006	Begusarai	Begusarai	Bindpur
92	S01D001B005V007	Begusarai	Begusarai	Bishanpur
93	S01D001B005V008	Begusarai	Begusarai	Barisanwak
94	S01D001B005V009	Begusarai	Begusarai	Basdeopur
95	S01D001B005V010	Begusarai	Begusarai	Bharra
96	S01D001B005V011	Begusarai	Begusarai	Chandpura
97	S01D001B005V012	Begusarai	Begusarai	Chilmil
98	S01D001B005V013	Begusarai	Begusarai	Dhabauli
99	S01D001B005V014	Begusarai	Begusarai	Dumri
100	S01D001B005V015	Begusarai	Begusarai	Etwa
101	S01D001B005V016	Begusarai	Begusarai	Hebatpur
102	S01D001B005V017	Begusarai	Begusarai	Jinedpur
103	S01D001B005V018	Begusarai	Begusarai	Kaith
104	S01D001B005V019	Begusarai	Begusarai	Kaithma
105	S01D001B005V020	Begusarai	Begusarai	Khamhar
106	S01D001B005V021	Begusarai	Begusarai	Kusmaut
107	S01D001B005V022	Begusarai	Begusarai	Laduwara
108	S01D001B005V023	Begusarai	Begusarai	Lakho
109	S01D001B005V024	Begusarai	Begusarai	Masti Fatehpur
110	S01D001B005V025	Begusarai	Begusarai	Mahmadpur Raghunathpur
111	S01D001B005V026	Begusarai	Begusarai	MohanAghu
112	S01D001B005V027	Begusarai	Begusarai	Mohanpur
113	S01D001B005V028	Begusarai	Begusarai	Nagdah
114	S01D001B005V029	Begusarai	Begusarai	Neema
115	S01D001B005V030	Begusarai	Begusarai	Pachmba
116	S01D001B005V031	Begusarai	Begusarai	Panhas
117	S01D001B005V032	Begusarai	Begusarai	Parna
118	S01D001B005V033	Begusarai	Begusarai	Puspura
119	S01D001B005V034	Begusarai	Begusarai	Rachiyahi
120	S01D001B005V035	Begusarai	Begusarai	Rajaura
121	S01D001B005V036	Begusarai	Begusarai	Sankah
122	S01D001B005V037	Begusarai	Begusarai	Shahpur
123	S01D001B005V038	Begusarai	Begusarai	Singhaul
124	S01D001B005V039	Begusarai	Begusarai	Suja
125	S01D001B005V040	Begusarai	Begusarai	Ulao
126	S01D001B006V001	Begusarai	Bhagwanpur	Banwaripur
127	S01D001B006V002	Begusarai	Bhagwanpur	Bhitsari
128	S01D001B006V003	Begusarai	Bhagwanpur	Chandaur
129	S01D001B006V004	Begusarai	Bhagwanpur	Damodarpur
130	S01D001B006V005	Begusarai	Bhagwanpur	Jokia
131	S01D001B006V006	Begusarai	Bhagwanpur	Kali Rasalpur
132	S01D001B006V007	Begusarai	Bhagwanpur	Kazi Rasulpur
133	S01D001B006V008	Begusarai	Bhagwanpur	Kiratpur
134	S01D001B006V009	Begusarai	Bhagwanpur	Lakhanpur
135	S01D001B006V010	Begusarai	Bhagwanpur	Maheshpur
136	S01D001B006V011	Begusarai	Bhagwanpur	Mehdauli
137	S01D001B006V012	Begusarai	Bhagwanpur	Mokhtiyarpur
138	S01D001B006V013	Begusarai	Bhagwanpur	Narharipur
139	S01D001B006V014	Begusarai	Bhagwanpur	Rasalpur
140	S01D001B006V015	Begusarai	Bhagwanpur	Sanjat
141	S01D001B006V016	Begusarai	Bhagwanpur	Teai
142	S01D001B006V017	Begusarai	Bhagwanpur	Takia
143	S01D001B007V001	Begusarai	Birpur	Bhawanandpur
144	S01D001B007V002	Begusarai	Birpur	Birpur East
145	S01D001B007V003	Begusarai	Birpur	Birpur West
146	S01D001B007V004	Begusarai	Birpur	Deehpar
147	S01D001B007V005	Begusarai	Birpur	Genharpur
148	S01D001B007V006	Begusarai	Birpur	Jagdar
149	S01D001B007V007	Begusarai	Birpur	Mozafra
150	S01D001B007V008	Begusarai	Birpur	Naula
151	S01D001B007V009	Begusarai	Birpur	Saraunja
152	S01D001B007V010	Begusarai	Birpur	Parra
153	S01D001B008V001	Begusarai	Cheria Bariyarpur	Basahi
154	S01D001B008V002	Begusarai	Cheria Bariyarpur	Bikarampur
155	S01D001B008V003	Begusarai	Cheria Bariyarpur	Cheria Bariarpur
156	S01D001B008V004	Begusarai	Cheria Bariyarpur	Gopalpur
157	S01D001B008V005	Begusarai	Cheria Bariyarpur	Khanjahapur
158	S01D001B008V006	Begusarai	Cheria Bariyarpur	Kumbhi
159	S01D001B008V007	Begusarai	Cheria Bariyarpur	Majhaul
160	S01D001B008V008	Begusarai	Cheria Bariyarpur	Pabra
161	S01D001B008V009	Begusarai	Cheria Bariyarpur	Sakarbasa
162	S01D001B008V010	Begusarai	Cheria Bariyarpur	Shahpur
163	S01D001B008V011	Begusarai	Cheria Bariyarpur	Sripur
164	S01D001B009V001	Begusarai	Chhaurahi	Amari
165	S01D001B009V002	Begusarai	Chhaurahi	Ejani
166	S01D001B009V003	Begusarai	Chhaurahi	Ekamba
167	S01D001B009V004	Begusarai	Chhaurahi	Malpur
168	S01D001B009V005	Begusarai	Chhaurahi	Narain Pipar
169	S01D001B009V006	Begusarai	Chhaurahi	Parora
170	S01D001B009V007	Begusarai	Chhaurahi	Sahuri
171	S01D001B009V008	Begusarai	Chhaurahi	Sawat
172	S01D001B009V009	Begusarai	Chhaurahi	Sanwat
173	S01D001B009V010	Begusarai	Chhaurahi	Shahpur
174	S01D001B009V011	Begusarai	Chhaurahi	Sihma
175	S01D001B010V001	Begusarai	Dandri	Baunk
176	S01D001B010V002	Begusarai	Dandri	Dendari
177	S01D001B010V003	Begusarai	Dandri	Katahari
178	S01D001B010V004	Begusarai	Dandri	Katarmala
179	S01D001B010V005	Begusarai	Dandri	Katarmala South
180	S01D001B010V006	Begusarai	Dandri	Mahipatol
181	S01D001B010V007	Begusarai	Dandri	Mohanpur
182	S01D001B010V008	Begusarai	Dandri	Pachrukhi
183	S01D001B010V009	Begusarai	Dandri	Rajopur
184	S01D001B010V010	Begusarai	Dandri	Tetari
185	S01D001B011V001	Begusarai	Garhpura	Dunahi
186	S01D001B011V002	Begusarai	Garhpura	Garhpura
187	S01D001B011V003	Begusarai	Garhpura	Korai
188	S01D001B011V004	Begusarai	Garhpura	Koriama
189	S01D001B011V005	Begusarai	Garhpura	Kumharso
190	S01D001B011V006	Begusarai	Garhpura	Malipur
191	S01D001B011V007	Begusarai	Garhpura	Mauji Hari Singh
192	S01D001B011V008	Begusarai	Garhpura	Rajaur
193	S01D001B011V009	Begusarai	Garhpura	Sujanpur
194	S01D001B011V010	Begusarai	Garhpura	Sonama
195	S01D001B012V001	Begusarai	Khodabandpur	Meghaul
196	S01D001B012V002	Begusarai	Khodabandpur	Bariarpur East
197	S01D001B012V003	Begusarai	Khodabandpur	Sagi
198	S01D001B012V004	Begusarai	Khodabandpur	bariarpur West
199	S01D001B012V005	Begusarai	Khodabandpur	Bara
200	S01D001B012V006	Begusarai	Khodabandpur	Daulatpur
201	S01D001B012V007	Begusarai	Khodabandpur	Fafaut
202	S01D001B012V008	Begusarai	Khodabandpur	Khodabandpur
203	S01D001B013V001	Begusarai	Mansoorchak	Satha
204	S01D001B013V002	Begusarai	Mansoorchak	Govindpur1
1288	S01D005B013V007	Patna	Mokama	MALPUR
205	S01D001B013V003	Begusarai	Mansoorchak	Govindpur2
206	S01D001B013V004	Begusarai	Mansoorchak	Shamsa
207	S01D001B013V005	Begusarai	Mansoorchak	Samsa1
208	S01D001B013V006	Begusarai	Mansoorchak	Samsa2
209	S01D001B013V007	Begusarai	Mansoorchak	Bahrampur
210	S01D001B013V008	Begusarai	Mansoorchak	Ganpataul
211	S01D001B013V009	Begusarai	Mansoorchak	Mansoorchak
212	S01D001B014V001	Begusarai	Matihani	Safapur
213	S01D001B014V002	Begusarai	Matihani	Matihani1
214	S01D001B014V003	Begusarai	Matihani	Matihani2
215	S01D001B014V004	Begusarai	Matihani	Sihma
216	S01D001B014V005	Begusarai	Matihani	Sonapur
217	S01D001B014V006	Begusarai	Matihani	Saidpur Ema
218	S01D001B014V007	Begusarai	Matihani	Khorampur
219	S01D001B014V008	Begusarai	Matihani	Ramdiri1
220	S01D001B014V009	Begusarai	Matihani	Ramdiri2
221	S01D001B014V010	Begusarai	Matihani	Ramdiri3
222	S01D001B014V011	Begusarai	Matihani	Ramdiri4
223	S01D001B014V012	Begusarai	Matihani	Balahpur
224	S01D001B014V013	Begusarai	Matihani	Balahpur1
225	S01D001B014V014	Begusarai	Matihani	Balahpur2
226	S01D001B014V015	Begusarai	Matihani	Maniappa
227	S01D001B014V016	Begusarai	Matihani	Dariapur
228	S01D001B014V017	Begusarai	Matihani	Gorgama
229	S01D001B015V001	Begusarai	Navkothi	Babhanganwan
230	S01D001B015V002	Begusarai	Navkothi	Dafarpur
231	S01D001B015V003	Begusarai	Navkothi	Hasanpur Bagar
232	S01D001B015V004	Begusarai	Navkothi	Maheshwara
233	S01D001B015V005	Begusarai	Navkothi	Navkothi
234	S01D001B015V006	Begusarai	Navkothi	Pahsara East
235	S01D001B015V007	Begusarai	Navkothi	Pahsara West
236	S01D001B015V008	Begusarai	Navkothi	Rajakpur
237	S01D001B015V009	Begusarai	Navkothi	Saidpur Bishnupur
238	S01D001B015V010	Begusarai	Navkothi	Samsa
239	S01D001B016V001	Begusarai	Sahebpur Kamal	Bishanpur Ahok
240	S01D001B016V002	Begusarai	Sahebpur Kamal	Chauki
241	S01D001B016V003	Begusarai	Sahebpur Kamal	Fulmallick
242	S01D001B016V004	Begusarai	Sahebpur Kamal	Pachbir
243	S01D001B016V005	Begusarai	Sahebpur Kamal	Raghunathpur Barari
244	S01D001B016V006	Begusarai	Sahebpur Kamal	Raghunathpur Karari
245	S01D001B016V007	Begusarai	Sahebpur Kamal	Rahuwa
246	S01D001B016V008	Begusarai	Sahebpur Kamal	Sabdalpur
247	S01D001B016V009	Begusarai	Sahebpur Kamal	Sadpur East
248	S01D001B016V010	Begusarai	Sahebpur Kamal	Sadpur West
249	S01D001B016V011	Begusarai	Sahebpur Kamal	Sahebpur Kamal East
250	S01D001B016V012	Begusarai	Sahebpur Kamal	Sahebpur Kamal West
251	S01D001B016V013	Begusarai	Sahebpur Kamal	Samastipur
252	S01D001B016V014	Begusarai	Sahebpur Kamal	Sandalpur
253	S01D001B016V015	Begusarai	Sahebpur Kamal	Sanha East
254	S01D001B016V016	Begusarai	Sahebpur Kamal	Sanha North
255	S01D001B016V017	Begusarai	Sahebpur Kamal	Sanha West
256	S01D001B017V001	Begusarai	Shamho Akha Kurha	Akbarpur Barari
257	S01D001B017V002	Begusarai	Shamho Akha Kurha	Saidpur Salha Barari1
258	S01D001B017V003	Begusarai	Shamho Akha Kurha	Saidpur Salha Barari2
259	S01D001B018V001	Begusarai	Teghra	Aadharpur
260	S01D001B018V002	Begusarai	Teghra	Barauni1
261	S01D001B018V003	Begusarai	Teghra	Barauni2
262	S01D001B018V004	Begusarai	Teghra	Barauni3
263	S01D001B018V005	Begusarai	Teghra	Baro North
264	S01D001B018V006	Begusarai	Teghra	Baro South
265	S01D001B018V007	Begusarai	Teghra	Chakdad Madhurapur
266	S01D001B018V008	Begusarai	Teghra	Chilhai
267	S01D001B018V009	Begusarai	Teghra	Dhankaul
268	S01D001B018V010	Begusarai	Teghra	Fulwaria1
269	S01D001B018V011	Begusarai	Teghra	Fulwaria2
270	S01D001B018V012	Begusarai	Teghra	Fulwaria3
271	S01D001B018V013	Begusarai	Teghra	Gaura1
272	S01D001B018V014	Begusarai	Teghra	Gaura2
273	S01D001B018V015	Begusarai	Teghra	Gaura3
274	S01D001B018V016	Begusarai	Teghra	Gaura4
275	S01D001B018V017	Begusarai	Teghra	Gaura5
276	S01D001B018V018	Begusarai	Teghra	Gaura6
277	S01D001B018V019	Begusarai	Teghra	Kirtaul
278	S01D001B018V020	Begusarai	Teghra	Madhurapur
279	S01D001B018V021	Begusarai	Teghra	Nipania
280	S01D001B018V022	Begusarai	Teghra	Nipania Madhurapur
281	S01D001B018V023	Begusarai	Teghra	Pakthaul
282	S01D001B018V024	Begusarai	Teghra	Peepara Dodraj
283	S01D001B018V025	Begusarai	Teghra	Pidhauli
284	S01D001B018V026	Begusarai	Teghra	Raatgaon
285	S01D001B018V027	Begusarai	Teghra	Shokahara1
286	S01D001B018V028	Begusarai	Teghra	Shokahara2
287	S01D002B001V001	East Champaran	Adapur	ANDHRA
288	S01D002B001V002	East Champaran	Adapur	AURAIA
289	S01D002B001V003	East Champaran	Adapur	BAKHARI
290	S01D002B001V004	East Champaran	Adapur	BARWA
291	S01D002B001V005	East Champaran	Adapur	BELWA
292	S01D002B001V006	East Champaran	Adapur	BHAWANIPUR
293	S01D002B001V007	East Champaran	Adapur	BHERIHARI
294	S01D002B001V008	East Champaran	Adapur	DUBAHA
295	S01D002B001V009	East Champaran	Adapur	GAMHARIYA
296	S01D002B001V010	East Champaran	Adapur	HARPUR
297	S01D002B001V011	East Champaran	Adapur	Katkenwa
298	S01D002B001V012	East Champaran	Adapur	KORAIYA
299	S01D002B001V013	East Champaran	Adapur	LAXAMIPUR POKHARIYA
300	S01D002B001V014	East Champaran	Adapur	MAJHARIYA
301	S01D002B001V015	East Champaran	Adapur	MURTIYA
302	S01D002B001V016	East Champaran	Adapur	NAKARDEI
303	S01D002B001V017	East Champaran	Adapur	SHAMPUR
304	S01D002B001V018	East Champaran	Adapur	SIRISIYA KALA
305	S01D002B002V001	East Champaran	Areraj	BABHNAULI
306	S01D002B002V002	East Champaran	Areraj	BAHADURPUR
307	S01D002B002V003	East Champaran	Areraj	CHATIA BARHARWA
308	S01D002B002V004	East Champaran	Areraj	CHATIA CHINTAMANPUR
309	S01D002B002V005	East Champaran	Areraj	MAHARKHA
310	S01D002B002V006	East Champaran	Areraj	MAMARKHATOLA BHAIYA
311	S01D002B002V007	East Champaran	Areraj	MANGURAHAN
312	S01D002B002V008	East Champaran	Areraj	MISRAULIA
313	S01D002B002V009	East Champaran	Areraj	MURA
314	S01D002B002V010	East Champaran	Areraj	NAGDAHAN
315	S01D002B002V011	East Champaran	Areraj	NAWADA
316	S01D002B002V012	East Champaran	Areraj	PIPRA
317	S01D002B002V013	East Champaran	Areraj	RARHIYA
318	S01D002B002V014	East Champaran	Areraj	SAREYA
319	S01D002B003V001	East Champaran	Banjariya	NORTH FULWAR
320	S01D002B003V002	East Champaran	Banjariya	SOUTH FULWAR
321	S01D002B003V003	East Champaran	Banjariya	ROHINIYA
322	S01D002B003V004	East Champaran	Banjariya	JANERWA
323	S01D002B003V005	East Champaran	Banjariya	EAST PANCHRUKHA
324	S01D002B003V006	East Champaran	Banjariya	MADHYA PANCHRUKHA
325	S01D002B003V007	East Champaran	Banjariya	WEST PANCHRUKHA
326	S01D002B003V008	East Champaran	Banjariya	EAST SISWA
327	S01D002B003V009	East Champaran	Banjariya	WEST SISWA
328	S01D002B003V010	East Champaran	Banjariya	AJGARI
329	S01D002B003V011	East Champaran	Banjariya	BANJARIYA
330	S01D002B003V012	East Champaran	Banjariya	CHAILAHA
331	S01D002B003V013	East Champaran	Banjariya	SEMRA
332	S01D002B004V001	East Champaran	Chakia	BAISAHA
333	S01D002B004V002	East Champaran	Chakia	BARAMADIA
334	S01D002B004V003	East Champaran	Chakia	BHIRKHIA
335	S01D002B004V004	East Champaran	Chakia	BISHUNPURA
336	S01D002B004V005	East Champaran	Chakia	CHAKIA
337	S01D002B004V006	East Champaran	Chakia	CHAKWARA
338	S01D002B004V007	East Champaran	Chakia	CHINTAMANPUR
339	S01D002B004V008	East Champaran	Chakia	HARDIYABAD
340	S01D002B004V009	East Champaran	Chakia	HARPPUR
341	S01D002B004V010	East Champaran	Chakia	JAMUNIA
342	S01D002B004V011	East Champaran	Chakia	KUARPUR
343	S01D002B004V012	East Champaran	Chakia	Kunarpur
344	S01D002B004V013	East Champaran	Chakia	KUNAWA
345	S01D002B004V014	East Champaran	Chakia	KUNRIA
346	S01D002B004V015	East Champaran	Chakia	MADHUBAN BEDIBAN
347	S01D002B004V016	East Champaran	Chakia	MADHURAPUR
348	S01D002B004V017	East Champaran	Chakia	MAHUAWA
349	S01D002B004V018	East Champaran	Chakia	RAMGARHMAHUAAWA
350	S01D002B004V019	East Champaran	Chakia	SAGAR
351	S01D002B005V001	East Champaran	Chhauradano	BHATNAHIYA
352	S01D002B005V002	East Champaran	Chhauradano	BHELWA
353	S01D002B005V003	East Champaran	Chhauradano	DARPA
354	S01D002B005V004	East Champaran	Chhauradano	EKADARI
355	S01D002B005V005	East Champaran	Chhauradano	HIRAMNI
356	S01D002B005V006	East Champaran	Chhauradano	JITPUR
357	S01D002B005V007	East Champaran	Chhauradano	JUAFAR
358	S01D002B005V008	East Champaran	Chhauradano	KHAIRWA
359	S01D002B005V009	East Champaran	Chhauradano	KUDARKAT
360	S01D002B005V010	East Champaran	Chhauradano	MAHUAWA
361	S01D002B005V011	East Champaran	Chhauradano	NARKATIA
362	S01D002B005V012	East Champaran	Chhauradano	PAKARIYA
363	S01D002B005V013	East Champaran	Chhauradano	PURAINIA
364	S01D002B005V014	East Champaran	Chhauradano	RAMPUR
365	S01D002B005V015	East Champaran	Chhauradano	TINKONI
366	S01D002B006V001	East Champaran	Chiraiya	KHORA
367	S01D002B006V002	East Champaran	Chiraiya	SARAUGARH
368	S01D002B006V003	East Champaran	Chiraiya	MAHUAWAEAST
369	S01D002B006V004	East Champaran	Chiraiya	MAHUAWAWEST
370	S01D002B006V005	East Champaran	Chiraiya	MADHOPUR
371	S01D002B006V006	East Champaran	Chiraiya	DIPAHI DHARHARWA
372	S01D002B006V007	East Champaran	Chiraiya	RAMPURNORTH
373	S01D002B006V008	East Champaran	Chiraiya	RAMPURSOUTH
374	S01D002B006V009	East Champaran	Chiraiya	MISRAULIA
375	S01D002B006V010	East Champaran	Chiraiya	KHARTARI WEST
376	S01D002B006V011	East Champaran	Chiraiya	KHARTARI MIDLE
377	S01D002B006V012	East Champaran	Chiraiya	KHARTARI EAST
378	S01D002B006V013	East Champaran	Chiraiya	BARAJAIRAM
379	S01D002B006V014	East Champaran	Chiraiya	MIRPUR
380	S01D002B006V015	East Champaran	Chiraiya	RAGHOPUR
381	S01D002B006V016	East Champaran	Chiraiya	MADHUBNAI
382	S01D002B006V017	East Champaran	Chiraiya	SEMRA
383	S01D002B006V018	East Champaran	Chiraiya	HARAJ NURULLAHPUR
384	S01D002B006V019	East Champaran	Chiraiya	PAREWA
385	S01D002B006V020	East Champaran	Chiraiya	KAPOORPAKRI
386	S01D002B006V021	East Champaran	Chiraiya	ROOPHARA
387	S01D002B006V022	East Champaran	Chiraiya	SIRAUNA
388	S01D002B006V023	East Champaran	Chiraiya	HARNARAINA
389	S01D002B007V001	East Champaran	Dhaka	GAHAI
390	S01D002B007V002	East Champaran	Dhaka	KARSAHIA
391	S01D002B007V003	East Champaran	Dhaka	JHAUWARAM
392	S01D002B007V004	East Champaran	Dhaka	GAWANDARI
393	S01D002B007V005	East Champaran	Dhaka	KHARHUA CHAINPUR
394	S01D002B007V006	East Champaran	Dhaka	JATWALIYA
395	S01D002B007V007	East Champaran	Dhaka	TELAHARA KALAN
396	S01D002B007V008	East Champaran	Dhaka	GURHANWA
397	S01D002B007V009	East Champaran	Dhaka	BALUA GUABARI
398	S01D002B007V010	East Champaran	Dhaka	PHULWARIYA
1289	S01D005B022V010	Patna	Danapur	MANAS
399	S01D002B007V011	East Champaran	Dhaka	BARHARWA FATEH MOHAMAD
400	S01D002B007V012	East Champaran	Dhaka	CHANDAN BARA
401	S01D002B007V013	East Champaran	Dhaka	PARARI
402	S01D002B007V014	East Champaran	Dhaka	KARMAWA
403	S01D002B007V015	East Champaran	Dhaka	MALKAUNIYA
404	S01D002B007V016	East Champaran	Dhaka	JAMUA
405	S01D002B007V017	East Champaran	Dhaka	BARHARWA SIWAN
406	S01D002B007V018	East Champaran	Dhaka	BARHARWA LAKHAN SEN
407	S01D002B007V019	East Champaran	Dhaka	DALPAT BISUNPUR
408	S01D002B007V020	East Champaran	Dhaka	PACHPAKRI
409	S01D002B007V021	East Champaran	Dhaka	JHITKAHI
410	S01D002B007V022	East Champaran	Dhaka	BHANDAR
411	S01D002B007V023	East Champaran	Dhaka	BHAGWANPUR
412	S01D002B008V001	East Champaran	Ghorasahan	BAGAHI BHELWA
413	S01D002B008V002	East Champaran	Ghorasahan	BARWA KALA
414	S01D002B008V003	East Champaran	Ghorasahan	BIJAYI
415	S01D002B008V004	East Champaran	Ghorasahan	BISHUNPUR
416	S01D002B008V005	East Champaran	Ghorasahan	GHORASAHAN NORTH
417	S01D002B008V006	East Champaran	Ghorasahan	GHORASAHAN SOUTH
418	S01D002B008V007	East Champaran	Ghorasahan	GURMIA
419	S01D002B008V008	East Champaran	Ghorasahan	JHAROKHAR
420	S01D002B008V009	East Champaran	Ghorasahan	KABAIYA
421	S01D002B008V010	East Champaran	Ghorasahan	KADAMWA
422	S01D002B008V011	East Champaran	Ghorasahan	LAUKHAN
423	S01D002B008V012	East Champaran	Ghorasahan	PURNAHIYA
424	S01D002B008V013	East Champaran	Ghorasahan	SAMANPUR
425	S01D002B008V014	East Champaran	Ghorasahan	SRIPUR
426	S01D002B009V001	East Champaran	Harsidhi	BAORIYADIH
427	S01D002B009V002	East Champaran	Harsidhi	BHADA
428	S01D002B009V003	East Champaran	Harsidhi	BHADOPUR
429	S01D002B009V004	East Champaran	Harsidhi	CHAR RAHIYA
430	S01D002B009V005	East Champaran	Harsidhi	GAYGHAT
431	S01D002B009V006	East Champaran	Harsidhi	GHIWADHAR
432	S01D002B009V007	East Champaran	Harsidhi	HARPUR RAY
433	S01D002B009V008	East Champaran	Harsidhi	HARSIDHI PAKARIYA
434	S01D002B009V009	East Champaran	Harsidhi	Hasuwaha
435	S01D002B009V010	East Champaran	Harsidhi	JAGAPAKAR
436	S01D002B009V011	East Champaran	Harsidhi	KANCHHEDWA
437	S01D002B009V012	East Champaran	Harsidhi	KRITPUR
438	S01D002B009V013	East Champaran	Harsidhi	MANIKPUR
439	S01D002B009V014	East Champaran	Harsidhi	MATH LOHIYAR
440	S01D002B009V015	East Champaran	Harsidhi	MATIYARIYA
441	S01D002B009V016	East Champaran	Harsidhi	MURARPUR
442	S01D002B009V017	East Champaran	Harsidhi	OLAHA MEHATA TOLA
443	S01D002B009V018	East Champaran	Harsidhi	PANAPUR RANJITA
444	S01D002B009V019	East Champaran	Harsidhi	Rampurwa
445	S01D002B009V020	East Champaran	Harsidhi	SONBARWA
446	S01D002B009V021	East Champaran	Harsidhi	UJJAIN LOHIYAR
447	S01D002B010V001	East Champaran	Kalyanpur	BAKHRI
448	S01D002B010V002	East Champaran	Kalyanpur	Bahlolpur Garbi
449	S01D002B010V003	East Champaran	Kalyanpur	BANSH GHAT
450	S01D002B010V004	East Champaran	Kalyanpur	BARHARWA MAHA NAND
451	S01D002B010V005	East Champaran	Kalyanpur	BHUWAN CHHAPRA
452	S01D002B010V006	East Champaran	Kalyanpur	BRINDA BAN
453	S01D002B010V007	East Champaran	Kalyanpur	DARMAHA
454	S01D002B010V008	East Champaran	Kalyanpur	DILAWARPUR
455	S01D002B010V009	East Champaran	Kalyanpur	GARIBA
456	S01D002B010V010	East Champaran	Kalyanpur	KALYANPUR
457	S01D002B010V011	East Champaran	Kalyanpur	Kaithawalia
458	S01D002B010V012	East Champaran	Kalyanpur	KOELA BELWA
459	S01D002B010V013	East Champaran	Kalyanpur	MANI CHHAPRA
460	S01D002B010V014	East Champaran	Kalyanpur	MEDAN SIRISIYA
461	S01D002B010V015	East Champaran	Kalyanpur	NORTH GAWABDRA
462	S01D002B010V016	East Champaran	Kalyanpur	PAKARI DIXIT
463	S01D002B010V017	East Champaran	Kalyanpur	PARSAUNI WAZID
464	S01D002B010V018	East Champaran	Kalyanpur	PATNA
465	S01D002B010V019	East Champaran	Kalyanpur	PIPRA KHEM
466	S01D002B010V020	East Champaran	Kalyanpur	Puran Chhapra
467	S01D002B010V021	East Champaran	Kalyanpur	RAGHU NATHPUR
468	S01D002B010V022	East Champaran	Kalyanpur	RAJPUR
469	S01D002B010V023	East Champaran	Kalyanpur	SHAMBHU CAHK
470	S01D002B010V024	East Champaran	Kalyanpur	SISWA KHARAR
471	S01D002B010V025	East Champaran	Kalyanpur	SISWA SHOBH
472	S01D002B010V026	East Champaran	Kalyanpur	SITALPUR
473	S01D002B010V027	East Champaran	Kalyanpur	SOUTH GAWANDRA
474	S01D002B011V001	East Champaran	Kesaria	BAIRIA
475	S01D002B011V002	East Champaran	Kesaria	BATHNA
476	S01D002B011V003	East Champaran	Kesaria	BIJDHARIEASTSUNDRAPUR
477	S01D002B011V004	East Champaran	Kesaria	Chand Parsa
478	S01D002B011V005	East Champaran	Kesaria	DHEKAHAN
479	S01D002B011V006	East Champaran	Kesaria	EAST KESARIA
480	S01D002B011V007	East Champaran	Kesaria	EAST SUNDRAPUR
481	S01D002B011V008	East Champaran	Kesaria	GONCHHIKUSHAHAR
482	S01D002B011V009	East Champaran	Kesaria	KADHAN
483	S01D002B011V010	East Champaran	Kesaria	KHIJIRPURA BENIPUR
484	S01D002B011V011	East Champaran	Kesaria	MATHIA
485	S01D002B011V012	East Champaran	Kesaria	NORTH HUSSENI
486	S01D002B011V013	East Champaran	Kesaria	RAMPUR KHAJURIA
487	S01D002B011V014	East Champaran	Kesaria	SEMAPUR
488	S01D002B011V015	East Champaran	Kesaria	SOUTH HUSSENI
489	S01D002B011V016	East Champaran	Kesaria	TAJPURPATHAULIA
490	S01D002B011V017	East Champaran	Kesaria	WEST KESARIA LOHARGAWA
491	S01D002B011V018	East Champaran	Kesaria	WEST SAROTAR
492	S01D002B012V001	East Champaran	Kotwa	MACHHARGAWA
493	S01D002B012V002	East Champaran	Kotwa	KARARIYA
494	S01D002B012V003	East Champaran	Kotwa	BARHARWA KALA WEST
495	S01D002B012V004	East Champaran	Kotwa	BARHARWA KALA EAST
496	S01D002B012V005	East Champaran	Kotwa	KOTWA
497	S01D002B012V006	East Champaran	Kotwa	BATHANA
498	S01D002B012V007	East Champaran	Kotwa	MAHARANI BHOPAT
499	S01D002B012V008	East Champaran	Kotwa	JASAULI PATTI
500	S01D002B012V009	East Champaran	Kotwa	POKHRA
501	S01D002B012V010	East Champaran	Kotwa	AHIRAULIYA
502	S01D002B012V011	East Champaran	Kotwa	DUMRA
503	S01D002B012V012	East Champaran	Kotwa	JASAULI
504	S01D002B012V013	East Champaran	Kotwa	GOPI CHHAPRA
505	S01D002B012V014	East Champaran	Kotwa	BHOPATPUR NORTH
506	S01D002B012V015	East Champaran	Kotwa	BHOPATPUR SOUTH
507	S01D002B012V016	East Champaran	Kotwa	JAGIRAHA
508	S01D002B013V001	East Champaran	Madhuban	DULMA
509	S01D002B013V002	East Champaran	Madhuban	BHELWA
510	S01D002B013V003	East Champaran	Madhuban	RUPNI
511	S01D002B013V004	East Champaran	Madhuban	NAURANGIYA MADHOPUR
512	S01D002B013V005	East Champaran	Madhuban	TALIMPUR
513	S01D002B013V006	East Champaran	Madhuban	MADHUBAN NORTH
514	S01D002B013V007	East Champaran	Madhuban	MADHUBAN SOUTH
515	S01D002B013V008	East Champaran	Madhuban	WAJITPUR
516	S01D002B013V009	East Champaran	Madhuban	KOILAHRA
517	S01D002B013V010	East Champaran	Madhuban	SAWANGIYA
518	S01D002B013V011	East Champaran	Madhuban	GARAHIYA
519	S01D002B013V012	East Champaran	Madhuban	KRISHNA NAGAR
520	S01D002B013V013	East Champaran	Madhuban	KAURIYA
521	S01D002B014V001	East Champaran	Mehsi	BAKHARI NAZIR
522	S01D002B014V002	East Champaran	Mehsi	BHIMALPUR
523	S01D002B014V003	East Champaran	Mehsi	Bishambharpur Pahari
524	S01D002B014V004	East Champaran	Mehsi	BHURUKURWA
525	S01D002B014V005	East Champaran	Mehsi	CHAKLALU
526	S01D002B014V006	East Champaran	Mehsi	HARPURNAG
527	S01D002B014V007	East Champaran	Mehsi	JHITKAHIA
528	S01D002B014V008	East Champaran	Mehsi	KASAWAMEHSI
529	S01D002B014V009	East Champaran	Mehsi	KATAHA
530	S01D002B014V010	East Champaran	Mehsi	KOTHIA HARIRAM
531	S01D002B014V011	East Champaran	Mehsi	MIRZAPUR
532	S01D002B014V012	East Champaran	Mehsi	MOHAMADPUR MAJHAULIA
533	S01D002B014V013	East Champaran	Mehsi	NONIMAL
534	S01D002B014V014	East Champaran	Mehsi	PARSAUNI DEWAJIT
535	S01D002B014V015	East Champaran	Mehsi	PARTAPUR
536	S01D002B014V016	East Champaran	Mehsi	RAJEPUR
537	S01D002B015V001	East Champaran	Motihari	BARDAHA
538	S01D002B015V002	East Champaran	Motihari	Bankat
539	S01D002B015V003	East Champaran	Motihari	BARIYARPUR
540	S01D002B015V004	East Champaran	Motihari	BARWA
541	S01D002B015V005	East Champaran	Motihari	BASMANPUR
542	S01D002B015V006	East Champaran	Motihari	CHANDRAHIYA
543	S01D002B015V007	East Champaran	Motihari	CHHATAUNI AMAR
544	S01D002B015V008	East Champaran	Motihari	Dostia
545	S01D002B015V009	East Champaran	Motihari	DHRUV LAKHAURA
546	S01D002B015V010	East Champaran	Motihari	GORHWA
547	S01D002B015V011	East Champaran	Motihari	Jamla
548	S01D002B015V012	East Champaran	Motihari	JHITKAHIYA
549	S01D002B015V013	East Champaran	Motihari	KATHA
550	S01D002B015V014	East Champaran	Motihari	Lachhmipur
551	S01D002B015V015	East Champaran	Motihari	MADHUBANI GHAT
552	S01D002B015V016	East Champaran	Motihari	NAURANGIYA
553	S01D002B015V017	East Champaran	Motihari	NORTH DHEKAHAN
554	S01D002B015V018	East Champaran	Motihari	PATAURA
555	S01D002B015V019	East Champaran	Motihari	RAMGARWA
556	S01D002B015V020	East Champaran	Motihari	RAMSINGH CHHATAUNI
557	S01D002B015V021	East Champaran	Motihari	RULAHI
558	S01D002B015V022	East Champaran	Motihari	SIRSA MAL
559	S01D002B015V023	East Champaran	Motihari	TIKULIYA
560	S01D002B015V024	East Champaran	Motihari	WEST DHEKAHA
561	S01D002B016V001	East Champaran	Paharpur	Batraulia
562	S01D002B016V002	East Champaran	Paharpur	Bankatwa
563	S01D002B016V003	East Champaran	Paharpur	Bharwaliya
564	S01D002B016V004	East Champaran	Paharpur	BALUA
565	S01D002B016V005	East Champaran	Paharpur	DAKSHINI NONWEYA
566	S01D002B016V006	East Champaran	Paharpur	ENARWABHAR
567	S01D002B016V007	East Champaran	Paharpur	Ganga Pipra
568	S01D002B016V008	East Champaran	Paharpur	KAMAL PIPARA
569	S01D002B016V009	East Champaran	Paharpur	KOTWA
570	S01D002B016V010	East Champaran	Paharpur	MANJHARIYA
571	S01D002B016V011	East Champaran	Paharpur	MANKARARIYA
572	S01D002B016V012	East Champaran	Paharpur	NAUWADIH
573	S01D002B016V013	East Champaran	Paharpur	PARSWANI
574	S01D002B016V014	East Champaran	Paharpur	PASCHHIMI SISWA
575	S01D002B016V015	East Champaran	Paharpur	PASCHIMI SAREYAN
576	S01D002B016V016	East Champaran	Paharpur	PURBI SAREYA
577	S01D002B016V017	East Champaran	Paharpur	PURBI SISWA
578	S01D002B016V018	East Champaran	Paharpur	SONWAL
579	S01D002B016V019	East Champaran	Paharpur	TEJPURWA
580	S01D002B016V020	East Champaran	Paharpur	Panditpururf Bandarhelwa
581	S01D002B016V021	East Champaran	Paharpur	Ibrahimpur
582	S01D002B016V022	East Champaran	Paharpur	Siswa
583	S01D002B016V023	East Champaran	Paharpur	UTTARI NONEYA
584	S01D002B017V001	East Champaran	Pakaridayal	AJGARWA SISHANI
585	S01D002B017V002	East Champaran	Pakaridayal	BARKA GAW
2368	S01D004B004V000	Khagaria	Parbatta	
586	S01D002B017V003	East Champaran	Pakaridayal	CHAITA
587	S01D002B017V004	East Champaran	Pakaridayal	CHORMA
588	S01D002B017V005	East Champaran	Pakaridayal	DHANAUJI
589	S01D002B017V006	East Champaran	Pakaridayal	PAKARDAYAL SOUTH
590	S01D002B017V007	East Champaran	Pakaridayal	PAKARIDAYAL NORTH
591	S01D002B017V008	East Champaran	Pakaridayal	RAJEPUR NAWADA
592	S01D002B017V009	East Champaran	Pakaridayal	SIRAHA
593	S01D002B017V010	East Champaran	Pakaridayal	SUNDAR PATTI
594	S01D002B017V011	East Champaran	Pakaridayal	THARBITIYA
595	S01D002B018V001	East Champaran	Patahi	BALUWA ZULFEKARA BAD
596	S01D002B018V002	East Champaran	Patahi	SARAIYA GOPAL
597	S01D002B018V003	East Champaran	Patahi	PATAHI EAST
598	S01D002B018V004	East Champaran	Patahi	PATAHI WEST
599	S01D002B018V005	East Champaran	Patahi	PARSAUNI KAPUR
600	S01D002B018V006	East Champaran	Patahi	BOKANE KALA
601	S01D002B018V007	East Champaran	Patahi	BARA SHANKAR
602	S01D002B018V008	East Champaran	Patahi	NUNFARWA
603	S01D002B018V009	East Champaran	Patahi	PADUMKER
604	S01D002B018V010	East Champaran	Patahi	DEWAPUR
605	S01D002B018V011	East Champaran	Patahi	JIHULI
606	S01D002B018V012	East Champaran	Patahi	BAKHARI
607	S01D002B018V013	East Champaran	Patahi	BETAUNA
608	S01D002B018V014	East Champaran	Patahi	BELAHI RAM
609	S01D002B018V015	East Champaran	Patahi	GONAHI
610	S01D002B019V001	East Champaran	Phenhara	KHAN PIPRA MADHURAPUR
611	S01D002B019V002	East Champaran	Phenhara	RUPAULIYA
612	S01D002B019V003	East Champaran	Phenhara	MANKARWA
613	S01D002B019V004	East Champaran	Phenhara	FENHARA
614	S01D002B019V005	East Champaran	Phenhara	BARA PARSAUNI
615	S01D002B019V006	East Champaran	Phenhara	MADHUBANI
616	S01D002B020V001	East Champaran	Piprakothi	TIKAITAGOVINDAPUR
617	S01D002B020V002	East Champaran	Piprakothi	SALEMPUR
618	S01D002B020V003	East Champaran	Piprakothi	PANDITPUR
619	S01D002B020V004	East Champaran	Piprakothi	SURUJPUR
620	S01D002B020V005	East Champaran	Piprakothi	BIRCHAPRA
621	S01D002B020V006	East Champaran	Piprakothi	DAKHINI DHEKHA
622	S01D002B021V001	East Champaran	Ramgarhwa	ADHAKAPARIYA
623	S01D002B021V002	East Champaran	Ramgarhwa	AHIRAULIA
624	S01D002B021V003	East Champaran	Ramgarhwa	AMODEI
625	S01D002B021V004	East Champaran	Ramgarhwa	BAIRIYA
626	S01D002B021V005	East Champaran	Ramgarhwa	Bandhu Barwa
627	S01D002B021V006	East Champaran	Ramgarhwa	BELA
628	S01D002B021V007	East Champaran	Ramgarhwa	CHAMPAPUR
629	S01D002B021V008	East Champaran	Ramgarhwa	DHANHAR DHIHULI
630	S01D002B021V009	East Champaran	Ramgarhwa	JAITAPUR
631	S01D002B021V010	East Champaran	Ramgarhwa	MURLA
632	S01D002B021V011	East Champaran	Ramgarhwa	PATANI
633	S01D002B021V012	East Champaran	Ramgarhwa	PKHANAHIYA
634	S01D002B021V013	East Champaran	Ramgarhwa	RAGHU NATHPUR
635	S01D002B021V014	East Champaran	Ramgarhwa	RAMGARHWA
636	S01D002B021V015	East Champaran	Ramgarhwa	SAKRAR
637	S01D002B021V016	East Champaran	Ramgarhwa	SHIVA NAGAR
638	S01D002B021V017	East Champaran	Ramgarhwa	SINGASANI
639	S01D002B022V001	East Champaran	Raxaul	BHELAHI
640	S01D002B022V002	East Champaran	Raxaul	DHANGADHAWA KOURIHAR
641	S01D002B022V003	East Champaran	Raxaul	HARDIA
642	S01D002B022V004	East Champaran	Raxaul	HARNAHI
643	S01D002B022V005	East Champaran	Raxaul	JOKIYARI
644	S01D002B022V006	East Champaran	Raxaul	LAUKARIA
645	S01D002B022V007	East Champaran	Raxaul	LUXMIPUR LACHHUMANWA
646	S01D002B022V008	East Champaran	Raxaul	NONEADIH
647	S01D002B022V009	East Champaran	Raxaul	PALANWA JAGDHAR
648	S01D002B022V010	East Champaran	Raxaul	PANTOKA
649	S01D002B022V011	East Champaran	Raxaul	PARSAUNA TAPSI
650	S01D002B022V012	East Champaran	Raxaul	PURENDRA
651	S01D002B022V013	East Champaran	Raxaul	SISWA
652	S01D002B023V001	East Champaran	Sangrampur	PASCHIMI SANGRAMPUR
653	S01D002B023V002	East Champaran	Sangrampur	PURBI SANGRAMPUR
654	S01D002B023V003	East Champaran	Sangrampur	UTTARI BARIYARIA
655	S01D002B023V004	East Champaran	Sangrampur	DAKSHANI BARIYARIA
656	S01D002B023V005	East Champaran	Sangrampur	PASCHHIMI MADHUBANI
657	S01D002B023V006	East Champaran	Sangrampur	UTTARI MADHUBANI
658	S01D002B023V007	East Champaran	Sangrampur	DAKSHANI MADHUBANI
659	S01D002B023V008	East Champaran	Sangrampur	PURBI MADHUBANI
660	S01D002B023V009	East Champaran	Sangrampur	BHATWALIA
661	S01D002B023V010	East Champaran	Sangrampur	BARWA
662	S01D002B023V011	East Champaran	Sangrampur	BARIYARIA TOLARAJPUR
663	S01D002B023V012	East Champaran	Sangrampur	UTTARI BHAWANIPUR
664	S01D002B023V013	East Champaran	Sangrampur	DAKSHINI BHAWANIPUR
665	S01D002B023V014	East Champaran	Sangrampur	DUMARIYA
666	S01D002B024V001	East Champaran	Sugauli	BAGAHI
667	S01D002B024V002	East Champaran	Sugauli	BHARGAWAN
668	S01D002B024V003	East Champaran	Sugauli	BHATAHA
669	S01D002B024V004	East Champaran	Sugauli	FULWARIYA
670	S01D002B024V005	East Champaran	Sugauli	Khonra
671	S01D002B024V006	East Champaran	Sugauli	Kaithwalia
672	S01D002B024V007	East Champaran	Sugauli	KAMARWA RAGHUNATHPUR
673	S01D002B024V008	East Champaran	Sugauli	MALI
674	S01D002B024V009	East Champaran	Sugauli	MANSINGHA SOUTH
675	S01D002B024V010	East Champaran	Sugauli	MANSINGHA UTTARI
676	S01D002B024V011	East Champaran	Sugauli	NORTH CHHAPRA BAHAS
677	S01D002B024V012	East Champaran	Sugauli	NORTH SRIPUR
678	S01D002B024V013	East Champaran	Sugauli	NORTH SUGAWN
679	S01D002B024V014	East Champaran	Sugauli	PAJIYARWA
680	S01D002B024V015	East Champaran	Sugauli	SOUTH CHHAPRA BAHAS
681	S01D002B024V016	East Champaran	Sugauli	SOUTH SRIPUR
682	S01D002B024V017	East Champaran	Sugauli	SOUTH SUGAWN
683	S01D002B024V018	East Champaran	Sugauli	SUKUL PAKAR
684	S01D002B025V001	East Champaran	Tetaria	GHEGHWA
685	S01D002B025V002	East Champaran	Tetaria	KOTHIA
686	S01D002B025V003	East Champaran	Tetaria	PUNAS LAHLADPUR
687	S01D002B025V004	East Champaran	Tetaria	TETARIA
688	S01D002B025V005	East Champaran	Tetaria	MADHUAHAN BRIT
689	S01D002B025V006	East Champaran	Tetaria	BAHUARA GOPI SINGH
690	S01D002B025V007	East Champaran	Tetaria	SEMRAHA
691	S01D002B025V008	East Champaran	Tetaria	NARAHA PANAPUR
692	S01D002B025V009	East Champaran	Tetaria	MEGHUA
693	S01D002B026V001	East Champaran	Turkauliya	BELWA RAY
694	S01D002B026V002	East Champaran	Turkauliya	BIJULPUR
695	S01D002B026V003	East Champaran	Turkauliya	SAPAHI
696	S01D002B026V004	East Champaran	Turkauliya	JAISINGHPUR NORTH
697	S01D002B026V005	East Champaran	Turkauliya	JAISINGHPUR SOUTH
698	S01D002B026V006	East Champaran	Turkauliya	JAISINGHPUR EAST
699	S01D002B026V007	East Champaran	Turkauliya	CHARGAHA
700	S01D002B026V008	East Champaran	Turkauliya	TURKAULIYA WEST
701	S01D002B026V009	East Champaran	Turkauliya	TURKAULIYA MIDDILE
702	S01D002B026V010	East Champaran	Turkauliya	TURKAULIYA EAST
703	S01D002B026V011	East Champaran	Turkauliya	RAGHUNATHPUR
704	S01D002B026V012	East Champaran	Turkauliya	SHANKAR SARAYA NORTH
705	S01D002B026V013	East Champaran	Turkauliya	SHAKNAR SARAYA SOUTH
706	S01D002B026V014	East Champaran	Turkauliya	HARDIYA
707	S01D002B026V015	East Champaran	Turkauliya	MADHOPUR MADHUMALAT
708	S01D002B026V016	East Champaran	Turkauliya	MATHURAPUR
709	S01D002B027V001	East Champaran	Bankatwa	BANKATWA
710	S01D002B027V002	East Champaran	Bankatwa	BIJBANI EAST
711	S01D002B027V003	East Champaran	Bankatwa	BIJBANI NORTH
712	S01D002B027V004	East Champaran	Bankatwa	BIJBANI SOUTH
713	S01D002B027V005	East Champaran	Bankatwa	GOLA PAKARIA
714	S01D002B027V006	East Champaran	Bankatwa	INARWA PHULWAR
715	S01D002B027V007	East Champaran	Bankatwa	Jagirha Kamraula
716	S01D002B027V008	East Champaran	Bankatwa	JEETPUR
717	S01D002B027V009	East Champaran	Bankatwa	JHANJHARA
718	S01D002B027V010	East Champaran	Bankatwa	NIMUIYA EAST
719	S01D002B027V011	East Champaran	Bankatwa	Semri
720	S01D002B027V012	East Champaran	Bankatwa	NIMUIYA WEST
721	S01D003B001V001	Gopalganj	Manjha	AADAMAPUR
722	S01D003B002V001	Gopalganj	Kuchaikot	AHIROLI DUBOLI
723	S01D003B002V002	Gopalganj	Kuchaikot	AHIYAPUR
724	S01D003B003V001	Gopalganj	Vijaipur	AHIYAPUR
725	S01D003B004V001	Gopalganj	Baikunthpur	AJBINAGAR
726	S01D003B005V001	Gopalganj	Sidhwalia	AMARPURA
727	S01D003B006V001	Gopalganj	Kateya	AMEYA
728	S01D003B007V001	Gopalganj	Gopalganj	BABU BISHUNPUR PASCH
729	S01D003B002V003	Gopalganj	Kuchaikot	BADHAHRA
730	S01D003B008V001	Gopalganj	Bhorey	BAGHA MISHRA
731	S01D003B009V001	Gopalganj	Barauli	BAGHEJI
732	S01D003B006V002	Gopalganj	Kateya	BAIKUNTHPUR
733	S01D003B006V003	Gopalganj	Kateya	BAIRIYA
734	S01D003B005V002	Gopalganj	Sidhwalia	BAKHARAUR
735	S01D003B004V002	Gopalganj	Baikunthpur	BAKHARI
736	S01D003B010V001	Gopalganj	Uchkagaon	BALESRA
737	S01D003B002V004	Gopalganj	Kuchaikot	BALIBAN SAGAR
738	S01D003B004V003	Gopalganj	Baikunthpur	BANDHOLI BANAIRA
739	S01D003B003V002	Gopalganj	Vijaipur	BANDHOURA GHAT
740	S01D003B002V005	Gopalganj	Kuchaikot	BANGAL KHAD
741	S01D003B001V002	Gopalganj	Manjha	BANGARA
742	S01D003B004V004	Gopalganj	Baikunthpur	BANGRA
743	S01D003B002V006	Gopalganj	Kuchaikot	BANKATA
744	S01D003B008V002	Gopalganj	Bhorey	BANKATA JAGIDARI
745	S01D003B011V001	Gopalganj	Panchdevri	BANKATIYA
746	S01D003B002V007	Gopalganj	Kuchaikot	BANTAIL
747	S01D003B007V002	Gopalganj	Gopalganj	BARAI PATTI
748	S01D003B012V001	Gopalganj	Thawe	BARARI JAGDISH
749	S01D003B013V001	Gopalganj	Hathua	BARIESAR
750	S01D003B013V002	Gopalganj	Hathua	BARIRAIVAN
751	S01D003B010V002	Gopalganj	Uchkagaon	BARIYA DURG
752	S01D003B013V003	Gopalganj	Hathua	BARWAKAPERPURA
753	S01D003B007V003	Gopalganj	Gopalganj	BASDILA KHAS
754	S01D003B004V005	Gopalganj	Baikunthpur	BASHKGAT MASURIYA
755	S01D003B009V002	Gopalganj	Barauli	BATARDEH
756	S01D003B001V003	Gopalganj	Manjha	BATHUA
757	S01D003B014V001	Gopalganj	Phulwaria	BATHUAA BAJAR
758	S01D003B006V004	Gopalganj	Kateya	BELAHI KHAS
759	S01D003B009V003	Gopalganj	Barauli	BELSAND
760	S01D003B003V003	Gopalganj	Vijaipur	BELWA
761	S01D003B014V002	Gopalganj	Phulwaria	BERAGITOLA
762	S01D003B011V002	Gopalganj	Panchdevri	BHAGWANPUR
763	S01D003B001V004	Gopalganj	Manjha	BHAISHAHI
764	S01D003B002V008	Gopalganj	Kuchaikot	BHAKHARI
765	S01D003B003V004	Gopalganj	Vijaipur	BHARPURWA
766	S01D003B006V005	Gopalganj	Kateya	BHEDIYA
767	S01D003B007V004	Gopalganj	Gopalganj	BHITBHERAWA
768	S01D003B002V009	Gopalganj	Kuchaikot	BHOPATPUR
769	S01D003B008V003	Gopalganj	Bhorey	BHORE
770	S01D003B012V002	Gopalganj	Thawe	BIDESI TOLA
771	S01D003B007V005	Gopalganj	Gopalganj	BISHUNPUR PURWI
772	S01D003B009V004	Gopalganj	Barauli	BISHUNPURA
773	S01D003B012V003	Gopalganj	Thawe	BRINDA BAN
774	S01D003B005V003	Gopalganj	Sidhwalia	BUCHIYA
775	S01D003B005V004	Gopalganj	Sidhwalia	BUDHASI
776	S01D003B008V004	Gopalganj	Bhorey	CHAKARWA KHASH
777	S01D003B004V006	Gopalganj	Baikunthpur	CHAMANPURA
778	S01D003B014V003	Gopalganj	Phulwaria	CHAMARIPATTI
779	S01D003B003V005	Gopalganj	Vijaipur	CHAUMUKHA
780	S01D003B007V006	Gopalganj	Gopalganj	CHAURAW
781	S01D003B013V004	Gopalganj	Hathua	CHEANPUR
782	S01D003B013V005	Gopalganj	Hathua	CHHAP
783	S01D003B008V005	Gopalganj	Bhorey	CHHATIYAW
784	S01D003B001V005	Gopalganj	Manjha	CHHAWAHI TAKI
785	S01D003B010V003	Gopalganj	Uchkagaon	CHHOTKASAKHE
786	S01D003B004V007	Gopalganj	Baikunthpur	CHIUTAHA
787	S01D003B014V004	Gopalganj	Phulwaria	CHURAMNCHAK
788	S01D003B010V004	Gopalganj	Uchkagaon	DAHIBHATA
789	S01D003B004V008	Gopalganj	Baikunthpur	DARAMBARI
790	S01D003B009V005	Gopalganj	Barauli	DEWAPUR
791	S01D003B001V006	Gopalganj	Manjha	DEWAPUR SHEKHPURDIL
792	S01D003B012V004	Gopalganj	Thawe	DHATIWNA
793	S01D003B002V010	Gopalganj	Kuchaikot	DHODWALIYA
794	S01D003B004V009	Gopalganj	Baikunthpur	DIGHWADUBAULI NOURTH
795	S01D003B004V010	Gopalganj	Baikunthpur	DIGHWADUBAULI SOUTH
796	S01D003B008V006	Gopalganj	Bhorey	DOMANPUR
797	S01D003B008V007	Gopalganj	Bhorey	DUMAR NARENDRA
798	S01D003B005V005	Gopalganj	Sidhwalia	DUMARIYA
799	S01D003B002V011	Gopalganj	Kuchaikot	DURGMATIHNIYA
800	S01D003B013V006	Gopalganj	Hathua	EKDANGAA
801	S01D003B012V005	Gopalganj	Thawe	EKDERAWA
802	S01D003B007V007	Gopalganj	Gopalganj	EKDERWA
803	S01D003B004V011	Gopalganj	Baikunthpur	FAIJULAHPUR
804	S01D003B013V007	Gopalganj	Hathua	FATEHPUR
805	S01D003B012V006	Gopalganj	Thawe	FULUGANI
806	S01D003B004V012	Gopalganj	Baikunthpur	GAMHARI
807	S01D003B014V005	Gopalganj	Phulwaria	GARESH DUMAR
808	S01D003B006V006	Gopalganj	Kateya	GAURA
809	S01D003B001V007	Gopalganj	Manjha	GAUSIYA
810	S01D003B014V006	Gopalganj	Phulwaria	GIDHA
811	S01D003B008V008	Gopalganj	Bhorey	GOPALPUR
812	S01D003B004V013	Gopalganj	Baikunthpur	HAKAM
813	S01D003B004V014	Gopalganj	Baikunthpur	HAMIDPUR
814	S01D003B010V005	Gopalganj	Uchkagaon	HARPUR
815	S01D003B009V006	Gopalganj	Barauli	HASANPUR
816	S01D003B013V008	Gopalganj	Hathua	HATHUA
817	S01D003B008V009	Gopalganj	Bhorey	HRADIYA
818	S01D003B008V010	Gopalganj	Bhorey	HUSEPUR
819	S01D003B012V007	Gopalganj	Thawe	INDARWA BEDULLAH
820	S01D003B001V008	Gopalganj	Manjha	JAGARNATHA
821	S01D003B003V006	Gopalganj	Vijaipur	JAGDISHPUR
822	S01D003B004V015	Gopalganj	Baikunthpur	JAGDISHPUR
823	S01D003B008V011	Gopalganj	Bhorey	JAGHTAITI
824	S01D003B007V008	Gopalganj	Gopalganj	JAGIRI TOLA
825	S01D003B012V008	Gopalganj	Thawe	JAGMALWA
826	S01D003B002V012	Gopalganj	Kuchaikot	JALALPUR
827	S01D003B005V006	Gopalganj	Sidhwalia	JALALPUR KALA
828	S01D003B010V006	Gopalganj	Uchkagaon	JAMSAR
829	S01D003B010V007	Gopalganj	Uchkagaon	JHIRWA
830	S01D003B013V009	Gopalganj	Hathua	JIGNAJAGRANATH
831	S01D003B009V007	Gopalganj	Barauli	KAHALA
832	S01D003B008V012	Gopalganj	Bhorey	KALYANPUR
833	S01D003B009V008	Gopalganj	Barauli	KALYANPUR
834	S01D003B013V010	Gopalganj	Hathua	KANDHGOPI
835	S01D003B014V007	Gopalganj	Phulwaria	KARARIYAA
836	S01D003B005V007	Gopalganj	Sidhwalia	KARASH GHAT
837	S01D003B006V007	Gopalganj	Kateya	KARKATAHA
838	S01D003B001V009	Gopalganj	Manjha	KARNPURA
839	S01D003B005V008	Gopalganj	Sidhwalia	KASHITENGRAHI
840	S01D003B004V016	Gopalganj	Baikunthpur	KATALPUR
841	S01D003B007V009	Gopalganj	Gopalganj	KATHGHARAWA
842	S01D003B008V013	Gopalganj	Bhorey	KHADHI
843	S01D003B004V017	Gopalganj	Baikunthpur	KHAIRA AJAM
844	S01D003B002V013	Gopalganj	Kuchaikot	KHAJURI
845	S01D003B009V009	Gopalganj	Barauli	KHAJURIYA
846	S01D003B011V003	Gopalganj	Panchdevri	KHAL GAON
847	S01D003B007V010	Gopalganj	Gopalganj	KHAWAJEPUR
848	S01D003B013V011	Gopalganj	Hathua	KHERTIYA
849	S01D003B003V007	Gopalganj	Vijaipur	KHIRIDHIH
850	S01D003B001V010	Gopalganj	Manjha	KOINI
851	S01D003B007V011	Gopalganj	Gopalganj	KONHAWA
852	S01D003B008V014	Gopalganj	Bhorey	KOREYA
853	S01D003B003V008	Gopalganj	Vijaipur	KOTIYA
854	S01D003B014V008	Gopalganj	Phulwaria	KOYLADEWA
855	S01D003B002V014	Gopalganj	Kuchaikot	KUCHAIKOTE
856	S01D003B011V004	Gopalganj	Panchdevri	KUISHA KHURD
857	S01D003B005V009	Gopalganj	Sidhwalia	KUSHAHAR
858	S01D003B013V012	Gopalganj	Hathua	KUSONDHI
859	S01D003B012V009	Gopalganj	Thawe	LACHHWAR
860	S01D003B013V013	Gopalganj	Hathua	LAINBAJAR
861	S01D003B008V015	Gopalganj	Bhorey	LAMICHAUR
862	S01D003B009V010	Gopalganj	Barauli	LARAULI
863	S01D003B005V010	Gopalganj	Sidhwalia	LOHIJARA
864	S01D003B010V008	Gopalganj	Uchkagaon	LUHSI
865	S01D003B013V014	Gopalganj	Hathua	MACHHAGARJAGDHISH
866	S01D003B013V015	Gopalganj	Hathua	MACHHAGARLAXIRAM
867	S01D003B009V011	Gopalganj	Barauli	MADHOPUR
868	S01D003B001V011	Gopalganj	Manjha	MADHU SAREYA
869	S01D003B011V005	Gopalganj	Panchdevri	MAGAHIYA
870	S01D003B014V009	Gopalganj	Phulwaria	MAGIRWAKALA
871	S01D003B005V011	Gopalganj	Sidhwalia	MAHAMADPUR
872	S01D003B009V012	Gopalganj	Barauli	MAHAMADPUR NILAMI
873	S01D003B010V009	Gopalganj	Uchkagaon	MAHECHA
874	S01D003B011V006	Gopalganj	Panchdevri	MAHUAWA
875	S01D003B014V010	Gopalganj	Phulwaria	MAJHAGOSAI
876	S01D003B003V009	Gopalganj	Vijaipur	MAJHWALIYA
877	S01D003B011V007	Gopalganj	Panchdevri	MAJHWALIYA
878	S01D003B007V012	Gopalganj	Gopalganj	MANIKPUR
879	S01D003B001V012	Gopalganj	Manjha	MANJHA PASCHAMI
880	S01D003B001V013	Gopalganj	Manjha	MANJHA PURBI
881	S01D003B002V015	Gopalganj	Kuchaikot	MATHIYA HARDO
882	S01D003B013V016	Gopalganj	Hathua	MATIHANINEEN
883	S01D003B002V016	Gopalganj	Kuchaikot	MATIHANIYATIWARI
884	S01D003B002V017	Gopalganj	Kuchaikot	MATIHNIYAKALA
885	S01D003B002V018	Gopalganj	Kuchaikot	METEYA KHAS
886	S01D003B009V013	Gopalganj	Barauli	MOHADIPUR PAKARIYA
887	S01D003B009V014	Gopalganj	Barauli	MONGAL BIRAICHA
888	S01D003B003V010	Gopalganj	Vijaipur	MUSEHARI
889	S01D003B003V011	Gopalganj	Vijaipur	NAIWTAN
890	S01D003B009V015	Gopalganj	Barauli	NAWADA CHAND
891	S01D003B010V010	Gopalganj	Uchkagaon	NAWADAPARSONI
892	S01D003B001V014	Gopalganj	Manjha	NIMUIYA
893	S01D003B006V008	Gopalganj	Kateya	PADARIYA
894	S01D003B003V012	Gopalganj	Vijaipur	PAGARA
895	S01D003B001V015	Gopalganj	Manjha	PAITHANPATTI
896	S01D003B013V017	Gopalganj	Hathua	PANCHFERA
897	S01D003B004V018	Gopalganj	Baikunthpur	PARSAUNI
898	S01D003B010V011	Gopalganj	Uchkagaon	PARSONIKHAS
899	S01D003B006V009	Gopalganj	Kateya	PATKHAULI
900	S01D003B014V011	Gopalganj	Phulwaria	PEKOLIBANDO
901	S01D003B014V012	Gopalganj	Phulwaria	PHULWARIA
902	S01D003B009V016	Gopalganj	Barauli	PIPRA
903	S01D003B001V016	Gopalganj	Manjha	PRATAPPUR
904	S01D003B001V017	Gopalganj	Manjha	PURAINA
905	S01D003B002V019	Gopalganj	Kuchaikot	PURKHAAS
906	S01D003B004V019	Gopalganj	Baikunthpur	PYAREPUR
907	S01D003B008V016	Gopalganj	Bhorey	RAKBA
908	S01D003B012V010	Gopalganj	Thawe	RAMCHANDRAPUR
909	S01D003B006V010	Gopalganj	Kateya	RAMDAS BAGAHI
910	S01D003B009V017	Gopalganj	Barauli	RAMPUR
911	S01D003B002V020	Gopalganj	Kuchaikot	RAMPUR KHAREYA
912	S01D003B007V013	Gopalganj	Gopalganj	RAMPUR TENGRAHI
913	S01D003B002V021	Gopalganj	Kuchaikot	RAMPURMADHO
914	S01D003B013V018	Gopalganj	Hathua	RATANCHAK
915	S01D003B004V020	Gopalganj	Baikunthpur	REWTIT
916	S01D003B006V011	Gopalganj	Kateya	RUDRAPUR
917	S01D003B009V018	Gopalganj	Barauli	SADAUA
918	S01D003B001V018	Gopalganj	Manjha	SAFAPUR
919	S01D003B010V012	Gopalganj	Uchkagaon	SAKHEKHAS
920	S01D003B002V022	Gopalganj	Kuchaikot	SALEHPUR
921	S01D003B009V019	Gopalganj	Barauli	SALEMPUR EAST
922	S01D003B009V020	Gopalganj	Barauli	SALEMPUR WEST
923	S01D003B002V023	Gopalganj	Kuchaikot	SANGWADIH
924	S01D003B009V021	Gopalganj	Barauli	SARAPHARA
925	S01D003B009V022	Gopalganj	Barauli	SAREYA NARENDRA
926	S01D003B003V013	Gopalganj	Vijaipur	SAROPAI
927	S01D003B002V024	Gopalganj	Kuchaikot	SASAMUSA
928	S01D003B012V011	Gopalganj	Thawe	SEMARA
929	S01D003B011V008	Gopalganj	Panchdevri	SEMARIYA
930	S01D003B002V025	Gopalganj	Kuchaikot	SEMRA
931	S01D003B013V019	Gopalganj	Hathua	SEMRAW
932	S01D003B002V026	Gopalganj	Kuchaikot	SESWA
933	S01D003B001V019	Gopalganj	Manjha	SHEKH PARSA
934	S01D003B005V012	Gopalganj	Sidhwalia	SHER
935	S01D003B004V021	Gopalganj	Baikunthpur	SHIRSHA MANPUR
936	S01D003B011V009	Gopalganj	Panchdevri	SIKATIA
937	S01D003B013V020	Gopalganj	Hathua	SINGHA
938	S01D003B001V020	Gopalganj	Manjha	SIPAHKHAS
939	S01D003B002V027	Gopalganj	Kuchaikot	SIRESIYA
940	S01D003B008V017	Gopalganj	Bhorey	SISAI
941	S01D003B013V021	Gopalganj	Hathua	SOEREN
942	S01D003B013V022	Gopalganj	Hathua	SOHAGPUR
943	S01D003B009V023	Gopalganj	Barauli	SONBARSHA
944	S01D003B002V028	Gopalganj	Kuchaikot	SONHULA GOKHOL
945	S01D003B005V013	Gopalganj	Sidhwalia	SUPAULI
946	S01D003B007V014	Gopalganj	Gopalganj	TIRBIRWA
947	S01D003B002V029	Gopalganj	Kuchaikot	TOLA SIPAYA
948	S01D003B010V013	Gopalganj	Uchkagaon	TRILOKPUR
949	S01D003B010V014	Gopalganj	Uchkagaon	UCHAKAGAON
950	S01D003B002V030	Gopalganj	Kuchaikot	UCHAKAGAON
951	S01D003B004V022	Gopalganj	Baikunthpur	USARI
952	S01D003B002V031	Gopalganj	Kuchaikot	VIKRAMPUR
953	S01D003B007V015	Gopalganj	Gopalganj	YADOPUR
954	S01D003B007V016	Gopalganj	Gopalganj	YADOPUR DUKHHARAN
955	S01D003B011V010	Gopalganj	Panchdevri	Machwa
956	S01D004B001V001	Khagaria	Allouli Beldaur	ALLOULI
957	S01D004B001V002	Khagaria	Allouli Beldaur	AMBA ICHARWA
958	S01D004B002V001	Khagaria	Mansi	AMNI
959	S01D004B001V003	Khagaria	Allouli Beldaur	ANANDPUR
960	S01D004B003V001	Khagaria	Khagaria	BACHHOUTA
961	S01D004B001V004	Khagaria	Allouli Beldaur	BAHADUPUR
962	S01D004B004V001	Khagaria	Parbatta	BAISA
963	S01D004B005V001	Khagaria	Beldaur	BALAITHA
964	S01D004B002V002	Khagaria	Mansi	BALHA
965	S01D004B006V001	Khagaria	Gogri	BALTARA
966	S01D004B004V002	Khagaria	Parbatta	BANDEHRA
967	S01D004B001V005	Khagaria	Allouli Beldaur	BANDH CHATAR
968	S01D004B006V002	Khagaria	Gogri	BANNI
969	S01D004B003V002	Khagaria	Khagaria	BARAIY
970	S01D004B006V003	Khagaria	Gogri	BASUDEOPUR
971	S01D004B005V002	Khagaria	Beldaur	BELANOWAD
972	S01D004B003V003	Khagaria	Khagaria	BELASIMRI
973	S01D004B005V003	Khagaria	Beldaur	BELDOUR
974	S01D004B003V004	Khagaria	Khagaria	BHADAS DAKSHINI
975	S01D004B003V005	Khagaria	Khagaria	BHADAS UATTARI
976	S01D004B004V003	Khagaria	Parbatta	BHARSO
977	S01D004B001V006	Khagaria	Allouli Beldaur	BHIKHARI GHAT
978	S01D004B005V004	Khagaria	Beldaur	BOBIL
979	S01D004B006V004	Khagaria	Gogri	BORNA
980	S01D004B007V001	Khagaria	Chautham	BORNE
981	S01D004B007V002	Khagaria	Chautham	BORNE CENTRAL
982	S01D004B007V003	Khagaria	Chautham	BORNE EAST
983	S01D004B007V004	Khagaria	Chautham	BUCHHA
984	S01D004B002V003	Khagaria	Mansi	CHAKHUSAINI
985	S01D004B001V007	Khagaria	Allouli Beldaur	CHANPURA
986	S01D004B007V005	Khagaria	Chautham	CHAUTHAM
987	S01D004B001V008	Khagaria	Allouli Beldaur	CHERA KHERA
988	S01D004B001V009	Khagaria	Allouli Beldaur	CHHILKAURI
989	S01D004B005V005	Khagaria	Beldaur	CHODHLI
990	S01D004B004V004	Khagaria	Parbatta	DARIYAPUR BHELWA
991	S01D004B006V005	Khagaria	Gogri	DEOTHA
992	S01D004B004V005	Khagaria	Parbatta	DEWARI
993	S01D004B001V010	Khagaria	Allouli Beldaur	DHAHMA KHAIRI
994	S01D004B003V006	Khagaria	Khagaria	DHUSMURI
995	S01D004B007V006	Khagaria	Chautham	DHUTAULI
996	S01D004B005V006	Khagaria	Beldaur	DIGHON
997	S01D004B005V007	Khagaria	Beldaur	DUMRI
998	S01D004B002V004	Khagaria	Mansi	EAST THATHA
999	S01D004B006V006	Khagaria	Gogri	GAUCHHARI
1000	S01D004B004V006	Khagaria	Parbatta	GOBINDPUR
1001	S01D004B006V007	Khagaria	Gogri	GOGRI
1002	S01D004B001V011	Khagaria	Allouli Beldaur	GORIYAMI
1003	S01D004B001V012	Khagaria	Allouli Beldaur	GOURACHAK
1004	S01D004B003V007	Khagaria	Khagaria	GOURASHAKTI
1005	S01D004B007V007	Khagaria	Chautham	HARDIA
1006	S01D004B001V013	Khagaria	Allouli Beldaur	HARIPUR
1007	S01D004B001V014	Khagaria	Allouli Beldaur	HATHWAN
1008	S01D004B006V008	Khagaria	Gogri	ITAHARI
1009	S01D004B005V008	Khagaria	Beldaur	ITAMADI
1010	S01D004B003V008	Khagaria	Khagaria	JALKOURA
1011	S01D004B003V009	Khagaria	Khagaria	JANHAGIRA
1012	S01D004B006V009	Khagaria	Gogri	JHIKTIYA
1013	S01D004B004V007	Khagaria	Parbatta	JORAWARPUR
1014	S01D004B004V008	Khagaria	Parbatta	KABELA
1015	S01D004B005V009	Khagaria	Beldaur	KANJARI
1016	S01D004B003V010	Khagaria	Khagaria	KASIMPUR
1017	S01D004B004V009	Khagaria	Parbatta	KHAJRETHA
1018	S01D004B004V010	Khagaria	Parbatta	KHIRADIH
1019	S01D004B002V005	Khagaria	Mansi	KHUTIA
1020	S01D004B006V010	Khagaria	Gogri	KOELA
1021	S01D004B004V011	Khagaria	Parbatta	KOLWARA
1022	S01D004B003V011	Khagaria	Khagaria	KOTHIA
1023	S01D004B004V012	Khagaria	Parbatta	KULHARIYA
1024	S01D004B005V010	Khagaria	Beldaur	KURAWAN
1025	S01D004B003V012	Khagaria	Khagaria	LABHGANVA
1026	S01D004B004V013	Khagaria	Parbatta	LAGAR
1027	S01D004B006V011	Khagaria	Gogri	MADARPUR
1028	S01D004B004V014	Khagaria	Parbatta	MADHAWAPUR
1029	S01D004B004V015	Khagaria	Parbatta	MAHADIPUR
1030	S01D004B006V012	Khagaria	Gogri	MAHESHKHUNT
1031	S01D004B005V011	Khagaria	Beldaur	MAHINATH NAGAR
1032	S01D004B006V013	Khagaria	Gogri	MAIRA
1033	S01D004B005V012	Khagaria	Beldaur	MALI
1034	S01D004B003V013	Khagaria	Khagaria	MANDAR DAKSHINI
1035	S01D004B003V014	Khagaria	Khagaria	MANDAR UATTARI
1036	S01D004B003V015	Khagaria	Khagaria	MATHURAPUR
1037	S01D004B001V015	Khagaria	Allouli Beldaur	MEGHAUNA
1038	S01D004B006V014	Khagaria	Gogri	MUSKIPUR
1039	S01D004B007V008	Khagaria	Chautham	NIRPUR
1040	S01D004B006V015	Khagaria	Gogri	NORTH JAMALPUR
1041	S01D004B003V016	Khagaria	Khagaria	OLAPUR GANGOUR
1042	S01D004B005V013	Khagaria	Beldaur	PACHOT
1043	S01D004B006V016	Khagaria	Gogri	PAIKAT
1044	S01D004B006V017	Khagaria	Gogri	PALRAIL
1045	S01D004B004V016	Khagaria	Parbatta	PARBATTA
1046	S01D004B006V018	Khagaria	Gogri	PASARAHA
1047	S01D004B006V019	Khagaria	Gogri	PAWRA
1048	S01D004B007V009	Khagaria	Chautham	PIPRA
1049	S01D004B004V017	Khagaria	Parbatta	PIPRA LATIF
1050	S01D004B005V014	Khagaria	Beldaur	PIRNAGRA
1051	S01D004B003V017	Khagaria	Khagaria	RAHIMPUR DAKSHIN
1052	S01D004B003V018	Khagaria	Khagaria	RAHIMPUR MADHYA
1053	S01D004B003V019	Khagaria	Khagaria	RAHIMPUR UATTARI
1054	S01D004B006V020	Khagaria	Gogri	RAMPUR
1055	S01D004B001V016	Khagaria	Allouli Beldaur	RAMPUR ALLOLI
1056	S01D004B004V018	Khagaria	Parbatta	RAMPUR URF RAHIMPUR PANCH
1057	S01D004B003V020	Khagaria	Khagaria	RANI SAKARPRUA
1058	S01D004B003V021	Khagaria	Khagaria	RANKO
1059	S01D004B003V022	Khagaria	Khagaria	RASOUNK
1060	S01D004B006V021	Khagaria	Gogri	RATAN
1061	S01D004B007V010	Khagaria	Chautham	ROHIYAR
1062	S01D004B001V017	Khagaria	Allouli Beldaur	ROUN
1063	S01D004B001V018	Khagaria	Allouli Beldaur	SAHAR BNNI
1064	S01D004B001V019	Khagaria	Allouli Beldaur	SAHSI
1065	S01D004B002V006	Khagaria	Mansi	SAIDPUR
1066	S01D004B005V015	Khagaria	Beldaur	SAKROHAR
1067	S01D004B006V022	Khagaria	Gogri	SAMASPUR
1068	S01D004B003V023	Khagaria	Khagaria	SANHOULI
1069	S01D004B003V024	Khagaria	Khagaria	SANSARPUR
1070	S01D004B007V011	Khagaria	Chautham	SARSAWA
1071	S01D004B006V023	Khagaria	Gogri	SHERCHAKLA
1072	S01D004B001V020	Khagaria	Allouli Beldaur	SIMRHA
1073	S01D004B004V019	Khagaria	Parbatta	SIYADATPUR ARUWANI
1074	S01D004B004V020	Khagaria	Parbatta	SOUR DAKSHINI
1075	S01D004B004V021	Khagaria	Parbatta	SOUR UTTARI
1076	S01D004B006V024	Khagaria	Gogri	SOUTH JAMALPUR
1077	S01D004B001V021	Khagaria	Allouli Beldaur	SUMBHA
1078	S01D004B007V012	Khagaria	Chautham	TELAUNCHH
1079	S01D004B005V016	Khagaria	Beldaur	TELIHAR
1080	S01D004B004V022	Khagaria	Parbatta	TEMTHA KARARI
1081	S01D004B003V025	Khagaria	Khagaria	TETARABAD
1082	S01D004B007V013	Khagaria	Chautham	THUTHI MOHAN PUR
1083	S01D004B003V026	Khagaria	Khagaria	UTTAR MANDAR
1084	S01D004B002V007	Khagaria	Mansi	WEST THATHA
1085	S01D004B005V017	Khagaria	Beldaur	Dudraja
1086	S01D004B005V018	Khagaria	Beldaur	Goas
1087	S01D004B005V019	Khagaria	Beldaur	Sathman
1088	S01D004B003V027	Khagaria	Khagaria	Olapur
1089	S01D004B004V023	Khagaria	Parbatta	Rampur urf Rahimpur Inghish
1090	S01D005B001V001	Patna	Dulhin Bajar	AAINKHA VIMANICHAK
1091	S01D005B002V001	Patna	Bihta	AANANDPUR
1092	S01D005B001V002	Patna	Dulhin Bajar	ACHHUYA RAKASIYA
1093	S01D005B003V001	Patna	Naubatpur	ADLA
1094	S01D005B004V001	Patna	Barh	AGWANPUR
1095	S01D005B005V001	Patna	Pandarak	AJGARA BAKAWAN
1096	S01D005B003V002	Patna	Naubatpur	AJWAN
1097	S01D005B006V001	Patna	Paliganj	AKABARPUR RANIPUR
1098	S01D005B007V001	Patna	Punpun	AKAUNA
1099	S01D005B008V001	Patna	Bikram	AKHITIYARPUR MANJHAULI
1100	S01D005B009V001	Patna	Fatuha	ALAWALPUR
1101	S01D005B010V001	Patna	Khusrupur	ALAWALPUR
1102	S01D005B011V001	Patna	Bakhtiyarpur	ALIPUR BIHTA
1103	S01D005B002V002	Patna	Bihta	AMHARA
1104	S01D005B012V001	Patna	Belchhi	ANDAULI DARVESHPURA
1105	S01D005B008V002	Patna	Bikram	ARAP
1106	S01D005B013V001	Patna	Mokama	AUNTA
1107	S01D005B006V002	Patna	Paliganj	AZADA SIKARIA
1108	S01D005B014V001	Patna	Athmalgola	BAHADURPUR
1109	S01D005B015V001	Patna	Dhanarua	BAHRAMPUR
1110	S01D005B010V002	Patna	Khusrupur	BAIKATHPUR
1111	S01D005B016V001	Patna	Sampatchak	BAIRIA KARANPPUR
1112	S01D005B009V002	Patna	Fatuha	BALI
1113	S01D005B017V001	Patna	Maner	BALUWA
1114	S01D005B017V002	Patna	Maner	BANK
1115	S01D005B018V001	Patna	Daniyawan	BANKIPUR MACHHARIYAWAN
1116	S01D005B019V001	Patna	Masaurhi	BARA
1117	S01D005B003V003	Patna	Naubatpur	BARA
1118	S01D005B012V002	Patna	Belchhi	BARAH
1119	S01D005B008V003	Patna	Bikram	BARAH
1120	S01D005B007V002	Patna	Punpun	BARAH
1121	S01D005B013V002	Patna	Mokama	BARAHPUR
1122	S01D005B007V003	Patna	Punpun	BARANWA
1123	S01D005B015V002	Patna	Dhanarua	BARI BIGHA
1124	S01D005B003V004	Patna	Naubatpur	BARI TAINGARELLA
1125	S01D005B015V003	Patna	Dhanarua	BARNI
1126	S01D005B005V002	Patna	Pandarak	BARUANE BATHOI
1127	S01D005B007V004	Patna	Punpun	BEHRANWA
1128	S01D005B002V003	Patna	Bihta	BELA
1129	S01D005B012V003	Patna	Belchhi	BELCHHI
1130	S01D005B008V004	Patna	Bikram	BENIBIGHA
1131	S01D005B004V002	Patna	Barh	BERHNA EAST
1132	S01D005B004V003	Patna	Barh	BERHNA WEST
1133	S01D005B019V002	Patna	Masaurhi	BERRA
1134	S01D005B019V003	Patna	Masaurhi	BHADAURA
1135	S01D005B019V004	Patna	Masaurhi	BHAGWANGANJ
1136	S01D005B019V005	Patna	Masaurhi	BHAISAWAN
1137	S01D005B001V003	Patna	Dulhin Bajar	BHARATPURA
1138	S01D005B004V004	Patna	Barh	BHATGAWN
1139	S01D005B006V003	Patna	Paliganj	BHELARIASAI RAMPUR
1140	S01D005B016V002	Patna	Sampatchak	BHELWARA DARIYAPUR
1141	S01D005B020V001	Patna	Phulwari Sharif	BHUSHAULA DANAPUR
1142	S01D005B011V002	Patna	Bakhtiyarpur	BIDHIPUR NARAULI
1143	S01D005B005V003	Patna	Pandarak	BIHARI BIGHA
1144	S01D005B002V004	Patna	Bihta	BIHTA
1145	S01D005B008V005	Patna	Bikram	BIKRAM
1146	S01D005B002V005	Patna	Bihta	BINDAUL
1147	S01D005B008V006	Patna	Bikram	BIRGHAUR BERAR KATARI
1148	S01D005B002V006	Patna	Bihta	BISHUNPURA
1149	S01D005B017V003	Patna	Maner	BIYAPUR
1150	S01D005B005V004	Patna	Pandarak	CHAK JALAL
1151	S01D005B003V005	Patna	Naubatpur	CHAKCHECHOL
1152	S01D005B011V003	Patna	Bakhtiyarpur	CHAMPAPUR
1153	S01D005B006V004	Patna	Paliganj	CHANDHOS
1154	S01D005B019V006	Patna	Masaurhi	CHAPAUR
1155	S01D005B019V007	Patna	Masaurhi	CHARMA
1156	S01D005B010V003	Patna	Khusrupur	CHAURA
1157	S01D005B003V006	Patna	Naubatpur	CHESI
1158	S01D005B015V004	Patna	Dhanarua	CHHATTI
1159	S01D005B006V005	Patna	Paliganj	CHIKSI
1160	S01D005B020V002	Patna	Phulwari Sharif	CHILBILLI
1161	S01D005B016V003	Patna	Sampatchak	CHIPURA
1162	S01D005B011V004	Patna	Bakhtiyarpur	CHIRAIYA RUPAS
1163	S01D005B003V007	Patna	Naubatpur	CHIRORA
1164	S01D005B006V006	Patna	Paliganj	DAHIA
1165	S01D005B008V007	Patna	Bikram	DANARA KATARI
1166	S01D005B018V002	Patna	Daniyawan	DANIYAWAN
1167	S01D005B013V003	Patna	Mokama	DARIAPUR
1168	S01D005B005V005	Patna	Pandarak	DARWE BHADAUR
1169	S01D005B017V004	Patna	Maner	DARWESHPUR SOUTH
1170	S01D005B017V005	Patna	Maner	DARWESMPUR NORTH
1171	S01D005B003V008	Patna	Naubatpur	DARYAPUR
1172	S01D005B008V008	Patna	Bikram	DATIYANA
1173	S01D005B019V008	Patna	Masaurhi	DAULATPUR
1174	S01D005B002V007	Patna	Bihta	DAULATPUR SIMRI
1175	S01D005B002V008	Patna	Bihta	DAYALPUR DAULATPUR
1176	S01D005B003V009	Patna	Naubatpur	DEORA
1177	S01D005B019V009	Patna	Masaurhi	DEORIA
1178	S01D005B015V005	Patna	Dhanarua	DEWA
1179	S01D005B015V006	Patna	Dhanarua	DEWADAHA
1180	S01D005B001V004	Patna	Dulhin Bajar	DHANA NISHARPURA
1181	S01D005B004V005	Patna	Barh	DHANAWAN MOBARAKPUR
1182	S01D005B015V007	Patna	Dhanarua	DHANRUA
1183	S01D005B006V007	Patna	Paliganj	DHARHARA
1184	S01D005B020V003	Patna	Phulwari Sharif	DHIBRA
1185	S01D005B005V006	Patna	Pandarak	DHIWAR
1186	S01D005B005V007	Patna	Pandarak	DHOBHAWAN
1187	S01D005B011V005	Patna	Bakhtiyarpur	DOMA
1188	S01D005B009V003	Patna	Fatuha	DUMAREE
1189	S01D005B007V005	Patna	Punpun	DUMRI
1190	S01D005B021V001	Patna	Patnasadar	EAST DIGHA
1191	S01D005B021V002	Patna	Patnasadar	EAST MAINPURA
1192	S01D005B005V008	Patna	Pandarak	EAST PANDARAK
1193	S01D005B012V004	Patna	Belchhi	EAST SAKSOHARA
1194	S01D005B004V006	Patna	Barh	EKDANGA
1195	S01D005B003V010	Patna	Naubatpur	FARIDPUR
1196	S01D005B012V005	Patna	Belchhi	FATEHPUR
1197	S01D005B021V003	Patna	Patnasadar	FATEHPUR
1198	S01D005B008V009	Patna	Bikram	GANGACHAK TELPA
1199	S01D005B022V001	Patna	Danapur	GANGHARA
1200	S01D005B009V004	Patna	Fatuha	GAURIPUNDAH
1201	S01D005B011V006	Patna	Bakhtiyarpur	GHANGAH
1202	S01D005B011V007	Patna	Bakhtiyarpur	GHOSWARI
1203	S01D005B023V001	Patna	Ghoswari	GHOSWARI
1204	S01D005B017V006	Patna	Maner	GIYASPUR
1205	S01D005B005V009	Patna	Pandarak	GOASA SHEKHPURA
1206	S01D005B015V008	Patna	Dhanarua	GOBINDPUR BORHI
1207	S01D005B003V011	Patna	Naubatpur	GONAWAN
1208	S01D005B020V004	Patna	Phulwari Sharif	GONPURA
1209	S01D005B008V010	Patna	Bikram	GORKHARI
1210	S01D005B023V002	Patna	Ghoswari	GOSAIGAON
1211	S01D005B008V011	Patna	Bikram	HABASPUR GONA
1212	S01D005B010V004	Patna	Khusrupur	HAIBATPUR
1213	S01D005B010V005	Patna	Khusrupur	HARDASBIGHA
1214	S01D005B011V008	Patna	Bakhtiyarpur	HARDASPUR DIYARA
1215	S01D005B013V004	Patna	Mokama	HATHIDAH BUZURG
1216	S01D005B022V002	Patna	Danapur	HATHIYA KANDH
1217	S01D005B022V003	Patna	Danapur	HETANPUR
1218	S01D005B011V009	Patna	Bakhtiyarpur	HIDAYATPUR SAIDPUR
1219	S01D005B015V009	Patna	Dhanarua	HULASCHAK BIR
1220	S01D005B004V007	Patna	Barh	IBRAHIMPUR
1221	S01D005B003V012	Patna	Naubatpur	IBRAHIMPUR
1222	S01D005B009V005	Patna	Fatuha	JAITA
1223	S01D005B003V013	Patna	Naubatpur	JAITIPUR
1224	S01D005B022V004	Patna	Danapur	JALALPUR
1225	S01D005B003V014	Patna	Naubatpur	JAMALPURA
1226	S01D005B022V005	Patna	Danapur	JAMALUDDIN
1227	S01D005B006V008	Patna	Paliganj	JAMHARU IMAMGANJ
1228	S01D005B022V006	Patna	Danapur	JAMSAUT
1229	S01D005B006V009	Patna	Paliganj	JARKHA
1230	S01D005B009V006	Patna	Fatuha	JETHULI
1231	S01D005B001V005	Patna	Dulhin Bajar	KAB
1232	S01D005B011V010	Patna	Bakhtiyarpur	KALA DIYARA
1233	S01D005B006V010	Patna	Paliganj	KALAYANPUR PAYPURA
1234	S01D005B014V002	Patna	Athmalgola	KALYANPUR
1235	S01D005B007V006	Patna	Punpun	KALYANPUR
1236	S01D005B016V004	Patna	Sampatchak	KANAUJI KCHHUARA
1237	S01D005B002V009	Patna	Bihta	KANCHANPUR KHARAGPUR
1238	S01D005B016V005	Patna	Sampatchak	KANDAP TARANPUR
1239	S01D005B013V005	Patna	Mokama	KANHAIPUR
1240	S01D005B002V010	Patna	Bihta	KAORIA
1241	S01D005B019V010	Patna	Masaurhi	KARAI
1242	S01D005B003V015	Patna	Naubatpur	KARANZA GOWAI
1243	S01D005B014V003	Patna	Athmalgola	KARJAN
1244	S01D005B011V011	Patna	Bakhtiyarpur	KARNAUTI
1245	S01D005B013V006	Patna	Mokama	KASAHA DIARA
1246	S01D005B022V007	Patna	Danapur	KASIM CHAK
1247	S01D005B002V011	Patna	Bihta	KATESHAR
1248	S01D005B006V011	Patna	Paliganj	KATKA PAGAMBERPUR
1249	S01D005B007V007	Patna	Punpun	KEWRAH
1250	S01D005B006V012	Patna	Paliganj	KHANPUR TRANPUR
1251	S01D005B019V011	Patna	Masaurhi	KHARANT
1252	S01D005B018V003	Patna	Daniyawan	KHARBHAIYA
1253	S01D005B017V007	Patna	Maner	KHASHPUR
1254	S01D005B003V016	Patna	Naubatpur	KHAZURI
1255	S01D005B005V010	Patna	Pandarak	KHUSHHAL CHAK
1256	S01D005B017V008	Patna	Maner	KITA CHAUHATTAR EAST
1257	S01D005B017V009	Patna	Maner	KITA CHAUHATTAR MIDDLE
1258	S01D005B017V010	Patna	Maner	KITA CHAUHATTAR WEST
1259	S01D005B009V007	Patna	Fatuha	KOLHAR
1260	S01D005B012V006	Patna	Belchhi	KORARI
1261	S01D005B020V005	Patna	Phulwari Sharif	KORIYAWAN
1262	S01D005B015V010	Patna	Dhanarua	KOSUT
1263	S01D005B022V008	Patna	Danapur	KOTHWA
1264	S01D005B023V003	Patna	Ghoswari	KUMHARA
1265	S01D005B005V011	Patna	Pandarak	KUNDI
1266	S01D005B002V012	Patna	Bihta	KUNWA
1267	S01D005B020V006	Patna	Phulwari Sharif	KURKURI
1268	S01D005B023V004	Patna	Ghoswari	KURMICHAK
1269	S01D005B020V007	Patna	Phulwari Sharif	KURTHOUL
1270	S01D005B022V009	Patna	Danapur	LAKHANI BIGHA
1271	S01D005B007V008	Patna	Punpun	LAKHANPUR
1272	S01D005B007V009	Patna	Punpun	LAKHNA EAST
1273	S01D005B007V010	Patna	Punpun	LAKHNA NORTH WEST
1274	S01D005B019V012	Patna	Masaurhi	LAKHNAUR BEDAULI
1275	S01D005B001V006	Patna	Dulhin Bajar	LAL BHADSHARA
1276	S01D005B006V013	Patna	Paliganj	LALGANJ SAHERA
1277	S01D005B016V006	Patna	Sampatchak	LANKA KACHHUARA
1278	S01D005B005V012	Patna	Pandarak	LEMUABAD
1279	S01D005B002V013	Patna	Bihta	MACHCHHALPUR LAI
1280	S01D005B006V014	Patna	Paliganj	MADHMA MAKHMILPUR
1281	S01D005B017V011	Patna	Maner	MADHOPUR
1282	S01D005B017V012	Patna	Maner	MAGARPAL
1283	S01D005B006V015	Patna	Paliganj	MAHABALIPUR CHOK
1284	S01D005B008V012	Patna	Bikram	MAHAJPURA
1285	S01D005B021V004	Patna	Patnasadar	MAHULI
1286	S01D005B015V011	Patna	Dhanarua	MAI NATAUL
1287	S01D005B002V014	Patna	Bihta	MAKHDOOMPUR
1290	S01D005B008V013	Patna	Bikram	MANER TELPA
1291	S01D005B011V012	Patna	Bakhtiyarpur	MANJHAULI
1292	S01D005B009V008	Patna	Fatuha	MANSINGPUR
1293	S01D005B013V008	Patna	Mokama	MARANCHI NORTH
1294	S01D005B013V009	Patna	Mokama	MARANCHI SOUTH
1295	S01D005B021V005	Patna	Patnasadar	MARCHI
1296	S01D005B009V009	Patna	Fatuha	MASADHI
1297	S01D005B006V016	Patna	Paliganj	MASHORA JALPURA
1298	S01D005B009V010	Patna	Fatuha	MAUJIPUR
1299	S01D005B013V010	Patna	Mokama	MEKRA
1300	S01D005B020V008	Patna	Phulwari Sharif	MENPURA SANDA
1301	S01D005B006V017	Patna	Paliganj	MERA PATONA
1302	S01D005B011V013	Patna	Bakhtiyarpur	MISSI
1303	S01D005B022V011	Patna	Danapur	MOBARKPUR RAGHURAMPUR
1304	S01D005B011V014	Patna	Bakhtiyarpur	MOGALPURA
1305	S01D005B009V011	Patna	Fatuha	MOHIUDDINPUR
1306	S01D005B009V012	Patna	Fatuha	MOMINDPUR
1307	S01D005B013V011	Patna	Mokama	MORE EAST
1308	S01D005B013V012	Patna	Mokama	MORE WEST
1309	S01D005B006V018	Patna	Paliganj	MORI PERPURA
1310	S01D005B008V014	Patna	Bikram	MORIYAWA SHIVGARH
1311	S01D005B015V012	Patna	Dhanarua	MORIYAWAN
1312	S01D005B010V006	Patna	Khusrupur	MOSIMPUR
1313	S01D005B006V019	Patna	Paliganj	MURIKA
1314	S01D005B002V015	Patna	Bihta	MUSHEPUR
1315	S01D005B019V013	Patna	Masaurhi	NADAUL
1316	S01D005B004V008	Patna	Barh	NADAWAN
1317	S01D005B006V020	Patna	Paliganj	NADHARI KODHARI
1318	S01D005B015V013	Patna	Dhanarua	NADWA
1319	S01D005B008V015	Patna	Bikram	NAGHAR
1320	S01D005B021V006	Patna	Patnasadar	NAKTA DIYARA
1321	S01D005B001V007	Patna	Dulhin Bajar	NARHI PIRHI
1322	S01D005B004V009	Patna	Barh	NARUADA
1323	S01D005B003V017	Patna	Naubatpur	NAUBATPUR
1324	S01D005B013V013	Patna	Mokama	NAURANGA JALALPUR
1325	S01D005B003V018	Patna	Naubatpur	NAVDIHYA
1326	S01D005B003V019	Patna	Naubatpur	NAVHI
1327	S01D005B002V016	Patna	Bihta	NEORA
1328	S01D005B015V014	Patna	Dhanarua	NIMARA
1329	S01D005B006V021	Patna	Paliganj	NIRAKHPUR PALI
1330	S01D005B003V020	Patna	Naubatpur	NISARPURA
1331	S01D005B019V014	Patna	Masaurhi	NISHIAWAN
1332	S01D005B020V009	Patna	Phulwari Sharif	NOHASA
1333	S01D005B021V007	Patna	Patnasadar	NORTH MAINPURA
1334	S01D005B019V015	Patna	Masaurhi	NURA
1335	S01D005B015V015	Patna	Dhanarua	PABHARA
1336	S01D005B023V005	Patna	Ghoswari	PAIJANA
1337	S01D005B007V011	Patna	Punpun	PAIMAR
1338	S01D005B002V017	Patna	Bihta	PAINAL
1339	S01D005B007V012	Patna	Punpun	PARATHU
1340	S01D005B002V018	Patna	Bihta	PAREV
1341	S01D005B020V010	Patna	Phulwari Sharif	PARSA
1342	S01D005B005V013	Patna	Pandarak	PARSAMAN
1343	S01D005B015V016	Patna	Dhanarua	PATHARAHAT
1344	S01D005B022V012	Patna	Danapur	PATLAPUR
1345	S01D005B008V016	Patna	Bikram	PATUT
1346	S01D005B014V004	Patna	Athmalgola	PHULELPUR MEURA
1347	S01D005B009V013	Patna	Fatuha	PITAMBARPUR
1348	S01D005B007V013	Patna	Punpun	POTHHI
1349	S01D005B021V008	Patna	Patnasadar	PUNADIH
1350	S01D005B007V014	Patna	Punpun	PUNPUN
1351	S01D005B022V013	Patna	Danapur	PURANI PANAPUR
1352	S01D005B002V019	Patna	Bihta	PURSHOTTAMPUR PAINATHI
1353	S01D005B002V020	Patna	Bihta	RAGHOPUR
1354	S01D005B004V010	Patna	Barh	RAHIMPUR RUPAS
1355	S01D005B005V014	Patna	Pandarak	RAILI
1356	S01D005B001V008	Patna	Dulhin Bajar	RAJIPUR
1357	S01D005B014V005	Patna	Athmalgola	RAMNAGAR DIARA
1358	S01D005B014V006	Patna	Athmalgola	RAMNAGAR KARARI KACHHAR
1359	S01D005B017V013	Patna	Maner	RAMPUR DIYARA TAUFFIR
1360	S01D005B013V014	Patna	Mokama	RAMPUR DUMRA
1361	S01D005B020V011	Patna	Phulwari Sharif	RAMPUR FARIDPUR
1362	S01D005B006V022	Patna	Paliganj	RAMPUR NAGMA
1363	S01D005B004V011	Patna	Barh	RANGBIGHA
1364	S01D005B006V023	Patna	Paliganj	RANIPUR KURKURI
1365	S01D005B019V016	Patna	Masaurhi	REWAN
1366	S01D005B009V014	Patna	Fatuha	RUKUNPUR
1367	S01D005B011V015	Patna	Bakhtiyarpur	RUPAS MAHAJI
1368	S01D005B021V009	Patna	Patnasadar	SABALPUR
1369	S01D005B014V007	Patna	Athmalgola	SABNIMA
1370	S01D005B001V009	Patna	Dulhin Bajar	SADAWAH DORAWA
1371	S01D005B017V014	Patna	Maner	SADIKPUR
1372	S01D005B002V021	Patna	Bihta	SADISOPUR
1373	S01D005B008V017	Patna	Bikram	SAIDABAD KANPA
1374	S01D005B020V012	Patna	Phulwari Sharif	SAKRAICHA
1375	S01D005B018V004	Patna	Daniyawan	SALARPUR
1376	S01D005B023V006	Patna	Ghoswari	SAMYAGARH
1377	S01D005B015V017	Patna	Dhanarua	SANDA
1378	S01D005B017V015	Patna	Maner	SARAI
1379	S01D005B022V014	Patna	Danapur	SARARI
1380	S01D005B004V012	Patna	Barh	SARKATTI SAIDPUR
1381	S01D005B011V016	Patna	Bakhtiyarpur	SATBHAIYA RAM NAGAR
1382	S01D005B015V018	Patna	Dhanarua	SATPARSA
1383	S01D005B003V021	Patna	Naubatpur	SAVARCHAK
1384	S01D005B001V010	Patna	Dulhin Bajar	SELAHAURI BELAHAURI
1385	S01D005B019V017	Patna	Masaurhi	SHAHABAD
1386	S01D005B018V005	Patna	Daniyawan	SHAHJAHANPUR
1387	S01D005B006V024	Patna	Paliganj	SHARJI PIPARDAHA
1388	S01D005B017V016	Patna	Maner	SHERPUR EAST
1389	S01D005B017V017	Patna	Maner	SHERPUR WEST
1390	S01D005B013V015	Patna	Mokama	SHIVNAR
1391	S01D005B004V013	Patna	Barh	SHOHARI
1392	S01D005B020V013	Patna	Phulwari Sharif	SHORAMPUR
1393	S01D005B002V022	Patna	Bihta	SHRI CHANDPUR
1394	S01D005B002V023	Patna	Bihta	SHRI RAMPUR
1395	S01D005B006V025	Patna	Paliganj	SIGORI
1396	S01D005B001V011	Patna	Dulhin Bajar	SIHI
1397	S01D005B002V024	Patna	Bihta	SIKANDARPUR
1398	S01D005B018V006	Patna	Daniyawan	SINGARIYAWAN
1399	S01D005B017V018	Patna	Maner	SINGHARA
1400	S01D005B001V012	Patna	Dulhin Bajar	SINGHARA KOPA
1401	S01D005B016V007	Patna	Sampatchak	SONA GOPALPUR
1402	S01D005B001V013	Patna	Dulhin Bajar	SONIYAWA
1403	S01D005B015V019	Patna	Dhanarua	SONMAI
1404	S01D005B021V010	Patna	Patnasadar	SONWAPUR
1405	S01D005B020V014	Patna	Phulwari Sharif	SUITHA
1406	S01D005B010V007	Patna	Khusrupur	SUKERBEG CHAK
1407	S01D005B017V019	Patna	Maner	SUWARMARWAH
1408	S01D005B002V025	Patna	Bihta	TARANAGAR
1409	S01D005B023V007	Patna	Ghoswari	TARTAR
1410	S01D005B019V018	Patna	Masaurhi	TINERI
1411	S01D005B023V008	Patna	Ghoswari	TRIMUHAN
1412	S01D005B001V014	Patna	Dulhin Bajar	ULAR SORAMPUR
1413	S01D005B009V015	Patna	Fatuha	USFA
1414	S01D005B014V008	Patna	Athmalgola	USMANPUR
1415	S01D005B008V018	Patna	Bikram	VAJIRPUR
1416	S01D005B015V020	Patna	Dhanarua	VIJAYAPURA
1417	S01D005B021V011	Patna	Patnasadar	WEST DIGHA
1418	S01D005B021V012	Patna	Patnasadar	WEST MAINPURA
1419	S01D005B005V015	Patna	Pandarak	WEST PANDARAK
1420	S01D005B012V007	Patna	Belchhi	WEST SAKSOHARA
1421	S01D005B002V026	Patna	Bihta	YAMUNAPUR
1422	S01D005B002V027	Patna	Bihta	Nathupur
1423	S01D005B008V019	Patna	Bikram	Sundarpur
1424	S01D005B001V015	Patna	Dulhin Bajar	Achhua
1425	S01D005B023V009	Patna	Ghoswari	Sahri
1426	S01D005B017V020	Patna	Maner	Nagwan
1427	S01D005B017V021	Patna	Maner	Sherpur
1428	S01D005B017V022	Patna	Maner	Sikandarpur
1429	S01D005B006V026	Patna	Paliganj	Raghunathpur
1430	S01D006B001V001	Saharsa	Mahishi	AEINA
1431	S01D006B002V001	Saharsa	Saur Bazar	AJGEBA
1432	S01D006B003V001	Saharsa	Salkhua	ALANI
1433	S01D006B004V001	Saharsa	Kahra	AMARPUR
1434	S01D006B001V002	Saharsa	Mahishi	ARAPATTI
1435	S01D006B005V001	Saharsa	Sonbarsa	ATALKHA
1436	S01D006B001V003	Saharsa	Mahishi	BAGHWA
1437	S01D006B006V001	Saharsa	Simri Bakhatiyarpur	BAGHWA
1438	S01D006B002V002	Saharsa	Saur Bazar	BAIDHNATHPUR
1439	S01D006B005V002	Saharsa	Sonbarsa	BAITH MUSAHHARI
1440	S01D006B007V001	Saharsa	Nauhatta	BAKUNIA
1441	S01D006B004V002	Saharsa	Kahra	BALAHAPATTI
1442	S01D006B004V003	Saharsa	Kahra	BANGAUN East
1443	S01D006B004V004	Saharsa	Kahra	BANGAUN North
1444	S01D006B004V005	Saharsa	Kahra	BANGAUN South
1445	S01D006B008V001	Saharsa	Sattar Katiya	BARA
1446	S01D006B008V002	Saharsa	Sattar Katiya	BARAHSER
1447	S01D006B005V003	Saharsa	Sonbarsa	BARAITH
1448	S01D006B005V004	Saharsa	Sonbarsa	BARGAUN
1449	S01D006B004V006	Saharsa	Kahra	BARIAHA
1450	S01D006B005V005	Saharsa	Sonbarsa	BARSAM
1451	S01D006B006V002	Saharsa	Simri Bakhatiyarpur	BELWARA
1452	S01D006B006V003	Saharsa	Simri Bakhatiyarpur	BHAKHTIYARPUR
1453	S01D006B001V004	Saharsa	Mahishi	BHALAHI
1454	S01D006B006V004	Saharsa	Simri Bakhatiyarpur	BHATONI
1455	S01D006B008V003	Saharsa	Sattar Katiya	BHELWA
1456	S01D006B008V004	Saharsa	Sattar Katiya	BIHRA
1457	S01D006B008V005	Saharsa	Sattar Katiya	BIJALPUR
1458	S01D006B005V006	Saharsa	Sonbarsa	BIRATPURPAITA
1459	S01D006B001V005	Saharsa	Mahishi	BIRGAUN
1460	S01D006B008V006	Saharsa	Sattar Katiya	BISHANPUR
1461	S01D006B009V001	Saharsa	Patarghat	BISHANPUR
1462	S01D006B004V007	Saharsa	Kahra	CHAINPUR
1463	S01D006B006V005	Saharsa	Simri Bakhatiyarpur	CHAKBHARO
1464	S01D006B003V002	Saharsa	Salkhua	CHANAN
1465	S01D006B002V003	Saharsa	Saur Bazar	CHANDOUR
1466	S01D006B002V004	Saharsa	Saur Bazar	CHANDOUR East
1467	S01D006B007V002	Saharsa	Nauhatta	CHANDRAYAN
1468	S01D006B007V003	Saharsa	Nauhatta	DARHAR
1469	S01D006B005V007	Saharsa	Sonbarsa	DEHAD
1470	S01D006B009V002	Saharsa	Patarghat	DHABAULI East
1471	S01D006B009V003	Saharsa	Patarghat	DHABAULI South
1472	S01D006B009V004	Saharsa	Patarghat	DHABAULI West
1473	S01D006B006V006	Saharsa	Simri Bakhatiyarpur	DHANPURA
1474	S01D006B004V008	Saharsa	Kahra	DIWARI
1475	S01D006B002V005	Saharsa	Saur Bazar	GAMHARIYA
1476	S01D006B001V006	Saharsa	Mahishi	GHOGHEPUR
1477	S01D006B006V007	Saharsa	Simri Bakhatiyarpur	GHOGHESAM
1478	S01D006B009V005	Saharsa	Patarghat	GOLMA East
1479	S01D006B009V006	Saharsa	Patarghat	GOLMA West
1480	S01D006B003V003	Saharsa	Salkhua	GORDAH
1481	S01D006B010V001	Saharsa	Banma Itahri	GORDAH
1482	S01D006B003V004	Saharsa	Salkhua	HAREBA
1483	S01D006B007V004	Saharsa	Nauhatta	HATI
1484	S01D006B010V002	Saharsa	Banma Itahri	ITAHRI
1485	S01D006B010V003	Saharsa	Banma Itahri	JAMAL NAGAR
1486	S01D006B009V007	Saharsa	Patarghat	JAMHARA
1487	S01D006B001V007	Saharsa	Mahishi	JHARA
1488	S01D006B003V005	Saharsa	Salkhua	KABIRA
1489	S01D006B002V006	Saharsa	Saur Bazar	KANP West
1490	S01D006B006V008	Saharsa	Simri Bakhatiyarpur	KANTHO
1491	S01D006B002V007	Saharsa	Saur Bazar	KARHAIYA
1492	S01D006B007V005	Saharsa	Nauhatta	KASHIMPUR
1493	S01D006B005V008	Saharsa	Sonbarsa	KASHNAGAR
1494	S01D006B006V009	Saharsa	Simri Bakhatiyarpur	KATHDUMAR
1495	S01D006B007V006	Saharsa	Nauhatta	KEDLI
1496	S01D006B005V009	Saharsa	Sonbarsa	KHAJURAHA
1497	S01D006B002V008	Saharsa	Saur Bazar	KHAJURI
1498	S01D006B006V010	Saharsa	Simri Bakhatiyarpur	KHAJURI
1499	S01D006B006V011	Saharsa	Simri Bakhatiyarpur	KHAMAUTI
1500	S01D006B007V007	Saharsa	Nauhatta	KHARKA TELWA
1501	S01D006B009V008	Saharsa	Patarghat	KISHANPUR
1502	S01D006B005V010	Saharsa	Sonbarsa	KOPA
1503	S01D006B003V006	Saharsa	Salkhua	KOPARYA
1504	S01D006B001V008	Saharsa	Mahishi	KUNDAH
1505	S01D006B005V011	Saharsa	Sonbarsa	LAGMA
1506	S01D006B010V004	Saharsa	Banma Itahri	MAHARAS
1507	S01D006B001V009	Saharsa	Mahishi	MAHESHI North
1508	S01D006B001V010	Saharsa	Mahishi	MAHESHI South
1509	S01D006B001V011	Saharsa	Mahishi	MAHISARHO
1510	S01D006B006V012	Saharsa	Simri Bakhatiyarpur	MAHKHAR
1511	S01D006B006V013	Saharsa	Simri Bakhatiyarpur	MAHMADPUR
1512	S01D006B005V012	Saharsa	Sonbarsa	MAHUA North
1513	S01D006B005V013	Saharsa	Sonbarsa	MANGWAR
1514	S01D006B001V012	Saharsa	Mahishi	MANWAR
1515	S01D006B004V009	Saharsa	Kahra	MOHANPUR
1516	S01D006B007V008	Saharsa	Nauhatta	MOHANPUR
1517	S01D006B006V014	Saharsa	Simri Bakhatiyarpur	MOHANPUR
1518	S01D006B005V014	Saharsa	Sonbarsa	MOKMA
1519	S01D006B003V007	Saharsa	Salkhua	MUBARAKPUR
1520	S01D006B007V009	Saharsa	Nauhatta	MURADPUR
1521	S01D006B004V010	Saharsa	Kahra	MURLI BASANTPUR
1522	S01D006B002V009	Saharsa	Saur Bazar	NADO
1523	S01D006B001V013	Saharsa	Mahishi	NAHARWAR
1524	S01D006B004V011	Saharsa	Kahra	NARIYAR
1525	S01D006B007V010	Saharsa	Nauhatta	NAUHATTA East
1526	S01D006B007V011	Saharsa	Nauhatta	NAUHATTA west
1527	S01D006B007V012	Saharsa	Nauhatta	NAULA
1528	S01D006B008V007	Saharsa	Sattar Katiya	OKAHI
1529	S01D006B006V015	Saharsa	Simri Bakhatiyarpur	PAHARPUR
1530	S01D006B009V009	Saharsa	Patarghat	PAMA
1531	S01D006B008V008	Saharsa	Sattar Katiya	PANCHGACHHIA
1532	S01D006B005V015	Saharsa	Sonbarsa	PARARIA
1533	S01D006B004V012	Saharsa	Kahra	PARRI
1534	S01D006B009V010	Saharsa	Patarghat	PASTPAR
1535	S01D006B001V014	Saharsa	Mahishi	PASTPAR
1536	S01D006B009V011	Saharsa	Patarghat	PATARGHAT
1537	S01D006B008V009	Saharsa	Sattar Katiya	PATORI
1538	S01D006B004V013	Saharsa	Kahra	PATUAHA
1539	S01D006B008V010	Saharsa	Sattar Katiya	PURIKH
1540	S01D006B005V016	Saharsa	Sonbarsa	RAGHUNATHPUR
1541	S01D006B006V016	Saharsa	Simri Bakhatiyarpur	RAIPURA
1542	S01D006B001V015	Saharsa	Mahishi	RAJANPUR
1543	S01D006B008V011	Saharsa	Sattar Katiya	RAKIYA
1544	S01D006B002V010	Saharsa	Saur Bazar	RAMPUR
1545	S01D006B010V005	Saharsa	Banma Itahri	RASALPUR
1546	S01D006B002V011	Saharsa	Saur Bazar	RAUTA
1547	S01D006B005V017	Saharsa	Sonbarsa	SAHPUR
1548	S01D006B007V013	Saharsa	Nauhatta	SAHPUR
1549	S01D006B005V018	Saharsa	Sonbarsa	SAHSAUL
1550	S01D006B010V006	Saharsa	Banma Itahri	SAHURIA
1551	S01D006B002V012	Saharsa	Saur Bazar	SAHURIYA East
1552	S01D006B002V013	Saharsa	Saur Bazar	SAHURIYA west
1553	S01D006B003V008	Saharsa	Salkhua	SALKHUA
1554	S01D006B003V009	Saharsa	Salkhua	SAMHAR KHURD
1555	S01D006B006V017	Saharsa	Simri Bakhatiyarpur	SARAUJA
1556	S01D006B005V019	Saharsa	Sonbarsa	SARAUNI MADHEPURA
1557	S01D006B006V018	Saharsa	Simri Bakhatiyarpur	SARDIHA
1558	S01D006B010V007	Saharsa	Banma Itahri	SARVELA
1559	S01D006B008V012	Saharsa	Sattar Katiya	SATTAR
1560	S01D006B007V014	Saharsa	Nauhatta	SATTAUR
1561	S01D006B008V013	Saharsa	Sattar Katiya	SHAHPUR
1562	S01D006B008V014	Saharsa	Sattar Katiya	SIHAUL
1563	S01D006B006V019	Saharsa	Simri Bakhatiyarpur	SIMRI
1564	S01D006B006V020	Saharsa	Simri Bakhatiyarpur	SIMRI BHAKHTIYARPUR
1565	S01D006B004V014	Saharsa	Kahra	SIRADAIEPATTI
1566	S01D006B001V016	Saharsa	Mahishi	SIRWAR
1567	S01D006B006V021	Saharsa	Simri Bakhatiyarpur	SITANABAD North
1568	S01D006B006V022	Saharsa	Simri Bakhatiyarpur	SITANABAD South
1569	S01D006B003V010	Saharsa	Salkhua	SITUAHA
1570	S01D006B005V020	Saharsa	Sonbarsa	SOHA
1571	S01D006B005V021	Saharsa	Sonbarsa	SONBARSA
1572	S01D006B006V023	Saharsa	Simri Bakhatiyarpur	SONPURA
1573	S01D006B002V014	Saharsa	Saur Bazar	SOUR BAZAR
1574	S01D006B002V015	Saharsa	Saur Bazar	SUHATH
1575	S01D006B004V015	Saharsa	Kahra	SULINDABAD
1576	S01D006B006V024	Saharsa	Simri Bakhatiyarpur	TARYAMA
1577	S01D006B001V017	Saharsa	Mahishi	TELHAR
1578	S01D006B001V018	Saharsa	Mahishi	TELWA East
1579	S01D006B001V019	Saharsa	Mahishi	TELWA West
1580	S01D006B002V016	Saharsa	Saur Bazar	TIRI
1581	S01D006B003V011	Saharsa	Salkhua	UTESRA
1582	S01D006B004V016	Saharsa	Kahra	Bishunpur
1583	S01D006B004V017	Saharsa	Kahra	Deona Gopal
1584	S01D006B004V018	Saharsa	Kahra	Dighia
1585	S01D006B004V019	Saharsa	Kahra	Harpur
1586	S01D006B004V020	Saharsa	Kahra	Parwania
1587	S01D006B004V021	Saharsa	Kahra	Rohuamon
1588	S01D006B001V020	Saharsa	Mahishi	Birgaon
1589	S01D007B001V001	Samastipur	Tajpur	ADHARPUR
1590	S01D007B002V001	Samastipur	Hasanpur	AHILWAR
1591	S01D007B003V001	Samastipur	Kalyanpur	AJNA
1592	S01D007B004V001	Samastipur	Dalsinghsarai	AJNAUL
1593	S01D007B005V001	Samastipur	Sarairanjan	AKHTIYARPUR BALBHADRAPUR
1594	S01D007B006V001	Samastipur	Bibhutipur	ALAMPUR KODARIA
1595	S01D007B007V001	Samastipur	Ujiyarpur	ANGAR
1596	S01D007B002V002	Samastipur	Hasanpur	AURA
1597	S01D007B008V001	Samastipur	Khanpur	BACHAWLI
1598	S01D007B009V001	Samastipur	Vidyapatinagar	BADHOUNA
2369	S01D004B005V000	Khagaria	Beldaur	
1599	S01D007B001V002	Samastipur	Tajpur	BAGHI
1600	S01D007B010V001	Samastipur	Mohanpur	BAGHRA
1601	S01D007B011V001	Samastipur	Patori	BAHADURPUR PATORI
1602	S01D007B007V002	Samastipur	Ujiyarpur	BAIKUNTHPUR BARHANDA
1603	S01D007B012V001	Samastipur	Samastipur	BAJITPUR
1604	S01D007B006V002	Samastipur	Bibhutipur	BAJITPUR BAMBAIYA
1605	S01D007B013V001	Samastipur	Morwa	BAJITPUR KARNAIL
1606	S01D007B005V002	Samastipur	Sarairanjan	BAJITPUR MEYARI
1607	S01D007B005V003	Samastipur	Sarairanjan	BAKHRI BUZURG
1608	S01D007B009V002	Samastipur	Vidyapatinagar	BALKRISHANPUR MARWA
1609	S01D007B014V001	Samastipur	Shivajeenagar	BALLIPUR
1610	S01D007B004V002	Samastipur	Dalsinghsarai	BAMBAIYA HARLAL
1611	S01D007B013V002	Samastipur	Morwa	BANBIRA
1612	S01D007B014V002	Samastipur	Shivajeenagar	BANDHAR
1613	S01D007B015V001	Samastipur	Singhia	BANGARAHTA
1614	S01D007B009V003	Samastipur	Vidyapatinagar	BANGRAHA
1615	S01D007B005V004	Samastipur	Sarairanjan	BARBATTA
1616	S01D007B002V003	Samastipur	Hasanpur	BARGAON
1617	S01D007B003V002	Samastipur	Kalyanpur	BARHETA
1618	S01D007B016V001	Samastipur	Warisnagar	BASANTPUR RAMANI
1619	S01D007B004V003	Samastipur	Dalsinghsarai	BASARIYA
1620	S01D007B003V003	Samastipur	Kalyanpur	BASUDEOPUR
1621	S01D007B005V005	Samastipur	Sarairanjan	BATHUA BUZURG
1622	S01D007B009V004	Samastipur	Vidyapatinagar	BAZIDPUR
1623	S01D007B012V002	Samastipur	Samastipur	BEGHADIH
1624	S01D007B007V003	Samastipur	Ujiyarpur	BELAMEGH
1625	S01D007B007V004	Samastipur	Ujiyarpur	BELARI
1626	S01D007B003V004	Samastipur	Kalyanpur	BELSANDI
1627	S01D007B017V001	Samastipur	Bithan	BELSANDI
1628	S01D007B006V003	Samastipur	Bibhutipur	BELSANDI TARA
1629	S01D007B018V001	Samastipur	Mohiuddinnagar	BHADAIYA
1630	S01D007B003V005	Samastipur	Kalyanpur	BHAGIRATHPUR
1631	S01D007B007V005	Samastipur	Ujiyarpur	BHAGWANPUR DESUA
1632	S01D007B007V006	Samastipur	Ujiyarpur	BHAGWANPUR KAMLA
1633	S01D007B005V006	Samastipur	Sarairanjan	BHAGWATPUR
1634	S01D007B006V004	Samastipur	Bibhutipur	BHARPURA PATPARA
1635	S01D007B019V001	Samastipur	Rosera	BHARWARI
1636	S01D007B014V003	Samastipur	Shivajeenagar	BHATAURA
1637	S01D007B002V004	Samastipur	Hasanpur	BHATWAN
1638	S01D007B001V003	Samastipur	Tajpur	BHEROKHARA
1639	S01D007B019V002	Samastipur	Rosera	BHIRAHA PURAB
1640	S01D007B019V003	Samastipur	Rosera	BHIRAHA SOUTH
1641	S01D007B019V004	Samastipur	Rosera	BHIRAHA WEST
1642	S01D007B008V002	Samastipur	Khanpur	BHORE JAYRAM
1643	S01D007B006V005	Samastipur	Bibhutipur	BHUSWAR
1644	S01D007B006V006	Samastipur	Bibhutipur	BIBHUTIPUR EAST
1645	S01D007B006V007	Samastipur	Bibhutipur	BIBHUTIPUR NORTH
1646	S01D007B012V003	Samastipur	Samastipur	BIKRAMPUR BANDEY
1647	S01D007B007V007	Samastipur	Ujiyarpur	BIRNAMA TULA
1648	S01D007B003V006	Samastipur	Kalyanpur	BIRSINGPUR
1649	S01D007B005V007	Samastipur	Sarairanjan	BISHAMBHARPUR ALOTH
1650	S01D007B008V003	Samastipur	Khanpur	BISHANPUR
1651	S01D007B012V004	Samastipur	Samastipur	BISHANPUR
1652	S01D007B020V001	Samastipur	Pusa	BISHANPUR BATHUA
1653	S01D007B010V002	Samastipur	Mohanpur	BISHANPUR BERI
1654	S01D007B015V002	Samastipur	Singhia	BISHNUPUR DIHA
1655	S01D007B017V002	Samastipur	Bithan	BITHAN
1656	S01D007B018V002	Samastipur	Mohiuddinnagar	BOACHHA
1657	S01D007B006V008	Samastipur	Bibhutipur	BORIA
1658	S01D007B004V004	Samastipur	Dalsinghsarai	BULAKIPUR
1659	S01D007B007V008	Samastipur	Ujiyarpur	CHAITA NORTH
1660	S01D007B007V009	Samastipur	Ujiyarpur	CHAITA SOUTH
1661	S01D007B004V005	Samastipur	Dalsinghsarai	CHAKBAHUDDIN
1662	S01D007B006V009	Samastipur	Bibhutipur	CHAKHABIB
1663	S01D007B020V002	Samastipur	Pusa	CHAKLA WAINI
1664	S01D007B003V007	Samastipur	Kalyanpur	CHAKMEHSI
1665	S01D007B012V005	Samastipur	Samastipur	CHAKNOOR
1666	S01D007B013V003	Samastipur	Morwa	CHAKPAHAR
1667	S01D007B011V002	Samastipur	Patori	CHAKSAHO
1668	S01D007B011V003	Samastipur	Patori	CHAKSALEM
1669	S01D007B013V004	Samastipur	Morwa	CHAKSIKANDAR
1670	S01D007B019V005	Samastipur	Rosera	CHAKTHAT EAST
1671	S01D007B019V006	Samastipur	Rosera	CHAKTHAT WEST
1672	S01D007B020V003	Samastipur	Pusa	CHANDAULI
1673	S01D007B007V010	Samastipur	Ujiyarpur	CHANDCHAUR EAST
1674	S01D007B007V011	Samastipur	Ujiyarpur	CHANDCHAUR KARIHARA
1675	S01D007B007V012	Samastipur	Ujiyarpur	CHANDCHAUR MIDDLE
1676	S01D007B007V013	Samastipur	Ujiyarpur	CHANDCHAUR WEST
1677	S01D007B012V006	Samastipur	Samastipur	CHHATAUNA
1678	S01D007B016V002	Samastipur	Warisnagar	CHHATNESHWAR
1679	S01D007B006V010	Samastipur	Bibhutipur	CHORA TABHAKA
1680	S01D007B007V014	Samastipur	Ujiyarpur	DADHIA MURIARO
1681	S01D007B014V004	Samastipur	Shivajeenagar	DAHIYAR RANNA
1682	S01D007B011V004	Samastipur	Patori	DAKSHINI DHAMAUN
1683	S01D007B011V005	Samastipur	Patori	DARBA
1684	S01D007B016V003	Samastipur	Warisnagar	DARSUR
1685	S01D007B014V005	Samastipur	Shivajeenagar	DASAUT
1686	S01D007B010V003	Samastipur	Mohanpur	DASHAHARA
1687	S01D007B020V004	Samastipur	Pusa	DEGHARA
1688	S01D007B002V005	Samastipur	Hasanpur	DEODHA
1689	S01D007B006V011	Samastipur	Bibhutipur	DESHARI KARRAKH
1690	S01D007B002V006	Samastipur	Hasanpur	DEVRA
1691	S01D007B016V004	Samastipur	Warisnagar	DHANHAR
1692	S01D007B020V005	Samastipur	Pusa	DHARAHA
1693	S01D007B005V008	Samastipur	Sarairanjan	DHARAMPUR
1694	S01D007B013V005	Samastipur	Morwa	DHARAMPUR BANDE
2370	S01D004B006V000	Khagaria	Gogri	
1695	S01D007B010V004	Samastipur	Mohanpur	DHARNIPATTI PASICHIM
1696	S01D007B010V005	Samastipur	Mohanpur	DHARNIPATTI PURBI
1697	S01D007B020V006	Samastipur	Pusa	DHOBGAMA
1698	S01D007B003V008	Samastipur	Kalyanpur	DHRUBGAMA
1699	S01D007B012V007	Samastipur	Samastipur	DHURLAKH
1700	S01D007B008V004	Samastipur	Khanpur	DINMANPUR DAKSHIN
1701	S01D007B008V005	Samastipur	Khanpur	DINMANPUR UTTAR
1702	S01D007B012V008	Samastipur	Samastipur	DOODHPURA
1703	S01D007B018V003	Samastipur	Mohiuddinnagar	DUBHA
1704	S01D007B002V007	Samastipur	Hasanpur	DUDHPURA
1705	S01D007B014V006	Samastipur	Shivajeenagar	DUMRA MOHAN
1706	S01D007B010V006	Samastipur	Mohanpur	DUMRI DAKSHINI
1707	S01D007B016V005	Samastipur	Warisnagar	DURLAKH
1708	S01D007B001V004	Samastipur	Tajpur	FATEHPUR
1709	S01D007B020V007	Samastipur	Pusa	GANGAPUR
1710	S01D007B005V009	Samastipur	Sarairanjan	GANGAPUR
1711	S01D007B006V012	Samastipur	Bibhutipur	GANGOLI MANDA
1712	S01D007B005V010	Samastipur	Sarairanjan	GANGSARA
1713	S01D007B009V005	Samastipur	Vidyapatinagar	GARDHSISAI
1714	S01D007B007V015	Samastipur	Ujiyarpur	GAUPUR
1715	S01D007B001V005	Samastipur	Tajpur	GAUSPUR SARSAUNA
1716	S01D007B014V007	Samastipur	Shivajeenagar	GHIWAHI
1717	S01D007B003V009	Samastipur	Kalyanpur	GOBINDPUR KHAJURI
1718	S01D007B016V006	Samastipur	Warisnagar	GOHI
1719	S01D007B003V010	Samastipur	Kalyanpur	GOPALPUR
1720	S01D007B003V011	Samastipur	Kalyanpur	GORAI
1721	S01D007B013V006	Samastipur	Morwa	GUNAI BASHI
1722	S01D007B003V012	Samastipur	Kalyanpur	HAJPURWA
1723	S01D007B012V009	Samastipur	Samastipur	HAKIMABAD
1724	S01D007B016V007	Samastipur	Warisnagar	HANSA
1725	S01D007B008V006	Samastipur	Khanpur	HANSOPUR
1726	S01D007B018V004	Samastipur	Mohiuddinnagar	HARAIL
1727	S01D007B015V003	Samastipur	Singhia	HARDIA
1728	S01D007B019V007	Samastipur	Rosera	HARIPUR
1729	S01D007B004V006	Samastipur	Dalsinghsarai	HARISHANKARPUR
1730	S01D007B001V006	Samastipur	Tajpur	HARISHANKARPUR BAGHAUNI
1731	S01D007B012V010	Samastipur	Samastipur	HARPUR ALOTH
1732	S01D007B005V011	Samastipur	Sarairanjan	HARPUR BARHETA
1733	S01D007B013V007	Samastipur	Morwa	HARPUR BHINDI
1734	S01D007B009V006	Samastipur	Vidyapatinagar	HARPUR BOCHAHA
1735	S01D007B020V008	Samastipur	Pusa	HARPUR MAHAMADA
1736	S01D007B007V016	Samastipur	Ujiyarpur	HARPUR REWARI
1737	S01D007B011V006	Samastipur	Patori	HARPUR SAIDABAD
1738	S01D007B002V008	Samastipur	Hasanpur	HASANPUR
1739	S01D007B011V007	Samastipur	Patori	HASANPUR SURAT
1740	S01D007B011V008	Samastipur	Patori	HETTANPUR
1741	S01D007B011V009	Samastipur	Patori	IMANSARAI
1742	S01D007B011V010	Samastipur	Patori	INAYATPUR
1743	S01D007B013V008	Samastipur	Morwa	INDRAWARA
1744	S01D007B017V003	Samastipur	Bithan	JAGMOHRA
1745	S01D007B008V007	Samastipur	Khanpur	JAHANGIRPUR
1746	S01D007B015V004	Samastipur	Singhia	JAHANGIRPUR
1747	S01D007B019V008	Samastipur	Rosera	JAHANGIRPUR NORTH
1748	S01D007B019V009	Samastipur	Rosera	JAHANGIRPUR SOUTH
1749	S01D007B014V008	Samastipur	Shivajeenagar	JAKHAR DHARAMPUR
1750	S01D007B010V007	Samastipur	Mohanpur	JALALPUR
1751	S01D007B005V012	Samastipur	Sarairanjan	JHAKHRA
1752	S01D007B003V013	Samastipur	Kalyanpur	JITWARIA
1753	S01D007B005V013	Samastipur	Sarairanjan	JITWARPUR
1754	S01D007B012V011	Samastipur	Samastipur	JITWARPUR CHAUTH
1755	S01D007B012V012	Samastipur	Samastipur	JITWARPUR NIJAMAT
1756	S01D007B011V011	Samastipur	Patori	JORPURA
1757	S01D007B003V014	Samastipur	Kalyanpur	KALOJAR
1758	S01D007B003V015	Samastipur	Kalyanpur	KALYANPUR
1759	S01D007B006V013	Samastipur	Bibhutipur	KALYANPUR NORTH
1760	S01D007B018V005	Samastipur	Mohiuddinnagar	KALYANPUR PASCHIM
1761	S01D007B018V006	Samastipur	Mohiuddinnagar	KALYANPUR PURAB
1762	S01D007B006V014	Samastipur	Bibhutipur	KALYANPUR SOUTH
1763	S01D007B004V007	Samastipur	Dalsinghsarai	KAMRAWN
1764	S01D007B009V007	Samastipur	Vidyapatinagar	KANCHA
1765	S01D007B008V008	Samastipur	Khanpur	KANUBISHANPUR
1766	S01D007B017V004	Samastipur	Bithan	KARANCHI
1767	S01D007B014V009	Samastipur	Shivajeenagar	KARIAN
1768	S01D007B018V007	Samastipur	Mohiuddinnagar	KARIMNAGAR
1769	S01D007B012V013	Samastipur	Samastipur	KARPOORIGRAM
1770	S01D007B001V007	Samastipur	Tajpur	KASEE AHAR
1771	S01D007B015V005	Samastipur	Singhia	KEOTHAR
1772	S01D007B006V015	Samastipur	Bibhutipur	KERAI
1773	S01D007B013V009	Samastipur	Morwa	KESHONARAYANPUR
1774	S01D007B004V008	Samastipur	Dalsinghsarai	KEWANTA
1775	S01D007B012V014	Samastipur	Samastipur	KEWAS NIJAMAT
1776	S01D007B008V009	Samastipur	Khanpur	KHAIRI
1777	S01D007B008V010	Samastipur	Khanpur	KHANPUR DAKSHIN
1778	S01D007B008V011	Samastipur	Khanpur	KHANPUR UTTAR
1779	S01D007B003V016	Samastipur	Kalyanpur	KHARSAND EAST
1780	S01D007B003V017	Samastipur	Kalyanpur	KHARSAND WEST
1781	S01D007B006V016	Samastipur	Bibhutipur	KHAS TABHAKA NORTH
1782	S01D007B006V017	Samastipur	Bibhutipur	KHAS TABHAKA SOUTH
1783	S01D007B005V014	Samastipur	Sarairanjan	KISHANPUR YUSUF
1784	S01D007B001V008	Samastipur	Tajpur	KOTHIYA
1785	S01D007B020V009	Samastipur	Pusa	KUBOULI
1786	S01D007B003V018	Samastipur	Kalyanpur	KUDHWA
1787	S01D007B015V006	Samastipur	Singhia	KUNDAL First
1788	S01D007B015V007	Samastipur	Singhia	KUNDAL Second
1789	S01D007B018V008	Samastipur	Mohiuddinnagar	KURSAHA
1790	S01D007B016V008	Samastipur	Warisnagar	KUSAIYA
1791	S01D007B003V019	Samastipur	Kalyanpur	LADNIA
1792	S01D007B012V015	Samastipur	Samastipur	LAGUNIA RAGHUKANTH
1793	S01D007B012V016	Samastipur	Samastipur	LAGUNIA SURYAKANTH
1794	S01D007B007V017	Samastipur	Ujiyarpur	LAKHANIPUR MAHESHPATTI
1795	S01D007B016V009	Samastipur	Warisnagar	LAKHANPATTI
1796	S01D007B013V010	Samastipur	Morwa	LARUA
1797	S01D007B005V015	Samastipur	Sarairanjan	LATBASEPURA
1798	S01D007B015V008	Samastipur	Singhia	LILHAUL
1799	S01D007B007V018	Samastipur	Ujiyarpur	LOHAGIR
1800	S01D007B001V009	Samastipur	Tajpur	MADHOPUR DHGHARUA
1801	S01D007B010V008	Samastipur	Mohanpur	MADHOPUR SARARI
1802	S01D007B003V020	Samastipur	Kalyanpur	MADHURAPUR
1803	S01D007B014V010	Samastipur	Shivajeenagar	MADHURAPUR
1804	S01D007B018V009	Samastipur	Mohiuddinnagar	MADUDABAD
1805	S01D007B020V010	Samastipur	Pusa	MAHAMADPUR DEOPAR
1806	S01D007B006V018	Samastipur	Bibhutipur	MAHATHI NORTH
1807	S01D007B006V019	Samastipur	Bibhutipur	MAHATHI SOUTH
1808	S01D007B015V009	Samastipur	Singhia	MAHEN
1809	S01D007B007V019	Samastipur	Ujiyarpur	MAHISARI
1810	S01D007B018V010	Samastipur	Mohiuddinnagar	MAHMADDIPUR
1811	S01D007B020V011	Samastipur	Pusa	MAHMADPUR KOURI
1812	S01D007B015V010	Samastipur	Singhia	MAHRA
1813	S01D007B003V021	Samastipur	Kalyanpur	MALINAGAR
1814	S01D007B004V009	Samastipur	Dalsinghsarai	MALPUR PURWARIPATTI
1815	S01D007B007V020	Samastipur	Ujiyarpur	MALTI
1816	S01D007B002V009	Samastipur	Hasanpur	MANGALGARH
1817	S01D007B005V016	Samastipur	Sarairanjan	MANIKPUR
1818	S01D007B016V010	Samastipur	Warisnagar	MANIYARPUR
1819	S01D007B009V008	Samastipur	Vidyapatinagar	MANIYARPUR
1820	S01D007B001V010	Samastipur	Tajpur	MANPURA
1821	S01D007B002V010	Samastipur	Hasanpur	MARANCHI UJAGAR
1822	S01D007B013V011	Samastipur	Morwa	MARICHA
1823	S01D007B017V005	Samastipur	Bithan	MARTHUA
1824	S01D007B016V011	Samastipur	Warisnagar	MATHURAPUR
1825	S01D007B002V011	Samastipur	Hasanpur	MAUJI
1826	S01D007B006V020	Samastipur	Bibhutipur	MEHASHI
1827	S01D007B019V010	Samastipur	Rosera	MNAGAR URF ROSARA PURB
1828	S01D007B019V011	Samastipur	Rosera	MNAGAR URF ROSERA WEST
1829	S01D007B012V017	Samastipur	Samastipur	MOHANPUR
1830	S01D007B010V009	Samastipur	Mohanpur	MOHANPUR
1831	S01D007B018V011	Samastipur	Mohiuddinnagar	MOHIUDDINNAGAR DAKSHIN
1832	S01D007B018V012	Samastipur	Mohiuddinnagar	MOHIUDDINNAGAR UTTAR
1833	S01D007B016V012	Samastipur	Warisnagar	MOHIUDDINPUR
1834	S01D007B004V010	Samastipur	Dalsinghsarai	MOKHTIYARPUR SALKHANI
1835	S01D007B012V018	Samastipur	Samastipur	MORDIWA
1836	S01D007B020V012	Samastipur	Pusa	MORSAND
1837	S01D007B013V012	Samastipur	Morwa	MORWA DAKSHNI
1838	S01D007B013V013	Samastipur	Morwa	MORWA UTARI
1839	S01D007B019V012	Samastipur	Rosera	MOTIPUR
1840	S01D007B009V009	Samastipur	Vidyapatinagar	MOW DHANESHPUR NORTH
1841	S01D007B009V010	Samastipur	Vidyapatinagar	MOW DHANESHPUR SOUTH
1842	S01D007B006V021	Samastipur	Bibhutipur	MUHAMMADPUR SAKRA
1843	S01D007B003V022	Samastipur	Kalyanpur	MUKTAPUR
1844	S01D007B001V011	Samastipur	Tajpur	MURADPUR BANGRA
1845	S01D007B012V019	Samastipur	Samastipur	MUSAPUR
1846	S01D007B005V017	Samastipur	Sarairanjan	MUSAPUR
1847	S01D007B006V022	Samastipur	Bibhutipur	MUSTAFAPUR
1848	S01D007B004V011	Samastipur	Dalsinghsarai	NAGARGAMA
1849	S01D007B002V012	Samastipur	Hasanpur	NAKUNI
1850	S01D007B003V023	Samastipur	Kalyanpur	NAMAPUR
1851	S01D007B005V018	Samastipur	Sarairanjan	NARGHOGHI
1852	S01D007B006V023	Samastipur	Bibhutipur	NARHAN
1853	S01D007B017V006	Samastipur	Bithan	NARPA
1854	S01D007B008V012	Samastipur	Khanpur	NATHUDWAR
1855	S01D007B005V019	Samastipur	Sarairanjan	NAUCHAK
1856	S01D007B004V012	Samastipur	Dalsinghsarai	NAWADA
1857	S01D007B002V013	Samastipur	Hasanpur	NAYANAGAR
1858	S01D007B007V021	Samastipur	Ujiyarpur	NAZIRPUR
1859	S01D007B013V014	Samastipur	Morwa	NIKASPUR
1860	S01D007B007V022	Samastipur	Ujiyarpur	NIKASPUR
1861	S01D007B012V020	Samastipur	Samastipur	NIRPUR
1862	S01D007B015V011	Samastipur	Singhia	NIRPUR BHARIRIYA
1863	S01D007B004V013	Samastipur	Dalsinghsarai	PAGRA
1864	S01D007B004V014	Samastipur	Dalsinghsarai	PANAR
1865	S01D007B002V014	Samastipur	Hasanpur	PARIDAH
1866	S01D007B007V023	Samastipur	Ujiyarpur	PARORIA
1867	S01D007B002V015	Samastipur	Hasanpur	PARORIYA
1868	S01D007B014V011	Samastipur	Shivajeenagar	PARSA
1869	S01D007B007V024	Samastipur	Ujiyarpur	PATAILI EAST
1870	S01D007B007V025	Samastipur	Ujiyarpur	PATAILI WEST
1871	S01D007B006V024	Samastipur	Bibhutipur	PATAILIYA
1872	S01D007B015V012	Samastipur	Singhia	PHULHARA
1873	S01D007B002V016	Samastipur	Hasanpur	PHULHARA
1874	S01D007B012V021	Samastipur	Samastipur	POKHRAIRA
1875	S01D007B012V022	Samastipur	Samastipur	PUNAS
1876	S01D007B016V013	Samastipur	Warisnagar	PURNAHI
1877	S01D007B008V013	Samastipur	Khanpur	PUROSHOTTAMPUR ANNU
1878	S01D007B003V024	Samastipur	Kalyanpur	PURUSHOTTAMPUR
1879	S01D007B017V007	Samastipur	Bithan	PUSAHO
1880	S01D007B014V012	Samastipur	Shivajeenagar	RAHIAR NORTH
1881	S01D007B014V013	Samastipur	Shivajeenagar	RAHIAR SOUTH
1882	S01D007B001V012	Samastipur	Tajpur	RAHIMABAD
1883	S01D007B012V023	Samastipur	Samastipur	RAHIMPUR RUDAULI
1884	S01D007B014V014	Samastipur	Shivajeenagar	RAHTAULI
1885	S01D007B019V013	Samastipur	Rosera	RAHUA
1886	S01D007B016V014	Samastipur	Warisnagar	RAIPUR
1887	S01D007B007V026	Samastipur	Ujiyarpur	RAIPUR
2371	S01D004B007V000	Khagaria	Chautham	
1888	S01D007B005V020	Samastipur	Sarairanjan	RAIPUR BUZURG
1889	S01D007B018V013	Samastipur	Mohiuddinnagar	RAJAJAN
1890	S01D007B014V015	Samastipur	Shivajeenagar	RAJAUR RAMBHADRAPUR
1891	S01D007B010V010	Samastipur	Mohanpur	RAJPUR
1892	S01D007B001V013	Samastipur	Tajpur	RAJWA
1893	S01D007B003V025	Samastipur	Kalyanpur	RAM BHADRAPUR
1894	S01D007B007V027	Samastipur	Ujiyarpur	RAMCHANDRAPUR ANDHAIL
1895	S01D007B002V017	Samastipur	Hasanpur	RAMPUR
1896	S01D007B016V015	Samastipur	Warisnagar	RAMPUR BISUN
1897	S01D007B004V015	Samastipur	Dalsinghsarai	RAMPUR JALALPUR
1898	S01D007B001V014	Samastipur	Tajpur	RAMPUR MAHESHPUR
1899	S01D007B014V016	Samastipur	Shivajeenagar	RANIPARDI
1900	S01D007B013V015	Samastipur	Morwa	RARIYAHI
1901	S01D007B018V014	Samastipur	Mohiuddinnagar	RASPUR PATASIA PASCHIM
1902	S01D007B018V015	Samastipur	Mohiuddinnagar	RASPUR PATASIA PURAB
1903	S01D007B003V026	Samastipur	Kalyanpur	RATABARA
1904	S01D007B008V014	Samastipur	Khanpur	REBRA
1905	S01D007B016V016	Samastipur	Warisnagar	ROHUA EAST
1906	S01D007B016V017	Samastipur	Warisnagar	ROHUA WEST
1907	S01D007B012V024	Samastipur	Samastipur	ROOP NARAYANPUR BELA
1908	S01D007B011V012	Samastipur	Patori	RUPAULI
1909	S01D007B005V021	Samastipur	Sarairanjan	RUPAULI BUZURG
1910	S01D007B008V015	Samastipur	Khanpur	SADIPUR
1911	S01D007B009V011	Samastipur	Vidyapatinagar	SAHIT
1912	S01D007B003V027	Samastipur	Kalyanpur	SAIDPUR
1913	S01D007B002V018	Samastipur	Hasanpur	SAKARPURA
1914	S01D007B006V025	Samastipur	Bibhutipur	SAKH MOHAN
1915	S01D007B017V008	Samastipur	Bithan	SAKHWA
1916	S01D007B017V009	Samastipur	Bithan	SALHA BUJURG
1917	S01D007B017V010	Samastipur	Bithan	SALHA CHANDAN
1918	S01D007B005V022	Samastipur	Sarairanjan	SARAIRANJAN EAST
1919	S01D007B005V023	Samastipur	Sarairanjan	SARAIRANJAN WEST
1920	S01D007B013V016	Samastipur	Morwa	SARANGPUR PACHAMI
1921	S01D007B013V017	Samastipur	Morwa	SARANGPUR PURBI
1922	S01D007B016V018	Samastipur	Warisnagar	SARI
1923	S01D007B007V028	Samastipur	Ujiyarpur	SATANPUR
1924	S01D007B016V019	Samastipur	Warisnagar	SATMALPUR
1925	S01D007B003V028	Samastipur	Kalyanpur	SEMARIYA BHINDI
1926	S01D007B011V013	Samastipur	Patori	SEORA
1927	S01D007B009V012	Samastipur	Vidyapatinagar	SERPUR DHEPURA
1928	S01D007B001V015	Samastipur	Tajpur	SHAHPUR BAGHAUNI
1929	S01D007B011V014	Samastipur	Patori	SHAHPUR UNDI
1930	S01D007B015V013	Samastipur	Singhia	SHALEPUR
1931	S01D007B012V025	Samastipur	Samastipur	SHAMBHUPATTI
1932	S01D007B014V017	Samastipur	Shivajeenagar	SHANKARPUR
1933	S01D007B002V019	Samastipur	Hasanpur	SHASHAN
1934	S01D007B020V013	Samastipur	Pusa	SHAUTH HARPUR PUSA
1935	S01D007B016V020	Samastipur	Warisnagar	SHEKHOPUR
1936	S01D007B018V016	Samastipur	Mohiuddinnagar	SHIBAISINGPUR
1937	S01D007B008V016	Samastipur	Khanpur	SHIVAISINGPUR
1938	S01D007B017V011	Samastipur	Bithan	SIHMA
1939	S01D007B009V013	Samastipur	Vidyapatinagar	SIMRI
1940	S01D007B006V026	Samastipur	Bibhutipur	SINGHIA BUJURG NORTH
1941	S01D007B006V027	Samastipur	Bibhutipur	SINGHIA BUJURG SOUTH
1942	S01D007B015V014	Samastipur	Singhia	SINGHIA First
1943	S01D007B012V026	Samastipur	Samastipur	SINGHIA KHURD
1944	S01D007B015V015	Samastipur	Singhia	SINGHIA Second
1945	S01D007B015V016	Samastipur	Singhia	SINGHIA Third
1946	S01D007B011V015	Samastipur	Patori	SIRDILPUR SUPAUL
1947	S01D007B008V017	Samastipur	Khanpur	SOBHAN
1948	S01D007B017V012	Samastipur	Bithan	SOHMA
1949	S01D007B003V029	Samastipur	Kalyanpur	SOMNAHA
1950	S01D007B013V018	Samastipur	Morwa	SONGAR
1951	S01D007B019V014	Samastipur	Rosera	SONUPUR MIRZAPUR SOUTH
1952	S01D007B019V015	Samastipur	Rosera	SONUPUR NORTH
1953	S01D007B003V030	Samastipur	Kalyanpur	SORMAR
1954	S01D007B009V014	Samastipur	Vidyapatinagar	SOTHGAMA
1955	S01D007B008V018	Samastipur	Khanpur	SRIPUR GAHAR PASCHIM
1956	S01D007B008V019	Samastipur	Khanpur	SRIPUR GAHAR PURBI
1957	S01D007B004V016	Samastipur	Dalsinghsarai	SULTANPUR GHATAHO
1958	S01D007B006V028	Samastipur	Bibhutipur	SURAULI
1959	S01D007B002V020	Samastipur	Hasanpur	SURHA BASANTPUR
1960	S01D007B006V029	Samastipur	Bibhutipur	TABHAKA
1961	S01D007B001V016	Samastipur	Tajpur	TAJPUR
1962	S01D007B011V016	Samastipur	Patori	TARA DHAMAUN
1963	S01D007B003V031	Samastipur	Kalyanpur	TERA
1964	S01D007B018V017	Samastipur	Mohiuddinnagar	TETARPUR
1965	S01D007B019V016	Samastipur	Rosera	THAHAR BASADHIA
1966	S01D007B017V013	Samastipur	Bithan	UJAN
1967	S01D007B011V017	Samastipur	Patori	UTTRI DHAMAUN
1968	S01D007B010V011	Samastipur	Mohanpur	UTTRI DUMRI
1969	S01D007B015V017	Samastipur	Singhia	WARI
1970	S01D007B017V014	Samastipur	Bithan	Banbhaura
1971	S01D007B004V017	Samastipur	Dalsinghsarai	Balo Chak
1972	S01D007B004V018	Samastipur	Dalsinghsarai	Biswaspur
1973	S01D007B004V019	Samastipur	Dalsinghsarai	Bhatgawan
1974	S01D007B004V020	Samastipur	Dalsinghsarai	Gauspur Inaet
1975	S01D007B004V021	Samastipur	Dalsinghsarai	Konaila
1976	S01D007B004V022	Samastipur	Dalsinghsarai	Mahnaia
1977	S01D007B004V023	Samastipur	Dalsinghsarai	Mathurapur
1978	S01D007B004V024	Samastipur	Dalsinghsarai	Raghubarpur
1979	S01D007B004V025	Samastipur	Dalsinghsarai	Soera
1980	S01D007B002V021	Samastipur	Hasanpur	Atapur
1981	S01D007B003V032	Samastipur	Kalyanpur	Gobindpur
1982	S01D007B003V033	Samastipur	Kalyanpur	Ladaura
1983	S01D007B019V017	Samastipur	Rosera	Dharampur Jairam
1984	S01D007B001V017	Samastipur	Tajpur	Baghauni Harshanker
1985	S01D007B007V029	Samastipur	Ujiyarpur	Krihara
1986	S01D007B007V030	Samastipur	Ujiyarpur	Muriaro
1987	S01D007B016V021	Samastipur	Warisnagar	Babupur
1988	S01D008B001V001	West Champaran	Majhaulia	AHAWAR KURIA
1989	S01D008B002V001	West Champaran	Bettiah	AHAWAR MAJHARIA
1990	S01D008B001V002	West Champaran	Majhaulia	AMAWA MAJHAR
1991	S01D008B003V001	West Champaran	Chanpatia	AURAIYA
1992	S01D008B004V001	West Champaran	Ramnagar	BAGAHI
1993	S01D008B005V001	West Champaran	Bairiya	BAGAHI BAGHAMBERPUR
1994	S01D008B006V001	West Champaran	Jogapatti	BAGAHI PURAINA
1995	S01D008B005V002	West Champaran	Bairiya	BAGAHI RATANPUR
1996	S01D008B006V002	West Champaran	Jogapatti	BAHUARAWA
1997	S01D008B001V003	West Champaran	Majhaulia	BAHUARAWA
1998	S01D008B007V001	West Champaran	Lauriya	BAHUARWA
1999	S01D008B005V003	West Champaran	Bairiya	BAIJUA
2000	S01D008B008V001	West Champaran	Nautan	BAIKUNTHAWA
2001	S01D008B009V001	West Champaran	Bagaha2	BAIRAGI SONABARASA
2002	S01D008B005V004	West Champaran	Bairiya	BAIRIYA
2003	S01D008B010V001	West Champaran	Sikta	BAISHAKHAWA
2004	S01D008B001V004	West Champaran	Majhaulia	BAITHANIA BHANACHAK
2005	S01D008B011V001	West Champaran	Gaunaha	BAJARA
2006	S01D008B001V005	West Champaran	Majhaulia	BAKHARIA
2007	S01D008B003V002	West Champaran	Chanpatia	BAKULAHAR
2008	S01D008B009V002	West Champaran	Bagaha2	BAKULI PANGWA
2009	S01D008B009V003	West Champaran	Bagaha2	BALMIKINAGAR
2010	S01D008B010V002	West Champaran	Sikta	BALTHAR
2011	S01D008B012V001	West Champaran	Piprasi	BALUA
2012	S01D008B006V003	West Champaran	Jogapatti	BALUA BHAWANIPUR
2013	S01D008B009V004	West Champaran	Bagaha2	BALUA CHHATRAUL
2014	S01D008B003V003	West Champaran	Chanpatia	BANKAT PURAINA
2015	S01D008B004V002	West Champaran	Ramnagar	BANKATWA KARAMAHIA
2016	S01D008B002V002	West Champaran	Bettiah	BANUCHHAPER
2017	S01D008B013V001	West Champaran	Narkatiaganj	BANWARIA
2018	S01D008B013V002	West Champaran	Narkatiaganj	BARAWA BARAULI
2019	S01D008B001V006	West Champaran	Majhaulia	BARAWA SEMARAGHAT
2020	S01D008B002V003	West Champaran	Bettiah	BARAWAT PRASARAIN
2021	S01D008B002V004	West Champaran	Bettiah	BARAWAT SENA
2022	S01D008B008V002	West Champaran	Nautan	BARDAHA
2023	S01D008B014V001	West Champaran	Bagaha1	BARGAON
2024	S01D008B014V002	West Champaran	Bagaha1	BARIARAWA SALHA
2025	S01D008B009V005	West Champaran	Bagaha2	BARIARWA
2026	S01D008B015V001	West Champaran	Madhuban	BARWA
2027	S01D008B016V001	West Champaran	Mainatand	BARWA
2028	S01D008B006V004	West Champaran	Jogapatti	BARWA OJHA
2029	S01D008B007V002	West Champaran	Lauriya	BASANTPUR
2030	S01D008B014V003	West Champaran	Bagaha1	BASAWARIA
2031	S01D008B007V003	West Champaran	Lauriya	BASAWARIA
2032	S01D008B014V004	West Champaran	Bagaha1	BASGAON
2033	S01D008B006V005	West Champaran	Jogapatti	BASOPATTI
2034	S01D008B007V004	West Champaran	Lauriya	BASWARIYA PARAUTOLA
2035	S01D008B005V005	West Champaran	Bairiya	BATHANA
2036	S01D008B014V005	West Champaran	Bagaha1	BATHUARIA TESRAHIYA
2037	S01D008B010V003	West Champaran	Sikta	BEHARA
2038	S01D008B009V006	West Champaran	Bagaha2	BELAHAWA MADANPUR
2039	S01D008B011V002	West Champaran	Gaunaha	BELAWA
2040	S01D008B011V003	West Champaran	Gaunaha	BELSANDI
2041	S01D008B007V005	West Champaran	Lauriya	BELWA LAKHANPUR
2042	S01D008B003V004	West Champaran	Chanpatia	BETTIAH DIH
2043	S01D008B008V003	West Champaran	Nautan	BHAGWANPUR
2044	S01D008B014V006	West Champaran	Bagaha1	BHAIROGANJ
2045	S01D008B014V007	West Champaran	Bagaha1	BHAISAHI PADARKHAP
2046	S01D008B003V005	West Champaran	Chanpatia	BHAISAHI POKHARIA
2047	S01D008B016V002	West Champaran	Mainatand	BHANGAHA
2048	S01D008B009V007	West Champaran	Bagaha2	BHARACHHI
2049	S01D008B013V003	West Champaran	Narkatiaganj	BHASURARI
2050	S01D008B013V004	West Champaran	Narkatiaganj	BHAVTA
2051	S01D008B004V003	West Champaran	Ramnagar	BHAWAL
2052	S01D008B006V006	West Champaran	Jogapatti	BHAWANIPUR
2053	S01D008B013V005	West Champaran	Narkatiaganj	BHERIHARWA
2054	S01D008B005V006	West Champaran	Bairiya	BHITAHAN
2055	S01D008B011V004	West Champaran	Gaunaha	BHITIHARAWA
2056	S01D008B017V001	West Champaran	Bhitha	BHUIDHARAWA
2057	S01D008B014V008	West Champaran	Bagaha1	BIBI BANKATAWA
2058	S01D008B013V006	West Champaran	Narkatiaganj	BINAWALIA
2059	S01D008B009V008	West Champaran	Bagaha2	BINWALIA BODHSER
2060	S01D008B001V007	West Champaran	Majhaulia	BISHAMBHARPUR
2061	S01D008B009V009	West Champaran	Bagaha2	BORAWAL NARAWAL
2062	S01D008B016V003	West Champaran	Mainatand	BUSTHA
2063	S01D008B009V010	West Champaran	Bagaha2	CHAMAWALIA
2064	S01D008B009V011	West Champaran	Bagaha2	CHAMPAPUR GONAULI
2065	S01D008B013V007	West Champaran	Narkatiaganj	CHAMUA
2066	S01D008B001V008	West Champaran	Majhaulia	CHANAYAN BANDH
2067	S01D008B014V009	West Champaran	Bagaha1	CHANDRAHAN RUPWALIA
2068	S01D008B014V010	West Champaran	Bagaha1	CHANDRAPUR RATWAL
2069	S01D008B003V006	West Champaran	Chanpatia	CHARGAHAN
2070	S01D008B016V004	West Champaran	Mainatand	CHAUHATTA
2071	S01D008B006V007	West Champaran	Jogapatti	CHAUMUKHA
2072	S01D008B017V002	West Champaran	Bhitha	CHILAWANIA
2073	S01D008B015V002	West Champaran	Madhuban	CHIURAHI
2074	S01D008B003V007	West Champaran	Chanpatia	CHUHARI
2075	S01D008B008V004	West Champaran	Nautan	DABARIYA
2076	S01D008B004V004	West Champaran	Ramnagar	DAINMARAWA
2077	S01D008B016V005	West Champaran	Mainatand	DAMARAPUR
2372	S01D005B001V000	Patna	Dulhin Bajar	
2078	S01D008B007V006	West Champaran	Lauriya	DANIYAL PRASAUNA
2079	S01D008B011V005	West Champaran	Gaunaha	DARAUL
2080	S01D008B015V003	West Champaran	Madhuban	DAUNAHAN
2081	S01D008B007V007	West Champaran	Lauriya	DEURAWA
2082	S01D008B006V008	West Champaran	Jogapatti	DHADHWA
2083	S01D008B011V006	West Champaran	Gaunaha	DHAMAURA
2084	S01D008B007V008	West Champaran	Lauriya	DHAMAURA
2085	S01D008B015V004	West Champaran	Madhuban	DHANAHA PAUSHAHARI
2086	S01D008B011V007	West Champaran	Gaunaha	DHANAUJI
2087	S01D008B010V004	West Champaran	Sikta	DHANKUTAWA
2088	S01D008B007V009	West Champaran	Lauriya	DHOBANI DHARAMPUR
2089	S01D008B007V010	West Champaran	Lauriya	DHOBINI
2090	S01D008B004V005	West Champaran	Ramnagar	DHOKARAHA
2091	S01D008B001V009	West Champaran	Majhaulia	DHOKARAHAN
2092	S01D008B009V012	West Champaran	Bagaha2	DHOLBAJWA LAXMIPUR
2093	S01D008B018V001	West Champaran	Thakaraha	DHUMNAGAR
2094	S01D008B013V008	West Champaran	Narkatiaganj	DHUMNAGAR
2095	S01D008B008V005	West Champaran	Nautan	DHUMNAGAR
2096	S01D008B017V003	West Champaran	Bhitha	DIHI PAKARI
2097	S01D008B011V008	West Champaran	Gaunaha	DOMATH
2098	S01D008B006V009	West Champaran	Jogapatti	DONAWAR
2099	S01D008B006V010	West Champaran	Jogapatti	DUMARI
2100	S01D008B001V010	West Champaran	Majhaulia	DUMARI
2101	S01D008B013V009	West Champaran	Narkatiaganj	DUMARIA
2102	S01D008B012V002	West Champaran	Piprasi	DUMRI BHAGERAWA
2103	S01D008B012V003	West Champaran	Piprasi	DUMRI MURADIH
2104	S01D008B014V011	West Champaran	Bagaha1	ENGLISHIA
2105	S01D008B006V011	West Champaran	Jogapatti	FATEHPUR
2106	S01D008B005V007	West Champaran	Bairiya	FULIAKHAND
2107	S01D008B008V006	West Champaran	Nautan	GAHIRI
2108	S01D008B001V011	West Champaran	Majhaulia	GARAWA HARPUR
2109	S01D008B011V009	West Champaran	Gaunaha	GAUNAHA
2110	S01D008B003V008	West Champaran	Chanpatia	GHOGHA NORTH
2111	S01D008B003V009	West Champaran	Chanpatia	GHOGHA SOUTH
2112	S01D008B003V010	West Champaran	Chanpatia	GIDHA
2113	S01D008B007V011	West Champaran	Lauriya	GOBARAURA
2114	S01D008B013V010	West Champaran	Narkatiaganj	GOKHULA
2115	S01D008B002V005	West Champaran	Bettiah	GONAULI
2116	S01D008B007V012	West Champaran	Lauriya	GONAULI DUMRA
2117	S01D008B010V005	West Champaran	Sikta	GOWCHARI
2118	S01D008B001V012	West Champaran	Majhaulia	GUDARA
2119	S01D008B004V006	West Champaran	Ramnagar	GUDGUDDI
2120	S01D008B003V011	West Champaran	Chanpatia	GURWALIA
2121	S01D008B014V012	West Champaran	Bagaha1	HARDI NADWA
2122	S01D008B013V011	West Champaran	Narkatiaganj	HARDI TEHRA
2123	S01D008B009V013	West Champaran	Bagaha2	HARNATAND
2124	S01D008B018V002	West Champaran	Thakaraha	HARPUR
2125	S01D008B006V012	West Champaran	Jogapatti	HATHIYA
2126	S01D008B017V004	West Champaran	Bhitha	HATHUAHWA
2127	S01D008B016V006	West Champaran	Mainatand	INARAWA
2128	S01D008B008V007	West Champaran	Nautan	JAGADISHPUR
2129	S01D008B010V006	West Champaran	Sikta	JAGANNATHPUR
2130	S01D008B018V003	West Champaran	Thakaraha	JAGIRAHAN
2131	S01D008B003V012	West Champaran	Chanpatia	JAITIA
2132	S01D008B009V014	West Champaran	Bagaha2	JAMUNAPUR TARWAWALIA
2133	S01D008B011V010	West Champaran	Gaunaha	JAMUNIA
2134	S01D008B008V008	West Champaran	Nautan	JAMUNIA
2135	S01D008B001V013	West Champaran	Majhaulia	JAUKATIA
2136	S01D008B008V009	West Champaran	Nautan	JHAKHARA
2137	S01D008B009V015	West Champaran	Bagaha2	JIMARI NAUTANAWA
2138	S01D008B001V014	West Champaran	Majhaulia	KARAMAWA
2139	S01D008B002V006	West Champaran	Bettiah	KARGAHIA PURABI
2140	S01D008B007V013	West Champaran	Lauriya	KATAIYA
2141	S01D008B015V005	West Champaran	Madhuban	KATHAR
2142	S01D008B010V007	West Champaran	Sikta	KATHIA MATHIA
2143	S01D008B013V012	West Champaran	Narkatiaganj	KEHUNIA ROARI
2144	S01D008B013V013	West Champaran	Narkatiaganj	KESHARIA
2145	S01D008B008V010	West Champaran	Nautan	KHADDA
2146	S01D008B017V005	West Champaran	Bhitha	KHAIRA
2147	S01D008B009V016	West Champaran	Bagaha2	KHARAHAR TIRBHAWNI
2148	S01D008B004V007	West Champaran	Ramnagar	KHATAURI
2149	S01D008B015V006	West Champaran	Madhuban	KHOTAHWA
2150	S01D008B006V013	West Champaran	Jogapatti	KHUTAWANIA JARALPUR
2151	S01D008B018V004	West Champaran	Thakaraha	KOIRPATTI
2152	S01D008B014V013	West Champaran	Bagaha1	KOLHUA CHAUTARAWA
2153	S01D008B013V014	West Champaran	Narkatiaganj	KUKURA
2154	S01D008B013V015	West Champaran	Narkatiaganj	KUNDILPUR
2155	S01D008B003V013	West Champaran	Chanpatia	KURAWA MATHIA
2156	S01D008B011V011	West Champaran	Gaunaha	LACHHANAUTA
2157	S01D008B014V014	West Champaran	Bagaha1	LAGUNAHA CHAUTARWA
2158	S01D008B007V014	West Champaran	Lauriya	LAKAR SISAI
2159	S01D008B003V014	West Champaran	Chanpatia	LAKHAURA
2160	S01D008B016V007	West Champaran	Mainatand	LAKSHMIPUR
2161	S01D008B001V015	West Champaran	Majhaulia	LAL SARAIYA
2162	S01D008B003V015	West Champaran	Chanpatia	LALGARH
2163	S01D008B005V008	West Champaran	Bairiya	LAUKARIA
2164	S01D008B007V015	West Champaran	Lauriya	LAURIYA
2165	S01D008B009V017	West Champaran	Bagaha2	LAXMIPUR RAMPURWA
2166	S01D008B003V016	West Champaran	Chanpatia	LOHIARIA
2167	S01D008B017V006	West Champaran	Bhitha	MACHHAHAN
2168	S01D008B006V014	West Champaran	Jogapatti	MACHHARGAWAN
2169	S01D008B011V012	West Champaran	Gaunaha	MADHOPUR
2170	S01D008B001V016	West Champaran	Majhaulia	MADHOPUR
2171	S01D008B015V007	West Champaran	Madhuban	MADHUA
2172	S01D008B015V008	West Champaran	Madhuban	MADHUBAN
2173	S01D008B016V008	West Champaran	Mainatand	MADHURI
2174	S01D008B001V017	West Champaran	Majhaulia	MAHANA GANI
2175	S01D008B001V018	West Champaran	Majhaulia	MAHANWA RAMPURWA
2176	S01D008B014V015	West Champaran	Bagaha1	MAHIPUR BHATHAURA
2177	S01D008B003V017	West Champaran	Chanpatia	MAHNA KULI
2178	S01D008B001V019	West Champaran	Majhaulia	MAHODDIPUR
2179	S01D008B009V018	West Champaran	Bagaha2	MAHUAWA KATHARAWA
2180	S01D008B016V009	West Champaran	Mainatand	MAHUAWA SANGRAUA
2181	S01D008B004V008	West Champaran	Ramnagar	MAHUI
2182	S01D008B011V013	West Champaran	Gaunaha	MAHUI
2183	S01D008B016V010	West Champaran	Mainatand	MAINATAND
2184	S01D008B011V014	West Champaran	Gaunaha	MAJHARIA
2185	S01D008B012V004	West Champaran	Piprasi	MAJHARIA
2186	S01D008B001V020	West Champaran	Majhaulia	MAJHARIA SHEIKH
2187	S01D008B001V021	West Champaran	Majhaulia	MAJHAULIA
2188	S01D008B014V016	West Champaran	Bagaha1	MAJHAUWA
2189	S01D008B005V009	West Champaran	Bairiya	MALAHI BALUA
2190	S01D008B013V016	West Champaran	Narkatiaganj	MALDAHIYA POKHARIYA
2191	S01D008B004V009	West Champaran	Ramnagar	MANCHANGWA
2192	S01D008B009V019	West Champaran	Bagaha2	MANGALPUR AUSANI
2193	S01D008B008V011	West Champaran	Nautan	MANGALPUR GUDARIA
2194	S01D008B008V012	West Champaran	Nautan	MANGALPUR KALA
2195	S01D008B017V007	West Champaran	Bhitha	MANPUR
2196	S01D008B013V017	West Champaran	Narkatiaganj	MANWA PARSI
2197	S01D008B010V008	West Champaran	Sikta	MASWAS
2198	S01D008B004V010	West Champaran	Ramnagar	MATHIA
2199	S01D008B007V016	West Champaran	Lauriya	MATHIA
2200	S01D008B011V015	West Champaran	Gaunaha	MATIARAWA
2201	S01D008B011V016	West Champaran	Gaunaha	MEHNAUL
2202	S01D008B014V017	West Champaran	Bagaha1	MEHURA
2203	S01D008B005V010	West Champaran	Bairiya	MIYAPUR
2204	S01D008B018V005	West Champaran	Thakaraha	MOTIPUR
2205	S01D008B003V018	West Champaran	Chanpatia	MUSAHARI SENUARIA
2206	S01D008B014V018	West Champaran	Bagaha1	NADDA
2207	S01D008B009V020	West Champaran	Bagaha2	NAURANGIA DARDAHI
2208	S01D008B004V011	West Champaran	Ramnagar	NAURANGIA DONE
2209	S01D008B008V013	West Champaran	Nautan	NAUTAN EAST
2210	S01D008B001V022	West Champaran	Majhaulia	NAUTAN KHURD
2211	S01D008B008V014	West Champaran	Nautan	NAUTAN WEST
2212	S01D008B013V018	West Champaran	Narkatiaganj	NAUTANWA
2213	S01D008B006V015	West Champaran	Jogapatti	NAWALPUR
2214	S01D008B008V015	West Champaran	Nautan	PAKADIA
2215	S01D008B007V017	West Champaran	Lauriya	PAKARI BHARAHIYA
2216	S01D008B005V011	West Champaran	Bairiya	PAKHANAHA DUMARIA
2217	S01D008B009V021	West Champaran	Bagaha2	PAKWALIA MARJADPUR
2218	S01D008B013V019	West Champaran	Narkatiaganj	PARORAHAN
2219	S01D008B001V023	West Champaran	Majhaulia	PARSA
2220	S01D008B014V019	West Champaran	Bagaha1	PARSA BANCHAHARI
2221	S01D008B004V012	West Champaran	Ramnagar	PARSAUNI
2222	S01D008B014V020	West Champaran	Bagaha1	PATILAR
2223	S01D008B005V012	West Champaran	Bairiya	PATJIRWA NORTH
2224	S01D008B005V013	West Champaran	Bairiya	PATJIRWA SOUTH
2225	S01D008B006V016	West Champaran	Jogapatti	PIPARA NAURANGIA
2226	S01D008B002V007	West Champaran	Bettiah	PIPARA PAKARI
2227	S01D008B006V017	West Champaran	Jogapatti	PIPARAHIYA
2228	S01D008B012V005	West Champaran	Piprasi	PIPARASI
2229	S01D008B014V021	West Champaran	Bagaha1	PIPARIA SINGARI
2230	S01D008B016V011	West Champaran	Mainatand	PIRARI
2231	S01D008B003V019	West Champaran	Chanpatia	POKHARIA KHARZ
2232	S01D008B017V008	West Champaran	Bhitha	PRASAUNA
2233	S01D008B010V009	West Champaran	Sikta	PRASAUNI
2234	S01D008B010V010	West Champaran	Sikta	PURAINA
2235	S01D008B016V012	West Champaran	Mainatand	PURANIA
2236	S01D008B013V020	West Champaran	Narkatiaganj	PURNIA HARSARI
2237	S01D008B014V022	West Champaran	Bagaha1	RAIBARI MAHUAWA
2238	S01D008B001V024	West Champaran	Majhaulia	RAJABHAR
2239	S01D008B013V021	West Champaran	Narkatiaganj	RAJPUR TUMKARIA
2240	S01D008B014V023	West Champaran	Bagaha1	RAJWATIA CHAKHANI
2241	S01D008B013V022	West Champaran	Narkatiaganj	RAKAHI CHAMPAPUR
2242	S01D008B001V025	West Champaran	Majhaulia	RAMNAGAR BANKAT
2243	S01D008B016V013	West Champaran	Mainatand	RAMPUR
2244	S01D008B003V020	West Champaran	Chanpatia	RANIPUR RAMPURAWA
2245	S01D008B001V026	West Champaran	Majhaulia	RATANMALA
2246	S01D008B001V027	West Champaran	Majhaulia	RULAHI
2247	S01D008B011V017	West Champaran	Gaunaha	RUPAULIUA
2248	S01D008B004V013	West Champaran	Ramnagar	SABAIYA
2249	S01D008B016V014	West Champaran	Mainatand	SAKRAUL
2250	S01D008B008V016	West Champaran	Nautan	SANSARAIYA
2251	S01D008B009V022	West Champaran	Bagaha2	SANTPUR SOHARIA
2252	S01D008B004V014	West Champaran	Ramnagar	SAPAHI MADHUBANI
2253	S01D008B010V011	West Champaran	Sikta	SARGATIA
2254	S01D008B001V028	West Champaran	Majhaulia	SARISAWA
2255	S01D008B007V018	West Champaran	Lauriya	SATHI
2256	S01D008B012V006	West Champaran	Piprasi	SAURAHAN
2257	S01D008B012V007	West Champaran	Piprasi	SEMARA LABEDAHA
2258	S01D008B013V023	West Champaran	Narkatiaganj	SEMARI
2259	S01D008B017V009	West Champaran	Bhitha	SEMARWARI
2260	S01D008B009V023	West Champaran	Bagaha2	SEMRA KATHAKUIA
2261	S01D008B001V029	West Champaran	Majhaulia	SENUARIA
2262	S01D008B013V024	West Champaran	Narkatiaganj	SERAHWA
2263	S01D008B013V025	West Champaran	Narkatiaganj	SHIKARPUR
2264	S01D008B010V012	West Champaran	Sikta	SHIKARPUR
2265	S01D008B008V017	West Champaran	Nautan	SHIVARAJPUR
2266	S01D008B008V018	West Champaran	Nautan	SHYAMPUR KOTRAHA
2267	S01D008B010V013	West Champaran	Sikta	SIKTA
2268	S01D008B007V019	West Champaran	Lauriya	SINGHPUR SATAWARIA
2269	S01D008B010V014	West Champaran	Sikta	SIRISIA
2270	S01D008B003V021	West Champaran	Chanpatia	SIRISIA
2271	S01D008B015V009	West Champaran	Madhuban	SISAI
2272	S01D008B014V024	West Champaran	Bagaha1	SISAWA BASANTPUR
2273	S01D008B006V018	West Champaran	Jogapatti	SISAWA BHUMIHAR
2274	S01D008B006V019	West Champaran	Jogapatti	SISAWA MANGALPUR
2275	S01D008B005V014	West Champaran	Bairiya	SISAWA SARAIYA
2276	S01D008B006V020	West Champaran	Jogapatti	SISWA BAIRAGI
2277	S01D008B007V020	West Champaran	Lauriya	SISWANIA
2278	S01D008B011V018	West Champaran	Gaunaha	SITHTHI
2279	S01D008B004V015	West Champaran	Ramnagar	SOHASA
2280	S01D008B013V026	West Champaran	Narkatiaganj	SOMGARH
2281	S01D008B004V016	West Champaran	Ramnagar	SONKHAR
2282	S01D008B018V006	West Champaran	Thakaraha	SRI NAGAR
2283	S01D008B010V015	West Champaran	Sikta	SUGAHA BHAWANIPUR
2284	S01D008B013V027	West Champaran	Narkatiaganj	SUGAULI
2285	S01D008B016V015	West Champaran	Mainatand	SUKHALAHI
2286	S01D008B010V016	West Champaran	Sikta	SURYAPUR
2287	S01D008B005V015	West Champaran	Bairiya	SURYAPUR
2288	S01D008B005V016	West Champaran	Bairiya	TADHAWA NANDPUR
2289	S01D008B015V010	West Champaran	Madhuban	TAMKUHA
2290	S01D008B009V024	West Champaran	Bagaha2	TARUANAWA DEWARIA
2291	S01D008B004V017	West Champaran	Ramnagar	TAULAHAN
2292	S01D008B008V019	West Champaran	Nautan	TELAHUA NORTH
2293	S01D008B008V020	West Champaran	Nautan	TELAHUA SOUTH
2294	S01D008B007V021	West Champaran	Lauriya	TELPUR
2295	S01D008B018V007	West Champaran	Thakaraha	THAKARAHAN
2296	S01D008B016V016	West Champaran	Mainatand	TOLA CHAPARIA
2297	S01D008B005V017	West Champaran	Bairiya	TUMKARIA
2298	S01D008B003V022	West Champaran	Chanpatia	TUNIA VISHUNPUR
2299	S01D008B003V023	West Champaran	Chanpatia	TURAHAPATTI EAST
2300	S01D008B003V024	West Champaran	Chanpatia	TURAHAPATTI WEST
2301	S01D008B004V018	West Champaran	Ramnagar	YOGIA
2302	S01D008B003V025	West Champaran	Chanpatia	TURAHHA PATTI
2303	S01D008B011V019	West Champaran	Gaunaha	Amolwa
2304	S01D008B007V022	West Champaran	Lauriya	DHARAMPUR
2305	S01D008B012V008	West Champaran	Piprasi	DUMRI
2306	S01D001B001V000	Begusarai	Bachhwara	
2307	S01D001B002V000	Begusarai	Bakhri	
2308	S01D001B003V000	Begusarai	Balia	
2309	S01D001B004V000	Begusarai	Barauni	
2310	S01D001B005V000	Begusarai	Begusarai	
2311	S01D001B006V000	Begusarai	Bhagwanpur	
2312	S01D001B007V000	Begusarai	Birpur	
2313	S01D001B008V000	Begusarai	Cheria Bariyarpur	
2314	S01D001B009V000	Begusarai	Chhaurahi	
2315	S01D001B010V000	Begusarai	Dandri	
2316	S01D001B011V000	Begusarai	Garhpura	
2317	S01D001B012V000	Begusarai	Khodabandpur	
2318	S01D001B013V000	Begusarai	Mansoorchak	
2319	S01D001B014V000	Begusarai	Matihani	
2320	S01D001B015V000	Begusarai	Navkothi	
2321	S01D001B016V000	Begusarai	Sahebpur Kamal	
2322	S01D001B017V000	Begusarai	Shamho Akha Kurha	
2323	S01D001B018V000	Begusarai	Teghra	
2324	S01D002B001V000	East Champaran	Adapur	
2325	S01D002B002V000	East Champaran	Areraj	
2326	S01D002B003V000	East Champaran	Banjariya	
2327	S01D002B004V000	East Champaran	Chakia	
2328	S01D002B005V000	East Champaran	Chhauradano	
2329	S01D002B006V000	East Champaran	Chiraiya	
2330	S01D002B007V000	East Champaran	Dhaka	
2331	S01D002B008V000	East Champaran	Ghorasahan	
2332	S01D002B009V000	East Champaran	Harsidhi	
2333	S01D002B010V000	East Champaran	Kalyanpur	
2334	S01D002B011V000	East Champaran	Kesaria	
2335	S01D002B012V000	East Champaran	Kotwa	
2336	S01D002B013V000	East Champaran	Madhuban	
2337	S01D002B014V000	East Champaran	Mehsi	
2338	S01D002B015V000	East Champaran	Motihari	
2339	S01D002B016V000	East Champaran	Paharpur	
2340	S01D002B017V000	East Champaran	Pakaridayal	
2341	S01D002B018V000	East Champaran	Patahi	
2342	S01D002B019V000	East Champaran	Phenhara	
2343	S01D002B020V000	East Champaran	Piprakothi	
2344	S01D002B021V000	East Champaran	Ramgarhwa	
2345	S01D002B022V000	East Champaran	Raxaul	
2346	S01D002B023V000	East Champaran	Sangrampur	
2347	S01D002B024V000	East Champaran	Sugauli	
2348	S01D002B025V000	East Champaran	Tetaria	
2349	S01D002B026V000	East Champaran	Turkauliya	
2350	S01D002B027V000	East Champaran	Bankatwa	
2351	S01D003B001V000	Gopalganj	Manjha	
2352	S01D003B002V000	Gopalganj	Kuchaikot	
2353	S01D003B003V000	Gopalganj	Vijaipur	
2354	S01D003B004V000	Gopalganj	Baikunthpur	
2355	S01D003B005V000	Gopalganj	Sidhwalia	
2356	S01D003B006V000	Gopalganj	Kateya	
2357	S01D003B007V000	Gopalganj	Gopalganj	
2358	S01D003B008V000	Gopalganj	Bhorey	
2359	S01D003B009V000	Gopalganj	Barauli	
2360	S01D003B010V000	Gopalganj	Uchkagaon	
2361	S01D003B011V000	Gopalganj	Panchdevri	
2362	S01D003B012V000	Gopalganj	Thawe	
2363	S01D003B013V000	Gopalganj	Hathua	
2364	S01D003B014V000	Gopalganj	Phulwaria	
2365	S01D004B001V000	Khagaria	Allouli Beldaur	
2366	S01D004B002V000	Khagaria	Mansi	
2367	S01D004B003V000	Khagaria	Khagaria	
2373	S01D005B002V000	Patna	Bihta	
2374	S01D005B003V000	Patna	Naubatpur	
2375	S01D005B004V000	Patna	Barh	
2376	S01D005B005V000	Patna	Pandarak	
2377	S01D005B006V000	Patna	Paliganj	
2378	S01D005B007V000	Patna	Punpun	
2379	S01D005B008V000	Patna	Bikram	
2380	S01D005B009V000	Patna	Fatuha	
2381	S01D005B010V000	Patna	Khusrupur	
2382	S01D005B011V000	Patna	Bakhtiyarpur	
2383	S01D005B012V000	Patna	Belchhi	
2384	S01D005B013V000	Patna	Mokama	
2385	S01D005B014V000	Patna	Athmalgola	
2386	S01D005B015V000	Patna	Dhanarua	
2387	S01D005B016V000	Patna	Sampatchak	
2388	S01D005B017V000	Patna	Maner	
2389	S01D005B018V000	Patna	Daniyawan	
2390	S01D005B019V000	Patna	Masaurhi	
2391	S01D005B020V000	Patna	Phulwari Sharif	
2392	S01D005B021V000	Patna	Patnasadar	
2393	S01D005B022V000	Patna	Danapur	
2394	S01D005B023V000	Patna	Ghoswari	
2395	S01D006B001V000	Saharsa	Mahishi	
2396	S01D006B002V000	Saharsa	Saur Bazar	
2397	S01D006B003V000	Saharsa	Salkhua	
2398	S01D006B004V000	Saharsa	Kahra	
2399	S01D006B005V000	Saharsa	Sonbarsa	
2400	S01D006B006V000	Saharsa	Simri Bakhatiyarpur	
2401	S01D006B007V000	Saharsa	Nauhatta	
2402	S01D006B008V000	Saharsa	Sattar Katiya	
2403	S01D006B009V000	Saharsa	Patarghat	
2404	S01D006B010V000	Saharsa	Banma Itahri	
2405	S01D007B001V000	Samastipur	Tajpur	
2406	S01D007B002V000	Samastipur	Hasanpur	
2407	S01D007B003V000	Samastipur	Kalyanpur	
2408	S01D007B004V000	Samastipur	Dalsinghsarai	
2409	S01D007B005V000	Samastipur	Sarairanjan	
2410	S01D007B006V000	Samastipur	Bibhutipur	
2411	S01D007B007V000	Samastipur	Ujiyarpur	
2412	S01D007B008V000	Samastipur	Khanpur	
2413	S01D007B009V000	Samastipur	Vidyapatinagar	
2414	S01D007B010V000	Samastipur	Mohanpur	
2415	S01D007B011V000	Samastipur	Patori	
2416	S01D007B012V000	Samastipur	Samastipur	
2417	S01D007B013V000	Samastipur	Morwa	
2418	S01D007B014V000	Samastipur	Shivajeenagar	
2419	S01D007B015V000	Samastipur	Singhia	
2420	S01D007B016V000	Samastipur	Warisnagar	
2421	S01D007B017V000	Samastipur	Bithan	
2422	S01D007B018V000	Samastipur	Mohiuddinnagar	
2423	S01D007B019V000	Samastipur	Rosera	
2424	S01D007B020V000	Samastipur	Pusa	
2425	S01D008B001V000	West Champaran	Majhaulia	
2426	S01D008B002V000	West Champaran	Bettiah	
2427	S01D008B003V000	West Champaran	Chanpatia	
2428	S01D008B004V000	West Champaran	Ramnagar	
2429	S01D008B005V000	West Champaran	Bairiya	
2430	S01D008B006V000	West Champaran	Jogapatti	
2431	S01D008B007V000	West Champaran	Lauriya	
2432	S01D008B008V000	West Champaran	Nautan	
2433	S01D008B009V000	West Champaran	Bagaha2	
2434	S01D008B010V000	West Champaran	Sikta	
2435	S01D008B011V000	West Champaran	Gaunaha	
2436	S01D008B012V000	West Champaran	Piprasi	
2437	S01D008B013V000	West Champaran	Narkatiaganj	
2438	S01D008B014V000	West Champaran	Bagaha1	
2439	S01D008B015V000	West Champaran	Madhuban	
2440	S01D008B016V000	West Champaran	Mainatand	
2441	S01D008B017V000	West Champaran	Bhitha	
2442	S01D008B018V000	West Champaran	Thakaraha	
\.


--
-- Data for Name: time_dimension; Type: TABLE DATA; Schema: report; Owner: postgres
--

COPY time_dimension (id, day, week, month, year, date) FROM stdin;
2	2	1	1	2012	2012-01-02
3	3	1	1	2012	2012-01-03
4	4	1	1	2012	2012-01-04
5	5	1	1	2012	2012-01-05
6	6	1	1	2012	2012-01-06
7	7	1	1	2012	2012-01-07
8	8	1	1	2012	2012-01-08
10	10	2	1	2012	2012-01-10
11	11	2	1	2012	2012-01-11
12	12	2	1	2012	2012-01-12
13	13	2	1	2012	2012-01-13
14	14	2	1	2012	2012-01-14
15	15	2	1	2012	2012-01-15
16	16	3	1	2012	2012-01-16
17	17	3	1	2012	2012-01-17
18	18	3	1	2012	2012-01-18
19	19	3	1	2012	2012-01-19
21	21	3	1	2012	2012-01-21
22	22	3	1	2012	2012-01-22
23	23	4	1	2012	2012-01-23
24	24	4	1	2012	2012-01-24
25	25	4	1	2012	2012-01-25
26	26	4	1	2012	2012-01-26
27	27	4	1	2012	2012-01-27
28	28	4	1	2012	2012-01-28
29	29	4	1	2012	2012-01-29
30	30	5	1	2012	2012-01-30
32	32	5	2	2012	2012-02-01
33	33	5	2	2012	2012-02-02
34	34	5	2	2012	2012-02-03
35	35	5	2	2012	2012-02-04
36	36	5	2	2012	2012-02-05
37	37	6	2	2012	2012-02-06
38	38	6	2	2012	2012-02-07
39	39	6	2	2012	2012-02-08
40	40	6	2	2012	2012-02-09
41	41	6	2	2012	2012-02-10
43	43	6	2	2012	2012-02-12
44	44	7	2	2012	2012-02-13
45	45	7	2	2012	2012-02-14
46	46	7	2	2012	2012-02-15
47	47	7	2	2012	2012-02-16
48	48	7	2	2012	2012-02-17
49	49	7	2	2012	2012-02-18
50	50	7	2	2012	2012-02-19
51	51	8	2	2012	2012-02-20
52	52	8	2	2012	2012-02-21
54	54	8	2	2012	2012-02-23
55	55	8	2	2012	2012-02-24
56	56	8	2	2012	2012-02-25
57	57	8	2	2012	2012-02-26
58	58	9	2	2012	2012-02-27
59	59	9	2	2012	2012-02-28
60	60	9	2	2012	2012-02-29
61	61	9	3	2012	2012-03-01
62	62	9	3	2012	2012-03-02
63	63	9	3	2012	2012-03-03
65	65	10	3	2012	2012-03-05
66	66	10	3	2012	2012-03-06
67	67	10	3	2012	2012-03-07
68	68	10	3	2012	2012-03-08
69	69	10	3	2012	2012-03-09
70	70	10	3	2012	2012-03-10
71	71	10	3	2012	2012-03-11
72	72	11	3	2012	2012-03-12
73	73	11	3	2012	2012-03-13
74	74	11	3	2012	2012-03-14
76	76	11	3	2012	2012-03-16
77	77	11	3	2012	2012-03-17
78	78	11	3	2012	2012-03-18
79	79	12	3	2012	2012-03-19
80	80	12	3	2012	2012-03-20
81	81	12	3	2012	2012-03-21
82	82	12	3	2012	2012-03-22
83	83	12	3	2012	2012-03-23
84	84	12	3	2012	2012-03-24
85	85	12	3	2012	2012-03-25
87	87	13	3	2012	2012-03-27
88	88	13	3	2012	2012-03-28
89	89	13	3	2012	2012-03-29
90	90	13	3	2012	2012-03-30
91	91	13	3	2012	2012-03-31
92	92	13	4	2012	2012-04-01
93	93	14	4	2012	2012-04-02
94	94	14	4	2012	2012-04-03
95	95	14	4	2012	2012-04-04
96	96	14	4	2012	2012-04-05
98	98	14	4	2012	2012-04-07
99	99	14	4	2012	2012-04-08
100	100	15	4	2012	2012-04-09
101	101	15	4	2012	2012-04-10
102	102	15	4	2012	2012-04-11
103	103	15	4	2012	2012-04-12
104	104	15	4	2012	2012-04-13
105	105	15	4	2012	2012-04-14
106	106	15	4	2012	2012-04-15
107	107	16	4	2012	2012-04-16
109	109	16	4	2012	2012-04-18
110	110	16	4	2012	2012-04-19
111	111	16	4	2012	2012-04-20
112	112	16	4	2012	2012-04-21
113	113	16	4	2012	2012-04-22
114	114	17	4	2012	2012-04-23
115	115	17	4	2012	2012-04-24
116	116	17	4	2012	2012-04-25
117	117	17	4	2012	2012-04-26
187	187	27	7	2012	2012-07-05
188	188	27	7	2012	2012-07-06
189	189	27	7	2012	2012-07-07
190	190	27	7	2012	2012-07-08
191	191	28	7	2012	2012-07-09
192	192	28	7	2012	2012-07-10
193	193	28	7	2012	2012-07-11
195	195	28	7	2012	2012-07-13
196	196	28	7	2012	2012-07-14
197	197	28	7	2012	2012-07-15
198	198	29	7	2012	2012-07-16
199	199	29	7	2012	2012-07-17
200	200	29	7	2012	2012-07-18
201	201	29	7	2012	2012-07-19
202	202	29	7	2012	2012-07-20
203	203	29	7	2012	2012-07-21
204	204	29	7	2012	2012-07-22
206	206	30	7	2012	2012-07-24
207	207	30	7	2012	2012-07-25
208	208	30	7	2012	2012-07-26
209	209	30	7	2012	2012-07-27
210	210	30	7	2012	2012-07-28
211	211	30	7	2012	2012-07-29
212	212	31	7	2012	2012-07-30
213	213	31	7	2012	2012-07-31
214	214	31	8	2012	2012-08-01
215	215	31	8	2012	2012-08-02
217	217	31	8	2012	2012-08-04
218	218	31	8	2012	2012-08-05
219	219	32	8	2012	2012-08-06
220	220	32	8	2012	2012-08-07
221	221	32	8	2012	2012-08-08
222	222	32	8	2012	2012-08-09
223	223	32	8	2012	2012-08-10
224	224	32	8	2012	2012-08-11
225	225	32	8	2012	2012-08-12
226	226	33	8	2012	2012-08-13
228	228	33	8	2012	2012-08-15
229	229	33	8	2012	2012-08-16
230	230	33	8	2012	2012-08-17
231	231	33	8	2012	2012-08-18
232	232	33	8	2012	2012-08-19
233	233	34	8	2012	2012-08-20
234	234	34	8	2012	2012-08-21
235	235	34	8	2012	2012-08-22
236	236	34	8	2012	2012-08-23
237	237	34	8	2012	2012-08-24
239	239	34	8	2012	2012-08-26
240	240	35	8	2012	2012-08-27
241	241	35	8	2012	2012-08-28
242	242	35	8	2012	2012-08-29
243	243	35	8	2012	2012-08-30
244	244	35	8	2012	2012-08-31
245	245	35	9	2012	2012-09-01
246	246	35	9	2012	2012-09-02
247	247	36	9	2012	2012-09-03
248	248	36	9	2012	2012-09-04
250	250	36	9	2012	2012-09-06
251	251	36	9	2012	2012-09-07
252	252	36	9	2012	2012-09-08
253	253	36	9	2012	2012-09-09
254	254	37	9	2012	2012-09-10
255	255	37	9	2012	2012-09-11
256	256	37	9	2012	2012-09-12
257	257	37	9	2012	2012-09-13
258	258	37	9	2012	2012-09-14
259	259	37	9	2012	2012-09-15
261	261	38	9	2012	2012-09-17
262	262	38	9	2012	2012-09-18
263	263	38	9	2012	2012-09-19
264	264	38	9	2012	2012-09-20
265	265	38	9	2012	2012-09-21
266	266	38	9	2012	2012-09-22
267	267	38	9	2012	2012-09-23
268	268	39	9	2012	2012-09-24
269	269	39	9	2012	2012-09-25
270	270	39	9	2012	2012-09-26
272	272	39	9	2012	2012-09-28
273	273	39	9	2012	2012-09-29
274	274	39	9	2012	2012-09-30
275	275	40	10	2012	2012-10-01
276	276	40	10	2012	2012-10-02
277	277	40	10	2012	2012-10-03
278	278	40	10	2012	2012-10-04
279	279	40	10	2012	2012-10-05
280	280	40	10	2012	2012-10-06
281	281	40	10	2012	2012-10-07
283	283	41	10	2012	2012-10-09
284	284	41	10	2012	2012-10-10
285	285	41	10	2012	2012-10-11
286	286	41	10	2012	2012-10-12
287	287	41	10	2012	2012-10-13
288	288	41	10	2012	2012-10-14
289	289	42	10	2012	2012-10-15
290	290	42	10	2012	2012-10-16
291	291	42	10	2012	2012-10-17
292	292	42	10	2012	2012-10-18
294	294	42	10	2012	2012-10-20
295	295	42	10	2012	2012-10-21
296	296	43	10	2012	2012-10-22
297	297	43	10	2012	2012-10-23
298	298	43	10	2012	2012-10-24
299	299	43	10	2012	2012-10-25
300	300	43	10	2012	2012-10-26
301	301	43	10	2012	2012-10-27
302	302	43	10	2012	2012-10-28
372	6	1	1	2013	2013-01-06
373	7	2	1	2013	2013-01-07
374	8	2	1	2013	2013-01-08
375	9	2	1	2013	2013-01-09
376	10	2	1	2013	2013-01-10
377	11	2	1	2013	2013-01-11
378	12	2	1	2013	2013-01-12
380	14	3	1	2013	2013-01-14
381	15	3	1	2013	2013-01-15
382	16	3	1	2013	2013-01-16
383	17	3	1	2013	2013-01-17
384	18	3	1	2013	2013-01-18
385	19	3	1	2013	2013-01-19
386	20	3	1	2013	2013-01-20
387	21	4	1	2013	2013-01-21
388	22	4	1	2013	2013-01-22
389	23	4	1	2013	2013-01-23
391	25	4	1	2013	2013-01-25
392	26	4	1	2013	2013-01-26
393	27	4	1	2013	2013-01-27
394	28	5	1	2013	2013-01-28
395	29	5	1	2013	2013-01-29
396	30	5	1	2013	2013-01-30
397	31	5	1	2013	2013-01-31
398	32	5	2	2013	2013-02-01
399	33	5	2	2013	2013-02-02
400	34	5	2	2013	2013-02-03
402	36	6	2	2013	2013-02-05
403	37	6	2	2013	2013-02-06
404	38	6	2	2013	2013-02-07
405	39	6	2	2013	2013-02-08
406	40	6	2	2013	2013-02-09
407	41	6	2	2013	2013-02-10
408	42	7	2	2013	2013-02-11
409	43	7	2	2013	2013-02-12
410	44	7	2	2013	2013-02-13
411	45	7	2	2013	2013-02-14
413	47	7	2	2013	2013-02-16
414	48	7	2	2013	2013-02-17
415	49	8	2	2013	2013-02-18
416	50	8	2	2013	2013-02-19
417	51	8	2	2013	2013-02-20
418	52	8	2	2013	2013-02-21
419	53	8	2	2013	2013-02-22
420	54	8	2	2013	2013-02-23
421	55	8	2	2013	2013-02-24
422	56	9	2	2013	2013-02-25
424	58	9	2	2013	2013-02-27
425	59	9	2	2013	2013-02-28
426	60	9	3	2013	2013-03-01
427	61	9	3	2013	2013-03-02
428	62	9	3	2013	2013-03-03
429	63	10	3	2013	2013-03-04
430	64	10	3	2013	2013-03-05
431	65	10	3	2013	2013-03-06
432	66	10	3	2013	2013-03-07
433	67	10	3	2013	2013-03-08
435	69	10	3	2013	2013-03-10
436	70	11	3	2013	2013-03-11
437	71	11	3	2013	2013-03-12
438	72	11	3	2013	2013-03-13
439	73	11	3	2013	2013-03-14
440	74	11	3	2013	2013-03-15
441	75	11	3	2013	2013-03-16
442	76	11	3	2013	2013-03-17
443	77	12	3	2013	2013-03-18
444	78	12	3	2013	2013-03-19
446	80	12	3	2013	2013-03-21
447	81	12	3	2013	2013-03-22
448	82	12	3	2013	2013-03-23
449	83	12	3	2013	2013-03-24
450	84	13	3	2013	2013-03-25
451	85	13	3	2013	2013-03-26
452	86	13	3	2013	2013-03-27
453	87	13	3	2013	2013-03-28
454	88	13	3	2013	2013-03-29
455	89	13	3	2013	2013-03-30
457	91	14	4	2013	2013-04-01
458	92	14	4	2013	2013-04-02
459	93	14	4	2013	2013-04-03
460	94	14	4	2013	2013-04-04
461	95	14	4	2013	2013-04-05
462	96	14	4	2013	2013-04-06
463	97	14	4	2013	2013-04-07
464	98	15	4	2013	2013-04-08
465	99	15	4	2013	2013-04-09
466	100	15	4	2013	2013-04-10
468	102	15	4	2013	2013-04-12
469	103	15	4	2013	2013-04-13
470	104	15	4	2013	2013-04-14
471	105	16	4	2013	2013-04-15
472	106	16	4	2013	2013-04-16
473	107	16	4	2013	2013-04-17
474	108	16	4	2013	2013-04-18
475	109	16	4	2013	2013-04-19
476	110	16	4	2013	2013-04-20
477	111	16	4	2013	2013-04-21
479	113	17	4	2013	2013-04-23
480	114	17	4	2013	2013-04-24
481	115	17	4	2013	2013-04-25
482	116	17	4	2013	2013-04-26
483	117	17	4	2013	2013-04-27
484	118	17	4	2013	2013-04-28
485	119	18	4	2013	2013-04-29
486	120	18	4	2013	2013-04-30
487	121	18	5	2013	2013-05-01
1	1	52	1	2012	2012-01-01
9	9	2	1	2012	2012-01-09
20	20	3	1	2012	2012-01-20
31	31	5	1	2012	2012-01-31
42	42	6	2	2012	2012-02-11
53	53	8	2	2012	2012-02-22
64	64	9	3	2012	2012-03-04
75	75	11	3	2012	2012-03-15
557	191	28	7	2013	2013-07-10
558	192	28	7	2013	2013-07-11
559	193	28	7	2013	2013-07-12
560	194	28	7	2013	2013-07-13
561	195	28	7	2013	2013-07-14
562	196	29	7	2013	2013-07-15
563	197	29	7	2013	2013-07-16
565	199	29	7	2013	2013-07-18
566	200	29	7	2013	2013-07-19
567	201	29	7	2013	2013-07-20
568	202	29	7	2013	2013-07-21
569	203	30	7	2013	2013-07-22
570	204	30	7	2013	2013-07-23
571	205	30	7	2013	2013-07-24
572	206	30	7	2013	2013-07-25
573	207	30	7	2013	2013-07-26
574	208	30	7	2013	2013-07-27
576	210	31	7	2013	2013-07-29
577	211	31	7	2013	2013-07-30
578	212	31	7	2013	2013-07-31
579	213	31	8	2013	2013-08-01
580	214	31	8	2013	2013-08-02
581	215	31	8	2013	2013-08-03
582	216	31	8	2013	2013-08-04
583	217	32	8	2013	2013-08-05
584	218	32	8	2013	2013-08-06
585	219	32	8	2013	2013-08-07
587	221	32	8	2013	2013-08-09
588	222	32	8	2013	2013-08-10
589	223	32	8	2013	2013-08-11
590	224	33	8	2013	2013-08-12
591	225	33	8	2013	2013-08-13
592	226	33	8	2013	2013-08-14
593	227	33	8	2013	2013-08-15
594	228	33	8	2013	2013-08-16
595	229	33	8	2013	2013-08-17
596	230	33	8	2013	2013-08-18
598	232	34	8	2013	2013-08-20
599	233	34	8	2013	2013-08-21
600	234	34	8	2013	2013-08-22
601	235	34	8	2013	2013-08-23
602	236	34	8	2013	2013-08-24
603	237	34	8	2013	2013-08-25
604	238	35	8	2013	2013-08-26
605	239	35	8	2013	2013-08-27
606	240	35	8	2013	2013-08-28
607	241	35	8	2013	2013-08-29
609	243	35	8	2013	2013-08-31
610	244	35	9	2013	2013-09-01
611	245	36	9	2013	2013-09-02
612	246	36	9	2013	2013-09-03
613	247	36	9	2013	2013-09-04
614	248	36	9	2013	2013-09-05
615	249	36	9	2013	2013-09-06
616	250	36	9	2013	2013-09-07
617	251	36	9	2013	2013-09-08
618	252	37	9	2013	2013-09-09
620	254	37	9	2013	2013-09-11
621	255	37	9	2013	2013-09-12
622	256	37	9	2013	2013-09-13
623	257	37	9	2013	2013-09-14
624	258	37	9	2013	2013-09-15
625	259	38	9	2013	2013-09-16
626	260	38	9	2013	2013-09-17
627	261	38	9	2013	2013-09-18
628	262	38	9	2013	2013-09-19
629	263	38	9	2013	2013-09-20
631	265	38	9	2013	2013-09-22
632	266	39	9	2013	2013-09-23
633	267	39	9	2013	2013-09-24
634	268	39	9	2013	2013-09-25
635	269	39	9	2013	2013-09-26
636	270	39	9	2013	2013-09-27
637	271	39	9	2013	2013-09-28
638	272	39	9	2013	2013-09-29
639	273	40	9	2013	2013-09-30
640	274	40	10	2013	2013-10-01
642	276	40	10	2013	2013-10-03
643	277	40	10	2013	2013-10-04
644	278	40	10	2013	2013-10-05
645	279	40	10	2013	2013-10-06
646	280	41	10	2013	2013-10-07
647	281	41	10	2013	2013-10-08
648	282	41	10	2013	2013-10-09
649	283	41	10	2013	2013-10-10
650	284	41	10	2013	2013-10-11
651	285	41	10	2013	2013-10-12
653	287	42	10	2013	2013-10-14
654	288	42	10	2013	2013-10-15
655	289	42	10	2013	2013-10-16
656	290	42	10	2013	2013-10-17
657	291	42	10	2013	2013-10-18
658	292	42	10	2013	2013-10-19
659	293	42	10	2013	2013-10-20
660	294	43	10	2013	2013-10-21
661	295	43	10	2013	2013-10-22
662	296	43	10	2013	2013-10-23
664	298	43	10	2013	2013-10-25
665	299	43	10	2013	2013-10-26
666	300	43	10	2013	2013-10-27
667	301	44	10	2013	2013-10-28
668	302	44	10	2013	2013-10-29
669	303	44	10	2013	2013-10-30
670	304	44	10	2013	2013-10-31
671	305	44	11	2013	2013-11-01
672	306	44	11	2013	2013-11-02
86	86	13	3	2012	2012-03-26
97	97	14	4	2012	2012-04-06
108	108	16	4	2012	2012-04-17
118	118	17	4	2012	2012-04-27
119	119	17	4	2012	2012-04-28
120	120	17	4	2012	2012-04-29
121	121	18	4	2012	2012-04-30
122	122	18	5	2012	2012-05-01
123	123	18	5	2012	2012-05-02
124	124	18	5	2012	2012-05-03
125	125	18	5	2012	2012-05-04
126	126	18	5	2012	2012-05-05
127	127	18	5	2012	2012-05-06
128	128	19	5	2012	2012-05-07
129	129	19	5	2012	2012-05-08
130	130	19	5	2012	2012-05-09
131	131	19	5	2012	2012-05-10
132	132	19	5	2012	2012-05-11
133	133	19	5	2012	2012-05-12
134	134	19	5	2012	2012-05-13
135	135	20	5	2012	2012-05-14
136	136	20	5	2012	2012-05-15
137	137	20	5	2012	2012-05-16
138	138	20	5	2012	2012-05-17
139	139	20	5	2012	2012-05-18
140	140	20	5	2012	2012-05-19
141	141	20	5	2012	2012-05-20
142	142	21	5	2012	2012-05-21
143	143	21	5	2012	2012-05-22
144	144	21	5	2012	2012-05-23
145	145	21	5	2012	2012-05-24
146	146	21	5	2012	2012-05-25
147	147	21	5	2012	2012-05-26
148	148	21	5	2012	2012-05-27
149	149	22	5	2012	2012-05-28
150	150	22	5	2012	2012-05-29
151	151	22	5	2012	2012-05-30
152	152	22	5	2012	2012-05-31
153	153	22	6	2012	2012-06-01
154	154	22	6	2012	2012-06-02
155	155	22	6	2012	2012-06-03
156	156	23	6	2012	2012-06-04
157	157	23	6	2012	2012-06-05
158	158	23	6	2012	2012-06-06
159	159	23	6	2012	2012-06-07
160	160	23	6	2012	2012-06-08
161	161	23	6	2012	2012-06-09
162	162	23	6	2012	2012-06-10
163	163	24	6	2012	2012-06-11
164	164	24	6	2012	2012-06-12
165	165	24	6	2012	2012-06-13
166	166	24	6	2012	2012-06-14
167	167	24	6	2012	2012-06-15
168	168	24	6	2012	2012-06-16
169	169	24	6	2012	2012-06-17
227	227	33	8	2012	2012-08-14
238	238	34	8	2012	2012-08-25
249	249	36	9	2012	2012-09-05
260	260	37	9	2012	2012-09-16
271	271	39	9	2012	2012-09-27
282	282	41	10	2012	2012-10-08
293	293	42	10	2012	2012-10-19
303	303	44	10	2012	2012-10-29
304	304	44	10	2012	2012-10-30
305	305	44	10	2012	2012-10-31
306	306	44	11	2012	2012-11-01
307	307	44	11	2012	2012-11-02
308	308	44	11	2012	2012-11-03
309	309	44	11	2012	2012-11-04
310	310	45	11	2012	2012-11-05
311	311	45	11	2012	2012-11-06
312	312	45	11	2012	2012-11-07
313	313	45	11	2012	2012-11-08
314	314	45	11	2012	2012-11-09
315	315	45	11	2012	2012-11-10
316	316	45	11	2012	2012-11-11
317	317	46	11	2012	2012-11-12
318	318	46	11	2012	2012-11-13
319	319	46	11	2012	2012-11-14
320	320	46	11	2012	2012-11-15
321	321	46	11	2012	2012-11-16
322	322	46	11	2012	2012-11-17
323	323	46	11	2012	2012-11-18
324	324	47	11	2012	2012-11-19
325	325	47	11	2012	2012-11-20
326	326	47	11	2012	2012-11-21
327	327	47	11	2012	2012-11-22
328	328	47	11	2012	2012-11-23
329	329	47	11	2012	2012-11-24
330	330	47	11	2012	2012-11-25
331	331	48	11	2012	2012-11-26
332	332	48	11	2012	2012-11-27
333	333	48	11	2012	2012-11-28
334	334	48	11	2012	2012-11-29
335	335	48	11	2012	2012-11-30
336	336	48	12	2012	2012-12-01
337	337	48	12	2012	2012-12-02
338	338	49	12	2012	2012-12-03
339	339	49	12	2012	2012-12-04
340	340	49	12	2012	2012-12-05
341	341	49	12	2012	2012-12-06
342	342	49	12	2012	2012-12-07
343	343	49	12	2012	2012-12-08
344	344	49	12	2012	2012-12-09
345	345	50	12	2012	2012-12-10
346	346	50	12	2012	2012-12-11
347	347	50	12	2012	2012-12-12
348	348	50	12	2012	2012-12-13
349	349	50	12	2012	2012-12-14
350	350	50	12	2012	2012-12-15
351	351	50	12	2012	2012-12-16
352	352	51	12	2012	2012-12-17
353	353	51	12	2012	2012-12-18
354	354	51	12	2012	2012-12-19
355	355	51	12	2012	2012-12-20
356	356	51	12	2012	2012-12-21
357	357	51	12	2012	2012-12-22
358	358	51	12	2012	2012-12-23
359	359	52	12	2012	2012-12-24
360	360	52	12	2012	2012-12-25
361	361	52	12	2012	2012-12-26
362	362	52	12	2012	2012-12-27
363	363	52	12	2012	2012-12-28
364	364	52	12	2012	2012-12-29
365	365	52	12	2012	2012-12-30
366	366	1	12	2012	2012-12-31
367	1	1	1	2013	2013-01-01
368	2	1	1	2013	2013-01-02
369	3	1	1	2013	2013-01-03
370	4	1	1	2013	2013-01-04
371	5	1	1	2013	2013-01-05
379	13	2	1	2013	2013-01-13
390	24	4	1	2013	2013-01-24
401	35	6	2	2013	2013-02-04
412	46	7	2	2013	2013-02-15
423	57	9	2	2013	2013-02-26
434	68	10	3	2013	2013-03-09
445	79	12	3	2013	2013-03-20
456	90	13	3	2013	2013-03-31
467	101	15	4	2013	2013-04-11
478	112	17	4	2013	2013-04-22
488	122	18	5	2013	2013-05-02
489	123	18	5	2013	2013-05-03
490	124	18	5	2013	2013-05-04
491	125	18	5	2013	2013-05-05
492	126	19	5	2013	2013-05-06
493	127	19	5	2013	2013-05-07
494	128	19	5	2013	2013-05-08
495	129	19	5	2013	2013-05-09
652	286	41	10	2013	2013-10-13
663	297	43	10	2013	2013-10-24
673	307	44	11	2013	2013-11-03
674	308	45	11	2013	2013-11-04
675	309	45	11	2013	2013-11-05
676	310	45	11	2013	2013-11-06
677	311	45	11	2013	2013-11-07
678	312	45	11	2013	2013-11-08
679	313	45	11	2013	2013-11-09
680	314	45	11	2013	2013-11-10
681	315	46	11	2013	2013-11-11
682	316	46	11	2013	2013-11-12
683	317	46	11	2013	2013-11-13
684	318	46	11	2013	2013-11-14
685	319	46	11	2013	2013-11-15
686	320	46	11	2013	2013-11-16
687	321	46	11	2013	2013-11-17
688	322	47	11	2013	2013-11-18
689	323	47	11	2013	2013-11-19
690	324	47	11	2013	2013-11-20
691	325	47	11	2013	2013-11-21
692	326	47	11	2013	2013-11-22
693	327	47	11	2013	2013-11-23
694	328	47	11	2013	2013-11-24
695	329	48	11	2013	2013-11-25
696	330	48	11	2013	2013-11-26
697	331	48	11	2013	2013-11-27
698	332	48	11	2013	2013-11-28
699	333	48	11	2013	2013-11-29
700	334	48	11	2013	2013-11-30
701	335	48	12	2013	2013-12-01
702	336	49	12	2013	2013-12-02
703	337	49	12	2013	2013-12-03
704	338	49	12	2013	2013-12-04
705	339	49	12	2013	2013-12-05
706	340	49	12	2013	2013-12-06
170	170	25	6	2012	2012-06-18
171	171	25	6	2012	2012-06-19
172	172	25	6	2012	2012-06-20
173	173	25	6	2012	2012-06-21
174	174	25	6	2012	2012-06-22
175	175	25	6	2012	2012-06-23
176	176	25	6	2012	2012-06-24
177	177	26	6	2012	2012-06-25
178	178	26	6	2012	2012-06-26
179	179	26	6	2012	2012-06-27
180	180	26	6	2012	2012-06-28
181	181	26	6	2012	2012-06-29
182	182	26	6	2012	2012-06-30
183	183	26	7	2012	2012-07-01
184	184	27	7	2012	2012-07-02
185	185	27	7	2012	2012-07-03
186	186	27	7	2012	2012-07-04
194	194	28	7	2012	2012-07-12
205	205	30	7	2012	2012-07-23
216	216	31	8	2012	2012-08-03
496	130	19	5	2013	2013-05-10
497	131	19	5	2013	2013-05-11
498	132	19	5	2013	2013-05-12
499	133	20	5	2013	2013-05-13
500	134	20	5	2013	2013-05-14
501	135	20	5	2013	2013-05-15
502	136	20	5	2013	2013-05-16
503	137	20	5	2013	2013-05-17
504	138	20	5	2013	2013-05-18
505	139	20	5	2013	2013-05-19
506	140	21	5	2013	2013-05-20
507	141	21	5	2013	2013-05-21
508	142	21	5	2013	2013-05-22
509	143	21	5	2013	2013-05-23
510	144	21	5	2013	2013-05-24
511	145	21	5	2013	2013-05-25
512	146	21	5	2013	2013-05-26
513	147	22	5	2013	2013-05-27
514	148	22	5	2013	2013-05-28
515	149	22	5	2013	2013-05-29
516	150	22	5	2013	2013-05-30
517	151	22	5	2013	2013-05-31
518	152	22	6	2013	2013-06-01
519	153	22	6	2013	2013-06-02
520	154	23	6	2013	2013-06-03
521	155	23	6	2013	2013-06-04
522	156	23	6	2013	2013-06-05
523	157	23	6	2013	2013-06-06
524	158	23	6	2013	2013-06-07
525	159	23	6	2013	2013-06-08
526	160	23	6	2013	2013-06-09
527	161	24	6	2013	2013-06-10
528	162	24	6	2013	2013-06-11
529	163	24	6	2013	2013-06-12
530	164	24	6	2013	2013-06-13
531	165	24	6	2013	2013-06-14
532	166	24	6	2013	2013-06-15
533	167	24	6	2013	2013-06-16
534	168	25	6	2013	2013-06-17
535	169	25	6	2013	2013-06-18
536	170	25	6	2013	2013-06-19
537	171	25	6	2013	2013-06-20
538	172	25	6	2013	2013-06-21
539	173	25	6	2013	2013-06-22
540	174	25	6	2013	2013-06-23
541	175	26	6	2013	2013-06-24
542	176	26	6	2013	2013-06-25
543	177	26	6	2013	2013-06-26
544	178	26	6	2013	2013-06-27
545	179	26	6	2013	2013-06-28
546	180	26	6	2013	2013-06-29
547	181	26	6	2013	2013-06-30
548	182	27	7	2013	2013-07-01
549	183	27	7	2013	2013-07-02
550	184	27	7	2013	2013-07-03
551	185	27	7	2013	2013-07-04
552	186	27	7	2013	2013-07-05
553	187	27	7	2013	2013-07-06
554	188	27	7	2013	2013-07-07
555	189	28	7	2013	2013-07-08
556	190	28	7	2013	2013-07-09
564	198	29	7	2013	2013-07-17
575	209	30	7	2013	2013-07-28
586	220	32	8	2013	2013-08-08
597	231	34	8	2013	2013-08-19
608	242	35	8	2013	2013-08-30
619	253	37	9	2013	2013-09-10
630	264	38	9	2013	2013-09-21
641	275	40	10	2013	2013-10-02
707	341	49	12	2013	2013-12-07
708	342	49	12	2013	2013-12-08
709	343	50	12	2013	2013-12-09
710	344	50	12	2013	2013-12-10
711	345	50	12	2013	2013-12-11
712	346	50	12	2013	2013-12-12
713	347	50	12	2013	2013-12-13
714	348	50	12	2013	2013-12-14
715	349	50	12	2013	2013-12-15
716	350	51	12	2013	2013-12-16
717	351	51	12	2013	2013-12-17
718	352	51	12	2013	2013-12-18
719	353	51	12	2013	2013-12-19
720	354	51	12	2013	2013-12-20
721	355	51	12	2013	2013-12-21
722	356	51	12	2013	2013-12-22
723	357	52	12	2013	2013-12-23
724	358	52	12	2013	2013-12-24
725	359	52	12	2013	2013-12-25
726	360	52	12	2013	2013-12-26
727	361	52	12	2013	2013-12-27
728	362	52	12	2013	2013-12-28
729	363	52	12	2013	2013-12-29
730	364	1	12	2013	2013-12-30
731	365	1	12	2013	2013-12-31
732	1	1	1	2014	2014-01-01
\.


--
-- Name: pk_call_duration_measure; Type: CONSTRAINT; Schema: report; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY call_duration_measure
    ADD CONSTRAINT pk_call_duration_measure PRIMARY KEY (id);


--
-- Name: pk_course_item_dimension; Type: CONSTRAINT; Schema: report; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY course_item_dimension
    ADD CONSTRAINT pk_course_item_dimension PRIMARY KEY (id);


--
-- Name: pk_course_item_measure; Type: CONSTRAINT; Schema: report; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY course_item_measure
    ADD CONSTRAINT pk_course_item_measure PRIMARY KEY (id);


--
-- Name: pk_databasechangelog; Type: CONSTRAINT; Schema: report; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY databasechangelog
    ADD CONSTRAINT pk_databasechangelog PRIMARY KEY (id, author, filename);


--
-- Name: pk_databasechangeloglock; Type: CONSTRAINT; Schema: report; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY databasechangeloglock
    ADD CONSTRAINT pk_databasechangeloglock PRIMARY KEY (id);


--
-- Name: pk_front_line_worker_dimension; Type: CONSTRAINT; Schema: report; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY front_line_worker_dimension
    ADD CONSTRAINT pk_front_line_worker_dimension PRIMARY KEY (id);


--
-- Name: pk_job_aid_content_dimension; Type: CONSTRAINT; Schema: report; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY job_aid_content_dimension
    ADD CONSTRAINT pk_job_aid_content_dimension PRIMARY KEY (id);


--
-- Name: pk_job_aid_content_measure; Type: CONSTRAINT; Schema: report; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY job_aid_content_measure
    ADD CONSTRAINT pk_job_aid_content_measure PRIMARY KEY (id);


--
-- Name: pk_location_dimension; Type: CONSTRAINT; Schema: report; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY location_dimension
    ADD CONSTRAINT pk_location_dimension PRIMARY KEY (id);


--
-- Name: pk_registration_measure; Type: CONSTRAINT; Schema: report; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY registration_measure
    ADD CONSTRAINT pk_registration_measure PRIMARY KEY (id);


--
-- Name: pk_sms_sent_measure; Type: CONSTRAINT; Schema: report; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY sms_sent_measure
    ADD CONSTRAINT pk_sms_sent_measure PRIMARY KEY (id);


--
-- Name: pk_time_dimension; Type: CONSTRAINT; Schema: report; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY time_dimension
    ADD CONSTRAINT pk_time_dimension PRIMARY KEY (id);


--
-- Name: unq_day_week_month_year; Type: CONSTRAINT; Schema: report; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY time_dimension
    ADD CONSTRAINT unq_day_week_month_year UNIQUE (day, week, month, year);


--
-- Name: unq_name_type; Type: CONSTRAINT; Schema: report; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY course_item_dimension
    ADD CONSTRAINT unq_name_type UNIQUE (name, type);


--
-- Name: idx_call_duration_measure_flw_id; Type: INDEX; Schema: report; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_call_duration_measure_flw_id ON call_duration_measure USING btree (flw_id);


--
-- Name: idx_course_item_dimension_content_id; Type: INDEX; Schema: report; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_course_item_dimension_content_id ON course_item_dimension USING btree (content_id);


--
-- Name: idx_course_item_dimension_name_type; Type: INDEX; Schema: report; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_course_item_dimension_name_type ON course_item_dimension USING btree (name, type);


--
-- Name: idx_course_item_measure_course_item_id; Type: INDEX; Schema: report; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_course_item_measure_course_item_id ON course_item_measure USING btree (course_item_id);


--
-- Name: idx_course_item_measure_flw_id; Type: INDEX; Schema: report; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_course_item_measure_flw_id ON course_item_measure USING btree (flw_id);


--
-- Name: idx_course_item_measure_time_id; Type: INDEX; Schema: report; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_course_item_measure_time_id ON course_item_measure USING btree (time_id);


--
-- Name: idx_front_line_worker_dimension_msisdn; Type: INDEX; Schema: report; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_front_line_worker_dimension_msisdn ON front_line_worker_dimension USING btree (msisdn);


--
-- Name: idx_job_aid_content_dimension_content_id; Type: INDEX; Schema: report; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_job_aid_content_dimension_content_id ON job_aid_content_dimension USING btree (content_id);


--
-- Name: idx_location_dimension_location_id; Type: INDEX; Schema: report; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_location_dimension_location_id ON location_dimension USING btree (location_id);


--
-- Name: idx_registration_measure_flw_id; Type: INDEX; Schema: report; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_registration_measure_flw_id ON registration_measure USING btree (flw_id);


--
-- Name: idx_registration_measure_location_id; Type: INDEX; Schema: report; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_registration_measure_location_id ON registration_measure USING btree (location_id);


--
-- Name: idx_registration_measure_time_id; Type: INDEX; Schema: report; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_registration_measure_time_id ON registration_measure USING btree (time_id);


--
-- Name: idx_sms_sent_measure_flw_id; Type: INDEX; Schema: report; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_sms_sent_measure_flw_id ON sms_sent_measure USING btree (flw_id);


--
-- Name: idx_sms_sent_measure_time_id; Type: INDEX; Schema: report; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_sms_sent_measure_time_id ON sms_sent_measure USING btree (time_id);


--
-- Name: idx_time_dimension_year_month_day; Type: INDEX; Schema: report; Owner: postgres; Tablespace: 
--

CREATE INDEX idx_time_dimension_year_month_day ON time_dimension USING btree (year, month, day);


--
-- Name: fk_call_duration_measure_flw_dimension; Type: FK CONSTRAINT; Schema: report; Owner: postgres
--

ALTER TABLE ONLY call_duration_measure
    ADD CONSTRAINT fk_call_duration_measure_flw_dimension FOREIGN KEY (flw_id) REFERENCES front_line_worker_dimension(id) ON DELETE CASCADE;


--
-- Name: fk_call_duration_measure_location_dimension; Type: FK CONSTRAINT; Schema: report; Owner: postgres
--

ALTER TABLE ONLY call_duration_measure
    ADD CONSTRAINT fk_call_duration_measure_location_dimension FOREIGN KEY (location_id) REFERENCES location_dimension(id) ON DELETE CASCADE;


--
-- Name: fk_call_duration_measure_time_dimension; Type: FK CONSTRAINT; Schema: report; Owner: postgres
--

ALTER TABLE ONLY call_duration_measure
    ADD CONSTRAINT fk_call_duration_measure_time_dimension FOREIGN KEY (time_id) REFERENCES time_dimension(id) ON DELETE CASCADE;


--
-- Name: fk_course_item_dimension_course_item_dimension; Type: FK CONSTRAINT; Schema: report; Owner: postgres
--

ALTER TABLE ONLY course_item_dimension
    ADD CONSTRAINT fk_course_item_dimension_course_item_dimension FOREIGN KEY (parent_id) REFERENCES course_item_dimension(id) ON DELETE CASCADE;


--
-- Name: fk_course_item_measure_course_item_dimension; Type: FK CONSTRAINT; Schema: report; Owner: postgres
--

ALTER TABLE ONLY course_item_measure
    ADD CONSTRAINT fk_course_item_measure_course_item_dimension FOREIGN KEY (course_item_id) REFERENCES course_item_dimension(id) ON DELETE CASCADE;


--
-- Name: fk_course_item_measure_flw_dimension; Type: FK CONSTRAINT; Schema: report; Owner: postgres
--

ALTER TABLE ONLY course_item_measure
    ADD CONSTRAINT fk_course_item_measure_flw_dimension FOREIGN KEY (flw_id) REFERENCES front_line_worker_dimension(id) ON DELETE CASCADE;


--
-- Name: fk_course_item_measure_location_dimension; Type: FK CONSTRAINT; Schema: report; Owner: postgres
--

ALTER TABLE ONLY course_item_measure
    ADD CONSTRAINT fk_course_item_measure_location_dimension FOREIGN KEY (location_id) REFERENCES location_dimension(id) ON DELETE CASCADE;


--
-- Name: fk_course_item_measure_time_dimension; Type: FK CONSTRAINT; Schema: report; Owner: postgres
--

ALTER TABLE ONLY course_item_measure
    ADD CONSTRAINT fk_course_item_measure_time_dimension FOREIGN KEY (time_id) REFERENCES time_dimension(id) ON DELETE CASCADE;


--
-- Name: fk_ja_content_measure_ja_content_dimension; Type: FK CONSTRAINT; Schema: report; Owner: postgres
--

ALTER TABLE ONLY job_aid_content_measure
    ADD CONSTRAINT fk_ja_content_measure_ja_content_dimension FOREIGN KEY (job_aid_content_id) REFERENCES job_aid_content_dimension(id) ON DELETE CASCADE;


--
-- Name: fk_job_aid_content_dimension_job_aid_content_dimension; Type: FK CONSTRAINT; Schema: report; Owner: postgres
--

ALTER TABLE ONLY job_aid_content_dimension
    ADD CONSTRAINT fk_job_aid_content_dimension_job_aid_content_dimension FOREIGN KEY (parent_id) REFERENCES job_aid_content_dimension(id) ON DELETE CASCADE;


--
-- Name: fk_job_aid_content_measure_flw_dimension; Type: FK CONSTRAINT; Schema: report; Owner: postgres
--

ALTER TABLE ONLY job_aid_content_measure
    ADD CONSTRAINT fk_job_aid_content_measure_flw_dimension FOREIGN KEY (flw_id) REFERENCES front_line_worker_dimension(id) ON DELETE CASCADE;


--
-- Name: fk_job_aid_content_measure_location_dimension; Type: FK CONSTRAINT; Schema: report; Owner: postgres
--

ALTER TABLE ONLY job_aid_content_measure
    ADD CONSTRAINT fk_job_aid_content_measure_location_dimension FOREIGN KEY (location_id) REFERENCES location_dimension(id) ON DELETE CASCADE;


--
-- Name: fk_job_aid_content_measure_time_dimension; Type: FK CONSTRAINT; Schema: report; Owner: postgres
--

ALTER TABLE ONLY job_aid_content_measure
    ADD CONSTRAINT fk_job_aid_content_measure_time_dimension FOREIGN KEY (time_id) REFERENCES time_dimension(id) ON DELETE CASCADE;


--
-- Name: fk_registration_measure_flw_dimension; Type: FK CONSTRAINT; Schema: report; Owner: postgres
--

ALTER TABLE ONLY registration_measure
    ADD CONSTRAINT fk_registration_measure_flw_dimension FOREIGN KEY (flw_id) REFERENCES front_line_worker_dimension(id) ON DELETE CASCADE;


--
-- Name: fk_registration_measure_location_dimension; Type: FK CONSTRAINT; Schema: report; Owner: postgres
--

ALTER TABLE ONLY registration_measure
    ADD CONSTRAINT fk_registration_measure_location_dimension FOREIGN KEY (location_id) REFERENCES location_dimension(id) ON DELETE CASCADE;


--
-- Name: fk_registration_measure_time_dimension; Type: FK CONSTRAINT; Schema: report; Owner: postgres
--

ALTER TABLE ONLY registration_measure
    ADD CONSTRAINT fk_registration_measure_time_dimension FOREIGN KEY (time_id) REFERENCES time_dimension(id) ON DELETE CASCADE;


--
-- Name: fk_sms_sent_measure_flw_dimension; Type: FK CONSTRAINT; Schema: report; Owner: postgres
--

ALTER TABLE ONLY sms_sent_measure
    ADD CONSTRAINT fk_sms_sent_measure_flw_dimension FOREIGN KEY (flw_id) REFERENCES front_line_worker_dimension(id) ON DELETE CASCADE;


--
-- Name: fk_sms_sent_measure_location_dimension; Type: FK CONSTRAINT; Schema: report; Owner: postgres
--

ALTER TABLE ONLY sms_sent_measure
    ADD CONSTRAINT fk_sms_sent_measure_location_dimension FOREIGN KEY (location_id) REFERENCES location_dimension(id) ON DELETE CASCADE;


--
-- Name: fk_sms_sent_measure_time_dimension; Type: FK CONSTRAINT; Schema: report; Owner: postgres
--

ALTER TABLE ONLY sms_sent_measure
    ADD CONSTRAINT fk_sms_sent_measure_time_dimension FOREIGN KEY (time_id) REFERENCES time_dimension(id) ON DELETE CASCADE;


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

