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

