/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2012                    */
/* Created on:     2019/11/20 12:46:11                          */
/*==============================================================*/


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('sys_role_resource') and o.name = 'FK_SYS_ROLE_REFERENCE_SYS_RESO')
alter table sys_role_resource
   drop constraint FK_SYS_ROLE_REFERENCE_SYS_RESO
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('sys_role_resource') and o.name = 'FK_SYS_ROLE_REFERENCE_SYS_ROLE')
alter table sys_role_resource
   drop constraint FK_SYS_ROLE_REFERENCE_SYS_ROLE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('sys_user_role') and o.name = 'FK_SYS_USER_REFERENCE_SYS_USER')
alter table sys_user_role
   drop constraint FK_SYS_USER_REFERENCE_SYS_USER
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('sys_user_role') and o.name = 'FK_SYS_USER_REFERENCE_SYS_ROLE')
alter table sys_user_role
   drop constraint FK_SYS_USER_REFERENCE_SYS_ROLE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('oauth_client_details')
            and   type = 'U')
   drop table oauth_client_details
go

if exists (select 1
            from  sysobjects
           where  id = object_id('sys_resources')
            and   type = 'U')
   drop table sys_resources
go

if exists (select 1
            from  sysobjects
           where  id = object_id('sys_role')
            and   type = 'U')
   drop table sys_role
go

if exists (select 1
            from  sysobjects
           where  id = object_id('sys_role_resource')
            and   type = 'U')
   drop table sys_role_resource
go

if exists (select 1
            from  sysobjects
           where  id = object_id('sys_user')
            and   type = 'U')
   drop table sys_user
go

if exists (select 1
            from  sysobjects
           where  id = object_id('sys_user_role')
            and   type = 'U')
   drop table sys_user_role
go

/*==============================================================*/
/* Table: oauth_client_details                                  */
/*==============================================================*/
create table oauth_client_details (
   client_id            varchar(255)         not null,
   resource_ids         varchar(255)         null,
   client_secret        varchar(255)         null,
   scope                varchar(255)         null,
   authorized_grant_types varchar(255)         null,
   web_server_redirect_uri varchar(255)         null,
   authorities          varchar(255)         null,
   access_token_validity varchar(255)         null,
   refresh_token_validity varchar(255)         null,
   additional_information varchar(255)         null,
   create_time          varchar(255)         null,
   archived             varchar(255)         null,
   trusted              varchar(255)         null,
   autoapprove          varchar(255)         null,
   constraint PK_OAUTH_CLIENT_DETAILS primary key (client_id)
)
go

/*==============================================================*/
/* Table: sys_resources                                         */
/*==============================================================*/
create table sys_resources (
   id                   int                  identity,
   name                 varchar(20)          not null,
   url                  varchar(200)         null,
   icon                 varchar(30)          null,
   resource_type        char(1)              null default '0',
   status               char(1)              null default 'Y',
   pid                  varchar(50)          null,
   createtime           datetime             null,
   createby             varchar(50)          null,
   updatetime           datetime             null,
   updateby             varchar(50)          null,
   constraint PK_SYS_RESOURCE primary key nonclustered (id)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('sys_resources') and minor_id = 0)
begin 
   declare @CurrentUser sysname 
select @CurrentUser = user_name() 
execute sp_dropextendedproperty 'MS_Description',  
   'user', @CurrentUser, 'table', 'sys_resources' 
 
end 


select @CurrentUser = user_name() 
execute sp_addextendedproperty 'MS_Description',  
   '系统资源表', 
   'user', @CurrentUser, 'table', 'sys_resources'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('sys_resources')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'id')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'sys_resources', 'column', 'id'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '资源编号',
   'user', @CurrentUser, 'table', 'sys_resources', 'column', 'id'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('sys_resources')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'name')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'sys_resources', 'column', 'name'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '资源名称',
   'user', @CurrentUser, 'table', 'sys_resources', 'column', 'name'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('sys_resources')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'url')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'sys_resources', 'column', 'url'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '资源连接地址',
   'user', @CurrentUser, 'table', 'sys_resources', 'column', 'url'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('sys_resources')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'icon')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'sys_resources', 'column', 'icon'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '资源图标',
   'user', @CurrentUser, 'table', 'sys_resources', 'column', 'icon'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('sys_resources')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'resource_type')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'sys_resources', 'column', 'resource_type'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '资源类型；0:菜单;1：按钮',
   'user', @CurrentUser, 'table', 'sys_resources', 'column', 'resource_type'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('sys_resources')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'status')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'sys_resources', 'column', 'status'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '资源状态；Y:启用;N：禁用',
   'user', @CurrentUser, 'table', 'sys_resources', 'column', 'status'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('sys_resources')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'pid')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'sys_resources', 'column', 'pid'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '父资源编号',
   'user', @CurrentUser, 'table', 'sys_resources', 'column', 'pid'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('sys_resources')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'createtime')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'sys_resources', 'column', 'createtime'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '创建时间',
   'user', @CurrentUser, 'table', 'sys_resources', 'column', 'createtime'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('sys_resources')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'createby')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'sys_resources', 'column', 'createby'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '创建人;jAVA后台写入为-1,存储过程批量处理写入为-2,其它情况为真实的用户ID',
   'user', @CurrentUser, 'table', 'sys_resources', 'column', 'createby'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('sys_resources')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'updatetime')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'sys_resources', 'column', 'updatetime'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '修改时间',
   'user', @CurrentUser, 'table', 'sys_resources', 'column', 'updatetime'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('sys_resources')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'updateby')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'sys_resources', 'column', 'updateby'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '修改人;jAVA后台写入为-1,存储过程批量处理写入为-2,其它情况为真实的用户ID',
   'user', @CurrentUser, 'table', 'sys_resources', 'column', 'updateby'
go

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role (
   id                   int                  identity,
   remark               varchar(255)         null,
   role_name            varchar(50)          null,
   createtime           datetime             null,
   createby             varchar(50)          null,
   updatetime           datetime             null,
   updateby             varchar(50)          null,
   constraint PK_SYS_ROLE primary key nonclustered (id)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('sys_role') and minor_id = 0)
begin 
   declare @CurrentUser sysname 
select @CurrentUser = user_name() 
execute sp_dropextendedproperty 'MS_Description',  
   'user', @CurrentUser, 'table', 'sys_role' 
 
end 


select @CurrentUser = user_name() 
execute sp_addextendedproperty 'MS_Description',  
   '角色表', 
   'user', @CurrentUser, 'table', 'sys_role'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('sys_role')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'createtime')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'sys_role', 'column', 'createtime'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '创建时间',
   'user', @CurrentUser, 'table', 'sys_role', 'column', 'createtime'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('sys_role')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'createby')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'sys_role', 'column', 'createby'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '创建人;jAVA后台写入为-1,存储过程批量处理写入为-2,其它情况为真实的用户ID',
   'user', @CurrentUser, 'table', 'sys_role', 'column', 'createby'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('sys_role')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'updatetime')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'sys_role', 'column', 'updatetime'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '修改时间',
   'user', @CurrentUser, 'table', 'sys_role', 'column', 'updatetime'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('sys_role')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'updateby')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'sys_role', 'column', 'updateby'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '修改人;jAVA后台写入为-1,存储过程批量处理写入为-2,其它情况为真实的用户ID',
   'user', @CurrentUser, 'table', 'sys_role', 'column', 'updateby'
go

/*==============================================================*/
/* Table: sys_role_resource                                     */
/*==============================================================*/
create table sys_role_resource (
   id                   int                  identity,
   resource_id          int                  null,
   role_id              int                  null,
   constraint PK_SYS_ROLE_RESOURCE primary key nonclustered (id)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('sys_role_resource') and minor_id = 0)
begin 
   declare @CurrentUser sysname 
select @CurrentUser = user_name() 
execute sp_dropextendedproperty 'MS_Description',  
   'user', @CurrentUser, 'table', 'sys_role_resource' 
 
end 


select @CurrentUser = user_name() 
execute sp_addextendedproperty 'MS_Description',  
   '角色资源', 
   'user', @CurrentUser, 'table', 'sys_role_resource'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('sys_role_resource')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'resource_id')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'sys_role_resource', 'column', 'resource_id'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '资源编号',
   'user', @CurrentUser, 'table', 'sys_role_resource', 'column', 'resource_id'
go

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user (
   user_id              varchar(11)          null,
   user_name            varchar(50)          null,
   phone                varchar(11)          null,
   gender               varchar(1)           null,
   active               int                  null,
   age                  int                  null,
   password             varchar(255)         null,
   createtime           datetime             null,
   createby             varchar(50)          null,
   updatetime           datetime             null,
   updateby             varchar(50)          null,
   constraint PK_SYS_USER primary key nonclustered ()
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('sys_user') and minor_id = 0)
begin 
   declare @CurrentUser sysname 
select @CurrentUser = user_name() 
execute sp_dropextendedproperty 'MS_Description',  
   'user', @CurrentUser, 'table', 'sys_user' 
 
end 


select @CurrentUser = user_name() 
execute sp_addextendedproperty 'MS_Description',  
   '用户表', 
   'user', @CurrentUser, 'table', 'sys_user'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('sys_user')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'createtime')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'sys_user', 'column', 'createtime'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '创建时间',
   'user', @CurrentUser, 'table', 'sys_user', 'column', 'createtime'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('sys_user')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'createby')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'sys_user', 'column', 'createby'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '创建人;jAVA后台写入为-1,存储过程批量处理写入为-2,其它情况为真实的用户ID',
   'user', @CurrentUser, 'table', 'sys_user', 'column', 'createby'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('sys_user')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'updatetime')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'sys_user', 'column', 'updatetime'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '修改时间',
   'user', @CurrentUser, 'table', 'sys_user', 'column', 'updatetime'
go

if exists(select 1 from sys.extended_properties p where
      p.major_id = object_id('sys_user')
  and p.minor_id = (select c.column_id from sys.columns c where c.object_id = p.major_id and c.name = 'updateby')
)
begin
   declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_dropextendedproperty 'MS_Description', 
   'user', @CurrentUser, 'table', 'sys_user', 'column', 'updateby'

end


select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '修改人;jAVA后台写入为-1,存储过程批量处理写入为-2,其它情况为真实的用户ID',
   'user', @CurrentUser, 'table', 'sys_user', 'column', 'updateby'
go

/*==============================================================*/
/* Table: sys_user_role                                         */
/*==============================================================*/
create table sys_user_role (
   id                   int                  identity,
   role_id              int                  null,
   constraint PK_SYS_USER_ROLE primary key nonclustered (id)
)
go

if exists (select 1 from  sys.extended_properties
           where major_id = object_id('sys_user_role') and minor_id = 0)
begin 
   declare @CurrentUser sysname 
select @CurrentUser = user_name() 
execute sp_dropextendedproperty 'MS_Description',  
   'user', @CurrentUser, 'table', 'sys_user_role' 
 
end 


select @CurrentUser = user_name() 
execute sp_addextendedproperty 'MS_Description',  
   '用户角色', 
   'user', @CurrentUser, 'table', 'sys_user_role'
go

alter table sys_role_resource
   add constraint FK_SYS_ROLE_REFERENCE_SYS_RESO foreign key (resource_id)
      references sys_resources (id)
go

alter table sys_role_resource
   add constraint FK_SYS_ROLE_REFERENCE_SYS_ROLE foreign key (role_id)
      references sys_role (id)
go

alter table sys_user_role
   add constraint FK_SYS_USER_REFERENCE_SYS_USER foreign key ()
      references sys_user
go

alter table sys_user_role
   add constraint FK_SYS_USER_REFERENCE_SYS_ROLE foreign key (role_id)
      references sys_role (id)
go

