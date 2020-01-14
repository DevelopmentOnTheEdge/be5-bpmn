<#include 'camunda_db'/>

DELETE FROM users WHERE user_name = 'Administrator';
DELETE FROM user_roles WHERE user_name = 'Administrator';

INSERT INTO users( user_name, user_pass ) VALUES( 'Administrator', '12345' );
INSERT INTO user_roles VALUES( 'Administrator', 'Administrator' );
INSERT INTO user_roles VALUES( 'Administrator', 'SystemDeveloper' );
INSERT INTO user_roles VALUES( 'Administrator', 'Operator' );

DELETE FROM users WHERE user_name = 'Operator';
DELETE FROM user_roles WHERE user_name = 'Operator';

INSERT INTO users( user_name, user_pass ) VALUES( 'Operator', '12345' );
INSERT INTO user_roles VALUES( 'Operator', 'Operator' );
