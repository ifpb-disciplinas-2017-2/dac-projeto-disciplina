sudo docker stop jse
sudo docker rm jse 
sudo docker rmi projeto/jse
sudo docker build -t projeto/jse .
sudo docker run -d --name jse --link dac:host-core projeto/jse
sudo docker logs -f jse
