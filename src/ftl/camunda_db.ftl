CREATE SCHEMA IF NOT EXISTS ${CAMUNDA_SCHEMA};

SET search_path to ${CAMUNDA_SCHEMA};

<#include 'camunda/postgres_engine_7.12.0'/>
<#include 'camunda/postgres_identity_7.12.0'/>

SET search_path to 'public';
