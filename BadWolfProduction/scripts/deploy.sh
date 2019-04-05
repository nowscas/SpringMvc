#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/id_rsa \
 target/BadWolfProduction-1.0-SNAPSHOT.jar \
 badwolfproduction@185.251.39.96:/home/badwolfproduction/

echo ' Restart server...'

ssh -i ~/.ssh/id_rsa badwolfproduction@185.251.39.96 << EOF

pgrep java | xargs kill -9
nohup java -jar BadWolfProduction-1.0-SNAPSHOT.jar > log.txt &

EOF

echo 'Bye'