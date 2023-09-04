--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4
-- Dumped by pg_dump version 15.3

-- Started on 2023-09-04 07:30:14


-- Database: clever-bank

CREATE DATABASE "clever-bank"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;




SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 16401)
-- Name: banks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.banks (
    id integer NOT NULL,
    bankname character varying(50)
);


ALTER TABLE public.banks OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16400)
-- Name: banks_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.banks_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.banks_id_seq OWNER TO postgres;

--
-- TOC entry 3272 (class 0 OID 0)
-- Dependencies: 214
-- Name: banks_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.banks_id_seq OWNED BY public.banks.id;


--
-- TOC entry 219 (class 1259 OID 16415)
-- Name: bills; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bills (
    id integer NOT NULL,
    bankid integer NOT NULL,
    clientid integer NOT NULL,
    currency character varying(30),
    dateopen date,
    dateclose date,
    number character varying(34) NOT NULL,
    balance double precision
);


ALTER TABLE public.bills OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16414)
-- Name: bills_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.bills_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bills_id_seq OWNER TO postgres;

--
-- TOC entry 3273 (class 0 OID 0)
-- Dependencies: 218
-- Name: bills_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.bills_id_seq OWNED BY public.bills.id;


--
-- TOC entry 217 (class 1259 OID 16408)
-- Name: clients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clients (
    id integer NOT NULL,
    firstname character varying(30),
    lastname character varying(30),
    surname character varying(30)
);


ALTER TABLE public.clients OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16407)
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.clients_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.clients_id_seq OWNER TO postgres;

--
-- TOC entry 3274 (class 0 OID 0)
-- Dependencies: 216
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.clients_id_seq OWNED BY public.clients.id;


--
-- TOC entry 221 (class 1259 OID 16434)
-- Name: transactions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transactions (
    id bigint NOT NULL,
    datecurrent timestamp without time zone,
    sendbill character varying(34),
    recipientbill character varying(34),
    typetransaction character varying(30),
    total double precision,
    status boolean
);


ALTER TABLE public.transactions OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16433)
-- Name: transactions_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transactions_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transactions_id_seq OWNER TO postgres;

--
-- TOC entry 3275 (class 0 OID 0)
-- Dependencies: 220
-- Name: transactions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transactions_id_seq OWNED BY public.transactions.id;


--
-- TOC entry 3099 (class 2604 OID 16404)
-- Name: banks id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.banks ALTER COLUMN id SET DEFAULT nextval('public.banks_id_seq'::regclass);


--
-- TOC entry 3101 (class 2604 OID 16418)
-- Name: bills id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bills ALTER COLUMN id SET DEFAULT nextval('public.bills_id_seq'::regclass);


--
-- TOC entry 3100 (class 2604 OID 16411)
-- Name: clients id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients ALTER COLUMN id SET DEFAULT nextval('public.clients_id_seq'::regclass);


--
-- TOC entry 3102 (class 2604 OID 16479)
-- Name: transactions id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions ALTER COLUMN id SET DEFAULT nextval('public.transactions_id_seq'::regclass);


--
-- TOC entry 3260 (class 0 OID 16401)
-- Dependencies: 215
-- Data for Name: banks; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.banks (id, bankname) VALUES (3, 'Clever-Bank');
INSERT INTO public.banks (id, bankname) VALUES (4, 'Priorbank');
INSERT INTO public.banks (id, bankname) VALUES (0, 'ATM');
INSERT INTO public.banks (id, bankname) VALUES (5, 'BelarusBank');
INSERT INTO public.banks (id, bankname) VALUES (6, 'Alfa-Bank');
INSERT INTO public.banks (id, bankname) VALUES (7, 'Idea-Bank');

--
-- TOC entry 3262 (class 0 OID 16408)
-- Dependencies: 217
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.clients (id, firstname, lastname, surname) VALUES (2, 'Сапего', 'Станислав', 'Михайлович');
INSERT INTO public.clients (id, firstname, lastname, surname) VALUES (1, 'Хоровой', 'Кирилл', 'Дмитриевич');
INSERT INTO public.clients (id, firstname, lastname, surname) VALUES (0, '', '', NULL);
INSERT INTO public.clients (id, firstname, lastname, surname) VALUES (3, 'Голицын', 'Станислав', 'Михайлович');
INSERT INTO public.clients (id, firstname, lastname, surname) VALUES (4, 'Крывин', 'Станислав', 'Сергеевич');
INSERT INTO public.clients (id, firstname, lastname, surname) VALUES (5, 'Голицын', 'Леонид', 'Михайлович');
INSERT INTO public.clients (id, firstname, lastname, surname) VALUES (6, 'Ставрик', 'Петр', 'Михайлович');
INSERT INTO public.clients (id, firstname, lastname, surname) VALUES (7, 'Голицын', 'Станислав', 'Вячеславович');
INSERT INTO public.clients (id, firstname, lastname, surname) VALUES (8, 'Голицын', 'Федор', 'Михайлович');
INSERT INTO public.clients (id, firstname, lastname, surname) VALUES (9, 'Старовойтов', 'Станислав', 'Михайлович');
INSERT INTO public.clients (id, firstname, lastname, surname) VALUES (10, 'Голицын', 'Станислав', 'Михайлович');
INSERT INTO public.clients (id, firstname, lastname, surname) VALUES (11, 'Голицын', 'Алексей', 'Степанович');
INSERT INTO public.clients (id, firstname, lastname, surname) VALUES (12, 'Петров', 'Станислав', 'Петрович');
INSERT INTO public.clients (id, firstname, lastname, surname) VALUES (13, 'Голицын', 'Михаил', 'Михайлович');
INSERT INTO public.clients (id, firstname, lastname, surname) VALUES (14, 'Голицын', 'Станислав', 'Михайлович');
INSERT INTO public.clients (id, firstname, lastname, surname) VALUES (15, 'Голицын', 'Василий', 'Владимирович');
INSERT INTO public.clients (id, firstname, lastname, surname) VALUES (16, 'Голицын', 'Станислав', 'Михайлович');
INSERT INTO public.clients (id, firstname, lastname, surname) VALUES (17, 'Голицын', 'Егор', 'Михайлович');
INSERT INTO public.clients (id, firstname, lastname, surname) VALUES (18, 'Ломачев', 'Станислав', 'Михайлович');
INSERT INTO public.clients (id, firstname, lastname, surname) VALUES (19, 'Голицын', 'Александр', 'Кириллович');
INSERT INTO public.clients (id, firstname, lastname, surname) VALUES (20, 'Соболь', 'Станислав', 'Михайлович');
INSERT INTO public.clients (id, firstname, lastname, surname) VALUES (21, 'Голицын', 'Станислав', 'Михайлович');


--
-- TOC entry 3264 (class 0 OID 16415)
-- Dependencies: 219
-- Data for Name: bills; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (0, 0, 0, NULL, NULL, NULL, 'ATM', NULL);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (3, 4, 2, 'BYN', '2022-05-31', NULL, 'BY20 OLMP 3135 0000 0010 0000 0934', 8744);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (5, 4, 2, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0010 0000 0935', 680000);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (6, 4, 3, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0010 0000 0936', 85875);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (7, 3, 4, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0010 0000 0983', 680000);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (8, 4, 5, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0010 0000 0033', 680000);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (9, 5, 6, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0010 0001 0933', 258585);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (10, 4, 7, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0010 0010 0933', 680000);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (11, 4, 8, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0010 0003 0933', 4111111);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (12, 4, 9, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0010 0004 0933', 680000);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (13, 5, 10, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0010 0005 0933', 680000);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (14, 4, 11, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0010 0006 0933', 2485222);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (2, 4, 2, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0010 0000 0933', 680000);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (15, 4, 12, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0010 0007 0933', 4251);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (16, 7, 13, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0010 0008 0933', 680000);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (17, 4, 14, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0010 0009 0933', 680000);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (18, 4, 15, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0010 0055 0933', 680000);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (19, 4, 16, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0010 0033 0933', 680000);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (20, 4, 17, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0010 0225 0933', 680000);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (21, 4, 18, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0010 0088 0933', 680000);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (22, 4, 19, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0010 0077 0933', 680000);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (23, 4, 20, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0010 0550 0933', 680000);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (24, 4, 21, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0010 0220 0933', 680000);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (25, 7, 21, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0010 0330 0933', 680000);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (26, 7, 20, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0010 0990 0933', 680000);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (27, 7, 19, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0011 0000 0933', 545);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (28, 7, 18, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0012 0000 0933', 543512120);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (29, 7, 17, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0013 0000 0933', 680000);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (30, 7, 16, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0014 0000 0933', 55555545);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (31, 7, 15, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0015 0000 0933', 555555);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (32, 4, 14, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0016 0000 0933', 533453453);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (33, 5, 13, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0017 0000 0933', 5433210);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (34, 5, 12, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0018 0000 0933', 354343);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (35, 5, 11, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0019 0000 0933', 3543);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (36, 5, 10, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0000 0022 0000 0933', 24533);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (37, 5, 9, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0033 0010 0000 0933', 5345402);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (38, 4, 8, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0055 0010 0000 0933', 456856);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (39, 4, 7, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0088 0010 0000 0933', 2535430);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (40, 4, 6, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0005 0010 0000 0933', 222222);
INSERT INTO public.bills (id, bankid, clientid, currency, dateopen, dateclose, number, balance) VALUES (41, 4, 5, 'BYN', '2022-08-31', NULL, 'BY20 OLMP 3135 0002 0010 0000 0933', 50000);


--
-- TOC entry 3266 (class 0 OID 16434)
-- Dependencies: 221
-- Data for Name: transactions; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.transactions (id, datecurrent, sendbill, recipientbill, typetransaction, total, status) VALUES (9, '2022-05-30 00:00:00', 'BY20 OLMP 3135 0000 0010 0000 0934', 'BY20 OLMP 3135 0000 0010 0000 0933', 'перевод', 500, true);
INSERT INTO public.transactions (id, datecurrent, sendbill, recipientbill, typetransaction, total, status) VALUES (8, '2022-05-30 00:00:00', 'BY20 OLMP 3135 0000 0010 0000 0934', 'BY20 OLMP 3135 0000 0010 0000 0933', 'перевод', 100, true);
INSERT INTO public.transactions (id, datecurrent, sendbill, recipientbill, typetransaction, total, status) VALUES (7, '2022-05-30 00:00:00', 'BY20 OLMP 3135 0000 0010 0000 0934', 'BY20 OLMP 3135 0000 0010 0000 0933', 'перевод', 100, true);
INSERT INTO public.transactions (id, datecurrent, sendbill, recipientbill, typetransaction, total, status) VALUES (13, '2023-09-02 00:00:00', 'BY20 OLMP 3135 0000 0010 0000 0934', 'ATM', 'снятие', 2000, true);
INSERT INTO public.transactions (id, datecurrent, sendbill, recipientbill, typetransaction, total, status) VALUES (12, '2023-09-02 00:00:00', 'ATM', 'BY20 OLMP 3135 0000 0010 0000 0934', 'пополнение', 1000, true);
INSERT INTO public.transactions (id, datecurrent, sendbill, recipientbill, typetransaction, total, status) VALUES (14, '2023-09-03 14:05:55.825008', 'BY20 OLMP 3135 0000 0010 0000 0934', 'ATM', 'снятие', 2000, true);
INSERT INTO public.transactions (id, datecurrent, sendbill, recipientbill, typetransaction, total, status) VALUES (15, '2023-09-03 14:07:50.851605', 'BY20 OLMP 3135 0000 0010 0000 0934', 'ATM', 'снятие', 2000, true);
INSERT INTO public.transactions (id, datecurrent, sendbill, recipientbill, typetransaction, total, status) VALUES (16, '2023-09-03 14:10:23.716783', 'BY20 OLMP 3135 0000 0010 0000 0934', 'ATM', 'снятие', 2000, true);
INSERT INTO public.transactions (id, datecurrent, sendbill, recipientbill, typetransaction, total, status) VALUES (17, '2023-09-03 14:10:48.72976', 'BY20 OLMP 3135 0000 0010 0000 0934', 'ATM', 'снятие', 2000, true);
INSERT INTO public.transactions (id, datecurrent, sendbill, recipientbill, typetransaction, total, status) VALUES (10, '2022-05-30 00:00:00', 'BY20 OLMP 3135 0000 0010 0000 0934', 'BY20 OLMP 3135 0000 0010 0000 0933', 'перевод', 500, true);
INSERT INTO public.transactions (id, datecurrent, sendbill, recipientbill, typetransaction, total, status) VALUES (300, '2023-09-03 14:46:49.729785', 'BY20 OLMP 3135 0000 0010 0000 0934', 'ATM', 'снятие', 2000, true);
INSERT INTO public.transactions (id, datecurrent, sendbill, recipientbill, typetransaction, total, status) VALUES (301, '2023-09-03 14:55:59.688693', 'BY20 OLMP 3135 0000 0010 0000 0934', 'ATM', 'снятие', 2000, true);
INSERT INTO public.transactions (id, datecurrent, sendbill, recipientbill, typetransaction, total, status) VALUES (302, '2023-09-03 15:21:36.025852', 'ATM', 'BY20 OLMP 3135 0000 0010 0000 0934', 'пополнение', 4000, true);
INSERT INTO public.transactions (id, datecurrent, sendbill, recipientbill, typetransaction, total, status) VALUES (6743454956464262743, '2023-09-03 15:38:12.692209', 'ATM', 'BY20 OLMP 3135 0000 0010 0000 0934', 'пополнение', 4000, true);
INSERT INTO public.transactions (id, datecurrent, sendbill, recipientbill, typetransaction, total, status) VALUES (4041625958617466406, '2023-09-03 15:39:25.54725', 'ATM', 'BY20 OLMP 3135 0000 0010 0000 0934', 'пополнение', 4000, true);
INSERT INTO public.transactions (id, datecurrent, sendbill, recipientbill, typetransaction, total, status) VALUES (5023840498041636419, '2023-09-03 20:33:36.781579', 'BY20 OLMP 3135 0000 0010 0000 0934', 'BY20 OLMP 3135 0000 0010 0000 0933', 'перевод', 5000, true);
INSERT INTO public.transactions (id, datecurrent, sendbill, recipientbill, typetransaction, total, status) VALUES (6027367595241719571, '2023-09-03 20:38:27.744736', 'BY20 OLMP 3135 0000 0010 0000 0934', 'BY20 OLMP 3135 0000 0010 0000 0933', 'перевод', 5000, true);
INSERT INTO public.transactions (id, datecurrent, sendbill, recipientbill, typetransaction, total, status) VALUES (1227080395174985863, '2023-09-03 20:39:12.796497', 'BY20 OLMP 3135 0000 0010 0000 0934', 'BY20 OLMP 3135 0000 0010 0000 0933', 'перевод', 5000, false);


--
-- TOC entry 3276 (class 0 OID 0)
-- Dependencies: 214
-- Name: banks_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.banks_id_seq', 7, true);


--
-- TOC entry 3277 (class 0 OID 0)
-- Dependencies: 218
-- Name: bills_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.bills_id_seq', 4, true);


--
-- TOC entry 3278 (class 0 OID 0)
-- Dependencies: 216
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.clients_id_seq', 2, true);


--
-- TOC entry 3279 (class 0 OID 0)
-- Dependencies: 220
-- Name: transactions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transactions_id_seq', 17, true);


--
-- TOC entry 3104 (class 2606 OID 16406)
-- Name: banks banks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.banks
    ADD CONSTRAINT banks_pkey PRIMARY KEY (id);


--
-- TOC entry 3108 (class 2606 OID 16422)
-- Name: bills bills_accountnumber_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bills
    ADD CONSTRAINT bills_accountnumber_key UNIQUE (number);


--
-- TOC entry 3110 (class 2606 OID 16420)
-- Name: bills bills_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bills
    ADD CONSTRAINT bills_pkey PRIMARY KEY (id);


--
-- TOC entry 3106 (class 2606 OID 16413)
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- TOC entry 3112 (class 2606 OID 16481)
-- Name: transactions transactions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT transactions_pkey PRIMARY KEY (id);


--
-- TOC entry 3113 (class 2606 OID 16423)
-- Name: bills bills_bankid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bills
    ADD CONSTRAINT bills_bankid_fkey FOREIGN KEY (bankid) REFERENCES public.banks(id);


--
-- TOC entry 3114 (class 2606 OID 16428)
-- Name: bills bills_clientid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bills
    ADD CONSTRAINT bills_clientid_fkey FOREIGN KEY (clientid) REFERENCES public.clients(id);


--
-- TOC entry 3115 (class 2606 OID 16445)
-- Name: transactions transactions_recipientbill_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT transactions_recipientbill_fkey FOREIGN KEY (recipientbill) REFERENCES public.bills(number);


--
-- TOC entry 3116 (class 2606 OID 16440)
-- Name: transactions transactions_sendbill_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT transactions_sendbill_fkey FOREIGN KEY (sendbill) REFERENCES public.bills(number);


-- Completed on 2023-09-04 07:30:14

--
-- PostgreSQL database dump complete
--

