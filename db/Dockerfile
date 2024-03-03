# Use the latest MySQL image
FROM mysql
# Set the working directory
WORKDIR /tmp
#Copy all the files to the working directory container
COPY ./world-db/*.sql /tmp/
# Copy SQL script into the container
COPY ./world-db /docker-entrypoint-initdb.d
# Set the root password
ENV MYSQL_ROOT_PASSWORD example