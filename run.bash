docker run -d --network host --name rabbit-broker -p 15672:15672 -p 5672:5672 rabbitmq:latest
docker run -d --network host --name couchbase -p 8091:8091 -p 8092:8092 -p 8093:8093 -p 8094:8094 -p 8095:8095 -p 8096:8096 -p 11210:11210 -p 11211:11211 couchbase:latest
docker run -d --network host --name balance-server -p 8080:8080 balance/server:latest
sleep 20
bash ./init-couchbase.sh
sleep 5
bash ./init-view.sh
sleep 5
docker run -d --network host --name balance-client balance/client:latest