create table if NOT EXISTS para_note (
    id varchar(36) not null,
    title varchar(255),
    content text,
    constraint pk_para_note primary key (id)
);

comment on table para_note is '노트';
comment on column para_note.id is 'id';
comment on column para_note.title is '제목';
comment on column para_note.content is '노트 내용';

create table if not exists para_project (
    project_id varchar(36) not null,
    title varchar(255),
    content text,
    start_date timestamp,
    end_date timestamp,
    status varchar(10), /* backlog, ready, inprogress, done */
    constraint pk_para_project primary key (project_id)
);

comment on table para_project is 'para Project';
comment on column para_project.project_id is 'id';
comment on column para_project.title is '프로젝트명';
comment on column para_project.content is '프로젝트 내용';
comment on column para_project.start_date is '시작일';
comment on column para_project.end_date is '종료일';
comment on column para_project.status is '진행항태. backlog,ready, inprogress, done';