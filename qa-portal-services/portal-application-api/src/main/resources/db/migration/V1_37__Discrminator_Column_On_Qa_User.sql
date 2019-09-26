alter table training.qa_user rename column role to discriminator;

alter table training.qa_user alter column discriminator set default 'PORTAL';