sudo docker stop dac
sudo docker rm dac
mvn clean install
sudo docker rmi projeto/web
sudo docker build -t projeto/web .
sudo docker run -p 8081:8080 -p 3700:3700 -d --name dac --link banco:host-banco projeto/web
