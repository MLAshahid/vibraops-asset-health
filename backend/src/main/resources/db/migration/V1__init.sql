-- Initial VibraOps schema

create table device (
  id bigserial primary key,
  name varchar(120) not null,
  status varchar(16) not null default 'OK',
  location varchar(120),
  last_seen timestamptz
);

create table sample (
  id bigserial primary key,
  device_id bigint not null references device(id),
  ts timestamptz not null,
  rms double precision not null,
  peak double precision not null,
  kurtosis double precision not null
);

create table anomaly (
  id bigserial primary key,
  device_id bigint not null references device(id),
  ts timestamptz not null,
  rule varchar(32) not null,
  score double precision not null,
  note varchar(200)
);