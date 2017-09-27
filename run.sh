sudo docker stop dac
sudo docker rm dac
mvn clean install
sudo docker rmi projeto/app
sudo docker build -t projeto/app .
sudo docker run -p 8080:8080 -p 3700:3700 -d --name dac projeto/app
