sudo docker stop json
sudo docker rm json
sudo docker rmi projeto/json
mvn clean install
sudo docker build -t projeto/json .
sudo docker run -d --name json --link banco:host-banco projeto/json
