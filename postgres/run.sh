sudo docker stop banco
sudo docker rm banco
#sudo docker projeto/banco
sudo docker build -t projeto/banco .
sudo docker run -p 5433:5432 -d --name banco projeto/banco
