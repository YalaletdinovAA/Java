FROM postgres:13-alpine

COPY init.sql /docker-entrypoint-initdb.d/

ENV POSTGRES_DB=television_db
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres

EXPOSE 5432